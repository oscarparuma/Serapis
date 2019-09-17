package controlador;

import modelo.RecomendacionesMedicasOtras;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.RecomendacionesMedicasOtrasFacade;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import modelo.Citapersona;
import modelo.Datospersona;

@ManagedBean(name = "recomendacionesMedicasOtrasController")
@SessionScoped
public class RecomendacionesMedicasOtrasController implements Serializable {

    private RecomendacionesMedicasOtras current;
    private DataModel items = null;
    @EJB
    private fachada.RecomendacionesMedicasOtrasFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public RecomendacionesMedicasOtrasController() {
    }

    public RecomendacionesMedicasOtras getSelected() {
        if (current == null) {
            current = new RecomendacionesMedicasOtras();
            selectedItemIndex = -1;
        }
        return current;
    }

    private RecomendacionesMedicasOtrasFacade getFacade() {
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
        current = (RecomendacionesMedicasOtras) getItems().getRowData();
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
        current = new RecomendacionesMedicasOtras();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecomendacionesMedicasOtrasCreated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        current.setCodigoCita(actAgenCit);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.getCodigoCita().setBotRecomMedicaOtros("N");
        current.setBotRecMedica("S");

    }

    public String prepareEdit() {
        current = (RecomendacionesMedicasOtras) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecomendacionesMedicasOtrasUpdated"));
           return  prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    private void modificar(){
    current.setCodigoUsuario(getUsuarioController().getSelected());
    current.setModificadoPor(getUsuarioController().getSelected());
    current.setFechaModificacion(new Date());
    current.getCodigoCita().setBotRecomMedicaOtros("N");
    
    }

    public String destroy() {
        current = (RecomendacionesMedicasOtras) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RecomendacionesMedicasOtrasDeleted"));
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

    @FacesConverter(forClass = RecomendacionesMedicasOtras.class)
    public static class RecomendacionesMedicasOtrasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RecomendacionesMedicasOtrasController controller = (RecomendacionesMedicasOtrasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "recomendacionesMedicasOtrasController");
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
            if (object instanceof RecomendacionesMedicasOtras) {
                RecomendacionesMedicasOtras o = (RecomendacionesMedicasOtras) object;
                return getStringKey(o.getCodigoRecMedica());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + RecomendacionesMedicasOtras.class.getName());
            }
        }

    }

    public RecomendacionesMedicasOtras getCurrent() {
        return current;
    }

    public void setCurrent(RecomendacionesMedicasOtras current) {
        this.current = current;
    }

    public RecomendacionesMedicasOtrasFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(RecomendacionesMedicasOtrasFacade ejbFacade) {
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

}
