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
@Table(name = "examenfisico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examenfisico.findAll", query = "SELECT e FROM Examenfisico e")
    , @NamedQuery(name = "Examenfisico.findByConsecutivo", query = "SELECT e FROM Examenfisico e WHERE e.consecutivo = :consecutivo")
    , @NamedQuery(name = "Examenfisico.findByFechaRegistro", query = "SELECT e FROM Examenfisico e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Examenfisico.findByFechaModificacion", query = "SELECT e FROM Examenfisico e WHERE e.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Examenfisico.findByPesoPersona", query = "SELECT e FROM Examenfisico e WHERE e.pesoPersona = :pesoPersona")
    , @NamedQuery(name = "Examenfisico.findByTallaPersona", query = "SELECT e FROM Examenfisico e WHERE e.tallaPersona = :tallaPersona")
    , @NamedQuery(name = "Examenfisico.findByFrecuenciaCardiacaPersona", query = "SELECT e FROM Examenfisico e WHERE e.frecuenciaCardiacaPersona = :frecuenciaCardiacaPersona")
    , @NamedQuery(name = "Examenfisico.findByFrecuenciaRespiratoriaPersona", query = "SELECT e FROM Examenfisico e WHERE e.frecuenciaRespiratoriaPersona = :frecuenciaRespiratoriaPersona")
    , @NamedQuery(name = "Examenfisico.findByPresionArterialSistomaticaPersona", query = "SELECT e FROM Examenfisico e WHERE e.presionArterialSistomaticaPersona = :presionArterialSistomaticaPersona")
    , @NamedQuery(name = "Examenfisico.findByPresionArterialDiastolicaPersona", query = "SELECT e FROM Examenfisico e WHERE e.presionArterialDiastolicaPersona = :presionArterialDiastolicaPersona")
    , @NamedQuery(name = "Examenfisico.findByPerimetroCranealPersona", query = "SELECT e FROM Examenfisico e WHERE e.perimetroCranealPersona = :perimetroCranealPersona")
    , @NamedQuery(name = "Examenfisico.findByPerimetroToraxPersona", query = "SELECT e FROM Examenfisico e WHERE e.perimetroToraxPersona = :perimetroToraxPersona")
    , @NamedQuery(name = "Examenfisico.findByTemperaturaPersona", query = "SELECT e FROM Examenfisico e WHERE e.temperaturaPersona = :temperaturaPersona")
    , @NamedQuery(name = "Examenfisico.findByReticulocitos", query = "SELECT e FROM Examenfisico e WHERE e.reticulocitos = :reticulocitos")
    , @NamedQuery(name = "Examenfisico.findByRecuentoPlaquetas", query = "SELECT e FROM Examenfisico e WHERE e.recuentoPlaquetas = :recuentoPlaquetas")
    , @NamedQuery(name = "Examenfisico.findByNumeroDocumentoIdentidad", query = "SELECT e FROM Examenfisico e WHERE e.numeroDocumentoIdentidad = :numeroDocumentoIdentidad")})
public class Examenfisico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivo")
    private Long consecutivo;
    @Lob
    @Column(name = "cabeza")
    private String cabeza;
    @Lob
    @Column(name = "ojos")
    private String ojos;
    @Lob
    @Column(name = "oidos")
    private String oidos;
    @Lob
    @Column(name = "agudezaAuditiva")
    private String agudezaAuditiva;
    @Lob
    @Column(name = "nariz")
    private String nariz;
    @Lob
    @Column(name = "faringe")
    private String faringe;
    @Lob
    @Column(name = "dental")
    private String dental;
    @Lob
    @Column(name = "cuello")
    private String cuello;
    @Lob
    @Column(name = "aparatoCirculatorio")
    private String aparatoCirculatorio;
    @Lob
    @Column(name = "aparatoRespitatorio")
    private String aparatoRespitatorio;
    @Lob
    @Column(name = "abdomen")
    private String abdomen;
    @Lob
    @Column(name = "genitales")
    private String genitales;
    @Lob
    @Column(name = "tanner")
    private String tanner;
    @Lob
    @Column(name = "neurologico")
    private String neurologico;
    @Lob
    @Column(name = "desarrolloSicomotor")
    private String desarrolloSicomotor;
    @Lob
    @Column(name = "osteomuscular")
    private String osteomuscular;
    @Lob
    @Column(name = "pielFanelas")
    private String pielFanelas;
    @Lob
    @Column(name = "sistemasGanglionar")
    private String sistemasGanglionar;
    @Lob
    @Column(name = "bcg")
    private String bcg;
    @Lob
    @Column(name = "otros")
    private String otros;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 6)
    @Column(name = "pesoPersona")
    private String pesoPersona;
    @Lob
    @Column(name = "descripcionPeso")
    private String descripcionPeso;
    @Size(max = 6)
    @Column(name = "tallaPersona")
    private String tallaPersona;
    @Lob
    @Column(name = "descripcionTalla")
    private String descripcionTalla;
    @Size(max = 6)
    @Column(name = "frecuenciaCardiacaPersona")
    private String frecuenciaCardiacaPersona;
    @Lob
    @Column(name = "descripcionFrecuenciaCardiaca")
    private String descripcionFrecuenciaCardiaca;
    @Size(max = 6)
    @Column(name = "frecuenciaRespiratoriaPersona")
    private String frecuenciaRespiratoriaPersona;
    @Lob
    @Column(name = "detalleFrecuenciaRespiratoria")
    private String detalleFrecuenciaRespiratoria;
    @Size(max = 6)
    @Column(name = "presionArterialSistomaticaPersona")
    private String presionArterialSistomaticaPersona;
    @Lob
    @Column(name = "descripcionPresionArterialSistomatica")
    private String descripcionPresionArterialSistomatica;
    @Size(max = 6)
    @Column(name = "presionArterialDiastolicaPersona")
    private String presionArterialDiastolicaPersona;
    @Lob
    @Column(name = "descripcionPresionArterialDiastolica")
    private String descripcionPresionArterialDiastolica;
    @Size(max = 6)
    @Column(name = "perimetroCranealPersona")
    private String perimetroCranealPersona;
    @Lob
    @Column(name = "descripcionPerimetroCraneal")
    private String descripcionPerimetroCraneal;
    @Size(max = 6)
    @Column(name = "perimetroToraxPersona")
    private String perimetroToraxPersona;
    @Lob
    @Column(name = "descripcionPerimetroTorax")
    private String descripcionPerimetroTorax;
    @Size(max = 6)
    @Column(name = "temperaturaPersona")
    private String temperaturaPersona;
    @Lob
    @Column(name = "descripcionTemperatura")
    private String descripcionTemperatura;
    @Size(max = 6)
    @Column(name = "reticulocitos")
    private String reticulocitos;
    @Lob
    @Column(name = "descripcionReticulocitos")
    private String descripcionReticulocitos;
    @Size(max = 6)
    @Column(name = "recuentoPlaquetas")
    private String recuentoPlaquetas;
    @Lob
    @Column(name = "descripcionRecuentosPlaquetas")
    private String descripcionRecuentosPlaquetas;
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

    public Examenfisico() {
    }

    public Examenfisico(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getCabeza() {
        return cabeza;
    }

    public void setCabeza(String cabeza) {
        this.cabeza = cabeza;
    }

    public String getOjos() {
        return ojos;
    }

    public void setOjos(String ojos) {
        this.ojos = ojos;
    }

    public String getOidos() {
        return oidos;
    }

    public void setOidos(String oidos) {
        this.oidos = oidos;
    }

    public String getAgudezaAuditiva() {
        return agudezaAuditiva;
    }

    public void setAgudezaAuditiva(String agudezaAuditiva) {
        this.agudezaAuditiva = agudezaAuditiva;
    }

    public String getNariz() {
        return nariz;
    }

    public void setNariz(String nariz) {
        this.nariz = nariz;
    }

    public String getFaringe() {
        return faringe;
    }

    public void setFaringe(String faringe) {
        this.faringe = faringe;
    }

    public String getDental() {
        return dental;
    }

    public void setDental(String dental) {
        this.dental = dental;
    }

    public String getCuello() {
        return cuello;
    }

    public void setCuello(String cuello) {
        this.cuello = cuello;
    }

    public String getAparatoCirculatorio() {
        return aparatoCirculatorio;
    }

    public void setAparatoCirculatorio(String aparatoCirculatorio) {
        this.aparatoCirculatorio = aparatoCirculatorio;
    }

    public String getAparatoRespitatorio() {
        return aparatoRespitatorio;
    }

    public void setAparatoRespitatorio(String aparatoRespitatorio) {
        this.aparatoRespitatorio = aparatoRespitatorio;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getGenitales() {
        return genitales;
    }

    public void setGenitales(String genitales) {
        this.genitales = genitales;
    }

    public String getTanner() {
        return tanner;
    }

    public void setTanner(String tanner) {
        this.tanner = tanner;
    }

    public String getNeurologico() {
        return neurologico;
    }

    public void setNeurologico(String neurologico) {
        this.neurologico = neurologico;
    }

    public String getDesarrolloSicomotor() {
        return desarrolloSicomotor;
    }

    public void setDesarrolloSicomotor(String desarrolloSicomotor) {
        this.desarrolloSicomotor = desarrolloSicomotor;
    }

    public String getOsteomuscular() {
        return osteomuscular;
    }

    public void setOsteomuscular(String osteomuscular) {
        this.osteomuscular = osteomuscular;
    }

    public String getPielFanelas() {
        return pielFanelas;
    }

    public void setPielFanelas(String pielFanelas) {
        this.pielFanelas = pielFanelas;
    }

    public String getSistemasGanglionar() {
        return sistemasGanglionar;
    }

    public void setSistemasGanglionar(String sistemasGanglionar) {
        this.sistemasGanglionar = sistemasGanglionar;
    }

    public String getBcg() {
        return bcg;
    }

    public void setBcg(String bcg) {
        this.bcg = bcg;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
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

    public String getPesoPersona() {
        return pesoPersona;
    }

    public void setPesoPersona(String pesoPersona) {
        this.pesoPersona = pesoPersona;
    }

    public String getDescripcionPeso() {
        return descripcionPeso;
    }

    public void setDescripcionPeso(String descripcionPeso) {
        this.descripcionPeso = descripcionPeso;
    }

    public String getTallaPersona() {
        return tallaPersona;
    }

    public void setTallaPersona(String tallaPersona) {
        this.tallaPersona = tallaPersona;
    }

    public String getDescripcionTalla() {
        return descripcionTalla;
    }

    public void setDescripcionTalla(String descripcionTalla) {
        this.descripcionTalla = descripcionTalla;
    }

    public String getFrecuenciaCardiacaPersona() {
        return frecuenciaCardiacaPersona;
    }

    public void setFrecuenciaCardiacaPersona(String frecuenciaCardiacaPersona) {
        this.frecuenciaCardiacaPersona = frecuenciaCardiacaPersona;
    }

    public String getDescripcionFrecuenciaCardiaca() {
        return descripcionFrecuenciaCardiaca;
    }

    public void setDescripcionFrecuenciaCardiaca(String descripcionFrecuenciaCardiaca) {
        this.descripcionFrecuenciaCardiaca = descripcionFrecuenciaCardiaca;
    }

    public String getFrecuenciaRespiratoriaPersona() {
        return frecuenciaRespiratoriaPersona;
    }

    public void setFrecuenciaRespiratoriaPersona(String frecuenciaRespiratoriaPersona) {
        this.frecuenciaRespiratoriaPersona = frecuenciaRespiratoriaPersona;
    }

    public String getDetalleFrecuenciaRespiratoria() {
        return detalleFrecuenciaRespiratoria;
    }

    public void setDetalleFrecuenciaRespiratoria(String detalleFrecuenciaRespiratoria) {
        this.detalleFrecuenciaRespiratoria = detalleFrecuenciaRespiratoria;
    }

    public String getPresionArterialSistomaticaPersona() {
        return presionArterialSistomaticaPersona;
    }

    public void setPresionArterialSistomaticaPersona(String presionArterialSistomaticaPersona) {
        this.presionArterialSistomaticaPersona = presionArterialSistomaticaPersona;
    }

    public String getDescripcionPresionArterialSistomatica() {
        return descripcionPresionArterialSistomatica;
    }

    public void setDescripcionPresionArterialSistomatica(String descripcionPresionArterialSistomatica) {
        this.descripcionPresionArterialSistomatica = descripcionPresionArterialSistomatica;
    }

    public String getPresionArterialDiastolicaPersona() {
        return presionArterialDiastolicaPersona;
    }

    public void setPresionArterialDiastolicaPersona(String presionArterialDiastolicaPersona) {
        this.presionArterialDiastolicaPersona = presionArterialDiastolicaPersona;
    }

    public String getDescripcionPresionArterialDiastolica() {
        return descripcionPresionArterialDiastolica;
    }

    public void setDescripcionPresionArterialDiastolica(String descripcionPresionArterialDiastolica) {
        this.descripcionPresionArterialDiastolica = descripcionPresionArterialDiastolica;
    }

    public String getPerimetroCranealPersona() {
        return perimetroCranealPersona;
    }

    public void setPerimetroCranealPersona(String perimetroCranealPersona) {
        this.perimetroCranealPersona = perimetroCranealPersona;
    }

    public String getDescripcionPerimetroCraneal() {
        return descripcionPerimetroCraneal;
    }

    public void setDescripcionPerimetroCraneal(String descripcionPerimetroCraneal) {
        this.descripcionPerimetroCraneal = descripcionPerimetroCraneal;
    }

    public String getPerimetroToraxPersona() {
        return perimetroToraxPersona;
    }

    public void setPerimetroToraxPersona(String perimetroToraxPersona) {
        this.perimetroToraxPersona = perimetroToraxPersona;
    }

    public String getDescripcionPerimetroTorax() {
        return descripcionPerimetroTorax;
    }

    public void setDescripcionPerimetroTorax(String descripcionPerimetroTorax) {
        this.descripcionPerimetroTorax = descripcionPerimetroTorax;
    }

    public String getTemperaturaPersona() {
        return temperaturaPersona;
    }

    public void setTemperaturaPersona(String temperaturaPersona) {
        this.temperaturaPersona = temperaturaPersona;
    }

    public String getDescripcionTemperatura() {
        return descripcionTemperatura;
    }

    public void setDescripcionTemperatura(String descripcionTemperatura) {
        this.descripcionTemperatura = descripcionTemperatura;
    }

    public String getReticulocitos() {
        return reticulocitos;
    }

    public void setReticulocitos(String reticulocitos) {
        this.reticulocitos = reticulocitos;
    }

    public String getDescripcionReticulocitos() {
        return descripcionReticulocitos;
    }

    public void setDescripcionReticulocitos(String descripcionReticulocitos) {
        this.descripcionReticulocitos = descripcionReticulocitos;
    }

    public String getRecuentoPlaquetas() {
        return recuentoPlaquetas;
    }

    public void setRecuentoPlaquetas(String recuentoPlaquetas) {
        this.recuentoPlaquetas = recuentoPlaquetas;
    }

    public String getDescripcionRecuentosPlaquetas() {
        return descripcionRecuentosPlaquetas;
    }

    public void setDescripcionRecuentosPlaquetas(String descripcionRecuentosPlaquetas) {
        this.descripcionRecuentosPlaquetas = descripcionRecuentosPlaquetas;
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
        hash += (consecutivo != null ? consecutivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examenfisico)) {
            return false;
        }
        Examenfisico other = (Examenfisico) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Examenfisico[ consecutivo=" + consecutivo + " ]";
    }

}
