package controlador;

import modelo.RadicacionFacturas;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.RadicacionFacturasFacade;

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
import modelo.FacturaRadicacion;
import modelo.Facturacion;
import modelo.Eps;

@ManagedBean(name = "radicacionFacturasController")
@SessionScoped
public class RadicacionFacturasController implements Serializable {

    private RadicacionFacturas current;
    private DataModel items = null;
    private List<RadicacionFacturas> items1 = null;
    @EJB
    private fachada.RadicacionFacturasFacade ejbFacade;
    @EJB
    private fachada.FacturacionFacade ejbFacturacionFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Sede sedeSelected;
    private Dependencia dependenciaSelected;
    private Facturacion faturacionSelected;
    private Eps epsSelected;

    public RadicacionFacturasController() {
    }

    public RadicacionFacturas getSelected() {
        if (current == null) {
            current = new RadicacionFacturas();
            selectedItemIndex = -1;
        }
        return current;
    }

    private RadicacionFacturasFacade getFacade() {
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
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, actFactRad));
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
        current = (RadicacionFacturas) getItems().getRowData();
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
        current = new RadicacionFacturas();
        selectedItemIndex = -1;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            recreateModel();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RadicacionFacturasCreated"));
            recreateModel();
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setCodigoUsuario(getUsuarioController().getSelected());
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        current.setFechaRegistro(new Date());
        current.setCodigoRadicacionFactura(actFactRad);
        current.setValorUnitario(current.getCodigoFactura().getValorUnitario());
        current.setValorCopago(current.getCodigoFactura().getValorCopago());
        current.setValorFacturado(current.getCodigoFactura().getValorTotalFactura());
        current.setTotalRadicado(current.getValorFacturado());
        current.getCodigoFactura().setEstadoFactura("R");

    }

    public String prepareEdit() {
        current = (RadicacionFacturas) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String prepareEditCons(RadicacionFacturas item) {
        current = item;
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            actFactRad.getCodigoFactura();
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RadicacionFacturasUpdated"));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {

        current = (RadicacionFacturas) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("RadicacionFacturasDeleted"));
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

    @FacesConverter(forClass = RadicacionFacturas.class)
    public static class RadicacionFacturasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            RadicacionFacturasController controller = (RadicacionFacturasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "radicacionFacturasController");
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
            if (object instanceof RadicacionFacturas) {
                RadicacionFacturas o = (RadicacionFacturas) object;
                return getStringKey(o.getCodigoRadicacion());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + RadicacionFacturas.class.getName());
            }
        }

    }

    public RadicacionFacturas getCurrent() {
        return current;
    }

    public void setCurrent(RadicacionFacturas current) {
        this.current = current;
    }

    public RadicacionFacturasFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(RadicacionFacturasFacade ejbFacade) {
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

    public Facturacion getFaturacionSelected() {
        return faturacionSelected;
    }

    public void setFaturacionSelected(Facturacion faturacionSelected) {
        this.faturacionSelected = faturacionSelected;
    }

    public Eps getEpsSelected() {
        return epsSelected;
    }

    public void setEpsSelected(Eps epsSelected) {
        this.epsSelected = epsSelected;
    }

    //---------------------ajax para refrescar los campos seleccionados facturas eps sede ----------------------//
    public SelectItem[] getFactXEpsSede() {
        if (current.getCodigoEps() != null) {
            return JsfUtil.getSelectItems(current.getCodigoEps().getFacturacionList(), false);
        }
        return null;
    }
//-----------------------------------------------------------------------------------------------------------//

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
    //---------------- codigo para asignar factura al pago-------------------------//
    public void recreateModel(Facturacion fact) {
        actFact = fact;
        items1 = null;
    }

    private Facturacion actFact;

    public Facturacion getActFact() {
        return actFact;
    }

    public void setFact(Facturacion actFact) {
        this.actFact = actFact;
    }

    private Long totalRadicadoFact = new Long(0);

    public Long getTotalRadicadoFact() {
        return totalRadicadoFact;
    }

    public void setTotalRadicadoFact(Long totalRadicadoFact) {
        this.totalRadicadoFact = totalRadicadoFact;
    }

//-----------------Listado de facturas contrato mantenimiento -----------------//
    public Object getItems1() {
        if (items1 == null) {
//-------------------------Lamar el query correspondiente ----------------------//
            items1 = ejbFacade.findAll();
        }
        return items1;
    }
//-------------------------------------------------------------------------//

//------------------------------------------------------------------------------//    
//------ Se creo este metodo para el filtro de las facturas poe EPS------------//
    private List<RadicacionFacturas> listFacturacion;

    public List<RadicacionFacturas> getListFacturacion() {
        return listFacturacion;
    }

    public void setListFacturacion(List<RadicacionFacturas> listFacturacion) {
        this.listFacturacion = listFacturacion;
    }
//----------------------Lista de carpetas por Dependencia Usuario --------------------------//
/**
 * verificar aqui
 * @param codigoEps
 * @return 
 *   public List<RadicacionFacturas> getSedeXFacturacionR(EPS codigoEps) {
        return ejbFacade.findAllFact(actFactRad.getCodigoEps());
    }
 */
  
    
//------------------------------------------------------------------------------------------//

//------------------------------------------- ajax para refrescar los campos seleccionados TipoRegional vs Dependencia ----------------------//
    public SelectItem[] getRadicaXEps() {
        if (current.getCodigoEps() != null) {
//------------------Se utiliza este metodo para filtrar por activos y no activos con el findAll se llama el query ---------------------------//
            return JsfUtil.getSelectItems(ejbFacturacionFacade.findAll(current.getCodigoEps().getCodigoEps()), false);
        }
        return null;
    }
//------------------------------------------------------------------------------------------//

//------------------------------------------------------------------------------//
}
