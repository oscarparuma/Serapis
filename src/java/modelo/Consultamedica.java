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
 * @author ANPILU
 */
@Entity
@Table(name = "consultamedica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultamedica.findAll", query = "SELECT c FROM Consultamedica c")
    , @NamedQuery(name = "Consultamedica.findByConsecutivo", query = "SELECT c FROM Consultamedica c WHERE c.consecutivo = :consecutivo")
    , @NamedQuery(name = "Consultamedica.findByFechaRegistro", query = "SELECT c FROM Consultamedica c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Consultamedica.findByFechaModificacion", query = "SELECT c FROM Consultamedica c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Consultamedica.findByBotConsulta", query = "SELECT c FROM Consultamedica c WHERE c.botConsulta = :botConsulta")
    , @NamedQuery(name = "Consultamedica.findByActivo", query = "SELECT c FROM Consultamedica c WHERE c.activo = :activo")
    , @NamedQuery(name = "Consultamedica.findByCodigoFinalidadRips", query = "SELECT c FROM Consultamedica c WHERE c.codigoFinalidadRips = :codigoFinalidadRips")
    , @NamedQuery(name = "Consultamedica.findByCodigoCausaExternaRips", query = "SELECT c FROM Consultamedica c WHERE c.codigoCausaExternaRips = :codigoCausaExternaRips")
    , @NamedQuery(name = "Consultamedica.findByNumeroDocumentoIdentidad", query = "SELECT c FROM Consultamedica c WHERE c.numeroDocumentoIdentidad = :numeroDocumentoIdentidad")})
public class Consultamedica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivo")
    private Long consecutivo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Column(name = "evolucionPaciente")
    private String evolucionPaciente;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 1)
    @Column(name = "botConsulta")
    private String botConsulta;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Size(max = 50)
    @Column(name = "numeroDocumentoIdentidad")
    private String numeroDocumentoIdentidad;
    @JoinColumn(name = "codigoCausa", referencedColumnName = "codigo")
    @ManyToOne
    private Causaexterna codigoCausa;
    @JoinColumn(name = "codigoCita", referencedColumnName = "codigoCitaPersona")
    @ManyToOne
    private Citapersona codigoCita;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "codigoFinalidad", referencedColumnName = "codigo")
    @ManyToOne
    private Finalidadconsulta codigoFinalidad;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @Size(max = 3)
    @Column(name = "codigoFinalidadRips")
    private String codigoFinalidadRips;
    @Size(max = 3)
    @Column(name = "codigoCausaExternaRips")
    private String codigoCausaExternaRips;
    

    public Consultamedica() {
    }

    public Consultamedica(Long consecutivo) {
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

    public String getEvolucionPaciente() {
        return evolucionPaciente;
    }

    public void setEvolucionPaciente(String evolucionPaciente) {
        this.evolucionPaciente = evolucionPaciente;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getBotConsulta() {
        return botConsulta;
    }

    public void setBotConsulta(String botConsulta) {
        this.botConsulta = botConsulta;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public Causaexterna getCodigoCausa() {
        return codigoCausa;
    }

    public void setCodigoCausa(Causaexterna codigoCausa) {
        this.codigoCausa = codigoCausa;
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

    public Finalidadconsulta getCodigoFinalidad() {
        return codigoFinalidad;
    }

    public void setCodigoFinalidad(Finalidadconsulta codigoFinalidad) {
        this.codigoFinalidad = codigoFinalidad;
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

    public String getCodigoFinalidadRips() {
        return codigoFinalidadRips;
    }

    public void setCodigoFinalidadRips(String codigoFinalidadRips) {
        this.codigoFinalidadRips = codigoFinalidadRips;
    }

    public String getCodigoCausaExternaRips() {
        return codigoCausaExternaRips;
    }

    public void setCodigoCausaExternaRips(String codigoCausaExternaRips) {
        this.codigoCausaExternaRips = codigoCausaExternaRips;
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
        if (!(object instanceof Consultamedica)) {
            return false;
        }
        Consultamedica other = (Consultamedica) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Consultamedica[ consecutivo=" + consecutivo + " ]";
    }

}
