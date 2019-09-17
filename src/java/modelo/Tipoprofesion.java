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
@Table(name = "tipoprofesion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoprofesion.findAll", query = "SELECT t FROM Tipoprofesion t")
    , @NamedQuery(name = "Tipoprofesion.findByCodigoProfesion", query = "SELECT t FROM Tipoprofesion t WHERE t.codigoProfesion = :codigoProfesion")
    , @NamedQuery(name = "Tipoprofesion.findByNombreProfesional", query = "SELECT t FROM Tipoprofesion t WHERE t.nombreProfesional = :nombreProfesional")
    , @NamedQuery(name = "Tipoprofesion.findByActivo", query = "SELECT t FROM Tipoprofesion t WHERE t.activo = :activo")
    , @NamedQuery(name = "Tipoprofesion.findByFechaRegistro", query = "SELECT t FROM Tipoprofesion t WHERE t.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Tipoprofesion.findByFechaModificacion", query = "SELECT t FROM Tipoprofesion t WHERE t.fechaModificacion = :fechaModificacion")})
public class Tipoprofesion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoProfesion")
    private Integer codigoProfesion;
    @Size(max = 250)
    @Column(name = "nombreProfesional")
    private String nombreProfesional;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "codigProfesion")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;

    public Tipoprofesion() {
    }

    public Tipoprofesion(Integer codigoProfesion) {
        this.codigoProfesion = codigoProfesion;
    }

    public Integer getCodigoProfesion() {
        return codigoProfesion;
    }

    public void setCodigoProfesion(Integer codigoProfesion) {
        this.codigoProfesion = codigoProfesion;
    }

    public String getNombreProfesional() {
        return nombreProfesional;
    }

    public void setNombreProfesional(String nombreProfesional) {
        this.nombreProfesional = nombreProfesional;
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
        hash += (codigoProfesion != null ? codigoProfesion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoprofesion)) {
            return false;
        }
        Tipoprofesion other = (Tipoprofesion) object;
        if ((this.codigoProfesion == null && other.codigoProfesion != null) || (this.codigoProfesion != null && !this.codigoProfesion.equals(other.codigoProfesion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreProfesional;
    }

}
