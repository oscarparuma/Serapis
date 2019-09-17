package controlador;

import modelo.Citapersona;
import controlador.util.JsfUtil;
import controlador.util.PaginationHelper;
import fachada.CitapersonaFacade;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import modelo.Datospersona;
import modelo.Sede;
import modelo.Area;
import modelo.Dependencia;
import modelo.Tipoexamen;
import modelo.Departamento;
import modelo.Municipio;
import modelo.Pais;
import modelo.Eps;
import modelo.Procedimientosede;
import modelo.TipoArchivoRips;
import modelo.Usuario;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

@ManagedBean(name = "citapersonaController")
@SessionScoped
public class CitapersonaController implements Serializable {

    private List<Citapersona> filteredCitapersona;

    public List<Citapersona> getFilteredCitapersona() {
        return filteredCitapersona;
    }

    public void setFilteredCitapersona(List<Citapersona> filteredCitapersona) {
        this.filteredCitapersona = filteredCitapersona;
    }

    private Citapersona current;
    private DataModel items = null;

    @EJB
    private fachada.CitapersonaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    private Sede sedeSelected;
    private Area areaSelected;
    private Dependencia dependenciaSelected;
    private Tipoexamen tipoexamenSelected;
    private Pais paisSelected;
    private Departamento departamentoSelected;
    private Municipio municipioSelected;
    private Municipio municipioRSelected;
    private Departamento departamentoRSelected;
    private Pais paisRSelected;
    private Eps epsSelected;
    private Procedimientosede procedimientosedeSelected;
    private short radicador = 1;
    private String finalizada = "N";

    public CitapersonaController() {
    }

    public Citapersona getSelected() {
        if (current == null) {
            current = new Citapersona();
            selectedItemIndex = -1;
        }
        return current;
    }

    private CitapersonaFacade getFacade() {
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
                            //todas las citas
                            return getFacade().count(getUsuarioController().getSelected().getCodigoDependencia());

                        case 5:
                            //todas las citas
                            return getFacade().count();
                        case 6:
                            //Sede
                            return getFacade().count(getUsuarioController().getSelected().getCodigoSede());

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
                            // consulta por Sede
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
                        case 6:
                            // consulta por Sede
                            return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}, getUsuarioController().getSelected().getCodigoSede()));

                    }
                    return null;
                }

            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }
//-------------Codigo para Seleccionar una a una las citas pendientes de facturar---------------------//

    public String prepareListF(Citapersona item) {
        current = item;
        return prepareListFacturacionList();
    }

//------------------------------------------------------------------------------------------------------//    
//-------------Codigo para modificar utilizando filtro ----------------------//
    public String prepareList(Citapersona item) {
        current = item;
        return "View";
    }
//------------------------------------------------------------------------------//

    public String prepareView() {
        current = (Citapersona) getItems().getRowData();
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

    private Datospersona pacienteActual;

    public Datospersona getPacienteActual() {
        return pacienteActual;
    }

    public void setPacienteActual(Datospersona pacienteActual) {
        this.pacienteActual = pacienteActual;
    }
    
    public String prepareCreate(Usuario usuario) {
        current = new Citapersona();
        current.setCodigoProfesional(medicoSeleccionado);
        current.setCodigoPersonaCita(pacienteActual);
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            guardar();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CitapersonaCreated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    private void guardar() {
        current.setRadicadoPor(getUsuarioController().getSelected());
        current.setCodigoPersonaCita(pacienteActual);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        current.setFechaSolicitud(new Date());
        current.setCodigoSede(getUsuarioController().getSelected().getCodigoSede());
        current.setCodigoArea(getUsuarioController().getSelected().getCodigoArea());
        current.setCodigoDependencia(getUsuarioController().getSelected().getCodigoDependencia());
        current.setCodigoEntidadPromotora(current.getCodigoEps().getCodigoEpsSede().getCodigoAsignado());
        current.setCodigoRipsUS(new TipoArchivoRips((int) 3));
        current.setCodigoRipsAC(current.getCodigoExamen().getCodigoArchivoRips());
        long calcularIngreso = getFacade().calcularIngreso(current.getCodigoPersonaCita().getCodigoPersona());
        current.setCscIngreso(calcularIngreso);
        current.setFinalizada("A");
        current.setBotDiag("S");
        current.setBotAnteFami("S");
        current.setBotAntcGenerales("S");
        current.setBotDatCargo("S");
        current.setBotAntGineco("S");
        current.setBotnGenero("S");
        current.setBotExFisico("S");
        current.setBotExFisico1("S");
        current.setBotExFisico2("S");
        current.setBotExFisico3("S");
        current.setBotExFisico4("S");
        current.setBotExFisico5("S");
        current.setBotToxAlerg("S");
        current.setBotAntecOcupacionales("S");
        current.setBotExamenIngreso("S");
        current.setBotIncapacidades("S");
        current.setBotElementosProteccion("S");
        current.setBotTrabajoActual("S");
        current.setBotProcedimiento("S");
        current.setBotConsultaMedica("S");
        current.setBotRecomMedicas("S");
        current.setBotInmunizacion("S");
        current.setBotExamApoyDiagnostico("N");
        current.setBotRecomMedicaOtros("S");
        current.setBotFinalizar("S");
        current.setBotRevisionSistem("S");
        current.setBotCitaFinalizada("N");
        current.setCitaActiva("A");

//----------------------Pasamos valores de datos persona------------------------//        
        current.setNumeroDocumentoIdentidad(pacienteActual.getNumeroDocumentoIdentidad());
        current.setCodigoIdentificacion(pacienteActual.getCodigoDocumentoIdentidad());
        current.setCodigoMunicipioExpedicion(pacienteActual.getCodigoMunicipioExpedicion());
        current.setPrimerApellido(pacienteActual.getApellidos());
        current.setSegundoApellido(pacienteActual.getSegundoApellido());
        current.setPrimerNombre(pacienteActual.getNombres());
        current.setSegundoNombre(pacienteActual.getSegundoNombre());
        current.setGenero(pacienteActual.getGenero());
        current.setCodigoMunicipioNacimiento(pacienteActual.getCodigoMunicipioNacimiento());
        current.setFechaNacimiento(pacienteActual.getFechaNacimiento());
        current.setFechaExpedicionDocumento(pacienteActual.getFechaExpedicion());
        current.setCodigoReligion(pacienteActual.getCodigoReligion());
        current.setFotoPersona(pacienteActual.getFotoPersona());
        current.setEdad(pacienteActual.getEdad());
        current.setEdadMeses(pacienteActual.getEdadMeses());
        current.setCodigoEstadoCivil(pacienteActual.getCodigoEstadoCivil());
        current.setPersonasACargo(pacienteActual.getPersonasACargo());
        current.setEdadAnos(pacienteActual.getEdadAnos());
        current.setEmail(pacienteActual.getEmail());
        current.setCodigoMunicipioResidencia(pacienteActual.getCodigoMunicipioNacimiento());
        current.setPrimaria(pacienteActual.getPrimaria());
        current.setSecundaria(pacienteActual.getSecundaria());
        current.setCodigoProfesionPersona(pacienteActual.getCodigoProfesionPersona());
        current.setOtrosEstudios(pacienteActual.getOtrosEstudios());
        current.setCodigoLocalidad(pacienteActual.getCodigoLocalidad());
        current.setCodigoBarrio(pacienteActual.getCodigoBarrio());
        current.setUpz(pacienteActual.getUpz());
        current.setDireccion(pacienteActual.getDireccion());
        current.setTelefono(pacienteActual.getTelefono());
        current.setTecnico(pacienteActual.getTecnico());
        current.setTecnologo(pacienteActual.getTecnologo());
        current.setProfesional(pacienteActual.getProfesional());
        current.setCodigoArl(pacienteActual.getCodigoArl());
        current.setCodigoPensiones(pacienteActual.getCodigoPensiones());
        current.setCodigoEmpresa(pacienteActual.getCodigoEmpresa());
        current.setSucursalEmpresa(pacienteActual.getSucursalEmpresa());
        current.setCodigoCargo(pacienteActual.getCodigoCargo());
        current.setCodigoProfesionPersona(pacienteActual.getCodigoProfesionPersona());
        current.setCodigoClasificacionProfesionPersona(pacienteActual.getCodigoClasificacionProfesionPersona());
        current.setCodigoAfiliacion(pacienteActual.getCodigoAfiliacion());
        current.setNivelAfiliacion(pacienteActual.getNivelAfiliacion());
        current.setEdadPersona(pacienteActual.getEdadPersona());
        current.setCodigoTipoEdad(pacienteActual.getCodigoTipoEdad());
        current.setCodigoTipoUsuario(pacienteActual.getCodigoTipoUsuario());
        current.setCodigoZonaResidencia(pacienteActual.getCodigoZonaResidencia());
        current.setClasificacionIdentificacion(pacienteActual.getClasificacionIdentificacion());
        current.setCodigoDepartamentoRips(pacienteActual.getCodigoDepartamentoRips());
        current.setCodigoMunicipioRip(pacienteActual.getCodigoMunicipioRips());

//--------------------------------------------------------------------------------------------------------------------//
    }

    public String prepareEdit() {
        current = (Citapersona) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            finalizarCita();
            getFacade().edit(current);
            current.setFinalizada("S");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CitaPersonaUpdated"));
            recreateModel();
            return "/vista/recomendacionesmedicamentos/List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();
            return null;
        }
    }

    private void finalizarCita() {
        current.setFinalizada("S");
    }

    public String updateProcedimiento() {
        try {
            finalizarProcedimiento();
            getFacade().edit(current);
            current.setFinalizada("S");
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CitaPersonaFinalizada"));
            recreateModel();
            return prepareListImprimirProcedimientoList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();
            return null;
        }
    }

    private void finalizarProcedimiento() {
        current.setFinalizada("S");
    }

    public String updateCita() {
        try {
            finalizarCitaP();
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CitaPersonaFinalizada"));
            recreateModel();
            return "/vista/citapersona/List";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            recreateModel();
            return null;
        }
    }

    private void finalizarCitaP() {
        current.setFinalizada("S");
    }

    public String destroy() {
        current = (Citapersona) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("CitapersonaDeleted"));
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

    @FacesConverter(forClass = Citapersona.class)
    public static class CitapersonaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            CitapersonaController controller = (CitapersonaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "citapersonaController");
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
            if (object instanceof Citapersona) {
                Citapersona o = (Citapersona) object;
                return getStringKey(o.getCodigoCitaPersona());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Citapersona.class.getName());
            }
        }

    }

    public Citapersona getCurrent() {
        return current;
    }

    public void setCurrent(Citapersona current) {
        this.current = current;
    }

    public CitapersonaFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(CitapersonaFacade ejbFacade) {
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

    public Tipoexamen getTipoexamenSelected() {
        return tipoexamenSelected;
    }

    public void setTipoexamenSelected(Tipoexamen tipoexamenSelected) {
        this.tipoexamenSelected = tipoexamenSelected;
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

    public Eps getEpsSelected() {
        return epsSelected;
    }

    public void setEpsSelected(Eps epsSelected) {
        this.epsSelected = epsSelected;
    }

    public Procedimientosede getProcedimientosedeSelected() {
        return procedimientosedeSelected;
    }

    public void setProcedimientosedeSelected(Procedimientosede procedimientosedeSelected) {
        this.procedimientosedeSelected = procedimientosedeSelected;
    }

    public short getRadicador() {
        return radicador;
    }

    public void setRadicador(short radicador) {
        this.radicador = radicador;
    }

    public String getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(String finalizada) {
        this.finalizada = finalizada;
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
//---------------------- Asignar medico a una cita --------------------------------------------------------------------//

    public void recreateModel(Usuario datUsuario) {
        actDatUsuario = datUsuario;
        items = null;
    }

    private Usuario actDatUsuario;

    public Usuario getActDatUsuario() {
        return actDatUsuario;
    }

    public void setActDatUsuario(Usuario actDatUsuario) {
        this.actDatUsuario = actDatUsuario;
    }
//-------------------------------------------------------------------------------------------//
//---------------- codigo para asignar citas a una persona---------------------//

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
//-------------------------------lista por usuario Logeado ---------------------------//
    public String listarXRadicador() {
        finalizada = "N";
        radicador = 1;
        items = null;
        return null;
    }

    //lista por regional usuario
    public String listarXSede() {
        finalizada = "N";
        radicador = 2;
        items = null;
        return null;
    }
    //lista todas las manifestaciones

    public String listarXTodos() {
        finalizada = "N";
        radicador = 3;
        items = null;
        return null;
    }
    //lista por Dependencia usuario

    public String listarXDependencia() {
        finalizada = "N";
        radicador = 4;
        items = null;
        return null;
    }

    public String listaXCitaSede() {
        finalizada = "N";
        radicador = 6;
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
//------------------------------------------- ajax para refrescar los campos seleccionados TipoRegional vs Dependencia ----------------------//
    public SelectItem[] getEpsXProcedimiento() {
        if (current.getCodigoEps() != null) {
            return JsfUtil.getSelectItems(current.getCodigoEps().getProcedimientosedeList(), false);

        }
        return null;
    }
//-------------------------------------------------------------------------------------------------------------------------------------//

//------------------------------------------Reporte Consecutivos por dependencia-----------------------------------------------------------------// 
    private List<Citapersona> listaCitaSede;

    public List<Citapersona> getListaCitaSede() {
        return listaCitaSede;
    }

    public void setListaCitaSede(List<Citapersona> listaCitaSede) {
        this.listaCitaSede = listaCitaSede;
    }

    public String prepareListCitaPeronaXSede(Usuario usuario) {
        listaCitaSede = getFacade().consXCitaSede(usuario.getCodigoSede().getCodigoSede(), fechaInicial, fechaFinal);
        return "/vista/consultas/ListCitasMedicasSede";

    }
//------------------------------------------------------------------------------------------------------------------------------------------------//

//-----------------------------Citas asignadas Sede---------------------------------------------//     
    private List<Citapersona> citasAsignadasSede;

    public List<Citapersona> getCitasAsignadasSede() {
        return citasAsignadasSede;
    }

    public void setCitasAsignadasSede(List<Citapersona> citasAsigndasSede) {
        this.citasAsignadasSede = citasAsigndasSede;
    }

    public String citaAsignadaXSede(Usuario usuario) {
        citasAsignadasSede = getFacade().citaAsignadaXSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/citapersona/ListCitasAsignadasSede";

    }
//------------------------------------------------------------------------------------------------------------------------------------------------//

//------------------------------se creo este metodo para el filtro de los usuarios------------------------------------------//
    private List<Citapersona> listCitapersona;

    public List<Citapersona> getListCitapersona() {
        return listCitapersona;
    }

    public void setListCitapersona(List<Citapersona> listCitapersona) {
        this.listCitapersona = listCitapersona;
    }

    public String prepareListCitaXSedes(Usuario usuario) {
        listCitapersona = getFacade().consXCitasPersona(usuario.getCodigoSede().getCodigoSede());
        return "/vista/citapersona/ListCitasSede";
    }

//------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------Se creo este metodo para listar las citas pendientes de facturar ----------------------------------------------------//
    private List<Citapersona> listaFacturarCitaPersona;

    public List<Citapersona> getListaFacturarCitaPersona() {
        return listaFacturarCitaPersona;
    }

    public void setListaFacturarCitaPersona(List<Citapersona> listaFacturarCitaPersona) {
        this.listaFacturarCitaPersona = listaFacturarCitaPersona;
    }

    public String prepareListFacturarCitaXSedes(Usuario usuario) {
        listaFacturarCitaPersona = getFacade().citaAsignadaXSede(usuario.getCodigoSede().getCodigoSede());
        return "/vista/citapersona/ListFacturacionCitas";
    }

//-----------------------------------------------------------------------------------------------------------//
    /**
     * Query lista de citas asignadas por sede
     *
     */
//------------------------------------------------------------------------------------------//
    private List<Citapersona> listaCitaAsignadas;

    public List<Citapersona> getListaCitaAsignadas() {
        return listaCitaAsignadas;
    }

    public void setListaCitaAsignadas(List<Citapersona> listaCitaAsignadas) {
        this.listaCitaAsignadas = listaCitaAsignadas;
    }

    public String prepareListaCitaAsignadasXSedes(Usuario usuario) {
        listaCitaAsignadas = getFacade().citaAsignadaXSedeAct(usuario.getCodigoSede().getCodigoSede());
        return "/vista/citapersona/listaCitaAsignadas";
    }

    /**
     * Lista citas asignadas al usurio
     */
    private List<Citapersona> listaCitapersonaUsuario;

    public List<Citapersona> getListaCitapersonaUsuario() {
        return listaCitapersonaUsuario;
    }

    public void setListaCitapersonaUsuario(List<Citapersona> listaCitapersonaUsuario) {
        this.listaCitapersonaUsuario = listaCitapersonaUsuario;
    }

    public String prepareListaCitaAsignadasXUsuario(Usuario usuario) {
        listaCitaAsignadas = getFacade().citaAsignadaXUsuario(usuario.getCodigoUsuario());
        return "/vista/citapersona/List";
    }

    //-----------------------------------------------------------------------------------------------------------//
    //------------------------------------------Factura Cita ----------------------------------------------------------------------------------------//
    @ManagedProperty("#{facturacionController}")
    protected FacturacionController facturacionController;

    public FacturacionController getFacturacionController() {
        return facturacionController;
    }

    public void setFacturacionController(FacturacionController facturacionController) {
        this.facturacionController = facturacionController;
    }

    public String prepareListFacturacion() {
        current = getFacade().find(((Citapersona) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getFacturacionController().recreateModel(current);
        return "/vista/facturacion/Create";
    }

    public String prepareListFacturacionList() {
        getFacturacionController().recreateModel(current);

        return "/vista/facturacion/Create.xhml";

    }

//------------------------------------------------------------------------------------------------------------------------------------------------//   
//------------------------------Se creo este metodo para listar las citas pendientes de facturar ----------------------------------------------------//
    private List<Citapersona> listaFacturarProfesional;

    public List<Citapersona> getListaFacturarProfesional() {
        return listaFacturarProfesional;
    }

    public void setListaFacturarProfesional(List<Citapersona> listaFacturarProfesional) {
        this.listaFacturarProfesional = listaFacturarProfesional;
    }

    public String prepareListFacturarCitaXProfesional(Usuario usuario) {
        setMedicoSeleccionado(usuario);
        listaFacturarProfesional = getFacade().citaProfesionalXProfesional(usuario.getCodigoUsuario());
        return "/vista/citapersona/AgendaMedicoSede";
    }

    private Usuario medicoSeleccionado;

//-----------------------------------------------------------------------------------------------------------//
    public Usuario getMedicoSeleccionado() {
        return medicoSeleccionado;
    }

    public void setMedicoSeleccionado(Usuario medicoSeleccionado) {
        this.medicoSeleccionado = medicoSeleccionado;
    }
//--------------------------------------------------------------------------------------------------------------//
//=========================================Consulta Medica======================================================================//
    //------------------------------------------Asignar Examen Fisico------------------------------------------------------------------------//
    @ManagedProperty("#{examenesApoyoDiagnosticoController}")
    protected ExamenesApoyoDiagnosticoController examenesApoyoDiagnosticoController;

    public ExamenesApoyoDiagnosticoController getExamenesApoyoDiagnosticoController() {
        return examenesApoyoDiagnosticoController;
    }

    public void setExamenesApoyoDiagnosticoController(ExamenesApoyoDiagnosticoController examenesApoyoDiagnosticoController) {
        this.examenesApoyoDiagnosticoController = examenesApoyoDiagnosticoController;
    }

    public String prepareListExamenesApoyoDiagnosticoList() {
        getExamenesApoyoDiagnosticoController().recreateModel(current);
        return "/vista/examenesApoyoDiagnostico/List";
    }

    public String prepareListExamenesApoyoDiagnosticoProcedimientoList() {
        getExamenesApoyoDiagnosticoController().recreateModel(current);
        return "/vista/examenesApoyoDiagnosticoProcedimiento/List";
    }

    public String prepareListExamenesSolicitudPersonaList() {
        getExamenesApoyoDiagnosticoController().recreateModel(current);
        return "/vista/examenSolicitadosPersona/List";
    }

//------------------------------------------------------------------------------------------------------------------------------------------------// 
//------------------------------------------Asignar Examen Fisico------------------------------------------------------------------------//
    @ManagedProperty("#{examenfisicoController}")
    protected ExamenfisicoController examenfisicoController;

    public ExamenfisicoController getExamenfisicoController() {
        return examenfisicoController;
    }

    public void setExamenfisicoController(ExamenfisicoController examenfisicoController) {
        this.examenfisicoController = examenfisicoController;
    }

    public String prepareListExamenfisico() {
        current = getFacade().find(((Citapersona) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getExamenfisicoController().recreateModel(current);
        // return "/vista/examenfisico/MenuConsulta.xhtml?faces-redirect=true";
        return "/vista/examenfisico/List.xhtml?faces-redirect=true";
    }

    public String prepareListExamenfisicoProcedimiento() {
        current = getFacade().find(((Citapersona) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getExamenfisicoController().recreateModel(current);
        // return "/vista/examenfisicoprocedimiento/MenuConsulta.xhtml?faces-redirect=true";
        return "/vista/examenfisicoprocedimiento/List.xhtml?faces-redirect=true";
    }

    public String prepareListExamenfisicoList() {
        getExamenfisicoController().recreateModel(current);
        // return "/vista/examenfisico/MenuConsulta.xhtml?faces-redirect=true";
        return "/vista/examenfisico/List.xhtml?faces-redirect=true";
    }
//---------------------------------------------------------------------------------------------------------------------------------------// 
//------------------------------------------Asignar Examen Fisico------------------------------------------------------------------------//
    @ManagedProperty("#{consultamedicaController}")
    protected ConsultamedicaController consultamedicaController;

    public ConsultamedicaController getConsultamedicaController() {
        return consultamedicaController;
    }

    public void setConsultamedicaController(ConsultamedicaController consultamedicaController) {
        this.consultamedicaController = consultamedicaController;
    }

    public String prepareListConsultamedica() {
        current = getFacade().find(((Citapersona) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getConsultamedicaController().recreateModel(current);
        return "/vista/consultamedica/List";
    }

    public String prepareListConsultamedicaList() {
        getConsultamedicaController().recreateModel(current);
        return "/vista/consultamedica/List";
    }
//--------------------------------------------------------------------------------------------------------------------------------------// 
//------------------------------------------Asignar Citas a una Persona------------------------------------------------------------------------//
    @ManagedProperty("#{diagnosticoIngresoController}")
    protected DiagnosticoIngresoController diagnosticoIngresoController;

    public DiagnosticoIngresoController getDiagnosticoIngresoController() {
        return diagnosticoIngresoController;
    }

    public void setDiagnosticoIngresoController(DiagnosticoIngresoController diagnosticoIngresoController) {
        this.diagnosticoIngresoController = diagnosticoIngresoController;
    }

    public String prepareListDigIngreso() {
        current = getFacade().find(((Citapersona) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getDiagnosticoIngresoController().recreateModel(current);
        return "/vista/diagnosticoIngreso/List";
    }

    public String prepareListDigIngresoList() {
        getDiagnosticoIngresoController().recreateModel(current);
        return "/vista/diagnosticoIngreso/List";
    }

    public String prepareListDigIngresoProcedimientoList() {
        getDiagnosticoIngresoController().recreateModel(current);
        return "/vista/diagnosticoIngresoprocedimiento/List";
    }
//------------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------------Asignar Examen Formula medica------------------------------------------------------------------------//
    @ManagedProperty("#{formulamedicaController}")
    protected FormulamedicaController formulamedicaController;

    public FormulamedicaController getFormulamedicaController() {
        return formulamedicaController;
    }

    public void setFormulamedicaController(FormulamedicaController formulamedicaController) {
        this.formulamedicaController = formulamedicaController;
    }

    public String prepareListFormulamedica() {
        current = getFacade().find(((Citapersona) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getFormulamedicaController().recreateModel(current);
        return "/vista/formulamedica/List";
    }

    public String prepareListFormulamedicaList() {
        getFormulamedicaController().recreateModel(current);
        return "/vista/formulamedica/List";
    }

    public String prepareListFormulamedicaProcedimientoList() {
        getFormulamedicaController().recreateModel(current);
        return "/vista/formulamedicaProcedimiento/List";
    }
//---------------------------------------------------------------------------------------------------------------------------------------// 

//------------------------------------------Asignar Examen Fisico------------------------------------------------------------------------//
    @ManagedProperty("#{recomendacionesmedicamentosController}")
    protected RecomendacionesmedicamentosController recomendacionesmedicamentosController;

    public RecomendacionesmedicamentosController getRecomendacionesmedicamentosController() {
        return recomendacionesmedicamentosController;
    }

    public void setRecomendacionesmedicamentosController(RecomendacionesmedicamentosController recomendacionesmedicamentosController) {
        this.recomendacionesmedicamentosController = recomendacionesmedicamentosController;
    }

    public String prepareListRecomendacionesMedicamentos() {
        current = getFacade().find(((Citapersona) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getRecomendacionesmedicamentosController().recreateModel(current);
        return "/vista/recomendacionesmedicamentos/List";
    }

    public String prepareListRecomendacionesMedicamentosList() {
        getRecomendacionesmedicamentosController().recreateModel(current);
        return "/vista/recomendacionesmedicamentos/List";
    }

    public String prepareListRecomendacionesMedicamentosProcedimientosList() {
        getRecomendacionesmedicamentosController().recreateModel(current);
        return "/vista/recomendacionesMedicamentosProcedimiento/List";
    }

//------------------------------------------------------------------------------------------------------------------------------------------------// 
//------------------------------------------Asignar Recomendaciones medicas--------------------------------------------------------------//
    @ManagedProperty("#{recomendacionesMedicasOtrasController}")
    protected RecomendacionesMedicasOtrasController recomendacionesMedicasOtrasController;

    public RecomendacionesMedicasOtrasController getRecomendacionesMedicasOtrasController() {
        return recomendacionesMedicasOtrasController;
    }

    public void setRecomendacionesMedicasOtrasController(RecomendacionesMedicasOtrasController recomendacionesMedicasOtrasController) {
        this.recomendacionesMedicasOtrasController = recomendacionesMedicasOtrasController;
    }

    public String prepareListRecomendacionesMedicasOtras() {
        current = getFacade().find(((Citapersona) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getRecomendacionesMedicasOtrasController().recreateModel(current);
        return "/vista/recomendacionesMedicasOtras/List.xhtml";
    }

    public String prepareListRecomendacionesMedicasOtrasList() {
        getRecomendacionesMedicasOtrasController().recreateModel(current);
        return "/vista/recomendacionesMedicasOtras/List.xhtml";
    }

    public String prepareListRecomendacionesMedicasOtrasProcedList() {
        getRecomendacionesMedicasOtrasController().recreateModel(current);
        return "/vista/recomendacionesMedicasOtrasProcedimiento/List.xhtml";
    }

//------------------------------------------------------------------------------------------------------------------------------------------------// 
//------------------------------------------Factura Cita ----------------------------------------------------------------------------------------//
    @ManagedProperty("#{imprimirController}")
    protected ImprimirController imprimirController;

    public ImprimirController getImprimirController() {
        return imprimirController;
    }

    public void setImprimirController(ImprimirController imprimirController) {
        this.imprimirController = imprimirController;
    }

    public String prepareListImprimir() {
        current = getFacade().find(((Citapersona) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getImprimirController().recreateModel(current);
        return "/vista/imprimir/Create";
    }

    public String prepareListImprimirList() {
        getImprimirController().recreateModel(current);
        return "/vista/imprimir/View.xhml";

    }

    public String prepareListImprimirProcedimientoList() {
        getImprimirController().recreateModel(current);
        return "/vista/imprimir/ViewProcedimiento.xhml";

    }

//------------------------------------------------------------------------------------------------------------------------------------------------//
//======================================================================================================================================//
//==============================================================Procedimientos Sede======================================================//
//------------------------------------------Asignar Examen Fisico------------------------------------------------------------------------//
    @ManagedProperty("#{procedimientoController}")
    protected ProcedimientoController procedimientoController;

    public ProcedimientoController getProcedimientoController() {
        return procedimientoController;
    }

    public void setProcedimientoController(ProcedimientoController procedimientoController) {
        this.procedimientoController = procedimientoController;
    }

    public String prepareListProcedimiento() {
        current = getFacade().find(((Citapersona) getItems().getRowData()).getCodigoCitaPersona());
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        getProcedimientoController().recreateModel(current);
        return "/vista/procedimiento/List";
    }

    public String prepareListProcedimientoList() {
        getProcedimientoController().recreateModel(current);
        return "/vista/procedimiento/List";
    }
//------------------------------------------------------------------------------------------------------------------------------------------------// 

//======================================================================================================================================//
//==========================================REPORTES============================================================================================//
//----------------------------------------------------Parametos de fecha Reporte Consultas Medics------------------------------------------------------------------//
    private Date fechaInicial = new Date();
    private Date fechaFinal = new Date();

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------//
//------------------------------------Reportes pARAMETROS---------------------------------------------------------//
    private HashMap agregarParametros(HashMap parametros) throws Exception {

        SimpleDateFormat sdfy = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        SimpleDateFormat sdfx = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");

        parametros.put("condicion", "");

        parametros.put("fechaHoraElabora", sdfy.format(new Date()));
        parametros.put("TituloReporte", "XXXXXXXXXXX");
        return parametros;
    }

//-------------------------Parametros Reportes ----------------//
//----------------- Reporte Formula Medica------------------------//
    public void reportFormulaMedica() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "reportFormulaMedica";
            String tituloReporte = "Reporte Formula Medica";
            String tipoConexion = "serapis";

            String tipoSalida = "pdf";
            String nrd = "";
            //Establezco la conexión

            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("serapis");
            conexion = ds.getConnection();

            //Se crean los parametros y se asignan valores
            HashMap parametros = new HashMap();
            String condicion = "";

            //----ingresamos el parameto para generar le reporte----
            parametros = agregarParametros(parametros);
            parametros.put("codigoCita", current.getCodigoCitaPersona());
            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);

            ServletContext theApplicationsServletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaArchivo = theApplicationsServletContext.getRealPath("/ReportesPDF") + File.separator;
            String reporte = rutaArchivo + nombreReporte + nrd + ".jasper";

            parametros.put("SUBREPORT_DIR", rutaArchivo);
            //--- codigo para llamar la imagen logo
            parametros.put("imagen", rutaArchivo + "escudo.jpg");
            net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
            //JasperPrintManager.printReport(jasperPrint, true);

            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            response = (HttpServletResponse) ectx.getResponse();
            response.setHeader("Content-disposition", "attachment; filename=" + nombreReporte + "." + tipoSalida);
            if (tipoSalida.equals("xls")) {
                JRXlsExporter exporter = new JRXlsExporter();
                response.setContentType("application/vnd.ms-excel");
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporter.exportReport();
                exporter = null;
            } else {
                JRPdfExporter exporter1 = new JRPdfExporter();
                response.setContentType("application/pdf");

                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter1.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter1.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, "LEAR");

                exporter1.exportReport();
                exporter1 = null;
            }
            jasperPrint = null;
            ctx = null;

        } catch (NamingException ex) {
            Logger.getLogger(Citapersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Citapersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(Citapersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Citapersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "Error al momento de imprimir. " + ex.getMessage());
            Logger.getLogger(Citapersona.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            } catch (Exception ex) {
            }
        }
        fcontext.responseComplete();
    }
//------------------------- aqui termina el llamodo del reporte ingreso ------------------------------------------//
//----------------- Reporte ConsultaMedica------------------------//

    public void reportConsultaMedica() {
        FacesContext fcontext = FacesContext.getCurrentInstance();
        Connection conexion = null;

        try {
            HttpServletResponse response = null;
            String nombreReporte = "reportConsultaMedica";
            String tituloReporte = "Reporte Consulta Medica";
            String tipoConexion = "serapis";

            String tipoSalida = "pdf";
            String nrd = "";
            //Establezco la conexión

            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("serapis");
            conexion = ds.getConnection();

            //Se crean los parametros y se asignan valores
            HashMap parametros = new HashMap();
            String condicion = "";

            //----ingresamos el parameto para generar le reporte----
            parametros = agregarParametros(parametros);
            parametros.put("codigoCita", current.getCodigoCitaPersona());
            //parametros.put("fechaInicial", fechaInicial);
            //parametros.put("fechaFinal", fechaFinal);

            ServletContext theApplicationsServletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String rutaArchivo = theApplicationsServletContext.getRealPath("/ReportesPDF") + File.separator;
            String reporte = rutaArchivo + nombreReporte + nrd + ".jasper";

            parametros.put("SUBREPORT_DIR", rutaArchivo);
            //--- codigo para llamar la imagen logo
            parametros.put("imagen", rutaArchivo + "escudo.jpg");
            net.sf.jasperreports.engine.JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, conexion);
            //JasperPrintManager.printReport(jasperPrint, true);

            ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext();
            response = (HttpServletResponse) ectx.getResponse();
            response.setHeader("Content-disposition", "attachment; filename=" + nombreReporte + "." + tipoSalida);
            if (tipoSalida.equals("xls")) {
                JRXlsExporter exporter = new JRXlsExporter();
                response.setContentType("application/vnd.ms-excel");
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporter.exportReport();
                exporter = null;
            } else {
                JRPdfExporter exporter1 = new JRPdfExporter();
                response.setContentType("application/pdf");

                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_FILE_NAME, nombreReporte + "." + tipoSalida);
                exporter1.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
                exporter1.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, response.getOutputStream());
                exporter1.setParameter(JRPdfExporterParameter.METADATA_AUTHOR, "LEAR");

                exporter1.exportReport();
                exporter1 = null;
            }
            jasperPrint = null;
            ctx = null;

        } catch (NamingException ex) {
            Logger.getLogger(Citapersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Citapersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(Citapersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Citapersona.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex, "Error al momento de imprimir. " + ex.getMessage());
            Logger.getLogger(Citapersona.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
            } catch (Exception ex) {
            }
        }
        fcontext.responseComplete();
    }
//------------------------- aqui termina el llamodo del reporte ingreso ------------------------------------------//

//==============================================================================================================================================//
}
