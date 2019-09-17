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
@Table(name = "NivelEducativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NivelEducativo.findAll", query = "SELECT n FROM NivelEducativo n")
    , @NamedQuery(name = "NivelEducativo.findByCodigoNivelEducativo", query = "SELECT n FROM NivelEducativo n WHERE n.codigoNivelEducativo = :codigoNivelEducativo")
    , @NamedQuery(name = "NivelEducativo.findByNombreNivelEducativo", query = "SELECT n FROM NivelEducativo n WHERE n.nombreNivelEducativo = :nombreNivelEducativo")
    , @NamedQuery(name = "NivelEducativo.findByActivo", query = "SELECT n FROM NivelEducativo n WHERE n.activo = :activo")
    , @NamedQuery(name = "NivelEducativo.findByFechaRegistro", query = "SELECT n FROM NivelEducativo n WHERE n.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "NivelEducativo.findByFechaModificacion", query = "SELECT n FROM NivelEducativo n WHERE n.fechaModificacion = :fechaModificacion")})
public class NivelEducativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoNivelEducativo")
    private Integer codigoNivelEducativo;
    @Size(max = 150)
    @Column(name = "nombreNivelEducativo")
    private String nombreNivelEducativo;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "primaria")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "secundaria")
    private List<Citapersona> citapersonaList1;
    @OneToMany(mappedBy = "secundaria")
    private List<Datospersona> datospersonaList;
    @OneToMany(mappedBy = "primaria")
    private List<Datospersona> datospersonaList1;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;

    public NivelEducativo() {
    }

    public NivelEducativo(Integer codigoNivelEducativo) {
        this.codigoNivelEducativo = codigoNivelEducativo;
    }

    public Integer getCodigoNivelEducativo() {
        return codigoNivelEducativo;
    }

    public void setCodigoNivelEducativo(Integer codigoNivelEducativo) {
        this.codigoNivelEducativo = codigoNivelEducativo;
    }

    public String getNombreNivelEducativo() {
        return nombreNivelEducativo;
    }

    public void setNombreNivelEducativo(String nombreNivelEducativo) {
        this.nombreNivelEducativo = nombreNivelEducativo;
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
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList1() {
        return citapersonaList1;
    }

    public void setCitapersonaList1(List<Citapersona> citapersonaList1) {
        this.citapersonaList1 = citapersonaList1;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList() {
        return datospersonaList;
    }

    public void setDatospersonaList(List<Datospersona> datospersonaList) {
        this.datospersonaList = datospersonaList;
    }

    @XmlTransient
    public List<Datospersona> getDatospersonaList1() {
        return datospersonaList1;
    }

    public void setDatospersonaList1(List<Datospersona> datospersonaList1) {
        this.datospersonaList1 = datospersonaList1;
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
        hash += (codigoNivelEducativo != null ? codigoNivelEducativo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NivelEducativo)) {
            return false;
        }
        NivelEducativo other = (NivelEducativo) object;
        if ((this.codigoNivelEducativo == null && other.codigoNivelEducativo != null) || (this.codigoNivelEducativo != null && !this.codigoNivelEducativo.equals(other.codigoNivelEducativo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreNivelEducativo;
    }

}
