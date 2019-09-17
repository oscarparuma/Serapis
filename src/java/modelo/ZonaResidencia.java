/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "ZonaResidencia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ZonaResidencia.findAll", query = "SELECT z FROM ZonaResidencia z")
    , @NamedQuery(name = "ZonaResidencia.findByCodigoZona", query = "SELECT z FROM ZonaResidencia z WHERE z.codigoZona = :codigoZona")
    , @NamedQuery(name = "ZonaResidencia.findByNombre", query = "SELECT z FROM ZonaResidencia z WHERE z.nombre = :nombre")
    , @NamedQuery(name = "ZonaResidencia.findByActivo", query = "SELECT z FROM ZonaResidencia z WHERE z.activo = :activo")})
public class ZonaResidencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigoZona")
    private String codigoZona;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "codigoZonaResidencia")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoZonaResidencia")
    private List<Datospersona> datospersonaList;

    public ZonaResidencia() {
    }

    public ZonaResidencia(String codigoZona) {
        this.codigoZona = codigoZona;
    }

    public String getCodigoZona() {
        return codigoZona;
    }

    public void setCodigoZona(String codigoZona) {
        this.codigoZona = codigoZona;
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

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList() {
        return datospersonaList;
    }

    public void setDatospersonaList(List<Datospersona> datospersonaList) {
        this.datospersonaList = datospersonaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoZona != null ? codigoZona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZonaResidencia)) {
            return false;
        }
        ZonaResidencia other = (ZonaResidencia) object;
        if ((this.codigoZona == null && other.codigoZona != null) || (this.codigoZona != null && !this.codigoZona.equals(other.codigoZona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
