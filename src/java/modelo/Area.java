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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "area")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Area.findAll", query = "SELECT a FROM Area a")
    , @NamedQuery(name = "Area.findByCodigoArea", query = "SELECT a FROM Area a WHERE a.codigoArea = :codigoArea")
    , @NamedQuery(name = "Area.findByNombreArea", query = "SELECT a FROM Area a WHERE a.nombreArea = :nombreArea")
    , @NamedQuery(name = "Area.findByActivo", query = "SELECT a FROM Area a WHERE a.activo = :activo")
    , @NamedQuery(name = "Area.findByFechaRegistro", query = "SELECT a FROM Area a WHERE a.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Area.findByFechaModificacion", query = "SELECT a FROM Area a WHERE a.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Area.findBySiglasArea", query = "SELECT a FROM Area a WHERE a.siglasArea = :siglasArea")
    , @NamedQuery(name = "Area.findByDireccion", query = "SELECT a FROM Area a WHERE a.direccion = :direccion")
    , @NamedQuery(name = "Area.findByTelefono", query = "SELECT a FROM Area a WHERE a.telefono = :telefono")
    , @NamedQuery(name = "Area.findByEmail", query = "SELECT a FROM Area a WHERE a.email = :email")})
public class Area implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoArea")
    private Integer codigoArea;
    @Size(max = 150)
    @Column(name = "nombreArea")
    private String nombreArea;
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
    @Column(name = "siglasArea")
    private String siglasArea;
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
    @OneToMany(mappedBy = "codigoArea")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "codigoArea")
    private List<Citapersona> citapersonaList;
    @JoinColumn(name = "codigoMunicipio", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipio;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigousuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigousuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "codigoArea")
    private List<Datospersona> datospersonaList;
    @OneToMany(mappedBy = "codigoArea")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "codigoArea")
    private List<Dependencia> dependenciaList;
    @OneToMany(mappedBy = "codigoArea")
    private List<Tipoexamen> tipoexamenList;

    public Area() {
    }

    public Area(Integer codigoArea) {
        this.codigoArea = codigoArea;
    }

    public Integer getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Integer codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
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

    public String getSiglasArea() {
        return siglasArea;
    }

    public void setSiglasArea(String siglasArea) {
        this.siglasArea = siglasArea;
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

    public Usuario getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(Usuario codigousuario) {
        this.codigousuario = codigousuario;
    }

    public Usuario getCodigoUsuarioModifico() {
        return codigoUsuarioModifico;
    }

    public void setCodigoUsuarioModifico(Usuario codigoUsuarioModifico) {
        this.codigoUsuarioModifico = codigoUsuarioModifico;
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
        hash += (codigoArea != null ? codigoArea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Area)) {
            return false;
        }
        Area other = (Area) object;
        if ((this.codigoArea == null && other.codigoArea != null) || (this.codigoArea != null && !this.codigoArea.equals(other.codigoArea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreArea;
    }

}
