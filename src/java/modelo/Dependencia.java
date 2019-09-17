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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "dependencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dependencia.findAll", query = "SELECT d FROM Dependencia d")
    , @NamedQuery(name = "Dependencia.findByCodigoDependencia", query = "SELECT d FROM Dependencia d WHERE d.codigoDependencia = :codigoDependencia")
    , @NamedQuery(name = "Dependencia.findByNombreDependencia", query = "SELECT d FROM Dependencia d WHERE d.nombreDependencia = :nombreDependencia")
    , @NamedQuery(name = "Dependencia.findByActivo", query = "SELECT d FROM Dependencia d WHERE d.activo = :activo")
    , @NamedQuery(name = "Dependencia.findByFechaRegistro", query = "SELECT d FROM Dependencia d WHERE d.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Dependencia.findByFechaModificacion", query = "SELECT d FROM Dependencia d WHERE d.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Dependencia.findBySiglasDependencia", query = "SELECT d FROM Dependencia d WHERE d.siglasDependencia = :siglasDependencia")
    , @NamedQuery(name = "Dependencia.findByDireccion", query = "SELECT d FROM Dependencia d WHERE d.direccion = :direccion")
    , @NamedQuery(name = "Dependencia.findByTelefono", query = "SELECT d FROM Dependencia d WHERE d.telefono = :telefono")
    , @NamedQuery(name = "Dependencia.findByEmail", query = "SELECT d FROM Dependencia d WHERE d.email = :email")})
public class Dependencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoDependencia")
    private Integer codigoDependencia;
    @Size(max = 150)
    @Column(name = "nombreDependencia")
    private String nombreDependencia;
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
    @Column(name = "siglasDependencia")
    private String siglasDependencia;
    @Size(max = 150)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 50)
    @Column(name = "telefono")
    private String telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
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
    @OneToMany(mappedBy = "codigoDependencia")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "codigoDependencia")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoDependencia")
    private List<Datospersona> datospersonaList;
    @OneToMany(mappedBy = "codigoDependencia")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "codigoDependencia")
    private List<Consultorio> consultorioList;
    @JoinColumn(name = "codigoArea", referencedColumnName = "codigoArea")
    @ManyToOne
    private Area codigoArea;
    @JoinColumn(name = "codigoMunicipio", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipio;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "codigoDependencia")
    private List<Tipoexamen> tipoexamenList;

    public Dependencia() {
    }

    public Dependencia(Integer codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }

    public Integer getCodigoDependencia() {
        return codigoDependencia;
    }

    public void setCodigoDependencia(Integer codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }

    public String getNombreDependencia() {
        return nombreDependencia;
    }

    public void setNombreDependencia(String nombreDependencia) {
        this.nombreDependencia = nombreDependencia;
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

    public String getSiglasDependencia() {
        return siglasDependencia;
    }

    public void setSiglasDependencia(String siglasDependencia) {
        this.siglasDependencia = siglasDependencia;
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

    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList() {
        return datospersonaList;
    }

    public void setDatospersonaList(List<Datospersona> datospersonaList) {
        this.datospersonaList = datospersonaList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Consultorio> getConsultorioList() {
        return consultorioList;
    }

    public void setConsultorioList(List<Consultorio> consultorioList) {
        this.consultorioList = consultorioList;
    }

    public Area getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Area codigoArea) {
        this.codigoArea = codigoArea;
    }

    public Municipio getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipio codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
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
    public List<Tipoexamen> getTipoexamenList() {
        return tipoexamenList;
    }

    public void setTipoexamenList(List<Tipoexamen> tipoexamenList) {
        this.tipoexamenList = tipoexamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDependencia != null ? codigoDependencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dependencia)) {
            return false;
        }
        Dependencia other = (Dependencia) object;
        if ((this.codigoDependencia == null && other.codigoDependencia != null) || (this.codigoDependencia != null && !this.codigoDependencia.equals(other.codigoDependencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreDependencia;
    }

}
