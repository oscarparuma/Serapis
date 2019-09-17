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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "ListaRadicadoEps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListaRadicadoEps.findAll", query = "SELECT l FROM ListaRadicadoEps l")
    , @NamedQuery(name = "ListaRadicadoEps.findByConsecutivoRacadoEps", query = "SELECT l FROM ListaRadicadoEps l WHERE l.consecutivoRacadoEps = :consecutivoRacadoEps")
    , @NamedQuery(name = "ListaRadicadoEps.findByFechaRegistro", query = "SELECT l FROM ListaRadicadoEps l WHERE l.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ListaRadicadoEps.findByFechaSistema", query = "SELECT l FROM ListaRadicadoEps l WHERE l.fechaSistema = :fechaSistema")
    , @NamedQuery(name = "ListaRadicadoEps.findByEstado", query = "SELECT l FROM ListaRadicadoEps l WHERE l.estado = :estado")
    , @NamedQuery(name = "ListaRadicadoEps.findByValorLista", query = "SELECT l FROM ListaRadicadoEps l WHERE l.valorLista = :valorLista")
    , @NamedQuery(name = "ListaRadicadoEps.findByFechaRadicado", query = "SELECT l FROM ListaRadicadoEps l WHERE l.fechaRadicado = :fechaRadicado")
    , @NamedQuery(name = "ListaRadicadoEps.findByFacturaNumero", query = "SELECT l FROM ListaRadicadoEps l WHERE l.facturaNumero = :facturaNumero")
    , @NamedQuery(name = "ListaRadicadoEps.findByFechaFactura", query = "SELECT l FROM ListaRadicadoEps l WHERE l.fechaFactura = :fechaFactura")
    , @NamedQuery(name = "ListaRadicadoEps.findByFechaInicialFactura", query = "SELECT l FROM ListaRadicadoEps l WHERE l.fechaInicialFactura = :fechaInicialFactura")
    , @NamedQuery(name = "ListaRadicadoEps.findByFechaFinalFactura", query = "SELECT l FROM ListaRadicadoEps l WHERE l.fechaFinalFactura = :fechaFinalFactura")
    , @NamedQuery(name = "ListaRadicadoEps.findByFechaFinalizado", query = "SELECT l FROM ListaRadicadoEps l WHERE l.fechaFinalizado = :fechaFinalizado")
    , @NamedQuery(name = "ListaRadicadoEps.findByNumeroRadicado", query = "SELECT l FROM ListaRadicadoEps l WHERE l.numeroRadicado = :numeroRadicado")
    , @NamedQuery(name = "ListaRadicadoEps.findByValorCanceladoEps", query = "SELECT l FROM ListaRadicadoEps l WHERE l.valorCanceladoEps = :valorCanceladoEps")
    , @NamedQuery(name = "ListaRadicadoEps.findByValorAceptadoEps", query = "SELECT l FROM ListaRadicadoEps l WHERE l.valorAceptadoEps = :valorAceptadoEps")
    , @NamedQuery(name = "ListaRadicadoEps.findBySaldoTotal", query = "SELECT l FROM ListaRadicadoEps l WHERE l.saldoTotal = :saldoTotal")
    , @NamedQuery(name = "ListaRadicadoEps.findByFechaRadicadoEps", query = "SELECT l FROM ListaRadicadoEps l WHERE l.fechaRadicadoEps = :fechaRadicadoEps")})
public class ListaRadicadoEps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "consecutivoRacadoEps")
    private Integer consecutivoRacadoEps;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaSistema")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSistema;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Column(name = "valorLista")
    private BigDecimal valorLista;
    @Column(name = "fechaRadicado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRadicado;
    @Size(max = 50)
    @Column(name = "facturaNumero")
    private String facturaNumero;
    @Column(name = "fechaFactura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFactura;
    @Column(name = "fechaInicialFactura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicialFactura;
    @Column(name = "fechaFinalFactura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalFactura;
    @Column(name = "fechaFinalizado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizado;
    @Size(max = 50)
    @Column(name = "numeroRadicado")
    private String numeroRadicado;
    @Column(name = "fechaRadicadoEps")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRadicadoEps;
    @JoinColumn(name = "codigoEps", referencedColumnName = "codigoEps")
    @ManyToOne
    private Eps codigoEps;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioRadico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioRadico;
    @JoinColumn(name = "radicadaPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario radicadaPor;
    @JoinColumn(name = "codigoUsuarioFinalizo", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioFinalizo;
    @OneToMany(mappedBy = "codigoListadoRadicado")
    private List<DetalleRadicadoSede> detalleRadicadoSedeList;
    @OneToMany(mappedBy = "consecutivoRacado")
    private List<GlosaEps> glosaEpsList;
    @OneToMany(mappedBy = "consecutivoRacado")
    private List<PagosEps> pagosEpsList;
    @Column(name = "valorCanceladoEps")
    private BigDecimal valorCanceladoEps;
    @Column(name = "valorAceptadoEps")
    private BigDecimal valorAceptadoEps;
    @Column(name = "saldoTotal")
    private BigDecimal saldoTotal;

    public ListaRadicadoEps() {
    }

    public ListaRadicadoEps(Integer consecutivoRacadoEps) {
        this.consecutivoRacadoEps = consecutivoRacadoEps;
    }

    public Integer getConsecutivoRacadoEps() {
        return consecutivoRacadoEps;
    }

    public void setConsecutivoRacadoEps(Integer consecutivoRacadoEps) {
        this.consecutivoRacadoEps = consecutivoRacadoEps;
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

    public BigDecimal getValorLista() {
        return valorLista;
    }

    public void setValorLista(BigDecimal valorLista) {
        this.valorLista = valorLista;
    }

    public Date getFechaRadicado() {
        return fechaRadicado;
    }

    public void setFechaRadicado(Date fechaRadicado) {
        this.fechaRadicado = fechaRadicado;
    }

    public String getFacturaNumero() {
        return facturaNumero;
    }

    public void setFacturaNumero(String facturaNumero) {
        this.facturaNumero = facturaNumero;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Date getFechaInicialFactura() {
        return fechaInicialFactura;
    }

    public void setFechaInicialFactura(Date fechaInicialFactura) {
        this.fechaInicialFactura = fechaInicialFactura;
    }

    public Date getFechaFinalFactura() {
        return fechaFinalFactura;
    }

    public void setFechaFinalFactura(Date fechaFinalFactura) {
        this.fechaFinalFactura = fechaFinalFactura;
    }

    public Date getFechaFinalizado() {
        return fechaFinalizado;
    }

    public void setFechaFinalizado(Date fechaFinalizado) {
        this.fechaFinalizado = fechaFinalizado;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public Date getFechaRadicadoEps() {
        return fechaRadicadoEps;
    }

    public void setFechaRadicadoEps(Date fechaRadicadoEps) {
        this.fechaRadicadoEps = fechaRadicadoEps;
    }

    public Eps getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(Eps codigoEps) {
        this.codigoEps = codigoEps;
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

    public Usuario getCodigoUsuarioRadico() {
        return codigoUsuarioRadico;
    }

    public void setCodigoUsuarioRadico(Usuario codigoUsuarioRadico) {
        this.codigoUsuarioRadico = codigoUsuarioRadico;
    }

    public Usuario getRadicadaPor() {
        return radicadaPor;
    }

    public void setRadicadaPor(Usuario radicadaPor) {
        this.radicadaPor = radicadaPor;
    }

    public Usuario getCodigoUsuarioFinalizo() {
        return codigoUsuarioFinalizo;
    }

    public void setCodigoUsuarioFinalizo(Usuario codigoUsuarioFinalizo) {
        this.codigoUsuarioFinalizo = codigoUsuarioFinalizo;
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
    
    
    

    @XmlTransient
    public List<DetalleRadicadoSede> getDetalleRadicadoSedeList() {
        return detalleRadicadoSedeList;
    }

    public void setDetalleRadicadoSedeList(List<DetalleRadicadoSede> detalleRadicadoSedeList) {
        this.detalleRadicadoSedeList = detalleRadicadoSedeList;
    }

    @XmlTransient
    public List<GlosaEps> getGlosaEpsList() {
        return glosaEpsList;
    }

    public void setGlosaEpsList(List<GlosaEps> glosaEpsList) {
        this.glosaEpsList = glosaEpsList;
    }

    @XmlTransient
    public List<PagosEps> getPagosEpsList() {
        return pagosEpsList;
    }

    public void setPagosEpsList(List<PagosEps> pagosEpsList) {
        this.pagosEpsList = pagosEpsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (consecutivoRacadoEps != null ? consecutivoRacadoEps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListaRadicadoEps)) {
            return false;
        }
        ListaRadicadoEps other = (ListaRadicadoEps) object;
        if ((this.consecutivoRacadoEps == null && other.consecutivoRacadoEps != null) || (this.consecutivoRacadoEps != null && !this.consecutivoRacadoEps.equals(other.consecutivoRacadoEps))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoEps + " ";
    }

}
