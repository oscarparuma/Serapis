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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "cargopersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargopersona.findAll", query = "SELECT c FROM Cargopersona c")
    , @NamedQuery(name = "Cargopersona.findByCodigoCargoPersona", query = "SELECT c FROM Cargopersona c WHERE c.codigoCargoPersona = :codigoCargoPersona")
    , @NamedQuery(name = "Cargopersona.findByNombreCargo", query = "SELECT c FROM Cargopersona c WHERE c.nombreCargo = :nombreCargo")
    , @NamedQuery(name = "Cargopersona.findByFechaRegistro", query = "SELECT c FROM Cargopersona c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Cargopersona.findByFechaModificacion", query = "SELECT c FROM Cargopersona c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Cargopersona.findByActivo", query = "SELECT c FROM Cargopersona c WHERE c.activo = :activo")})
public class Cargopersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoCargoPersona")
    private Integer codigoCargoPersona;
    @Size(max = 250)
    @Column(name = "nombreCargo")
    private String nombreCargo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "codigoCargo")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoCargo")
    private List<Datospersona> datospersonaList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;

    public Cargopersona() {
    }

    public Cargopersona(Integer codigoCargoPersona) {
        this.codigoCargoPersona = codigoCargoPersona;
    }

    public Integer getCodigoCargoPersona() {
        return codigoCargoPersona;
    }

    public void setCodigoCargoPersona(Integer codigoCargoPersona) {
        this.codigoCargoPersona = codigoCargoPersona;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
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

    public Usuario getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(Usuario usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCargoPersona != null ? codigoCargoPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargopersona)) {
            return false;
        }
        Cargopersona other = (Cargopersona) object;
        if ((this.codigoCargoPersona == null && other.codigoCargoPersona != null) || (this.codigoCargoPersona != null && !this.codigoCargoPersona.equals(other.codigoCargoPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreCargo;
    }

}
