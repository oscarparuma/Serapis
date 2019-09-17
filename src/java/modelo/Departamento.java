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
@Table(name = "departamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
    , @NamedQuery(name = "Departamento.findByCodigoDepartamento", query = "SELECT d FROM Departamento d WHERE d.codigoDepartamento = :codigoDepartamento")
    , @NamedQuery(name = "Departamento.findByNombreDepartamento", query = "SELECT d FROM Departamento d WHERE d.nombreDepartamento = :nombreDepartamento")
    , @NamedQuery(name = "Departamento.findByActivo", query = "SELECT d FROM Departamento d WHERE d.activo = :activo")
    , @NamedQuery(name = "Departamento.findByFechaRegistro", query = "SELECT d FROM Departamento d WHERE d.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Departamento.findByFechaModificacion", query = "SELECT d FROM Departamento d WHERE d.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Departamento.findByCodigoDepartamentoRips", query = "SELECT d FROM Departamento d WHERE d.codigoDepartamentoRips = :codigoDepartamentoRips")})
public class Departamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "codigoDepartamento")
    private String codigoDepartamento;
    @Size(max = 200)
    @Column(name = "nombreDepartamento")
    private String nombreDepartamento;
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
    @Column(name = "codigoDepartamentoRips")
    private String codigoDepartamentoRips;
    @OneToMany(mappedBy = "codigoDepartamento")
    private List<Pensiones> pensionesList;
    @OneToMany(mappedBy = "codigoDepartamento")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoDepartamento")
    private List<Municipio> municipioList;
    @OneToMany(mappedBy = "codigoDepartamento")
    private List<Datospersona> datospersonaList;
    @OneToMany(mappedBy = "codigoDepartamento")
    private List<Eps> epsList;
    @OneToMany(mappedBy = "codigoDepartamento")
    private List<Empresa> empresaList;
    @OneToMany(mappedBy = "codigoDepartamento")
    private List<Sucursalempresa> sucursalempresaList;
    @OneToMany(mappedBy = "codigoDepartamento")
    private List<Arl> arlList;
    @JoinColumn(name = "codigoPais", referencedColumnName = "codigoPais")
    @ManyToOne
    private Pais codigoPais;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;

    public Departamento() {
    }

    public Departamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
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

    public String getCodigoDepartamentoRips() {
        return codigoDepartamentoRips;
    }

    public void setCodigoDepartamentoRips(String codigoDepartamentoRips) {
        this.codigoDepartamentoRips = codigoDepartamentoRips;
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
    public List<Municipio> getMunicipioList() {
        return municipioList;
    }

    public void setMunicipioList(List<Municipio> municipioList) {
        this.municipioList = municipioList;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList() {
        return datospersonaList;
    }

    public void setDatospersonaList(List<Datospersona> datospersonaList) {
        this.datospersonaList = datospersonaList;
    }

    @XmlTransient
    public List<Eps> getEpsList() {
        return epsList;
    }

    public void setEpsList(List<Eps> epsList) {
        this.epsList = epsList;
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

    public Pais getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Pais codigoPais) {
        this.codigoPais = codigoPais;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDepartamento != null ? codigoDepartamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departamento)) {
            return false;
        }
        Departamento other = (Departamento) object;
        if ((this.codigoDepartamento == null && other.codigoDepartamento != null) || (this.codigoDepartamento != null && !this.codigoDepartamento.equals(other.codigoDepartamento))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return nombreDepartamento;
    }

}
