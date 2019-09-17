package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.DependenciaFacade;

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
import modelo.Pais;
import modelo.Departamento;
import modelo.Dependencia;
import modelo.Municipio;
import modelo.Sede;
import modelo.Usuario;

@ManagedBean(name = "dependenciaController")
@SessionScoped
public class DependenciaController implements Serializable {

    private Dependencia current;
    private DataModel items = null;
    private List<Dependencia> items1 = null;
    @EJB
    private fachada.DependenciaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Pais paisSelected;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private Sede sedeSelected;
    private Usuario usuarioSelected;
    private Dependencia dependenciaSelected;

    public DependenciaController() {
    }

    public Dependencia getSelected() {
        if (current == null) {
            current = new Dependencia();
            selectedItemIndex = -1;
        }
        return current;
    }

    private DependenciaFacade getFacade() {
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
        current = (Dependencia) getItems().getRowData();
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
        current = new Dependencia();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DependenciaCreated"));
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
        current.setActivo("S");

    }

    public String prepareEdit() {
        current = (Dependencia) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DependenciaUpdated"));
            return prepareList();
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
        current = (Dependencia) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DependenciaDeleted"));
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

    @FacesConverter(forClass = Dependencia.class)
    public static class DependenciaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DependenciaController controller = (DependenciaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "dependenciaController");
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
            if (object instanceof Dependencia) {
                Dependencia o = (Dependencia) object;
                return getStringKey(o.getCodigoDependencia());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Dependencia.class.getName());
            }
        }

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

    public Dependencia getCurrent() {
        return current;
    }

    public void setCurrent(Dependencia current) {
        this.current = current;
    }

    public DependenciaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DependenciaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Dependencia getDependenciaSelected() {
        return dependenciaSelected;
    }

    public void setDependenciaSelected(Dependencia dependenciaSelected) {
        this.dependenciaSelected = dependenciaSelected;
    }
    
    

//--------ajax para refrescar los campos Pais, departamento, municipio---------//
    public void tipoPaisChange(ValueChangeEvent e) {
        paisSelected = (Pais) e.getNewValue();
        departamentoSelected = null;
        municipioSelected = null;
    }
//------------------------------------------------------------------------------//

//-----------------------ajax para refrescar los campos seleccionados-----------//
    public void tipoSedeChange(ValueChangeEvent e) {
        sedeSelected = (Sede) e.getNewValue();
        dependenciaSelected = null;
    }

//------------------------------------------------------------------------------//
//------------------------------------------- ajax para refrescar los campos seleccionados TipoRegional vs Dependencia ----------------------//
    public SelectItem[] getReferenciaXDependencia() {
        if (current.getCodigoSede() != null) {
            return JsfUtil.getSelectItems(current.getCodigoSede().getAreaList(), false);
        }
        return null;
    }
//-------------------------------------------------------------------------------------------------------------------------------------//

//------------------------Reporte Consecutivos por dependencia------------------//     
    private List<Dependencia> listaDependencia;

    public void setItems1(List<Dependencia> items1) {
        this.items1 = items1;
    }

    public List<Dependencia> getListaDependencia() {
        return listaDependencia;
    }

    public void setListaDependencia(List<Dependencia> listaDependencia) {
        this.listaDependencia = listaDependencia;
    }

    public String prepareListXDependencia(Usuario usuario) {
        listaDependencia = getFacade().consXDependencia(usuario.getCodigoSede().getCodigoSede());
        return "/vista/dependencia/List";

    }
//----------------------------------------------------------------------------------------------------------------------//

}
