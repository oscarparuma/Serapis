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
@Table(name = "consultorio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consultorio.findAll", query = "SELECT c FROM Consultorio c")
    , @NamedQuery(name = "Consultorio.findByCodigoConsultorio", query = "SELECT c FROM Consultorio c WHERE c.codigoConsultorio = :codigoConsultorio")
    , @NamedQuery(name = "Consultorio.findByNombreConsultorio", query = "SELECT c FROM Consultorio c WHERE c.nombreConsultorio = :nombreConsultorio")
    , @NamedQuery(name = "Consultorio.findByActivo", query = "SELECT c FROM Consultorio c WHERE c.activo = :activo")
    , @NamedQuery(name = "Consultorio.findByFechaRegistro", query = "SELECT c FROM Consultorio c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Consultorio.findByFechaModificacion", query = "SELECT c FROM Consultorio c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Consultorio.findByUbicacion", query = "SELECT c FROM Consultorio c WHERE c.ubicacion = :ubicacion")})
public class Consultorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoConsultorio")
    private Integer codigoConsultorio;
    @Size(max = 150)
    @Column(name = "nombreConsultorio")
    private String nombreConsultorio;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 100)
    @Column(name = "ubicacion")
    private String ubicacion;
    @OneToMany(mappedBy = "codigoConsultorio")
    private List<Citapersona> citapersonaList;
    @JoinColumn(name = "codigoDependencia", referencedColumnName = "codigoDependencia")
    @ManyToOne
    private Dependencia codigoDependencia;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;

    public Consultorio() {
    }

    public Consultorio(Integer codigoConsultorio) {
        this.codigoConsultorio = codigoConsultorio;
    }

    public Integer getCodigoConsultorio() {
        return codigoConsultorio;
    }

    public void setCodigoConsultorio(Integer codigoConsultorio) {
        this.codigoConsultorio = codigoConsultorio;
    }

    public String getNombreConsultorio() {
        return nombreConsultorio;
    }

    public void setNombreConsultorio(String nombreConsultorio) {
        this.nombreConsultorio = nombreConsultorio;
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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    public Dependencia getCodigoDependencia() {
        return codigoDependencia;
    }

    public void setCodigoDependencia(Dependencia codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
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

    public Usuario getCodigoUsuarioModifico() {
        return codigoUsuarioModifico;
    }

    public void setCodigoUsuarioModifico(Usuario codigoUsuarioModifico) {
        this.codigoUsuarioModifico = codigoUsuarioModifico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoConsultorio != null ? codigoConsultorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consultorio)) {
            return false;
        }
        Consultorio other = (Consultorio) object;
        if ((this.codigoConsultorio == null && other.codigoConsultorio != null) || (this.codigoConsultorio != null && !this.codigoConsultorio.equals(other.codigoConsultorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreConsultorio;
    }

}
