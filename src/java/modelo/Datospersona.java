/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "datospersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Datospersona.findAll", query = "SELECT d FROM Datospersona d")
    , @NamedQuery(name = "Datospersona.findByCodigoPersona", query = "SELECT d FROM Datospersona d WHERE d.codigoPersona = :codigoPersona")
    , @NamedQuery(name = "Datospersona.findByNumeroDocumentoIdentidad", query = "SELECT d FROM Datospersona d WHERE d.numeroDocumentoIdentidad = :numeroDocumentoIdentidad")
    , @NamedQuery(name = "Datospersona.findByApellidos", query = "SELECT d FROM Datospersona d WHERE d.apellidos = :apellidos")
    , @NamedQuery(name = "Datospersona.findByNombres", query = "SELECT d FROM Datospersona d WHERE d.nombres = :nombres")
    , @NamedQuery(name = "Datospersona.findBySegundoApellido", query = "SELECT d FROM Datospersona d WHERE d.segundoApellido = :segundoApellido")
    , @NamedQuery(name = "Datospersona.findBySegundoNombre", query = "SELECT d FROM Datospersona d WHERE d.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "Datospersona.findByFechaRegistro", query = "SELECT d FROM Datospersona d WHERE d.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Datospersona.findByFechaNacimiento", query = "SELECT d FROM Datospersona d WHERE d.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Datospersona.findByFechaExpedicion", query = "SELECT d FROM Datospersona d WHERE d.fechaExpedicion = :fechaExpedicion")
    , @NamedQuery(name = "Datospersona.findByEdad", query = "SELECT d FROM Datospersona d WHERE d.edad = :edad")
    , @NamedQuery(name = "Datospersona.findByEdadMeses", query = "SELECT d FROM Datospersona d WHERE d.edadMeses = :edadMeses")
    , @NamedQuery(name = "Datospersona.findByPersonasACargo", query = "SELECT d FROM Datospersona d WHERE d.personasACargo = :personasACargo")
    , @NamedQuery(name = "Datospersona.findByBttonActivo", query = "SELECT d FROM Datospersona d WHERE d.bttonActivo = :bttonActivo")
    , @NamedQuery(name = "Datospersona.findByEdadAnos", query = "SELECT d FROM Datospersona d WHERE d.edadAnos = :edadAnos")
    , @NamedQuery(name = "Datospersona.findByEmail", query = "SELECT d FROM Datospersona d WHERE d.email = :email")
    , @NamedQuery(name = "Datospersona.findByDireccion", query = "SELECT d FROM Datospersona d WHERE d.direccion = :direccion")
    , @NamedQuery(name = "Datospersona.findByTelefono", query = "SELECT d FROM Datospersona d WHERE d.telefono = :telefono")
    , @NamedQuery(name = "Datospersona.findByTecnico", query = "SELECT d FROM Datospersona d WHERE d.tecnico = :tecnico")
    , @NamedQuery(name = "Datospersona.findByTecnologo", query = "SELECT d FROM Datospersona d WHERE d.tecnologo = :tecnologo")
    , @NamedQuery(name = "Datospersona.findByProfesional", query = "SELECT d FROM Datospersona d WHERE d.profesional = :profesional")
    , @NamedQuery(name = "Datospersona.findByEstadoActual", query = "SELECT d FROM Datospersona d WHERE d.estadoActual = :estadoActual")
    , @NamedQuery(name = "Datospersona.findByEdadPersona", query = "SELECT d FROM Datospersona d WHERE d.edadPersona = :edadPersona")
    , @NamedQuery(name = "Datospersona.findByNivelAfiliacion", query = "SELECT d FROM Datospersona d WHERE d.nivelAfiliacion = :nivelAfiliacion")
    , @NamedQuery(name = "Datospersona.findByCodigoDepartamentoRips", query = "SELECT d FROM Datospersona d WHERE d.codigoDepartamentoRips = :codigoDepartamentoRips")
    , @NamedQuery(name = "Datospersona.findByClasificacionIdentificacion", query = "SELECT d FROM Datospersona d WHERE d.clasificacionIdentificacion = :clasificacionIdentificacion")
    , @NamedQuery(name = "Datospersona.findByCodigoMunicipioRips", query = "SELECT d FROM Datospersona d WHERE d.codigoMunicipioRips = :codigoMunicipioRips")})
public class Datospersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoPersona")
    private Long codigoPersona;
    @Size(max = 50)
    @Column(name = "numeroDocumentoIdentidad")
    private String numeroDocumentoIdentidad;
    @Size(max = 100)
    @Column(name = "Apellidos")
    private String apellidos;
    @Size(max = 100)
    @Column(name = "Nombres")
    private String nombres;
    @Size(max = 100)
    @Column(name = "segundoApellido")
    private String segundoApellido;
    @Size(max = 100)
    @Column(name = "segundoNombre")
    private String segundoNombre;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNacimiento;
    @Column(name = "fechaExpedicion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaExpedicion;
    @Lob
    @Column(name = "fotoPersona")
    private Serializable fotoPersona;
    @Column(name = "edad")
    @Temporal(TemporalType.TIMESTAMP)
    private Date edad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "edadMeses")
    private BigDecimal edadMeses;
    @Size(max = 3)
    @Column(name = "personasACargo")
    private String personasACargo;
    @Size(max = 1)
    @Column(name = "bttonActivo")
    private String bttonActivo;
    @Column(name = "edadAnos")
    private BigDecimal edadAnos;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 350)
    @Column(name = "email")
    private String email;
    @Lob
    @Column(name = "otrosEstudios")
    private String otrosEstudios;
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 1)
    @Column(name = "tecnico")
    private String tecnico;
    @Size(max = 1)
    @Column(name = "tecnologo")
    private String tecnologo;
    @Size(max = 1)
    @Column(name = "profesional")
    private String profesional;
    @Lob
    @Column(name = "profesion")
    private String profesion;
    @Size(max = 1)
    @Column(name = "estadoActual")
    private String estadoActual;
    @Size(max = 4)
    @Column(name = "edadPersona")
    private String edadPersona;
    @Size(max = 1)
    @Column(name = "nivelAfiliacion")
    private String nivelAfiliacion;
    @Size(max = 15)
    @Column(name = "codigoDepartamentoRips")
    private String codigoDepartamentoRips;
    @Size(max = 15)
    @Column(name = "codigoMunicipioRips")
    private String codigoMunicipioRips;
    @OneToMany(mappedBy = "codigoPersona")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "codigoPersonaCita")
    private List<Recomendacionesmedicamentos> recomendacionesmedicamentosList;
    @OneToMany(mappedBy = "codigoPersona")
    private List<CarteraSede> carteraSedeList;
    @OneToMany(mappedBy = "codigoPersonaCita")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoPersona")
    private List<RecomendacionesMedicasOtras> recomendacionesMedicasOtrasList;
    @OneToMany(mappedBy = "codigoPersona")
    private List<Examenfisico> examenfisicoList;
    @JoinColumn(name = "estadoPersona", referencedColumnName = "codigoEstadoPersona")
    @ManyToOne
    private EstadoPersona estadoPersona;
    @JoinColumn(name = "clasificacionEps", referencedColumnName = "codigoClasificacionEps")
    @ManyToOne
    private ClasificacionEps clasificacionEps;
    @JoinColumn(name = "codigoArea", referencedColumnName = "codigoArea")
    @ManyToOne
    private Area codigoArea;
    @JoinColumn(name = "codigoArl", referencedColumnName = "codigoArl")
    @ManyToOne
    private Arl codigoArl;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @JoinColumn(name = "codigoReligion", referencedColumnName = "codigoReligion")
    @ManyToOne
    private Religion codigoReligion;
    @JoinColumn(name = "upz", referencedColumnName = "codigoUpz")
    @ManyToOne
    private Upz upz;
    @JoinColumn(name = "codigoMunicipioResidencia", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipioResidencia;
    @JoinColumn(name = "secundaria", referencedColumnName = "codigoNivelEducativo")
    @ManyToOne
    private NivelEducativo secundaria;
    @JoinColumn(name = "codigoBarrio", referencedColumnName = "codigoBarrio")
    @ManyToOne
    private Barrio codigoBarrio;
    @JoinColumn(name = "codigoLocalidad", referencedColumnName = "codigoLocalidad")
    @ManyToOne
    private Localidad codigoLocalidad;
    @JoinColumn(name = "sucursalEmpresa", referencedColumnName = "consecutivoSucursalEmpresa")
    @ManyToOne
    private Sucursalempresa sucursalEmpresa;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoCargo", referencedColumnName = "codigoCargoPersona")
    @ManyToOne
    private Cargopersona codigoCargo;
    @JoinColumn(name = "codigoClasificacionProfesionPersona", referencedColumnName = "codigoClasificacionProfesion")
    @ManyToOne
    private ClasificacionProfesionPersona codigoClasificacionProfesionPersona;
    @JoinColumn(name = "codigoDepartamento", referencedColumnName = "codigoDepartamento")
    @ManyToOne
    private Departamento codigoDepartamento;
    @JoinColumn(name = "genero", referencedColumnName = "codigoGenero")
    @ManyToOne
    private Tipogenero genero;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoProfesionPersona", referencedColumnName = "codigoProfesionPersona")
    @ManyToOne
    private Profesionpersona codigoProfesionPersona;
    @JoinColumn(name = "codigoAfiliacion", referencedColumnName = "codigoAfiliacion")
    @ManyToOne
    private Tipoafiliacion codigoAfiliacion;
    @JoinColumn(name = "codigoDependencia", referencedColumnName = "codigoDependencia")
    @ManyToOne
    private Dependencia codigoDependencia;
    @JoinColumn(name = "codigoEmpresa", referencedColumnName = "consecutivoEmpresa")
    @ManyToOne
    private Empresa codigoEmpresa;
    @JoinColumn(name = "codigoEps", referencedColumnName = "codigoEps")
    @ManyToOne
    private Eps codigoEps;
    @JoinColumn(name = "codigoDocumentoIdentidad", referencedColumnName = "codigoIdentificacion")
    @ManyToOne
    private Tipoidentificacion codigoDocumentoIdentidad;
    @JoinColumn(name = "codigProfesion", referencedColumnName = "codigoProfesion")
    @ManyToOne
    private Tipoprofesion codigProfesion;
    @JoinColumn(name = "codigoMunicipioNacimiento", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipioNacimiento;
    @JoinColumn(name = "codigoPensiones", referencedColumnName = "codigoPensiones")
    @ManyToOne
    private Pensiones codigoPensiones;
    @JoinColumn(name = "codigoMunicipioExpedicion", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipioExpedicion;
    @JoinColumn(name = "codigoEstadoCivil", referencedColumnName = "codigoEstadoCivil")
    @ManyToOne
    private Estadocivil codigoEstadoCivil;
    @JoinColumn(name = "primaria", referencedColumnName = "codigoNivelEducativo")
    @ManyToOne
    private NivelEducativo primaria;
    @OneToMany(mappedBy = "codigoPersona")
    private List<DetalleRadicadoSede> detalleRadicadoSedeList;
    @OneToMany(mappedBy = "codigoPersona")
    private List<Formulamedica> formulamedicaList;
    @OneToMany(mappedBy = "codigoPersona")
    private List<Imprimir> imprimirList;
    @OneToMany(mappedBy = "codigoPersona")
    private List<Procedimiento> procedimientoList;
    @OneToMany(mappedBy = "codigoPersona")
    private List<VacunacionPersona> vacunacionPersonaList;
    @OneToMany(mappedBy = "codigoPersona")
    private List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList;
    @OneToMany(mappedBy = "codigoPersona")
    private List<DiagnosticoIngreso> diagnosticoIngresoList;
    @OneToMany(mappedBy = "codigoPersona")
    private List<Consultamedica> consultamedicaList;
    @JoinColumn(name = "codigoTipoUsuario", referencedColumnName = "codigoTipoUsuario")
    @ManyToOne
    private TipoUsuario codigoTipoUsuario;
    @JoinColumn(name = "codigoTipoEdad", referencedColumnName = "codigoTipoEdad")
    @ManyToOne
    private TipoEdad codigoTipoEdad;
    @JoinColumn(name = "codigoZonaResidencia", referencedColumnName = "codigoZona")
    @ManyToOne
    private ZonaResidencia codigoZonaResidencia;
    @Size(max = 10)
    @Column(name = "clasificacionIdentificacion")
    private String clasificacionIdentificacion;

    public Datospersona() {
    }

    public Datospersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public Long getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Long codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Serializable getFotoPersona() {
        return fotoPersona;
    }

    public void setFotoPersona(Serializable fotoPersona) {
        this.fotoPersona = fotoPersona;
    }

    public Date getEdad() {
        return edad;
    }

    public void setEdad(Date edad) {
        this.edad = edad;
    }

    public BigDecimal getEdadMeses() {
        return edadMeses;
    }

    public void setEdadMeses(BigDecimal edadMeses) {
        this.edadMeses = edadMeses;
    }

    public String getPersonasACargo() {
        return personasACargo;
    }

    public void setPersonasACargo(String personasACargo) {
        this.personasACargo = personasACargo;
    }

    public String getBttonActivo() {
        return bttonActivo;
    }

    public void setBttonActivo(String bttonActivo) {
        this.bttonActivo = bttonActivo;
    }

    public BigDecimal getEdadAnos() {
        return edadAnos;
    }

    public void setEdadAnos(BigDecimal edadAnos) {
        this.edadAnos = edadAnos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtrosEstudios() {
        return otrosEstudios;
    }

    public void setOtrosEstudios(String otrosEstudios) {
        this.otrosEstudios = otrosEstudios;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getTecnologo() {
        return tecnologo;
    }

    public void setTecnologo(String tecnologo) {
        this.tecnologo = tecnologo;
    }

    public String getProfesional() {
        return profesional;
    }

    public void setProfesional(String profesional) {
        this.profesional = profesional;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    public String getEdadPersona() {
        return edadPersona;
    }

    public void setEdadPersona(String edadPersona) {
        this.edadPersona = edadPersona;
    }

    public String getNivelAfiliacion() {
        return nivelAfiliacion;
    }

    public void setNivelAfiliacion(String nivelAfiliacion) {
        this.nivelAfiliacion = nivelAfiliacion;
    }

    public String getCodigoDepartamentoRips() {
        return codigoDepartamentoRips;
    }

    public void setCodigoDepartamentoRips(String codigoDepartamentoRips) {
        this.codigoDepartamentoRips = codigoDepartamentoRips;
    }

    public String getCodigoMunicipioRips() {
        return codigoMunicipioRips;
    }

    public void setCodigoMunicipioRips(String codigoMunicipioRips) {
        this.codigoMunicipioRips = codigoMunicipioRips;
    }

    public String getClasificacionIdentificacion() {
        return clasificacionIdentificacion;
    }

    public void setClasificacionIdentificacion(String clasificacionIdentificacion) {
        this.clasificacionIdentificacion = clasificacionIdentificacion;
    }

    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @XmlTransient
    public List<Recomendacionesmedicamentos> getRecomendacionesmedicamentosList() {
        return recomendacionesmedicamentosList;
    }

    public void setRecomendacionesmedicamentosList(List<Recomendacionesmedicamentos> recomendacionesmedicamentosList) {
        this.recomendacionesmedicamentosList = recomendacionesmedicamentosList;
    }

    @XmlTransient
    public List<CarteraSede> getCarteraSedeList() {
        return carteraSedeList;
    }

    public void setCarteraSedeList(List<CarteraSede> carteraSedeList) {
        this.carteraSedeList = carteraSedeList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    @XmlTransient
    public List<RecomendacionesMedicasOtras> getRecomendacionesMedicasOtrasList() {
        return recomendacionesMedicasOtrasList;
    }

    public void setRecomendacionesMedicasOtrasList(List<RecomendacionesMedicasOtras> recomendacionesMedicasOtrasList) {
        this.recomendacionesMedicasOtrasList = recomendacionesMedicasOtrasList;
    }

    @XmlTransient
    public List<Examenfisico> getExamenfisicoList() {
        return examenfisicoList;
    }

    public void setExamenfisicoList(List<Examenfisico> examenfisicoList) {
        this.examenfisicoList = examenfisicoList;
    }

    public EstadoPersona getEstadoPersona() {
        return estadoPersona;
    }

    public void setEstadoPersona(EstadoPersona estadoPersona) {
        this.estadoPersona = estadoPersona;
    }

    public ClasificacionEps getClasificacionEps() {
        return clasificacionEps;
    }

    public void setClasificacionEps(ClasificacionEps clasificacionEps) {
        this.clasificacionEps = clasificacionEps;
    }

    public Area getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Area codigoArea) {
        this.codigoArea = codigoArea;
    }

    public Arl getCodigoArl() {
        return codigoArl;
    }

    public void setCodigoArl(Arl codigoArl) {
        this.codigoArl = codigoArl;
    }

    public Usuario getCodigoUsuarioModifico() {
        return codigoUsuarioModifico;
    }

    public void setCodigoUsuarioModifico(Usuario codigoUsuarioModifico) {
        this.codigoUsuarioModifico = codigoUsuarioModifico;
    }

    public Religion getCodigoReligion() {
        return codigoReligion;
    }

    public void setCodigoReligion(Religion codigoReligion) {
        this.codigoReligion = codigoReligion;
    }

    public Upz getUpz() {
        return upz;
    }

    public void setUpz(Upz upz) {
        this.upz = upz;
    }

    public Municipio getCodigoMunicipioResidencia() {
        return codigoMunicipioResidencia;
    }

    public void setCodigoMunicipioResidencia(Municipio codigoMunicipioResidencia) {
        this.codigoMunicipioResidencia = codigoMunicipioResidencia;
    }

    public NivelEducativo getSecundaria() {
        return secundaria;
    }

    public void setSecundaria(NivelEducativo secundaria) {
        this.secundaria = secundaria;
    }

    public Barrio getCodigoBarrio() {
        return codigoBarrio;
    }

    public void setCodigoBarrio(Barrio codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public Localidad getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(Localidad codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public Sucursalempresa getSucursalEmpresa() {
        return sucursalEmpresa;
    }

    public void setSucursalEmpresa(Sucursalempresa sucursalEmpresa) {
        this.sucursalEmpresa = sucursalEmpresa;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public Cargopersona getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Cargopersona codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public ClasificacionProfesionPersona getCodigoClasificacionProfesionPersona() {
        return codigoClasificacionProfesionPersona;
    }

    public void setCodigoClasificacionProfesionPersona(ClasificacionProfesionPersona codigoClasificacionProfesionPersona) {
        this.codigoClasificacionProfesionPersona = codigoClasificacionProfesionPersona;
    }

    public Departamento getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Departamento codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Tipogenero getGenero() {
        return genero;
    }

    public void setGenero(Tipogenero genero) {
        this.genero = genero;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Profesionpersona getCodigoProfesionPersona() {
        return codigoProfesionPersona;
    }

    public void setCodigoProfesionPersona(Profesionpersona codigoProfesionPersona) {
        this.codigoProfesionPersona = codigoProfesionPersona;
    }

    public Tipoafiliacion getCodigoAfiliacion() {
        return codigoAfiliacion;
    }

    public void setCodigoAfiliacion(Tipoafiliacion codigoAfiliacion) {
        this.codigoAfiliacion = codigoAfiliacion;
    }

    public Dependencia getCodigoDependencia() {
        return codigoDependencia;
    }

    public void setCodigoDependencia(Dependencia codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }

    public Empresa getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Empresa codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public Eps getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(Eps codigoEps) {
        this.codigoEps = codigoEps;
    }

    public Tipoidentificacion getCodigoDocumentoIdentidad() {
        return codigoDocumentoIdentidad;
    }

    public void setCodigoDocumentoIdentidad(Tipoidentificacion codigoDocumentoIdentidad) {
        this.codigoDocumentoIdentidad = codigoDocumentoIdentidad;
    }

    public Tipoprofesion getCodigProfesion() {
        return codigProfesion;
    }

    public void setCodigProfesion(Tipoprofesion codigProfesion) {
        this.codigProfesion = codigProfesion;
    }

    public Municipio getCodigoMunicipioNacimiento() {
        return codigoMunicipioNacimiento;
    }

    public void setCodigoMunicipioNacimiento(Municipio codigoMunicipioNacimiento) {
        this.codigoMunicipioNacimiento = codigoMunicipioNacimiento;
    }

    public Pensiones getCodigoPensiones() {
        return codigoPensiones;
    }

    public void setCodigoPensiones(Pensiones codigoPensiones) {
        this.codigoPensiones = codigoPensiones;
    }

    public Municipio getCodigoMunicipioExpedicion() {
        return codigoMunicipioExpedicion;
    }

    public void setCodigoMunicipioExpedicion(Municipio codigoMunicipioExpedicion) {
        this.codigoMunicipioExpedicion = codigoMunicipioExpedicion;
    }

    public Estadocivil getCodigoEstadoCivil() {
        return codigoEstadoCivil;
    }

    public void setCodigoEstadoCivil(Estadocivil codigoEstadoCivil) {
        this.codigoEstadoCivil = codigoEstadoCivil;
    }

    public NivelEducativo getPrimaria() {
        return primaria;
    }

    public void setPrimaria(NivelEducativo primaria) {
        this.primaria = primaria;
    }

    @XmlTransient
    public List<DetalleRadicadoSede> getDetalleRadicadoSedeList() {
        return detalleRadicadoSedeList;
    }

    public void setDetalleRadicadoSedeList(List<DetalleRadicadoSede> detalleRadicadoSedeList) {
        this.detalleRadicadoSedeList = detalleRadicadoSedeList;
    }

    @XmlTransient
    public List<Formulamedica> getFormulamedicaList() {
        return formulamedicaList;
    }

    public void setFormulamedicaList(List<Formulamedica> formulamedicaList) {
        this.formulamedicaList = formulamedicaList;
    }

    @XmlTransient
    public List<Imprimir> getImprimirList() {
        return imprimirList;
    }

    public void setImprimirList(List<Imprimir> imprimirList) {
        this.imprimirList = imprimirList;
    }

    @XmlTransient
    public List<Procedimiento> getProcedimientoList() {
        return procedimientoList;
    }

    public void setProcedimientoList(List<Procedimiento> procedimientoList) {
        this.procedimientoList = procedimientoList;
    }

    @XmlTransient
    public List<VacunacionPersona> getVacunacionPersonaList() {
        return vacunacionPersonaList;
    }

    public void setVacunacionPersonaList(List<VacunacionPersona> vacunacionPersonaList) {
        this.vacunacionPersonaList = vacunacionPersonaList;
    }

    @XmlTransient
    public List<ExamenesApoyoDiagnostico> getExamenesApoyoDiagnosticoList() {
        return examenesApoyoDiagnosticoList;
    }

    public void setExamenesApoyoDiagnosticoList(List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList) {
        this.examenesApoyoDiagnosticoList = examenesApoyoDiagnosticoList;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList() {
        return diagnosticoIngresoList;
    }

    public void setDiagnosticoIngresoList(List<DiagnosticoIngreso> diagnosticoIngresoList) {
        this.diagnosticoIngresoList = diagnosticoIngresoList;
    }

    @XmlTransient
    public List<Consultamedica> getConsultamedicaList() {
        return consultamedicaList;
    }

    public void setConsultamedicaList(List<Consultamedica> consultamedicaList) {
        this.consultamedicaList = consultamedicaList;
    }

    public TipoUsuario getCodigoTipoUsuario() {
        return codigoTipoUsuario;
    }

    public void setCodigoTipoUsuario(TipoUsuario codigoTipoUsuario) {
        this.codigoTipoUsuario = codigoTipoUsuario;
    }

    public TipoEdad getCodigoTipoEdad() {
        return codigoTipoEdad;
    }

    public void setCodigoTipoEdad(TipoEdad codigoTipoEdad) {
        this.codigoTipoEdad = codigoTipoEdad;
    }

    public ZonaResidencia getCodigoZonaResidencia() {
        return codigoZonaResidencia;
    }

    public void setCodigoZonaResidencia(ZonaResidencia codigoZonaResidencia) {
        this.codigoZonaResidencia = codigoZonaResidencia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPersona != null ? codigoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Datospersona)) {
            return false;
        }
        Datospersona other = (Datospersona) object;
        if ((this.codigoPersona == null && other.codigoPersona != null) || (this.codigoPersona != null && !this.codigoPersona.equals(other.codigoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombres + " " + segundoNombre + " " + apellidos + " " + segundoApellido;
    }

    public void setCodigoDepartamentoRips(Departamento codigoDepartamento, String codigoDepartamentoRips) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCodigoDepartamento(String codigoDepartamentoRips) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
