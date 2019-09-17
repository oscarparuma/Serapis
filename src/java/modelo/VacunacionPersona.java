/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "VacunacionPersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VacunacionPersona.findAll", query = "SELECT v FROM VacunacionPersona v")
    , @NamedQuery(name = "VacunacionPersona.findByConsecutivoVacunacion", query = "SELECT v FROM VacunacionPersona v WHERE v.consecutivoVacunacion = :consecutivoVacunacion")
    , @NamedQuery(name = "VacunacionPersona.findBySiNo", query = "SELECT v FROM VacunacionPersona v WHERE v.siNo = :siNo")
    , @NamedQuery(name = "VacunacionPersona.findByRefuerzos", query = "SELECT v FROM VacunacionPersona v WHERE v.refuerzos = :refuerzos")
    , @NamedQuery(name = "VacunacionPersona.findByActivo", query = "SELECT v FROM VacunacionPersona v WHERE v.activo = :activo")
    , @NamedQuery(name = "VacunacionPersona.findByFechaVacunacion", query = "SELECT v FROM VacunacionPersona v WHERE v.fechaVacunacion = :fechaVacunacion")
    , @NamedQuery(name = "VacunacionPersona.findByFechaRefuerzoDosis", query = "SELECT v FROM VacunacionPersona v WHERE v.fechaRefuerzoDosis = :fechaRefuerzoDosis")
    , @NamedQuery(name = "VacunacionPersona.findByFechaRegistro", query = "SELECT v FROM VacunacionPersona v WHERE v.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "VacunacionPersona.findByFechaModificacion", query = "SELECT v FROM VacunacionPersona v WHERE v.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "VacunacionPersona.findByFabricanteLote", query = "SELECT v FROM VacunacionPersona v WHERE v.fabricanteLote = :fabricanteLote")
    , @NamedQuery(name = "VacunacionPersona.findByEdadPersona", query = "SELECT v FROM VacunacionPersona v WHERE v.edadPersona = :edadPersona")
    , @NamedQuery(name = "VacunacionPersona.findByPersonaEdad", query = "SELECT v FROM VacunacionPersona v WHERE v.personaEdad = :personaEdad")})
public class VacunacionPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "consecutivoVacunacion")
    private Long consecutivoVacunacion;
    @Size(max = 1)
    @Column(name = "siNo")
    private String siNo;
    @Column(name = "refuerzos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date refuerzos;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaVacunacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaVacunacion;
    @Column(name = "fechaRefuerzoDosis")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRefuerzoDosis;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @Size(max = 150)
    @Column(name = "fabricanteLote")
    private String fabricanteLote;
    @Column(name = "edadPersona")
    @Temporal(TemporalType.TIMESTAMP)
    private Date edadPersona;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "personaEdad")
    private BigDecimal personaEdad;
    @JoinColumn(name = "codigoAplicarEn", referencedColumnName = "codigoAplicar")
    @ManyToOne
    private AplicarVacunaEn codigoAplicarEn;
    @JoinColumn(name = "codigoCita", referencedColumnName = "codigoCitaPersona")
    @ManyToOne
    private Citapersona codigoCita;
    @JoinColumn(name = "codigoDosisClasificacion", referencedColumnName = "codigoDosisVacunas")
    @ManyToOne
    private ClasificacionDosisVacunas codigoDosisClasificacion;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "codigoDosisi", referencedColumnName = "codigoDosis")
    @ManyToOne
    private NumeroDosis codigoDosisi;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "codigoVacuna", referencedColumnName = "codigoVacuna")
    @ManyToOne
    private Vacuna codigoVacuna;

    public VacunacionPersona() {
    }

    public VacunacionPersona(Long consecutivoVacunacion) {
        this.consecutivoVacunacion = consecutivoVacunacion;
    }

    public Long getConsecutivoVacunacion() {
        return consecutivoVacunacion;
    }

    public void setConsecutivoVacunacion(Long consecutivoVacunacion) {
        this.consecutivoVacunacion = consecutivoVacunacion;
    }

    public String getSiNo() {
        return siNo;
    }

    public void setSiNo(String siNo) {
        this.siNo = siNo;
    }

    public Date getRefuerzos() {
        return refuerzos;
    }

    public void setRefuerzos(Date refuerzos) {
        this.refuerzos = refuerzos;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFechaVacunacion() {
        return fechaVacunacion;
    }

    public void setFechaVacunacion(Date fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }

    public Date getFechaRefuerzoDosis() {
        return fechaRefuerzoDosis;
    }

    public void setFechaRefuerzoDosis(Date fechaRefuerzoDosis) {
        this.fechaRefuerzoDosis = fechaRefuerzoDosis;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFabricanteLote() {
        return fabricanteLote;
    }

    public void setFabricanteLote(String fabricanteLote) {
        this.fabricanteLote = fabricanteLote;
    }

    public Date getEdadPersona() {
        return edadPersona;
    }

    public void setEdadPersona(Date edadPersona) {
        this.edadPersona = edadPersona;
    }

    public BigDecimal getPersonaEdad() {
        return personaEdad;
    }

    public void setPersonaEdad(BigDecimal personaEdad) {
        this.personaEdad = personaEdad;
    }

    public AplicarVacunaEn getCodigoAplicarEn() {
        return codigoAplicarEn;
    }

    public void setCodigoAplicarEn(AplicarVacunaEn codigoAplicarEn) {
        this.codigoAplicarEn = codigoAplicarEn;
    }

    public Citapersona getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(Citapersona codigoCita) {
        this.codigoCita = codigoCita;
    }

    public ClasificacionDosisVacunas getCodigoDosisClasificacion() {
        return codigoDosisClasificacion;
    }

    public void setCodigoDosisClasificacion(ClasificacionDosisVacunas codigoDosisClasificacion) {
        this.codigoDosisClasificacion = codigoDosisClasificacion;
    }

    public Datospersona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Datospersona codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public NumeroDosis getCodigoDosisi() {
        return codigoDosisi;
    }

    public void setCodigoDosisi(NumeroDosis codigoDosisi) {
        this.codigoDosisi = codigoDosisi;
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

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Vacuna getCodigoVacuna() {
        return codigoVacuna;
    }

    public void setCodigoVacuna(Vacuna codigoVacuna) {
        this.codigoVacuna = codigoVacuna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consecutivoVacunacion != null ? consecutivoVacunacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VacunacionPersona)) {
            return false;
        }
        VacunacionPersona other = (VacunacionPersona) object;
        if ((this.consecutivoVacunacion == null && other.consecutivoVacunacion != null) || (this.consecutivoVacunacion != null && !this.consecutivoVacunacion.equals(other.consecutivoVacunacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.VacunacionPersona[ consecutivoVacunacion=" + consecutivoVacunacion + " ]";
    }
    
}
