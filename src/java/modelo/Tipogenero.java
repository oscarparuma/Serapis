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
@Table(name = "tipogenero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipogenero.findAll", query = "SELECT t FROM Tipogenero t")
    , @NamedQuery(name = "Tipogenero.findByCodigoGenero", query = "SELECT t FROM Tipogenero t WHERE t.codigoGenero = :codigoGenero")
    , @NamedQuery(name = "Tipogenero.findByNombreGenero", query = "SELECT t FROM Tipogenero t WHERE t.nombreGenero = :nombreGenero")
    , @NamedQuery(name = "Tipogenero.findByActivo", query = "SELECT t FROM Tipogenero t WHERE t.activo = :activo")})
public class Tipogenero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "codigoGenero")
    private String codigoGenero;
    @Size(max = 50)
    @Column(name = "nombreGenero")
    private String nombreGenero;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "genero")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "genero")
    private List<Datospersona> datospersonaList;

    public Tipogenero() {
    }

    public Tipogenero(String codigoGenero) {
        this.codigoGenero = codigoGenero;
    }

    public String getCodigoGenero() {
        return codigoGenero;
    }

    public void setCodigoGenero(String codigoGenero) {
        this.codigoGenero = codigoGenero;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
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
        hash += (codigoGenero != null ? codigoGenero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipogenero)) {
            return false;
        }
        Tipogenero other = (Tipogenero) object;
        if ((this.codigoGenero == null && other.codigoGenero != null) || (this.codigoGenero != null && !this.codigoGenero.equals(other.codigoGenero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreGenero;
    }
    
}
