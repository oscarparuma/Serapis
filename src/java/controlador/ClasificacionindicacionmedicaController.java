
package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.ClasificacionindicacionmedicaFacade;

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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import modelo.Clasificacionindicacionmedica;

import modelo.Sede;
import modelo.Usuario;


@ManagedBean(name = "clasificacionindicacionmedicaController")
@SessionScoped
public class ClasificacionindicacionmedicaController implements Serializable {

    private Clasificacionindicacionmedica current;
    private DataModel items = null;
   
    @EJB
    private fachada.ClasificacionindicacionmedicaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private Sede sedeSelected;
    

    public ClasificacionindicacionmedicaController() {
    }

    public Clasificacionindicacionmedica getSelected() {
        if (current == null) {
            current = new Clasificacionindicacionmedica();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ClasificacionindicacionmedicaFacade getFacade() {
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
        current = (Clasificacionindicacionmedica) getItems().getRowData();
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
        current = new Clasificacionindicacionmedica();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClasificacionindicacionmedicaCreated"));
            recreateModel();
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();
            return null;
        }
    }

    private void guardar() {
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.setActvo("S");
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());

    }

    public String prepareEdit() {
        current = (Clasificacionindicacionmedica) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String prepareEditClasfIndMedic(Clasificacionindicacionmedica item) {
        current = item;
        return "Edit";
    }
    
    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClasificacionindicacionmedicaUpdated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {
        current.setUsuarioModifico(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());
    }

    public String destroy() {
        current = (Clasificacionindicacionmedica) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ClasificacionindicacionmedicaDeleted"));
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

    @FacesConverter(forClass = Clasificacionindicacionmedica.class)
    public static class ClasificacionindicacionmedicaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClasificacionindicacionmedicaController controller = (ClasificacionindicacionmedicaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clasificacionindicacionmedicaController");
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
            if (object instanceof Clasificacionindicacionmedica) {
                Clasificacionindicacionmedica o = (Clasificacionindicacionmedica) object;
                return getStringKey(o.getCodigoClasificacionIndMedicas());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Clasificacionindicacionmedica.class.getName());
            }
        }

    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

//------ se creo este metodo para el filtro de los Indicaciones Medicas Sede------------//
     private List<Clasificacionindicacionmedica> listClasificacionindicacionmedica;
    public List<Clasificacionindicacionmedica> getListClasificacionindicacionmedica() {
        return listClasificacionindicacionmedica;
    }
    public void setListClasificacionindicacionmedica(List<Clasificacionindicacionmedica> listClasificacionindicacionmedica) {
        this.listClasificacionindicacionmedica= listClasificacionindicacionmedica;
    }
//----------------------Lista de carpetas por Dependencia Usuario --------------------------//
      public List<Clasificacionindicacionmedica> getSedeXClasificacionindicacionmedica(Sede codigoSede) {
        return ejbFacade.findAll(codigoSede);
    }
//------------------------------------------------------------------------------------------//
      
//-----------------------------Consultorio Sede---------------------------------------------//     
private List<Clasificacionindicacionmedica> listaClasificacionindicacionmedica;

    public List<Clasificacionindicacionmedica> getListaClasificacionindicacionmedica() {
        return listaClasificacionindicacionmedica;
    }

    public void setListaClasificacionindicacionmedica(List<Clasificacionindicacionmedica> listaClasificacionindicacionmedica) {
        this.listaClasificacionindicacionmedica = listaClasificacionindicacionmedica;
    }

    public String prepareListXClasificacionindicacionmedica(Usuario usuario) {
        listaClasificacionindicacionmedica = getFacade().consXClasificacInd(usuario.getCodigoSede().getCodigoSede());
        return "/vista/clasificacionindicacionmedica/List.xhtml";

    }
//------------------------------------------------------------------------------------------------------------------------------------------------//
   



}

