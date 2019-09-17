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
@Table(name = "estadocivil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadocivil.findAll", query = "SELECT e FROM Estadocivil e")
    , @NamedQuery(name = "Estadocivil.findByCodigoEstadoCivil", query = "SELECT e FROM Estadocivil e WHERE e.codigoEstadoCivil = :codigoEstadoCivil")
    , @NamedQuery(name = "Estadocivil.findByNombre", query = "SELECT e FROM Estadocivil e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Estadocivil.findByActivo", query = "SELECT e FROM Estadocivil e WHERE e.activo = :activo")
    , @NamedQuery(name = "Estadocivil.findByFechaRegistro", query = "SELECT e FROM Estadocivil e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Estadocivil.findByFechaModificacion", query = "SELECT e FROM Estadocivil e WHERE e.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Estadocivil.findBySiNo", query = "SELECT e FROM Estadocivil e WHERE e.siNo = :siNo")})
public class Estadocivil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoEstadoCivil")
    private Integer codigoEstadoCivil;
    @Size(max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 1)
    @Column(name = "siNo")
    private String siNo;
    @OneToMany(mappedBy = "codigoEstadoCivil")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoEstadoCivil")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;

    public Estadocivil() {
    }

    public Estadocivil(Integer codigoEstadoCivil) {
        this.codigoEstadoCivil = codigoEstadoCivil;
    }

    public Integer getCodigoEstadoCivil() {
        return codigoEstadoCivil;
    }

    public void setCodigoEstadoCivil(Integer codigoEstadoCivil) {
        this.codigoEstadoCivil = codigoEstadoCivil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getSiNo() {
        return siNo;
    }

    public void setSiNo(String siNo) {
        this.siNo = siNo;
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
        hash += (codigoEstadoCivil != null ? codigoEstadoCivil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadocivil)) {
            return false;
        }
        Estadocivil other = (Estadocivil) object;
        if ((this.codigoEstadoCivil == null && other.codigoEstadoCivil != null) || (this.codigoEstadoCivil != null && !this.codigoEstadoCivil.equals(other.codigoEstadoCivil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       return nombre;
    }

}
