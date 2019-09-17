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
@Table(name = "localidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Localidad.findAll", query = "SELECT l FROM Localidad l")
    , @NamedQuery(name = "Localidad.findByCodigoLocalidad", query = "SELECT l FROM Localidad l WHERE l.codigoLocalidad = :codigoLocalidad")
    , @NamedQuery(name = "Localidad.findByNombreLocalidad", query = "SELECT l FROM Localidad l WHERE l.nombreLocalidad = :nombreLocalidad")
    , @NamedQuery(name = "Localidad.findByActivo", query = "SELECT l FROM Localidad l WHERE l.activo = :activo")
    , @NamedQuery(name = "Localidad.findByFechaRegistro", query = "SELECT l FROM Localidad l WHERE l.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Localidad.findByFechaModificacion", query = "SELECT l FROM Localidad l WHERE l.fechaModificacion = :fechaModificacion")})
public class Localidad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codigoLocalidad")
    private String codigoLocalidad;
    @Size(max = 350)
    @Column(name = "nombreLocalidad")
    private String nombreLocalidad;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "codigoLocalidad")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoLocalidad")
    private List<Datospersona> datospersonaList;
    @OneToMany(mappedBy = "codigoLocalidad")
    private List<Upz> upzList;
    @JoinColumn(name = "codigoMunicipio", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipio;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "codigoLocalidad")
    private List<Barrio> barrioList;

    public Localidad() {
    }

    public Localidad(String codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public String getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(String codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
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
    public List<Upz> getUpzList() {
        return upzList;
    }

    public void setUpzList(List<Upz> upzList) {
        this.upzList = upzList;
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

    public Usuario getCodigoUsuarioModifico() {
        return codigoUsuarioModifico;
    }

    public void setCodigoUsuarioModifico(Usuario codigoUsuarioModifico) {
        this.codigoUsuarioModifico = codigoUsuarioModifico;
    }

    @XmlTransient
    public List<Barrio> getBarrioList() {
        return barrioList;
    }

    public void setBarrioList(List<Barrio> barrioList) {
        this.barrioList = barrioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoLocalidad != null ? codigoLocalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localidad)) {
            return false;
        }
        Localidad other = (Localidad) object;
        if ((this.codigoLocalidad == null && other.codigoLocalidad != null) || (this.codigoLocalidad != null && !this.codigoLocalidad.equals(other.codigoLocalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Localidad[ codigoLocalidad=" + codigoLocalidad + " ]";
    }
    
}
