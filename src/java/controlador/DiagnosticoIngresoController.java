package controlador;

import modelo.DiagnosticoIngreso;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.DiagnosticoIngresoFacade;

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
import modelo.Cie10;
import modelo.Citapersona;
import modelo.Datospersona;
import modelo.Descripcioncie10;

@ManagedBean(name = "diagnosticoIngresoController")
@SessionScoped
public class DiagnosticoIngresoController implements Serializable {

    private DiagnosticoIngreso current;
    private DataModel items = null;
    @EJB
    private fachada.DiagnosticoIngresoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Cie10 cie10Selected;
    private Descripcioncie10 descripcioncie10Selected;
    private Cie10 cie10ASelected;
    private Descripcioncie10 descripcioncie10ASelected;
     private Cie10 cie10BSelected;
    private Descripcioncie10 descripcioncie10BSelected;
     private Cie10 cie10CSelected;
    private Descripcioncie10 descripcioncie10CSelected;
     private Cie10 cie10DSelected;
    private Descripcioncie10 descripcioncie10DSelected;
     private Cie10 cie10ESelected;
    private Descripcioncie10 descripcioncie10ESelected;
     private Cie10 cie10FSelected;
    private Descripcioncie10 descripcioncie10FSelected;
     private Cie10 cie10GSelected;
    private Descripcioncie10 descripcioncie10GSelected;

    public DiagnosticoIngresoController() {
    }

    public DiagnosticoIngreso getSelected() {
        if (current == null) {
            current = new DiagnosticoIngreso();
            selectedItemIndex = -1;
        }
        return current;
    }

    private DiagnosticoIngresoFacade getFacade() {
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
        current = (DiagnosticoIngreso) getItems().getRowData();
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
        current = new DiagnosticoIngreso();
        selectedItemIndex = -1;
        current = null;
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DiagnosticoIngresoCreated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();
            return null;
        }
    }

    private void guardar() {
        current.setCodigoCita(actAgenCit);
        current.setCodigoPersona(actDatPern);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.setActivo("S");
        current.setBorrado("N");
        current.getCodigoCita().setBotDiag("N");

    }

    public String prepareEdit() {
        current = (DiagnosticoIngreso) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    
    
//-------------Codigo para modificar utilizando filtro ----------------------//
   public String prepareEditDiagnosticoIngreso(DiagnosticoIngreso item) {
        current = item;
        return "Edit";
    }
//------------------------------------------------------------------------------//
    
    
    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DiagnosticoIngresoUpdated"));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());

    }

    public String updateBorrar() {
        try {
            borrar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DiagnosticoIngresoUpdated"));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void borrar() {
        current.setBorradoPor(getUsuarioController().getSelected());
        current.setFechaBorrado(new Date());
        current.setBorrado("S");
        current.setActivo("N");

    }

    public String destroy() {
        current = (DiagnosticoIngreso) getItems().getRowData();
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
            return "List";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            eliminar();
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DiagnosticoIngresoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void eliminar() {
        current.getCodigoCita().setBotDiag("S");

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

    @FacesConverter(forClass = DiagnosticoIngreso.class)
    public static class DiagnosticoIngresoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DiagnosticoIngresoController controller = (DiagnosticoIngresoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "diagnosticoIngresoController");
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
            if (object instanceof DiagnosticoIngreso) {
                DiagnosticoIngreso o = (DiagnosticoIngreso) object;
                return getStringKey(o.getCodigoDianosticoIngreso());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DiagnosticoIngreso.class.getName());
            }
        }

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

    public DiagnosticoIngreso getCurrent() {
        return current;
    }

    public void setCurrent(DiagnosticoIngreso current) {
        this.current = current;
    }

    public DiagnosticoIngresoFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DiagnosticoIngresoFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public Cie10 getCie10Selected() {
        return cie10Selected;
    }

    public void setCie10Selected(Cie10 cie10Selected) {
        this.cie10Selected = cie10Selected;
    }

    public Descripcioncie10 getDescripcioncie10Selected() {
        return descripcioncie10Selected;
    }

    public void setDescripcioncie10Selected(Descripcioncie10 descripcioncie10Selected) {
        this.descripcioncie10Selected = descripcioncie10Selected;
    }

    public Cie10 getCie10ASelected() {
        return cie10ASelected;
    }

    public void setCie10ASelected(Cie10 cie10ASelected) {
        this.cie10ASelected = cie10ASelected;
    }

    public Descripcioncie10 getDescripcioncie10ASelected() {
        return descripcioncie10ASelected;
    }

    public void setDescripcioncie10ASelected(Descripcioncie10 descripcioncie10ASelected) {
        this.descripcioncie10ASelected = descripcioncie10ASelected;
    }

    public Cie10 getCie10BSelected() {
        return cie10BSelected;
    }

    public void setCie10BSelected(Cie10 cie10BSelected) {
        this.cie10BSelected = cie10BSelected;
    }

    public Descripcioncie10 getDescripcioncie10BSelected() {
        return descripcioncie10BSelected;
    }

    public void setDescripcioncie10BSelected(Descripcioncie10 descripcioncie10BSelected) {
        this.descripcioncie10BSelected = descripcioncie10BSelected;
    }

    public Cie10 getCie10CSelected() {
        return cie10CSelected;
    }

    public void setCie10CSelected(Cie10 cie10CSelected) {
        this.cie10CSelected = cie10CSelected;
    }

    public Descripcioncie10 getDescripcioncie10CSelected() {
        return descripcioncie10CSelected;
    }

    public void setDescripcioncie10CSelected(Descripcioncie10 descripcioncie10CSelected) {
        this.descripcioncie10CSelected = descripcioncie10CSelected;
    }

    public Cie10 getCie10DSelected() {
        return cie10DSelected;
    }

    public void setCie10DSelected(Cie10 cie10DSelected) {
        this.cie10DSelected = cie10DSelected;
    }

    public Descripcioncie10 getDescripcioncie10DSelected() {
        return descripcioncie10DSelected;
    }

    public void setDescripcioncie10DSelected(Descripcioncie10 descripcioncie10DSelected) {
        this.descripcioncie10DSelected = descripcioncie10DSelected;
    }

    public Cie10 getCie10ESelected() {
        return cie10ESelected;
    }

    public void setCie10ESelected(Cie10 cie10ESelected) {
        this.cie10ESelected = cie10ESelected;
    }

    public Descripcioncie10 getDescripcioncie10ESelected() {
        return descripcioncie10ESelected;
    }

    public void setDescripcioncie10ESelected(Descripcioncie10 descripcioncie10ESelected) {
        this.descripcioncie10ESelected = descripcioncie10ESelected;
    }

    public Cie10 getCie10FSelected() {
        return cie10FSelected;
    }

    public void setCie10FSelected(Cie10 cie10FSelected) {
        this.cie10FSelected = cie10FSelected;
    }

    public Descripcioncie10 getDescripcioncie10FSelected() {
        return descripcioncie10FSelected;
    }

    public void setDescripcioncie10FSelected(Descripcioncie10 descripcioncie10FSelected) {
        this.descripcioncie10FSelected = descripcioncie10FSelected;
    }

    public Cie10 getCie10GSelected() {
        return cie10GSelected;
    }

    public void setCie10GSelected(Cie10 cie10GSelected) {
        this.cie10GSelected = cie10GSelected;
    }

    public Descripcioncie10 getDescripcioncie10GSelected() {
        return descripcioncie10GSelected;
    }

    public void setDescripcioncie10GSelected(Descripcioncie10 descripcioncie10GSelected) {
        this.descripcioncie10GSelected = descripcioncie10GSelected;
    }
    

}
