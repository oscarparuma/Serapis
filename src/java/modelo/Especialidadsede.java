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
@Table(name = "especialidadsede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Especialidadsede.findAll", query = "SELECT e FROM Especialidadsede e")
    , @NamedQuery(name = "Especialidadsede.findByCodigoEspecialidad", query = "SELECT e FROM Especialidadsede e WHERE e.codigoEspecialidad = :codigoEspecialidad")
    , @NamedQuery(name = "Especialidadsede.findByNombreEspecialidad", query = "SELECT e FROM Especialidadsede e WHERE e.nombreEspecialidad = :nombreEspecialidad")
    , @NamedQuery(name = "Especialidadsede.findByActivo", query = "SELECT e FROM Especialidadsede e WHERE e.activo = :activo")
    , @NamedQuery(name = "Especialidadsede.findByFechaRegistro", query = "SELECT e FROM Especialidadsede e WHERE e.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Especialidadsede.findByFechaModificacion", query = "SELECT e FROM Especialidadsede e WHERE e.fechaModificacion = :fechaModificacion")})
public class Especialidadsede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoEspecialidad")
    private Integer codigoEspecialidad;
    @Size(max = 100)
    @Column(name = "nombreEspecialidad")
    private String nombreEspecialidad;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigousUarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigousUarioModifico;
    @OneToMany(mappedBy = "codigoEspecialidad")
    private List<Usuario> usuarioList;

    public Especialidadsede() {
    }

    public Especialidadsede(Integer codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    public Integer getCodigoEspecialidad() {
        return codigoEspecialidad;
    }

    public void setCodigoEspecialidad(Integer codigoEspecialidad) {
        this.codigoEspecialidad = codigoEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
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

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getCodigousUarioModifico() {
        return codigousUarioModifico;
    }

    public void setCodigousUarioModifico(Usuario codigousUarioModifico) {
        this.codigousUarioModifico = codigousUarioModifico;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoEspecialidad != null ? codigoEspecialidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Especialidadsede)) {
            return false;
        }
        Especialidadsede other = (Especialidadsede) object;
        if ((this.codigoEspecialidad == null && other.codigoEspecialidad != null) || (this.codigoEspecialidad != null && !this.codigoEspecialidad.equals(other.codigoEspecialidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       return nombreEspecialidad;
    }

}
