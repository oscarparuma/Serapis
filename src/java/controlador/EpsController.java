package controlador;

import modelo.Eps;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.EpsFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import modelo.Sede;
import modelo.Usuario;
import modelo.Pais;
import modelo.Departamento;
import modelo.Municipio;
import modelo.Eps;
import modelo.ClasificacionEps;
import modelo.EpsSede;

@ManagedBean(name = "epsController")
@SessionScoped
public class EpsController implements Serializable {

    private Eps current;
    private DataModel items = null;
    @EJB
    private fachada.EpsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Pais paisSelected;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private Eps epsSelected;
    private ClasificacionEps clasificacionEpsSelected;
    private EpsSede epsSedeSelected;

    public EpsController() {
    }

    public Eps getSelected() {
        if (current == null) {
            current = new Eps();
            selectedItemIndex = -1;
        }
        return current;
    }

    private EpsFacade getFacade() {
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
        current = (Eps) getItems().getRowData();
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
        current = new Eps();
        selectedItemIndex = -1;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EpsCreated"));
            return prepareListXEps(usuarioController.getSelected());
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
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
    }

    public String prepareEdit() {
        current = (Eps) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

     public String prepareEditEps(Eps item) {
        current = item;
        return "Edit";
    }
    
    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EpsUpdated"));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {
        current.setUsuarioModifico(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());
        current.setActivo("N");
    }

    public String destroy() {
        current = (Eps) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EpsDeleted"));
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

    @FacesConverter(forClass = Eps.class)
    public static class EpsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EpsController controller = (EpsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "epsController");
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
            if (object instanceof Eps) {
                Eps o = (Eps) object;
                return getStringKey(o.getCodigoEps());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Eps.class.getName());
            }
        }

    }

    public Eps getCurrent() {
        return current;
    }

    public void setCurrent(Eps current) {
        this.current = current;
    }

    public EpsFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(EpsFacade ejbFacade) {
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

    public Eps getEpsSelected() {
        return epsSelected;
    }

    public void setEpsSelected(Eps epsSelected) {
        this.epsSelected = epsSelected;
    }

    public ClasificacionEps getClasificacionEpsSelected() {
        return clasificacionEpsSelected;
    }

    public void setClasificacionEpsSelected(ClasificacionEps clasificacionEpsSelected) {
        this.clasificacionEpsSelected = clasificacionEpsSelected;
    }

    public EpsSede getEpsSedeSelected() {
        return epsSedeSelected;
    }

    public void setEpsSedeSelected(EpsSede epsSedeSelected) {
        this.epsSedeSelected = epsSedeSelected;
    }
    
    
    
//------ se creo este metodo para el filtro de las eps por sede----------------------------//
    private List<Eps> listEps;
    

    public List<Eps> getListEps() {
        return listEps;
    }

    public void setListEps(List<Eps> listEps) {
        this.listEps = listEps;
    }
//----------------------Lista de carpetas por Dependencia Usuario --------------------------//

    public List<Eps> getEpsXSede(Sede codigoSede) {
        return ejbFacade.findAll(codigoSede);
    }
    
     
//------------------------------------------------------------------------------------------//
    
//--------------------------------------Eps por sede-----------------------------------------//     
     
    private List<Eps> listaEps;  

    public List<Eps> getListaEps() {
        return listaEps;
    }

    public void setListaEps(List<Eps> listaEps) {
        this.listaEps = listaEps;
    }

    public String prepareListXEps(Usuario usuario) {
        listaEps = getFacade().consXEps(usuario.getCodigoSede().getCodigoSede());
        return "/vista/eps/List";

    }
//----------------------------------------------------------------------------------------------------------------------//


}
