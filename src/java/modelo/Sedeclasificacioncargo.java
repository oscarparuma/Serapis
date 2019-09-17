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
@Table(name = "sedeclasificacioncargo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sedeclasificacioncargo.findAll", query = "SELECT s FROM Sedeclasificacioncargo s")
    , @NamedQuery(name = "Sedeclasificacioncargo.findByCodigoCargoSede", query = "SELECT s FROM Sedeclasificacioncargo s WHERE s.codigoCargoSede = :codigoCargoSede")
    , @NamedQuery(name = "Sedeclasificacioncargo.findByNombreClasificacionCargo", query = "SELECT s FROM Sedeclasificacioncargo s WHERE s.nombreClasificacionCargo = :nombreClasificacionCargo")
    , @NamedQuery(name = "Sedeclasificacioncargo.findByActivo", query = "SELECT s FROM Sedeclasificacioncargo s WHERE s.activo = :activo")
    , @NamedQuery(name = "Sedeclasificacioncargo.findByFechaRegistro", query = "SELECT s FROM Sedeclasificacioncargo s WHERE s.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Sedeclasificacioncargo.findByFechaModificacion", query = "SELECT s FROM Sedeclasificacioncargo s WHERE s.fechaModificacion = :fechaModificacion")})
public class Sedeclasificacioncargo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoCargoSede")
    private Integer codigoCargoSede;
    @Size(max = 250)
    @Column(name = "nombreClasificacionCargo")
    private String nombreClasificacionCargo;
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
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;
    @OneToMany(mappedBy = "codigoClasifcacionCargo")
    private List<Usuario> usuarioList;

    public Sedeclasificacioncargo() {
    }

    public Sedeclasificacioncargo(Integer codigoCargoSede) {
        this.codigoCargoSede = codigoCargoSede;
    }

    public Integer getCodigoCargoSede() {
        return codigoCargoSede;
    }

    public void setCodigoCargoSede(Integer codigoCargoSede) {
        this.codigoCargoSede = codigoCargoSede;
    }

    public String getNombreClasificacionCargo() {
        return nombreClasificacionCargo;
    }

    public void setNombreClasificacionCargo(String nombreClasificacionCargo) {
        this.nombreClasificacionCargo = nombreClasificacionCargo;
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

    public Usuario getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(Usuario usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
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
        hash += (codigoCargoSede != null ? codigoCargoSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sedeclasificacioncargo)) {
            return false;
        }
        Sedeclasificacioncargo other = (Sedeclasificacioncargo) object;
        if ((this.codigoCargoSede == null && other.codigoCargoSede != null) || (this.codigoCargoSede != null && !this.codigoCargoSede.equals(other.codigoCargoSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreClasificacionCargo;
    }

}
