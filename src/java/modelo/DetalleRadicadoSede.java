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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "DetalleRadicadoSede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleRadicadoSede.findAll", query = "SELECT d FROM DetalleRadicadoSede d")
    , @NamedQuery(name = "DetalleRadicadoSede.findByCodigoDetalleRadicado", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.codigoDetalleRadicado = :codigoDetalleRadicado")
    , @NamedQuery(name = "DetalleRadicadoSede.findByNumeroDocumentoIdentidad", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.numeroDocumentoIdentidad = :numeroDocumentoIdentidad")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorFacturaTotal", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorFacturaTotal = :valorFacturaTotal")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorCopago", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorCopago = :valorCopago")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorFacturado", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorFacturado = :valorFacturado")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorTotalFactura", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorTotalFactura = :valorTotalFactura")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorTotalCopago", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorTotalCopago = :valorTotalCopago")
    , @NamedQuery(name = "DetalleRadicadoSede.findByFechaRegistro", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "DetalleRadicadoSede.findByFechaSistema", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.fechaSistema = :fechaSistema")
    , @NamedQuery(name = "DetalleRadicadoSede.findByEstado", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.estado = :estado")
    , @NamedQuery(name = "DetalleRadicadoSede.findBySaldoRadicado", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.saldoRadicado = :saldoRadicado")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorLista", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorLista = :valorLista")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorCanceladoEps", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorCanceladoEps = :valorCanceladoEps")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorAceptadoEps", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorAceptadoEps = :valorAceptadoEps")
    , @NamedQuery(name = "DetalleRadicadoSede.findBySaldoTotal", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.saldoTotal = :saldoTotal")
    , @NamedQuery(name = "DetalleRadicadoSede.findBySaldoLista", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.saldoLista = :saldoLista")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorGlosa", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorGlosa = :valorGlosa")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorAceptado", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorAceptado = :valorAceptado")
    , @NamedQuery(name = "DetalleRadicadoSede.findByValorReclamado", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.valorReclamado = :valorReclamado")
    , @NamedQuery(name = "DetalleRadicadoSede.findByGlosa", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.glosa = :glosa")
    , @NamedQuery(name = "DetalleRadicadoSede.findBySaldoListado", query = "SELECT d FROM DetalleRadicadoSede d WHERE d.saldoListado = :saldoListado")})
public class DetalleRadicadoSede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoDetalleRadicado")
    private Long codigoDetalleRadicado;
    @Size(max = 50)
    @Column(name = "numeroDocumentoIdentidad")
    private String numeroDocumentoIdentidad;
    @Column(name = "valorFacturaTotal")
    private BigDecimal valorFacturaTotal;
    @Column(name = "valorCopago")
    private BigDecimal valorCopago;
    @Column(name = "valorFacturado")
    private BigDecimal valorFacturado;
    @Column(name = "valorTotalFactura")
    private BigDecimal valorTotalFactura;
    @Column(name = "valorTotalCopago")
    private BigDecimal valorTotalCopago;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaSistema")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Column(name = "saldoRadicado")
    private BigDecimal saldoRadicado;
    @Column(name = "saldoListado")
    private BigDecimal saldoListado;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "codigoEps", referencedColumnName = "codigoEps")
    @ManyToOne
    private Eps codigoEps;
    @JoinColumn(name = "codigoFactura", referencedColumnName = "codigoFactura")
    @ManyToOne(cascade = CascadeType.ALL)
    private Facturacion codigoFactura;
    @JoinColumn(name = "codigoListadoRadicado", referencedColumnName = "consecutivoRacadoEps")
    @ManyToOne(cascade = CascadeType.ALL)
    private ListaRadicadoEps codigoListadoRadicado;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @Column(name = "valorLista")
    private BigDecimal valorLista;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "codigoDetalleRadicado")
    private List<GlosaEps> glosaEpsList;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "codigoDetalleRadicado")
    private List<PagosEps> pagosEpsList;
    @Column(name = "valorCanceladoEps")
    private BigDecimal valorCanceladoEps;
    @Column(name = "valorAceptadoEps")
    private BigDecimal valorAceptadoEps;
    @Column(name = "saldoTotal")
    private BigDecimal saldoTotal;
    @Column(name = "saldoLista")
    private BigDecimal saldoLista;
    @Column(name = "valorGlosa")
    private BigDecimal valorGlosa;
    @Column(name = "valorAceptado")
    private BigDecimal valorAceptado;
    @Column(name = "valorReclamado")
    private BigDecimal valorReclamado;
    @Size(max = 1)
    @Column(name = "glosa")
    private String glosa;

    public DetalleRadicadoSede() {
    }

    public DetalleRadicadoSede(Long codigoDetalleRadicado) {
        this.codigoDetalleRadicado = codigoDetalleRadicado;
    }

    public Long getCodigoDetalleRadicado() {
        return codigoDetalleRadicado;
    }

    public void setCodigoDetalleRadicado(Long codigoDetalleRadicado) {
        this.codigoDetalleRadicado = codigoDetalleRadicado;
    }

    public String getNumeroDocumentoIdentidad() {
        return numeroDocumentoIdentidad;
    }

    public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
        this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaSistema() {
        return fechaSistema;
    }

    public void setFechaSistema(Date fechaSistema) {
        this.fechaSistema = fechaSistema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getValorFacturaTotal() {
        return valorFacturaTotal;
    }

    public void setValorFacturaTotal(BigDecimal valorFacturaTotal) {
        this.valorFacturaTotal = valorFacturaTotal;
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

    public BigDecimal getValorTotalFactura() {
        return valorTotalFactura;
    }

    public void setValorTotalFactura(BigDecimal valorTotalFactura) {
        this.valorTotalFactura = valorTotalFactura;
    }

    public BigDecimal getValorTotalCopago() {
        return valorTotalCopago;
    }

    public void setValorTotalCopago(BigDecimal valorTotalCopago) {
        this.valorTotalCopago = valorTotalCopago;
    }

    public BigDecimal getSaldoRadicado() {
        return saldoRadicado;
    }

    public void setSaldoRadicado(BigDecimal saldoRadicado) {
        this.saldoRadicado = saldoRadicado;
    }

    public BigDecimal getSaldoListado() {
        return saldoListado;
    }

    public void setSaldoListado(BigDecimal saldoListado) {
        this.saldoListado = saldoListado;
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

    public Facturacion getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Facturacion codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public ListaRadicadoEps getCodigoListadoRadicado() {
        return codigoListadoRadicado;
    }

    public void setCodigoListadoRadicado(ListaRadicadoEps codigoListadoRadicado) {
        this.codigoListadoRadicado = codigoListadoRadicado;
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

    public BigDecimal getValorLista() {
        return valorLista;
    }

    public void setValorLista(BigDecimal valorLista) {
        this.valorLista = valorLista;
    }

    public List<GlosaEps> getGlosaEpsList() {
        return glosaEpsList;
    }

    public void setGlosaEpsList(List<GlosaEps> glosaEpsList) {
        this.glosaEpsList = glosaEpsList;
    }

    public List<PagosEps> getPagosEpsList() {
        return pagosEpsList;
    }

    public void setPagosEpsList(List<PagosEps> pagosEpsList) {
        this.pagosEpsList = pagosEpsList;
    }

    public BigDecimal getValorCanceladoEps() {
        return valorCanceladoEps;
    }

    public void setValorCanceladoEps(BigDecimal valorCanceladoEps) {
        this.valorCanceladoEps = valorCanceladoEps;
    }

    public BigDecimal getValorAceptadoEps() {
        return valorAceptadoEps;
    }

    public void setValorAceptadoEps(BigDecimal valorAceptadoEps) {
        this.valorAceptadoEps = valorAceptadoEps;
    }

    public BigDecimal getSaldoTotal() {
        return saldoTotal;
    }

    public void setSaldoTotal(BigDecimal saldoTotal) {
        this.saldoTotal = saldoTotal;
    }

    public BigDecimal getSaldoLista() {
        return saldoLista;
    }

    public void setSaldoLista(BigDecimal saldoLista) {
        this.saldoLista = saldoLista;
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

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDetalleRadicado != null ? codigoDetalleRadicado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleRadicadoSede)) {
            return false;
        }
        DetalleRadicadoSede other = (DetalleRadicadoSede) object;
        if ((this.codigoDetalleRadicado == null && other.codigoDetalleRadicado != null) || (this.codigoDetalleRadicado != null && !this.codigoDetalleRadicado.equals(other.codigoDetalleRadicado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.DetalleRadicadoSede[ codigoDetalleRadicado=" + codigoDetalleRadicado + " ]";
    }

}
