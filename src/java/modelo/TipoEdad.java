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
@Table(name = "TipoEdad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEdad.findAll", query = "SELECT t FROM TipoEdad t")
    , @NamedQuery(name = "TipoEdad.findByCodigoTipoEdad", query = "SELECT t FROM TipoEdad t WHERE t.codigoTipoEdad = :codigoTipoEdad")
    , @NamedQuery(name = "TipoEdad.findByNombre", query = "SELECT t FROM TipoEdad t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoEdad.findByActivo", query = "SELECT t FROM TipoEdad t WHERE t.activo = :activo")})
public class TipoEdad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigoTipoEdad")
    private String codigoTipoEdad;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "codigoTipoEdad")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoTipoEdad")
    private List<Datospersona> datospersonaList;

    public TipoEdad() {
    }

    public TipoEdad(String codigoTipoEdad) {
        this.codigoTipoEdad = codigoTipoEdad;
    }

    public String getCodigoTipoEdad() {
        return codigoTipoEdad;
    }

    public void setCodigoTipoEdad(String codigoTipoEdad) {
        this.codigoTipoEdad = codigoTipoEdad;
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
        hash += (codigoTipoEdad != null ? codigoTipoEdad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEdad)) {
            return false;
        }
        TipoEdad other = (TipoEdad) object;
        if ((this.codigoTipoEdad == null && other.codigoTipoEdad != null) || (this.codigoTipoEdad != null && !this.codigoTipoEdad.equals(other.codigoTipoEdad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
