package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.EpsSedeFacade;

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
import modelo.EpsSede;
import modelo.Pais;
import modelo.Departamento;
import modelo.Municipio;
import modelo.ClasificacionEps;
import modelo.Eps;
import modelo.Sede;
import modelo.Usuario;

@ManagedBean(name = "epsSedeController")
@SessionScoped
public class EpsSedeController implements Serializable {
     private List<EpsSede> filteredEpsSede;

    public List<EpsSede> getFilteredEpsSede() {
        return filteredEpsSede;
    }

    public void setFilteredEpsSede(List<EpsSede> filteredEpsSede) {
        this.filteredEpsSede = filteredEpsSede;
    }
     
     

    private EpsSede current;
    private DataModel items = null;
    private Pais paisSeleted;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private ClasificacionEps clasificacionEpsSelected;
    private Eps epsSelected;

    @EJB
    private fachada.EpsSedeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    

    public EpsSedeController() {
    }

    public EpsSede getSelected() {
        if (current == null) {
            current = new EpsSede();
            selectedItemIndex = -1;
        }
        return current;
    }

    private EpsSedeFacade getFacade() {
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
        current = (EpsSede) getItems().getRowData();
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
        current = new EpsSede();
        selectedItemIndex = -1;
        current = null;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EpsSedeCreated"));
            recreateModel();
            return prepareListXEpsSede(usuarioController.getSelected());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
       current.setFechaRegistro(new Date());
       current.setEstado("A");
       current.setCodigoUsuario(getUsuarioController().getSelected().getCodigoUsuario());    
       current.setCodigoPais(getUsuarioController().getSelected().getCodigoPais());

    }

    public String prepareEdit() {
        current = (EpsSede) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

//-------------Codigo para modificar utilizando filtro ----------------------//
    public String prepareEditEpsSede(EpsSede item) {
        current = item;
        return "Edit";
    }
//------------------------------------------------------------------------------//

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EpsSedeUpdated"));
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
        current = (EpsSede) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("EpsSedeDeleted"));
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

    @FacesConverter(forClass = EpsSede.class)
    public static class EpsSedeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EpsSedeController controller = (EpsSedeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "epsSedeController");
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
            if (object instanceof EpsSede) {
                EpsSede o = (EpsSede) object;
                return getStringKey(o.getCodigoEpsSede());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EpsSede.class.getName());
            }
        }

    }

    public EpsSede getCurrent() {
        return current;
    }

    public void setCurrent(EpsSede current) {
        this.current = current;
    }

    public EpsSedeFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(EpsSedeFacade ejbFacade) {
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

    //------ se creo este metodo para el filtro de las eps por sede----------------------------//
    
    
//------------------------------------------------------------------------------------------//
    
//--------------------------------------Eps por sede-----------------------------------------//     
     
    private List<EpsSede> listaEpsSede;  

    public List<EpsSede> getListaEpsSede() {
        return listaEpsSede;
    }

    public void setListaEpsSede(List<EpsSede> listaEpsSede) {
        this.listaEpsSede = listaEpsSede;
    }

    public String prepareListXEpsSede(Usuario usuario) {
        listaEpsSede = getFacade().consXEps(usuario.getCodigoPais().getCodigoPais());
        return "/vista/epsSede/List";

    }
//----------------------------------------------------------------------------------------------------------------------//
    
    private List<EpsSede> listEpsSede;

       public List<EpsSede> getListEpsSede() {
        return listEpsSede;
    }

    public void setListEpsSede(List<EpsSede> listEpsSede) {
        this.listEpsSede = listEpsSede;
    }
     
//----------------------Lista de carpetas por Dependencia Usuario --------------------------//
      public List<EpsSede> getSedeXEpsSede(Pais codigoPais) {
        return ejbFacade.findAll(codigoPais);
    }

    

}
