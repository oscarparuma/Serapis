package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.IndicacionmedicaFacade;

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
import modelo.Indicacionmedica;
import modelo.Sede;
import modelo.Usuario;


@ManagedBean(name = "indicacionmedicaController")
@SessionScoped
public class IndicacionmedicaController implements Serializable {

    private Indicacionmedica current;
    private DataModel items = null;
   
    @EJB
    private fachada.IndicacionmedicaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    private Sede sedeSelected;
    

    public IndicacionmedicaController() {
    }

    public Indicacionmedica getSelected() {
        if (current == null) {
            current = new Indicacionmedica();
            selectedItemIndex = -1;
        }
        return current;
    }

    private IndicacionmedicaFacade getFacade() {
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
        current = (Indicacionmedica) getItems().getRowData();
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
        current = new Indicacionmedica();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IndicacionmedicaCreated"));
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
        current = (Indicacionmedica) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String prepareEditIndMedic(Indicacionmedica item) {
        current = item;
        return "Edit";
    }
    
    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IndicacionmedicaUpdated"));
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
        current = (Indicacionmedica) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("IndicacionmedicaDeleted"));
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

    @FacesConverter(forClass = Indicacionmedica.class)
    public static class IndicacionmedicaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IndicacionmedicaController controller = (IndicacionmedicaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "indicacionmedicaController");
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
            if (object instanceof Indicacionmedica) {
                Indicacionmedica o = (Indicacionmedica) object;
                return getStringKey(o.getCodigoIndicacionMedica());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Indicacionmedica.class.getName());
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
     private List<Indicacionmedica> listIndicacionmedica;
    public List<Indicacionmedica> getListIndicacionmedica() {
        return listIndicacionmedica;
    }
    public void setListIndicacionmedica(List<Indicacionmedica> listIndicacionmedica) {
        this.listIndicacionmedica= listIndicacionmedica;
    }
//----------------------Lista de carpetas por Dependencia Usuario --------------------------//
      public List<Indicacionmedica> getSedeXIndicacionmedica(Sede codigoSede) {
        return ejbFacade.findAll(codigoSede);
    }
//------------------------------------------------------------------------------------------//
      
//-----------------------------Consultorio Sede---------------------------------------------//     
private List<Indicacionmedica> listaIndicacionmedica;

    public List<Indicacionmedica> getListaIndicacionmedica() {
        return listaIndicacionmedica;
    }

    public void setListaIndicacionmedica(List<Indicacionmedica> listaIndicacionmedica) {
        this.listaIndicacionmedica = listaIndicacionmedica;
    }

    public String prepareListXIndicacionmedica(Usuario usuario) {
        listaIndicacionmedica = getFacade().consXSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/indicacionmedica/List";

    }
//------------------------------------------------------------------------------------------------------------------------------------------------//
   



}

