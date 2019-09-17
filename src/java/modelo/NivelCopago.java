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
@Table(name = "nivelCopago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelCopago.findAll", query = "SELECT n FROM NivelCopago n")
    , @NamedQuery(name = "NivelCopago.findByCodigoNivel", query = "SELECT n FROM NivelCopago n WHERE n.codigoNivel = :codigoNivel")
    , @NamedQuery(name = "NivelCopago.findByNombre", query = "SELECT n FROM NivelCopago n WHERE n.nombre = :nombre")
    , @NamedQuery(name = "NivelCopago.findByActivo", query = "SELECT n FROM NivelCopago n WHERE n.activo = :activo")
    , @NamedQuery(name = "NivelCopago.findByFechaRegistro", query = "SELECT n FROM NivelCopago n WHERE n.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "NivelCopago.findByFechaModificacion", query = "SELECT n FROM NivelCopago n WHERE n.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "NivelCopago.findByPorcentajeIncremento", query = "SELECT n FROM NivelCopago n WHERE n.porcentajeIncremento = :porcentajeIncremento")
    , @NamedQuery(name = "NivelCopago.findByValorCopago", query = "SELECT n FROM NivelCopago n WHERE n.valorCopago = :valorCopago")
    , @NamedQuery(name = "NivelCopago.findByValorTotalCopago", query = "SELECT n FROM NivelCopago n WHERE n.valorTotalCopago = :valorTotalCopago")})
public class NivelCopago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "codigoNivel")
    private String codigoNivel;
    @Size(max = 10)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "porcentajeIncremento")
    private BigDecimal porcentajeIncremento;
    @Column(name = "valorCopago")
    private BigDecimal valorCopago;
    @Column(name = "valorTotalCopago")
    private BigDecimal valorTotalCopago;
    @OneToMany(mappedBy = "codigoNivelCopago")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "codigoNivelCopago")
    private List<Clasificacioncopago> clasificacioncopagoList;
    @JoinColumn(name = "tipoClasificacionEps", referencedColumnName = "codigoClasificacionEps")
    @ManyToOne
    private ClasificacionEps tipoClasificacionEps;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public NivelCopago() {
    }

    public NivelCopago(String codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public String getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(String codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
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

    public BigDecimal getPorcentajeIncremento() {
        return porcentajeIncremento;
    }

    public void setPorcentajeIncremento(BigDecimal porcentajeIncremento) {
        this.porcentajeIncremento = porcentajeIncremento;
    }

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public BigDecimal getValorTotalCopago() {
        return valorTotalCopago;
    }

    public void setValorTotalCopago(BigDecimal valorTotalCopago) {
        this.valorTotalCopago = valorTotalCopago;
    }

    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @XmlTransient
    public List<Clasificacioncopago> getClasificacioncopagoList() {
        return clasificacioncopagoList;
    }

    public void setClasificacioncopagoList(List<Clasificacioncopago> clasificacioncopagoList) {
        this.clasificacioncopagoList = clasificacioncopagoList;
    }

    public ClasificacionEps getTipoClasificacionEps() {
        return tipoClasificacionEps;
    }

    public void setTipoClasificacionEps(ClasificacionEps tipoClasificacionEps) {
        this.tipoClasificacionEps = tipoClasificacionEps;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoNivel != null ? codigoNivel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelCopago)) {
            return false;
        }
        NivelCopago other = (NivelCopago) object;
        if ((this.codigoNivel == null && other.codigoNivel != null) || (this.codigoNivel != null && !this.codigoNivel.equals(other.codigoNivel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoNivel;
    }
    
}
