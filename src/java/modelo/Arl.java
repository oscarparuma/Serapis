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
@Table(name = "arl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arl.findAll", query = "SELECT a FROM Arl a")
    , @NamedQuery(name = "Arl.findByCodigoArl", query = "SELECT a FROM Arl a WHERE a.codigoArl = :codigoArl")
    , @NamedQuery(name = "Arl.findByIdentificacionArl", query = "SELECT a FROM Arl a WHERE a.identificacionArl = :identificacionArl")
    , @NamedQuery(name = "Arl.findByNombreArl", query = "SELECT a FROM Arl a WHERE a.nombreArl = :nombreArl")
    , @NamedQuery(name = "Arl.findByDireccion", query = "SELECT a FROM Arl a WHERE a.direccion = :direccion")
    , @NamedQuery(name = "Arl.findByTelefono", query = "SELECT a FROM Arl a WHERE a.telefono = :telefono")
    , @NamedQuery(name = "Arl.findByFechaRegistro", query = "SELECT a FROM Arl a WHERE a.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Arl.findByFechaModificacion", query = "SELECT a FROM Arl a WHERE a.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Arl.findByClasificacionArl", query = "SELECT a FROM Arl a WHERE a.clasificacionArl = :clasificacionArl")
    , @NamedQuery(name = "Arl.findByCodigoAsignado", query = "SELECT a FROM Arl a WHERE a.codigoAsignado = :codigoAsignado")
    , @NamedQuery(name = "Arl.findByActivo", query = "SELECT a FROM Arl a WHERE a.activo = :activo")
    , @NamedQuery(name = "Arl.findByTipoAdministradora", query = "SELECT a FROM Arl a WHERE a.tipoAdministradora = :tipoAdministradora")
    , @NamedQuery(name = "Arl.findByContacto", query = "SELECT a FROM Arl a WHERE a.contacto = :contacto")
    , @NamedQuery(name = "Arl.findByCodigoClasificacion", query = "SELECT a FROM Arl a WHERE a.codigoClasificacion = :codigoClasificacion")})
public class Arl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoArl")
    private Integer codigoArl;
    @Size(max = 150)
    @Column(name = "identificacionArl")
    private String identificacionArl;
    @Size(max = 350)
    @Column(name = "nombreArl")
    private String nombreArl;
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 100)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "clasificacionArl")
    private Integer clasificacionArl;
    @Size(max = 50)
    @Column(name = "codigoAsignado")
    private String codigoAsignado;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Size(max = 50)
    @Column(name = "tipoAdministradora")
    private String tipoAdministradora;
    @Size(max = 250)
    @Column(name = "contacto")
    private String contacto;
    @Size(max = 50)
    @Column(name = "codigoClasificacion")
    private String codigoClasificacion;
    @OneToMany(mappedBy = "codigoArl")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoArl")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoDepartamento", referencedColumnName = "codigoDepartamento")
    @ManyToOne
    private Departamento codigoDepartamento;
    @JoinColumn(name = "codigoMunicipio", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipio;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;

    public Arl() {
    }

    public Arl(Integer codigoArl) {
        this.codigoArl = codigoArl;
    }

    public Integer getCodigoArl() {
        return codigoArl;
    }

    public void setCodigoArl(Integer codigoArl) {
        this.codigoArl = codigoArl;
    }

    public String getIdentificacionArl() {
        return identificacionArl;
    }

    public void setIdentificacionArl(String identificacionArl) {
        this.identificacionArl = identificacionArl;
    }

    public String getNombreArl() {
        return nombreArl;
    }

    public void setNombreArl(String nombreArl) {
        this.nombreArl = nombreArl;
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

    public Integer getClasificacionArl() {
        return clasificacionArl;
    }

    public void setClasificacionArl(Integer clasificacionArl) {
        this.clasificacionArl = clasificacionArl;
    }

    public String getCodigoAsignado() {
        return codigoAsignado;
    }

    public void setCodigoAsignado(String codigoAsignado) {
        this.codigoAsignado = codigoAsignado;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getTipoAdministradora() {
        return tipoAdministradora;
    }

    public void setTipoAdministradora(String tipoAdministradora) {
        this.tipoAdministradora = tipoAdministradora;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getCodigoClasificacion() {
        return codigoClasificacion;
    }

    public void setCodigoClasificacion(String codigoClasificacion) {
        this.codigoClasificacion = codigoClasificacion;
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

    public Departamento getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Departamento codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Municipio getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipio codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(Usuario usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoArl != null ? codigoArl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arl)) {
            return false;
        }
        Arl other = (Arl) object;
        if ((this.codigoArl == null && other.codigoArl != null) || (this.codigoArl != null && !this.codigoArl.equals(other.codigoArl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreArl;
    }

}
