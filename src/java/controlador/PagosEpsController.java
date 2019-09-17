package controlador;

import modelo.PagosEps;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.PagosEpsFacade;
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
import modelo.Pais;
import modelo.Departamento;
import modelo.Municipio;
import modelo.ClasificacionEps;
import modelo.DetalleRadicadoSede;
import modelo.Eps;
import modelo.ListaRadicadoEps;
import modelo.Sede;
import modelo.Usuario;

@ManagedBean(name = "pagosEpsController")
@SessionScoped
public class PagosEpsController implements Serializable {

    private List<PagosEps> filteredPagosEps;

    public List<PagosEps> getFilteredPagosEps() {
        return filteredPagosEps;
    }

    public void setFilteredPagosEps(List<PagosEps> filteredPagosEps) {
        this.filteredPagosEps = filteredPagosEps;
    }

    private PagosEps current;
    private DataModel items = null;
    private Pais paisSeleted;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private ClasificacionEps clasificacionEpsSelected;
    private Eps epsSelected;
    private Sede sedeSelected;
    private Usuario usuarioSelected;

    @EJB
    private fachada.PagosEpsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public PagosEpsController() {
    }

    public PagosEps getSelected() {
        if (current == null) {
            current = new PagosEps();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PagosEpsFacade getFacade() {
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
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, actDetRadEpsPago));
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
        current = (PagosEps) getItems().getRowData();
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
        current = new PagosEps();
        selectedItemIndex = -1;
        current = null;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PagosEpsCreated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.setCodigoUsuario(getUsuarioController().getSelected());
        current.setCodigoDetalleRadicado(actDetRadEpsPago);
        current.setValorSaldo(actDetRadEpsPago.getSaldoTotal());
        current.setConsecutivoRacado(actDetRadEpsPago.getCodigoListadoRadicado());
        current.setSaldoActual(current.getValorSaldo().subtract(current.getValorCancelado()));
        current.setCodigoFactura(actDetRadEpsPago.getCodigoFactura());
        current.setCodigoEps(actDetRadEpsPago.getCodigoEps());
        current.getCodigoDetalleRadicado().setSaldoTotal(current.getSaldoActual());
        current.setValorLista(actDetRadEpsPago.getCodigoListadoRadicado().getValorLista());
        current.setValorCanceladoLista(actDetRadEpsPago.getValorCanceladoEps().add(current.getValorCancelado()));
        current.setSaldoLista(current.getValorLista().subtract(current.getValorCanceladoLista()));
        current.getCodigoDetalleRadicado().setValorCanceladoEps(current.getValorCanceladoLista());
        current.getCodigoDetalleRadicado().setSaldoLista(current.getSaldoLista());
        current.getCodigoDetalleRadicado().setSaldoLista(current.getSaldoLista());
        current.getConsecutivoRacado().setSaldoTotal(current.getSaldoLista());
        current.getConsecutivoRacado().setValorCanceladoEps(current.getValorLista().subtract(current.getSaldoLista()));
        
        
        current.getConsecutivoRacado().setValorCanceladoEps(current.getConsecutivoRacado().getValorCanceladoEps().add(current.getValorCancelado()));
    }

    public String prepareEdit() {
        current = (PagosEps) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

//-------------Codigo para modificar utilizando filtro ----------------------//
    public String prepareEditPagosEps(PagosEps item) {
        current = item;
        return "Edit";
    }
//------------------------------------------------------------------------------//

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PagosEpsUpdated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {

    }

    public String destroy() {
        current = (PagosEps) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("PagosEpsDeleted"));
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

    @FacesConverter(forClass = PagosEps.class)
    public static class PagosEpsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PagosEpsController controller = (PagosEpsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "pagosEpsController");
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
            if (object instanceof PagosEps) {
                PagosEps o = (PagosEps) object;
                return getStringKey(o.getCodigoPago());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PagosEps.class.getName());
            }
        }

    }

    public PagosEps getCurrent() {
        return current;
    }

    public void setCurrent(PagosEps current) {
        this.current = current;
    }

    public PagosEpsFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(PagosEpsFacade ejbFacade) {
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

    public Usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    /**
     * Pasar un concecutivo a un seguimiento
     *
     * @param detRadicadoEps
     */
    public void recreateModel(ListaRadicadoEps detRadicadoEps) {
        actDetRadicadoEps = detRadicadoEps;
        items = null;
    }
    private ListaRadicadoEps actDetRadicadoEps;

    public ListaRadicadoEps getActDetRadicadoEps() {
        return actDetRadicadoEps;
    }

    public void setActDetRadicadoEps(ListaRadicadoEps actDetRadicadoEps) {
        this.actDetRadicadoEps = actDetRadicadoEps;
    }

    /**
     * Pasar una a una las facturas de la lista para pago EPS
     */
    public void recreateModel(DetalleRadicadoSede detRadEpsPago) {
        actDetRadEpsPago = detRadEpsPago;
        items = null;
    }
    private DetalleRadicadoSede actDetRadEpsPago;

    public DetalleRadicadoSede getActDetRadEpsPago() {
        return actDetRadEpsPago;
    }

    public void setActDetRadEpsPago(DetalleRadicadoSede actDetRadEpsPago) {
        this.actDetRadEpsPago = actDetRadEpsPago;
    }

}
