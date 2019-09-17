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
@Table(name = "facturacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facturacion.findAll", query = "SELECT f FROM Facturacion f")
    , @NamedQuery(name = "Facturacion.findByCodigoFactura", query = "SELECT f FROM Facturacion f WHERE f.codigoFactura = :codigoFactura")
    , @NamedQuery(name = "Facturacion.findByNumeroDocumento", query = "SELECT f FROM Facturacion f WHERE f.numeroDocumento = :numeroDocumento")
    , @NamedQuery(name = "Facturacion.findByIngresoPersona", query = "SELECT f FROM Facturacion f WHERE f.ingresoPersona = :ingresoPersona")
    , @NamedQuery(name = "Facturacion.findByFechaRegistro", query = "SELECT f FROM Facturacion f WHERE f.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Facturacion.findByFechaModificacion", query = "SELECT f FROM Facturacion f WHERE f.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Facturacion.findByValorUnitario", query = "SELECT f FROM Facturacion f WHERE f.valorUnitario = :valorUnitario")
    , @NamedQuery(name = "Facturacion.findByCantidad", query = "SELECT f FROM Facturacion f WHERE f.cantidad = :cantidad")
    , @NamedQuery(name = "Facturacion.findByValorParcial", query = "SELECT f FROM Facturacion f WHERE f.valorParcial = :valorParcial")
    , @NamedQuery(name = "Facturacion.findByValorCopago", query = "SELECT f FROM Facturacion f WHERE f.valorCopago = :valorCopago")
    , @NamedQuery(name = "Facturacion.findByValorTotalFactura", query = "SELECT f FROM Facturacion f WHERE f.valorTotalFactura = :valorTotalFactura")
    , @NamedQuery(name = "Facturacion.findByValorCacelado", query = "SELECT f FROM Facturacion f WHERE f.valorCacelado = :valorCacelado")
    , @NamedQuery(name = "Facturacion.findBySaldoActual", query = "SELECT f FROM Facturacion f WHERE f.saldoActual = :saldoActual")
    , @NamedQuery(name = "Facturacion.findByEstadoFactura", query = "SELECT f FROM Facturacion f WHERE f.estadoFactura = :estadoFactura")
    , @NamedQuery(name = "Facturacion.findByCcsFacturaArea", query = "SELECT f FROM Facturacion f WHERE f.ccsFacturaArea = :ccsFacturaArea")
    , @NamedQuery(name = "Facturacion.findByCcsFactura", query = "SELECT f FROM Facturacion f WHERE f.ccsFactura = :ccsFactura")
    , @NamedQuery(name = "Facturacion.findByCcsFacturaDependnecia", query = "SELECT f FROM Facturacion f WHERE f.ccsFacturaDependnecia = :ccsFacturaDependnecia")
    , @NamedQuery(name = "Facturacion.findByFacturaSede", query = "SELECT f FROM Facturacion f WHERE f.facturaSede = :facturaSede")
    , @NamedQuery(name = "Facturacion.findByFacturaArea", query = "SELECT f FROM Facturacion f WHERE f.facturaArea = :facturaArea")
    , @NamedQuery(name = "Facturacion.findByFacturaDependnecia", query = "SELECT f FROM Facturacion f WHERE f.facturaDependnecia = :facturaDependnecia")
    , @NamedQuery(name = "Facturacion.findBySaldoFactura", query = "SELECT f FROM Facturacion f WHERE f.saldoFactura = :saldoFactura")
    , @NamedQuery(name = "Facturacion.findByCodigoFacturaCartera", query = "SELECT f FROM Facturacion f WHERE f.codigoFacturaCartera = :codigoFacturaCartera")
    , @NamedQuery(name = "Facturacion.findBySaldoFactura", query = "SELECT f FROM Facturacion f WHERE f.saldoFactura = :saldoFactura")
    , @NamedQuery(name = "Facturacion.findByCodigoFacturaCartera", query = "SELECT f FROM Facturacion f WHERE f.codigoFacturaCartera = :codigoFacturaCartera")
    , @NamedQuery(name = "Facturacion.findByCopagoFactura", query = "SELECT f FROM Facturacion f WHERE f.copagoFactura = :copagoFactura")
    , @NamedQuery(name = "Facturacion.findByCopagoFactura", query = "SELECT f FROM Facturacion f WHERE f.copagoFactura = :copagoFactura")})
public class Facturacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoFactura")
    private Long codigoFactura;
    @Size(max = 50)
    @Column(name = "numeroDocumento")
    private String numeroDocumento;
    @Column(name = "ingresoPersona")
    private Integer ingresoPersona;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorUnitario")
    private BigDecimal valorUnitario;
    @Column(name = "cantidad")
    private Short cantidad;
    @Column(name = "valorParcial")
    private BigDecimal valorParcial;
    @Column(name = "valorCopago")
    private BigDecimal valorCopago;
    @Column(name = "valorTotalFactura")
    private BigDecimal valorTotalFactura;
    @Lob
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "valorCacelado")
    private BigDecimal valorCacelado;
    @Column(name = "saldoActual")
    private BigDecimal saldoActual;
    @Size(max = 1)
    @Column(name = "estadoFactura")
    private String estadoFactura;
    @Column(name = "ccsFacturaArea")
    private Long ccsFacturaArea;
    @Column(name = "ccsFactura")
    private Long ccsFactura;
    @Column(name = "ccsFacturaDependnecia")
    private Long ccsFacturaDependnecia;
    @Size(max = 250)
    @Column(name = "facturaSede")
    private String facturaSede;
    @Size(max = 250)
    @Column(name = "facturaArea")
    private String facturaArea;
    @Size(max = 250)
    @Column(name = "facturaDependnecia")
    private String facturaDependnecia;
    @Column(name = "saldoFactura")
    private BigDecimal saldoFactura;
    @Column(name = "codigoFacturaCartera")
    private Long codigoFacturaCartera;
    @Size(max = 1)
    @Column(name = "copagoFactura")
    private String copagoFactura;
    @JoinColumn(name = "codigoCitaPersona", referencedColumnName = "codigoCitaPersona")
    @ManyToOne(cascade = CascadeType.ALL)
    private Citapersona codigoCitaPersona;
    @JoinColumn(name = "codigoArea", referencedColumnName = "codigoArea")
    @ManyToOne
    private Area codigoArea;
    @JoinColumn(name = "codigoClasificacionCopago", referencedColumnName = "codigoCopago")
    @ManyToOne
    private Clasificacioncopago codigoClasificacionCopago;
    @JoinColumn(name = "codigoNoFacturable", referencedColumnName = "codigoNoFacturable")
    @ManyToOne
    private Tiponofacturable codigoNoFacturable;
    @JoinColumn(name = "usuarioAutoriza", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioAutoriza;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "codigoDependencia", referencedColumnName = "codigoDependencia")
    @ManyToOne
    private Dependencia codigoDependencia;
    @JoinColumn(name = "codigoEps", referencedColumnName = "codigoEps")
    @ManyToOne
    private Eps codigoEps;
    @JoinColumn(name = "facturableSN", referencedColumnName = "codigoFacturableSN")
    @ManyToOne
    private Tipofacturable facturableSN;
    @JoinColumn(name = "codigoEpsSede", referencedColumnName = "codigoEpsSede")
    @ManyToOne
    private EpsSede codigoEpsSede;
    @JoinColumn(name = "codigoEstadoFactura", referencedColumnName = "codigoEstadoFactura")
    @ManyToOne
    private Estadofactura codigoEstadoFactura;
    @JoinColumn(name = "codigoNivelCopago", referencedColumnName = "codigoNivel")
    @ManyToOne
    private NivelCopago codigoNivelCopago;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @JoinColumn(name = "codigoProcedimientoSede", referencedColumnName = "codigoValorConsulta")
    @ManyToOne
    private Procedimientosede codigoProcedimientoSede;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @OneToMany(mappedBy = "codigoFacturacion")
    private List<CarteraSede> carteraSedeList;
    @OneToMany(mappedBy = "codigoFactura")
    private List<FacturaRadicacion> facturaRadicacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoFactura")
    private List<DetalleRadicadoSede> detalleRadicadoSedeList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoFactura")
    private List<RadicacionFacturas> radicacionFacturasList;
    @OneToMany(mappedBy = "codigoFactura")
    private List<GlosaEps> glosaEpsList;
    @OneToMany(mappedBy = "codigoFactura")
    private List<PagosEps> pagosEpsList;
    @Column(name = "valorCanceladoEps")
    private BigDecimal valorCanceladoEps;
    @Column(name = "valorAceptadoEps")
    private BigDecimal valorAceptadoEps;
    @Column(name = "saldoTotal")
    private BigDecimal saldoTotal;

    public Facturacion() {
    }

    public Facturacion(Long codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public Long getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(Long codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Integer getIngresoPersona() {
        return ingresoPersona;
    }

    public void setIngresoPersona(Integer ingresoPersona) {
        this.ingresoPersona = ingresoPersona;
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

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Short getCantidad() {
        return cantidad;
    }

    public void setCantidad(Short cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getValorParcial() {
        return valorParcial;
    }

    public void setValorParcial(BigDecimal valorParcial) {
        this.valorParcial = valorParcial;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorTotalFactura() {
        return valorTotalFactura;
    }

    public void setValorTotalFactura(BigDecimal valorTotalFactura) {
        this.valorTotalFactura = valorTotalFactura;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public BigDecimal getValorCacelado() {
        return valorCacelado;
    }

    public void setValorCacelado(BigDecimal valorCacelado) {
        this.valorCacelado = valorCacelado;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getEstadoFactura() {
        return estadoFactura;
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public Long getCcsFacturaArea() {
        return ccsFacturaArea;
    }

    public void setCcsFacturaArea(Long ccsFacturaArea) {
        this.ccsFacturaArea = ccsFacturaArea;
    }

    public Long getCcsFactura() {
        return ccsFactura;
    }

    public void setCcsFactura(Long ccsFactura) {
        this.ccsFactura = ccsFactura;
    }

    public Long getCcsFacturaDependnecia() {
        return ccsFacturaDependnecia;
    }

    public void setCcsFacturaDependnecia(Long ccsFacturaDependnecia) {
        this.ccsFacturaDependnecia = ccsFacturaDependnecia;
    }

    public String getFacturaSede() {
        return facturaSede;
    }

    public void setFacturaSede(String facturaSede) {
        this.facturaSede = facturaSede;
    }

    public String getFacturaArea() {
        return facturaArea;
    }

    public void setFacturaArea(String facturaArea) {
        this.facturaArea = facturaArea;
    }

    public String getFacturaDependnecia() {
        return facturaDependnecia;
    }

    public void setFacturaDependnecia(String facturaDependnecia) {
        this.facturaDependnecia = facturaDependnecia;
    }

    public BigDecimal getSaldoFactura() {
        return saldoFactura;
    }

    public void setSaldoFactura(BigDecimal saldoFactura) {
        this.saldoFactura = saldoFactura;
    }

    public Long getCodigoFacturaCartera() {
        return codigoFacturaCartera;
    }

    public void setCodigoFacturaCartera(Long codigoFacturaCartera) {
        this.codigoFacturaCartera = codigoFacturaCartera;
    }

    public String getCopagoFactura() {
        return copagoFactura;
    }

    public void setCopagoFactura(String copagoFactura) {
        this.copagoFactura = copagoFactura;
    }

    public Citapersona getCodigoCitaPersona() {
        return codigoCitaPersona;
    }

    public void setCodigoCitaPersona(Citapersona codigoCitaPersona) {
        this.codigoCitaPersona = codigoCitaPersona;
    }

    public Area getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Area codigoArea) {
        this.codigoArea = codigoArea;
    }

    public Clasificacioncopago getCodigoClasificacionCopago() {
        return codigoClasificacionCopago;
    }

    public void setCodigoClasificacionCopago(Clasificacioncopago codigoClasificacionCopago) {
        this.codigoClasificacionCopago = codigoClasificacionCopago;
    }

    public Tiponofacturable getCodigoNoFacturable() {
        return codigoNoFacturable;
    }

    public void setCodigoNoFacturable(Tiponofacturable codigoNoFacturable) {
        this.codigoNoFacturable = codigoNoFacturable;
    }

    public Usuario getUsuarioAutoriza() {
        return usuarioAutoriza;
    }

    public void setUsuarioAutoriza(Usuario usuarioAutoriza) {
        this.usuarioAutoriza = usuarioAutoriza;
    }

    public Datospersona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Datospersona codigoPersona) {
        this.codigoPersona = codigoPersona;
    }

    public Dependencia getCodigoDependencia() {
        return codigoDependencia;
    }

    public void setCodigoDependencia(Dependencia codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }

    public Eps getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(Eps codigoEps) {
        this.codigoEps = codigoEps;
    }

    public Tipofacturable getFacturableSN() {
        return facturableSN;
    }

    public void setFacturableSN(Tipofacturable facturableSN) {
        this.facturableSN = facturableSN;
    }

    public EpsSede getCodigoEpsSede() {
        return codigoEpsSede;
    }

    public void setCodigoEpsSede(EpsSede codigoEpsSede) {
        this.codigoEpsSede = codigoEpsSede;
    }

    public Estadofactura getCodigoEstadoFactura() {
        return codigoEstadoFactura;
    }

    public void setCodigoEstadoFactura(Estadofactura codigoEstadoFactura) {
        this.codigoEstadoFactura = codigoEstadoFactura;
    }

    public NivelCopago getCodigoNivelCopago() {
        return codigoNivelCopago;
    }

    public void setCodigoNivelCopago(NivelCopago codigoNivelCopago) {
        this.codigoNivelCopago = codigoNivelCopago;
    }

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    public Procedimientosede getCodigoProcedimientoSede() {
        return codigoProcedimientoSede;
    }

    public void setCodigoProcedimientoSede(Procedimientosede codigoProcedimientoSede) {
        this.codigoProcedimientoSede = codigoProcedimientoSede;
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
    
    
    
    

    @XmlTransient
    public List<CarteraSede> getCarteraSedeList() {
        return carteraSedeList;
    }

    public void setCarteraSedeList(List<CarteraSede> carteraSedeList) {
        this.carteraSedeList = carteraSedeList;
    }

    @XmlTransient
    public List<FacturaRadicacion> getFacturaRadicacionList() {
        return facturaRadicacionList;
    }

    public void setFacturaRadicacionList(List<FacturaRadicacion> facturaRadicacionList) {
        this.facturaRadicacionList = facturaRadicacionList;
    }

    @XmlTransient
    public List<DetalleRadicadoSede> getDetalleRadicadoSedeList() {
        return detalleRadicadoSedeList;
    }

    public void setDetalleRadicadoSedeList(List<DetalleRadicadoSede> detalleRadicadoSedeList) {
        this.detalleRadicadoSedeList = detalleRadicadoSedeList;
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
        hash += (codigoFactura != null ? codigoFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Facturacion)) {
            return false;
        }
        Facturacion other = (Facturacion) object;
        if ((this.codigoFactura == null && other.codigoFactura != null) || (this.codigoFactura != null && !this.codigoFactura.equals(other.codigoFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return facturaSede + " " + codigoEps;
    }

}
