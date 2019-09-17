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
@Table(name = "vacuna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vacuna.findAll", query = "SELECT v FROM Vacuna v")
    , @NamedQuery(name = "Vacuna.findByCodigoVacuna", query = "SELECT v FROM Vacuna v WHERE v.codigoVacuna = :codigoVacuna")
    , @NamedQuery(name = "Vacuna.findByNombreVacuna", query = "SELECT v FROM Vacuna v WHERE v.nombreVacuna = :nombreVacuna")
    , @NamedQuery(name = "Vacuna.findByFechaRegistro", query = "SELECT v FROM Vacuna v WHERE v.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Vacuna.findByFechaModificacion", query = "SELECT v FROM Vacuna v WHERE v.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Vacuna.findByActivo", query = "SELECT v FROM Vacuna v WHERE v.activo = :activo")})
public class Vacuna implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoVacuna")
    private Integer codigoVacuna;
    @Size(max = 350)
    @Column(name = "nombreVacuna")
    private String nombreVacuna;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "codigoVacuna")
    private List<VacunacionPersona> vacunacionPersonaList;

    public Vacuna() {
    }

    public Vacuna(Integer codigoVacuna) {
        this.codigoVacuna = codigoVacuna;
    }

    public Integer getCodigoVacuna() {
        return codigoVacuna;
    }

    public void setCodigoVacuna(Integer codigoVacuna) {
        this.codigoVacuna = codigoVacuna;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
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
        hash += (codigoVacuna != null ? codigoVacuna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vacuna)) {
            return false;
        }
        Vacuna other = (Vacuna) object;
        if ((this.codigoVacuna == null && other.codigoVacuna != null) || (this.codigoVacuna != null && !this.codigoVacuna.equals(other.codigoVacuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Vacuna[ codigoVacuna=" + codigoVacuna + " ]";
    }
    
}
