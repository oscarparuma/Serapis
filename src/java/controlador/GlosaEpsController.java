package controlador;

import modelo.GlosaEps;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.GlosaEpsFacade;
import java.io.Serializable;
import java.math.BigDecimal;
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

@ManagedBean(name = "glosaEpsController")
@SessionScoped
public class GlosaEpsController implements Serializable {

    private List<GlosaEps> filteredGlosaEps;

    public List<GlosaEps> getFilteredGlosaEps() {
        return filteredGlosaEps;
    }

    public void setFilteredGlosaEps(List<GlosaEps> filteredGlosaEps) {
        this.filteredGlosaEps = filteredGlosaEps;
    }

    private GlosaEps current;
    private DataModel items = null;
    private Pais paisSeleted;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private ClasificacionEps clasificacionEpsSelected;
    private Eps epsSelected;
    private Sede sedeSelected;
    private Usuario usuarioSelected;

    @EJB
    private fachada.GlosaEpsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public GlosaEpsController() {
    }

    public GlosaEps getSelected() {
        if (current == null) {
            current = new GlosaEps();
            selectedItemIndex = -1;
        }
        return current;
    }

    private GlosaEpsFacade getFacade() {
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
        current = (GlosaEps) getItems().getRowData();
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
        current = new GlosaEps();
        selectedItemIndex = -1;
        current = null;
        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GlosaEpsCreated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFecharegistro(new Date());
        current.setCodigoUsuario(getUsuarioController().getSelected());
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        current.setCodigoDetalleRadicado(actDetRadEpsPago);
        current.setConsecutivoRacado(actDetRadEpsPago.getCodigoListadoRadicado());
        current.setCodigoFactura(actDetRadEpsPago.getCodigoFactura());
        current.setCodigoEps(actDetRadEpsPago.getCodigoEps());
        current.setSaldoFactura(actDetRadEpsPago.getSaldoTotal());
        current.getCodigoDetalleRadicado().setValorGlosa(current.getValorGlosa());
        current.getCodigoDetalleRadicado().setGlosa("S");
        current.setValorAceptado(BigDecimal.valueOf(0));
        current.setValorReclamado(BigDecimal.valueOf(0));
        current.setTotalGlosa(current.getValorGlosa().subtract(current.getValorAceptado().subtract(current.getValorReclamado())));
    }

    public String prepareEdit() {
        current = (GlosaEps) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

//-------------Codigo para modificar utilizando filtro ----------------------//
    public String prepareEditGlosaEps(GlosaEps item) {
        current = item;
        return "Edit";
    }

    public String prepareEditGlosaSede(GlosaEps item) {
        current = item;
        return "EditGlosa";
    }
//------------------------------------------------------------------------------//

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GlosaEpsUpdated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRespuesta(new Date());
        current.setUsuarioRespuesta(getUsuarioController().getSelected());
        current.setValorReclamado(current.getValorGlosa().subtract(current.getValorAceptado()));
        current.setTotalGlosa(current.getValorGlosa().subtract(current.getValorReclamado()).subtract(current.getValorAceptado()));
        current.getCodigoDetalleRadicado().setValorReclamado(current.getValorReclamado());
        current.getCodigoDetalleRadicado().setValorAceptado(current.getValorAceptado());
       current.getCodigoDetalleRadicado().setSaldoTotal(current.getSaldoFactura().subtract(current.getValorAceptado()));
    }

    public String destroy() {
        current = (GlosaEps) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("GlosaEpsDeleted"));
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

    @FacesConverter(forClass = GlosaEps.class)
    public static class GlosaEpsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            GlosaEpsController controller = (GlosaEpsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "glosaEpsController");
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
            if (object instanceof GlosaEps) {
                GlosaEps o = (GlosaEps) object;
                return getStringKey(o.getCodigoGlosa());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + GlosaEps.class.getName());
            }
        }

    }

    public GlosaEps getCurrent() {
        return current;
    }

    public void setCurrent(GlosaEps current) {
        this.current = current;
    }

    public GlosaEpsFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(GlosaEpsFacade ejbFacade) {
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

    private List<GlosaEps> listaGlosaEps;

    public List<GlosaEps> getListaGlosaEps() {
        return listaGlosaEps;
    }

    public void setListaGlosaEps(List<GlosaEps> listaGlosaEps) {
        this.listaGlosaEps = listaGlosaEps;
    }

    public String prepareListXGlosaSede(Usuario usuario) {
        listaGlosaEps = getFacade().consXGlosaSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/glosaEps/ListGlosaSede";

    }

}
