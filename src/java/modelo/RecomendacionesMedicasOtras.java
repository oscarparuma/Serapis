/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "recomendacionesMedicasOtras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RecomendacionesMedicasOtras.findAll", query = "SELECT r FROM RecomendacionesMedicasOtras r")
    , @NamedQuery(name = "RecomendacionesMedicasOtras.findByCodigoRecMedica", query = "SELECT r FROM RecomendacionesMedicasOtras r WHERE r.codigoRecMedica = :codigoRecMedica")
    , @NamedQuery(name = "RecomendacionesMedicasOtras.findByBotRecMedica", query = "SELECT r FROM RecomendacionesMedicasOtras r WHERE r.botRecMedica = :botRecMedica")
    , @NamedQuery(name = "RecomendacionesMedicasOtras.findByActivo", query = "SELECT r FROM RecomendacionesMedicasOtras r WHERE r.activo = :activo")
    , @NamedQuery(name = "RecomendacionesMedicasOtras.findByFechaRegistro", query = "SELECT r FROM RecomendacionesMedicasOtras r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "RecomendacionesMedicasOtras.findByFechaModificacion", query = "SELECT r FROM RecomendacionesMedicasOtras r WHERE r.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "RecomendacionesMedicasOtras.findByNumeroDocumentoIdentidad", query = "SELECT r FROM RecomendacionesMedicasOtras r WHERE r.numeroDocumentoIdentidad = :numeroDocumentoIdentidad")})
public class RecomendacionesMedicasOtras implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoRecMedica")
    private Long codigoRecMedica;
    @Lob
    @Column(name = "recoMedica")
    private String recoMedica;
    @Size(max = 1)
    @Column(name = "botRecMedica")
    private String botRecMedica;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 50)
    @Column(name = "numeroDocumentoIdentidad")
    private String numeroDocumentoIdentidad;
    @JoinColumn(name = "codigoCita", referencedColumnName = "codigoCitaPersona")
    @ManyToOne(cascade = CascadeType.ALL)
    private Citapersona codigoCita;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public RecomendacionesMedicasOtras() {
    }

    public RecomendacionesMedicasOtras(Long codigoRecMedica) {
        this.codigoRecMedica = codigoRecMedica;
    }

    public Long getCodigoRecMedica() {
        return codigoRecMedica;
    }

    public void setCodigoRecMedica(Long codigoRecMedica) {
        this.codigoRecMedica = codigoRecMedica;
    }

    public String getRecoMedica() {
        return recoMedica;
    }

    public void setRecoMedica(String recoMedica) {
        this.recoMedica = recoMedica;
    }

    public String getBotRecMedica() {
        return botRecMedica;
    }

    public void setBotRecMedica(String botRecMedica) {
        this.botRecMedica = botRecMedica;
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

    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public Citapersona getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(Citapersona codigoCita) {
        this.codigoCita = codigoCita;
    }

    public Datospersona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Datospersona codigoPersona) {
        this.codigoPersona = codigoPersona;
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
        hash += (codigoRecMedica != null ? codigoRecMedica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecomendacionesMedicasOtras)) {
            return false;
        }
        RecomendacionesMedicasOtras other = (RecomendacionesMedicasOtras) object;
        if ((this.codigoRecMedica == null && other.codigoRecMedica != null) || (this.codigoRecMedica != null && !this.codigoRecMedica.equals(other.codigoRecMedica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.RecomendacionesMedicasOtras[ codigoRecMedica=" + codigoRecMedica + " ]";
    }

}
