package controlador;

import modelo.Usuario;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.UsuarioFacade;
import java.io.IOException;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.PhaseEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Datospersona;
import modelo.Sede;
import modelo.Area;
import modelo.Dependencia;
import modelo.Pais;
import modelo.Departamento;
import modelo.Municipio;
import modelo.Tipoperfil;

@Named("usuarioController")
@SessionScoped
public class UsuarioController implements Serializable {

    private Usuario current;
    private DataModel items = null;
    @EJB
    private fachada.UsuarioFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Usuario usuarioNuevo;
    private Area areaUsuarioNuevo;
    private Dependencia dependenciaUsuarioNuevo;
    private Pais paisUsuarioNuevo;
    private Departamento departamentoUsuarioNuevo;
    private Municipio municipioUsuarioNuevo;
    private Sede sedeSelected;
    private Area areaSelected;
    private Dependencia dependenciaSelected;
    private Pais paisSelected;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;

    public UsuarioController() {
    }

    public Usuario getSelected() {
        if (current == null) {
            current = new Usuario();
            selectedItemIndex = -1;
        }
        return current;
    }

    public Usuario getUsuarioNuevo() {
        if (usuarioNuevo == null) {
            usuarioNuevo = new Usuario();
            selectedItemIndex = -1;
        }

        return usuarioNuevo;
    }

    public void setUsuarioNuevo(Usuario usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    private UsuarioFacade getFacade() {
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
        usuarioNuevo = (Usuario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String createUsuario() {
        try {
            guardarUsuario();
            getFacade().edit(usuarioNuevo);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
            return prepareListXUsuarioRU(current);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();
            return null;
        }
    }

    private void guardarUsuario() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        usuarioNuevo.setFechaRegistro(new Date());
        usuarioNuevo.setActivo("S");
        usuarioNuevo.setCodigoUsuarioRegistro(current);
        usuarioNuevo.setCodigoSede(current.getCodigoSede());
        usuarioNuevo.setCodigoPais(current.getCodigoPais());
    }

    public String prepareCreate() {
        usuarioNuevo = new Usuario();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareCreateSede() {
        usuarioNuevo = new Usuario();
        selectedItemIndex = -1;
        recreateModel();
        return "/vista/registrarUsuarioSistema/Create.xhtml?faces-redirect=true";
    }

    public String createSede() {
        try {
            usuarioSede();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioCreated"));
            return "/vista/menuSistema/MenuIngresos.xhtml?faces-redirect=true";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void usuarioSede() {
        current.setFechaRegistro(new Date());
        current.setActivo("S");
        current.setCodigoSede(actDatSede);
        current.setCodigoMunicipio(actDatSede.getCodigoMunicipio());
        current.setIdentificacionNit(actDatSede.getIdentificacionNit());
        current.setCodigoPais(actDatSede.getCodigoPais());
        current.setCodigoPerfil(new Tipoperfil((int) 2));

    }

    public String prepareEdit() {
        current = (Usuario) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Usuario) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UsuarioDeleted"));
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

    public Usuario getUsuario(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Usuario.class)
    public static class UsuarioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioController controller = (UsuarioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioController");
            return controller.getUsuario(getKey(value));
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
            if (object instanceof Usuario) {
                Usuario o = (Usuario) object;
                return getStringKey(o.getCodigoUsuario());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Usuario.class.getName());
            }
        }

    }

    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
    }

    public UsuarioFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(UsuarioFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public Area getAreaUsuarioNuevo() {
        return areaUsuarioNuevo;
    }

    public void setAreaUsuarioNuevo(Area areaUsuarioNuevo) {
        this.areaUsuarioNuevo = areaUsuarioNuevo;
    }

    public Dependencia getDependenciaUsuarioNuevo() {
        return dependenciaUsuarioNuevo;
    }

    public void setDependenciaUsuarioNuevo(Dependencia dependenciaUsuarioNuevo) {
        this.dependenciaUsuarioNuevo = dependenciaUsuarioNuevo;
    }

    public Pais getPaisUsuarioNuevo() {
        return paisUsuarioNuevo;
    }

    public void setPaisUsuarioNuevo(Pais paisUsuarioNuevo) {
        this.paisUsuarioNuevo = paisUsuarioNuevo;
    }

    public Departamento getDepartamentoUsuarioNuevo() {
        return departamentoUsuarioNuevo;
    }

    public void setDepartamentoUsuarioNuevo(Departamento departamentoUsuarioNuevo) {
        this.departamentoUsuarioNuevo = departamentoUsuarioNuevo;
    }

    public Municipio getMunicipioUsuarioNuevo() {
        return municipioUsuarioNuevo;
    }

    public void setMunicipioUsuarioNuevo(Municipio municipioUsuarioNuevo) {
        this.municipioUsuarioNuevo = municipioUsuarioNuevo;
    }

    public SelectItem[] getSedeXArea() {
        if (usuarioNuevo.getCodigoSede() != null) {
            return JsfUtil.getSelectItems(usuarioNuevo.getCodigoSede().getAreaList(), false);
        }
        return null;
    }

    public SelectItem[] getAreaXDependnecia() {
        if (usuarioNuevo.getCodigoArea() != null) {
            return JsfUtil.getSelectItems(usuarioNuevo.getCodigoArea().getDependenciaList(), false);
        }
        return null;
    }

    public String validarUsuario() {
        Long codigoUsuario = current.getCodigoUsuario();
        String clave = current.getClave();
        try {
            current = ejbFacade.validarUsuario(codigoUsuario, clave);
        } finally {

            if (current == null) {
                current = new Usuario();
                JsfUtil.addErrorMessage("Usuario y/o Clave Invalida");
                return null;
            }
            if (current.getCodigoPerfil().getCodigoPerfil() == 1) {
                loggedIn = true;
                JsfUtil.addSuccessMessage("Bienvenid@ " + current.getNombreUsuario());
                return "/vista/menuSistema/MenuIngresos";

            }

            if (current.getCodigoPerfil().getCodigoPerfil() == 2) {
                loggedIn = true;
                JsfUtil.addSuccessMessage("Bienvenid@ " + current.getNombreUsuario());
                return "/vista/menuSistema/MenuIngresos";

            }

            if (current.getCodigoPerfil().getCodigoPerfil() == 3) {
                loggedIn = true;
                JsfUtil.addSuccessMessage("Bienvenid@ " + current.getNombreUsuario());
                return "/vista/menuSistema/MenuConsultas";

            }

            if (current.getCodigoPerfil().getCodigoPerfil() == 4) {
                loggedIn = true;
                JsfUtil.addSuccessMessage("Bienvenid@ " + current.getNombreUsuario());
                return "/vista/menuSistema/MenuConsultas";

            }

            if (current.getCodigoPerfil().getCodigoPerfil() == 5) {
                loggedIn = true;
                JsfUtil.addSuccessMessage("Bienvenid@ " + current.getNombreUsuario());
                return "/vista/menuSistema/MenuConsultas";

            }

            if (current.getCodigoPerfil().getCodigoPerfil() == 6) {
                loggedIn = true;
                JsfUtil.addSuccessMessage("Bienvenid@ " + current.getNombreUsuario());
                return "/vista/citapersona/List";
            }

            loggedIn = true;
            JsfUtil.addSuccessMessage("Bienvenid@ " + current.getNombreUsuario());

            //return "/vista/datospersona/ListRecepcion";
            return "/vista/administracion/ListAdministracion";
        }
    }

    public void salir() {
        loggedIn = false;
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
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Acceso negado", "Sesión finalizada"));
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

//------ se creo este metodo para el filtro de los consultorios sede------------//
    private List<Usuario> listUsuario;

    public List<Usuario> getListConsultorio() {
        return listUsuario;
    }

    public void setListConsultorio(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }
//----------------------Lista de carpetas por Dependencia Usuario --------------------------//

    public List<Usuario> getSedeXUsuario(Sede codigoSede) {
        return ejbFacade.findAll(codigoSede);
    }
//------------------------------------------------------------------------------------------//

//------------------------------------------Reporte Usuario por Sede------------------------//     
    private List<Usuario> listaUsuario;

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String prepareListXUsuario(Usuario usuario) {
        listaUsuario = getFacade().consXUsuario(usuario.getCodigoSede().getCodigoSede());
        return "/vista/citaPersonaProfesional/MedicosSede";

    }

    private List<Usuario> listaRegUsuario;

    public List<Usuario> getListaRegUsuario() {
        return listaRegUsuario;
    }

    public void setListaRegUsuario(List<Usuario> listaRegUsuario) {
        this.listaRegUsuario = listaRegUsuario;
    }

    public String prepareListXUsuarioRU(Usuario usuario) {
        listaRegUsuario = getFacade().consXUsuario(usuario.getCodigoSede().getCodigoSede());
        return "/vista/registrarUsuarioSistema/List";

    }
//----------------------------------------------------------------------------------------------------------------------//

//---------------------- Asignar medico a una cita --------------------------------------------------------------------//
    public void recreateModel(Usuario datUsuario) {
        actDatUsuario = datUsuario;
        items = null;
    }

    private Usuario actDatUsuario;

    public Usuario getActDatUsuario() {
        return actDatUsuario;
    }

    public void setActDatUsuario(Usuario actDatUsuario) {
        this.actDatUsuario = actDatUsuario;
    }
//-------------------------------------------------------------------------------------------//
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

    public Sede getSedeSelected() {
        return sedeSelected;
    }

    public void setSedeSelected(Sede sedeSelected) {
        this.sedeSelected = sedeSelected;
    }

    public Area getAreaSelected() {
        return areaSelected;
    }

    public void setAreaSelected(Area areaSelected) {
        this.areaSelected = areaSelected;
    }

    public Dependencia getDependenciaSelected() {
        return dependenciaSelected;
    }

    public void setDependenciaSelected(Dependencia dependenciaSelected) {
        this.dependenciaSelected = dependenciaSelected;
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
}
