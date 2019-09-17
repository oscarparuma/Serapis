package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.SedeFacade;
import java.io.IOException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.PhaseEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Pais;
import modelo.Departamento;
import modelo.EstadoSede;
import modelo.Municipio;
import modelo.Sede;
import modelo.Usuario;

@ManagedBean(name = "sedeController")
@SessionScoped
public class SedeController implements Serializable {

    private Sede current;
    private DataModel items = null;

    @EJB
    private fachada.SedeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Pais paisSelected;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private Sede sedeSelected;
    private Usuario usuarioSelected;

    public SedeController() {
    }

    public Sede getSelected() {
        if (current == null) {
            current = new Sede();
            selectedItemIndex = -1;
        }
        return current;
    }

    private SedeFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

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
        current = (Sede) getItems().getRowData();
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
        current = new Sede();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SedeCreated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.setActivo("S");

    }

    public String prepareCreateSede() {
        current = new Sede();
        selectedItemIndex = -1;
        recreateModel();
        return "/vista/usuario/Create";
    }

    public String createDatosSede() {
        try {
            guardarDatosSede();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SedeCreated"));
            return "/indexSede.xhtml";
        } catch (Exception e) {
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PersistenciaSede"));
            //JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("sedePersistencia"));
            return null;
        }
    }

    private void guardarDatosSede() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.setActivo("S");
        current.setEstadoRadicacion("A");
        current.setCodigoEstadoSede(new EstadoSede((int) 4));
        current.setCodigoRegistro(new EstadoSede((5)));

    }

    public String prepareEdit() {
        current = (Sede) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SedeUpdated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {
        current.setCodigoUsuarioModifico(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());
    }

    public String destroy() {
        current = (Sede) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("SedeDeleted"));
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

    @FacesConverter(forClass = Sede.class)
    public static class SedeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SedeController controller = (SedeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "sedeController");
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
            if (object instanceof Sede) {
                Sede o = (Sede) object;
                return getStringKey(o.getCodigoSede());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Sede.class.getName());
            }
        }

    }

    public Sede getCurrent() {
        return current;
    }

    public void setCurrent(Sede current) {
        this.current = current;
    }

    public SedeFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(SedeFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
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
//--------ajax para refrescar los campos Pais, departamento, municipio---------//

    public void tipoPaisChange(ValueChangeEvent e) {
        paisSelected = (Pais) e.getNewValue();
        departamentoSelected = null;
        municipioSelected = null;
    }
//------------------------------------------------------------------------------//

//------------------------------------------Reporte Consecutivos por dependencia-----------------------------------------------------------------//     
    private List<Sede> listaSede;

    public List<Sede> getListaSede() {
        return listaSede;
    }

    public void setListaSede(List<Sede> listaSede) {
        this.listaSede = listaSede;
    }

    public String prepareListXSede(Usuario usuario) {
        listaSede = getFacade().consXSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/sede/List";

    }
//----------------------------------------------------------------------------------------------------------------------//

    //----------------------------------- consulta por numero de identificacion Sede --------------------------------------------------------//
    private List<Sede> listaIdentificacionNit;

    public List<Sede> getListaIdentificacionNit() {
        return listaIdentificacionNit;
    }

    public void setListaIdentificacionNit(List<Sede> listaIdentificacionNit) {
        this.listaIdentificacionNit = listaIdentificacionNit;
    }

    /**
     * @lalmeida Pasar una sede al usuario
     */
//--------------- ingresamos codigo para verificar si ya existe la sede ------------------//
    private String identificacionNit = new String();

    public String getIdentificacionNit() {
        return identificacionNit;
    }

    public void setIdentificacionNit(String identificacionNit) {
        this.identificacionNit = identificacionNit;
    }

    public String prepareListconsXCedulaSede() {
        listaIdentificacionNit = getFacade().consXNitSede(identificacionNit);
        //return "/vista/datospersona/ListaDatosPersona";
        return "/vista/sede/ListaDatosSedeIngreso";
    }

//------------------------------------------------------------------------------------------------------------------------//
    /**
     *
     * Validación Usuario Consultorio
     */
    public String validarUsuario() {
        String identificacionNit = current.getIdentificacionNit();
        String clave = current.getClave();
        try {
            current = ejbFacade.validarUsuario(identificacionNit, clave);
        } finally {
            if (current == null) {
                current = new Sede();
                JsfUtil.addErrorMessage("Usuario y/o Clave Invalida");
                // loggedIn = true;
                return null;

            }

            //loggedIn = true;
            JsfUtil.addSuccessMessage("Bienvenid@ " + current.getNombreSede());
            //return "/vista/administracion/ListIngresoJunta";
            return "/vista/sede/ViewUsuario";
            //return "/vista/datosRegistroSede/List";
        }
    }

    public void beforePhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        ExternalContext ext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        HttpSession session = (HttpSession) ext.getSession(false);
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "must-revalidate");
        response.addHeader("Expires", "0");
        boolean newSession = (session == null) || (session.isNew() || !isLoggedIn());
        boolean postback = !ext.getRequestParameterMap().isEmpty();
        boolean timedout = postback && newSession;
        if (timedout || !isLoggedIn()) {
            try {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Acceso negado", "SesiÃ³n finalizada"));
                ExternalContext extContext = facesContext.getExternalContext();
                String url = extContext.encodeActionURL(facesContext.getApplication().getViewHandler().getActionURL(facesContext, "/index.xhtml"));

                extContext.redirect(url);
                facesContext.renderResponse();
                facesContext.responseComplete();
            } catch (IOException ex) {
            }
        }
    }
    private boolean loggedIn = false;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
//------------------------------------------------------------------------------------------------------------------------//

    /**
     * @ Lalmeida Pasar una sede a un usuario
     */

    public void recreateModel(Sede datSede) {
        actDatSede = datSede;
        items = null;
    }
    private Sede actDatSede;

    public Sede getActDatSede() {
        return actDatSede;
    }

    public void setActDatSede(Sede actDatSede) {
        this.actDatSede = actDatSede;
    }

//-----------------------------------------------------------------------------//
    @ManagedProperty("#{usuarioController}")
    protected UsuarioController usuarioController1;

    public UsuarioController getUsuarioController1() {
        return usuarioController1;
    }

    public void setUsuarioController1(UsuarioController usuarioController1) {
        this.usuarioController1 = usuarioController1;
    }

    public String prepareListUsuarioSede() {
        current = getFacade().find(((Usuario) getItems().getRowData()).getIdentificacionNit());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getUsuarioController1().recreateModel(current);
        return "/vista/registrarUsuarioSistema/RegistroUsuarioSede";
    }

    public String prepareListUsuarioSedeList() {
        getUsuarioController1().recreateModel(current);
        return "/vista/registrarUsuarioSistema/RegistroUsuarioSede";
    }
//------------------------------------------------------------------------------------------------------------------------------------------------//  

    public void PaisChange(ValueChangeEvent e) {
        paisSelected = (Pais) e.getNewValue();
        departamentoSelected = null;
        municipioSelected = null;
    }

}
