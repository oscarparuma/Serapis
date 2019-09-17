package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.ListaRadicadoEpsFacade;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import modelo.CarteraSede;
import modelo.ListaRadicadoEps;
import modelo.Pais;
import modelo.Departamento;
import modelo.Municipio;
import modelo.ClasificacionEps;
import modelo.Eps;
import modelo.Sede;
import modelo.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

@ManagedBean(name = "listaRadicadoEpsController")
@SessionScoped
public class ListaRadicadoEpsController implements Serializable {

    private List<ListaRadicadoEps> filteredListaRadicadoEps;

    public List<ListaRadicadoEps> getFilteredListaRadicadoEps() {
        return filteredListaRadicadoEps;
    }

    public void setFilteredListaRadicadoEps(List<ListaRadicadoEps> filteredListaRadicadoEps) {
        this.filteredListaRadicadoEps = filteredListaRadicadoEps;
    }

    private ListaRadicadoEps current;
    private DataModel items = null;
    private Pais paisSeleted;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private ClasificacionEps clasificacionEpsSelected;
    private Eps epsSelected;
    private Sede sedeSelected;
    private Usuario usuarioSelected;

    @EJB
    private fachada.ListaRadicadoEpsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ListaRadicadoEpsController() {
    }

    public ListaRadicadoEps getSelected() {
        if (current == null) {
            current = new ListaRadicadoEps();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ListaRadicadoEpsFacade getFacade() {
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
        current = (ListaRadicadoEps) getItems().getRowData();
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
        current = new ListaRadicadoEps();
        selectedItemIndex = -1;
        current = null;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ListaRadicadoEpsCreated"));
            return prepareListXListaRadicadoEps(usuarioController.getSelected());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        current.setCodigoUsuario(getUsuarioController().getSelected());
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaSistema(new Date());
        current.setEstado("P");
        current.setValorLista(BigDecimal.valueOf(0));

    }

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ListaRadicadoEpsCreated"));
            //return prepareListXListaRadicadoEps(usuarioController.getSelected());
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

    }

    private void modificar() {
        current.setEstado("E");
        current.setFechaFinalizado(new Date());
        current.setCodigoUsuarioFinalizo(getUsuarioController().getSelected());

    }

    public String updateRadicado() {
        try {
            radicar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ListaRadicadoEpsCreated"));
            //return prepareListXListaRadicadoEps(usuarioController.getSelected());
            return "ViewRadicado";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }

    }

    private void radicar() {
        current.setEstado("R");
        current.setFechaRadicadoEps(new Date());
        current.setCodigoUsuarioFinalizo(getUsuarioController().getSelected());

    }

    public String prepareEdit() {
        current = (ListaRadicadoEps) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

//-------------Codigo para modificar utilizando filtro ----------------------//
    public String prepareEditListaRadicadoEps(ListaRadicadoEps item) {
        current = item;
        return "Edit";
    }

    public String prepareEditListaRadicadoEpsRips(ListaRadicadoEps item) {
        current = item;
        return "MenuRips";
    }

    public String prepareEditListaRadicadoEpsSede(ListaRadicadoEps item) {
        current = item;
        return "EditRadicadoEps";
    }

//------------------------------------------------------------------------------//
    public String destroy() {
        current = (ListaRadicadoEps) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ListaRadicadoEpsDeleted"));
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

    @FacesConverter(forClass = ListaRadicadoEps.class)
    public static class ListaRadicadoEpsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ListaRadicadoEpsController controller = (ListaRadicadoEpsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "listaRadicadoEpsController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ListaRadicadoEps) {
                ListaRadicadoEps o = (ListaRadicadoEps) object;
                return getStringKey(o.getConsecutivoRacadoEps());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ListaRadicadoEps.class.getName());
            }
        }

    }

    public ListaRadicadoEps getCurrent() {
        return current;
    }

    public void setCurrent(ListaRadicadoEps current) {
        this.current = current;
    }

    public ListaRadicadoEpsFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ListaRadicadoEpsFacade ejbFacade) {
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
     * Pasar un concecutivo a un seguimiento
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

    public String prepareDetalleRadicado(ListaRadicadoEps item) {
        current = item;
        return prepareListDetalleRadicadoSedeList();
    }

    public String prepareDetalleCarteraSede(ListaRadicadoEps item) {
        current = item;
        return prepareListDetalleCarteraSedeList();
    }

    public String prepareGlosaDev(ListaRadicadoEps item) {
        current = item;
        return prepareListGlosaDevSedeList();
    }

    public String prepareConsultaRadicadoSede(ListaRadicadoEps item) {
        current = item;
        return prepareListConsultaRadicacionSedeList();
    }
    
//------ se creo este metodo para el filtro de las eps por sede----------------------------//
//--------------------------------------Eps por sede-----------------------------------------//     
    private List<ListaRadicadoEps> listaRadicadoEps;

    public List<ListaRadicadoEps> getListaRadicadoEps() {
        return listaRadicadoEps;
    }

    public void setListaRadicadoEps(List<ListaRadicadoEps> listaRadicadoEps) {
        this.listaRadicadoEps = listaRadicadoEps;
    }

    public String prepareListXListaRadicadoEps(Usuario usuario) {
        listaRadicadoEps = getFacade().consXListaRadicado(usuario.getCodigoSede().getCodigoSede());
        return "/vista/listaRadicadoEps/List";
    }

    private List<ListaRadicadoEps> listaRadicadoEpsFecha;

    public List<ListaRadicadoEps> getListaRadicadoEpsFecha() {
        return listaRadicadoEpsFecha;
    }

    public void setListaRadicadoEpsFecha(List<ListaRadicadoEps> listaRadicadoEpsFecha) {
        this.listaRadicadoEpsFecha = listaRadicadoEpsFecha;
    }

    public String prepareListXListaRadicadoEpsFecha(Usuario usuario) {
        listaRadicadoEpsFecha = getFacade().consXListaRadicado(usuario.getCodigoSede().getCodigoSede());
        return "/vista/listaRadicadoEps/ListRadicado";
    }
//----------------------------------------------------------------------------------------------------------------------//
    @ManagedProperty("#{detalleRadicadoSedeController}")
    protected DetalleRadicadoSedeController detalleRadicadoSedeController;

    public DetalleRadicadoSedeController getDetalleRadicadoSedeController() {
        return detalleRadicadoSedeController;
    }

    public void setDetalleRadicadoSedeController(DetalleRadicadoSedeController detalleRadicadoSedeController) {
        this.detalleRadicadoSedeController = detalleRadicadoSedeController;
    }

    public String prepareListDetalleRadicadoSede() {
        current = getFacade().find(((ListaRadicadoEps) getItems().getRowData()).getConsecutivoRacadoEps());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getDetalleRadicadoSedeController().recreateModel(current);
        return "/vista/detalleRadicadoSede/List";
    }

    public String prepareListDetalleRadicadoSedeList() {
        getDetalleRadicadoSedeController().recreateModel(current);
        return "/vista/detalleRadicadoSede/List";
    }

    public String prepareListDetalleCarteraSedeList() {
        getDetalleRadicadoSedeController().recreateModel(current);
        return "/vista/detalleRadicadoSede/ListCarteraSede";
    }

    public String prepareListGlosaDevSedeList() {
        getDetalleRadicadoSedeController().recreateModel(current);
        return "/vista/detalleRadicadoSede/ListGlosaDevSede";
    }
    
     public String prepareListConsultaRadicacionSedeList() {
        getDetalleRadicadoSedeController().recreateModel(current);
        return "/vista/detalleRadicadoSede/ListDetalleConsultaRadicado";
    }

    private List<ListaRadicadoEps> listaRadicadoEpsRips;

    public List<ListaRadicadoEps> getListaRadicadoEpsRips() {
        return listaRadicadoEpsRips;
    }

    public void setListaRadicadoEpsRips(List<ListaRadicadoEps> listaRadicadoEpsRips) {
        this.listaRadicadoEpsRips = listaRadicadoEpsRips;
    }

    public String prepareListXListaRadicadoEpsRips(Usuario usuario) {
        listaRadicadoEpsRips = getFacade().consXListaRadicadoRips(usuario.getCodigoSede().getCodigoSede());
        return "/vista/listaRadicadoEps/ListRips";
    }

    private List<ListaRadicadoEps> listaRadicadoEpsSede;

    public List<ListaRadicadoEps> getListaRadicadoEpsSede() {
        return listaRadicadoEpsSede;
    }

    public void setListaRadicadoEpsSede(List<ListaRadicadoEps> listaRadicadoEpsSede) {
        this.listaRadicadoEpsSede = listaRadicadoEpsSede;
    }

    public String prepareListXListaRadicadoEpsSede(Usuario usuario) {
        listaRadicadoEpsSede = getFacade().consXListaRadicadoEpsSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/listaRadicadoEps/ListRadicadoEpsSede";
    }

    private List<ListaRadicadoEps> listaCarteraEpsSede;

    public List<ListaRadicadoEps> getListaCarteraEpsSede() {
        return listaCarteraEpsSede;
    }

    public void setListaCarteraEpsSede(List<ListaRadicadoEps> listaCarteraEpsSede) {
        this.listaCarteraEpsSede = listaCarteraEpsSede;
    }

    public String prepareListXListaCarteraEpsSede(Usuario usuario) {
        listaCarteraEpsSede = getFacade().consXListaCarteraEpsSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/listaRadicadoEps/ListCarteraSede";
    }

    private List<ListaRadicadoEps> listaGlosaDevEpsSede;

    public List<ListaRadicadoEps> getListaGlosaDevEpsSede() {
        return listaGlosaDevEpsSede;
    }

    public void setListaGlosaDevEpsSede(List<ListaRadicadoEps> listaGlosaDevEpsSede) {
        this.listaGlosaDevEpsSede = listaGlosaDevEpsSede;
    }

    public String prepareListGlosaDevSedeList(Usuario usuario) {
        listaCarteraEpsSede = getFacade().consXListaGlosaDevEpsSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/listaRadicadoEps/ListGlosaDevSede";
    }

//---------------------------------------------Reportes y Consultas ----------------------------------------//
//----------------------------------------------------------------------------------------------------------//

    /*
    Archivos RIPS excell    
     */
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

    private List<ListaRadicadoEps> listaListaRadicadoEps = new ArrayList<>();

    public List<ListaRadicadoEps> getListaListaRadicadoEps() {
        return listaListaRadicadoEps;
    }

    public void setListaListaRadicadoEps(List<ListaRadicadoEps> listaListaRadicadoEps) {
        this.listaListaRadicadoEps = listaListaRadicadoEps;
    }

    public String listEgresoConsoliXSede(Usuario usuario) {
        listaListaRadicadoEps = getFacade().listReporEgresosXSede(usuario.getCodigoSede().getCodigoSede(), fechaInicial, fechaFinal);
        return "/vista/reportes/ListRadicadoEpsSede";
    }

    private HashMap agregarParametros(HashMap parametros) throws Exception {

        SimpleDateFormat sdfy = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdfx = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");

        parametros.put("condicion", "");

        parametros.put("fechaHoraElabora", sdfy.format(new Date()));
        parametros.put("TituloReporte", "XXXXXXXXXXX");
        return parametros;
    }
//--------------------------------------------------------------------------------------//

    /*
    Reporte RIPS AC
     */
    public void AC() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "AC";
            String tituloReporte = "Reporte RIPS AC";
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
            parametros = agregarParametros(parametros);
            parametros.put("consecutivoRacadoEps", current.getConsecutivoRacadoEps());
            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);

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
//----------------------------------------------------------------------------------------------------//

    /*
    Reporte RIPS AF
     */
    public void AF() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "AF";
            String tituloReporte = "Reporte RIPS AF";
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
            parametros = agregarParametros(parametros);
            parametros.put("consecutivoRacadoEps", current.getConsecutivoRacadoEps());
            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);

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
//----------------------------------------------------------------------------------------------------//
/*
    Reporte RIPS US
     */
    public void US() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "US";
            String tituloReporte = "Reporte RIPS US";
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
            parametros = agregarParametros(parametros);
            parametros.put("consecutivoRacadoEps", current.getConsecutivoRacadoEps());
            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);

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

    /*
    Reporte RIPS CT
     */
    public void CT() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "CT";
            String tituloReporte = "Reporte RIPS CT";
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
            parametros = agregarParametros(parametros);
            parametros.put("consecutivoRacadoEps", current.getConsecutivoRacadoEps());
            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);

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

//----------------------------------------------------------------------------------------------------//
//========================================== Archivos RIPS TXT ======================================//

    /*
    Reporte RIPS AC
     */
    public void TXTAC() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "TXTAC";
            String tituloReporte = "Rips AC TXT";
            String tipoConexion = "serapis";

            String tipoSalida = "csv";
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
            parametros.put("consecutivoRacadoEps", current.getConsecutivoRacadoEps());

            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);
            ServletContext theApplicationsServletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaArchivo = theApplicationsServletContext.getRealPath("/ReportesTxt") + File.separator;
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
            } else if (tipoSalida.equals("csv")) {
                JRCsvExporter exporter = new JRCsvExporter();
                response.setContentType("text/csv");

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreReporte);
                exporter.exportReport();
                exporter = null;
            } else {
                JRPdfExporter exporter1 = new JRPdfExporter();
                response.setContentType("application/pdf");

                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter1.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter1.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, "INML");

                exporter1.exportReport();
                exporter1 = null;
            }
            jasperPrint = null;
            ctx = null;

        } catch (NamingException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "Error al momento de imprimir. " + ex.getMessage());
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            } catch (Exception ex) {
            }
        }
        fcontext.responseComplete();
    }

    /*
    Reporte RIPS AC
     */
    public void TXTAF() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "TXTAF";
            String tituloReporte = "Rips AF TXT";
            String tipoConexion = "serapis";

            String tipoSalida = "csv";
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
            parametros.put("consecutivoRacadoEps", current.getConsecutivoRacadoEps());

            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);
            ServletContext theApplicationsServletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaArchivo = theApplicationsServletContext.getRealPath("/ReportesTxt") + File.separator;
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
            } else if (tipoSalida.equals("csv")) {
                JRCsvExporter exporter = new JRCsvExporter();
                response.setContentType("text/csv");

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreReporte);
                exporter.exportReport();
                exporter = null;
            } else {
                JRPdfExporter exporter1 = new JRPdfExporter();
                response.setContentType("application/pdf");

                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter1.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter1.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, "INML");

                exporter1.exportReport();
                exporter1 = null;
            }
            jasperPrint = null;
            ctx = null;

        } catch (NamingException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "Error al momento de imprimir. " + ex.getMessage());
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            } catch (Exception ex) {
            }
        }
        fcontext.responseComplete();
    }

    /*
    Reporte RIPS AC
     */
    public void TXTUS() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "TXTUS";
            String tituloReporte = "Rips US TXT";
            String tipoConexion = "serapis";

            String tipoSalida = "csv";
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
            parametros.put("consecutivoRacadoEps", current.getConsecutivoRacadoEps());

            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);
            ServletContext theApplicationsServletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaArchivo = theApplicationsServletContext.getRealPath("/ReportesTxt") + File.separator;
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
            } else if (tipoSalida.equals("csv")) {
                JRCsvExporter exporter = new JRCsvExporter();
                response.setContentType("text/csv");

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreReporte);
                exporter.exportReport();
                exporter = null;
            } else {
                JRPdfExporter exporter1 = new JRPdfExporter();
                response.setContentType("application/pdf");

                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter1.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter1.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, "INML");

                exporter1.exportReport();
                exporter1 = null;
            }
            jasperPrint = null;
            ctx = null;

        } catch (NamingException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "Error al momento de imprimir. " + ex.getMessage());
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            } catch (Exception ex) {
            }
        }
        fcontext.responseComplete();
    }

    /*
    Reporte RIPS AC
     */
    public void TXTCT() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "TXTCT";
            String tituloReporte = "Rips CT TXT";
            String tipoConexion = "serapis";

            String tipoSalida = "csv";
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
            parametros.put("consecutivoRacadoEps", current.getConsecutivoRacadoEps());

            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);
            ServletContext theApplicationsServletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaArchivo = theApplicationsServletContext.getRealPath("/ReportesTxt") + File.separator;
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
            } else if (tipoSalida.equals("csv")) {
                JRCsvExporter exporter = new JRCsvExporter();
                response.setContentType("text/csv");

                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreReporte);
                exporter.exportReport();
                exporter = null;
            } else {
                JRPdfExporter exporter1 = new JRPdfExporter();
                response.setContentType("application/pdf");

                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter1.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter1.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, "INML");

                exporter1.exportReport();
                exporter1 = null;
            }
            jasperPrint = null;
            ctx = null;

        } catch (NamingException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "Error al momento de imprimir. " + ex.getMessage());
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            } catch (Exception ex) {
            }
        }
        fcontext.responseComplete();
    }

//===============================================Reportes PDF===============================================================//
    /*
    Reporte Lista de radicado EPS}
     */
    public void ListadoEps() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "ListadoEps";
            String tituloReporte = "Reporte ListadoEps";
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
            parametros.put("consecutivoRacadoEps", current.getConsecutivoRacadoEps());
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
                exporter1.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, "INML");

                exporter1.exportReport();
                exporter1 = null;
            }
            jasperPrint = null;
            ctx = null;

        } catch (NamingException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "Error al momento de imprimir. " + ex.getMessage());
            Logger.getLogger(ListaRadicadoEps.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            } catch (Exception ex) {
            }
        }
        fcontext.responseComplete();
    }

}
