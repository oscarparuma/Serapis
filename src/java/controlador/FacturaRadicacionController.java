package controlador;

import modelo.FacturaRadicacion;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.FacturaRadicacionFacade;

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

@ManagedBean(name = "facturaRadicacionController")
@SessionScoped
public class FacturaRadicacionController implements Serializable {

    private FacturaRadicacion current;
    private DataModel items = null;
    @EJB
    private fachada.FacturaRadicacionFacade ejbFacade;
    @EJB
    private fachada.FacturacionFacade ejbFacturacionFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Sede sedeSelected;
    private Dependencia dependenciaSelected;

    public FacturaRadicacionController() {
    }

    public FacturaRadicacion getSelected() {
        if (current == null) {
            current = new FacturaRadicacion();
            selectedItemIndex = -1;
        }
        return current;
    }

    private FacturaRadicacionFacade getFacade() {
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
        current = (FacturaRadicacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

//-------------Codigo para modificar utilizando filtro ----------------------//
    public String prepareViewRad(FacturaRadicacion item) {
        current = item;
        return "View";
    }
//--------------------------------------------------------------------------//

    @ManagedProperty("#{usuarioController}")
    protected UsuarioController usuarioController;

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public String prepareCreate() {
        current = new FacturaRadicacion();
        selectedItemIndex = -1;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaRadicacionCreated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setCodigoUsuario(getUsuarioController().getSelected());
        current.setFechaRegistro(new Date());

    }

    public String prepareEdit() {
        current = (FacturaRadicacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String prepareEditCons(FacturaRadicacion item) {
        current = item;
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaRadicacionUpdated"));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (FacturaRadicacion) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturaRadicacionDeleted"));
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

    @FacesConverter(forClass = FacturaRadicacion.class)
    public static class FacturaRadicacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FacturaRadicacionController controller = (FacturaRadicacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facturaRadicacionController");
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
            if (object instanceof FacturaRadicacion) {
                FacturaRadicacion o = (FacturaRadicacion) object;
                return getStringKey(o.getCodigoFacturaRadicacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FacturaRadicacion.class.getName());
            }
        }

    }

    public FacturaRadicacion getCurrent() {
        return current;
    }

    public void setCurrent(FacturaRadicacion current) {
        this.current = current;
    }

    public FacturaRadicacionFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(FacturaRadicacionFacade ejbFacade) {
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

//------ se creo este metodo para el filtro de los FacturaRadicacion sede------------//
    private List<FacturaRadicacion> listFacturaRadicacion;

    public List<FacturaRadicacion> getListFacturaRadicacion() {
        return listFacturaRadicacion;
    }

    public void setListFacturaRadicacion(List<FacturaRadicacion> listFacturaRadicacion) {
        this.listFacturaRadicacion = listFacturaRadicacion;
    }
//----------------------Lista de carpetas por FacturaRadicacion Usuario --------------------------//

    public List<FacturaRadicacion> getSedeXFacturaRadicacion(Sede codigoSede) {
        return ejbFacade.findAll(codigoSede);
    }
//------------------------------------------------------------------------------------------//

//---------------- codigo para asignar factura al Radicado-------------------------//
    public void recreateModel(FacturaRadicacion factRad) {
        actFactRad = factRad;
        items = null;
    }

    private FacturaRadicacion actFactRad;

    public FacturaRadicacion getActFactRad() {
        return actFactRad;
    }

    public void setFactRad(FacturaRadicacion actFactRad) {
        this.actFactRad = actFactRad;
    }

//------------------------------------------------------------------------------//
//-----------------------------FacturaRadicacion Sede---------------------------------------------//     
    private List<FacturaRadicacion> listaFacturaRadicacion;

    public List<FacturaRadicacion> getListaFacturaRadicacion() {
        return listaFacturaRadicacion;
    }

    public void setListaFacturaRadicacion(List<FacturaRadicacion> listaFacturaRadicacion) {
        this.listaFacturaRadicacion = listaFacturaRadicacion;
    }

    public String prepareListXFacturaRadicacion(Usuario usuario) {
        listaFacturaRadicacion = getFacade().consXSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/facturaRadicacion/List";

    }
//------------------------------------------------------------------------------------------------------------------------------------------------//

//------------------------------------------Asignar Examen Formula medica------------------------------------------------------------------------//
    @ManagedProperty("#{radicacionFacturasController}")
    protected RadicacionFacturasController radicacionFacturasController;

    public RadicacionFacturasController getRadicacionFacturasController() {
        return radicacionFacturasController;
    }

    public void setRadicacionFacturasController(RadicacionFacturasController radicacionFacturasController) {
        this.radicacionFacturasController = radicacionFacturasController;
    }

    public String prepareListRadicacionFacturas() {
        current = getFacade().find(((FacturaRadicacion) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getRadicacionFacturasController().recreateModel(current);
        return "/vista/radicacionFacturas/List";
    }

    public String prepareListRadicacionFacturasList() {
        getRadicacionFacturasController().recreateModel(current);
        return "/vista/radicacionFacturas/Create";
    }

//---------------------------------------------------------------------------------------------------------------------------------------// 
    //------------------------------------------- ajax para refrescar los campos seleccionados TipoRegional vs Dependencia ----------------------//
    public SelectItem[] getRadicaXEps() {
        if (current.getCodigoEps() != null) {
//------------------Se utiliza este metodo para filtrar por activos y no activos con el findAll se llama el query ---------------------------//
            return JsfUtil.getSelectItems(ejbFacturacionFacade.findAll(current.getCodigoEps().getCodigoEps()), false);
        }
        return null;
    }
}
