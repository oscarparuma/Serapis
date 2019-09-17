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
@Table(name = "NumeroDosis")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NumeroDosis.findAll", query = "SELECT n FROM NumeroDosis n")
    , @NamedQuery(name = "NumeroDosis.findByCodigoDosis", query = "SELECT n FROM NumeroDosis n WHERE n.codigoDosis = :codigoDosis")
    , @NamedQuery(name = "NumeroDosis.findByNombreDosis", query = "SELECT n FROM NumeroDosis n WHERE n.nombreDosis = :nombreDosis")
    , @NamedQuery(name = "NumeroDosis.findByActivo", query = "SELECT n FROM NumeroDosis n WHERE n.activo = :activo")
    , @NamedQuery(name = "NumeroDosis.findByFechaRegistro", query = "SELECT n FROM NumeroDosis n WHERE n.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "NumeroDosis.findByFechaModificacion", query = "SELECT n FROM NumeroDosis n WHERE n.fechaModificacion = :fechaModificacion")})
public class NumeroDosis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoDosis")
    private Integer codigoDosis;
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
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "codigoDosisi")
    private List<VacunacionPersona> vacunacionPersonaList;

    public NumeroDosis() {
    }

    public NumeroDosis(Integer codigoDosis) {
        this.codigoDosis = codigoDosis;
    }

    public Integer getCodigoDosis() {
        return codigoDosis;
    }

    public void setCodigoDosis(Integer codigoDosis) {
        this.codigoDosis = codigoDosis;
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
        hash += (codigoDosis != null ? codigoDosis.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NumeroDosis)) {
            return false;
        }
        NumeroDosis other = (NumeroDosis) object;
        if ((this.codigoDosis == null && other.codigoDosis != null) || (this.codigoDosis != null && !this.codigoDosis.equals(other.codigoDosis))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.NumeroDosis[ codigoDosis=" + codigoDosis + " ]";
    }
    
}
