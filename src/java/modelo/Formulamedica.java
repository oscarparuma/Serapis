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
@Table(name = "formulamedica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Formulamedica.findAll", query = "SELECT f FROM Formulamedica f")
    , @NamedQuery(name = "Formulamedica.findByConsecutivo", query = "SELECT f FROM Formulamedica f WHERE f.consecutivo = :consecutivo")
    , @NamedQuery(name = "Formulamedica.findByFechaRegistro", query = "SELECT f FROM Formulamedica f WHERE f.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Formulamedica.findByCantidad", query = "SELECT f FROM Formulamedica f WHERE f.cantidad = :cantidad")
    , @NamedQuery(name = "Formulamedica.findByFechaModificacion", query = "SELECT f FROM Formulamedica f WHERE f.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Formulamedica.findByNumeroDocumentoIdentidad", query = "SELECT f FROM Formulamedica f WHERE f.numeroDocumentoIdentidad = :numeroDocumentoIdentidad")})
public class Formulamedica implements Serializable {

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
    @Column(name = "recomendacionesMedicas")
    private String recomendacionesMedicas;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Lob
    @Column(name = "dosificacionMedicamento")
    private String dosificacionMedicamento;
    @Size(max = 50)
    @Column(name = "numeroDocumentoIdentidad")
    private String numeroDocumentoIdentidad;
    @JoinColumn(name = "codigoCita", referencedColumnName = "codigoCitaPersona")
    @ManyToOne
    private Citapersona codigoCita;
    @JoinColumn(name = "codigoClasificacionMedicamento", referencedColumnName = "codigoClasificacionmedicamento")
    @ManyToOne
    private Clasificacionmedicamento codigoClasificacionMedicamento;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "codigoMedicamento", referencedColumnName = "consecutivo")
    @ManyToOne
    private Plm codigoMedicamento;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public Formulamedica() {
    }

    public Formulamedica(Long consecutivo) {
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

    public String getRecomendacionesMedicas() {
        return recomendacionesMedicas;
    }

    public void setRecomendacionesMedicas(String recomendacionesMedicas) {
        this.recomendacionesMedicas = recomendacionesMedicas;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getDosificacionMedicamento() {
        return dosificacionMedicamento;
    }

    public void setDosificacionMedicamento(String dosificacionMedicamento) {
        this.dosificacionMedicamento = dosificacionMedicamento;
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

    public Clasificacionmedicamento getCodigoClasificacionMedicamento() {
        return codigoClasificacionMedicamento;
    }

    public void setCodigoClasificacionMedicamento(Clasificacionmedicamento codigoClasificacionMedicamento) {
        this.codigoClasificacionMedicamento = codigoClasificacionMedicamento;
    }

    public Datospersona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Datospersona codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public Plm getCodigoMedicamento() {
        return codigoMedicamento;
    }

    public void setCodigoMedicamento(Plm codigoMedicamento) {
        this.codigoMedicamento = codigoMedicamento;
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
        if (!(object instanceof Formulamedica)) {
            return false;
        }
        Formulamedica other = (Formulamedica) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Formulamedica[ consecutivo=" + consecutivo + " ]";
    }

}
