package controlador;

import modelo.CarteraSede;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.CarteraSedeFacade;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modelo.Sede;
import modelo.Dependencia;
import modelo.Area;
import modelo.Citapersona;
import modelo.Facturacion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

@ManagedBean(name = "carteraSedeController")
@SessionScoped
public class CarteraSedeController implements Serializable {
    
    private CarteraSede current;
    private DataModel items = null;
    
    @EJB
    private fachada.CarteraSedeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Sede sedeSelected;
    private Dependencia dependenciaSelected;
    private Area areaSelected;
    
    public CarteraSedeController() {
    }
    
    public CarteraSede getSelected() {
        if (current == null) {
            current = new CarteraSede();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    private CarteraSedeFacade getFacade() {
        return ejbFacade;
    }
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(100000000) {
                
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }
                
                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }
    
    public String prepareList() {
        recreateModel();
        return "List";
    }
    
    public String prepareView() {
        current = (CarteraSede) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
     public String prepareWiewP(CarteraSede item) {
        current = item;
        return "View";
    }
    
    @ManagedProperty("#{usuarioController}")
    protected UsuarioController usuarioController;
    
    public UsuarioController getUsuarioController() {
        return usuarioController;
    }
    
    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }
    
    public String prepareCreate() {
        current = new CarteraSede();
        selectedItemIndex = -1;
        recreateModel();
        return "Create";
    }
    
    public String create() {
        try {
            guardar();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarteraSedeCreated"));
           // recreateModel();
            return prepareWiewP(current);
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            
            return null;
        }
    }
    
    private void guardar() {
        current.setCodigoFacturacion(actFact);
        current.setCodigoCitaPersona(actFact.getCodigoCitaPersona());
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        current.setValorTotalfactura(actFact.getValorUnitario());
        current.setEstadoRecibo("P");
        
        
        long calcularRadicado = getFacade().calcularRadicado(current.getCodigoSede().getCodigoSede());
        current.setCcsRecibo(calcularRadicado);
        String reciboPago
                = sdf.format(new Date())
                + current.getCodigoSede().getSiglasSede() //El acronimo de la dependencia
                + String.format("%05d", current.getCcsRecibo());// El año...
        current.setReciboPago(reciboPago);
    }
    
    public String prepareEdit() {
        current = (CarteraSede) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarteraSedeUpdated"));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();
            return null;
        }
    }
    
    public String destroy() {
        current = (CarteraSede) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }
    
    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }
    
    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CarteraSedeDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }
    
    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }
    
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    private void recreateModel() {
        items = null;
    }
    
    private void recreatePagination() {
        pagination = null;
    }
    
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }
    
    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }
    
    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }
    
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }
    
    @FacesConverter(forClass = CarteraSede.class)
    public static class CarteraSedeControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CarteraSedeController controller = (CarteraSedeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "carteraSedeController");
            return controller.ejbFacade.find(getKey(value));
        }
        
        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }
        
        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }
        
        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof CarteraSede) {
                CarteraSede o = (CarteraSede) object;
                return getStringKey(o.getCodigoCartera());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CarteraSede.class.getName());
            }
        }
        
    }
    
    public CarteraSede getCurrent() {
        return current;
    }
    
    public void setCurrent(CarteraSede current) {
        this.current = current;
    }
    
    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }
    
    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }
    
    public Sede getSedeSelected() {
        return sedeSelected;
    }
    
    public void setSedeSelected(Sede sedeSelected) {
        this.sedeSelected = sedeSelected;
    }
    
    public Dependencia getDependenciaSelected() {
        return dependenciaSelected;
    }
    
    public void setDependenciaSelected(Dependencia dependenciaSelected) {
        this.dependenciaSelected = dependenciaSelected;
    }
    
    public Area getAreaSelected() {
        return areaSelected;
    }
    
    public void setAreaSelected(Area areaSelected) {
        this.areaSelected = areaSelected;
    }

//---------------- codigo para asignar citas a una factura---------------------//
    public void recreateModel(Citapersona agenda) {
        actAgenCit = agenda;
        items = null;
    }
    private Citapersona actAgenCit;
    
    public Citapersona getActAgenCit() {
        return actAgenCit;
    }
    
    public void setActAgenCit(Citapersona actAgenCit) {
        this.actAgenCit = actAgenCit;
    }

//------------------------------------------------------------------------------//
//---------------- codigo para asignar factura al pago-------------------------//
    public void recreateModel(Facturacion fact) {
        actFact = fact;
        items = null;
    }
    
    private Facturacion actFact;
    
    public Facturacion getActFact() {
        return actFact;
    }
    
    public void setFact(Facturacion actFact) {
        this.actFact = actFact;
    }

//------------------------------------------------------------------------------//    
//----------------------------------------------------Parametos de fecha ------------------------------------------------------------------//
    private Date fechaInicial = new Date();
    private Date fechaFinal = new Date();
    
    public Date getFechaFinal() {
        return fechaFinal;
    }
    
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    
    public Date getFechaInicial() {
        return fechaInicial;
    }
    
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------//
    private HashMap agregarParametros(HashMap parametros) throws Exception {
        
        SimpleDateFormat sdfy = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdfx = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
        
        parametros.put("condicion", "");
        
        parametros.put("fechaHoraElabora", sdfy.format(new Date()));
        parametros.put("TituloReporte", "XXXXXXXXXXX");
        return parametros;
    }

//-------------------------- Reporte Concepto Final -----------------------------------------------------------------//
    public void reportReciboPago() {       
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;
      
        try {
            HttpServletResponse response = null;
            String nombreReporte = "reportReciboPago";
            String tituloReporte = "Reporte Recibo de Pago";
            String tipoConexion = "serapis";
            
            String tipoSalida = "pdf";
            String nrd = "";
            //Establezco la conexión

            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("serapis");
            conexion = ds.getConnection();

            //Se crean los parametros y se asignan valores
            HashMap parametros = new HashMap();
            String condicion = "";

            //----ingresamos el parameto para generar le reporte----
            parametros = agregarParametros(parametros);
            parametros.put("codigoCartera", current.getCodigoCartera());
            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);

             
            
            
            ServletContext theApplicationsServletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaArchivo = theApplicationsServletContext.getRealPath("/ReportesPDF") + File.separator;
            String reporte = rutaArchivo + nombreReporte + nrd + ".jasper";
            
            parametros.put("SUBREPORT_DIR", rutaArchivo);
            //--- codigo para llamar la imagen logo
            //parametros.put("imagen", rutaArchivo + "escudo.jpg");
            net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
            //JasperPrintManager.printReport(jasperPrint, true);

            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            response = (HttpServletResponse) ectx.getResponse();
            response.setHeader("Content-disposition", "attachment; filename=" + nombreReporte + "." + tipoSalida);
            if (tipoSalida.equals("xls")) {
                JRXlsExporter exporter = new JRXlsExporter();
                response.setContentType("application/vnd.ms-excel");
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporter.exportReport();
                exporter = null;
            } else {
                JRPdfExporter exporter1 = new JRPdfExporter();
                response.setContentType("application/pdf");
                
                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter1.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter1.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, "DESOME");
                
                exporter1.exportReport();
                exporter1 = null;
            }
            jasperPrint = null;
            ctx = null;
            
        } catch (NamingException ex) {
            Logger.getLogger(CarteraSede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CarteraSede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(CarteraSede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CarteraSede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "Error al momento de imprimir. " + ex.getMessage());
            Logger.getLogger(CarteraSede.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            } catch (Exception ex) {
            }
        }
        fcontext.responseComplete();
    }

//---------------- aqui termina el Reporte Rwecibo Pago usuario ------------------------------//    
}
