/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "manualtarifas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manualtarifas.findAll", query = "SELECT m FROM Manualtarifas m")
    , @NamedQuery(name = "Manualtarifas.findByCodigoCups", query = "SELECT m FROM Manualtarifas m WHERE m.codigoCups = :codigoCups")
    , @NamedQuery(name = "Manualtarifas.findByCodigoManual", query = "SELECT m FROM Manualtarifas m WHERE m.codigoManual = :codigoManual")
    , @NamedQuery(name = "Manualtarifas.findByDescripcionCups", query = "SELECT m FROM Manualtarifas m WHERE m.descripcionCups = :descripcionCups")
    , @NamedQuery(name = "Manualtarifas.findByFechaRegistro", query = "SELECT m FROM Manualtarifas m WHERE m.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Manualtarifas.findByFechaModificacion", query = "SELECT m FROM Manualtarifas m WHERE m.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Manualtarifas.findByActivo", query = "SELECT m FROM Manualtarifas m WHERE m.activo = :activo")
    , @NamedQuery(name = "Manualtarifas.findByValorUvr", query = "SELECT m FROM Manualtarifas m WHERE m.valorUvr = :valorUvr")})
public class Manualtarifas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(name = "codigoCups")
    private String codigoCups;
    @Size(max = 10)
    @Column(name = "codigoManual")
    private String codigoManual;
    @Size(max = 350)
    @Column(name = "descripcionCups")
    private String descripcionCups;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorUvr")
    private BigDecimal valorUvr;
    @JoinColumn(name = "codigoClasificacionManual", referencedColumnName = "codigoClasificacionManual")
    @ManyToOne
    private ClasificacionManual codigoClasificacionManual;
    @JoinColumn(name = "codigoPais", referencedColumnName = "codigoPais")
    @ManyToOne
    private Pais codigoPais;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoManualTarifario", referencedColumnName = "codigoManual")
    @ManyToOne
    private TipoManual codigoManualTarifario;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "codigoCups")
    private List<Procedimientosede> procedimientosedeList;
    @OneToMany(mappedBy = "codigoCups")
    private List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList;

    public Manualtarifas() {
    }

    public Manualtarifas(String codigoCups) {
        this.codigoCups = codigoCups;
    }

    public String getCodigoCups() {
        return codigoCups;
    }

    public void setCodigoCups(String codigoCups) {
        this.codigoCups = codigoCups;
    }

    public String getCodigoManual() {
        return codigoManual;
    }

    public void setCodigoManual(String codigoManual) {
        this.codigoManual = codigoManual;
    }

    public String getDescripcionCups() {
        return descripcionCups;
    }

    public void setDescripcionCups(String descripcionCups) {
        this.descripcionCups = descripcionCups;
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

    public BigDecimal getValorUvr() {
        return valorUvr;
    }

    public void setValorUvr(BigDecimal valorUvr) {
        this.valorUvr = valorUvr;
    }

    public ClasificacionManual getCodigoClasificacionManual() {
        return codigoClasificacionManual;
    }

    public void setCodigoClasificacionManual(ClasificacionManual codigoClasificacionManual) {
        this.codigoClasificacionManual = codigoClasificacionManual;
    }

    public Pais getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Pais codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public TipoManual getCodigoManualTarifario() {
        return codigoManualTarifario;
    }

    public void setCodigoManualTarifario(TipoManual codigoManualTarifario) {
        this.codigoManualTarifario = codigoManualTarifario;
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

    @XmlTransient
    public List<Procedimientosede> getProcedimientosedeList() {
        return procedimientosedeList;
    }

    public void setProcedimientosedeList(List<Procedimientosede> procedimientosedeList) {
        this.procedimientosedeList = procedimientosedeList;
    }

    @XmlTransient
    public List<ExamenesApoyoDiagnostico> getExamenesApoyoDiagnosticoList() {
        return examenesApoyoDiagnosticoList;
    }

    public void setExamenesApoyoDiagnosticoList(List<ExamenesApoyoDiagnostico> examenesApoyoDiagnosticoList) {
        this.examenesApoyoDiagnosticoList = examenesApoyoDiagnosticoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCups != null ? codigoCups.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manualtarifas)) {
            return false;
        }
        Manualtarifas other = (Manualtarifas) object;
        if ((this.codigoCups == null && other.codigoCups != null) || (this.codigoCups != null && !this.codigoCups.equals(other.codigoCups))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoCups + " " + descripcionCups;
    }

}
