package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.FacturacionFacade;

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
import modelo.Area;
import modelo.Citapersona;
import modelo.Facturacion;
import modelo.Usuario;
import modelo.Eps;
import modelo.ListaRadicadoEps;

@ManagedBean(name = "facturacionController")
@SessionScoped
public class FacturacionController implements Serializable {

    private Facturacion current;
    private DataModel items = null;

    @EJB
    private fachada.FacturacionFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Sede sedeSelected;
    private Dependencia dependenciaSelected;
    private Area areaSelected;
    private Eps epsSelected;

    public FacturacionController() {
    }

    public Facturacion getSelected() {
        if (current == null) {
            current = new Facturacion();
            selectedItemIndex = -1;
        }
        return current;
    }

    private FacturacionFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(100000000) {

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

    public String prepareListP(Facturacion item) {
        current = item;
        return prepareListCarteraList();
    }

    public String prepareView() {
        current = (Facturacion) getItems().getRowData();
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
        current = null;
        current = new Facturacion();
        recreateModel();
        selectedItemIndex = -1;

        recreateModel();
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturacionCreated"));
            //current = null;
            recreateModel();
            //return prepareList();
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();

            return null;
        }
    }

    private void guardar() {
        current.setCodigoCitaPersona(actAgenCit);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        current.setCodigoArea(getUsuarioController().getSelected().getCodigoArea());
        current.setCodigoDependencia(getUsuarioController().getSelected().getCodigoDependencia());
        current.setNumeroDocumento(actAgenCit.getNumeroDocumentoIdentidad());
        current.setCodigoPersona(actAgenCit.getCodigoPersonaCita());
        current.setCodigoProcedimientoSede(actAgenCit.getCodigoExamen());
        current.setCodigoEps(actAgenCit.getCodigoEps());
        current.setValorUnitario(actAgenCit.getCodigoExamen().getValorExamen());
        current.setValorCopago(current.getCodigoNivelCopago().getValorTotalCopago());
        current.setValorTotalFactura(current.getValorUnitario().subtract(current.getValorCopago()));
        current.setEstadoFactura("F");
        current.setCopagoFactura("S");
        current.getCodigoCitaPersona().setFinalizada("N");
        current.getCodigoCitaPersona().setCitaActiva("F");
        long calcularRadicado = getFacade().calcularRadicado(current.getCodigoSede().getCodigoSede());
        current.setCcsFactura(calcularRadicado);
        String facturaSede
                = String.format("%d", current.getCcsFactura());// El año...
        current.setFacturaSede(facturaSede);

    }

    public String prepareCreateNF() {
        current = new Facturacion();
        selectedItemIndex = -1;
        recreateModel();
        return "Create";
    }

    public String createNF() {
        try {
            guardarNF();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturacionCreated"));
            current = null;
            recreateModel();
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();
            return null;
        }
    }

    private void guardarNF() {
        current.setCodigoCitaPersona(actAgenCit);
        current.setCodigoUsuario(getUsuarioController().getSelected());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaRegistro(new Date());
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        current.setCodigoArea(getUsuarioController().getSelected().getCodigoArea());
        current.setCodigoDependencia(getUsuarioController().getSelected().getCodigoDependencia());
        current.setNumeroDocumento(actAgenCit.getNumeroDocumentoIdentidad());
        current.setCodigoPersona(actAgenCit.getCodigoPersonaCita());
        current.setCodigoProcedimientoSede(actAgenCit.getCodigoExamen());
        current.setCodigoEps(actAgenCit.getCodigoEps());
        current.setEstadoFactura("F");
        current.setCopagoFactura("N");
        current.getCodigoCitaPersona().setFinalizada("N");

        /*
         long calcularRadicado = getFacade().calcularRadicado(current.getCodigoSede().getCodigoSede());
         current.setCcsFactura(calcularRadicado);
         String facturaSede
         = String.format("%d", current.getCcsFactura());// El año...
         current.setFacturaSede(facturaSede);
         */
    }

    public String prepareEdit() {
        current = (Facturacion) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturacionUpdated"));
            return "List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();
            return null;
        }
    }

    public String destroy() {
        current = (Facturacion) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FacturacionDeleted"));
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

    @FacesConverter(forClass = Facturacion.class)
    public static class FacturacionControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FacturacionController controller = (FacturacionController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "facturacionController");
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
            if (object instanceof Facturacion) {
                Facturacion o = (Facturacion) object;
                return getStringKey(o.getCodigoFactura());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Facturacion.class.getName());
            }
        }

    }

    public Facturacion getCurrent() {
        return current;
    }

    public void setCurrent(Facturacion current) {
        this.current = current;
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

    public Area getAreaSelected() {
        return areaSelected;
    }

    public void setAreaSelected(Area areaSelected) {
        this.areaSelected = areaSelected;
    }

    public FacturacionFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(FacturacionFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public Eps getEpsSelected() {
        return epsSelected;
    }

    public void setEpsSelected(Eps epsSelected) {
        this.epsSelected = epsSelected;
    }
    
    

//---------------- codigo para asignar citas a una factura---------------------//
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
//---------------- codigo para asignar factura al pago-------------------------//
    public void recreateModel(Facturacion fact) {
        actFact = fact;
        items = null;
    }

    private Facturacion actFact;

    public Facturacion getActFact() {
        return actFact;
    }

    public void setFact(Facturacion actFact) {
        this.actFact = actFact;
    }

//------------------------------------------------------------------------------//
//------------------------------------------Factura ReciboPago------------------//
    @ManagedProperty("#{carteraSedeController}")
    protected CarteraSedeController carteraSedeController;

    public CarteraSedeController getCarteraSedeController() {
        return carteraSedeController;
    }

    public void setCarteraSedeController(CarteraSedeController carteraSedeController) {
        this.carteraSedeController = carteraSedeController;
    }

    public String prepareListCartera() {
        current = getFacade().find(((Facturacion) getItems().getRowData()).getCodigoFactura());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getCarteraSedeController().recreateModel(current);
        return "/vista/carteraSede/Create";
    }

    public String prepareListCarteraList() {
        getCarteraSedeController().recreateModel(current);
        return "/vista/carteraSede/Create.xhml";
    }

//------------------------------------------------------------------------------------------------------------------------------------------------// 
//------------------- se creo este metodo para el filtro de los usuarios----------//
    private List<Facturacion> listFacturacion;

    public List<Facturacion> getListFacturacion() {
        return listFacturacion;
    }

    public void setListFacturacion(List<Facturacion> listFacturacion) {
        this.listFacturacion = listFacturacion;
    }

    public String prepareListFacturacionXSedes(Usuario usuario) {
        listFacturacion = getFacade().consXFacturacion(usuario.getCodigoSede().getCodigoSede());
        return "/vista/facturacion/ListFacturacion.xhtml?faces-redirect=true";
    }
    //------------------------------------------------------------------------------------------------------------------------------------------------//
    
//----------- Codigo para listar facturas pendientes de radicar eps sede --------------//
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
    
   public List<Facturacion> getProcedimientoXEps(Eps codigoEps) {
        return ejbFacade.findAllLista(codigoEps);
    }
//-------------------------------------------------------------------//
    
 public List<Facturacion> getFactPendientesRadi(Eps codigoEps) {
        return ejbFacade.consXFactRadicacion(codigoEps);
    }

}
