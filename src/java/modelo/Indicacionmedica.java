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
@Table(name = "indicacionmedica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indicacionmedica.findAll", query = "SELECT i FROM Indicacionmedica i")
    , @NamedQuery(name = "Indicacionmedica.findByCodigoIndicacionMedica", query = "SELECT i FROM Indicacionmedica i WHERE i.codigoIndicacionMedica = :codigoIndicacionMedica")
    , @NamedQuery(name = "Indicacionmedica.findByActvo", query = "SELECT i FROM Indicacionmedica i WHERE i.actvo = :actvo")
    , @NamedQuery(name = "Indicacionmedica.findByFechaRegistro", query = "SELECT i FROM Indicacionmedica i WHERE i.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Indicacionmedica.findByFechaModificacion", query = "SELECT i FROM Indicacionmedica i WHERE i.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Indicacionmedica.findByRecomendacionesMedicas", query = "SELECT i FROM Indicacionmedica i WHERE i.recomendacionesMedicas = :recomendacionesMedicas")})
public class Indicacionmedica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoIndicacionMedica")
    private Integer codigoIndicacionMedica;
    @Size(max = 10)
    @Column(name = "actvo")
    private String actvo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 450)
    @Column(name = "recomendacionesMedicas")
    private String recomendacionesMedicas;
    @OneToMany(mappedBy = "codigoIndicacionesMedicas")
    private List<Recomendacionesmedicamentos> recomendacionesmedicamentosList;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;
    @OneToMany(mappedBy = "codigoIndicacionMedica")
    private List<Clasificacionindicacionmedica> clasificacionindicacionmedicaList;

    public Indicacionmedica() {
    }

    public Indicacionmedica(Integer codigoIndicacionMedica) {
        this.codigoIndicacionMedica = codigoIndicacionMedica;
    }

    public Integer getCodigoIndicacionMedica() {
        return codigoIndicacionMedica;
    }

    public void setCodigoIndicacionMedica(Integer codigoIndicacionMedica) {
        this.codigoIndicacionMedica = codigoIndicacionMedica;
    }

    public String getActvo() {
        return actvo;
    }

    public void setActvo(String actvo) {
        this.actvo = actvo;
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

    public String getRecomendacionesMedicas() {
        return recomendacionesMedicas;
    }

    public void setRecomendacionesMedicas(String recomendacionesMedicas) {
        this.recomendacionesMedicas = recomendacionesMedicas;
    }

    @XmlTransient
    public List<Recomendacionesmedicamentos> getRecomendacionesmedicamentosList() {
        return recomendacionesmedicamentosList;
    }

    public void setRecomendacionesmedicamentosList(List<Recomendacionesmedicamentos> recomendacionesmedicamentosList) {
        this.recomendacionesmedicamentosList = recomendacionesmedicamentosList;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(Usuario usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    @XmlTransient
    public List<Clasificacionindicacionmedica> getClasificacionindicacionmedicaList() {
        return clasificacionindicacionmedicaList;
    }

    public void setClasificacionindicacionmedicaList(List<Clasificacionindicacionmedica> clasificacionindicacionmedicaList) {
        this.clasificacionindicacionmedicaList = clasificacionindicacionmedicaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoIndicacionMedica != null ? codigoIndicacionMedica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indicacionmedica)) {
            return false;
        }
        Indicacionmedica other = (Indicacionmedica) object;
        if ((this.codigoIndicacionMedica == null && other.codigoIndicacionMedica != null) || (this.codigoIndicacionMedica != null && !this.codigoIndicacionMedica.equals(other.codigoIndicacionMedica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       return recomendacionesMedicas;
    }

}
