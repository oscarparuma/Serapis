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
@Table(name = "clasificacionindicacionmedica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clasificacionindicacionmedica.findAll", query = "SELECT c FROM Clasificacionindicacionmedica c")
    , @NamedQuery(name = "Clasificacionindicacionmedica.findByCodigoClasificacionIndMedicas", query = "SELECT c FROM Clasificacionindicacionmedica c WHERE c.codigoClasificacionIndMedicas = :codigoClasificacionIndMedicas")
    , @NamedQuery(name = "Clasificacionindicacionmedica.findByActvo", query = "SELECT c FROM Clasificacionindicacionmedica c WHERE c.actvo = :actvo")
    , @NamedQuery(name = "Clasificacionindicacionmedica.findByFechaRegistro", query = "SELECT c FROM Clasificacionindicacionmedica c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Clasificacionindicacionmedica.findByFechaModificacion", query = "SELECT c FROM Clasificacionindicacionmedica c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Clasificacionindicacionmedica.findByRecomendacionesMedicas", query = "SELECT c FROM Clasificacionindicacionmedica c WHERE c.recomendacionesMedicas = :recomendacionesMedicas")})
public class Clasificacionindicacionmedica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoClasificacionIndMedicas")
    private Integer codigoClasificacionIndMedicas;
    @Size(max = 10)
    @Column(name = "actvo")
    private String actvo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 550)
    @Column(name = "recomendacionesMedicas")
    private String recomendacionesMedicas;
    @OneToMany(mappedBy = "codigoClasificacionIndicacionesMedicas")
    private List<Recomendacionesmedicamentos> recomendacionesmedicamentosList;
    @JoinColumn(name = "codigoIndicacionMedica", referencedColumnName = "codigoIndicacionMedica")
    @ManyToOne
    private Indicacionmedica codigoIndicacionMedica;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;

    public Clasificacionindicacionmedica() {
    }

    public Clasificacionindicacionmedica(Integer codigoClasificacionIndMedicas) {
        this.codigoClasificacionIndMedicas = codigoClasificacionIndMedicas;
    }

    public Integer getCodigoClasificacionIndMedicas() {
        return codigoClasificacionIndMedicas;
    }

    public void setCodigoClasificacionIndMedicas(Integer codigoClasificacionIndMedicas) {
        this.codigoClasificacionIndMedicas = codigoClasificacionIndMedicas;
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

    public Indicacionmedica getCodigoIndicacionMedica() {
        return codigoIndicacionMedica;
    }

    public void setCodigoIndicacionMedica(Indicacionmedica codigoIndicacionMedica) {
        this.codigoIndicacionMedica = codigoIndicacionMedica;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoClasificacionIndMedicas != null ? codigoClasificacionIndMedicas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificacionindicacionmedica)) {
            return false;
        }
        Clasificacionindicacionmedica other = (Clasificacionindicacionmedica) object;
        if ((this.codigoClasificacionIndMedicas == null && other.codigoClasificacionIndMedicas != null) || (this.codigoClasificacionIndMedicas != null && !this.codigoClasificacionIndMedicas.equals(other.codigoClasificacionIndMedicas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return recomendacionesMedicas;
    }

}
