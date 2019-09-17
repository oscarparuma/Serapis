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
@Table(name = "ClasificacionDosisVacunas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasificacionDosisVacunas.findAll", query = "SELECT c FROM ClasificacionDosisVacunas c")
    , @NamedQuery(name = "ClasificacionDosisVacunas.findByCodigoDosisVacunas", query = "SELECT c FROM ClasificacionDosisVacunas c WHERE c.codigoDosisVacunas = :codigoDosisVacunas")
    , @NamedQuery(name = "ClasificacionDosisVacunas.findByNombreDosis", query = "SELECT c FROM ClasificacionDosisVacunas c WHERE c.nombreDosis = :nombreDosis")
    , @NamedQuery(name = "ClasificacionDosisVacunas.findByActivo", query = "SELECT c FROM ClasificacionDosisVacunas c WHERE c.activo = :activo")
    , @NamedQuery(name = "ClasificacionDosisVacunas.findByFechaRegistro", query = "SELECT c FROM ClasificacionDosisVacunas c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ClasificacionDosisVacunas.findByFechaModificacion", query = "SELECT c FROM ClasificacionDosisVacunas c WHERE c.fechaModificacion = :fechaModificacion")})
public class ClasificacionDosisVacunas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoDosisVacunas")
    private Integer codigoDosisVacunas;
    @Size(max = 50)
    @Column(name = "nombreDosis")
    private String nombreDosis;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "codigoDosisClasificacion")
    private List<VacunacionPersona> vacunacionPersonaList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public ClasificacionDosisVacunas() {
    }

    public ClasificacionDosisVacunas(Integer codigoDosisVacunas) {
        this.codigoDosisVacunas = codigoDosisVacunas;
    }

    public Integer getCodigoDosisVacunas() {
        return codigoDosisVacunas;
    }

    public void setCodigoDosisVacunas(Integer codigoDosisVacunas) {
        this.codigoDosisVacunas = codigoDosisVacunas;
    }

    public String getNombreDosis() {
        return nombreDosis;
    }

    public void setNombreDosis(String nombreDosis) {
        this.nombreDosis = nombreDosis;
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
    public List<VacunacionPersona> getVacunacionPersonaList() {
        return vacunacionPersonaList;
    }

    public void setVacunacionPersonaList(List<VacunacionPersona> vacunacionPersonaList) {
        this.vacunacionPersonaList = vacunacionPersonaList;
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
        hash += (codigoDosisVacunas != null ? codigoDosisVacunas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClasificacionDosisVacunas)) {
            return false;
        }
        ClasificacionDosisVacunas other = (ClasificacionDosisVacunas) object;
        if ((this.codigoDosisVacunas == null && other.codigoDosisVacunas != null) || (this.codigoDosisVacunas != null && !this.codigoDosisVacunas.equals(other.codigoDosisVacunas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ClasificacionDosisVacunas[ codigoDosisVacunas=" + codigoDosisVacunas + " ]";
    }
    
}
