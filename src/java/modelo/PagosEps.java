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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "PagosEps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PagosEps.findAll", query = "SELECT p FROM PagosEps p")
    , @NamedQuery(name = "PagosEps.findByCodigoPago", query = "SELECT p FROM PagosEps p WHERE p.codigoPago = :codigoPago")
    , @NamedQuery(name = "PagosEps.findByValorSaldo", query = "SELECT p FROM PagosEps p WHERE p.valorSaldo = :valorSaldo")
    , @NamedQuery(name = "PagosEps.findByValorCancelado", query = "SELECT p FROM PagosEps p WHERE p.valorCancelado = :valorCancelado")
    , @NamedQuery(name = "PagosEps.findBySaldoActual", query = "SELECT p FROM PagosEps p WHERE p.saldoActual = :saldoActual")
    , @NamedQuery(name = "PagosEps.findByNumeroSoportePago", query = "SELECT p FROM PagosEps p WHERE p.numeroSoportePago = :numeroSoportePago")
    , @NamedQuery(name = "PagosEps.findByFechaRegistro", query = "SELECT p FROM PagosEps p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "PagosEps.findByValorLista", query = "SELECT p FROM PagosEps p WHERE p.valorLista = :valorLista")
    , @NamedQuery(name = "PagosEps.findByValorCanceladoLista", query = "SELECT p FROM PagosEps p WHERE p.valorCanceladoLista = :valorCanceladoLista")
    , @NamedQuery(name = "PagosEps.findBySaldoLista", query = "SELECT p FROM PagosEps p WHERE p.saldoLista = :saldoLista")
    , @NamedQuery(name = "PagosEps.findByFechaSoportePago", query = "SELECT p FROM PagosEps p WHERE p.fechaSoportePago = :fechaSoportePago")})
public class PagosEps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoPago")
    private Long codigoPago;
    @Column(name = "valorSaldo")
    private BigDecimal valorSaldo;
    @Column(name = "valorCancelado")
    private BigDecimal valorCancelado;
    @Column(name = "saldoActual")
    private BigDecimal saldoActual;
    @Size(max = 50)
    @Column(name = "numeroSoportePago")
    private String numeroSoportePago;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaSoportePago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSoportePago;
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
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @Column(name = "valorLista")
    private BigDecimal valorLista;
    @Column(name = "valorCanceladoLista")
    private BigDecimal valorCanceladoLista;
    @Column(name = "saldoLista")
    private BigDecimal saldoLista;

    public PagosEps() {
    }

    public PagosEps(Long codigoPago) {
        this.codigoPago = codigoPago;
    }

    public Long getCodigoPago() {
        return codigoPago;
    }

    public void setCodigoPago(Long codigoPago) {
        this.codigoPago = codigoPago;
    }

    public BigDecimal getValorSaldo() {
        return valorSaldo;
    }

    public void setValorSaldo(BigDecimal valorSaldo) {
        this.valorSaldo = valorSaldo;
    }

    public BigDecimal getValorCancelado() {
        return valorCancelado;
    }

    public void setValorCancelado(BigDecimal valorCancelado) {
        this.valorCancelado = valorCancelado;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getNumeroSoportePago() {
        return numeroSoportePago;
    }

    public void setNumeroSoportePago(String numeroSoportePago) {
        this.numeroSoportePago = numeroSoportePago;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaSoportePago() {
        return fechaSoportePago;
    }

    public void setFechaSoportePago(Date fechaSoportePago) {
        this.fechaSoportePago = fechaSoportePago;
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

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public BigDecimal getValorLista() {
        return valorLista;
    }

    public void setValorLista(BigDecimal valorLista) {
        this.valorLista = valorLista;
    }

    public BigDecimal getValorCanceladoLista() {
        return valorCanceladoLista;
    }

    public void setValorCanceladoLista(BigDecimal valorCanceladoLista) {
        this.valorCanceladoLista = valorCanceladoLista;
    }

    public BigDecimal getSaldoLista() {
        return saldoLista;
    }

    public void setSaldoLista(BigDecimal saldoLista) {
        this.saldoLista = saldoLista;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPago != null ? codigoPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PagosEps)) {
            return false;
        }
        PagosEps other = (PagosEps) object;
        if ((this.codigoPago == null && other.codigoPago != null) || (this.codigoPago != null && !this.codigoPago.equals(other.codigoPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.PagosEps[ codigoPago=" + codigoPago + " ]";
    }

}
