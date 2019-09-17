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
@Table(name = "tipoafiliacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoafiliacion.findAll", query = "SELECT t FROM Tipoafiliacion t")
    , @NamedQuery(name = "Tipoafiliacion.findByCodigoAfiliacion", query = "SELECT t FROM Tipoafiliacion t WHERE t.codigoAfiliacion = :codigoAfiliacion")
    , @NamedQuery(name = "Tipoafiliacion.findByNombreAfiliacion", query = "SELECT t FROM Tipoafiliacion t WHERE t.nombreAfiliacion = :nombreAfiliacion")
    , @NamedQuery(name = "Tipoafiliacion.findByActivo", query = "SELECT t FROM Tipoafiliacion t WHERE t.activo = :activo")
    , @NamedQuery(name = "Tipoafiliacion.findByFechaRegistro", query = "SELECT t FROM Tipoafiliacion t WHERE t.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Tipoafiliacion.findByFechaModificacion", query = "SELECT t FROM Tipoafiliacion t WHERE t.fechaModificacion = :fechaModificacion")})
public class Tipoafiliacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoAfiliacion")
    private Integer codigoAfiliacion;
    @Size(max = 50)
    @Column(name = "nombreAfiliacion")
    private String nombreAfiliacion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "codigoAfiliacion")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoAfiliacion")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public Tipoafiliacion() {
    }

    public Tipoafiliacion(Integer codigoAfiliacion) {
        this.codigoAfiliacion = codigoAfiliacion;
    }

    public Integer getCodigoAfiliacion() {
        return codigoAfiliacion;
    }

    public void setCodigoAfiliacion(Integer codigoAfiliacion) {
        this.codigoAfiliacion = codigoAfiliacion;
    }

    public String getNombreAfiliacion() {
        return nombreAfiliacion;
    }

    public void setNombreAfiliacion(String nombreAfiliacion) {
        this.nombreAfiliacion = nombreAfiliacion;
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
        hash += (codigoAfiliacion != null ? codigoAfiliacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoafiliacion)) {
            return false;
        }
        Tipoafiliacion other = (Tipoafiliacion) object;
        if ((this.codigoAfiliacion == null && other.codigoAfiliacion != null) || (this.codigoAfiliacion != null && !this.codigoAfiliacion.equals(other.codigoAfiliacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreAfiliacion;
    }

}
