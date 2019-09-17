package controlador;

import modelo.Examenfisico;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.ExamenfisicoFacade;

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

@ManagedBean(name = "examenfisicoController")
@SessionScoped
public class ExamenfisicoController implements Serializable {

    private Examenfisico current;
    private DataModel items = null;
    @EJB
    private fachada.ExamenfisicoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ExamenfisicoController() {
    }

    public Examenfisico getSelected() {
        if (current == null) {
            current = new Examenfisico();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ExamenfisicoFacade getFacade() {
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
        current = (Examenfisico) getItems().getRowData();
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
        current = new Examenfisico();
        selectedItemIndex = -1;
        return "Create";
    }
   
     public String prepareCreate1() {
        current = new Examenfisico();
        selectedItemIndex = -1;
        recreateModel();
        return "Create1";
    }
    
    public String prepareCreate2() {
        current = new Examenfisico();
        selectedItemIndex = -1;
        recreateModel();
        return "Create2";
    }
     public String prepareCreate3() {
        current = new Examenfisico();
        selectedItemIndex = -1;
        recreateModel();
        return "Create3";
    }
     
      public String prepareCreate4() {
        current = new Examenfisico();
        selectedItemIndex = -1;
        recreateModel();
        return "Create4";
    }
       public String prepareCreate5() {
        current = new Examenfisico();
        selectedItemIndex = -1;
        recreateModel();
        return "Create5";
    }
        public String prepareCreate6() {
        current = new Examenfisico();
        selectedItemIndex = -1;
        recreateModel();
        return "Create6";
    }
    
    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoCreated"));
            return prepareEdit();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        current.setCodigoCita(actAgenCit);
        current.setCodigoPersona(actDatPern);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.getCodigoCita().setBotExFisico("N");
    }
    
     public String create1() {
        try {
            guardar1();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoCreated"));
            return prepareEdit1();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar1() {
        current.setCodigoCita(actAgenCit);
        current.setCodigoPersona(actDatPern);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.getCodigoCita().setBotExFisico("N");
    }
    
    public String create2() {
        try {
            guardar2();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoCreated"));
            return prepareEdit2();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar2() {
        current.setCodigoCita(actAgenCit);
        current.setCodigoPersona(actDatPern);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.getCodigoCita().setBotExFisico("N");
    }
    
    public String create3() {
        try {
            guardar3();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoCreated"));
            return prepareEdit3();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar3() {
        current.setCodigoCita(actAgenCit);
        current.setCodigoPersona(actDatPern);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.getCodigoCita().setBotExFisico("N");
    }
    
    public String create4() {
        try {
            guardar4();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoCreated"));
            return prepareEdit4();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar4() {
        current.setCodigoCita(actAgenCit);
        current.setCodigoPersona(actDatPern);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.getCodigoCita().setBotExFisico("N");
    }
    
    public String create5() {
        try {
            guardar5();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoCreated"));
            return prepareEdit5();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar5() {
        current.setCodigoCita(actAgenCit);
        current.setCodigoPersona(actDatPern);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.getCodigoCita().setBotExFisico("N");
    }
    
    public String create6() {
        try {
            guardar6();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoCreated"));
            return prepareEdit6();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar6() {
        current.setCodigoCita(actAgenCit);
        current.setCodigoPersona(actDatPern);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.getCodigoCita().setBotExFisico("N");
    }

    public String prepareEdit() {
        current = (Examenfisico) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }
    

    public String prepareEditExamenFisico(Examenfisico item) {
        current = item;
        return "Edit1";
    } 
    
    public String prepareEdit1() {
        current = (Examenfisico) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit1";
    }
    
    public String prepareEdit2() {
        current = (Examenfisico) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit2";
    }

    public String prepareEdit3() {
        current = (Examenfisico) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit3";
    }

    public String prepareEdit4() {
        current = (Examenfisico) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit4";
    }

    public String prepareEdit5() {
        current = (Examenfisico) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit5";
    }
     public String prepareEdit6() {
        current = (Examenfisico) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit6";
    }

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoUpdated"));
            return "Edit";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());

    }
    
    public String update1() {
        try {
            modificar1();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoUpdated"));
            return "Edit1";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar1() {
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());

    }

    public String update2() {
        try {
            modificar2();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoUpdated"));
            return "Edit2";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar2() {
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());

    }
    
    public String update3() {
        try {
            modificar3();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoUpdated"));
            return "Edit3";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar3() {
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());

    }
    
    public String update4() {
        try {
            modificar4();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoUpdated"));
            return "Edit4";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar4() {
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());

    }
    public String update5() {
        try {
            modificar5();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoUpdated"));
            return "Edit5";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar5() {
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());

    }
    public String update6() {
        try {
            modificar6();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoUpdated"));
            return "Edit6";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar6() {
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());

    }
    
    
    public String destroy() {
        current = (Examenfisico) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ExamenfisicoDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured1"));
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

    @FacesConverter(forClass = Examenfisico.class)
    public static class ExamenfisicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ExamenfisicoController controller = (ExamenfisicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "examenfisicoController");
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
            if (object instanceof Examenfisico) {
                Examenfisico o = (Examenfisico) object;
                return getStringKey(o.getConsecutivo());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Examenfisico.class.getName());
            }
        }

    }

    public Examenfisico getCurrent() {
        return current;
    }

    public void setCurrent(Examenfisico current) {
        this.current = current;
    }

    public ExamenfisicoFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ExamenfisicoFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
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
