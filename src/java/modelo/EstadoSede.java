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
@Table(name = "EstadoSede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoSede.findAll", query = "SELECT e FROM EstadoSede e")
    , @NamedQuery(name = "EstadoSede.findByCodigoEstadoSede", query = "SELECT e FROM EstadoSede e WHERE e.codigoEstadoSede = :codigoEstadoSede")
    , @NamedQuery(name = "EstadoSede.findByNombreEstadoSede", query = "SELECT e FROM EstadoSede e WHERE e.nombreEstadoSede = :nombreEstadoSede")
    , @NamedQuery(name = "EstadoSede.findByEstado", query = "SELECT e FROM EstadoSede e WHERE e.estado = :estado")})
public class EstadoSede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoEstadoSede")
    private Integer codigoEstadoSede;
    @Size(max = 50)
    @Column(name = "nombreEstadoSede")
    private String nombreEstadoSede;
    @Size(max = 1)
    @Column(name = "estado")
    private String estado;
    @OneToMany(mappedBy = "codigoEstadoSede")
    private List<Sede> sedeList;
    @OneToMany(mappedBy = "codigoRegistro")
    private List<Sede> sedeList1;

    public EstadoSede() {
    }

    public EstadoSede(Integer codigoEstadoSede) {
        this.codigoEstadoSede = codigoEstadoSede;
    }

    public Integer getCodigoEstadoSede() {
        return codigoEstadoSede;
    }

    public void setCodigoEstadoSede(Integer codigoEstadoSede) {
        this.codigoEstadoSede = codigoEstadoSede;
    }

    public String getNombreEstadoSede() {
        return nombreEstadoSede;
    }

    public void setNombreEstadoSede(String nombreEstadoSede) {
        this.nombreEstadoSede = nombreEstadoSede;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public List<Sede> getSedeList() {
        return sedeList;
    }

    public void setSedeList(List<Sede> sedeList) {
        this.sedeList = sedeList;
    }

    @XmlTransient
    public List<Sede> getSedeList1() {
        return sedeList1;
    }

    public void setSedeList1(List<Sede> sedeList1) {
        this.sedeList1 = sedeList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEstadoSede != null ? codigoEstadoSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoSede)) {
            return false;
        }
        EstadoSede other = (EstadoSede) object;
        if ((this.codigoEstadoSede == null && other.codigoEstadoSede != null) || (this.codigoEstadoSede != null && !this.codigoEstadoSede.equals(other.codigoEstadoSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.EstadoSede[ codigoEstadoSede=" + codigoEstadoSede + " ]";
    }
    
}
