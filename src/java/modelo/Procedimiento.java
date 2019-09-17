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
@Table(name = "procedimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procedimiento.findAll", query = "SELECT p FROM Procedimiento p")
    , @NamedQuery(name = "Procedimiento.findByConsecutivo", query = "SELECT p FROM Procedimiento p WHERE p.consecutivo = :consecutivo")
    , @NamedQuery(name = "Procedimiento.findByFechaRegistro", query = "SELECT p FROM Procedimiento p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Procedimiento.findByFechaModificacion", query = "SELECT p FROM Procedimiento p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Procedimiento.findByBotProcedimiento", query = "SELECT p FROM Procedimiento p WHERE p.botProcedimiento = :botProcedimiento")
    , @NamedQuery(name = "Procedimiento.findByActivo", query = "SELECT p FROM Procedimiento p WHERE p.activo = :activo")})
public class Procedimiento implements Serializable {

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
    @Column(name = "descripcionProcedimiento")
    private String descripcionProcedimiento;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 1)
    @Column(name = "botProcedimiento")
    private String botProcedimiento;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @JoinColumn(name = "codigoCausa", referencedColumnName = "codigo")
    @ManyToOne
    private Causaexterna codigoCausa;
    @JoinColumn(name = "codigoCita", referencedColumnName = "codigoCitaPersona")
   @ManyToOne(cascade = CascadeType.ALL)
    private Citapersona codigoCita;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "codigoFinalidad", referencedColumnName = "codigo")
    @ManyToOne
    private Finalidadconsulta codigoFinalidad;
    @JoinColumn(name = "codigoAnestesia", referencedColumnName = "codigoAnestesia")
    @ManyToOne
    private Tipoanestesia codigoAnestesia;
    @JoinColumn(name = "codigoAyudante", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoAyudante;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public Procedimiento() {
    }

    public Procedimiento(Long consecutivo) {
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

    public String getDescripcionProcedimiento() {
        return descripcionProcedimiento;
    }

    public void setDescripcionProcedimiento(String descripcionProcedimiento) {
        this.descripcionProcedimiento = descripcionProcedimiento;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getBotProcedimiento() {
        return botProcedimiento;
    }

    public void setBotProcedimiento(String botProcedimiento) {
        this.botProcedimiento = botProcedimiento;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
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

    public Tipoanestesia getCodigoAnestesia() {
        return codigoAnestesia;
    }

    public void setCodigoAnestesia(Tipoanestesia codigoAnestesia) {
        this.codigoAnestesia = codigoAnestesia;
    }

    public Usuario getCodigoAyudante() {
        return codigoAyudante;
    }

    public void setCodigoAyudante(Usuario codigoAyudante) {
        this.codigoAyudante = codigoAyudante;
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
        if (!(object instanceof Procedimiento)) {
            return false;
        }
        Procedimiento other = (Procedimiento) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Procedimiento[ consecutivo=" + consecutivo + " ]";
    }

}
