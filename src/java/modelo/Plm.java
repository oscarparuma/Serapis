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
import javax.persistence.Lob;
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
@Table(name = "plm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plm.findAll", query = "SELECT p FROM Plm p")
    , @NamedQuery(name = "Plm.findByConsecutivo", query = "SELECT p FROM Plm p WHERE p.consecutivo = :consecutivo")
    , @NamedQuery(name = "Plm.findByCodigoClasificacion", query = "SELECT p FROM Plm p WHERE p.codigoClasificacion = :codigoClasificacion")
    , @NamedQuery(name = "Plm.findByNombreMedicamento", query = "SELECT p FROM Plm p WHERE p.nombreMedicamento = :nombreMedicamento")
    , @NamedQuery(name = "Plm.findByConcentracion", query = "SELECT p FROM Plm p WHERE p.concentracion = :concentracion")
    , @NamedQuery(name = "Plm.findByFechaRegistro", query = "SELECT p FROM Plm p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Plm.findByFechaModificacion", query = "SELECT p FROM Plm p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Plm.findByActivo", query = "SELECT p FROM Plm p WHERE p.activo = :activo")
    , @NamedQuery(name = "Plm.findByFormulaMedica", query = "SELECT p FROM Plm p WHERE p.formulaMedica = :formulaMedica")
    , @NamedQuery(name = "Plm.findByUnidadMedida", query = "SELECT p FROM Plm p WHERE p.unidadMedida = :unidadMedida")
    , @NamedQuery(name = "Plm.findByViaAdministracion", query = "SELECT p FROM Plm p WHERE p.viaAdministracion = :viaAdministracion")
    , @NamedQuery(name = "Plm.findByNivelUso", query = "SELECT p FROM Plm p WHERE p.nivelUso = :nivelUso")})
public class Plm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "consecutivo")
    private Long consecutivo;
    @Size(max = 50)
    @Column(name = "codigoClasificacion")
    private String codigoClasificacion;
    @Size(max = 300)
    @Column(name = "nombreMedicamento")
    private String nombreMedicamento;
    @Size(max = 500)
    @Column(name = "concentracion")
    private String concentracion;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Size(max = 450)
    @Column(name = "formulaMedica")
    private String formulaMedica;
    @Size(max = 10)
    @Column(name = "unidadMedida")
    private String unidadMedida;
    @Size(max = 350)
    @Column(name = "viaAdministracion")
    private String viaAdministracion;
    @Size(max = 250)
    @Column(name = "nivelUso")
    private String nivelUso;
    @Lob
    @Column(name = "observaciones")
    private String observaciones;
    @OneToMany(mappedBy = "codigoMedicamento")
    private List<Formulamedica> formulamedicaList;
    @JoinColumn(name = "codigoTipoMedicamento", referencedColumnName = "codigoClasificacionmedicamento")
    @ManyToOne
    private Clasificacionmedicamento codigoTipoMedicamento;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public Plm() {
    }

    public Plm(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Long getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Long consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getCodigoClasificacion() {
        return codigoClasificacion;
    }

    public void setCodigoClasificacion(String codigoClasificacion) {
        this.codigoClasificacion = codigoClasificacion;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(String concentracion) {
        this.concentracion = concentracion;
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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getFormulaMedica() {
        return formulaMedica;
    }

    public void setFormulaMedica(String formulaMedica) {
        this.formulaMedica = formulaMedica;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getViaAdministracion() {
        return viaAdministracion;
    }

    public void setViaAdministracion(String viaAdministracion) {
        this.viaAdministracion = viaAdministracion;
    }

    public String getNivelUso() {
        return nivelUso;
    }

    public void setNivelUso(String nivelUso) {
        this.nivelUso = nivelUso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @XmlTransient
    public List<Formulamedica> getFormulamedicaList() {
        return formulamedicaList;
    }

    public void setFormulamedicaList(List<Formulamedica> formulamedicaList) {
        this.formulamedicaList = formulamedicaList;
    }

    public Clasificacionmedicamento getCodigoTipoMedicamento() {
        return codigoTipoMedicamento;
    }

    public void setCodigoTipoMedicamento(Clasificacionmedicamento codigoTipoMedicamento) {
        this.codigoTipoMedicamento = codigoTipoMedicamento;
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
        if (!(object instanceof Plm)) {
            return false;
        }
        Plm other = (Plm) object;
        if ((this.consecutivo == null && other.consecutivo != null) || (this.consecutivo != null && !this.consecutivo.equals(other.consecutivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreMedicamento+" "+concentracion;
    }

}
