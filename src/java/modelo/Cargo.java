/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "cargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargo.findAll", query = "SELECT c FROM Cargo c")
    , @NamedQuery(name = "Cargo.findByCodigoCargo", query = "SELECT c FROM Cargo c WHERE c.codigoCargo = :codigoCargo")
    , @NamedQuery(name = "Cargo.findByNombre", query = "SELECT c FROM Cargo c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cargo.findByActivo", query = "SELECT c FROM Cargo c WHERE c.activo = :activo")
    , @NamedQuery(name = "Cargo.findByFechaRegistro", query = "SELECT c FROM Cargo c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Cargo.findByFechaModificacion", query = "SELECT c FROM Cargo c WHERE c.fechaModificacion = :fechaModificacion")})
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigoCargo")
    private String codigoCargo;
    @Size(max = 250)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "codigousuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigousuario;
    @JoinColumn(name = "codigousuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigousuarioModifico;

    public Cargo() {
    }

    public Cargo(String codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(String codigoCargo) {
        this.codigoCargo = codigoCargo;
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

    public Usuario getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(Usuario codigousuario) {
        this.codigousuario = codigousuario;
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
        hash += (codigoCargo != null ? codigoCargo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cargo)) {
            return false;
        }
        Cargo other = (Cargo) object;
        if ((this.codigoCargo == null && other.codigoCargo != null) || (this.codigoCargo != null && !this.codigoCargo.equals(other.codigoCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cargo[ codigoCargo=" + codigoCargo + " ]";
    }
    
}
