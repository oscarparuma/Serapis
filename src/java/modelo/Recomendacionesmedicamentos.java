/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "recomendacionesmedicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recomendacionesmedicamentos.findAll", query = "SELECT r FROM Recomendacionesmedicamentos r")
    , @NamedQuery(name = "Recomendacionesmedicamentos.findByConsecutivo", query = "SELECT r FROM Recomendacionesmedicamentos r WHERE r.consecutivo = :consecutivo")
    , @NamedQuery(name = "Recomendacionesmedicamentos.findByFechaRegistro", query = "SELECT r FROM Recomendacionesmedicamentos r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Recomendacionesmedicamentos.findByFechaModificacion", query = "SELECT r FROM Recomendacionesmedicamentos r WHERE r.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Recomendacionesmedicamentos.findByBotRecoMedicas", query = "SELECT r FROM Recomendacionesmedicamentos r WHERE r.botRecoMedicas = :botRecoMedicas")
    , @NamedQuery(name = "Recomendacionesmedicamentos.findByActivo", query = "SELECT r FROM Recomendacionesmedicamentos r WHERE r.activo = :activo")
    , @NamedQuery(name = "Recomendacionesmedicamentos.findByBotFinalizar", query = "SELECT r FROM Recomendacionesmedicamentos r WHERE r.botFinalizar = :botFinalizar")
    , @NamedQuery(name = "Recomendacionesmedicamentos.findByBotRecMediConsul", query = "SELECT r FROM Recomendacionesmedicamentos r WHERE r.botRecMediConsul = :botRecMediConsul")
    , @NamedQuery(name = "Recomendacionesmedicamentos.findByBotRecMediProced", query = "SELECT r FROM Recomendacionesmedicamentos r WHERE r.botRecMediProced = :botRecMediProced")
    , @NamedQuery(name = "Recomendacionesmedicamentos.findByBotRecSalOcup", query = "SELECT r FROM Recomendacionesmedicamentos r WHERE r.botRecSalOcup = :botRecSalOcup")
    , @NamedQuery(name = "Recomendacionesmedicamentos.findByNumeroDocumentoIdentidad", query = "SELECT r FROM Recomendacionesmedicamentos r WHERE r.numeroDocumentoIdentidad = :numeroDocumentoIdentidad")})
public class Recomendacionesmedicamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivo")
    private Long consecutivo;
    @Lob
    @Column(name = "recomendacionesMedicas")
    private String recomendacionesMedicas;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 1)
    @Column(name = "botRecoMedicas")
    private String botRecoMedicas;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Size(max = 1)
    @Column(name = "botFinalizar")
    private String botFinalizar;
    @Size(max = 1)
    @Column(name = "botRecMediConsul")
    private String botRecMediConsul;
    @Size(max = 1)
    @Column(name = "botRecMediProced")
    private String botRecMediProced;
    @Size(max = 1)
    @Column(name = "botRecSalOcup")
    private String botRecSalOcup;
    @Size(max = 50)
    @Column(name = "numeroDocumentoIdentidad")
    private String numeroDocumentoIdentidad;
    @JoinColumn(name = "codigoCita", referencedColumnName = "codigoCitaPersona")
    @ManyToOne
    private Citapersona codigoCita;
    @JoinColumn(name = "codigoClasificacionIndicacionesMedicas", referencedColumnName = "codigoClasificacionIndMedicas")
    @ManyToOne
    private Clasificacionindicacionmedica codigoClasificacionIndicacionesMedicas;
    @JoinColumn(name = "codigoPersonaCita", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersonaCita;
    @JoinColumn(name = "codigoIndicacionesMedicas", referencedColumnName = "codigoIndicacionMedica")
    @ManyToOne
    private Indicacionmedica codigoIndicacionesMedicas;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public Recomendacionesmedicamentos() {
    }

    public Recomendacionesmedicamentos(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getRecomendacionesMedicas() {
        return recomendacionesMedicas;
    }

    public void setRecomendacionesMedicas(String recomendacionesMedicas) {
        this.recomendacionesMedicas = recomendacionesMedicas;
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

    public String getBotRecoMedicas() {
        return botRecoMedicas;
    }

    public void setBotRecoMedicas(String botRecoMedicas) {
        this.botRecoMedicas = botRecoMedicas;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getBotFinalizar() {
        return botFinalizar;
    }

    public void setBotFinalizar(String botFinalizar) {
        this.botFinalizar = botFinalizar;
    }

    public String getBotRecMediConsul() {
        return botRecMediConsul;
    }

    public void setBotRecMediConsul(String botRecMediConsul) {
        this.botRecMediConsul = botRecMediConsul;
    }

    public String getBotRecMediProced() {
        return botRecMediProced;
    }

    public void setBotRecMediProced(String botRecMediProced) {
        this.botRecMediProced = botRecMediProced;
    }

    public String getBotRecSalOcup() {
        return botRecSalOcup;
    }

    public void setBotRecSalOcup(String botRecSalOcup) {
        this.botRecSalOcup = botRecSalOcup;
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

    public Clasificacionindicacionmedica getCodigoClasificacionIndicacionesMedicas() {
        return codigoClasificacionIndicacionesMedicas;
    }

    public void setCodigoClasificacionIndicacionesMedicas(Clasificacionindicacionmedica codigoClasificacionIndicacionesMedicas) {
        this.codigoClasificacionIndicacionesMedicas = codigoClasificacionIndicacionesMedicas;
    }

    public Datospersona getCodigoPersonaCita() {
        return codigoPersonaCita;
    }

    public void setCodigoPersonaCita(Datospersona codigoPersonaCita) {
        this.codigoPersonaCita = codigoPersonaCita;
    }

    public Indicacionmedica getCodigoIndicacionesMedicas() {
        return codigoIndicacionesMedicas;
    }

    public void setCodigoIndicacionesMedicas(Indicacionmedica codigoIndicacionesMedicas) {
        this.codigoIndicacionesMedicas = codigoIndicacionesMedicas;
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
        hash += (consecutivo != null ? consecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recomendacionesmedicamentos)) {
            return false;
        }
        Recomendacionesmedicamentos other = (Recomendacionesmedicamentos) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Recomendacionesmedicamentos[ consecutivo=" + consecutivo + " ]";
    }

}
