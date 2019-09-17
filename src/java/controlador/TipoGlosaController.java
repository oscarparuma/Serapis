package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.TipoGlosaFacade;
import java.io.Serializable;
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
import modelo.TipoGlosa;
import modelo.Pais;
import modelo.Departamento;
import modelo.Municipio;
import modelo.ClasificacionEps;
import modelo.Eps;
import modelo.Sede;
import modelo.Usuario;

@ManagedBean(name = "tipoGlosaController")
@SessionScoped
public class TipoGlosaController implements Serializable {

    private List<TipoGlosa> filteredTipoGlosa;

    public List<TipoGlosa> getFilteredTipoGlosa() {
        return filteredTipoGlosa;
    }

    public void setFilteredTipoGlosa(List<TipoGlosa> filteredTipoGlosa) {
        this.filteredTipoGlosa = filteredTipoGlosa;
    }
    private TipoGlosa current;
    private DataModel items = null;
    private Pais paisSeleted;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private ClasificacionEps clasificacionEpsSelected;
    private Eps epsSelected;
    private Sede sedeSelected;

    @EJB
    private fachada.TipoGlosaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TipoGlosaController() {
    }

    public TipoGlosa getSelected() {
        if (current == null) {
            current = new TipoGlosa();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TipoGlosaFacade getFacade() {
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
        current = (TipoGlosa) getItems().getRowData();
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
        current = new TipoGlosa();
        selectedItemIndex = -1;
        current = null;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoGlosaCreated"));
            recreateModel();
            return prepareListXTipoGlosa(usuarioController.getSelected());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        current.setActivo("S");
    }

    public String prepareEdit() {
        current = (TipoGlosa) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

//-------------Codigo para modificar utilizando filtro ----------------------//
    public String prepareEditTipoGlosa(TipoGlosa item) {
        current = item;
        return "Edit";
    }
//------------------------------------------------------------------------------//

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoGlosaUpdated"));
            recreateModel();
            return prepareListXTipoGlosa(usuarioController.getSelected());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {
        current.setActivo("N");

    }

    public String destroy() {
        current = (TipoGlosa) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("TipoGlosaDeleted"));
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

    @FacesConverter(forClass = TipoGlosa.class)
    public static class TipoGlosaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TipoGlosaController controller = (TipoGlosaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "tipoGlosaController");
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
            if (object instanceof TipoGlosa) {
                TipoGlosa o = (TipoGlosa) object;
                return getStringKey(o.getCodigoGlosa());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TipoGlosa.class.getName());
            }
        }

    }

    public TipoGlosa getCurrent() {
        return current;
    }

    public void setCurrent(TipoGlosa current) {
        this.current = current;
    }

    public TipoGlosaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(TipoGlosaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public Pais getPaisSeleted() {
        return paisSeleted;
    }

    public void setPaisSeleted(Pais paisSeleted) {
        this.paisSeleted = paisSeleted;
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

    public ClasificacionEps getClasificacionEpsSelected() {
        return clasificacionEpsSelected;
    }

    public void setClasificacionEpsSelected(ClasificacionEps clasificacionEpsSelected) {
        this.clasificacionEpsSelected = clasificacionEpsSelected;
    }

    public Eps getEpsSelected() {
        return epsSelected;
    }

    public void setEpsSelected(Eps epsSelected) {
        this.epsSelected = epsSelected;
    }

    public Sede getSedeSelected() {
        return sedeSelected;
    }

    public void setSedeSelected(Sede sedeSelected) {
        this.sedeSelected = sedeSelected;
    }

    //------ se creo este metodo para el filtro de las eps por sede----------------------------//
//------------------------------------------------------------------------------------------//
//--------------------------------------Eps por sede-----------------------------------------//     
    private List<TipoGlosa> listaTipoGlosa;

    public List<TipoGlosa> getListaTipoGlosa() {
        return listaTipoGlosa;
    }

    public void setListaTipoGlosa(List<TipoGlosa> listaTipoGlosa) {
        this.listaTipoGlosa = listaTipoGlosa;
    }

    public String prepareListXTipoGlosa(Usuario usuario) {
        listaTipoGlosa = getFacade().consXTipoGlosa(usuario.getCodigoSede().getCodigoSede());
        return "/vista/tipoGlosa/List";

    }
//----------------------------------------------------------------------------------------------------------------------//

    private List<TipoGlosa> listTipoGlosa;

    public List<TipoGlosa> getListTipoGlosa() {
        return listTipoGlosa;
    }

    public void setListTipoGlosa(List<TipoGlosa> listTipoGlosa) {
        this.listTipoGlosa = listTipoGlosa;
    }

//----------------------Lista de carpetas por Dependencia Usuario --------------------------//
}
