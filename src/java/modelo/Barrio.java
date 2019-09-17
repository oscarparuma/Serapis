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
@Table(name = "barrio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barrio.findAll", query = "SELECT b FROM Barrio b")
    , @NamedQuery(name = "Barrio.findByCodigoBarrio", query = "SELECT b FROM Barrio b WHERE b.codigoBarrio = :codigoBarrio")
    , @NamedQuery(name = "Barrio.findByNombreBarrio", query = "SELECT b FROM Barrio b WHERE b.nombreBarrio = :nombreBarrio")
    , @NamedQuery(name = "Barrio.findByActivo", query = "SELECT b FROM Barrio b WHERE b.activo = :activo")
    , @NamedQuery(name = "Barrio.findByFechaRegistro", query = "SELECT b FROM Barrio b WHERE b.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Barrio.findByFechaModificacion", query = "SELECT b FROM Barrio b WHERE b.fechaModificacion = :fechaModificacion")})
public class Barrio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "codigoBarrio")
    private String codigoBarrio;
    @Size(max = 350)
    @Column(name = "nombreBarrio")
    private String nombreBarrio;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "codigoBarrio")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoBarrio")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoLocalidad", referencedColumnName = "codigoLocalidad")
    @ManyToOne
    private Localidad codigoLocalidad;
    @JoinColumn(name = "codigoMunicipio", referencedColumnName = "codigoMunicipio")
    @ManyToOne
    private Municipio codigoMunicipio;
    @JoinColumn(name = "codigoUpz", referencedColumnName = "codigoUpz")
    @ManyToOne
    private Upz codigoUpz;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigousuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigousuarioModifico;

    public Barrio() {
    }

    public Barrio(String codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public String getCodigoBarrio() {
        return codigoBarrio;
    }

    public void setCodigoBarrio(String codigoBarrio) {
        this.codigoBarrio = codigoBarrio;
    }

    public String getNombreBarrio() {
        return nombreBarrio;
    }

    public void setNombreBarrio(String nombreBarrio) {
        this.nombreBarrio = nombreBarrio;
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

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
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

    public Localidad getCodigoLocalidad() {
        return codigoLocalidad;
    }

    public void setCodigoLocalidad(Localidad codigoLocalidad) {
        this.codigoLocalidad = codigoLocalidad;
    }

    public Municipio getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipio codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public Upz getCodigoUpz() {
        return codigoUpz;
    }

    public void setCodigoUpz(Upz codigoUpz) {
        this.codigoUpz = codigoUpz;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getCodigousuarioModifico() {
        return codigousuarioModifico;
    }

    public void setCodigousuarioModifico(Usuario codigousuarioModifico) {
        this.codigousuarioModifico = codigousuarioModifico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoBarrio != null ? codigoBarrio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barrio)) {
            return false;
        }
        Barrio other = (Barrio) object;
        if ((this.codigoBarrio == null && other.codigoBarrio != null) || (this.codigoBarrio != null && !this.codigoBarrio.equals(other.codigoBarrio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Barrio[ codigoBarrio=" + codigoBarrio + " ]";
    }
    
}
