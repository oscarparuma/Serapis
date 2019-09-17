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
@Table(name = "upz")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Upz.findAll", query = "SELECT u FROM Upz u")
    , @NamedQuery(name = "Upz.findByCodigoUpz", query = "SELECT u FROM Upz u WHERE u.codigoUpz = :codigoUpz")
    , @NamedQuery(name = "Upz.findByNombreUpz", query = "SELECT u FROM Upz u WHERE u.nombreUpz = :nombreUpz")
    , @NamedQuery(name = "Upz.findByActivo", query = "SELECT u FROM Upz u WHERE u.activo = :activo")
    , @NamedQuery(name = "Upz.findByFechaRegistro", query = "SELECT u FROM Upz u WHERE u.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Upz.findByFechaModificacion", query = "SELECT u FROM Upz u WHERE u.fechaModificacion = :fechaModificacion")})
public class Upz implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codigoUpz")
    private String codigoUpz;
    @Size(max = 350)
    @Column(name = "nombreUpz")
    private String nombreUpz;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "upz")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "upz")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoLocalidad", referencedColumnName = "codigoLocalidad")
    @ManyToOne
    private Localidad codigoLocalidad;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "codigoUpz")
    private List<Barrio> barrioList;

    public Upz() {
    }

    public Upz(String codigoUpz) {
        this.codigoUpz = codigoUpz;
    }

    public String getCodigoUpz() {
        return codigoUpz;
    }

    public void setCodigoUpz(String codigoUpz) {
        this.codigoUpz = codigoUpz;
    }

    public String getNombreUpz() {
        return nombreUpz;
    }

    public void setNombreUpz(String nombreUpz) {
        this.nombreUpz = nombreUpz;
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

    public Localidad getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(Localidad codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
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
        hash += (codigoUpz != null ? codigoUpz.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Upz)) {
            return false;
        }
        Upz other = (Upz) object;
        if ((this.codigoUpz == null && other.codigoUpz != null) || (this.codigoUpz != null && !this.codigoUpz.equals(other.codigoUpz))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Upz[ codigoUpz=" + codigoUpz + " ]";
    }
    
}
