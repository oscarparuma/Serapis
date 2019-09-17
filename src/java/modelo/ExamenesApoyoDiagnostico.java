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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author RUANLU
 */
@Entity
@Table(name = "ExamenesApoyoDiagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamenesApoyoDiagnostico.findAll", query = "SELECT e FROM ExamenesApoyoDiagnostico e"),
    @NamedQuery(name = "ExamenesApoyoDiagnostico.findByConsecutivo", query = "SELECT e FROM ExamenesApoyoDiagnostico e WHERE e.consecutivo = :consecutivo"),
    @NamedQuery(name = "ExamenesApoyoDiagnostico.findByFechaRegistro", query = "SELECT e FROM ExamenesApoyoDiagnostico e WHERE e.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "ExamenesApoyoDiagnostico.findByCantidad", query = "SELECT e FROM ExamenesApoyoDiagnostico e WHERE e.cantidad = :cantidad"),
    @NamedQuery(name = "ExamenesApoyoDiagnostico.findByFechaModificacion", query = "SELECT e FROM ExamenesApoyoDiagnostico e WHERE e.fechaModificacion = :fechaModificacion"),
    @NamedQuery(name = "ExamenesApoyoDiagnostico.findByFechaInterpretacion", query = "SELECT e FROM ExamenesApoyoDiagnostico e WHERE e.fechaInterpretacion = :fechaInterpretacion"),
    @NamedQuery(name = "ExamenesApoyoDiagnostico.findByBotEvolucionMedica", query = "SELECT e FROM ExamenesApoyoDiagnostico e WHERE e.botEvolucionMedica = :botEvolucionMedica"),
    @NamedQuery(name = "ExamenesApoyoDiagnostico.findByResultados", query = "SELECT e FROM ExamenesApoyoDiagnostico e WHERE e.resultados = :resultados"),
    @NamedQuery(name = "ExamenesApoyoDiagnostico.findByEvilucionado", query = "SELECT e FROM ExamenesApoyoDiagnostico e WHERE e.evilucionado = :evilucionado"),
    @NamedQuery(name = "ExamenesApoyoDiagnostico.findByNumeroDocumentoIdentidad", query = "SELECT e FROM ExamenesApoyoDiagnostico e WHERE e.numeroDocumentoIdentidad = :numeroDocumentoIdentidad")})
public class ExamenesApoyoDiagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivo")
    private Long consecutivo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "cantidad")
    private Short cantidad;
    @Lob
    @Column(name = "justificacionMedica")
    private String justificacionMedica;
    @Lob
    @Column(name = "interpretacionMedica")
    private String interpretacionMedica;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "fechaInterpretacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInterpretacion;
    @Size(max = 1)
    @Column(name = "botEvolucionMedica")
    private String botEvolucionMedica;
    @Size(max = 1)
    @Column(name = "resultados")
    private String resultados;
    @Size(max = 1)
    @Column(name = "evilucionado")
    private String evilucionado;
    @Size(max = 50)
    @Column(name = "numeroDocumentoIdentidad")
    private String numeroDocumentoIdentidad;
    @JoinColumn(name = "codigoCita", referencedColumnName = "codigoCitaPersona")
    @ManyToOne
    private Citapersona codigoCita;
    @JoinColumn(name = "codigoCitaEvolucion", referencedColumnName = "codigoCitaPersona")
    @ManyToOne
    private Citapersona codigoCitaEvolucion;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "codigoCups", referencedColumnName = "codigoCups")
    @ManyToOne
    private Manualtarifas codigoCups;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "medicoEvolucion", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario medicoEvolucion;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoInterpretacion", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoInterpretacion;

    public ExamenesApoyoDiagnostico() {
    }

    public ExamenesApoyoDiagnostico(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Short getCantidad() {
        return cantidad;
    }

    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }

    public String getJustificacionMedica() {
        return justificacionMedica;
    }

    public void setJustificacionMedica(String justificacionMedica) {
        this.justificacionMedica = justificacionMedica;
    }

    public String getInterpretacionMedica() {
        return interpretacionMedica;
    }

    public void setInterpretacionMedica(String interpretacionMedica) {
        this.interpretacionMedica = interpretacionMedica;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaInterpretacion() {
        return fechaInterpretacion;
    }

    public void setFechaInterpretacion(Date fechaInterpretacion) {
        this.fechaInterpretacion = fechaInterpretacion;
    }

    public String getBotEvolucionMedica() {
        return botEvolucionMedica;
    }

    public void setBotEvolucionMedica(String botEvolucionMedica) {
        this.botEvolucionMedica = botEvolucionMedica;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public String getEvilucionado() {
        return evilucionado;
    }

    public void setEvilucionado(String evilucionado) {
        this.evilucionado = evilucionado;
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

    public Citapersona getCodigoCitaEvolucion() {
        return codigoCitaEvolucion;
    }

    public void setCodigoCitaEvolucion(Citapersona codigoCitaEvolucion) {
        this.codigoCitaEvolucion = codigoCitaEvolucion;
    }

    public Datospersona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Datospersona codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public Manualtarifas getCodigoCups() {
        return codigoCups;
    }

    public void setCodigoCups(Manualtarifas codigoCups) {
        this.codigoCups = codigoCups;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public Usuario getMedicoEvolucion() {
        return medicoEvolucion;
    }

    public void setMedicoEvolucion(Usuario medicoEvolucion) {
        this.medicoEvolucion = medicoEvolucion;
    }

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getCodigoInterpretacion() {
        return codigoInterpretacion;
    }

    public void setCodigoInterpretacion(Usuario codigoInterpretacion) {
        this.codigoInterpretacion = codigoInterpretacion;
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
        if (!(object instanceof ExamenesApoyoDiagnostico)) {
            return false;
        }
        ExamenesApoyoDiagnostico other = (ExamenesApoyoDiagnostico) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoCups + " ";
    }

}
