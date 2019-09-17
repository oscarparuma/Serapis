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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "clasificacioncopago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clasificacioncopago.findAll", query = "SELECT c FROM Clasificacioncopago c")
    , @NamedQuery(name = "Clasificacioncopago.findByCodigoCopago", query = "SELECT c FROM Clasificacioncopago c WHERE c.codigoCopago = :codigoCopago")
    , @NamedQuery(name = "Clasificacioncopago.findByActivo", query = "SELECT c FROM Clasificacioncopago c WHERE c.activo = :activo")
    , @NamedQuery(name = "Clasificacioncopago.findByFechaRegistro", query = "SELECT c FROM Clasificacioncopago c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Clasificacioncopago.findByValorCopago", query = "SELECT c FROM Clasificacioncopago c WHERE c.valorCopago = :valorCopago")
    , @NamedQuery(name = "Clasificacioncopago.findByClasificacionCopago", query = "SELECT c FROM Clasificacioncopago c WHERE c.clasificacionCopago = :clasificacionCopago")
    , @NamedQuery(name = "Clasificacioncopago.findByTotalCopago", query = "SELECT c FROM Clasificacioncopago c WHERE c.totalCopago = :totalCopago")})
public class Clasificacioncopago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoCopago")
    private Integer codigoCopago;
    @Size(max = 2)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorCopago")
    private BigDecimal valorCopago;
    @Size(max = 3)
    @Column(name = "clasificacionCopago")
    private String clasificacionCopago;
    @Column(name = "totalCopago")
    private BigDecimal totalCopago;
    @OneToMany(mappedBy = "codigoClasificacionCopago")
    private List<Facturacion> facturacionList;
    @JoinColumn(name = "clasificacionEps", referencedColumnName = "codigoClasificacionEps")
    @ManyToOne
    private ClasificacionEps clasificacionEps;
    @JoinColumn(name = "codigoEps", referencedColumnName = "codigoEps")
    @ManyToOne
    private Eps codigoEps;
    @JoinColumn(name = "codigoNivelCopago", referencedColumnName = "codigoNivel")
    @ManyToOne
    private NivelCopago codigoNivelCopago;
    @JoinColumn(name = "tipoExamen", referencedColumnName = "codigo")
    @ManyToOne
    private Tipoexamen tipoExamen;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;

    public Clasificacioncopago() {
    }

    public Clasificacioncopago(Integer codigoCopago) {
        this.codigoCopago = codigoCopago;
    }

    public Integer getCodigoCopago() {
        return codigoCopago;
    }

    public void setCodigoCopago(Integer codigoCopago) {
        this.codigoCopago = codigoCopago;
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

    public BigDecimal getValorCopago() {
        return valorCopago;
    }

    public void setValorCopago(BigDecimal valorCopago) {
        this.valorCopago = valorCopago;
    }

    public String getClasificacionCopago() {
        return clasificacionCopago;
    }

    public void setClasificacionCopago(String clasificacionCopago) {
        this.clasificacionCopago = clasificacionCopago;
    }

    public BigDecimal getTotalCopago() {
        return totalCopago;
    }

    public void setTotalCopago(BigDecimal totalCopago) {
        this.totalCopago = totalCopago;
    }

    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    public ClasificacionEps getClasificacionEps() {
        return clasificacionEps;
    }

    public void setClasificacionEps(ClasificacionEps clasificacionEps) {
        this.clasificacionEps = clasificacionEps;
    }

    public Eps getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(Eps codigoEps) {
        this.codigoEps = codigoEps;
    }

    public NivelCopago getCodigoNivelCopago() {
        return codigoNivelCopago;
    }

    public void setCodigoNivelCopago(NivelCopago codigoNivelCopago) {
        this.codigoNivelCopago = codigoNivelCopago;
    }

    public Tipoexamen getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(Tipoexamen tipoExamen) {
        this.tipoExamen = tipoExamen;
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
        hash += (codigoCopago != null ? codigoCopago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificacioncopago)) {
            return false;
        }
        Clasificacioncopago other = (Clasificacioncopago) object;
        if ((this.codigoCopago == null && other.codigoCopago != null) || (this.codigoCopago != null && !this.codigoCopago.equals(other.codigoCopago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return valorCopago + " ";
    }

}
