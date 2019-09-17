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
@Table(name = "municipio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Municipio.findAll", query = "SELECT m FROM Municipio m")
    , @NamedQuery(name = "Municipio.findByCodigoMunicipio", query = "SELECT m FROM Municipio m WHERE m.codigoMunicipio = :codigoMunicipio")
    , @NamedQuery(name = "Municipio.findByNombreMunicipio", query = "SELECT m FROM Municipio m WHERE m.nombreMunicipio = :nombreMunicipio")
    , @NamedQuery(name = "Municipio.findByActivo", query = "SELECT m FROM Municipio m WHERE m.activo = :activo")
    , @NamedQuery(name = "Municipio.findByFechaRegistro", query = "SELECT m FROM Municipio m WHERE m.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Municipio.findByFechaModificacion", query = "SELECT m FROM Municipio m WHERE m.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Municipio.findByCodigoMunicipioRips", query = "SELECT m FROM Municipio m WHERE m.codigoMunicipioRips = :codigoMunicipioRips")})
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigoMunicipio")
    private String codigoMunicipio;
    @Size(max = 150)
    @Column(name = "nombreMunicipio")
    private String nombreMunicipio;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 15)
    @Column(name = "codigoMunicipioRips")
    private String codigoMunicipioRips;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Pensiones> pensionesList;
    @OneToMany(mappedBy = "codigoMunicipioNacimiento")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoMunicipioResidencia")
    private List<Citapersona> citapersonaList1;
    @OneToMany(mappedBy = "codigoMunicipioExpedicion")
    private List<Citapersona> citapersonaList2;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Area> areaList;
    @JoinColumn(name = "codigoDepartamento", referencedColumnName = "codigoDepartamento")
    @ManyToOne
    private Departamento codigoDepartamento;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "codigoMunicipioResidencia")
    private List<Datospersona> datospersonaList;
    @OneToMany(mappedBy = "codigoMunicipioNacimiento")
    private List<Datospersona> datospersonaList1;
    @OneToMany(mappedBy = "codigoMunicipioExpedicion")
    private List<Datospersona> datospersonaList2;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Eps> epsList;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Empresa> empresaList;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Sucursalempresa> sucursalempresaList;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Arl> arlList;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Localidad> localidadList;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Barrio> barrioList;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Sede> sedeList;
    @OneToMany(mappedBy = "codigoMunicipio")
    private List<Dependencia> dependenciaList;

    public Municipio() {
    }

    public Municipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
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

    public String getCodigoMunicipioRips() {
        return codigoMunicipioRips;
    }

    public void setCodigoMunicipioRips(String codigoMunicipioRips) {
        this.codigoMunicipioRips = codigoMunicipioRips;
    }

    @XmlTransient
    public List<Pensiones> getPensionesList() {
        return pensionesList;
    }

    public void setPensionesList(List<Pensiones> pensionesList) {
        this.pensionesList = pensionesList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList1() {
        return citapersonaList1;
    }

    public void setCitapersonaList1(List<Citapersona> citapersonaList1) {
        this.citapersonaList1 = citapersonaList1;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList2() {
        return citapersonaList2;
    }

    public void setCitapersonaList2(List<Citapersona> citapersonaList2) {
        this.citapersonaList2 = citapersonaList2;
    }

    @XmlTransient
    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public Departamento getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Departamento codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getCodigoUsuarioModifico() {
        return codigoUsuarioModifico;
    }

    public void setCodigoUsuarioModifico(Usuario codigoUsuarioModifico) {
        this.codigoUsuarioModifico = codigoUsuarioModifico;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList() {
        return datospersonaList;
    }

    public void setDatospersonaList(List<Datospersona> datospersonaList) {
        this.datospersonaList = datospersonaList;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList1() {
        return datospersonaList1;
    }

    public void setDatospersonaList1(List<Datospersona> datospersonaList1) {
        this.datospersonaList1 = datospersonaList1;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList2() {
        return datospersonaList2;
    }

    public void setDatospersonaList2(List<Datospersona> datospersonaList2) {
        this.datospersonaList2 = datospersonaList2;
    }

    @XmlTransient
    public List<Eps> getEpsList() {
        return epsList;
    }

    public void setEpsList(List<Eps> epsList) {
        this.epsList = epsList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<Empresa> getEmpresaList() {
        return empresaList;
    }

    public void setEmpresaList(List<Empresa> empresaList) {
        this.empresaList = empresaList;
    }

    @XmlTransient
    public List<Sucursalempresa> getSucursalempresaList() {
        return sucursalempresaList;
    }

    public void setSucursalempresaList(List<Sucursalempresa> sucursalempresaList) {
        this.sucursalempresaList = sucursalempresaList;
    }

    @XmlTransient
    public List<Arl> getArlList() {
        return arlList;
    }

    public void setArlList(List<Arl> arlList) {
        this.arlList = arlList;
    }

    @XmlTransient
    public List<Localidad> getLocalidadList() {
        return localidadList;
    }

    public void setLocalidadList(List<Localidad> localidadList) {
        this.localidadList = localidadList;
    }

    @XmlTransient
    public List<Barrio> getBarrioList() {
        return barrioList;
    }

    public void setBarrioList(List<Barrio> barrioList) {
        this.barrioList = barrioList;
    }

    @XmlTransient
    public List<Sede> getSedeList() {
        return sedeList;
    }

    public void setSedeList(List<Sede> sedeList) {
        this.sedeList = sedeList;
    }

    @XmlTransient
    public List<Dependencia> getDependenciaList() {
        return dependenciaList;
    }

    public void setDependenciaList(List<Dependencia> dependenciaList) {
        this.dependenciaList = dependenciaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMunicipio != null ? codigoMunicipio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.codigoMunicipio == null && other.codigoMunicipio != null) || (this.codigoMunicipio != null && !this.codigoMunicipio.equals(other.codigoMunicipio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreMunicipio;
    }

}
