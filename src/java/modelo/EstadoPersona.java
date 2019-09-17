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
@Table(name = "EstadoPersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoPersona.findAll", query = "SELECT e FROM EstadoPersona e")
    , @NamedQuery(name = "EstadoPersona.findByCodigoEstadoPersona", query = "SELECT e FROM EstadoPersona e WHERE e.codigoEstadoPersona = :codigoEstadoPersona")
    , @NamedQuery(name = "EstadoPersona.findByNombreEstadoPersona", query = "SELECT e FROM EstadoPersona e WHERE e.nombreEstadoPersona = :nombreEstadoPersona")
    , @NamedQuery(name = "EstadoPersona.findByEstado", query = "SELECT e FROM EstadoPersona e WHERE e.estado = :estado")})
public class EstadoPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoEstadoPersona")
    private Integer codigoEstadoPersona;
    @Size(max = 50)
    @Column(name = "nombreEstadoPersona")
    private String nombreEstadoPersona;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "estadoPersona")
    private List<Datospersona> datospersonaList;

    public EstadoPersona() {
    }

    public EstadoPersona(Integer codigoEstadoPersona) {
        this.codigoEstadoPersona = codigoEstadoPersona;
    }

    public Integer getCodigoEstadoPersona() {
        return codigoEstadoPersona;
    }

    public void setCodigoEstadoPersona(Integer codigoEstadoPersona) {
        this.codigoEstadoPersona = codigoEstadoPersona;
    }

    public String getNombreEstadoPersona() {
        return nombreEstadoPersona;
    }

    public void setNombreEstadoPersona(String nombreEstadoPersona) {
        this.nombreEstadoPersona = nombreEstadoPersona;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        hash += (codigoEstadoPersona != null ? codigoEstadoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoPersona)) {
            return false;
        }
        EstadoPersona other = (EstadoPersona) object;
        if ((this.codigoEstadoPersona == null && other.codigoEstadoPersona != null) || (this.codigoEstadoPersona != null && !this.codigoEstadoPersona.equals(other.codigoEstadoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EstadoPersona[ codigoEstadoPersona=" + codigoEstadoPersona + " ]";
    }
    
}
