package controlador;

import com.lowagie.text.pdf.PdfWriter;
import modelo.Formulamedica;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.FormulamedicaFacade;
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
import modelo.Citapersona;
import modelo.Datospersona;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

@ManagedBean(name = "formulamedicaController")
@SessionScoped
public class FormulamedicaController implements Serializable {

    private Formulamedica current;
    private DataModel items = null;
    @EJB
    private fachada.FormulamedicaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public FormulamedicaController() {
    }

    public Formulamedica getSelected() {
        if (current == null) {
            current = new Formulamedica();
            selectedItemIndex = -1;
        }
        return current;
    }

    private FormulamedicaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(1000000000) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, actAgenCit));
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
        current = (Formulamedica) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
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
        current = new Formulamedica();
        selectedItemIndex = -1;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FormulamedicaCreated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        current.setCodigoCita(actAgenCit);
        current.setCodigoPersona(actDatPern);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        //current.getCodigoCita().setBotExFisico3("N");
    }

    public String prepareEdit() {
        current = (Formulamedica) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FormulamedicaUpdated"));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());
    }

    public String destroy() {
        current = (Formulamedica) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FormulamedicaDeleted"));
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

    @FacesConverter(forClass = Formulamedica.class)
    public static class FormulamedicaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FormulamedicaController controller = (FormulamedicaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "formulamedicaController");
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
            if (object instanceof Formulamedica) {
                Formulamedica o = (Formulamedica) object;
                return getStringKey(o.getConsecutivo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Formulamedica.class.getName());
            }
        }

    }

    public Formulamedica getCurrent() {
        return current;
    }

    public void setCurrent(Formulamedica current) {
        this.current = current;
    }

    public FormulamedicaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(FormulamedicaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

//---------------- codigo para asignar citas a una persona---------------------//
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
//---------------------- codigo para asignar persona a una cita -----------------------------//
    public void recreateModel(Datospersona datPer) {
        actDatPern = datPer;
        items = null;
    }
    private Datospersona actDatPern;

    public Datospersona getActDatPern() {
        return actDatPern;
    }

    public void setActDatPern(Datospersona actDatPern) {
        this.actDatPern = actDatPern;
    }
//-------------------------------------------------------------------------------------------//
    
    
//----------------------------------------------------Parametos de fecha --------------------//
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

//------------------------------------Reportes---------------------------------------------------------//
    private HashMap agregarParametros(HashMap parametros) throws Exception {

        SimpleDateFormat sdfy = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdfx = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");

        parametros.put("condicion", "");

        parametros.put("fechaHoraElabora", sdfy.format(new Date()));
        parametros.put("TituloReporte", "XXXXXXXXXXX");
        return parametros;
    }

    //-------------------------Parametros Reportes ----------------//
    //---------- Fin Parametros Reportes ----------------//
    //--------- Reporte Consumo de combustible por Regional ------------------------//
    public void reportConsecutivo(Formulamedica codigoCita) {

        FacesContext fcontext = FacesContext.getCurrentInstance();

        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "reportConsecutivo";
            String tituloReporte = "Reporte Consecutivo";
            String tipoConexion = "sisac";

            String tipoSalida = "pdf";
            String nrd = "";
            //Establezco la conexión

            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("sisac");
            conexion = ds.getConnection();

            //Se crean los parametros y se asignan valores
            HashMap parametros = new HashMap();
            String condicion = "";

            //----ingresamos el parameto para generar le reporte----
            parametros = agregarParametros(parametros);
            //  parametros.put("fechaInicial", fechaInicial);
            // parametros.put("fechaFinal", fechaFinal);
            //parametros.put("codigoRegional", current.getCodigoRegional().getCodigoRegional());
            parametros.put("consecutivo", codigoCita.getCodigoCita());

            ServletContext theApplicationsServletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaArchivo = theApplicationsServletContext.getRealPath("/reportes") + File.separator;
            String reporte = rutaArchivo + nombreReporte + nrd + ".jasper";

            parametros.put("SUBREPORT_DIR", rutaArchivo);
            //--- codigo para llamar la imagen logo
            parametros.put("imagen", rutaArchivo + "NLogo.jpg");
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
                exporter1.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, "GNGD INML");
                exporter1.setParameter(JRPdfExporterParameter.IS_ENCRYPTED, Boolean.TRUE);
                exporter1.setParameter(JRPdfExporterParameter.PERMISSIONS, new Integer(PdfWriter.AllowPrinting | PdfWriter.HideToolbar | PdfWriter.HideToolbar));
                exporter1.setParameter(JRPdfExporterParameter.OWNER_PASSWORD, "Gr");
                exporter1.setParameter(JRPdfExporterParameter.IS_128_BIT_KEY, Boolean.TRUE);

                exporter1.exportReport();
                exporter1 = null;
            }
            jasperPrint = null;
            ctx = null;

        } catch (NamingException ex) {
            Logger.getLogger(Formulamedica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Formulamedica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(Formulamedica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Formulamedica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "Error al momento de imprimir. " + ex.getMessage());
            Logger.getLogger(Formulamedica.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            } catch (Exception ex) {
            }
        }
        fcontext.responseComplete();
    }
//---------------- aqui termina el Reporte consulta consumo de combustible por regional ------------------------------//

}
