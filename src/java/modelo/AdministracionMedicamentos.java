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
@Table(name = "AdministracionMedicamentos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdministracionMedicamentos.findAll", query = "SELECT a FROM AdministracionMedicamentos a")
    , @NamedQuery(name = "AdministracionMedicamentos.findByCodigoAdministracion", query = "SELECT a FROM AdministracionMedicamentos a WHERE a.codigoAdministracion = :codigoAdministracion")
    , @NamedQuery(name = "AdministracionMedicamentos.findByNombreAdministracionMedicamento", query = "SELECT a FROM AdministracionMedicamentos a WHERE a.nombreAdministracionMedicamento = :nombreAdministracionMedicamento")
    , @NamedQuery(name = "AdministracionMedicamentos.findByActivo", query = "SELECT a FROM AdministracionMedicamentos a WHERE a.activo = :activo")
    , @NamedQuery(name = "AdministracionMedicamentos.findByFechaRegistro", query = "SELECT a FROM AdministracionMedicamentos a WHERE a.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "AdministracionMedicamentos.findByFechaModificacion", query = "SELECT a FROM AdministracionMedicamentos a WHERE a.fechaModificacion = :fechaModificacion")})
public class AdministracionMedicamentos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoAdministracion")
    private Integer codigoAdministracion;
    @Size(max = 150)
    @Column(name = "nombreAdministracionMedicamento")
    private String nombreAdministracionMedicamento;
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
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;

    public AdministracionMedicamentos() {
    }

    public AdministracionMedicamentos(Integer codigoAdministracion) {
        this.codigoAdministracion = codigoAdministracion;
    }

    public Integer getCodigoAdministracion() {
        return codigoAdministracion;
    }

    public void setCodigoAdministracion(Integer codigoAdministracion) {
        this.codigoAdministracion = codigoAdministracion;
    }

    public String getNombreAdministracionMedicamento() {
        return nombreAdministracionMedicamento;
    }

    public void setNombreAdministracionMedicamento(String nombreAdministracionMedicamento) {
        this.nombreAdministracionMedicamento = nombreAdministracionMedicamento;
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

    public Usuario getUsuarioModifico() {
        return usuarioModifico;
    }

    public void setUsuarioModifico(Usuario usuarioModifico) {
        this.usuarioModifico = usuarioModifico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoAdministracion != null ? codigoAdministracion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdministracionMedicamentos)) {
            return false;
        }
        AdministracionMedicamentos other = (AdministracionMedicamentos) object;
        if ((this.codigoAdministracion == null && other.codigoAdministracion != null) || (this.codigoAdministracion != null && !this.codigoAdministracion.equals(other.codigoAdministracion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AdministracionMedicamentos[ codigoAdministracion=" + codigoAdministracion + " ]";
    }
    
}
