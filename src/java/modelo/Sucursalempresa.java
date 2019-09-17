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
@Table(name = "sucursalempresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sucursalempresa.findAll", query = "SELECT s FROM Sucursalempresa s")
    , @NamedQuery(name = "Sucursalempresa.findByConsecutivoSucursalEmpresa", query = "SELECT s FROM Sucursalempresa s WHERE s.consecutivoSucursalEmpresa = :consecutivoSucursalEmpresa")
    , @NamedQuery(name = "Sucursalempresa.findByIdentificacionEmpresa", query = "SELECT s FROM Sucursalempresa s WHERE s.identificacionEmpresa = :identificacionEmpresa")
    , @NamedQuery(name = "Sucursalempresa.findByNombreEmpresa", query = "SELECT s FROM Sucursalempresa s WHERE s.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "Sucursalempresa.findByDireccio", query = "SELECT s FROM Sucursalempresa s WHERE s.direccio = :direccio")
    , @NamedQuery(name = "Sucursalempresa.findByTelefono", query = "SELECT s FROM Sucursalempresa s WHERE s.telefono = :telefono")
    , @NamedQuery(name = "Sucursalempresa.findByFechaRegistro", query = "SELECT s FROM Sucursalempresa s WHERE s.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Sucursalempresa.findByFechaModificacion", query = "SELECT s FROM Sucursalempresa s WHERE s.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Sucursalempresa.findByNombreContacto", query = "SELECT s FROM Sucursalempresa s WHERE s.nombreContacto = :nombreContacto")
    , @NamedQuery(name = "Sucursalempresa.findByActivo", query = "SELECT s FROM Sucursalempresa s WHERE s.activo = :activo")})
public class Sucursalempresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivoSucursalEmpresa")
    private Integer consecutivoSucursalEmpresa;
    @Size(max = 150)
    @Column(name = "identificacionEmpresa")
    private String identificacionEmpresa;
    @Size(max = 350)
    @Column(name = "nombreEmpresa")
    private String nombreEmpresa;
    @Size(max = 250)
    @Column(name = "direccio")
    private String direccio;
    @Size(max = 100)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 250)
    @Column(name = "nombreContacto")
    private String nombreContacto;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "sucursalEmpresa")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "sucursalEmpresa")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoDepartamento", referencedColumnName = "codigoDepartamento")
    @ManyToOne
    private Departamento codigoDepartamento;
    @JoinColumn(name = "codigoEmpresa", referencedColumnName = "consecutivoEmpresa")
    @ManyToOne
    private Empresa codigoEmpresa;
    @JoinColumn(name = "codigoMunicipio", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipio;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;

    public Sucursalempresa() {
    }

    public Sucursalempresa(Integer consecutivoSucursalEmpresa) {
        this.consecutivoSucursalEmpresa = consecutivoSucursalEmpresa;
    }

    public Integer getConsecutivoSucursalEmpresa() {
        return consecutivoSucursalEmpresa;
    }

    public void setConsecutivoSucursalEmpresa(Integer consecutivoSucursalEmpresa) {
        this.consecutivoSucursalEmpresa = consecutivoSucursalEmpresa;
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

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
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

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
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

    public Empresa getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(Empresa codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
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
        hash += (consecutivoSucursalEmpresa != null ? consecutivoSucursalEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sucursalempresa)) {
            return false;
        }
        Sucursalempresa other = (Sucursalempresa) object;
        if ((this.consecutivoSucursalEmpresa == null && other.consecutivoSucursalEmpresa != null) || (this.consecutivoSucursalEmpresa != null && !this.consecutivoSucursalEmpresa.equals(other.consecutivoSucursalEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreEmpresa;
    }

}
