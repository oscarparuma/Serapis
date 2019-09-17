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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tipoidentificacion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoidentificacion.findAll", query = "SELECT t FROM Tipoidentificacion t")
    , @NamedQuery(name = "Tipoidentificacion.findByCodigoIdentificacion", query = "SELECT t FROM Tipoidentificacion t WHERE t.codigoIdentificacion = :codigoIdentificacion")
    , @NamedQuery(name = "Tipoidentificacion.findByNombreIdentificacion", query = "SELECT t FROM Tipoidentificacion t WHERE t.nombreIdentificacion = :nombreIdentificacion")
    , @NamedQuery(name = "Tipoidentificacion.findByActivo", query = "SELECT t FROM Tipoidentificacion t WHERE t.activo = :activo")
    , @NamedQuery(name = "Tipoidentificacion.findByFechaRegistro", query = "SELECT t FROM Tipoidentificacion t WHERE t.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Tipoidentificacion.findByFechaModificacion", query = "SELECT t FROM Tipoidentificacion t WHERE t.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Tipoidentificacion.findByClasificacionIdentificacion", query = "SELECT t FROM Tipoidentificacion t WHERE t.clasificacionIdentificacion = :clasificacionIdentificacion")})
public class Tipoidentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoIdentificacion")
    private Integer codigoIdentificacion;
    @Size(max = 100)
    @Column(name = "nombreIdentificacion")
    private String nombreIdentificacion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 10)
    @Column(name = "clasificacionIdentificacion")
    private String clasificacionIdentificacion;
    @OneToMany(mappedBy = "codigoIdentificacion")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoDocumentoIdentidad")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
     @OneToMany(mappedBy = "codigoTipoIdentificacionSede")
    private List<Sede> sedeList;

    public Tipoidentificacion() {
    }

    public Tipoidentificacion(Integer codigoIdentificacion) {
        this.codigoIdentificacion = codigoIdentificacion;
    }

    public Integer getCodigoIdentificacion() {
        return codigoIdentificacion;
    }

    public void setCodigoIdentificacion(Integer codigoIdentificacion) {
        this.codigoIdentificacion = codigoIdentificacion;
    }

    public String getNombreIdentificacion() {
        return nombreIdentificacion;
    }

    public void setNombreIdentificacion(String nombreIdentificacion) {
        this.nombreIdentificacion = nombreIdentificacion;
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

    public String getClasificacionIdentificacion() {
        return clasificacionIdentificacion;
    }

    public void setClasificacionIdentificacion(String clasificacionIdentificacion) {
        this.clasificacionIdentificacion = clasificacionIdentificacion;
    }

    public List<Sede> getSedeList() {
        return sedeList;
    }

    public void setSedeList(List<Sede> sedeList) {
        this.sedeList = sedeList;
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

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getCodigoUsuarioModifico() {
        return codigoUsuarioModifico;
    }

    public void setCodigoUsuarioModifico(Usuario codigoUsuarioModifico) {
        this.codigoUsuarioModifico = codigoUsuarioModifico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoIdentificacion != null ? codigoIdentificacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoidentificacion)) {
            return false;
        }
        Tipoidentificacion other = (Tipoidentificacion) object;
        if ((this.codigoIdentificacion == null && other.codigoIdentificacion != null) || (this.codigoIdentificacion != null && !this.codigoIdentificacion.equals(other.codigoIdentificacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return nombreIdentificacion;
    }

}
