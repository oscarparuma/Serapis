package controlador;

import modelo.Consultorio;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.ConsultorioFacade;

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
import modelo.Dependencia;
import modelo.Usuario;

@ManagedBean(name = "consultorioController")
@SessionScoped
public class ConsultorioController implements Serializable {

    private Consultorio current;
    private DataModel items = null;
    @EJB
    private fachada.ConsultorioFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Sede sedeSelected;
    private Dependencia dependenciaSelected;

    public ConsultorioController() {
    }

    public Consultorio getSelected() {
        if (current == null) {
            current = new Consultorio();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ConsultorioFacade getFacade() {
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
        current = (Consultorio) getItems().getRowData();
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
        current = new Consultorio();
        selectedItemIndex = -1;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ConsultorioCreated"));
            return prepareListXConsultorio(current.getCodigoUsuario());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

     private void guardar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setCodigoUsuario(getUsuarioController().getSelected());
        current.setFechaRegistro(new Date());
        current.setActivo("S");
        current.setCodigoDependencia(getUsuarioController().getSelected().getCodigoDependencia());
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
    }
    
    public String prepareEdit() {
        current = (Consultorio) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

   public String prepareEditCons(Consultorio item) {
        current = item;
        return "Edit";
    }
   
    
    public String update() {
        try {
             modificar();            
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ConsultorioUpdated"));
             return prepareListXConsultorio(current.getCodigoUsuario());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

      private void modificar() {
        current.setCodigoUsuarioModifico(getUsuarioController().getSelected());
        current.setActivo("N");
        current.setFechaModificacion(new Date());
    }
    
    public String destroy() {
        current = (Consultorio) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ConsultorioDeleted"));
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

    @FacesConverter(forClass = Consultorio.class)
    public static class ConsultorioControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConsultorioController controller = (ConsultorioController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "consultorioController");
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
            if (object instanceof Consultorio) {
                Consultorio o = (Consultorio) object;
                return getStringKey(o.getCodigoConsultorio());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Consultorio.class.getName());
            }
        }

    }

    public Consultorio getCurrent() {
        return current;
    }

    public void setCurrent(Consultorio current) {
        this.current = current;
    }

    public ConsultorioFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ConsultorioFacade ejbFacade) {
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

    public Dependencia getDependenciaSelected() {
        return dependenciaSelected;
    }

    public void setDependenciaSelected(Dependencia dependenciaSelected) {
        this.dependenciaSelected = dependenciaSelected;
    }
    
    
    
//------ se creo este metodo para el filtro de las Arl de la sede------------//
    private List<Consultorio> listConsultorio;

    public List<Consultorio> getListConsultorio() {
        return listConsultorio;
    }

    public void setListConsultorio(List<Consultorio> listConsultorio) {
        this.listConsultorio = listConsultorio;
    }

//----------------------Lista de Arl por Sede --------------------------//
    public List<Consultorio> getSedeXConsultorio(Sede codigoSede) {
        return ejbFacade.findAll(codigoSede);
    }
//------------------------------------------------------------------------------------------//

//-----------------------------Consultorio Sede---------------------------------------------//     
    private List<Consultorio> listaConsultorio;

    public List<Consultorio> getListaConsultorio() {
        return listaConsultorio;
    }

    public void setListaConsultorio(List<Consultorio> listaConsultorio) {
        this.listaConsultorio = listaConsultorio;
    }

    public String prepareListXConsultorio(Usuario usuario) {
        listaConsultorio = getFacade().consXSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/consultorio/List";

    }
//------------------------------------------------------------------------------------------------------------------------------------------------//


}
