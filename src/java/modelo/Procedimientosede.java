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
@Table(name = "procedimientosede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Procedimientosede.findAll", query = "SELECT p FROM Procedimientosede p")
    , @NamedQuery(name = "Procedimientosede.findByCodigoValorConsulta", query = "SELECT p FROM Procedimientosede p WHERE p.codigoValorConsulta = :codigoValorConsulta")
    , @NamedQuery(name = "Procedimientosede.findByValorExamen", query = "SELECT p FROM Procedimientosede p WHERE p.valorExamen = :valorExamen")
    , @NamedQuery(name = "Procedimientosede.findByFechaRegistro", query = "SELECT p FROM Procedimientosede p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Procedimientosede.findByFechaModificacion", query = "SELECT p FROM Procedimientosede p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Procedimientosede.findByActivo", query = "SELECT p FROM Procedimientosede p WHERE p.activo = :activo")
    , @NamedQuery(name = "Procedimientosede.findByCodigoRips", query = "SELECT p FROM Procedimientosede p WHERE p.codigoRips = :codigoRips")})
public class Procedimientosede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoValorConsulta")
    private Long codigoValorConsulta;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valorExamen")
    private BigDecimal valorExamen;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Size(max = 50)
    @Column(name = "codigoRips")
    private String codigoRips;
    @OneToMany(mappedBy = "codigoProcedimientoSede")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "codigoExamen")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoProcedimintoSede")
    private List<ValorExamenSede> valorExamenSedeList;
    @JoinColumn(name = "codigoClasificacionManual", referencedColumnName = "codigoClasificacionManual")
    @ManyToOne
    private ClasificacionManual codigoClasificacionManual;
    @JoinColumn(name = "codigoEps", referencedColumnName = "codigoEps")
    @ManyToOne
    private Eps codigoEps;
    @JoinColumn(name = "codigoCups", referencedColumnName = "codigoCups")
    @ManyToOne
    private Manualtarifas codigoCups;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoManualTarifario", referencedColumnName = "codigoManual")
    @ManyToOne
    private TipoManual codigoManualTarifario;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoProcedimintoSede")
    private List<CodigoRips> codigoRipsList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoProcedimintoSede")
    private List<ModificarExamen> modificarExamenList;
    @JoinColumn(name = "codigoArchivoRips", referencedColumnName = "codigoArchivoRips")
    @ManyToOne
    private TipoArchivoRips codigoArchivoRips;

    public Procedimientosede() {
    }

    public Procedimientosede(Long codigoValorConsulta) {
        this.codigoValorConsulta = codigoValorConsulta;
    }

    public Long getCodigoValorConsulta() {
        return codigoValorConsulta;
    }

    public void setCodigoValorConsulta(Long codigoValorConsulta) {
        this.codigoValorConsulta = codigoValorConsulta;
    }

    public BigDecimal getValorExamen() {
        return valorExamen;
    }

    public void setValorExamen(BigDecimal valorExamen) {
        this.valorExamen = valorExamen;
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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getCodigoRips() {
        return codigoRips;
    }

    public void setCodigoRips(String codigoRips) {
        this.codigoRips = codigoRips;
    }

    public TipoArchivoRips getCodigoArchivoRips() {
        return codigoArchivoRips;
    }

    public void setCodigoArchivoRips(TipoArchivoRips codigoArchivoRips) {
        this.codigoArchivoRips = codigoArchivoRips;
    }

    
    
    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    @XmlTransient
    public List<ValorExamenSede> getValorExamenSedeList() {
        return valorExamenSedeList;
    }

    public void setValorExamenSedeList(List<ValorExamenSede> valorExamenSedeList) {
        this.valorExamenSedeList = valorExamenSedeList;
    }

    public ClasificacionManual getCodigoClasificacionManual() {
        return codigoClasificacionManual;
    }

    public void setCodigoClasificacionManual(ClasificacionManual codigoClasificacionManual) {
        this.codigoClasificacionManual = codigoClasificacionManual;
    }

    public Eps getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(Eps codigoEps) {
        this.codigoEps = codigoEps;
    }

    public Manualtarifas getCodigoCups() {
        return codigoCups;
    }

    public void setCodigoCups(Manualtarifas codigoCups) {
        this.codigoCups = codigoCups;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public TipoManual getCodigoManualTarifario() {
        return codigoManualTarifario;
    }

    public void setCodigoManualTarifario(TipoManual codigoManualTarifario) {
        this.codigoManualTarifario = codigoManualTarifario;
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

    @XmlTransient
    public List<CodigoRips> getCodigoRipsList() {
        return codigoRipsList;
    }

    public void setCodigoRipsList(List<CodigoRips> codigoRipsList) {
        this.codigoRipsList = codigoRipsList;
    }

    @XmlTransient
    public List<ModificarExamen> getModificarExamenList() {
        return modificarExamenList;
    }

    public void setModificarExamenList(List<ModificarExamen> modificarExamenList) {
        this.modificarExamenList = modificarExamenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoValorConsulta != null ? codigoValorConsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procedimientosede)) {
            return false;
        }
        Procedimientosede other = (Procedimientosede) object;
        if ((this.codigoValorConsulta == null && other.codigoValorConsulta != null) || (this.codigoValorConsulta != null && !this.codigoValorConsulta.equals(other.codigoValorConsulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoCups + " ";
    }

}
