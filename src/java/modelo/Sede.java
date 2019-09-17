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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "sede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s")
    , @NamedQuery(name = "Sede.findByCodigoSede", query = "SELECT s FROM Sede s WHERE s.codigoSede = :codigoSede")
    , @NamedQuery(name = "Sede.findByNombreSede", query = "SELECT s FROM Sede s WHERE s.nombreSede = :nombreSede")
    , @NamedQuery(name = "Sede.findByActivo", query = "SELECT s FROM Sede s WHERE s.activo = :activo")
    , @NamedQuery(name = "Sede.findByFechaRegistro", query = "SELECT s FROM Sede s WHERE s.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Sede.findByFechaModificacion", query = "SELECT s FROM Sede s WHERE s.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Sede.findBySiglasSede", query = "SELECT s FROM Sede s WHERE s.siglasSede = :siglasSede")
    , @NamedQuery(name = "Sede.findByDireccion", query = "SELECT s FROM Sede s WHERE s.direccion = :direccion")
    , @NamedQuery(name = "Sede.findByTelefono", query = "SELECT s FROM Sede s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "Sede.findByEmail", query = "SELECT s FROM Sede s WHERE s.email = :email")
    , @NamedQuery(name = "Sede.findByIdentificacionNit", query = "SELECT s FROM Sede s WHERE s.identificacionNit = :identificacionNit")
    , @NamedQuery(name = "Sede.findByPaginaWeb", query = "SELECT s FROM Sede s WHERE s.paginaWeb = :paginaWeb")
    , @NamedQuery(name = "Sede.findByClave", query = "SELECT s FROM Sede s WHERE s.clave = :clave")
    , @NamedQuery(name = "Sede.findByDatosIngresosNit", query = "SELECT s FROM Sede s WHERE s.datosIngresosNit = :datosIngresosNit")
    , @NamedQuery(name = "Sede.findByEstadoRadicacion", query = "SELECT s FROM Sede s WHERE s.estadoRadicacion = :estadoRadicacion")
    , @NamedQuery(name = "Sede.findByLicenciasSede", query = "SELECT s FROM Sede s WHERE s.licenciasSede = :licenciasSede")})
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoSede")
    private Integer codigoSede;
    @Size(max = 150)
    @Column(name = "nombreSede")
    private String nombreSede;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 15)
    @Column(name = "SiglasSede")
    private String siglasSede;
    @Size(max = 150)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "Por favor ingrese un correo electrónico valido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 150)
    @Column(name = "email")
    private String email;
    @Lob
    @Column(name = "logo")
    private Serializable logo;
    @Lob
    @Column(name = "encabezado")
    private String encabezado;
    @Lob
    @Column(name = "piePagina")
    private String piePagina;
    @Size(max = 50)
    @Column(name = "identificacionNit")
    private String identificacionNit;
    @Size(max = 150)
    @Column(name = "paginaWeb")
    private String paginaWeb;
    @Size(max = 50)
    @Column(name = "clave")
    private String clave;
    @Size(max = 50)
    @Column(name = "datosIngresosNit")
    private String datosIngresosNit;
    @Size(max = 1)
    @Column(name = "estadoRadicacion")
    private String estadoRadicacion;
    @Column(name = "licenciasSede")
    private Integer licenciasSede;
    @OneToMany(mappedBy = "codigoSede")
    private List<Manualtarifas> manualtarifasList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "codigoSede")
    private List<CarteraSede> carteraSedeList;
    @OneToMany(mappedBy = "codigoSede")
    private List<FacturaRadicacion> facturaRadicacionList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoSede")
    private List<ValorExamenSede> valorExamenSedeList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Especialidadsede> especialidadsedeList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Indicacionmedica> indicacionmedicaList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Procedimientosede> procedimientosedeList;
    @OneToMany(mappedBy = "codigoSede")
    private List<CodigoRips> codigoRipsList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Area> areaList;
    @OneToMany(mappedBy = "codigoSede")
    private List<ListaRadicadoEps> listaRadicadoEpsList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Datospersona> datospersonaList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Eps> epsList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Sedeclasificacioncargo> sedeclasificacioncargoList;
    @OneToMany(mappedBy = "codigoSede")
    private List<DetalleRadicadoSede> detalleRadicadoSedeList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Clasificacionindicacionmedica> clasificacionindicacionmedicaList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "codigoSede")
    private List<ModificarExamen> modificarExamenList;
    @OneToMany(mappedBy = "codigoSede")
    private List<RadicacionFacturas> radicacionFacturasList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Consultorio> consultorioList;
    @OneToMany(mappedBy = "codigoSede")
    private List<VacunacionPersona> vacunacionPersonaList;
    @OneToMany(mappedBy = "codigoSede")
    private List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList;
    @JoinColumn(name = "codigoEstadoSede", referencedColumnName = "codigoEstadoSede")
    @ManyToOne
    private EstadoSede codigoEstadoSede;
    @JoinColumn(name = "codigoRegistro", referencedColumnName = "codigoEstadoSede")
    @ManyToOne
    private EstadoSede codigoRegistro;
    @JoinColumn(name = "codigoMunicipio", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipio;
    @JoinColumn(name = "codigoPais", referencedColumnName = "codigoPais")
    @ManyToOne
    private Pais codigoPais;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "codigoSede")
    private List<Cargosede> cargosedeList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Dependencia> dependenciaList;
    @OneToMany(mappedBy = "codigoSede")
    private List<Tipoexamen> tipoexamenList;
    @JoinColumn(name = "codigoTipoIdentificacionSede", referencedColumnName = "codigoIdentificacion")
    @ManyToOne
    private Tipoidentificacion codigoTipoIdentificacionSede;

    public Sede() {
    }

    public Sede(Integer codigoSede) {
        this.codigoSede = codigoSede;
    }

    public Integer getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Integer codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
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

    public String getSiglasSede() {
        return siglasSede;
    }

    public void setSiglasSede(String siglasSede) {
        this.siglasSede = siglasSede;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Serializable getLogo() {
        return logo;
    }

    public void setLogo(Serializable logo) {
        this.logo = logo;
    }

    public String getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    public String getPiePagina() {
        return piePagina;
    }

    public void setPiePagina(String piePagina) {
        this.piePagina = piePagina;
    }

    public String getIdentificacionNit() {
        return identificacionNit;
    }

    public void setIdentificacionNit(String identificacionNit) {
        this.identificacionNit = identificacionNit;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDatosIngresosNit() {
        return datosIngresosNit;
    }

    public void setDatosIngresosNit(String datosIngresosNit) {
        this.datosIngresosNit = datosIngresosNit;
    }

    public String getEstadoRadicacion() {
        return estadoRadicacion;
    }

    public void setEstadoRadicacion(String estadoRadicacion) {
        this.estadoRadicacion = estadoRadicacion;
    }

    public Integer getLicenciasSede() {
        return licenciasSede;
    }

    public void setLicenciasSede(Integer licenciasSede) {
        this.licenciasSede = licenciasSede;
    }

    public Tipoidentificacion getCodigoTipoIdentificacionSede() {
        return codigoTipoIdentificacionSede;
    }

    public void setCodigoTipoIdentificacionSede(Tipoidentificacion codigoTipoIdentificacionSede) {
        this.codigoTipoIdentificacionSede = codigoTipoIdentificacionSede;
    }
    
    

    @XmlTransient
    public List<Manualtarifas> getManualtarifasList() {
        return manualtarifasList;
    }

    public void setManualtarifasList(List<Manualtarifas> manualtarifasList) {
        this.manualtarifasList = manualtarifasList;
    }

    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @XmlTransient
    public List<CarteraSede> getCarteraSedeList() {
        return carteraSedeList;
    }

    public void setCarteraSedeList(List<CarteraSede> carteraSedeList) {
        this.carteraSedeList = carteraSedeList;
    }

    @XmlTransient
    public List<FacturaRadicacion> getFacturaRadicacionList() {
        return facturaRadicacionList;
    }

    public void setFacturaRadicacionList(List<FacturaRadicacion> facturaRadicacionList) {
        this.facturaRadicacionList = facturaRadicacionList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    @XmlTransient
    public List<ValorExamenSede> getValorExamenSedeList() {
        return valorExamenSedeList;
    }

    public void setValorExamenSedeList(List<ValorExamenSede> valorExamenSedeList) {
        this.valorExamenSedeList = valorExamenSedeList;
    }

    @XmlTransient
    public List<Especialidadsede> getEspecialidadsedeList() {
        return especialidadsedeList;
    }

    public void setEspecialidadsedeList(List<Especialidadsede> especialidadsedeList) {
        this.especialidadsedeList = especialidadsedeList;
    }

    @XmlTransient
    public List<Indicacionmedica> getIndicacionmedicaList() {
        return indicacionmedicaList;
    }

    public void setIndicacionmedicaList(List<Indicacionmedica> indicacionmedicaList) {
        this.indicacionmedicaList = indicacionmedicaList;
    }

    @XmlTransient
    public List<Procedimientosede> getProcedimientosedeList() {
        return procedimientosedeList;
    }

    public void setProcedimientosedeList(List<Procedimientosede> procedimientosedeList) {
        this.procedimientosedeList = procedimientosedeList;
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
    public List<ListaRadicadoEps> getListaRadicadoEpsList() {
        return listaRadicadoEpsList;
    }

    public void setListaRadicadoEpsList(List<ListaRadicadoEps> listaRadicadoEpsList) {
        this.listaRadicadoEpsList = listaRadicadoEpsList;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList() {
        return datospersonaList;
    }

    public void setDatospersonaList(List<Datospersona> datospersonaList) {
        this.datospersonaList = datospersonaList;
    }

    @XmlTransient
    public List<Eps> getEpsList() {
        return epsList;
    }

    public void setEpsList(List<Eps> epsList) {
        this.epsList = epsList;
    }

    @XmlTransient
    public List<Sedeclasificacioncargo> getSedeclasificacioncargoList() {
        return sedeclasificacioncargoList;
    }

    public void setSedeclasificacioncargoList(List<Sedeclasificacioncargo> sedeclasificacioncargoList) {
        this.sedeclasificacioncargoList = sedeclasificacioncargoList;
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
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<ModificarExamen> getModificarExamenList() {
        return modificarExamenList;
    }

    public void setModificarExamenList(List<ModificarExamen> modificarExamenList) {
        this.modificarExamenList = modificarExamenList;
    }

    @XmlTransient
    public List<RadicacionFacturas> getRadicacionFacturasList() {
        return radicacionFacturasList;
    }

    public void setRadicacionFacturasList(List<RadicacionFacturas> radicacionFacturasList) {
        this.radicacionFacturasList = radicacionFacturasList;
    }

    @XmlTransient
    public List<Consultorio> getConsultorioList() {
        return consultorioList;
    }

    public void setConsultorioList(List<Consultorio> consultorioList) {
        this.consultorioList = consultorioList;
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

    public EstadoSede getCodigoEstadoSede() {
        return codigoEstadoSede;
    }

    public void setCodigoEstadoSede(EstadoSede codigoEstadoSede) {
        this.codigoEstadoSede = codigoEstadoSede;
    }

    public EstadoSede getCodigoRegistro() {
        return codigoRegistro;
    }

    public void setCodigoRegistro(EstadoSede codigoRegistro) {
        this.codigoRegistro = codigoRegistro;
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

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getCodigoUsuarioModifico() {
        return codigoUsuarioModifico;
    }

    public void setCodigoUsuarioModifico(Usuario codigoUsuarioModifico) {
        this.codigoUsuarioModifico = codigoUsuarioModifico;
    }

    @XmlTransient
    public List<Cargosede> getCargosedeList() {
        return cargosedeList;
    }

    public void setCargosedeList(List<Cargosede> cargosedeList) {
        this.cargosedeList = cargosedeList;
    }

    @XmlTransient
    public List<Dependencia> getDependenciaList() {
        return dependenciaList;
    }

    public void setDependenciaList(List<Dependencia> dependenciaList) {
        this.dependenciaList = dependenciaList;
    }

    @XmlTransient
    public List<Tipoexamen> getTipoexamenList() {
        return tipoexamenList;
    }

    public void setTipoexamenList(List<Tipoexamen> tipoexamenList) {
        this.tipoexamenList = tipoexamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoSede != null ? codigoSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sede)) {
            return false;
        }
        Sede other = (Sede) object;
        if ((this.codigoSede == null && other.codigoSede != null) || (this.codigoSede != null && !this.codigoSede.equals(other.codigoSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreSede;
    }

}
