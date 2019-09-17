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
@Table(name = "TipoUsuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUsuario.findAll", query = "SELECT t FROM TipoUsuario t")
    , @NamedQuery(name = "TipoUsuario.findByCodigoTipoUsuario", query = "SELECT t FROM TipoUsuario t WHERE t.codigoTipoUsuario = :codigoTipoUsuario")
    , @NamedQuery(name = "TipoUsuario.findByNombre", query = "SELECT t FROM TipoUsuario t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoUsuario.findByActivo", query = "SELECT t FROM TipoUsuario t WHERE t.activo = :activo")})
public class TipoUsuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigoTipoUsuario")
    private String codigoTipoUsuario;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "codigoTipoUsuario")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoTipoUsuario")
    private List<Datospersona> datospersonaList;

    public TipoUsuario() {
    }

    public TipoUsuario(String codigoTipoUsuario) {
        this.codigoTipoUsuario = codigoTipoUsuario;
    }

    public String getCodigoTipoUsuario() {
        return codigoTipoUsuario;
    }

    public void setCodigoTipoUsuario(String codigoTipoUsuario) {
        this.codigoTipoUsuario = codigoTipoUsuario;
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
        hash += (codigoTipoUsuario != null ? codigoTipoUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUsuario)) {
            return false;
        }
        TipoUsuario other = (TipoUsuario) object;
        if ((this.codigoTipoUsuario == null && other.codigoTipoUsuario != null) || (this.codigoTipoUsuario != null && !this.codigoTipoUsuario.equals(other.codigoTipoUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
