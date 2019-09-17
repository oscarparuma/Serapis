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
@Table(name = "Pensiones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pensiones.findAll", query = "SELECT p FROM Pensiones p")
    , @NamedQuery(name = "Pensiones.findByCodigoPensiones", query = "SELECT p FROM Pensiones p WHERE p.codigoPensiones = :codigoPensiones")
    , @NamedQuery(name = "Pensiones.findByIdentificacionPensiones", query = "SELECT p FROM Pensiones p WHERE p.identificacionPensiones = :identificacionPensiones")
    , @NamedQuery(name = "Pensiones.findByNombrePensiones", query = "SELECT p FROM Pensiones p WHERE p.nombrePensiones = :nombrePensiones")
    , @NamedQuery(name = "Pensiones.findByDireccion", query = "SELECT p FROM Pensiones p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Pensiones.findByTelefono", query = "SELECT p FROM Pensiones p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Pensiones.findByFechaRegistro", query = "SELECT p FROM Pensiones p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Pensiones.findByFechaModificacion", query = "SELECT p FROM Pensiones p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Pensiones.findByClasificacionPensiones", query = "SELECT p FROM Pensiones p WHERE p.clasificacionPensiones = :clasificacionPensiones")
    , @NamedQuery(name = "Pensiones.findByCodigoAsignado", query = "SELECT p FROM Pensiones p WHERE p.codigoAsignado = :codigoAsignado")
    , @NamedQuery(name = "Pensiones.findByActivo", query = "SELECT p FROM Pensiones p WHERE p.activo = :activo")
    , @NamedQuery(name = "Pensiones.findByTipoAdministradora", query = "SELECT p FROM Pensiones p WHERE p.tipoAdministradora = :tipoAdministradora")
    , @NamedQuery(name = "Pensiones.findByContacto", query = "SELECT p FROM Pensiones p WHERE p.contacto = :contacto")
    , @NamedQuery(name = "Pensiones.findByCodigoClasificacion", query = "SELECT p FROM Pensiones p WHERE p.codigoClasificacion = :codigoClasificacion")})
public class Pensiones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoPensiones")
    private Integer codigoPensiones;
    @Size(max = 150)
    @Column(name = "identificacionPensiones")
    private String identificacionPensiones;
    @Size(max = 350)
    @Column(name = "nombrePensiones")
    private String nombrePensiones;
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
    @Column(name = "clasificacionPensiones")
    private Integer clasificacionPensiones;
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
    @OneToMany(mappedBy = "codigoPensiones")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoPensiones")
    private List<Datospersona> datospersonaList;

    public Pensiones() {
    }

    public Pensiones(Integer codigoPensiones) {
        this.codigoPensiones = codigoPensiones;
    }

    public Integer getCodigoPensiones() {
        return codigoPensiones;
    }

    public void setCodigoPensiones(Integer codigoPensiones) {
        this.codigoPensiones = codigoPensiones;
    }

    public String getIdentificacionPensiones() {
        return identificacionPensiones;
    }

    public void setIdentificacionPensiones(String identificacionPensiones) {
        this.identificacionPensiones = identificacionPensiones;
    }

    public String getNombrePensiones() {
        return nombrePensiones;
    }

    public void setNombrePensiones(String nombrePensiones) {
        this.nombrePensiones = nombrePensiones;
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

    public Integer getClasificacionPensiones() {
        return clasificacionPensiones;
    }

    public void setClasificacionPensiones(Integer clasificacionPensiones) {
        this.clasificacionPensiones = clasificacionPensiones;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPensiones != null ? codigoPensiones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pensiones)) {
            return false;
        }
        Pensiones other = (Pensiones) object;
        if ((this.codigoPensiones == null && other.codigoPensiones != null) || (this.codigoPensiones != null && !this.codigoPensiones.equals(other.codigoPensiones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombrePensiones;
    }

}
