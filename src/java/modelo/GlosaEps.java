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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "GlosaEps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GlosaEps.findAll", query = "SELECT g FROM GlosaEps g")
    , @NamedQuery(name = "GlosaEps.findByCodigoGlosa", query = "SELECT g FROM GlosaEps g WHERE g.codigoGlosa = :codigoGlosa")
    , @NamedQuery(name = "GlosaEps.findByValorGlosa", query = "SELECT g FROM GlosaEps g WHERE g.valorGlosa = :valorGlosa")
    , @NamedQuery(name = "GlosaEps.findByValorAceptado", query = "SELECT g FROM GlosaEps g WHERE g.valorAceptado = :valorAceptado")
    , @NamedQuery(name = "GlosaEps.findByValorReclamado", query = "SELECT g FROM GlosaEps g WHERE g.valorReclamado = :valorReclamado")
    , @NamedQuery(name = "GlosaEps.findByFecharegistro", query = "SELECT g FROM GlosaEps g WHERE g.fecharegistro = :fecharegistro")
    , @NamedQuery(name = "GlosaEps.findByTotalGlosa", query = "SELECT g FROM GlosaEps g WHERE g.totalGlosa = :totalGlosa")
    , @NamedQuery(name = "GlosaEps.findBySaldoFactura", query = "SELECT g FROM GlosaEps g WHERE g.saldoFactura = :saldoFactura")
    , @NamedQuery(name = "GlosaEps.findByFechaRespuesta", query = "SELECT g FROM GlosaEps g WHERE g.fechaRespuesta = :fechaRespuesta")})
public class GlosaEps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoGlosa")
    private Long codigoGlosa;
    @Column(name = "valorGlosa")
    private BigDecimal valorGlosa;
    @Column(name = "valorAceptado")
    private BigDecimal valorAceptado;
    @Column(name = "valorReclamado")
    private BigDecimal valorReclamado;
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecharegistro;
    @Column(name = "fechaRespuesta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRespuesta;
    @JoinColumn(name = "codigoDetalleRadicado", referencedColumnName = "codigoDetalleRadicado")
    @ManyToOne(cascade = CascadeType.ALL)
    private DetalleRadicadoSede codigoDetalleRadicado;
    @JoinColumn(name = "codigoEps", referencedColumnName = "codigoEps")
    @ManyToOne
    private Eps codigoEps;
    @JoinColumn(name = "codigoFactura", referencedColumnName = "codigoFactura")
    @ManyToOne
    private Facturacion codigoFactura;
    @JoinColumn(name = "consecutivoRacado", referencedColumnName = "consecutivoRacadoEps")
    @ManyToOne(cascade = CascadeType.ALL)
    private ListaRadicadoEps consecutivoRacado;
    @JoinColumn(name = "codigoTipoGlosa", referencedColumnName = "codigoGlosa")
    @ManyToOne
    private TipoGlosa codigoTipoGlosa;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "usuarioRespuesta", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioRespuesta;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @Column(name = "totalGlosa")
    private BigDecimal totalGlosa;
    @Column(name = "saldoFactura")
    private BigDecimal saldoFactura;

    public GlosaEps() {
    }

    public GlosaEps(Long codigoGlosa) {
        this.codigoGlosa = codigoGlosa;
    }

    public Long getCodigoGlosa() {
        return codigoGlosa;
    }

    public void setCodigoGlosa(Long codigoGlosa) {
        this.codigoGlosa = codigoGlosa;
    }

    public BigDecimal getValorGlosa() {
        return valorGlosa;
    }

    public void setValorGlosa(BigDecimal valorGlosa) {
        this.valorGlosa = valorGlosa;
    }

    public BigDecimal getValorAceptado() {
        return valorAceptado;
    }

    public void setValorAceptado(BigDecimal valorAceptado) {
        this.valorAceptado = valorAceptado;
    }

    public BigDecimal getValorReclamado() {
        return valorReclamado;
    }

    public void setValorReclamado(BigDecimal valorReclamado) {
        this.valorReclamado = valorReclamado;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public DetalleRadicadoSede getCodigoDetalleRadicado() {
        return codigoDetalleRadicado;
    }

    public void setCodigoDetalleRadicado(DetalleRadicadoSede codigoDetalleRadicado) {
        this.codigoDetalleRadicado = codigoDetalleRadicado;
    }

    public Eps getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(Eps codigoEps) {
        this.codigoEps = codigoEps;
    }

    public Facturacion getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Facturacion codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public ListaRadicadoEps getConsecutivoRacado() {
        return consecutivoRacado;
    }

    public void setConsecutivoRacado(ListaRadicadoEps consecutivoRacado) {
        this.consecutivoRacado = consecutivoRacado;
    }

    public TipoGlosa getCodigoTipoGlosa() {
        return codigoTipoGlosa;
    }

    public void setCodigoTipoGlosa(TipoGlosa codigoTipoGlosa) {
        this.codigoTipoGlosa = codigoTipoGlosa;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getUsuarioRespuesta() {
        return usuarioRespuesta;
    }

    public void setUsuarioRespuesta(Usuario usuarioRespuesta) {
        this.usuarioRespuesta = usuarioRespuesta;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public BigDecimal getTotalGlosa() {
        return totalGlosa;
    }

    public void setTotalGlosa(BigDecimal totalGlosa) {
        this.totalGlosa = totalGlosa;
    }

    public BigDecimal getSaldoFactura() {
        return saldoFactura;
    }

    public void setSaldoFactura(BigDecimal saldoFactura) {
        this.saldoFactura = saldoFactura;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoGlosa != null ? codigoGlosa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GlosaEps)) {
            return false;
        }
        GlosaEps other = (GlosaEps) object;
        if ((this.codigoGlosa == null && other.codigoGlosa != null) || (this.codigoGlosa != null && !this.codigoGlosa.equals(other.codigoGlosa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoFactura + " ";
    }

}
