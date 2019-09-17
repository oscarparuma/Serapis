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
@Table(name = "carteraSede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CarteraSede.findAll", query = "SELECT c FROM CarteraSede c")
    , @NamedQuery(name = "CarteraSede.findByCodigoCartera", query = "SELECT c FROM CarteraSede c WHERE c.codigoCartera = :codigoCartera")
    , @NamedQuery(name = "CarteraSede.findByValorTotalfactura", query = "SELECT c FROM CarteraSede c WHERE c.valorTotalfactura = :valorTotalfactura")
    , @NamedQuery(name = "CarteraSede.findByValorCancelado", query = "SELECT c FROM CarteraSede c WHERE c.valorCancelado = :valorCancelado")
    , @NamedQuery(name = "CarteraSede.findBySaldoFactura", query = "SELECT c FROM CarteraSede c WHERE c.saldoFactura = :saldoFactura")
    , @NamedQuery(name = "CarteraSede.findByFechaRegistro", query = "SELECT c FROM CarteraSede c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "CarteraSede.findByReciboPago", query = "SELECT c FROM CarteraSede c WHERE c.reciboPago = :reciboPago")
    , @NamedQuery(name = "CarteraSede.findByCcsRecibo", query = "SELECT c FROM CarteraSede c WHERE c.ccsRecibo = :ccsRecibo")
    , @NamedQuery(name = "CarteraSede.findByRegistroPagoEps", query = "SELECT c FROM CarteraSede c WHERE c.registroPagoEps = :registroPagoEps")
    , @NamedQuery(name = "CarteraSede.findByFechaReciboPagoEps", query = "SELECT c FROM CarteraSede c WHERE c.fechaReciboPagoEps = :fechaReciboPagoEps")
    , @NamedQuery(name = "CarteraSede.findByEstadoRecibo", query = "SELECT c FROM CarteraSede c WHERE c.estadoRecibo = :estadoRecibo")
    , @NamedQuery(name = "CarteraSede.findByNumeroDocumento", query = "SELECT c FROM CarteraSede c WHERE c.numeroDocumento = :numeroDocumento")})
public class CarteraSede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoCartera")
    private Long codigoCartera;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorTotalfactura")
    private BigDecimal valorTotalfactura;
    @Column(name = "valorCancelado")
    private BigDecimal valorCancelado;
    @Column(name = "saldoFactura")
    private BigDecimal saldoFactura;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Size(max = 50)
    @Column(name = "reciboPago")
    private String reciboPago;
    @Column(name = "ccsRecibo")
    private Long ccsRecibo;
    @Size(max = 50)
    @Column(name = "registroPagoEps")
    private String registroPagoEps;
    @Column(name = "fechaReciboPagoEps")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaReciboPagoEps;
    @Size(max = 1)
    @Column(name = "estadoRecibo")
    private String estadoRecibo;
    @Size(max = 50)
    @Column(name = "numeroDocumento")
    private String numeroDocumento;
    @JoinColumn(name = "codigoCitaPersona", referencedColumnName = "codigoCitaPersona")
    @ManyToOne
    private Citapersona codigoCitaPersona;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "codigoEps", referencedColumnName = "codigoEps")
    @ManyToOne
    private Eps codigoEps;
    @JoinColumn(name = "codigoFacturacion", referencedColumnName = "codigoFactura")
    @ManyToOne
    private Facturacion codigoFacturacion;
    @JoinColumn(name = "codigoMedioPago", referencedColumnName = "codigoMedioPago")
    @ManyToOne
    private MedioPago codigoMedioPago;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;

    public CarteraSede() {
    }

    public CarteraSede(Long codigoCartera) {
        this.codigoCartera = codigoCartera;
    }

    public Long getCodigoCartera() {
        return codigoCartera;
    }

    public void setCodigoCartera(Long codigoCartera) {
        this.codigoCartera = codigoCartera;
    }

    public BigDecimal getValorTotalfactura() {
        return valorTotalfactura;
    }

    public void setValorTotalfactura(BigDecimal valorTotalfactura) {
        this.valorTotalfactura = valorTotalfactura;
    }

    public BigDecimal getValorCancelado() {
        return valorCancelado;
    }

    public void setValorCancelado(BigDecimal valorCancelado) {
        this.valorCancelado = valorCancelado;
    }

    public BigDecimal getSaldoFactura() {
        return saldoFactura;
    }

    public void setSaldoFactura(BigDecimal saldoFactura) {
        this.saldoFactura = saldoFactura;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getReciboPago() {
        return reciboPago;
    }

    public void setReciboPago(String reciboPago) {
        this.reciboPago = reciboPago;
    }

    public Long getCcsRecibo() {
        return ccsRecibo;
    }

    public void setCcsRecibo(Long ccsRecibo) {
        this.ccsRecibo = ccsRecibo;
    }

    public String getRegistroPagoEps() {
        return registroPagoEps;
    }

    public void setRegistroPagoEps(String registroPagoEps) {
        this.registroPagoEps = registroPagoEps;
    }

    public Date getFechaReciboPagoEps() {
        return fechaReciboPagoEps;
    }

    public void setFechaReciboPagoEps(Date fechaReciboPagoEps) {
        this.fechaReciboPagoEps = fechaReciboPagoEps;
    }

    public String getEstadoRecibo() {
        return estadoRecibo;
    }

    public void setEstadoRecibo(String estadoRecibo) {
        this.estadoRecibo = estadoRecibo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Citapersona getCodigoCitaPersona() {
        return codigoCitaPersona;
    }

    public void setCodigoCitaPersona(Citapersona codigoCitaPersona) {
        this.codigoCitaPersona = codigoCitaPersona;
    }

    public Datospersona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Datospersona codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public Eps getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(Eps codigoEps) {
        this.codigoEps = codigoEps;
    }

    public Facturacion getCodigoFacturacion() {
        return codigoFacturacion;
    }

    public void setCodigoFacturacion(Facturacion codigoFacturacion) {
        this.codigoFacturacion = codigoFacturacion;
    }

    public MedioPago getCodigoMedioPago() {
        return codigoMedioPago;
    }

    public void setCodigoMedioPago(MedioPago codigoMedioPago) {
        this.codigoMedioPago = codigoMedioPago;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCartera != null ? codigoCartera.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarteraSede)) {
            return false;
        }
        CarteraSede other = (CarteraSede) object;
        if ((this.codigoCartera == null && other.codigoCartera != null) || (this.codigoCartera != null && !this.codigoCartera.equals(other.codigoCartera))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.CarteraSede[ codigoCartera=" + codigoCartera + " ]";
    }

}
