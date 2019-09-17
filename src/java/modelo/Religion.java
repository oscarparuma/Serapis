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
@Table(name = "Religion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Religion.findAll", query = "SELECT r FROM Religion r")
    , @NamedQuery(name = "Religion.findByCodigoReligion", query = "SELECT r FROM Religion r WHERE r.codigoReligion = :codigoReligion")
    , @NamedQuery(name = "Religion.findByNombreReligion", query = "SELECT r FROM Religion r WHERE r.nombreReligion = :nombreReligion")
    , @NamedQuery(name = "Religion.findByActiva", query = "SELECT r FROM Religion r WHERE r.activa = :activa")
    , @NamedQuery(name = "Religion.findByFechaRegistro", query = "SELECT r FROM Religion r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Religion.findByFechaModificacion", query = "SELECT r FROM Religion r WHERE r.fechaModificacion = :fechaModificacion")})
public class Religion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoReligion")
    private Integer codigoReligion;
    @Size(max = 150)
    @Column(name = "nombreReligion")
    private String nombreReligion;
    @Size(max = 1)
    @Column(name = "activa")
    private String activa;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "codigoReligion")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoReligion")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;

    public Religion() {
    }

    public Religion(Integer codigoReligion) {
        this.codigoReligion = codigoReligion;
    }

    public Integer getCodigoReligion() {
        return codigoReligion;
    }

    public void setCodigoReligion(Integer codigoReligion) {
        this.codigoReligion = codigoReligion;
    }

    public String getNombreReligion() {
        return nombreReligion;
    }

    public void setNombreReligion(String nombreReligion) {
        this.nombreReligion = nombreReligion;
    }

    public String getActiva() {
        return activa;
    }

    public void setActiva(String activa) {
        this.activa = activa;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoReligion != null ? codigoReligion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Religion)) {
            return false;
        }
        Religion other = (Religion) object;
        if ((this.codigoReligion == null && other.codigoReligion != null) || (this.codigoReligion != null && !this.codigoReligion.equals(other.codigoReligion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return nombreReligion;
    }

}
