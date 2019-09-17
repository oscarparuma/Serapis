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
@Table(name = "AplicarVacunaEn")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AplicarVacunaEn.findAll", query = "SELECT a FROM AplicarVacunaEn a")
    , @NamedQuery(name = "AplicarVacunaEn.findByCodigoAplicar", query = "SELECT a FROM AplicarVacunaEn a WHERE a.codigoAplicar = :codigoAplicar")
    , @NamedQuery(name = "AplicarVacunaEn.findByNombreParte", query = "SELECT a FROM AplicarVacunaEn a WHERE a.nombreParte = :nombreParte")
    , @NamedQuery(name = "AplicarVacunaEn.findByActivo", query = "SELECT a FROM AplicarVacunaEn a WHERE a.activo = :activo")
    , @NamedQuery(name = "AplicarVacunaEn.findByFechaRegistro", query = "SELECT a FROM AplicarVacunaEn a WHERE a.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "AplicarVacunaEn.findByFechaModificacion", query = "SELECT a FROM AplicarVacunaEn a WHERE a.fechaModificacion = :fechaModificacion")})
public class AplicarVacunaEn implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoAplicar")
    private Integer codigoAplicar;
    @Size(max = 50)
    @Column(name = "nombreParte")
    private String nombreParte;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "codigoAplicarEn")
    private List<VacunacionPersona> vacunacionPersonaList;

    public AplicarVacunaEn() {
    }

    public AplicarVacunaEn(Integer codigoAplicar) {
        this.codigoAplicar = codigoAplicar;
    }

    public Integer getCodigoAplicar() {
        return codigoAplicar;
    }

    public void setCodigoAplicar(Integer codigoAplicar) {
        this.codigoAplicar = codigoAplicar;
    }

    public String getNombreParte() {
        return nombreParte;
    }

    public void setNombreParte(String nombreParte) {
        this.nombreParte = nombreParte;
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

    @XmlTransient
    public List<VacunacionPersona> getVacunacionPersonaList() {
        return vacunacionPersonaList;
    }

    public void setVacunacionPersonaList(List<VacunacionPersona> vacunacionPersonaList) {
        this.vacunacionPersonaList = vacunacionPersonaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoAplicar != null ? codigoAplicar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AplicarVacunaEn)) {
            return false;
        }
        AplicarVacunaEn other = (AplicarVacunaEn) object;
        if ((this.codigoAplicar == null && other.codigoAplicar != null) || (this.codigoAplicar != null && !this.codigoAplicar.equals(other.codigoAplicar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AplicarVacunaEn[ codigoAplicar=" + codigoAplicar + " ]";
    }
    
}
