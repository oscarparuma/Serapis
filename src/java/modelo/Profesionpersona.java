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
@Table(name = "profesionpersona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesionpersona.findAll", query = "SELECT p FROM Profesionpersona p")
    , @NamedQuery(name = "Profesionpersona.findByCodigoProfesionPersona", query = "SELECT p FROM Profesionpersona p WHERE p.codigoProfesionPersona = :codigoProfesionPersona")
    , @NamedQuery(name = "Profesionpersona.findByIdentificacionProfesion", query = "SELECT p FROM Profesionpersona p WHERE p.identificacionProfesion = :identificacionProfesion")
    , @NamedQuery(name = "Profesionpersona.findByNombreProfesionPersona", query = "SELECT p FROM Profesionpersona p WHERE p.nombreProfesionPersona = :nombreProfesionPersona")
    , @NamedQuery(name = "Profesionpersona.findByActivo", query = "SELECT p FROM Profesionpersona p WHERE p.activo = :activo")
    , @NamedQuery(name = "Profesionpersona.findByFechaRegistro", query = "SELECT p FROM Profesionpersona p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Profesionpersona.findByFechaModificacion", query = "SELECT p FROM Profesionpersona p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Profesionpersona.findByCodigoCargoPersona", query = "SELECT p FROM Profesionpersona p WHERE p.codigoCargoPersona = :codigoCargoPersona")})
public class Profesionpersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoProfesionPersona")
    private Long codigoProfesionPersona;
    @Size(max = 10)
    @Column(name = "identificacionProfesion")
    private String identificacionProfesion;
    @Size(max = 250)
    @Column(name = "nombreProfesionPersona")
    private String nombreProfesionPersona;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Column(name = "codigoCargoPersona")
    private Integer codigoCargoPersona;
    @OneToMany(mappedBy = "codigoProfesionPersona")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoProfesionPersona")
    private List<Datospersona> datospersonaList;
    @OneToMany(mappedBy = "codigoProfesionPersona")
    private List<ClasificacionProfesionPersona> clasificacionProfesionPersonaList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "usuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario usuarioModifico;

    public Profesionpersona() {
    }

    public Profesionpersona(Long codigoProfesionPersona) {
        this.codigoProfesionPersona = codigoProfesionPersona;
    }

    public Long getCodigoProfesionPersona() {
        return codigoProfesionPersona;
    }

    public void setCodigoProfesionPersona(Long codigoProfesionPersona) {
        this.codigoProfesionPersona = codigoProfesionPersona;
    }

    public String getIdentificacionProfesion() {
        return identificacionProfesion;
    }

    public void setIdentificacionProfesion(String identificacionProfesion) {
        this.identificacionProfesion = identificacionProfesion;
    }

    public String getNombreProfesionPersona() {
        return nombreProfesionPersona;
    }

    public void setNombreProfesionPersona(String nombreProfesionPersona) {
        this.nombreProfesionPersona = nombreProfesionPersona;
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

    public Integer getCodigoCargoPersona() {
        return codigoCargoPersona;
    }

    public void setCodigoCargoPersona(Integer codigoCargoPersona) {
        this.codigoCargoPersona = codigoCargoPersona;
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

    @XmlTransient
    public List<ClasificacionProfesionPersona> getClasificacionProfesionPersonaList() {
        return clasificacionProfesionPersonaList;
    }

    public void setClasificacionProfesionPersonaList(List<ClasificacionProfesionPersona> clasificacionProfesionPersonaList) {
        this.clasificacionProfesionPersonaList = clasificacionProfesionPersonaList;
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
        hash += (codigoProfesionPersona != null ? codigoProfesionPersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesionpersona)) {
            return false;
        }
        Profesionpersona other = (Profesionpersona) object;
        if ((this.codigoProfesionPersona == null && other.codigoProfesionPersona != null) || (this.codigoProfesionPersona != null && !this.codigoProfesionPersona.equals(other.codigoProfesionPersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreProfesionPersona;
    }

}
