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
@Table(name = "cargosede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cargosede.findAll", query = "SELECT c FROM Cargosede c")
    , @NamedQuery(name = "Cargosede.findByCodigoCargo", query = "SELECT c FROM Cargosede c WHERE c.codigoCargo = :codigoCargo")
    , @NamedQuery(name = "Cargosede.findByNombreCargo", query = "SELECT c FROM Cargosede c WHERE c.nombreCargo = :nombreCargo")
    , @NamedQuery(name = "Cargosede.findByActivo", query = "SELECT c FROM Cargosede c WHERE c.activo = :activo")
    , @NamedQuery(name = "Cargosede.findByFechaRegistro", query = "SELECT c FROM Cargosede c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Cargosede.findByFechaModificacion", query = "SELECT c FROM Cargosede c WHERE c.fechaModificacion = :fechaModificacion")})
public class Cargosede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoCargo")
    private Integer codigoCargo;
    @Size(max = 250)
    @Column(name = "nombreCargo")
    private String nombreCargo;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "codigoCargo")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "usuarioModificacion", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModificacion;

    public Cargosede() {
    }

    public Cargosede(Integer codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public Integer getCodigoCargo() {
        return codigoCargo;
    }

    public void setCodigoCargo(Integer codigoCargo) {
        this.codigoCargo = codigoCargo;
    }

    public String getNombreCargo() {
        return nombreCargo;
    }

    public void setNombreCargo(String nombreCargo) {
        this.nombreCargo = nombreCargo;
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
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
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

    public Usuario getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Usuario usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
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
        if (!(object instanceof Cargosede)) {
            return false;
        }
        Cargosede other = (Cargosede) object;
        if ((this.codigoCargo == null && other.codigoCargo != null) || (this.codigoCargo != null && !this.codigoCargo.equals(other.codigoCargo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreCargo;
    }

}
