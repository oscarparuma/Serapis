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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "empresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByConsecutivoEmpresa", query = "SELECT e FROM Empresa e WHERE e.consecutivoEmpresa = :consecutivoEmpresa")
    , @NamedQuery(name = "Empresa.findByIdentificacionEmpresa", query = "SELECT e FROM Empresa e WHERE e.identificacionEmpresa = :identificacionEmpresa")
    , @NamedQuery(name = "Empresa.findByNombreEmpresa", query = "SELECT e FROM Empresa e WHERE e.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "Empresa.findByDireccion", query = "SELECT e FROM Empresa e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Empresa.findByTelefono", query = "SELECT e FROM Empresa e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Empresa.findByFechaRegistro", query = "SELECT e FROM Empresa e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Empresa.findByFechaModificacion", query = "SELECT e FROM Empresa e WHERE e.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Empresa.findByCodigoAsignado", query = "SELECT e FROM Empresa e WHERE e.codigoAsignado = :codigoAsignado")
    , @NamedQuery(name = "Empresa.findByActivo", query = "SELECT e FROM Empresa e WHERE e.activo = :activo")
    , @NamedQuery(name = "Empresa.findByNombreContacto", query = "SELECT e FROM Empresa e WHERE e.nombreContacto = :nombreContacto")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivoEmpresa")
    private Integer consecutivoEmpresa;
    @Size(max = 150)
    @Column(name = "identificacionEmpresa")
    private String identificacionEmpresa;
    @Size(max = 350)
    @Column(name = "nombreEmpresa")
    private String nombreEmpresa;
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
    @Size(max = 50)
    @Column(name = "codigoAsignado")
    private String codigoAsignado;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Size(max = 250)
    @Column(name = "nombreContacto")
    private String nombreContacto;
    @OneToMany(mappedBy = "codigoEmpresa")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoEmpresa")
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
    @OneToMany(mappedBy = "codigoEmpresa")
    private List<Sucursalempresa> sucursalempresaList;

    public Empresa() {
    }

    public Empresa(Integer consecutivoEmpresa) {
        this.consecutivoEmpresa = consecutivoEmpresa;
    }

    public Integer getConsecutivoEmpresa() {
        return consecutivoEmpresa;
    }

    public void setConsecutivoEmpresa(Integer consecutivoEmpresa) {
        this.consecutivoEmpresa = consecutivoEmpresa;
    }

    public String getIdentificacionEmpresa() {
        return identificacionEmpresa;
    }

    public void setIdentificacionEmpresa(String identificacionEmpresa) {
        this.identificacionEmpresa = identificacionEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
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

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
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

    @XmlTransient
    public List<Sucursalempresa> getSucursalempresaList() {
        return sucursalempresaList;
    }

    public void setSucursalempresaList(List<Sucursalempresa> sucursalempresaList) {
        this.sucursalempresaList = sucursalempresaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consecutivoEmpresa != null ? consecutivoEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.consecutivoEmpresa == null && other.consecutivoEmpresa != null) || (this.consecutivoEmpresa != null && !this.consecutivoEmpresa.equals(other.consecutivoEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Empresa[ consecutivoEmpresa=" + consecutivoEmpresa + " ]";
    }

}
