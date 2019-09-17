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
@Table(name = "tipofacturable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipofacturable.findAll", query = "SELECT t FROM Tipofacturable t")
    , @NamedQuery(name = "Tipofacturable.findByCodigoFacturableSN", query = "SELECT t FROM Tipofacturable t WHERE t.codigoFacturableSN = :codigoFacturableSN")
    , @NamedQuery(name = "Tipofacturable.findByActivo", query = "SELECT t FROM Tipofacturable t WHERE t.activo = :activo")
    , @NamedQuery(name = "Tipofacturable.findByFechaRegistro", query = "SELECT t FROM Tipofacturable t WHERE t.fechaRegistro = :fechaRegistro")})
public class Tipofacturable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "codigoFacturableSN")
    private String codigoFacturableSN;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToMany(mappedBy = "facturableSN")
    private List<Facturacion> facturacionList;

    public Tipofacturable() {
    }

    public Tipofacturable(String codigoFacturableSN) {
        this.codigoFacturableSN = codigoFacturableSN;
    }

    public String getCodigoFacturableSN() {
        return codigoFacturableSN;
    }

    public void setCodigoFacturableSN(String codigoFacturableSN) {
        this.codigoFacturableSN = codigoFacturableSN;
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

    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoFacturableSN != null ? codigoFacturableSN.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipofacturable)) {
            return false;
        }
        Tipofacturable other = (Tipofacturable) object;
        if ((this.codigoFacturableSN == null && other.codigoFacturableSN != null) || (this.codigoFacturableSN != null && !this.codigoFacturableSN.equals(other.codigoFacturableSN))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoFacturableSN;
    }
    
}
