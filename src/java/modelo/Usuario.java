/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByCodigoUsuario", query = "SELECT u FROM Usuario u WHERE u.codigoUsuario = :codigoUsuario")
    , @NamedQuery(name = "Usuario.findByClave", query = "SELECT u FROM Usuario u WHERE u.clave = :clave")
    , @NamedQuery(name = "Usuario.findByNombreUsuario", query = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
    , @NamedQuery(name = "Usuario.findByRegistroMedico", query = "SELECT u FROM Usuario u WHERE u.registroMedico = :registroMedico")
    , @NamedQuery(name = "Usuario.findByActivo", query = "SELECT u FROM Usuario u WHERE u.activo = :activo")
    , @NamedQuery(name = "Usuario.findByFechaRegistro", query = "SELECT u FROM Usuario u WHERE u.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Usuario.findByFechaModificacion", query = "SELECT u FROM Usuario u WHERE u.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Usuario.findByIdentificacionNit", query = "SELECT u FROM Usuario u WHERE u.identificacionNit = :identificacionNit")
    , @NamedQuery(name = "Usuario.findByEsMedico", query = "SELECT u FROM Usuario u WHERE u.esMedico = :esMedico")
    , @NamedQuery(name = "Usuario.findByContrase\u00f1a", query = "SELECT u FROM Usuario u WHERE u.contrase\u00f1a = :contrase\u00f1a")
    , @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado")
    , @NamedQuery(name = "Usuario.findByRespuesta", query = "SELECT u FROM Usuario u WHERE u.respuesta = :respuesta")
    , @NamedQuery(name = "Usuario.findByCambioClave", query = "SELECT u FROM Usuario u WHERE u.cambioClave = :cambioClave")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoUsuario")
    private Long codigoUsuario;
    @Size(max = 50)
    @Column(name = "clave")
    private String clave;
    @Size(max = 100)
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    @Size(max = 50)
    @Column(name = "registroMedico")
    private String registroMedico;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 50)
    @Column(name = "identificacionNit")
    private String identificacionNit;
    @Size(max = 1)
    @Column(name = "esMedico")
    private String esMedico;
    @Size(max = 50)
    @Column(name = "contrasenia")
    private String contraseña;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Size(max = 1)
    @Column(name = "respuesta")
    private String respuesta;
    @Size(max = 1)
    @Column(name = "cambioClave")
    private String cambioClave;
    @ManyToMany(mappedBy = "usuarioList")
    private List<Tipoperfil> tipoperfilList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Manualtarifas> manualtarifasList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Manualtarifas> manualtarifasList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Pensiones> pensionesList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<Pensiones> pensionesList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Facturacion> facturacionList1;
    @OneToMany(mappedBy = "usuarioAutoriza")
    private List<Facturacion> facturacionList2;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Recomendacionesmedicamentos> recomendacionesmedicamentosList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Recomendacionesmedicamentos> recomendacionesmedicamentosList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<CarteraSede> carteraSedeList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Clasificacioncopago> clasificacioncopagoList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<FacturaRadicacion> facturaRadicacionList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<FacturaRadicacion> facturaRadicacionList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<NumeroDosis> numeroDosisList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<NumeroDosis> numeroDosisList1;
    @OneToMany(mappedBy = "codigoProfesional")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "radicadoPor")
    private List<Citapersona> citapersonaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Tipoperfil> tipoperfilList1;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Tipoperfil> tipoperfilList2;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<AplicarVacunaEn> aplicarVacunaEnList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<AplicarVacunaEn> aplicarVacunaEnList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<ValorExamenSede> valorExamenSedeList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Causaexterna> causaexternaList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Causaexterna> causaexternaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Especialidadsede> especialidadsedeList;
    @OneToMany(mappedBy = "codigousUarioModifico")
    private List<Especialidadsede> especialidadsedeList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Indicacionmedica> indicacionmedicaList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<Indicacionmedica> indicacionmedicaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Procedimientosede> procedimientosedeList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Procedimientosede> procedimientosedeList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<ClasificacionEps> clasificacionEpsList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<ClasificacionEps> clasificacionEpsList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<RecomendacionesMedicasOtras> recomendacionesMedicasOtrasList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<RecomendacionesMedicasOtras> recomendacionesMedicasOtrasList1;
    @OneToMany(mappedBy = "codigousuario")
    private List<Cargo> cargoList;
    @OneToMany(mappedBy = "codigousuarioModifico")
    private List<Cargo> cargoList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Examenfisico> examenfisicoList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Examenfisico> examenfisicoList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<CodigoRips> codigoRipsList;
    @OneToMany(mappedBy = "codigousuario")
    private List<Area> areaList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Area> areaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Sino> sinoList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<ListaRadicadoEps> listaRadicadoEpsList;
    @OneToMany(mappedBy = "codigoUsuarioRadico")
    private List<ListaRadicadoEps> listaRadicadoEpsList1;
    @OneToMany(mappedBy = "radicadaPor")
    private List<ListaRadicadoEps> listaRadicadoEpsList2;
    @OneToMany(mappedBy = "codigoUsuarioFinalizo")
    private List<ListaRadicadoEps> listaRadicadoEpsList3;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Municipio> municipioList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Municipio> municipioList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Datospersona> datospersonaList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Datospersona> datospersonaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Eps> epsList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<Eps> epsList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<NivelCopago> nivelCopagoList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<NivelCopago> nivelCopagoList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Sedeclasificacioncargo> sedeclasificacioncargoList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<Sedeclasificacioncargo> sedeclasificacioncargoList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<DetalleRadicadoSede> detalleRadicadoSedeList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Clasificacionindicacionmedica> clasificacionindicacionmedicaList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<Clasificacionindicacionmedica> clasificacionindicacionmedicaList1;
   @OneToMany(mappedBy = "codigoUsuario")
    private List<GlosaEps> glosaEpsList;
    @OneToMany(mappedBy = "usuarioRespuesta")
    private List<GlosaEps> glosaEpsList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Vacuna> vacunaList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Vacuna> vacunaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Cargopersona> cargopersonaList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<Cargopersona> cargopersonaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Formulamedica> formulamedicaList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Formulamedica> formulamedicaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Tiponofacturable> tiponofacturableList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Tiponofacturable> tiponofacturableList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Cie10> cie10List;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Cie10> cie10List1;
    @JoinColumn(name = "codigoPerfil", referencedColumnName = "codigoPerfil")
    @ManyToOne
    private Tipoperfil codigoPerfil;
    @JoinColumn(name = "codigoArea", referencedColumnName = "codigoArea")
    @ManyToOne
    private Area codigoArea;
    @JoinColumn(name = "codigoCargo", referencedColumnName = "codigoCargo")
    @ManyToOne
    private Cargosede codigoCargo;
    @JoinColumn(name = "codigoDependencia", referencedColumnName = "codigoDependencia")
    @ManyToOne
    private Dependencia codigoDependencia;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @JoinColumn(name = "codigoEspecialidad", referencedColumnName = "codigoEspecialidad")
    @ManyToOne
    private Especialidadsede codigoEspecialidad;
    @JoinColumn(name = "codigoMunicipio", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipio;
    @JoinColumn(name = "codigoPais", referencedColumnName = "codigoPais")
    @ManyToOne
    private Pais codigoPais;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoClasifcacionCargo", referencedColumnName = "codigoCargoSede")
    @ManyToOne
    private Sedeclasificacioncargo codigoClasifcacionCargo;
    @JoinColumn(name = "codigoTema", referencedColumnName = "tema")
    @ManyToOne
    private Tema codigoTema;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Usuario> usuarioList1;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "codigoUsuarioRegistro")
    private List<Usuario> usuarioList2;
    @JoinColumn(name = "codigoUsuarioRegistro", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioRegistro;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<ModificarExamen> modificarExamenList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Empresa> empresaList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<Empresa> empresaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Tipoanestesia> tipoanestesiaList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Tipoanestesia> tipoanestesiaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Upz> upzList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Upz> upzList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Imprimir> imprimirList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<MedioPago> medioPagoList;
    @OneToMany(mappedBy = "codigousuarioModifico")
    private List<MedioPago> medioPagoList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<PagosEps> pagosEpsList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<RadicacionFacturas> radicacionFacturasList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<RadicacionFacturas> radicacionFacturasList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Consultorio> consultorioList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Consultorio> consultorioList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<NivelEducativo> nivelEducativoList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<NivelEducativo> nivelEducativoList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Estadocivil> estadocivilList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Estadocivil> estadocivilList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Sucursalempresa> sucursalempresaList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<Sucursalempresa> sucursalempresaList1;
    @OneToMany(mappedBy = "codigoAyudante")
    private List<Procedimiento> procedimientoList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Procedimiento> procedimientoList1;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Procedimiento> procedimientoList2;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Tipoprofesion> tipoprofesionList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Tipoprofesion> tipoprofesionList1;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<EpsSede> epsSedeList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<AdministracionMedicamentos> administracionMedicamentosList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<AdministracionMedicamentos> administracionMedicamentosList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<VacunacionPersona> vacunacionPersonaList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<VacunacionPersona> vacunacionPersonaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Arl> arlList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<Arl> arlList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Localidad> localidadList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Localidad> localidadList1;
    @OneToMany(mappedBy = "medicoEvolucion")
    private List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList1;
    @OneToMany(mappedBy = "codigoInterpretacion")
    private List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList2;
    @OneToMany(mappedBy = "modificadoPor")
    private List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList3;
    @OneToMany(mappedBy = "modificadoPor")
    private List<DiagnosticoIngreso> diagnosticoIngresoList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<DiagnosticoIngreso> diagnosticoIngresoList1;
    @OneToMany(mappedBy = "borradoPor")
    private List<DiagnosticoIngreso> diagnosticoIngresoList2;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Barrio> barrioList;
    @OneToMany(mappedBy = "codigousuarioModifico")
    private List<Barrio> barrioList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Tipoidentificacion> tipoidentificacionList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Tipoidentificacion> tipoidentificacionList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Sede> sedeList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Sede> sedeList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<ClasificacionDosisVacunas> clasificacionDosisVacunasList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<ClasificacionDosisVacunas> clasificacionDosisVacunasList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Religion> religionList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Religion> religionList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Consultamedica> consultamedicaList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Consultamedica> consultamedicaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Cargosede> cargosedeList;
    @OneToMany(mappedBy = "usuarioModificacion")
    private List<Cargosede> cargosedeList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Dependencia> dependenciaList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Dependencia> dependenciaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Finalidadconsulta> finalidadconsultaList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Finalidadconsulta> finalidadconsultaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Estadofactura> estadofacturaList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Estadofactura> estadofacturaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Tipoexamen> tipoexamenList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Tipoexamen> tipoexamenList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Pais> paisList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Pais> paisList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Plm> plmList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Plm> plmList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Descripcioncie10> descripcioncie10List;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Descripcioncie10> descripcioncie10List1;
    @OneToMany(mappedBy = "codigousuario")
    private List<Tema> temaList;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<ClasificacionProfesionPersona> clasificacionProfesionPersonaList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<ClasificacionProfesionPersona> clasificacionProfesionPersonaList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Departamento> departamentoList;
    @OneToMany(mappedBy = "codigoUsuarioModifico")
    private List<Departamento> departamentoList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Clasificacionmedicamento> clasificacionmedicamentoList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Clasificacionmedicamento> clasificacionmedicamentoList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Tipoafiliacion> tipoafiliacionList;
    @OneToMany(mappedBy = "modificadoPor")
    private List<Tipoafiliacion> tipoafiliacionList1;
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Profesionpersona> profesionpersonaList;
    @OneToMany(mappedBy = "usuarioModifico")
    private List<Profesionpersona> profesionpersonaList1;

    public Usuario() {
    }

    public Usuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRegistroMedico() {
        return registroMedico;
    }

    public void setRegistroMedico(String registroMedico) {
        this.registroMedico = registroMedico;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getIdentificacionNit() {
        return identificacionNit;
    }

    public void setIdentificacionNit(String identificacionNit) {
        this.identificacionNit = identificacionNit;
    }

    public String getEsMedico() {
        return esMedico;
    }

    public void setEsMedico(String esMedico) {
        this.esMedico = esMedico;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getCambioClave() {
        return cambioClave;
    }

    public void setCambioClave(String cambioClave) {
        this.cambioClave = cambioClave;
    }

    public List<GlosaEps> getGlosaEpsList1() {
        return glosaEpsList1;
    }

    public void setGlosaEpsList1(List<GlosaEps> glosaEpsList1) {
        this.glosaEpsList1 = glosaEpsList1;
    }

    
    
    @XmlTransient
    public List<Tipoperfil> getTipoperfilList() {
        return tipoperfilList;
    }

    public void setTipoperfilList(List<Tipoperfil> tipoperfilList) {
        this.tipoperfilList = tipoperfilList;
    }

    @XmlTransient
    public List<Manualtarifas> getManualtarifasList() {
        return manualtarifasList;
    }

    public void setManualtarifasList(List<Manualtarifas> manualtarifasList) {
        this.manualtarifasList = manualtarifasList;
    }

    @XmlTransient
    public List<Manualtarifas> getManualtarifasList1() {
        return manualtarifasList1;
    }

    public void setManualtarifasList1(List<Manualtarifas> manualtarifasList1) {
        this.manualtarifasList1 = manualtarifasList1;
    }

    @XmlTransient
    public List<Pensiones> getPensionesList() {
        return pensionesList;
    }

    public void setPensionesList(List<Pensiones> pensionesList) {
        this.pensionesList = pensionesList;
    }

    @XmlTransient
    public List<Pensiones> getPensionesList1() {
        return pensionesList1;
    }

    public void setPensionesList1(List<Pensiones> pensionesList1) {
        this.pensionesList1 = pensionesList1;
    }

    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @XmlTransient
    public List<Facturacion> getFacturacionList1() {
        return facturacionList1;
    }

    public void setFacturacionList1(List<Facturacion> facturacionList1) {
        this.facturacionList1 = facturacionList1;
    }

    @XmlTransient
    public List<Facturacion> getFacturacionList2() {
        return facturacionList2;
    }

    public void setFacturacionList2(List<Facturacion> facturacionList2) {
        this.facturacionList2 = facturacionList2;
    }

    @XmlTransient
    public List<Recomendacionesmedicamentos> getRecomendacionesmedicamentosList() {
        return recomendacionesmedicamentosList;
    }

    public void setRecomendacionesmedicamentosList(List<Recomendacionesmedicamentos> recomendacionesmedicamentosList) {
        this.recomendacionesmedicamentosList = recomendacionesmedicamentosList;
    }

    @XmlTransient
    public List<Recomendacionesmedicamentos> getRecomendacionesmedicamentosList1() {
        return recomendacionesmedicamentosList1;
    }

    public void setRecomendacionesmedicamentosList1(List<Recomendacionesmedicamentos> recomendacionesmedicamentosList1) {
        this.recomendacionesmedicamentosList1 = recomendacionesmedicamentosList1;
    }

    @XmlTransient
    public List<CarteraSede> getCarteraSedeList() {
        return carteraSedeList;
    }

    public void setCarteraSedeList(List<CarteraSede> carteraSedeList) {
        this.carteraSedeList = carteraSedeList;
    }

    @XmlTransient
    public List<Clasificacioncopago> getClasificacioncopagoList() {
        return clasificacioncopagoList;
    }

    public void setClasificacioncopagoList(List<Clasificacioncopago> clasificacioncopagoList) {
        this.clasificacioncopagoList = clasificacioncopagoList;
    }

    @XmlTransient
    public List<FacturaRadicacion> getFacturaRadicacionList() {
        return facturaRadicacionList;
    }

    public void setFacturaRadicacionList(List<FacturaRadicacion> facturaRadicacionList) {
        this.facturaRadicacionList = facturaRadicacionList;
    }

    @XmlTransient
    public List<FacturaRadicacion> getFacturaRadicacionList1() {
        return facturaRadicacionList1;
    }

    public void setFacturaRadicacionList1(List<FacturaRadicacion> facturaRadicacionList1) {
        this.facturaRadicacionList1 = facturaRadicacionList1;
    }

    @XmlTransient
    public List<NumeroDosis> getNumeroDosisList() {
        return numeroDosisList;
    }

    public void setNumeroDosisList(List<NumeroDosis> numeroDosisList) {
        this.numeroDosisList = numeroDosisList;
    }

    @XmlTransient
    public List<NumeroDosis> getNumeroDosisList1() {
        return numeroDosisList1;
    }

    public void setNumeroDosisList1(List<NumeroDosis> numeroDosisList1) {
        this.numeroDosisList1 = numeroDosisList1;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList1() {
        return citapersonaList1;
    }

    public void setCitapersonaList1(List<Citapersona> citapersonaList1) {
        this.citapersonaList1 = citapersonaList1;
    }

    @XmlTransient
    public List<Tipoperfil> getTipoperfilList1() {
        return tipoperfilList1;
    }

    public void setTipoperfilList1(List<Tipoperfil> tipoperfilList1) {
        this.tipoperfilList1 = tipoperfilList1;
    }

    @XmlTransient
    public List<Tipoperfil> getTipoperfilList2() {
        return tipoperfilList2;
    }

    public void setTipoperfilList2(List<Tipoperfil> tipoperfilList2) {
        this.tipoperfilList2 = tipoperfilList2;
    }

    @XmlTransient
    public List<AplicarVacunaEn> getAplicarVacunaEnList() {
        return aplicarVacunaEnList;
    }

    public void setAplicarVacunaEnList(List<AplicarVacunaEn> aplicarVacunaEnList) {
        this.aplicarVacunaEnList = aplicarVacunaEnList;
    }

    @XmlTransient
    public List<AplicarVacunaEn> getAplicarVacunaEnList1() {
        return aplicarVacunaEnList1;
    }

    public void setAplicarVacunaEnList1(List<AplicarVacunaEn> aplicarVacunaEnList1) {
        this.aplicarVacunaEnList1 = aplicarVacunaEnList1;
    }

    @XmlTransient
    public List<ValorExamenSede> getValorExamenSedeList() {
        return valorExamenSedeList;
    }

    public void setValorExamenSedeList(List<ValorExamenSede> valorExamenSedeList) {
        this.valorExamenSedeList = valorExamenSedeList;
    }

    @XmlTransient
    public List<Causaexterna> getCausaexternaList() {
        return causaexternaList;
    }

    public void setCausaexternaList(List<Causaexterna> causaexternaList) {
        this.causaexternaList = causaexternaList;
    }

    @XmlTransient
    public List<Causaexterna> getCausaexternaList1() {
        return causaexternaList1;
    }

    public void setCausaexternaList1(List<Causaexterna> causaexternaList1) {
        this.causaexternaList1 = causaexternaList1;
    }

    @XmlTransient
    public List<Especialidadsede> getEspecialidadsedeList() {
        return especialidadsedeList;
    }

    public void setEspecialidadsedeList(List<Especialidadsede> especialidadsedeList) {
        this.especialidadsedeList = especialidadsedeList;
    }

    @XmlTransient
    public List<Especialidadsede> getEspecialidadsedeList1() {
        return especialidadsedeList1;
    }

    public void setEspecialidadsedeList1(List<Especialidadsede> especialidadsedeList1) {
        this.especialidadsedeList1 = especialidadsedeList1;
    }

    @XmlTransient
    public List<Indicacionmedica> getIndicacionmedicaList() {
        return indicacionmedicaList;
    }

    public void setIndicacionmedicaList(List<Indicacionmedica> indicacionmedicaList) {
        this.indicacionmedicaList = indicacionmedicaList;
    }

    @XmlTransient
    public List<Indicacionmedica> getIndicacionmedicaList1() {
        return indicacionmedicaList1;
    }

    public void setIndicacionmedicaList1(List<Indicacionmedica> indicacionmedicaList1) {
        this.indicacionmedicaList1 = indicacionmedicaList1;
    }

    @XmlTransient
    public List<Procedimientosede> getProcedimientosedeList() {
        return procedimientosedeList;
    }

    public void setProcedimientosedeList(List<Procedimientosede> procedimientosedeList) {
        this.procedimientosedeList = procedimientosedeList;
    }

    @XmlTransient
    public List<Procedimientosede> getProcedimientosedeList1() {
        return procedimientosedeList1;
    }

    public void setProcedimientosedeList1(List<Procedimientosede> procedimientosedeList1) {
        this.procedimientosedeList1 = procedimientosedeList1;
    }

    @XmlTransient
    public List<ClasificacionEps> getClasificacionEpsList() {
        return clasificacionEpsList;
    }

    public void setClasificacionEpsList(List<ClasificacionEps> clasificacionEpsList) {
        this.clasificacionEpsList = clasificacionEpsList;
    }

    @XmlTransient
    public List<ClasificacionEps> getClasificacionEpsList1() {
        return clasificacionEpsList1;
    }

    public void setClasificacionEpsList1(List<ClasificacionEps> clasificacionEpsList1) {
        this.clasificacionEpsList1 = clasificacionEpsList1;
    }

    @XmlTransient
    public List<RecomendacionesMedicasOtras> getRecomendacionesMedicasOtrasList() {
        return recomendacionesMedicasOtrasList;
    }

    public void setRecomendacionesMedicasOtrasList(List<RecomendacionesMedicasOtras> recomendacionesMedicasOtrasList) {
        this.recomendacionesMedicasOtrasList = recomendacionesMedicasOtrasList;
    }

    @XmlTransient
    public List<RecomendacionesMedicasOtras> getRecomendacionesMedicasOtrasList1() {
        return recomendacionesMedicasOtrasList1;
    }

    public void setRecomendacionesMedicasOtrasList1(List<RecomendacionesMedicasOtras> recomendacionesMedicasOtrasList1) {
        this.recomendacionesMedicasOtrasList1 = recomendacionesMedicasOtrasList1;
    }

    @XmlTransient
    public List<Cargo> getCargoList() {
        return cargoList;
    }

    public void setCargoList(List<Cargo> cargoList) {
        this.cargoList = cargoList;
    }

    @XmlTransient
    public List<Cargo> getCargoList1() {
        return cargoList1;
    }

    public void setCargoList1(List<Cargo> cargoList1) {
        this.cargoList1 = cargoList1;
    }

    @XmlTransient
    public List<Examenfisico> getExamenfisicoList() {
        return examenfisicoList;
    }

    public void setExamenfisicoList(List<Examenfisico> examenfisicoList) {
        this.examenfisicoList = examenfisicoList;
    }

    @XmlTransient
    public List<Examenfisico> getExamenfisicoList1() {
        return examenfisicoList1;
    }

    public void setExamenfisicoList1(List<Examenfisico> examenfisicoList1) {
        this.examenfisicoList1 = examenfisicoList1;
    }

    @XmlTransient
    public List<CodigoRips> getCodigoRipsList() {
        return codigoRipsList;
    }

    public void setCodigoRipsList(List<CodigoRips> codigoRipsList) {
        this.codigoRipsList = codigoRipsList;
    }

    @XmlTransient
    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    @XmlTransient
    public List<Area> getAreaList1() {
        return areaList1;
    }

    public void setAreaList1(List<Area> areaList1) {
        this.areaList1 = areaList1;
    }

    @XmlTransient
    public List<Sino> getSinoList() {
        return sinoList;
    }

    public void setSinoList(List<Sino> sinoList) {
        this.sinoList = sinoList;
    }

    @XmlTransient
    public List<ListaRadicadoEps> getListaRadicadoEpsList() {
        return listaRadicadoEpsList;
    }

    public void setListaRadicadoEpsList(List<ListaRadicadoEps> listaRadicadoEpsList) {
        this.listaRadicadoEpsList = listaRadicadoEpsList;
    }

    @XmlTransient
    public List<ListaRadicadoEps> getListaRadicadoEpsList1() {
        return listaRadicadoEpsList1;
    }

    public void setListaRadicadoEpsList1(List<ListaRadicadoEps> listaRadicadoEpsList1) {
        this.listaRadicadoEpsList1 = listaRadicadoEpsList1;
    }

    @XmlTransient
    public List<ListaRadicadoEps> getListaRadicadoEpsList2() {
        return listaRadicadoEpsList2;
    }

    public void setListaRadicadoEpsList2(List<ListaRadicadoEps> listaRadicadoEpsList2) {
        this.listaRadicadoEpsList2 = listaRadicadoEpsList2;
    }

    @XmlTransient
    public List<ListaRadicadoEps> getListaRadicadoEpsList3() {
        return listaRadicadoEpsList3;
    }

    public void setListaRadicadoEpsList3(List<ListaRadicadoEps> listaRadicadoEpsList3) {
        this.listaRadicadoEpsList3 = listaRadicadoEpsList3;
    }

    @XmlTransient
    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    @XmlTransient
    public List<Municipio> getMunicipioList1() {
        return municipioList1;
    }

    public void setMunicipioList1(List<Municipio> municipioList1) {
        this.municipioList1 = municipioList1;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList() {
        return datospersonaList;
    }

    public void setDatospersonaList(List<Datospersona> datospersonaList) {
        this.datospersonaList = datospersonaList;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList1() {
        return datospersonaList1;
    }

    public void setDatospersonaList1(List<Datospersona> datospersonaList1) {
        this.datospersonaList1 = datospersonaList1;
    }

    @XmlTransient
    public List<Eps> getEpsList() {
        return epsList;
    }

    public void setEpsList(List<Eps> epsList) {
        this.epsList = epsList;
    }

    @XmlTransient
    public List<Eps> getEpsList1() {
        return epsList1;
    }

    public void setEpsList1(List<Eps> epsList1) {
        this.epsList1 = epsList1;
    }

    @XmlTransient
    public List<NivelCopago> getNivelCopagoList() {
        return nivelCopagoList;
    }

    public void setNivelCopagoList(List<NivelCopago> nivelCopagoList) {
        this.nivelCopagoList = nivelCopagoList;
    }

    @XmlTransient
    public List<NivelCopago> getNivelCopagoList1() {
        return nivelCopagoList1;
    }

    public void setNivelCopagoList1(List<NivelCopago> nivelCopagoList1) {
        this.nivelCopagoList1 = nivelCopagoList1;
    }

    @XmlTransient
    public List<Sedeclasificacioncargo> getSedeclasificacioncargoList() {
        return sedeclasificacioncargoList;
    }

    public void setSedeclasificacioncargoList(List<Sedeclasificacioncargo> sedeclasificacioncargoList) {
        this.sedeclasificacioncargoList = sedeclasificacioncargoList;
    }

    @XmlTransient
    public List<Sedeclasificacioncargo> getSedeclasificacioncargoList1() {
        return sedeclasificacioncargoList1;
    }

    public void setSedeclasificacioncargoList1(List<Sedeclasificacioncargo> sedeclasificacioncargoList1) {
        this.sedeclasificacioncargoList1 = sedeclasificacioncargoList1;
    }

    @XmlTransient
    public List<DetalleRadicadoSede> getDetalleRadicadoSedeList() {
        return detalleRadicadoSedeList;
    }

    public void setDetalleRadicadoSedeList(List<DetalleRadicadoSede> detalleRadicadoSedeList) {
        this.detalleRadicadoSedeList = detalleRadicadoSedeList;
    }

    @XmlTransient
    public List<Clasificacionindicacionmedica> getClasificacionindicacionmedicaList() {
        return clasificacionindicacionmedicaList;
    }

    public void setClasificacionindicacionmedicaList(List<Clasificacionindicacionmedica> clasificacionindicacionmedicaList) {
        this.clasificacionindicacionmedicaList = clasificacionindicacionmedicaList;
    }

    @XmlTransient
    public List<Clasificacionindicacionmedica> getClasificacionindicacionmedicaList1() {
        return clasificacionindicacionmedicaList1;
    }

    public void setClasificacionindicacionmedicaList1(List<Clasificacionindicacionmedica> clasificacionindicacionmedicaList1) {
        this.clasificacionindicacionmedicaList1 = clasificacionindicacionmedicaList1;
    }

    @XmlTransient
    public List<GlosaEps> getGlosaEpsList() {
        return glosaEpsList;
    }

    public void setGlosaEpsList(List<GlosaEps> glosaEpsList) {
        this.glosaEpsList = glosaEpsList;
    }

    @XmlTransient
    public List<Vacuna> getVacunaList() {
        return vacunaList;
    }

    public void setVacunaList(List<Vacuna> vacunaList) {
        this.vacunaList = vacunaList;
    }

    @XmlTransient
    public List<Vacuna> getVacunaList1() {
        return vacunaList1;
    }

    public void setVacunaList1(List<Vacuna> vacunaList1) {
        this.vacunaList1 = vacunaList1;
    }

    
    @XmlTransient
    public List<Cargopersona> getCargopersonaList() {
        return cargopersonaList;
    }

    public void setCargopersonaList(List<Cargopersona> cargopersonaList) {
        this.cargopersonaList = cargopersonaList;
    }

    @XmlTransient
    public List<Cargopersona> getCargopersonaList1() {
        return cargopersonaList1;
    }

    public void setCargopersonaList1(List<Cargopersona> cargopersonaList1) {
        this.cargopersonaList1 = cargopersonaList1;
    }

    @XmlTransient
    public List<Formulamedica> getFormulamedicaList() {
        return formulamedicaList;
    }

    public void setFormulamedicaList(List<Formulamedica> formulamedicaList) {
        this.formulamedicaList = formulamedicaList;
    }

    @XmlTransient
    public List<Formulamedica> getFormulamedicaList1() {
        return formulamedicaList1;
    }

    public void setFormulamedicaList1(List<Formulamedica> formulamedicaList1) {
        this.formulamedicaList1 = formulamedicaList1;
    }

    @XmlTransient
    public List<Tiponofacturable> getTiponofacturableList() {
        return tiponofacturableList;
    }

    public void setTiponofacturableList(List<Tiponofacturable> tiponofacturableList) {
        this.tiponofacturableList = tiponofacturableList;
    }

    @XmlTransient
    public List<Tiponofacturable> getTiponofacturableList1() {
        return tiponofacturableList1;
    }

    public void setTiponofacturableList1(List<Tiponofacturable> tiponofacturableList1) {
        this.tiponofacturableList1 = tiponofacturableList1;
    }

    @XmlTransient
    public List<Cie10> getCie10List() {
        return cie10List;
    }

    public void setCie10List(List<Cie10> cie10List) {
        this.cie10List = cie10List;
    }

    @XmlTransient
    public List<Cie10> getCie10List1() {
        return cie10List1;
    }

    public void setCie10List1(List<Cie10> cie10List1) {
        this.cie10List1 = cie10List1;
    }

    public Tipoperfil getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(Tipoperfil codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public Area getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Area codigoArea) {
        this.codigoArea = codigoArea;
    }

    public Cargosede getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Cargosede codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public Dependencia getCodigoDependencia() {
        return codigoDependencia;
    }

    public void setCodigoDependencia(Dependencia codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getCodigoUsuarioModifico() {
        return codigoUsuarioModifico;
    }

    public void setCodigoUsuarioModifico(Usuario codigoUsuarioModifico) {
        this.codigoUsuarioModifico = codigoUsuarioModifico;
    }

    public Especialidadsede getCodigoEspecialidad() {
        return codigoEspecialidad;
    }

    public void setCodigoEspecialidad(Especialidadsede codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    public Municipio getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipio codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Pais getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Pais codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public Sedeclasificacioncargo getCodigoClasifcacionCargo() {
        return codigoClasifcacionCargo;
    }

    public void setCodigoClasifcacionCargo(Sedeclasificacioncargo codigoClasifcacionCargo) {
        this.codigoClasifcacionCargo = codigoClasifcacionCargo;
    }

    public Tema getCodigoTema() {
        return codigoTema;
    }

    public void setCodigoTema(Tema codigoTema) {
        this.codigoTema = codigoTema;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList1() {
        return usuarioList1;
    }

    public void setUsuarioList1(List<Usuario> usuarioList1) {
        this.usuarioList1 = usuarioList1;
    }

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList2() {
        return usuarioList2;
    }

    public void setUsuarioList2(List<Usuario> usuarioList2) {
        this.usuarioList2 = usuarioList2;
    }

    public Usuario getCodigoUsuarioRegistro() {
        return codigoUsuarioRegistro;
    }

    public void setCodigoUsuarioRegistro(Usuario codigoUsuarioRegistro) {
        this.codigoUsuarioRegistro = codigoUsuarioRegistro;
    }

    @XmlTransient
    public List<ModificarExamen> getModificarExamenList() {
        return modificarExamenList;
    }

    public void setModificarExamenList(List<ModificarExamen> modificarExamenList) {
        this.modificarExamenList = modificarExamenList;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList1() {
        return empresaList1;
    }

    public void setEmpresaList1(List<Empresa> empresaList1) {
        this.empresaList1 = empresaList1;
    }

    @XmlTransient
    public List<Tipoanestesia> getTipoanestesiaList() {
        return tipoanestesiaList;
    }

    public void setTipoanestesiaList(List<Tipoanestesia> tipoanestesiaList) {
        this.tipoanestesiaList = tipoanestesiaList;
    }

    @XmlTransient
    public List<Tipoanestesia> getTipoanestesiaList1() {
        return tipoanestesiaList1;
    }

    public void setTipoanestesiaList1(List<Tipoanestesia> tipoanestesiaList1) {
        this.tipoanestesiaList1 = tipoanestesiaList1;
    }

    @XmlTransient
    public List<Upz> getUpzList() {
        return upzList;
    }

    public void setUpzList(List<Upz> upzList) {
        this.upzList = upzList;
    }

    @XmlTransient
    public List<Upz> getUpzList1() {
        return upzList1;
    }

    public void setUpzList1(List<Upz> upzList1) {
        this.upzList1 = upzList1;
    }

    @XmlTransient
    public List<Imprimir> getImprimirList() {
        return imprimirList;
    }

    public void setImprimirList(List<Imprimir> imprimirList) {
        this.imprimirList = imprimirList;
    }

    @XmlTransient
    public List<MedioPago> getMedioPagoList() {
        return medioPagoList;
    }

    public void setMedioPagoList(List<MedioPago> medioPagoList) {
        this.medioPagoList = medioPagoList;
    }

    @XmlTransient
    public List<MedioPago> getMedioPagoList1() {
        return medioPagoList1;
    }

    public void setMedioPagoList1(List<MedioPago> medioPagoList1) {
        this.medioPagoList1 = medioPagoList1;
    }

    @XmlTransient
    public List<PagosEps> getPagosEpsList() {
        return pagosEpsList;
    }

    public void setPagosEpsList(List<PagosEps> pagosEpsList) {
        this.pagosEpsList = pagosEpsList;
    }

    @XmlTransient
    public List<RadicacionFacturas> getRadicacionFacturasList() {
        return radicacionFacturasList;
    }

    public void setRadicacionFacturasList(List<RadicacionFacturas> radicacionFacturasList) {
        this.radicacionFacturasList = radicacionFacturasList;
    }

    @XmlTransient
    public List<RadicacionFacturas> getRadicacionFacturasList1() {
        return radicacionFacturasList1;
    }

    public void setRadicacionFacturasList1(List<RadicacionFacturas> radicacionFacturasList1) {
        this.radicacionFacturasList1 = radicacionFacturasList1;
    }

    @XmlTransient
    public List<Consultorio> getConsultorioList() {
        return consultorioList;
    }

    public void setConsultorioList(List<Consultorio> consultorioList) {
        this.consultorioList = consultorioList;
    }

    @XmlTransient
    public List<Consultorio> getConsultorioList1() {
        return consultorioList1;
    }

    public void setConsultorioList1(List<Consultorio> consultorioList1) {
        this.consultorioList1 = consultorioList1;
    }

    @XmlTransient
    public List<NivelEducativo> getNivelEducativoList() {
        return nivelEducativoList;
    }

    public void setNivelEducativoList(List<NivelEducativo> nivelEducativoList) {
        this.nivelEducativoList = nivelEducativoList;
    }

    @XmlTransient
    public List<NivelEducativo> getNivelEducativoList1() {
        return nivelEducativoList1;
    }

    public void setNivelEducativoList1(List<NivelEducativo> nivelEducativoList1) {
        this.nivelEducativoList1 = nivelEducativoList1;
    }

    @XmlTransient
    public List<Estadocivil> getEstadocivilList() {
        return estadocivilList;
    }

    public void setEstadocivilList(List<Estadocivil> estadocivilList) {
        this.estadocivilList = estadocivilList;
    }

    @XmlTransient
    public List<Estadocivil> getEstadocivilList1() {
        return estadocivilList1;
    }

    public void setEstadocivilList1(List<Estadocivil> estadocivilList1) {
        this.estadocivilList1 = estadocivilList1;
    }

    @XmlTransient
    public List<Sucursalempresa> getSucursalempresaList() {
        return sucursalempresaList;
    }

    public void setSucursalempresaList(List<Sucursalempresa> sucursalempresaList) {
        this.sucursalempresaList = sucursalempresaList;
    }

    @XmlTransient
    public List<Sucursalempresa> getSucursalempresaList1() {
        return sucursalempresaList1;
    }

    public void setSucursalempresaList1(List<Sucursalempresa> sucursalempresaList1) {
        this.sucursalempresaList1 = sucursalempresaList1;
    }

    @XmlTransient
    public List<Procedimiento> getProcedimientoList() {
        return procedimientoList;
    }

    public void setProcedimientoList(List<Procedimiento> procedimientoList) {
        this.procedimientoList = procedimientoList;
    }

    @XmlTransient
    public List<Procedimiento> getProcedimientoList1() {
        return procedimientoList1;
    }

    public void setProcedimientoList1(List<Procedimiento> procedimientoList1) {
        this.procedimientoList1 = procedimientoList1;
    }

    @XmlTransient
    public List<Procedimiento> getProcedimientoList2() {
        return procedimientoList2;
    }

    public void setProcedimientoList2(List<Procedimiento> procedimientoList2) {
        this.procedimientoList2 = procedimientoList2;
    }

    @XmlTransient
    public List<Tipoprofesion> getTipoprofesionList() {
        return tipoprofesionList;
    }

    public void setTipoprofesionList(List<Tipoprofesion> tipoprofesionList) {
        this.tipoprofesionList = tipoprofesionList;
    }

    @XmlTransient
    public List<Tipoprofesion> getTipoprofesionList1() {
        return tipoprofesionList1;
    }

    public void setTipoprofesionList1(List<Tipoprofesion> tipoprofesionList1) {
        this.tipoprofesionList1 = tipoprofesionList1;
    }

    @XmlTransient
    public List<EpsSede> getEpsSedeList() {
        return epsSedeList;
    }

    public void setEpsSedeList(List<EpsSede> epsSedeList) {
        this.epsSedeList = epsSedeList;
    }

    @XmlTransient
    public List<AdministracionMedicamentos> getAdministracionMedicamentosList() {
        return administracionMedicamentosList;
    }

    public void setAdministracionMedicamentosList(List<AdministracionMedicamentos> administracionMedicamentosList) {
        this.administracionMedicamentosList = administracionMedicamentosList;
    }

    @XmlTransient
    public List<AdministracionMedicamentos> getAdministracionMedicamentosList1() {
        return administracionMedicamentosList1;
    }

    public void setAdministracionMedicamentosList1(List<AdministracionMedicamentos> administracionMedicamentosList1) {
        this.administracionMedicamentosList1 = administracionMedicamentosList1;
    }

    @XmlTransient
    public List<VacunacionPersona> getVacunacionPersonaList() {
        return vacunacionPersonaList;
    }

    public void setVacunacionPersonaList(List<VacunacionPersona> vacunacionPersonaList) {
        this.vacunacionPersonaList = vacunacionPersonaList;
    }

    @XmlTransient
    public List<VacunacionPersona> getVacunacionPersonaList1() {
        return vacunacionPersonaList1;
    }

    public void setVacunacionPersonaList1(List<VacunacionPersona> vacunacionPersonaList1) {
        this.vacunacionPersonaList1 = vacunacionPersonaList1;
    }

    @XmlTransient
    public List<Arl> getArlList() {
        return arlList;
    }

    public void setArlList(List<Arl> arlList) {
        this.arlList = arlList;
    }

    @XmlTransient
    public List<Arl> getArlList1() {
        return arlList1;
    }

    public void setArlList1(List<Arl> arlList1) {
        this.arlList1 = arlList1;
    }

    @XmlTransient
    public List<Localidad> getLocalidadList() {
        return localidadList;
    }

    public void setLocalidadList(List<Localidad> localidadList) {
        this.localidadList = localidadList;
    }

    @XmlTransient
    public List<Localidad> getLocalidadList1() {
        return localidadList1;
    }

    public void setLocalidadList1(List<Localidad> localidadList1) {
        this.localidadList1 = localidadList1;
    }

    @XmlTransient
    public List<ExamenesApoyoDiagnostico> getExamenesApoyoDiagnosticoList() {
        return examenesApoyoDiagnosticoList;
    }

    public void setExamenesApoyoDiagnosticoList(List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList) {
        this.examenesApoyoDiagnosticoList = examenesApoyoDiagnosticoList;
    }

    @XmlTransient
    public List<ExamenesApoyoDiagnostico> getExamenesApoyoDiagnosticoList1() {
        return examenesApoyoDiagnosticoList1;
    }

    public void setExamenesApoyoDiagnosticoList1(List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList1) {
        this.examenesApoyoDiagnosticoList1 = examenesApoyoDiagnosticoList1;
    }

    @XmlTransient
    public List<ExamenesApoyoDiagnostico> getExamenesApoyoDiagnosticoList2() {
        return examenesApoyoDiagnosticoList2;
    }

    public void setExamenesApoyoDiagnosticoList2(List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList2) {
        this.examenesApoyoDiagnosticoList2 = examenesApoyoDiagnosticoList2;
    }

    @XmlTransient
    public List<ExamenesApoyoDiagnostico> getExamenesApoyoDiagnosticoList3() {
        return examenesApoyoDiagnosticoList3;
    }

    public void setExamenesApoyoDiagnosticoList3(List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList3) {
        this.examenesApoyoDiagnosticoList3 = examenesApoyoDiagnosticoList3;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList() {
        return diagnosticoIngresoList;
    }

    public void setDiagnosticoIngresoList(List<DiagnosticoIngreso> diagnosticoIngresoList) {
        this.diagnosticoIngresoList = diagnosticoIngresoList;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList1() {
        return diagnosticoIngresoList1;
    }

    public void setDiagnosticoIngresoList1(List<DiagnosticoIngreso> diagnosticoIngresoList1) {
        this.diagnosticoIngresoList1 = diagnosticoIngresoList1;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList2() {
        return diagnosticoIngresoList2;
    }

    public void setDiagnosticoIngresoList2(List<DiagnosticoIngreso> diagnosticoIngresoList2) {
        this.diagnosticoIngresoList2 = diagnosticoIngresoList2;
    }

    @XmlTransient
    public List<Barrio> getBarrioList() {
        return barrioList;
    }

    public void setBarrioList(List<Barrio> barrioList) {
        this.barrioList = barrioList;
    }

    @XmlTransient
    public List<Barrio> getBarrioList1() {
        return barrioList1;
    }

    public void setBarrioList1(List<Barrio> barrioList1) {
        this.barrioList1 = barrioList1;
    }

    @XmlTransient
    public List<Tipoidentificacion> getTipoidentificacionList() {
        return tipoidentificacionList;
    }

    public void setTipoidentificacionList(List<Tipoidentificacion> tipoidentificacionList) {
        this.tipoidentificacionList = tipoidentificacionList;
    }

    @XmlTransient
    public List<Tipoidentificacion> getTipoidentificacionList1() {
        return tipoidentificacionList1;
    }

    public void setTipoidentificacionList1(List<Tipoidentificacion> tipoidentificacionList1) {
        this.tipoidentificacionList1 = tipoidentificacionList1;
    }

    @XmlTransient
    public List<Sede> getSedeList() {
        return sedeList;
    }

    public void setSedeList(List<Sede> sedeList) {
        this.sedeList = sedeList;
    }

    @XmlTransient
    public List<Sede> getSedeList1() {
        return sedeList1;
    }

    public void setSedeList1(List<Sede> sedeList1) {
        this.sedeList1 = sedeList1;
    }

    @XmlTransient
    public List<ClasificacionDosisVacunas> getClasificacionDosisVacunasList() {
        return clasificacionDosisVacunasList;
    }

    public void setClasificacionDosisVacunasList(List<ClasificacionDosisVacunas> clasificacionDosisVacunasList) {
        this.clasificacionDosisVacunasList = clasificacionDosisVacunasList;
    }

    @XmlTransient
    public List<ClasificacionDosisVacunas> getClasificacionDosisVacunasList1() {
        return clasificacionDosisVacunasList1;
    }

    public void setClasificacionDosisVacunasList1(List<ClasificacionDosisVacunas> clasificacionDosisVacunasList1) {
        this.clasificacionDosisVacunasList1 = clasificacionDosisVacunasList1;
    }

    @XmlTransient
    public List<Religion> getReligionList() {
        return religionList;
    }

    public void setReligionList(List<Religion> religionList) {
        this.religionList = religionList;
    }

    @XmlTransient
    public List<Religion> getReligionList1() {
        return religionList1;
    }

    public void setReligionList1(List<Religion> religionList1) {
        this.religionList1 = religionList1;
    }

    @XmlTransient
    public List<Consultamedica> getConsultamedicaList() {
        return consultamedicaList;
    }

    public void setConsultamedicaList(List<Consultamedica> consultamedicaList) {
        this.consultamedicaList = consultamedicaList;
    }

    @XmlTransient
    public List<Consultamedica> getConsultamedicaList1() {
        return consultamedicaList1;
    }

    public void setConsultamedicaList1(List<Consultamedica> consultamedicaList1) {
        this.consultamedicaList1 = consultamedicaList1;
    }

    @XmlTransient
    public List<Cargosede> getCargosedeList() {
        return cargosedeList;
    }

    public void setCargosedeList(List<Cargosede> cargosedeList) {
        this.cargosedeList = cargosedeList;
    }

    @XmlTransient
    public List<Cargosede> getCargosedeList1() {
        return cargosedeList1;
    }

    public void setCargosedeList1(List<Cargosede> cargosedeList1) {
        this.cargosedeList1 = cargosedeList1;
    }

    @XmlTransient
    public List<Dependencia> getDependenciaList() {
        return dependenciaList;
    }

    public void setDependenciaList(List<Dependencia> dependenciaList) {
        this.dependenciaList = dependenciaList;
    }

    @XmlTransient
    public List<Dependencia> getDependenciaList1() {
        return dependenciaList1;
    }

    public void setDependenciaList1(List<Dependencia> dependenciaList1) {
        this.dependenciaList1 = dependenciaList1;
    }

    @XmlTransient
    public List<Finalidadconsulta> getFinalidadconsultaList() {
        return finalidadconsultaList;
    }

    public void setFinalidadconsultaList(List<Finalidadconsulta> finalidadconsultaList) {
        this.finalidadconsultaList = finalidadconsultaList;
    }

    @XmlTransient
    public List<Finalidadconsulta> getFinalidadconsultaList1() {
        return finalidadconsultaList1;
    }

    public void setFinalidadconsultaList1(List<Finalidadconsulta> finalidadconsultaList1) {
        this.finalidadconsultaList1 = finalidadconsultaList1;
    }

    @XmlTransient
    public List<Estadofactura> getEstadofacturaList() {
        return estadofacturaList;
    }

    public void setEstadofacturaList(List<Estadofactura> estadofacturaList) {
        this.estadofacturaList = estadofacturaList;
    }

    @XmlTransient
    public List<Estadofactura> getEstadofacturaList1() {
        return estadofacturaList1;
    }

    public void setEstadofacturaList1(List<Estadofactura> estadofacturaList1) {
        this.estadofacturaList1 = estadofacturaList1;
    }

    @XmlTransient
    public List<Tipoexamen> getTipoexamenList() {
        return tipoexamenList;
    }

    public void setTipoexamenList(List<Tipoexamen> tipoexamenList) {
        this.tipoexamenList = tipoexamenList;
    }

    @XmlTransient
    public List<Tipoexamen> getTipoexamenList1() {
        return tipoexamenList1;
    }

    public void setTipoexamenList1(List<Tipoexamen> tipoexamenList1) {
        this.tipoexamenList1 = tipoexamenList1;
    }

    @XmlTransient
    public List<Pais> getPaisList() {
        return paisList;
    }

    public void setPaisList(List<Pais> paisList) {
        this.paisList = paisList;
    }

    @XmlTransient
    public List<Pais> getPaisList1() {
        return paisList1;
    }

    public void setPaisList1(List<Pais> paisList1) {
        this.paisList1 = paisList1;
    }

    @XmlTransient
    public List<Plm> getPlmList() {
        return plmList;
    }

    public void setPlmList(List<Plm> plmList) {
        this.plmList = plmList;
    }

    @XmlTransient
    public List<Plm> getPlmList1() {
        return plmList1;
    }

    public void setPlmList1(List<Plm> plmList1) {
        this.plmList1 = plmList1;
    }

    @XmlTransient
    public List<Descripcioncie10> getDescripcioncie10List() {
        return descripcioncie10List;
    }

    public void setDescripcioncie10List(List<Descripcioncie10> descripcioncie10List) {
        this.descripcioncie10List = descripcioncie10List;
    }

    @XmlTransient
    public List<Descripcioncie10> getDescripcioncie10List1() {
        return descripcioncie10List1;
    }

    public void setDescripcioncie10List1(List<Descripcioncie10> descripcioncie10List1) {
        this.descripcioncie10List1 = descripcioncie10List1;
    }

    @XmlTransient
    public List<Tema> getTemaList() {
        return temaList;
    }

    public void setTemaList(List<Tema> temaList) {
        this.temaList = temaList;
    }

    @XmlTransient
    public List<ClasificacionProfesionPersona> getClasificacionProfesionPersonaList() {
        return clasificacionProfesionPersonaList;
    }

    public void setClasificacionProfesionPersonaList(List<ClasificacionProfesionPersona> clasificacionProfesionPersonaList) {
        this.clasificacionProfesionPersonaList = clasificacionProfesionPersonaList;
    }

    @XmlTransient
    public List<ClasificacionProfesionPersona> getClasificacionProfesionPersonaList1() {
        return clasificacionProfesionPersonaList1;
    }

    public void setClasificacionProfesionPersonaList1(List<ClasificacionProfesionPersona> clasificacionProfesionPersonaList1) {
        this.clasificacionProfesionPersonaList1 = clasificacionProfesionPersonaList1;
    }

    @XmlTransient
    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    @XmlTransient
    public List<Departamento> getDepartamentoList1() {
        return departamentoList1;
    }

    public void setDepartamentoList1(List<Departamento> departamentoList1) {
        this.departamentoList1 = departamentoList1;
    }

    @XmlTransient
    public List<Clasificacionmedicamento> getClasificacionmedicamentoList() {
        return clasificacionmedicamentoList;
    }

    public void setClasificacionmedicamentoList(List<Clasificacionmedicamento> clasificacionmedicamentoList) {
        this.clasificacionmedicamentoList = clasificacionmedicamentoList;
    }

    @XmlTransient
    public List<Clasificacionmedicamento> getClasificacionmedicamentoList1() {
        return clasificacionmedicamentoList1;
    }

    public void setClasificacionmedicamentoList1(List<Clasificacionmedicamento> clasificacionmedicamentoList1) {
        this.clasificacionmedicamentoList1 = clasificacionmedicamentoList1;
    }

    @XmlTransient
    public List<Tipoafiliacion> getTipoafiliacionList() {
        return tipoafiliacionList;
    }

    public void setTipoafiliacionList(List<Tipoafiliacion> tipoafiliacionList) {
        this.tipoafiliacionList = tipoafiliacionList;
    }

    @XmlTransient
    public List<Tipoafiliacion> getTipoafiliacionList1() {
        return tipoafiliacionList1;
    }

    public void setTipoafiliacionList1(List<Tipoafiliacion> tipoafiliacionList1) {
        this.tipoafiliacionList1 = tipoafiliacionList1;
    }

    @XmlTransient
    public List<Profesionpersona> getProfesionpersonaList() {
        return profesionpersonaList;
    }

    public void setProfesionpersonaList(List<Profesionpersona> profesionpersonaList) {
        this.profesionpersonaList = profesionpersonaList;
    }

    @XmlTransient
    public List<Profesionpersona> getProfesionpersonaList1() {
        return profesionpersonaList1;
    }

    public void setProfesionpersonaList1(List<Profesionpersona> profesionpersonaList1) {
        this.profesionpersonaList1 = profesionpersonaList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoUsuario != null ? codigoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.codigoUsuario == null && other.codigoUsuario != null) || (this.codigoUsuario != null && !this.codigoUsuario.equals(other.codigoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreUsuario;
    }
//----------------------Hacia abjo perfiles de usuario-------------------------------// 

    public boolean getAdministrador() {
        return codigoPerfil.getCodigoPerfil() == 1;
    }

    public boolean getAdministracionSistema() {
        return codigoPerfil.getCodigoPerfil() == 2;
    }

    public boolean getArea() {
        return codigoPerfil.getCodigoPerfil() == 3;
    }

    public boolean getDependencia() {
        return codigoPerfil.getCodigoPerfil() == 4;
    }

    public boolean getCoordinador() {
        return codigoPerfil.getCodigoPerfil() == 5;
    }

    public boolean getAvanzado() {
        return codigoPerfil.getCodigoPerfil() == 6;
    }

    public boolean getMedico() {
        return codigoPerfil.getCodigoPerfil() == 7;
    }

    public boolean getAsisitente() {
        return codigoPerfil.getCodigoPerfil() == 8;
    }

    public boolean getFacturacion() {
        return codigoPerfil.getCodigoPerfil() == 9;
    }

    public boolean getCartera() {
        return codigoPerfil.getCodigoPerfil() == 10;
    }

    // codigo para validar botones de finalizados y pendientes por cada tipo de caso según el perfil
    public boolean getListaXFuncionario() {
        return getAdministrador() || getAdministracionSistema() || getArea() || getDependencia() || getCoordinador();
    }

    public boolean getListaXSede() {
        return getAdministrador() || getAdministracionSistema() || getCoordinador();
    }

    public boolean getListaXArea() {
        return getAdministrador() || getAdministracionSistema() || getCoordinador() || getArea();
    }

    public boolean getListaXDependencia() {
        return getAdministrador() || getAdministracionSistema() || getCoordinador() || getArea();
    }

    public boolean getListaXTodos() {
        return getAdministrador() || getCoordinador();
    }

    public boolean getListaXFuncionarioU() {
        return getAdministrador() || getAdministracionSistema() || getArea() || getDependencia() || getCoordinador();
    }

    public boolean getListaXCitaSede() {
        return getAdministrador() || getAdministracionSistema() || getCoordinador();
    }

}
