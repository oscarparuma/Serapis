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
@Table(name = "diagnosticoIngreso")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DiagnosticoIngreso.findAll", query = "SELECT d FROM DiagnosticoIngreso d")
    , @NamedQuery(name = "DiagnosticoIngreso.findByCodigoDianosticoIngreso", query = "SELECT d FROM DiagnosticoIngreso d WHERE d.codigoDianosticoIngreso = :codigoDianosticoIngreso")
    , @NamedQuery(name = "DiagnosticoIngreso.findByFechaRegistro", query = "SELECT d FROM DiagnosticoIngreso d WHERE d.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "DiagnosticoIngreso.findByFechaModificacion", query = "SELECT d FROM DiagnosticoIngreso d WHERE d.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "DiagnosticoIngreso.findByFechaBorrado", query = "SELECT d FROM DiagnosticoIngreso d WHERE d.fechaBorrado = :fechaBorrado")
    , @NamedQuery(name = "DiagnosticoIngreso.findByActivo", query = "SELECT d FROM DiagnosticoIngreso d WHERE d.activo = :activo")
    , @NamedQuery(name = "DiagnosticoIngreso.findByBorrado", query = "SELECT d FROM DiagnosticoIngreso d WHERE d.borrado = :borrado")
    , @NamedQuery(name = "DiagnosticoIngreso.findByModificado", query = "SELECT d FROM DiagnosticoIngreso d WHERE d.modificado = :modificado")
    , @NamedQuery(name = "DiagnosticoIngreso.findByNumeroDocumentoIdentidad", query = "SELECT d FROM DiagnosticoIngreso d WHERE d.numeroDocumentoIdentidad = :numeroDocumentoIdentidad")})
public class DiagnosticoIngreso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoDianosticoIngreso")
    private Long codigoDianosticoIngreso;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "fechaBorrado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBorrado;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Size(max = 1)
    @Column(name = "borrado")
    private String borrado;
    @Size(max = 1)
    @Column(name = "modificado")
    private String modificado;
    @Size(max = 50)
    @Column(name = "numeroDocumentoIdentidad")
    private String numeroDocumentoIdentidad;
    @JoinColumn(name = "codigoCie10F", referencedColumnName = "codigoCie10")
    @ManyToOne
    private Cie10 codigoCie10F;
    @JoinColumn(name = "codigoCie10D", referencedColumnName = "codigoCie10")
    @ManyToOne
    private Cie10 codigoCie10D;
    @JoinColumn(name = "codigoCie10E", referencedColumnName = "codigoCie10")
    @ManyToOne
    private Cie10 codigoCie10E;
    @JoinColumn(name = "clasificacionCie10A", referencedColumnName = "codigoDescripcionCie10")
    @ManyToOne
    private Descripcioncie10 clasificacionCie10A;
    @JoinColumn(name = "codigoCie10G", referencedColumnName = "codigoCie10")
    @ManyToOne
    private Cie10 codigoCie10G;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "clasificacionCie10F", referencedColumnName = "codigoDescripcionCie10")
    @ManyToOne
    private Descripcioncie10 clasificacionCie10F;
    @JoinColumn(name = "clasificacionCie10C", referencedColumnName = "codigoDescripcionCie10")
    @ManyToOne
    private Descripcioncie10 clasificacionCie10C;
    @JoinColumn(name = "clasificacionCie10D", referencedColumnName = "codigoDescripcionCie10")
    @ManyToOne
    private Descripcioncie10 clasificacionCie10D;
    @JoinColumn(name = "borradoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario borradoPor;
    @JoinColumn(name = "codigoCie10A", referencedColumnName = "codigoCie10")
    @ManyToOne
    private Cie10 codigoCie10A;
    @JoinColumn(name = "codigoCie10B", referencedColumnName = "codigoCie10")
    @ManyToOne
    private Cie10 codigoCie10B;
    @JoinColumn(name = "codigoCie10C", referencedColumnName = "codigoCie10")
    @ManyToOne
    private Cie10 codigoCie10C;
    @JoinColumn(name = "codigoCie10", referencedColumnName = "codigoCie10")
    @ManyToOne
    private Cie10 codigoCie10;
    @JoinColumn(name = "codigoCita", referencedColumnName = "codigoCitaPersona")
    @ManyToOne(cascade = CascadeType.ALL)
    private Citapersona codigoCita;
    @JoinColumn(name = "clasificacionCie10E", referencedColumnName = "codigoDescripcionCie10")
    @ManyToOne
    private Descripcioncie10 clasificacionCie10E;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "clasificacionCie10", referencedColumnName = "codigoDescripcionCie10")
    @ManyToOne
    private Descripcioncie10 clasificacionCie10;
    @JoinColumn(name = "clasificacionCie10B", referencedColumnName = "codigoDescripcionCie10")
    @ManyToOne
    private Descripcioncie10 clasificacionCie10B;
    @JoinColumn(name = "clasificacionCie10G", referencedColumnName = "codigoDescripcionCie10")
    @ManyToOne
    private Descripcioncie10 clasificacionCie10G;
    @JoinColumn(name = "codigoTipoDiagnostico", referencedColumnName = "codigoTipoDiagnostico")
    @ManyToOne
    private TipoDiagnostico codigoTipoDiagnostico;

    public DiagnosticoIngreso() {
    }

    public DiagnosticoIngreso(Long codigoDianosticoIngreso) {
        this.codigoDianosticoIngreso = codigoDianosticoIngreso;
    }

    public Long getCodigoDianosticoIngreso() {
        return codigoDianosticoIngreso;
    }

    public void setCodigoDianosticoIngreso(Long codigoDianosticoIngreso) {
        this.codigoDianosticoIngreso = codigoDianosticoIngreso;
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

    public Date getFechaBorrado() {
        return fechaBorrado;
    }

    public void setFechaBorrado(Date fechaBorrado) {
        this.fechaBorrado = fechaBorrado;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getBorrado() {
        return borrado;
    }

    public void setBorrado(String borrado) {
        this.borrado = borrado;
    }

    public String getModificado() {
        return modificado;
    }

    public void setModificado(String modificado) {
        this.modificado = modificado;
    }

    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public Cie10 getCodigoCie10F() {
        return codigoCie10F;
    }

    public void setCodigoCie10F(Cie10 codigoCie10F) {
        this.codigoCie10F = codigoCie10F;
    }

    public Cie10 getCodigoCie10D() {
        return codigoCie10D;
    }

    public void setCodigoCie10D(Cie10 codigoCie10D) {
        this.codigoCie10D = codigoCie10D;
    }

    public Cie10 getCodigoCie10E() {
        return codigoCie10E;
    }

    public void setCodigoCie10E(Cie10 codigoCie10E) {
        this.codigoCie10E = codigoCie10E;
    }

    public Descripcioncie10 getClasificacionCie10A() {
        return clasificacionCie10A;
    }

    public void setClasificacionCie10A(Descripcioncie10 clasificacionCie10A) {
        this.clasificacionCie10A = clasificacionCie10A;
    }

    public Cie10 getCodigoCie10G() {
        return codigoCie10G;
    }

    public void setCodigoCie10G(Cie10 codigoCie10G) {
        this.codigoCie10G = codigoCie10G;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Descripcioncie10 getClasificacionCie10F() {
        return clasificacionCie10F;
    }

    public void setClasificacionCie10F(Descripcioncie10 clasificacionCie10F) {
        this.clasificacionCie10F = clasificacionCie10F;
    }

    public Descripcioncie10 getClasificacionCie10C() {
        return clasificacionCie10C;
    }

    public void setClasificacionCie10C(Descripcioncie10 clasificacionCie10C) {
        this.clasificacionCie10C = clasificacionCie10C;
    }

    public Descripcioncie10 getClasificacionCie10D() {
        return clasificacionCie10D;
    }

    public void setClasificacionCie10D(Descripcioncie10 clasificacionCie10D) {
        this.clasificacionCie10D = clasificacionCie10D;
    }

    public Usuario getBorradoPor() {
        return borradoPor;
    }

    public void setBorradoPor(Usuario borradoPor) {
        this.borradoPor = borradoPor;
    }

    public Cie10 getCodigoCie10A() {
        return codigoCie10A;
    }

    public void setCodigoCie10A(Cie10 codigoCie10A) {
        this.codigoCie10A = codigoCie10A;
    }

    public Cie10 getCodigoCie10B() {
        return codigoCie10B;
    }

    public void setCodigoCie10B(Cie10 codigoCie10B) {
        this.codigoCie10B = codigoCie10B;
    }

    public Cie10 getCodigoCie10C() {
        return codigoCie10C;
    }

    public void setCodigoCie10C(Cie10 codigoCie10C) {
        this.codigoCie10C = codigoCie10C;
    }

    public Cie10 getCodigoCie10() {
        return codigoCie10;
    }

    public void setCodigoCie10(Cie10 codigoCie10) {
        this.codigoCie10 = codigoCie10;
    }

    public Citapersona getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(Citapersona codigoCita) {
        this.codigoCita = codigoCita;
    }

    public Descripcioncie10 getClasificacionCie10E() {
        return clasificacionCie10E;
    }

    public void setClasificacionCie10E(Descripcioncie10 clasificacionCie10E) {
        this.clasificacionCie10E = clasificacionCie10E;
    }

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Datospersona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Datospersona codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public Descripcioncie10 getClasificacionCie10() {
        return clasificacionCie10;
    }

    public void setClasificacionCie10(Descripcioncie10 clasificacionCie10) {
        this.clasificacionCie10 = clasificacionCie10;
    }

    public Descripcioncie10 getClasificacionCie10B() {
        return clasificacionCie10B;
    }

    public void setClasificacionCie10B(Descripcioncie10 clasificacionCie10B) {
        this.clasificacionCie10B = clasificacionCie10B;
    }

    public Descripcioncie10 getClasificacionCie10G() {
        return clasificacionCie10G;
    }

    public void setClasificacionCie10G(Descripcioncie10 clasificacionCie10G) {
        this.clasificacionCie10G = clasificacionCie10G;
    }

    public TipoDiagnostico getCodigoTipoDiagnostico() {
        return codigoTipoDiagnostico;
    }

    public void setCodigoTipoDiagnostico(TipoDiagnostico codigoTipoDiagnostico) {
        this.codigoTipoDiagnostico = codigoTipoDiagnostico;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDianosticoIngreso != null ? codigoDianosticoIngreso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DiagnosticoIngreso)) {
            return false;
        }
        DiagnosticoIngreso other = (DiagnosticoIngreso) object;
        if ((this.codigoDianosticoIngreso == null && other.codigoDianosticoIngreso != null) || (this.codigoDianosticoIngreso != null && !this.codigoDianosticoIngreso.equals(other.codigoDianosticoIngreso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DiagnosticoIngreso[ codigoDianosticoIngreso=" + codigoDianosticoIngreso + " ]";
    }

}
