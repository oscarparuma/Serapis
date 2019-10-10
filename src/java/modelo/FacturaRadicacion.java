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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "FacturaRadicacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FacturaRadicacion.findAll", query = "SELECT f FROM FacturaRadicacion f")
    , @NamedQuery(name = "FacturaRadicacion.findByCodigoFacturaRadicacion", query = "SELECT f FROM FacturaRadicacion f WHERE f.codigoFacturaRadicacion = :codigoFacturaRadicacion")
    , @NamedQuery(name = "FacturaRadicacion.findByValorUnitario", query = "SELECT f FROM FacturaRadicacion f WHERE f.valorUnitario = :valorUnitario")
    , @NamedQuery(name = "FacturaRadicacion.findByValorCopago", query = "SELECT f FROM FacturaRadicacion f WHERE f.valorCopago = :valorCopago")
    , @NamedQuery(name = "FacturaRadicacion.findByValorFacturado", query = "SELECT f FROM FacturaRadicacion f WHERE f.valorFacturado = :valorFacturado")
    , @NamedQuery(name = "FacturaRadicacion.findByTotalRadicado", query = "SELECT f FROM FacturaRadicacion f WHERE f.totalRadicado = :totalRadicado")
    , @NamedQuery(name = "FacturaRadicacion.findByEstadoRadicacion", query = "SELECT f FROM FacturaRadicacion f WHERE f.estadoRadicacion = :estadoRadicacion")
    , @NamedQuery(name = "FacturaRadicacion.findByFechaRegistro", query = "SELECT f FROM FacturaRadicacion f WHERE f.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "FacturaRadicacion.findByFechaRadicacion", query = "SELECT f FROM FacturaRadicacion f WHERE f.fechaRadicacion = :fechaRadicacion")
    , @NamedQuery(name = "FacturaRadicacion.findByCcsRadicacion", query = "SELECT f FROM FacturaRadicacion f WHERE f.ccsRadicacion = :ccsRadicacion")
    , @NamedQuery(name = "FacturaRadicacion.findByRadicacionSede", query = "SELECT f FROM FacturaRadicacion f WHERE f.radicacionSede = :radicacionSede")
    , @NamedQuery(name = "FacturaRadicacion.findByFacturaSede", query = "SELECT f FROM FacturaRadicacion f WHERE f.facturaSede = :facturaSede")})
public class FacturaRadicacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoFacturaRadicacion")
    private Long codigoFacturaRadicacion;
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
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "UsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;
    @OneToMany(mappedBy = "codigoRadicacionFactura")
    private List<RadicacionFacturas> radicacionFacturasList;

    public FacturaRadicacion() {
    }

    public FacturaRadicacion(Long codigoFacturaRadicacion) {
        this.codigoFacturaRadicacion = codigoFacturaRadicacion;
    }

    public Long getCodigoFacturaRadicacion() {
        return codigoFacturaRadicacion;
    }

    public void setCodigoFacturaRadicacion(Long codigoFacturaRadicacion) {
        this.codigoFacturaRadicacion = codigoFacturaRadicacion;
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

    public Usuario getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(Usuario usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    @XmlTransient
    public List<RadicacionFacturas> getRadicacionFacturasList() {
        return radicacionFacturasList;
    }

    public void setRadicacionFacturasList(List<RadicacionFacturas> radicacionFacturasList) {
        this.radicacionFacturasList = radicacionFacturasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoFacturaRadicacion != null ? codigoFacturaRadicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaRadicacion)) {
            return false;
        }
        FacturaRadicacion other = (FacturaRadicacion) object;
        if ((this.codigoFacturaRadicacion == null && other.codigoFacturaRadicacion != null) || (this.codigoFacturaRadicacion != null && !this.codigoFacturaRadicacion.equals(other.codigoFacturaRadicacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoFacturaRadicacion + " ";
    }

}
