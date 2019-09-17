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
@Table(name = "estadofactura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadofactura.findAll", query = "SELECT e FROM Estadofactura e")
    , @NamedQuery(name = "Estadofactura.findByCodigoEstadoFactura", query = "SELECT e FROM Estadofactura e WHERE e.codigoEstadoFactura = :codigoEstadoFactura")
    , @NamedQuery(name = "Estadofactura.findByNombre", query = "SELECT e FROM Estadofactura e WHERE e.nombre = :nombre")
    , @NamedQuery(name = "Estadofactura.findByFechaRegistro", query = "SELECT e FROM Estadofactura e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Estadofactura.findByFechaModificacion", query = "SELECT e FROM Estadofactura e WHERE e.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Estadofactura.findByActivo", query = "SELECT e FROM Estadofactura e WHERE e.activo = :activo")})
public class Estadofactura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigoEstadoFactura")
    private String codigoEstadoFactura;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "codigoEstadoFactura")
    private List<Facturacion> facturacionList;
    @OneToMany(mappedBy = "codigoestadoFactura")
    private List<Citapersona> citapersonaList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public Estadofactura() {
    }

    public Estadofactura(String codigoEstadoFactura) {
        this.codigoEstadoFactura = codigoEstadoFactura;
    }

    public String getCodigoEstadoFactura() {
        return codigoEstadoFactura;
    }

    public void setCodigoEstadoFactura(String codigoEstadoFactura) {
        this.codigoEstadoFactura = codigoEstadoFactura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Facturacion> getFacturacionList() {
        return facturacionList;
    }

    public void setFacturacionList(List<Facturacion> facturacionList) {
        this.facturacionList = facturacionList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEstadoFactura != null ? codigoEstadoFactura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadofactura)) {
            return false;
        }
        Estadofactura other = (Estadofactura) object;
        if ((this.codigoEstadoFactura == null && other.codigoEstadoFactura != null) || (this.codigoEstadoFactura != null && !this.codigoEstadoFactura.equals(other.codigoEstadoFactura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Estadofactura[ codigoEstadoFactura=" + codigoEstadoFactura + " ]";
    }
    
}
