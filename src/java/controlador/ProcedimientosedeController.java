package controlador;

import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.ProcedimientosedeFacade;

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
import modelo.Procedimientosede;
import modelo.Sede;
import modelo.Usuario;
import modelo.TipoManual;
import modelo.Eps;

@ManagedBean(name = "procedimientosedeController")
@SessionScoped
public class ProcedimientosedeController implements Serializable {

    private List<Procedimientosede> filteredProcedimientosede;

    public List<Procedimientosede> getFilteredProcedimientosede() {
        return filteredProcedimientosede;
    }

    public void setFilteredProcedimientosede(List<Procedimientosede> filteredProcedimientosede) {
        this.filteredProcedimientosede = filteredProcedimientosede;
    }

    private Procedimientosede current;
    private DataModel items = null;
    private List<Procedimientosede> items1 = null;
    @EJB
    private fachada.ProcedimientosedeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private TipoManual tipoManualSelected;
    private Procedimientosede procedimientosedeSelected;
    private Eps epsSelected;

    public ProcedimientosedeController() {
    }

    public Procedimientosede getSelected() {
        if (current == null) {
            current = new Procedimientosede();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProcedimientosedeFacade getFacade() {
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
        current = (Procedimientosede) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    /**
     * Metodo para ir al menu de modificacion eps contrato
     *
     * @param item
     * @return
     */
    public String prepareModifProcedimientoSede(Procedimientosede item) {
        current = item;
        return "View";
    }

    public String prepareModifCodoRipsProcedimientoSede(Procedimientosede item) {
        current = item;
        return prepareListRips();
    }

    public String prepareModifValProcedimientoSede(Procedimientosede item) {
        current = item;
        return "/vista/procedimientosede/EditValorProcedimiento";
    }

    public String prepareEditProcedimientoSede(Procedimientosede item) {
        current = item;
        return prepareListModfExamenList();
    }
//------------------------------------------------------------------------------//

    @ManagedProperty("#{usuarioController}")
    protected UsuarioController usuarioController;

    public UsuarioController getUsuarioController() {
        return usuarioController;
    }

    public void setUsuarioController(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
    }

    public String prepareCreate() {
        current = new Procedimientosede();
        selectedItemIndex = -1;
        current = null;
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProcedimientosedeCreated"));
            return prepareListXProcedimientoSede(usuarioController.getSelected());
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
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        

    }

    public String prepareEdit() {
        current = (Procedimientosede) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProcedimientosedeUpdated"));
            return prepareListXProcedimientoSede(usuarioController.getSelected());
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();
            return null;
        }
    }

    private void modificar() {
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setModificadoPor(getUsuarioController().getSelected());
        current.setFechaModificacion(new Date());
    }

    public String destroy() {
        current = (Procedimientosede) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProcedimientosedeDeleted"));
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

    @FacesConverter(forClass = Procedimientosede.class)
    public static class ProcedimientosedeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProcedimientosedeController controller = (ProcedimientosedeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "procedimientosedeController");
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
            if (object instanceof Procedimientosede) {
                Procedimientosede o = (Procedimientosede) object;
                return getStringKey(o.getCodigoValorConsulta());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Procedimientosede.class.getName());
            }
        }

    }

    public Procedimientosede getCurrent() {
        return current;
    }

    public void setCurrent(Procedimientosede current) {
        this.current = current;
    }

    public List<Procedimientosede> getItems1() {
        return items1;
    }

    public void setItems1(List<Procedimientosede> items1) {
        this.items1 = items1;
    }

    public ProcedimientosedeFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(ProcedimientosedeFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    public TipoManual getTipoManualSelected() {
        return tipoManualSelected;
    }

    public void setTipoManualSelected(TipoManual tipoManualSelected) {
        this.tipoManualSelected = tipoManualSelected;
    }

    public Procedimientosede getProcedimientosedeSelected() {
        return procedimientosedeSelected;
    }

    public void setProcedimientosedeSelected(Procedimientosede procedimientosedeSelected) {
        this.procedimientosedeSelected = procedimientosedeSelected;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public Eps getEpsSelected() {
        return epsSelected;
    }

    public void setEpsSelected(Eps epsSelected) {
        this.epsSelected = epsSelected;
    }
    
    

//---------------- codigo para pasar el examen a otras tablas---------------------//
    public void recreateModel(Procedimientosede procSede) {
        actProcSede = procSede;
        items = null;
    }

    private Procedimientosede actProcSede;

    public Procedimientosede getActProcSede() {
        return actProcSede;
    }

    public void setActProcSede(Procedimientosede actProcSede) {
        this.actProcSede = actProcSede;
    }

//------------------------------------------------------------------------------//
//------ se creo este metodo para el filtro de los consultorios sede------------//
    private List<Procedimientosede> listProcedimientosede;

    public List<Procedimientosede> getListProcedimientosede() {
        return listProcedimientosede;
    }

    public void setListProcedimientosede(List<Procedimientosede> listProcedimientosede) {
        this.listProcedimientosede = listProcedimientosede;
    }


    public List<Procedimientosede> getProcedimientoXEps(Eps codigoEps) {
        return ejbFacade.findAll(codigoEps);
    }

//------------------------------------------------------------------------------------------//
//-----------------------Reporte Consecutivos por Sede Procedimiento sede------//     
    private List<Procedimientosede> listaProcedimientosede;

    public List<Procedimientosede> getListaProcedimientosede() {
        return listaProcedimientosede;
    }

    public void setListaProcedimientosede(List<Procedimientosede> listaProcedimientosede) {
        this.listaProcedimientosede = listaProcedimientosede;
    }

    public String prepareListXProcedimientoSede(Usuario usuario) {
        listaProcedimientosede = getFacade().consXProcedimientosede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/procedimientosede/List";

    }

//------------------------------------------------------------------------------------------------------------------------------------------------//
    public SelectItem[] getReferenciaXManualTarifas() {
        if (current.getCodigoManualTarifario() != null) {
            return JsfUtil.getSelectItems(current.getCodigoManualTarifario().getManualtarifasList(), false);
        }
        return null;
    }

    /**
     * Metodo para verificar el procedimiento a modificar Rips
     *
     *
     */
    @ManagedProperty("#{codigoRipsController}")
    protected CodigoRipsController codigoRipsController;

    public CodigoRipsController getCodigoRipsController() {
        return codigoRipsController;
    }

    public void setCodigoRipsController(CodigoRipsController codigoRipsController) {
        this.codigoRipsController = codigoRipsController;
    }

    public String prepareListRips() {
        current = getFacade().find(((Procedimientosede) getItems().getRowData()).getCodigoClasificacionManual());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getCodigoRipsController().recreateModel(current);
        return "/vista/codigoRips/List";
    }

    public String prepareListRipsList() {
        getCodigoRipsController().recreateModel(current);
        return "/vista/codigoRips/List";
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------//
    /**
     * Metodo para verificar el procedimiento inactivar
     *
     *
     */
    @ManagedProperty("#{modificarExamenController}")
    protected ModificarExamenController modificarExamenController;

    public ModificarExamenController getModificarExamenController() {
        return modificarExamenController;
    }

    public void setModificarExamenController(ModificarExamenController modificarExamenController) {
        this.modificarExamenController = modificarExamenController;
    }

    public String prepareListModifExamen() {
        current = getFacade().find(((Procedimientosede) getItems().getRowData()).getCodigoClasificacionManual());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getModificarExamenController().recreateModel(current);
        return "/vista/modificarExamen/Create";
    }

    public String prepareListModfExamenList() {
        getModificarExamenController().recreateModel(current);
        return "/vista/modificarExamen/Create";
    }

//-------------------------------------------------------------------------------------------------------------------------------------------//

private List<Procedimientosede> listProcedimientosedeEps;

       public List<Procedimientosede> getListProcedimientosedeEps() {
        return listProcedimientosedeEps;
    }

    public void setListProcedimientosedeEps(List<Procedimientosede> listProcedimientosedeEps) {
        this.listProcedimientosedeEps = listProcedimientosedeEps;
    }
     
    public String getProcedimientoXEpsL(Eps codigoEps) {
        listProcedimientosedeEps = getFacade().consXEps(codigoEps.getCodigoEps());
        return "/vista/procedimientosede/List";
}
    
    

    
}