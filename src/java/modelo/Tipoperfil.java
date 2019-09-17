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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "tipoperfil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoperfil.findAll", query = "SELECT t FROM Tipoperfil t")
    , @NamedQuery(name = "Tipoperfil.findByCodigoPerfil", query = "SELECT t FROM Tipoperfil t WHERE t.codigoPerfil = :codigoPerfil")
    , @NamedQuery(name = "Tipoperfil.findByNombrePerfil", query = "SELECT t FROM Tipoperfil t WHERE t.nombrePerfil = :nombrePerfil")
    , @NamedQuery(name = "Tipoperfil.findByActivo", query = "SELECT t FROM Tipoperfil t WHERE t.activo = :activo")
    , @NamedQuery(name = "Tipoperfil.findByFechaRegistro", query = "SELECT t FROM Tipoperfil t WHERE t.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Tipoperfil.findByFechaModificacion", query = "SELECT t FROM Tipoperfil t WHERE t.fechaModificacion = :fechaModificacion")})
public class Tipoperfil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoPerfil")
    private Integer codigoPerfil;
    @Size(max = 100)
    @Column(name = "nombrePerfil")
    private String nombrePerfil;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @ManyToMany(mappedBy = "tipoperfilList")
    private List<TipoOpcion> tipoOpcionList;
    @JoinTable(name = "PerfilUsuario", joinColumns = {
        @JoinColumn(name = "codigoPerfil", referencedColumnName = "codigoPerfil")}, inverseJoinColumns = {
        @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")})
    @ManyToMany
    private List<Usuario> usuarioList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "codigoPerfil")
    private List<Usuario> usuarioList1;

    public Tipoperfil() {
    }

    public Tipoperfil(Integer codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public Integer getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(Integer codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
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
    public List<TipoOpcion> getTipoOpcionList() {
        return tipoOpcionList;
    }

    public void setTipoOpcionList(List<TipoOpcion> tipoOpcionList) {
        this.tipoOpcionList = tipoOpcionList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
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
    public List<Usuario> getUsuarioList1() {
        return usuarioList1;
    }

    public void setUsuarioList1(List<Usuario> usuarioList1) {
        this.usuarioList1 = usuarioList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPerfil != null ? codigoPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoperfil)) {
            return false;
        }
        Tipoperfil other = (Tipoperfil) object;
        if ((this.codigoPerfil == null && other.codigoPerfil != null) || (this.codigoPerfil != null && !this.codigoPerfil.equals(other.codigoPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return nombrePerfil;
    }
    
}
