package controlador;

import modelo.Datospersona;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.DatospersonaFacade;

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
import modelo.Sede;
import modelo.Area;
import modelo.Dependencia;
import modelo.Usuario;
import modelo.Departamento;
import modelo.Municipio;
import modelo.Pais;

@ManagedBean(name = "datospersonaController")
@SessionScoped
public class DatospersonaController implements Serializable {

    private Datospersona current;
    private DataModel items = null;
    @EJB
    private fachada.DatospersonaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private Sede sedeSelected;
    private Area areaSelected;
    private Dependencia dependenciaSelected;
    private Usuario usuarioSelected;
    private short radicador = 1;
    private String estadoActual = "S";
    private Pais paisSelected;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private Municipio municipioRSelected;
    private Departamento departamentoRSelected;
    private Pais paisRSelected;

    public DatospersonaController() {
    }

    public Datospersona getSelected() {
        if (current == null) {
            current = new Datospersona();
            selectedItemIndex = -1;
        }
        return current;
    }

    private DatospersonaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(1000000000) {
                // -- se incluye codigo para listar las manifestaciones por regional y usuario --//
                @Override
                public int getItemsCount() {
                    switch (radicador) {
                        case 1:
                            //usuario Logeado
                            return getFacade().count(getUsuarioController().getSelected());
                        case 2:
                            //Regional
                            return getFacade().count(getUsuarioController().getSelected().getCodigoSede());
                        case 3:
                            //dependencia usuario actual
                            return getFacade().count(getUsuarioController().getSelected().getCodigoArea());
                        case 4:
                            //todas las manifestaciones
                            return getFacade().count(getUsuarioController().getSelected().getCodigoDependencia());

                        case 5:
                            //todas las manifestaciones
                            return getFacade().count();

                    }

                    return 0;
                }

                @Override
                @SuppressWarnings("unchecked")
                public DataModel createPageDataModel() {
                    switch (radicador) {
                        case 1:
                            //Consulta por Usuario Logeado
                            return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getUsuarioController().getSelected()));
                        case 2:
                            // consulta por Regional
                            return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getUsuarioController().getSelected().getCodigoSede()));
                        case 3:
                            // consulta por Dependencia usuario Actual
                            return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getUsuarioController().getSelected().getCodigoArea()));
                        case 4:
                            //consulta por dependencia
                            return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getUsuarioController().getSelected().getCodigoDependencia()));

                        case 5:
                            //consulta general todas las manifestaciones
                            return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));

                    }
                    return null;
                }

            };
        }
        return pagination;
    }

    public String prepareList() {
        return "List";
    }

    public String prepareView() {
        current = (Datospersona) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

//-------------Codigo para modificar utilizando filtro ----------------------//
    public String prepareView(Datospersona item) {
        current = item;
        citapersonaController.setPacienteActual(item);
        return "View";
    }
    public String prepareViewDP(Datospersona item) {
        current = item;
        return "View";
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
        current = new Datospersona();
        selectedItemIndex = -1;
        recreateModel();
        return "Create";
    }

    /**
     * Se crea este metodo para limpiar el fomulario de consulta persona
     *
     * @Lalmeida
     */
    public String prepareCreateDatosPersona() {
        current = new Datospersona();
        selectedItemIndex = -1;
        recreateModel();
        return "/vista/datosperson/DatosPersona";
    }

    public String prepareCreatePreRegistro() {
        current = new Datospersona();
        selectedItemIndex = -1;
        recreateModel();
        return "PreRegistro";
    }

    public String create() {
        try {
            guardar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DatospersonaCreated"));
            //return prepareListconsXCedulaPersona();
            return prepareViewDP(current);
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceRegistroPersona"));
            return null;
        }
    }

    private void guardar() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setCodigoUsuario(getUsuarioController().getSelected());
        current.setFechaRegistro(new Date());
        current.setEstadoActual("S");
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        current.setCodigoArea(getUsuarioController().getSelected().getCodigoArea());
        current.setCodigoDependencia(getUsuarioController().getSelected().getCodigoDependencia());
        current.setBttonActivo("S");
       
    }

    public String prepareEdit() {
        current = (Datospersona) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            modificar();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DatospersonaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void modificar() {
        current.setCodigoUsuarioModifico(getUsuarioController().getSelected());
        current.setCodigoDepartamentoRips(current.getCodigoDepartamento().getCodigoDepartamentoRips());
        current.setCodigoMunicipioRips(current.getCodigoMunicipioResidencia().getCodigoMunicipioRips());
        current.setClasificacionIdentificacion(current.getCodigoDocumentoIdentidad().getClasificacionIdentificacion());
    }

    public String destroy() {
        current = (Datospersona) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DatospersonaDeleted"));
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

//---------------------- codigo para asignar persona a una cita -----------------------------//
    public void recreateModel(Datospersona datPer) {
        actDatPern = datPer;
        items = null;
    }
    private Datospersona actDatPern;

    public Datospersona getActDatPern() {
        return actDatPern;
    }

    public void setActDatPern(Datospersona actDatPern) {
        this.actDatPern = actDatPern;
    }
//-------------------------------------------------------------------------------------------//

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

//-----------------Validamos si existe la persona-----------------------------------------------------------------------//
//-------- consulta por numero de identificacion DatosPersona --------------------//
    private List<Datospersona> listaDatosPersonaCedula;

    public List<Datospersona> getListaDatosPersonaCedula() {
        return listaDatosPersonaCedula;
    }

    public DatospersonaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(DatospersonaFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

//------ ingresamos codigo para generar la lista del la persona ----------------//
    /**
     * Validamos si el usuario esta registrado
     *
     * @return
     *
     */
    private String numeroDocumentoIdentidad = new String();

    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public String prepareListconsXCedulaPersona() {
        listaDatosPersonaCedula
                = getFacade().consXCedulaDatosPersona(numeroDocumentoIdentidad);
        if (!listaDatosPersonaCedula.isEmpty()) {
            getCitapersonaController().setPacienteActual(listaDatosPersonaCedula.get(0));
        } //return "/vista/datospersona/ListaDatosPersona"; 
        return "/vista/datospersona/List.xhtml?faces-redirect=true";
    }

//------------------------------------------------------------------------------------------------------------------------//
    @FacesConverter(forClass = Datospersona.class)
    public static class DatospersonaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DatospersonaController controller = (DatospersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "datospersonaController");
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
            if (object instanceof Datospersona) {
                Datospersona o = (Datospersona) object;
                return getStringKey(o.getCodigoPersona());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Datospersona.class.getName());
            }
        }

    }

    public Datospersona getCurrent() {
        return current;
    }

    public void setCurrent(Datospersona current) {
        this.current = current;
        getCitapersonaController().setPacienteActual(current);
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

    public Area getAreaSelected() {
        return areaSelected;
    }

    public void setAreaSelected(Area areaSelected) {
        this.areaSelected = areaSelected;
    }

    public Dependencia getDependenciaSelected() {
        return dependenciaSelected;
    }

    public void setDependenciaSelected(Dependencia dependenciaSelected) {
        this.dependenciaSelected = dependenciaSelected;
    }

    public Usuario getUsuarioSelected() {
        return usuarioSelected;
    }

    public void setUsuarioSelected(Usuario usuarioSelected) {
        this.usuarioSelected = usuarioSelected;
    }

    public short getRadicador() {
        return radicador;
    }

    public void setRadicador(short radicador) {
        this.radicador = radicador;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
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

    public Municipio getMunicipioRSelected() {
        return municipioRSelected;
    }

    public void setMunicipioRSelected(Municipio municipioRSelected) {
        this.municipioRSelected = municipioRSelected;
    }

    public Departamento getDepartamentoRSelected() {
        return departamentoRSelected;
    }

    public void setDepartamentoRSelected(Departamento departamentoRSelected) {
        this.departamentoRSelected = departamentoRSelected;
    }

    public Pais getPaisRSelected() {
        return paisRSelected;
    }

    public void setPaisRSelected(Pais paisRSelected) {
        this.paisRSelected = paisRSelected;
    }

//----------------Lista por usuario Logeado -- verificar codigo aqui
    public String listarXRadicador() {
        estadoActual = "S";
        radicador = 1;
        items = null;
        return null;
    }

    //lista por regional usuario
    public String listarXSede() {
        estadoActual = "S";
        radicador = 2;
        items = null;
        return null;
    }

    //lista por Area usuario
    public String listarXArea() {
        estadoActual = "S";
        radicador = 3;
        items = null;
        return null;
    }
    //lista por Dependencia usuario

    public String listarXDependencia() {
        estadoActual = "S";
        radicador = 4;
        items = null;
        return null;
    }

    //lista todas las manifestaciones
    public String listarXTodos() {
        estadoActual = "S";
        radicador = 5;
        items = null;
        return null;
    }

    public void actualizarListado() {
        pagination = null;
        items = null;
        items = getPagination().createPageDataModel();
    }

    public void actualizarListado(ValueChangeEvent e) {
        //assign new value to localeCode
        sedeSelected = (Sede) e.getNewValue();
        pagination = null;
        items = null;
        items = getPagination().createPageDataModel();
    }

//-------------------------------------------------------//
//-------------------------ajax para refrescar los campos seleccionados -------//
    public void paisRChange(ValueChangeEvent e) {
        paisRSelected = (Pais) e.getNewValue();
        departamentoRSelected = null;
        municipioRSelected = null;
    }

    public void paisChange(ValueChangeEvent e) {
        paisSelected = (Pais) e.getNewValue();
        departamentoSelected = null;
        municipioSelected = null;
    }

//------------------------------------------------------------------------------//
//------------------------------------------Asignar Citas a una Persona------------------------------------------------------------------------//
    @ManagedProperty("#{citapersonaController}")
    protected CitapersonaController citapersonaController;

    public CitapersonaController getCitapersonaController() {
        return citapersonaController;
    }

    public void setCitapersonaController(CitapersonaController citapersonaController) {
        this.citapersonaController = citapersonaController;
    }

    public String prepareListCitasPersona() {
        current = getFacade().find(((Datospersona) getItems().getRowData()).getCodigoPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getCitapersonaController().recreateModel(current);
        return "/vista/citapersona/AsignarCitas";
    }

    public String prepareListCitaPersonaList() {
        getCitapersonaController().recreateModel(current);
        return "/vista/citapersona/AsignarCitas";
    }

//------------------------------------------------------------------------------------------------------------------------------------------------//
    /**
     * Metodos Limpiar formularios Personas Lalmeida
     */
    public String resetPreregistroPersona() {
        current = null;
        return prepareCreatePreRegistro();
    }

    public String resetDatosPersona() {
        current = null;
        return prepareCreateDatosPersona();
    }

     public SelectItem[] getDepartamentoXMunicipio() {
        if (current.getCodigoDepartamento() != null) {
            return JsfUtil.getSelectItems(current.getCodigoDepartamento().getMunicipioList(), false);
        }
        return null;
    }
    
    
}
