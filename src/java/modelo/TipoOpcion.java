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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "TipoOpcion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoOpcion.findAll", query = "SELECT t FROM TipoOpcion t")
    , @NamedQuery(name = "TipoOpcion.findByCodigoOpcion", query = "SELECT t FROM TipoOpcion t WHERE t.codigoOpcion = :codigoOpcion")
    , @NamedQuery(name = "TipoOpcion.findByNombreOpcion", query = "SELECT t FROM TipoOpcion t WHERE t.nombreOpcion = :nombreOpcion")
    , @NamedQuery(name = "TipoOpcion.findByActivo", query = "SELECT t FROM TipoOpcion t WHERE t.activo = :activo")})
public class TipoOpcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoOpcion")
    private Integer codigoOpcion;
    @Size(max = 150)
    @Column(name = "NombreOpcion")
    private String nombreOpcion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @JoinTable(name = "OpcionPerfil", joinColumns = {
        @JoinColumn(name = "codigoOpcion", referencedColumnName = "codigoOpcion")}, inverseJoinColumns = {
        @JoinColumn(name = "codigoPerfil", referencedColumnName = "codigoPerfil")})
    @ManyToMany
    private List<Tipoperfil> tipoperfilList;

    public TipoOpcion() {
    }

    public TipoOpcion(Integer codigoOpcion) {
        this.codigoOpcion = codigoOpcion;
    }

    public Integer getCodigoOpcion() {
        return codigoOpcion;
    }

    public void setCodigoOpcion(Integer codigoOpcion) {
        this.codigoOpcion = codigoOpcion;
    }

    public String getNombreOpcion() {
        return nombreOpcion;
    }

    public void setNombreOpcion(String nombreOpcion) {
        this.nombreOpcion = nombreOpcion;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Tipoperfil> getTipoperfilList() {
        return tipoperfilList;
    }

    public void setTipoperfilList(List<Tipoperfil> tipoperfilList) {
        this.tipoperfilList = tipoperfilList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoOpcion != null ? codigoOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoOpcion)) {
            return false;
        }
        TipoOpcion other = (TipoOpcion) object;
        if ((this.codigoOpcion == null && other.codigoOpcion != null) || (this.codigoOpcion != null && !this.codigoOpcion.equals(other.codigoOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.TipoOpcion[ codigoOpcion=" + codigoOpcion + " ]";
    }
    
}
