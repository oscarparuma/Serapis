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
@Table(name = "tipoanestesia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoanestesia.findAll", query = "SELECT t FROM Tipoanestesia t")
    , @NamedQuery(name = "Tipoanestesia.findByCodigoAnestesia", query = "SELECT t FROM Tipoanestesia t WHERE t.codigoAnestesia = :codigoAnestesia")
    , @NamedQuery(name = "Tipoanestesia.findByNombre", query = "SELECT t FROM Tipoanestesia t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tipoanestesia.findByActivo", query = "SELECT t FROM Tipoanestesia t WHERE t.activo = :activo")
    , @NamedQuery(name = "Tipoanestesia.findByFechaRegistro", query = "SELECT t FROM Tipoanestesia t WHERE t.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Tipoanestesia.findByFechaModificacion", query = "SELECT t FROM Tipoanestesia t WHERE t.fechaModificacion = :fechaModificacion")})
public class Tipoanestesia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoAnestesia")
    private Integer codigoAnestesia;
    @Size(max = 50)
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
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "codigoAnestesia")
    private List<Procedimiento> procedimientoList;

    public Tipoanestesia() {
    }

    public Tipoanestesia(Integer codigoAnestesia) {
        this.codigoAnestesia = codigoAnestesia;
    }

    public Integer getCodigoAnestesia() {
        return codigoAnestesia;
    }

    public void setCodigoAnestesia(Integer codigoAnestesia) {
        this.codigoAnestesia = codigoAnestesia;
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

    @XmlTransient
    public List<Procedimiento> getProcedimientoList() {
        return procedimientoList;
    }

    public void setProcedimientoList(List<Procedimiento> procedimientoList) {
        this.procedimientoList = procedimientoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoAnestesia != null ? codigoAnestesia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoanestesia)) {
            return false;
        }
        Tipoanestesia other = (Tipoanestesia) object;
        if ((this.codigoAnestesia == null && other.codigoAnestesia != null) || (this.codigoAnestesia != null && !this.codigoAnestesia.equals(other.codigoAnestesia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
