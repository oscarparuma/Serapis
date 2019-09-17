package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.ClasificacionEpsFacade;

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
import modelo.ClasificacionEps;
import modelo.Sede;
import modelo.Usuario;
import modelo.Pais;


@ManagedBean(name = "clasificacionEpsController")
@SessionScoped
public class ClasificacionEpsController implements Serializable {
    
    private ClasificacionEps current;
    private DataModel items = null;
    
    @EJB
    private fachada.ClasificacionEpsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Sede sedeSelected;
    private Usuario usuarioSelected;
    private Pais paisSelected;
    
    
    public ClasificacionEpsController() {
    }
    
    public ClasificacionEps getSelected() {
        if (current == null) {
            current = new ClasificacionEps();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    private ClasificacionEpsFacade getFacade() {
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
        current = (ClasificacionEps) getItems().getRowData();
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
        current = new ClasificacionEps();
        selectedItemIndex = -1;
         recreateModel();
        return "Create";
    }
    
    public String create() {
        try {
            guardar();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClasificacionEpsCreated"));
            recreateModel();
            return prepareListXClasificacionEps(usuarioController.getSelected());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    private void guardar() {
        current.setCodigoUsuario(getUsuarioController().getSelected());
        current.setCodigoPais(getUsuarioController().getSelected().getCodigoPais());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.setActivo("S");
        
        
    }
    
    public String prepareEdit() {
        current = (ClasificacionEps) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
//-------------Codigo para modificar utilizando filtro ----------------------//
   public String prepareEditBloque(ClasificacionEps item) {
        current = item;
        return "Edit";
    }
//------------------------------------------------------------------------------//
    
    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClasificacionEpsUpdated"));
            return prepareListXClasificacionEps(usuarioController.getSelected());
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
        current = (ClasificacionEps) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClasificacionEpsDeleted"));
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
    
    @FacesConverter(forClass = ClasificacionEps.class)
    public static class ClasificacionEpsControllerConverter implements Converter {
        
        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClasificacionEpsController controller = (ClasificacionEpsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clasificacionEpsController");
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
            if (object instanceof ClasificacionEps) {
                ClasificacionEps o = (ClasificacionEps) object;
                return getStringKey(o.getCodigoClasificacionEps());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ClasificacionEps.class.getName());
            }
        }
        
    }

    public ClasificacionEps getCurrent() {
        return current;
    }

    public void setCurrent(ClasificacionEps current) {
        this.current = current;
    }

    public ClasificacionEpsFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ClasificacionEpsFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
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

    public Usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    public Pais getPaisSelected() {
        return paisSelected;
    }

    public void setPaisSelected(Pais paisSelected) {
        this.paisSelected = paisSelected;
    }

    
   
//------ se creo este metodo para el filtro de las Clasificacion Aseguradoras de la sede------------//
     private List<ClasificacionEps> listClasificacionEps;

    public List<ClasificacionEps> getListClasificacionEps() {
        return listClasificacionEps;
    }

    public void setListClasificacionEps(List<ClasificacionEps> listClasificacionEps) {
        this.listClasificacionEps = listClasificacionEps;
    }
   
//----------------------Lista de Clasificacion de EPS por Pais  por Dependencia Usuario --------------------------//
      public List<ClasificacionEps> getSedeXClasificacionEps(Pais codigoPais) {
        return ejbFacade.findAll(codigoPais);
    }
//------------------------------------------------------------------------------------------//
      
//-----------------------------Consultorio Sede---------------------------------------------//     
private List<ClasificacionEps> listaClasificacionEps;

    public List<ClasificacionEps> getListaClasificacionEps() {
        return listaClasificacionEps;
    }

    public void setListaClasificacionEps(List<ClasificacionEps> listaClasificacionEps) {
        this.listaClasificacionEps = listaClasificacionEps;
    }


    public String prepareListXClasificacionEps(Usuario usuario) {
        listaClasificacionEps = getFacade().consXSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/clasificacionEps/List";

    }
//------------------------------------------------------------------------------------------------------------------------------------------------//
    
}
