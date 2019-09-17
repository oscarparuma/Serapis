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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "RadicacionFacturas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RadicacionFacturas.findAll", query = "SELECT r FROM RadicacionFacturas r")
    , @NamedQuery(name = "RadicacionFacturas.findByCodigoRadicacion", query = "SELECT r FROM RadicacionFacturas r WHERE r.codigoRadicacion = :codigoRadicacion")
    , @NamedQuery(name = "RadicacionFacturas.findByValorUnitario", query = "SELECT r FROM RadicacionFacturas r WHERE r.valorUnitario = :valorUnitario")
    , @NamedQuery(name = "RadicacionFacturas.findByValorCopago", query = "SELECT r FROM RadicacionFacturas r WHERE r.valorCopago = :valorCopago")
    , @NamedQuery(name = "RadicacionFacturas.findByValorFacturado", query = "SELECT r FROM RadicacionFacturas r WHERE r.valorFacturado = :valorFacturado")
    , @NamedQuery(name = "RadicacionFacturas.findByTotalRadicado", query = "SELECT r FROM RadicacionFacturas r WHERE r.totalRadicado = :totalRadicado")
    , @NamedQuery(name = "RadicacionFacturas.findByEstadoRadicacion", query = "SELECT r FROM RadicacionFacturas r WHERE r.estadoRadicacion = :estadoRadicacion")
    , @NamedQuery(name = "RadicacionFacturas.findByFechaRegistro", query = "SELECT r FROM RadicacionFacturas r WHERE r.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "RadicacionFacturas.findByFechaRadicacion", query = "SELECT r FROM RadicacionFacturas r WHERE r.fechaRadicacion = :fechaRadicacion")
    , @NamedQuery(name = "RadicacionFacturas.findByCcsRadicacion", query = "SELECT r FROM RadicacionFacturas r WHERE r.ccsRadicacion = :ccsRadicacion")
    , @NamedQuery(name = "RadicacionFacturas.findByRadicacionSede", query = "SELECT r FROM RadicacionFacturas r WHERE r.radicacionSede = :radicacionSede")
    , @NamedQuery(name = "RadicacionFacturas.findByFacturaSede", query = "SELECT r FROM RadicacionFacturas r WHERE r.facturaSede = :facturaSede")})
public class RadicacionFacturas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoRadicacion")
    private Long codigoRadicacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorUnitario")
    private BigDecimal valorUnitario;
    @Column(name = "valorCopago")
    private BigDecimal valorCopago;
    @Column(name = "valorFacturado")
    private BigDecimal valorFacturado;
    @Column(name = "totalRadicado")
    private BigDecimal totalRadicado;
    @Size(max = 1)
    @Column(name = "estadoRadicacion")
    private String estadoRadicacion;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaRadicacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRadicacion;
    @Column(name = "ccsRadicacion")
    private Long ccsRadicacion;
    @Size(max = 250)
    @Column(name = "radicacionSede")
    private String radicacionSede;
    @Size(max = 150)
    @Column(name = "facturaSede")
    private String facturaSede;
    @JoinColumn(name = "codigoCitaPersona", referencedColumnName = "codigoCitaPersona")
    @ManyToOne
    private Citapersona codigoCitaPersona;
    @JoinColumn(name = "codigoEps", referencedColumnName = "codigoEps")
    @ManyToOne
    private Eps codigoEps;
    @JoinColumn(name = "codigoFactura", referencedColumnName = "codigoFactura")
    @ManyToOne
    private Facturacion codigoFactura;
    @JoinColumn(name = "codigoRadicacionFactura", referencedColumnName = "codigoFacturaRadicacion")
    @ManyToOne
    private FacturaRadicacion codigoRadicacionFactura;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;

    public RadicacionFacturas() {
    }

    public RadicacionFacturas(Long codigoRadicacion) {
        this.codigoRadicacion = codigoRadicacion;
    }

    public Long getCodigoRadicacion() {
        return codigoRadicacion;
    }

    public void setCodigoRadicacion(Long codigoRadicacion) {
        this.codigoRadicacion = codigoRadicacion;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorFacturado() {
        return valorFacturado;
    }

    public void setValorFacturado(BigDecimal valorFacturado) {
        this.valorFacturado = valorFacturado;
    }

    public BigDecimal getTotalRadicado() {
        return totalRadicado;
    }

    public void setTotalRadicado(BigDecimal totalRadicado) {
        this.totalRadicado = totalRadicado;
    }

    public String getEstadoRadicacion() {
        return estadoRadicacion;
    }

    public void setEstadoRadicacion(String estadoRadicacion) {
        this.estadoRadicacion = estadoRadicacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public Long getCcsRadicacion() {
        return ccsRadicacion;
    }

    public void setCcsRadicacion(Long ccsRadicacion) {
        this.ccsRadicacion = ccsRadicacion;
    }

    public String getRadicacionSede() {
        return radicacionSede;
    }

    public void setRadicacionSede(String radicacionSede) {
        this.radicacionSede = radicacionSede;
    }

    public String getFacturaSede() {
        return facturaSede;
    }

    public void setFacturaSede(String facturaSede) {
        this.facturaSede = facturaSede;
    }

    public Citapersona getCodigoCitaPersona() {
        return codigoCitaPersona;
    }

    public void setCodigoCitaPersona(Citapersona codigoCitaPersona) {
        this.codigoCitaPersona = codigoCitaPersona;
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

    public FacturaRadicacion getCodigoRadicacionFactura() {
        return codigoRadicacionFactura;
    }

    public void setCodigoRadicacionFactura(FacturaRadicacion codigoRadicacionFactura) {
        this.codigoRadicacionFactura = codigoRadicacionFactura;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public Usuario getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(Usuario usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoRadicacion != null ? codigoRadicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RadicacionFacturas)) {
            return false;
        }
        RadicacionFacturas other = (RadicacionFacturas) object;
        if ((this.codigoRadicacion == null && other.codigoRadicacion != null) || (this.codigoRadicacion != null && !this.codigoRadicacion.equals(other.codigoRadicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return codigoFactura + " ";
    }

}
