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
@Table(name = "tiponofacturable")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tiponofacturable.findAll", query = "SELECT t FROM Tiponofacturable t")
    , @NamedQuery(name = "Tiponofacturable.findByCodigoNoFacturable", query = "SELECT t FROM Tiponofacturable t WHERE t.codigoNoFacturable = :codigoNoFacturable")
    , @NamedQuery(name = "Tiponofacturable.findByNombre", query = "SELECT t FROM Tiponofacturable t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tiponofacturable.findByFechaRegistro", query = "SELECT t FROM Tiponofacturable t WHERE t.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Tiponofacturable.findByFechaModificacion", query = "SELECT t FROM Tiponofacturable t WHERE t.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Tiponofacturable.findByActivo", query = "SELECT t FROM Tiponofacturable t WHERE t.activo = :activo")})
public class Tiponofacturable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoNoFacturable")
    private Integer codigoNoFacturable;
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
    @OneToMany(mappedBy = "codigoNoFacturable")
    private List<Facturacion> facturacionList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public Tiponofacturable() {
    }

    public Tiponofacturable(Integer codigoNoFacturable) {
        this.codigoNoFacturable = codigoNoFacturable;
    }

    public Integer getCodigoNoFacturable() {
        return codigoNoFacturable;
    }

    public void setCodigoNoFacturable(Integer codigoNoFacturable) {
        this.codigoNoFacturable = codigoNoFacturable;
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
        hash += (codigoNoFacturable != null ? codigoNoFacturable.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tiponofacturable)) {
            return false;
        }
        Tiponofacturable other = (Tiponofacturable) object;
        if ((this.codigoNoFacturable == null && other.codigoNoFacturable != null) || (this.codigoNoFacturable != null && !this.codigoNoFacturable.equals(other.codigoNoFacturable))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tiponofacturable[ codigoNoFacturable=" + codigoNoFacturable + " ]";
    }
    
}
