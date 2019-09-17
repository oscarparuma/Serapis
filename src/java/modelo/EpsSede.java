/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
@Table(name = "EpsSede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EpsSede.findAll", query = "SELECT e FROM EpsSede e")
    , @NamedQuery(name = "EpsSede.findByCodigoEpsSede", query = "SELECT e FROM EpsSede e WHERE e.codigoEpsSede = :codigoEpsSede")
    , @NamedQuery(name = "EpsSede.findByIdentificacionEps", query = "SELECT e FROM EpsSede e WHERE e.identificacionEps = :identificacionEps")
    , @NamedQuery(name = "EpsSede.findByCodigoAsignado", query = "SELECT e FROM EpsSede e WHERE e.codigoAsignado = :codigoAsignado")
    , @NamedQuery(name = "EpsSede.findByCodigoMunicipio", query = "SELECT e FROM EpsSede e WHERE e.codigoMunicipio = :codigoMunicipio")
    , @NamedQuery(name = "EpsSede.findByCodigoUsuario", query = "SELECT e FROM EpsSede e WHERE e.codigoUsuario = :codigoUsuario")
    , @NamedQuery(name = "EpsSede.findByEstado", query = "SELECT e FROM EpsSede e WHERE e.estado = :estado")
    , @NamedQuery(name = "EpsSede.findByFechaRegistro", query = "SELECT e FROM EpsSede e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "EpsSede.findByFechaModificacion", query = "SELECT e FROM EpsSede e WHERE e.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "EpsSede.findByNombreEps", query = "SELECT e FROM EpsSede e WHERE e.nombreEps = :nombreEps")
    , @NamedQuery(name = "EpsSede.findByCodigoDatosRegistroNit", query = "SELECT e FROM EpsSede e WHERE e.codigoDatosRegistroNit = :codigoDatosRegistroNit")
    , @NamedQuery(name = "EpsSede.findByEstadoRadicado", query = "SELECT e FROM EpsSede e WHERE e.estadoRadicado = :estadoRadicado")})
public class EpsSede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoEpsSede")
    private Long codigoEpsSede;
    @Size(max = 150)
    @Column(name = "identificacionEps")
    private String identificacionEps;
    @Size(max = 50)
    @Column(name = "codigoAsignado")
    private String codigoAsignado;
    @Size(max = 20)
    @Column(name = "codigoMunicipio")
    private String codigoMunicipio;
    @Column(name = "codigoUsuario")
    private Long codigoUsuario;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 250)
    @Column(name = "nombreEps")
    private String nombreEps;
    @Size(max = 50)
    @Column(name = "codigoDatosRegistroNit")
    private String codigoDatosRegistroNit;
    @Size(max = 1)
    @Column(name = "estadoRadicado")
    private String estadoRadicado;
    @OneToMany(mappedBy = "codigoEpsSede")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "codigoEpsSede")
    private List<Eps> epsList;
    @JoinColumn(name = "codigoClasificacionEps", referencedColumnName = "codigoClasificacionEps")
    @ManyToOne
    private ClasificacionEps codigoClasificacionEps;
    @JoinColumn(name = "codigoPais", referencedColumnName = "codigoPais")
    @ManyToOne
    private Pais codigoPais;
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;

    public EpsSede() {
    }

    public EpsSede(Long codigoEpsSede) {
        this.codigoEpsSede = codigoEpsSede;
    }

    public Long getCodigoEpsSede() {
        return codigoEpsSede;
    }

    public void setCodigoEpsSede(Long codigoEpsSede) {
        this.codigoEpsSede = codigoEpsSede;
    }

    public String getIdentificacionEps() {
        return identificacionEps;
    }

    public void setIdentificacionEps(String identificacionEps) {
        this.identificacionEps = identificacionEps;
    }

    public String getCodigoAsignado() {
        return codigoAsignado;
    }

    public void setCodigoAsignado(String codigoAsignado) {
        this.codigoAsignado = codigoAsignado;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getNombreEps() {
        return nombreEps;
    }

    public void setNombreEps(String nombreEps) {
        this.nombreEps = nombreEps;
    }

    public String getCodigoDatosRegistroNit() {
        return codigoDatosRegistroNit;
    }

    public void setCodigoDatosRegistroNit(String codigoDatosRegistroNit) {
        this.codigoDatosRegistroNit = codigoDatosRegistroNit;
    }

    public String getEstadoRadicado() {
        return estadoRadicado;
    }

    public void setEstadoRadicado(String estadoRadicado) {
        this.estadoRadicado = estadoRadicado;
    }

    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @XmlTransient
    public List<Eps> getEpsList() {
        return epsList;
    }

    public void setEpsList(List<Eps> epsList) {
        this.epsList = epsList;
    }

    public ClasificacionEps getCodigoClasificacionEps() {
        return codigoClasificacionEps;
    }

    public void setCodigoClasificacionEps(ClasificacionEps codigoClasificacionEps) {
        this.codigoClasificacionEps = codigoClasificacionEps;
    }

    public Pais getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Pais codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Usuario getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(Usuario usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEpsSede != null ? codigoEpsSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EpsSede)) {
            return false;
        }
        EpsSede other = (EpsSede) object;
        if ((this.codigoEpsSede == null && other.codigoEpsSede != null) || (this.codigoEpsSede != null && !this.codigoEpsSede.equals(other.codigoEpsSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreEps;
    }

}
