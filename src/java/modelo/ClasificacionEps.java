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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "ClasificacionEps")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasificacionEps.findAll", query = "SELECT c FROM ClasificacionEps c")
    , @NamedQuery(name = "ClasificacionEps.findByCodigoClasificacionEps", query = "SELECT c FROM ClasificacionEps c WHERE c.codigoClasificacionEps = :codigoClasificacionEps")
    , @NamedQuery(name = "ClasificacionEps.findByNombre", query = "SELECT c FROM ClasificacionEps c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "ClasificacionEps.findByActivo", query = "SELECT c FROM ClasificacionEps c WHERE c.activo = :activo")
    , @NamedQuery(name = "ClasificacionEps.findByFechaRegistro", query = "SELECT c FROM ClasificacionEps c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ClasificacionEps.findByFechaModificacion", query = "SELECT c FROM ClasificacionEps c WHERE c.fechaModificacion = :fechaModificacion")})
public class ClasificacionEps implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoClasificacionEps")
    private Integer codigoClasificacionEps;
    @Size(max = 50)
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
    @OneToMany(mappedBy = "clasificacionEps")
    private List<Clasificacioncopago> clasificacioncopagoList;
    @OneToMany(mappedBy = "clasificacionEps")
    private List<Citapersona> citapersonaList;
    @JoinColumn(name = "codigoPais", referencedColumnName = "codigoPais")
    @ManyToOne
    private Pais codigoPais;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "clasificacionEps")
    private List<Datospersona> datospersonaList;
    @OneToMany(mappedBy = "clasificacionEps")
    private List<Eps> epsList;
    @OneToMany(mappedBy = "tipoClasificacionEps")
    private List<NivelCopago> nivelCopagoList;
    @OneToMany(mappedBy = "codigoClasificacionEps")
    private List<EpsSede> epsSedeList;

    public ClasificacionEps() {
    }

    public ClasificacionEps(Integer codigoClasificacionEps) {
        this.codigoClasificacionEps = codigoClasificacionEps;
    }

    public Integer getCodigoClasificacionEps() {
        return codigoClasificacionEps;
    }

    public void setCodigoClasificacionEps(Integer codigoClasificacionEps) {
        this.codigoClasificacionEps = codigoClasificacionEps;
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

    @XmlTransient
    public List<Clasificacioncopago> getClasificacioncopagoList() {
        return clasificacioncopagoList;
    }

    public void setClasificacioncopagoList(List<Clasificacioncopago> clasificacioncopagoList) {
        this.clasificacioncopagoList = clasificacioncopagoList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
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
    public List<NivelCopago> getNivelCopagoList() {
        return nivelCopagoList;
    }

    public void setNivelCopagoList(List<NivelCopago> nivelCopagoList) {
        this.nivelCopagoList = nivelCopagoList;
    }

    @XmlTransient
    public List<EpsSede> getEpsSedeList() {
        return epsSedeList;
    }

    public void setEpsSedeList(List<EpsSede> epsSedeList) {
        this.epsSedeList = epsSedeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoClasificacionEps != null ? codigoClasificacionEps.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClasificacionEps)) {
            return false;
        }
        ClasificacionEps other = (ClasificacionEps) object;
        if ((this.codigoClasificacionEps == null && other.codigoClasificacionEps != null) || (this.codigoClasificacionEps != null && !this.codigoClasificacionEps.equals(other.codigoClasificacionEps))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
