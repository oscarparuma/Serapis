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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "eps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eps.findAll", query = "SELECT e FROM Eps e")
    , @NamedQuery(name = "Eps.findByCodigoEps", query = "SELECT e FROM Eps e WHERE e.codigoEps = :codigoEps")
    , @NamedQuery(name = "Eps.findByIdentificacionEps", query = "SELECT e FROM Eps e WHERE e.identificacionEps = :identificacionEps")
    , @NamedQuery(name = "Eps.findByNombreEps", query = "SELECT e FROM Eps e WHERE e.nombreEps = :nombreEps")
    , @NamedQuery(name = "Eps.findByDireccion", query = "SELECT e FROM Eps e WHERE e.direccion = :direccion")
    , @NamedQuery(name = "Eps.findByTelefono", query = "SELECT e FROM Eps e WHERE e.telefono = :telefono")
    , @NamedQuery(name = "Eps.findByFechaRegistro", query = "SELECT e FROM Eps e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Eps.findByFechaModificacion", query = "SELECT e FROM Eps e WHERE e.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Eps.findByCodigoAsignado", query = "SELECT e FROM Eps e WHERE e.codigoAsignado = :codigoAsignado")
    , @NamedQuery(name = "Eps.findByActivo", query = "SELECT e FROM Eps e WHERE e.activo = :activo")
    , @NamedQuery(name = "Eps.findByContacto", query = "SELECT e FROM Eps e WHERE e.contacto = :contacto")})
public class Eps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoEps")
    private Integer codigoEps;
    @Size(max = 150)
    @Column(name = "identificacionEps")
    private String identificacionEps;
    @Size(max = 350)
    @Column(name = "nombreEps")
    private String nombreEps;
    @Size(max = 250)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 100)
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 50)
    @Column(name = "codigoAsignado")
    private String codigoAsignado;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Size(max = 250)
    @Column(name = "contacto")
    private String contacto;
    @OneToMany(mappedBy = "codigoEps")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "codigoEps")
    private List<CarteraSede> carteraSedeList;
    @OneToMany(mappedBy = "codigoEps")
    private List<Clasificacioncopago> clasificacioncopagoList;
    @OneToMany(mappedBy = "codigoEps")
    private List<FacturaRadicacion> facturaRadicacionList;
    @OneToMany(mappedBy = "codigoEps")
    private List<Citapersona> citapersonaList;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "codigoEps")
    private List<Procedimientosede> procedimientosedeList;
    @OneToMany(mappedBy = "codigoEps")
    private List<ListaRadicadoEps> listaRadicadoEpsList;
    @OneToMany(mappedBy = "codigoEps")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "clasificacionEps", referencedColumnName = "codigoClasificacionEps")
    @ManyToOne
    private ClasificacionEps clasificacionEps;
    @JoinColumn(name = "codigoDepartamento", referencedColumnName = "codigoDepartamento")
    @ManyToOne
    private Departamento codigoDepartamento;
    @JoinColumn(name = "codigoEpsSede", referencedColumnName = "codigoEpsSede")
    @ManyToOne
    private EpsSede codigoEpsSede;
    @JoinColumn(name = "codigoMunicipio", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipio;
    @JoinColumn(name = "codigoPais", referencedColumnName = "codigoPais")
    @ManyToOne
    private Pais codigoPais;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;
    @OneToMany(mappedBy = "codigoEps")
    private List<DetalleRadicadoSede> detalleRadicadoSedeList;
    @OneToMany(mappedBy = "codigoEps")
    private List<RadicacionFacturas> radicacionFacturasList;
    @OneToMany(mappedBy = "codigoEps")
    private List<GlosaEps> glosaEpsList;
    @OneToMany(mappedBy = "codigoEps")
    private List<PagosEps> pagosEpsList;

    public Eps() {
    }

    public Eps(Integer codigoEps) {
        this.codigoEps = codigoEps;
    }

    public Integer getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(Integer codigoEps) {
        this.codigoEps = codigoEps;
    }

    public String getIdentificacionEps() {
        return identificacionEps;
    }

    public void setIdentificacionEps(String identificacionEps) {
        this.identificacionEps = identificacionEps;
    }

    public String getNombreEps() {
        return nombreEps;
    }

    public void setNombreEps(String nombreEps) {
        this.nombreEps = nombreEps;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getCodigoAsignado() {
        return codigoAsignado;
    }

    public void setCodigoAsignado(String codigoAsignado) {
        this.codigoAsignado = codigoAsignado;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
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
    
    
    

    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @XmlTransient
    public List<CarteraSede> getCarteraSedeList() {
        return carteraSedeList;
    }

    public void setCarteraSedeList(List<CarteraSede> carteraSedeList) {
        this.carteraSedeList = carteraSedeList;
    }

    @XmlTransient
    public List<Clasificacioncopago> getClasificacioncopagoList() {
        return clasificacioncopagoList;
    }

    public void setClasificacioncopagoList(List<Clasificacioncopago> clasificacioncopagoList) {
        this.clasificacioncopagoList = clasificacioncopagoList;
    }

    @XmlTransient
    public List<FacturaRadicacion> getFacturaRadicacionList() {
        return facturaRadicacionList;
    }

    public void setFacturaRadicacionList(List<FacturaRadicacion> facturaRadicacionList) {
        this.facturaRadicacionList = facturaRadicacionList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    @XmlTransient
    public List<Procedimientosede> getProcedimientosedeList() {
        return procedimientosedeList;
    }

    public void setProcedimientosedeList(List<Procedimientosede> procedimientosedeList) {
        this.procedimientosedeList = procedimientosedeList;
    }

    @XmlTransient
    public List<ListaRadicadoEps> getListaRadicadoEpsList() {
        return listaRadicadoEpsList;
    }

    public void setListaRadicadoEpsList(List<ListaRadicadoEps> listaRadicadoEpsList) {
        this.listaRadicadoEpsList = listaRadicadoEpsList;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList() {
        return datospersonaList;
    }

    public void setDatospersonaList(List<Datospersona> datospersonaList) {
        this.datospersonaList = datospersonaList;
    }

    public ClasificacionEps getClasificacionEps() {
        return clasificacionEps;
    }

    public void setClasificacionEps(ClasificacionEps clasificacionEps) {
        this.clasificacionEps = clasificacionEps;
    }

    public Departamento getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Departamento codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public EpsSede getCodigoEpsSede() {
        return codigoEpsSede;
    }

    public void setCodigoEpsSede(EpsSede codigoEpsSede) {
        this.codigoEpsSede = codigoEpsSede;
    }

    public Municipio getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipio codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Pais getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Pais codigoPais) {
        this.codigoPais = codigoPais;
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
        hash += (codigoEps != null ? codigoEps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eps)) {
            return false;
        }
        Eps other = (Eps) object;
        if ((this.codigoEps == null && other.codigoEps != null) || (this.codigoEps != null && !this.codigoEps.equals(other.codigoEps))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoEpsSede + " ";
    }

}
