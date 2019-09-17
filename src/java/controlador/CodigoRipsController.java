package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.CodigoRipsFacade;

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
import modelo.CodigoRips;
import modelo.Procedimientosede;
import modelo.Sede;
import modelo.Usuario;

@ManagedBean(name = "codigoRipsController")
@SessionScoped
public class CodigoRipsController implements Serializable {

    private CodigoRips current;
    private DataModel items = null;

    @EJB
    private fachada.CodigoRipsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public CodigoRipsController() {
    }

    public CodigoRips getSelected() {
        if (current == null) {
            current = new CodigoRips();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CodigoRipsFacade getFacade() {
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
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, actProcSede));
                    
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
        current = (CodigoRips) getItems().getRowData();
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
        current = new CodigoRips();
        selectedItemIndex = -1;
        current = null;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CodigoRipsCreated"));
            recreateModel();
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {

        current.setCodigoRipsAnterior(actProcSede.getCodigoRips());
        current.setCodigoProcedimintoSede(actProcSede);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.getCodigoProcedimintoSede().setCodigoRips(current.getCodigoRips());

    }

    public String prepareEdit() {
        current = (CodigoRips) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return prepareEditCodigoRips(current);
    }

//-------------Codigo para modificar utilizando filtro ----------------------//
    public String prepareEditCodigoRips(CodigoRips item) {
        current = item;
        return "Edit";
    }
//------------------------------------------------------------------------------//

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CodigoRipsUpdated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {

    }

    public String destroy() {
        current = (CodigoRips) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CodigoRipsDeleted"));
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

    @FacesConverter(forClass = CodigoRips.class)
    public static class CodigoRipsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CodigoRipsController controller = (CodigoRipsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "codigoRipsController");
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
            if (object instanceof CodigoRips) {
                CodigoRips o = (CodigoRips) object;
                return getStringKey(o.getCodigoRipsSede());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + CodigoRips.class.getName());
            }
        }

    }

    public CodigoRips getCurrent() {
        return current;
    }

    public void setCurrent(CodigoRips current) {
        this.current = current;
    }

    public CodigoRipsFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CodigoRipsFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

//------ se creo este metodo para el filtro de las Arl de la sede------------//
    private List<CodigoRips> listCodigoRips;

    public List<CodigoRips> getListCodigoRips() {
        return listCodigoRips;
    }

    public void setListCodigoRips(List<CodigoRips> listCodigoRips) {
        this.listCodigoRips = listCodigoRips;
    }

//----------------------Lista de Arl por Sede --------------------------//
    public List<CodigoRips> getSedeXCodigoRips(Sede codigoSede) {
        return ejbFacade.findAll(codigoSede);
    }
//------------------------------------------------------------------------------------------//

//-----------------------------Consultorio Sede---------------------------------------------//     
    private List<CodigoRips> listaCodigoRips;

    public List<CodigoRips> getListaCodigoRips() {
        return listaCodigoRips;
    }

    public void setListaCodigoRips(List<CodigoRips> listaCodigoRips) {
        this.listaCodigoRips = listaCodigoRips;
    }

    public String prepareListXCodigoRips(Usuario usuario) {
        listaCodigoRips = getFacade().consXSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/codigoRips/List";

    }
//------------------------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Codigo Modificar Rips
     *
     */
    public void recreateModel(Procedimientosede procSede) {
        actProcSede = procSede;
        items = null;
    }

    private Procedimientosede actProcSede;

    public Procedimientosede getActProcSede() {
        return actProcSede;
    }

    public void setActProcSede(Procedimientosede actProcSede) {
        this.actProcSede = actProcSede;
    }

//------------------------------------------------------------------------------//
}
