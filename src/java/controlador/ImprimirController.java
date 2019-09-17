package controlador;

import modelo.Imprimir;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.ImprimirFacade;

import java.io.Serializable;
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

@ManagedBean(name = "imprimirController")
@SessionScoped
public class ImprimirController implements Serializable {

    private Imprimir current;
    private DataModel items = null;
    @EJB
    private fachada.ImprimirFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ImprimirController() {
    }

    public Imprimir getSelected() {
        if (current == null) {
            current = new Imprimir();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ImprimirFacade getFacade() {
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
        current = (Imprimir) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    @ManagedProperty("#{usuarioController}")
    protected UsuarioController usuarioController;

    public String prepareCreate() {
        current = new Imprimir();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImprimirCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    private void guardar(){
    
        
        current.getCodigoCita().setFinalizada("S");
    }

    public String prepareEdit() {
        current = (Imprimir) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            finalizarCita();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImprimirUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void finalizarCita() {
        current.setCodigoCita(actAgenCit);
        current.setFinalizada("S");
        current.getCodigoCita().setFinalizada("S");

    }

    public String destroy() {
        current = (Imprimir) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ImprimirDeleted"));
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

    @FacesConverter(forClass = Imprimir.class)
    public static class ImprimirControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ImprimirController controller = (ImprimirController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "imprimirController");
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
            if (object instanceof Imprimir) {
                Imprimir o = (Imprimir) object;
                return getStringKey(o.getCodigoImprimir());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Imprimir.class.getName());
            }
        }

    }

    public Imprimir getCurrent() {
        return current;
    }

    public void setCurrent(Imprimir current) {
        this.current = current;
    }

    public ImprimirFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ImprimirFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

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
}
