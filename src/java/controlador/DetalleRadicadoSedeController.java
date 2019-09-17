package controlador;

import com.lowagie.text.pdf.PdfWriter;
import modelo.DetalleRadicadoSede;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.DetalleRadicadoSedeFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import modelo.Pais;
import modelo.Departamento;
import modelo.Municipio;
import modelo.ClasificacionEps;
import modelo.Eps;
import modelo.ListaRadicadoEps;
import modelo.Sede;
import modelo.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

@ManagedBean(name = "detalleRadicadoSedeController")
@SessionScoped
public class DetalleRadicadoSedeController implements Serializable {

    private List<DetalleRadicadoSede> filteredDetalleRadicadoSede;

    public List<DetalleRadicadoSede> getFilteredDetalleRadicadoSede() {
        return filteredDetalleRadicadoSede;
    }

    public void setFilteredDetalleRadicadoSede(List<DetalleRadicadoSede> filteredDetalleRadicadoSede) {
        this.filteredDetalleRadicadoSede = filteredDetalleRadicadoSede;
    }

    private DetalleRadicadoSede current;
    private DataModel items = null;
    private Pais paisSeleted;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private ClasificacionEps clasificacionEpsSelected;
    private Eps epsSelected;
    private Sede sedeSelected;
    private Usuario usuarioSelected;

    @EJB
    private fachada.DetalleRadicadoSedeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public DetalleRadicadoSedeController() {
    }

    public DetalleRadicadoSede getSelected() {
        if (current == null) {
            current = new DetalleRadicadoSede();
            selectedItemIndex = -1;
        }
        return current;
    }

    private DetalleRadicadoSedeFacade getFacade() {
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
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, actDetRadicadoEps));
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
        current = (DetalleRadicadoSede) getItems().getRowData();
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
        current = new DetalleRadicadoSede();
        selectedItemIndex = -1;
        current = null;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleRadicadoSedeCreated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        current.setCodigoUsuario(getUsuarioController().getSelected());
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        current.setCodigoListadoRadicado(actDetRadicadoEps);
        current.setCodigoEps(current.getCodigoFactura().getCodigoEps());
        current.setCodigoPersona(current.getCodigoFactura().getCodigoPersona());
        current.setNumeroDocumentoIdentidad(current.getCodigoFactura().getNumeroDocumento());
        current.setValorFacturaTotal(current.getCodigoFactura().getValorUnitario());
        current.setValorCopago(current.getCodigoFactura().getValorCopago());
        current.setValorFacturado(current.getCodigoFactura().getValorTotalFactura());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaSistema(new Date());
        current.setEstado("P");
        current.setGlosa("N");
        current.setValorCanceladoEps(BigDecimal.valueOf(0));
        current.setValorAceptadoEps(BigDecimal.valueOf(0));
        current.setSaldoListado(BigDecimal.valueOf(0));
        current.setValorGlosa(BigDecimal.valueOf(0));
        current.setValorAceptado(BigDecimal.valueOf(0));
        current.setValorReclamado(BigDecimal.valueOf(0));
        current.setSaldoRadicado(current.getValorFacturado());
        current.setSaldoTotal(current.getValorFacturado());
        current.getCodigoFactura().setEstadoFactura("R");
        current.setValorLista(actDetRadicadoEps.getValorLista().add(current.getValorFacturado()));
        current.getCodigoListadoRadicado().setValorLista(current.getValorLista());

    }

    public String prepareEdit() {
        current = (DetalleRadicadoSede) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

//-------------Codigo para modificar utilizando filtro ----------------------//
    public String prepareEditDetalleRadicadoSede(DetalleRadicadoSede item) {
        current = item;
        return "Edit";
    }
//------------------------------------------------------------------------------//

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleRadicadoSedeUpdated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {

    }

    public String destroy() {
        current = (DetalleRadicadoSede) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DetalleRadicadoSedeDeleted"));
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

    @FacesConverter(forClass = DetalleRadicadoSede.class)
    public static class DetalleRadicadoSedeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetalleRadicadoSedeController controller = (DetalleRadicadoSedeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detalleRadicadoSedeController");
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
            if (object instanceof DetalleRadicadoSede) {
                DetalleRadicadoSede o = (DetalleRadicadoSede) object;
                return getStringKey(o.getCodigoDetalleRadicado());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetalleRadicadoSede.class.getName());
            }
        }

    }

    public DetalleRadicadoSede getCurrent() {
        return current;
    }

    public void setCurrent(DetalleRadicadoSede current) {
        this.current = current;
    }

    public DetalleRadicadoSedeFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DetalleRadicadoSedeFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public Pais getPaisSeleted() {
        return paisSeleted;
    }

    public void setPaisSeleted(Pais paisSeleted) {
        this.paisSeleted = paisSeleted;
    }

    public Departamento getDepartamentoSelected() {
        return departamentoSelected;
    }

    public void setDepartamentoSelected(Departamento departamentoSelected) {
        this.departamentoSelected = departamentoSelected;
    }

    public Municipio getMunicipioSelected() {
        return municipioSelected;
    }

    public void setMunicipioSelected(Municipio municipioSelected) {
        this.municipioSelected = municipioSelected;
    }

    public ClasificacionEps getClasificacionEpsSelected() {
        return clasificacionEpsSelected;
    }

    public void setClasificacionEpsSelected(ClasificacionEps clasificacionEpsSelected) {
        this.clasificacionEpsSelected = clasificacionEpsSelected;
    }

    public Eps getEpsSelected() {
        return epsSelected;
    }

    public void setEpsSelected(Eps epsSelected) {
        this.epsSelected = epsSelected;
    }

    public Sede getSedeSelected() {
        return sedeSelected;
    }

    public void setSedeSelected(Sede sedeSelected) {
        this.sedeSelected = sedeSelected;
    }

    public Usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    /**
     * Pasar un Lista radicado EPS
     *
     * @param detRadicadoEps
     */
    public void recreateModel(ListaRadicadoEps detRadicadoEps) {
        actDetRadicadoEps = detRadicadoEps;
        items = null;
    }
    private ListaRadicadoEps actDetRadicadoEps;

    public ListaRadicadoEps getActDetRadicadoEps() {
        return actDetRadicadoEps;
    }

    public void setActDetRadicadoEps(ListaRadicadoEps actDetRadicadoEps) {
        this.actDetRadicadoEps = actDetRadicadoEps;
    }

    public SelectItem[] getRadicadoXEps() {
        if (current.getCodigoEps() != null) {
            return JsfUtil.getSelectItems(current.getCodigoEps().getFacturacionList(), false);
        }
        return null;
    }

    public String prepareDetallePagoEps(DetalleRadicadoSede item) {
        current = item;
        return prepareListPagoEpsSedeList();
    }

    public String prepareGlosaDevEps(DetalleRadicadoSede item) {
        current = item;
        return prepareListGlosaDevEpsSedeList();
    }

    /**
     * Pasar una a una las facturas de la lista para pago EPS
     */
    public void recreateModel(DetalleRadicadoSede detRadEpsPago) {
        actDetRadEpsPago = detRadEpsPago;
        items = null;
    }
    private DetalleRadicadoSede actDetRadEpsPago;

    public DetalleRadicadoSede getActDetRadEpsPago() {
        return actDetRadEpsPago;
    }

    public void setActDetRadEpsPago(DetalleRadicadoSede actDetRadEpsPago) {
        this.actDetRadEpsPago = actDetRadEpsPago;
    }
    @ManagedProperty("#{pagosEpsController}")
    protected PagosEpsController pagosEpsController;

    public PagosEpsController getPagosEpsController() {
        return pagosEpsController;
    }

    public void setPagosEpsController(PagosEpsController pagosEpsController) {
        this.pagosEpsController = pagosEpsController;
    }

    public String prepareListPagoEpsSede() {
        current = getFacade().find(((DetalleRadicadoSede) getItems().getRowData()).getCodigoDetalleRadicado());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getPagosEpsController().recreateModel(current);
        return "/vista/pagosEps/List";
    }

    public String prepareListPagoEpsSedeList() {
        getPagosEpsController().recreateModel(current);
        return "/vista/pagosEps/List";
    }

    @ManagedProperty("#{glosaEpsController}")
    protected GlosaEpsController glosaEpsController;

    public GlosaEpsController getGlosaEpsController() {
        return glosaEpsController;
    }

    public void setGlosaEpsController(GlosaEpsController glosaEpsController) {
        this.glosaEpsController = glosaEpsController;
    }

    public String prepareListGlosaDevEpsSede() {
        current = getFacade().find(((DetalleRadicadoSede) getItems().getRowData()).getCodigoDetalleRadicado());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getGlosaEpsController().recreateModel(current);
        return "/vista/glosaEps/List";
    }

    public String prepareListGlosaDevEpsSedeList() {
        getGlosaEpsController().recreateModel(current);
        return "/vista/glosaEps/List";
    }

//---------------------------Parametros reportes----------------------------------------//
    /*
    Parametros Fecha
     */
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

    private HashMap agregarParametros(HashMap parametros) throws Exception {

        SimpleDateFormat sdfy = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdfx = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");

        parametros.put("condicion", "");

        parametros.put("fechaHoraElabora", sdfy.format(new Date()));
        parametros.put("TituloReporte", "XXXXXXXXXXX");
        return parametros;
    }

    /*
    Reporte RIPS AC
     */
    public void detalleRadicado() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "detalleRadicado";
            String tituloReporte = "Reporte Detalle Radicado";
            String tipoConexion = "serapis";

            String tipoSalida = "xls";
            String nrd = "";
            //Establezco la conexión

            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("serapis");
            conexion = ds.getConnection();

            //Se crean los parametros y se asignan valores
            HashMap parametros = new HashMap();
            String condicion = "";

            //----ingresamos el parameto para generar le reporte----
            Calendar inicio = Calendar.getInstance();
            inicio.setTime(fechaInicial);
            inicio.set(Calendar.HOUR, 0);
            inicio.set(Calendar.MINUTE, 0);
            inicio.set(Calendar.SECOND, 0);

            Calendar fin = Calendar.getInstance();
            fin.setTime(fechaFinal);
            fin.set(Calendar.HOUR_OF_DAY, 23);
            fin.set(Calendar.MINUTE, 59);
            fin.set(Calendar.SECOND, 59);

            parametros = agregarParametros(parametros);
            parametros.put("fechaInicial", new Timestamp(inicio.getTimeInMillis()));
            parametros.put("fechaFinal", new Timestamp(fin.getTimeInMillis()));
            parametros.put("codigoSede", usuarioController.getSelected().getCodigoSede().getCodigoSede());

            ServletContext theApplicationsServletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaArchivo = theApplicationsServletContext.getRealPath("/ReportesEcxcel") + File.separator;
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
                response.setContentType("application/xls");

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
            Logger.getLogger(DetalleRadicadoSede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DetalleRadicadoSede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(DetalleRadicadoSede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DetalleRadicadoSede.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "Error al momento de imprimir. " + ex.getMessage());
            Logger.getLogger(DetalleRadicadoSede.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            } catch (Exception ex) {
            }
        }
        fcontext.responseComplete();
    }
//----------------------------------------------------------------------------------------------------//    

}
