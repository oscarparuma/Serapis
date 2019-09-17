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
@Table(name = "ClasificacionProfesionPersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasificacionProfesionPersona.findAll", query = "SELECT c FROM ClasificacionProfesionPersona c")
    , @NamedQuery(name = "ClasificacionProfesionPersona.findByCodigoClasificacionProfesion", query = "SELECT c FROM ClasificacionProfesionPersona c WHERE c.codigoClasificacionProfesion = :codigoClasificacionProfesion")
    , @NamedQuery(name = "ClasificacionProfesionPersona.findByNombreClasificacionProfesion", query = "SELECT c FROM ClasificacionProfesionPersona c WHERE c.nombreClasificacionProfesion = :nombreClasificacionProfesion")
    , @NamedQuery(name = "ClasificacionProfesionPersona.findByActivo", query = "SELECT c FROM ClasificacionProfesionPersona c WHERE c.activo = :activo")
    , @NamedQuery(name = "ClasificacionProfesionPersona.findByFecharegistro", query = "SELECT c FROM ClasificacionProfesionPersona c WHERE c.fecharegistro = :fecharegistro")
    , @NamedQuery(name = "ClasificacionProfesionPersona.findByFechaModificacion", query = "SELECT c FROM ClasificacionProfesionPersona c WHERE c.fechaModificacion = :fechaModificacion")})
public class ClasificacionProfesionPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoClasificacionProfesion")
    private Long codigoClasificacionProfesion;
    @Size(max = 250)
    @Column(name = "nombreClasificacionProfesion")
    private String nombreClasificacionProfesion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "codigoClasificacionProfesionPersona")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoClasificacionProfesionPersona")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoProfesionPersona", referencedColumnName = "codigoProfesionPersona")
    @ManyToOne
    private Profesionpersona codigoProfesionPersona;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public ClasificacionProfesionPersona() {
    }

    public ClasificacionProfesionPersona(Long codigoClasificacionProfesion) {
        this.codigoClasificacionProfesion = codigoClasificacionProfesion;
    }

    public Long getCodigoClasificacionProfesion() {
        return codigoClasificacionProfesion;
    }

    public void setCodigoClasificacionProfesion(Long codigoClasificacionProfesion) {
        this.codigoClasificacionProfesion = codigoClasificacionProfesion;
    }

    public String getNombreClasificacionProfesion() {
        return nombreClasificacionProfesion;
    }

    public void setNombreClasificacionProfesion(String nombreClasificacionProfesion) {
        this.nombreClasificacionProfesion = nombreClasificacionProfesion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
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

    public Profesionpersona getCodigoProfesionPersona() {
        return codigoProfesionPersona;
    }

    public void setCodigoProfesionPersona(Profesionpersona codigoProfesionPersona) {
        this.codigoProfesionPersona = codigoProfesionPersona;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoClasificacionProfesion != null ? codigoClasificacionProfesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClasificacionProfesionPersona)) {
            return false;
        }
        ClasificacionProfesionPersona other = (ClasificacionProfesionPersona) object;
        if ((this.codigoClasificacionProfesion == null && other.codigoClasificacionProfesion != null) || (this.codigoClasificacionProfesion != null && !this.codigoClasificacionProfesion.equals(other.codigoClasificacionProfesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreClasificacionProfesion;
    }

}
