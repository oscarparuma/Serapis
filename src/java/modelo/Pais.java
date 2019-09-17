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
@Table(name = "pais")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
    , @NamedQuery(name = "Pais.findByCodigoPais", query = "SELECT p FROM Pais p WHERE p.codigoPais = :codigoPais")
    , @NamedQuery(name = "Pais.findByNombrePais", query = "SELECT p FROM Pais p WHERE p.nombrePais = :nombrePais")
    , @NamedQuery(name = "Pais.findByActivo", query = "SELECT p FROM Pais p WHERE p.activo = :activo")
    , @NamedQuery(name = "Pais.findByFechaRegistro", query = "SELECT p FROM Pais p WHERE p.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Pais.findByFechaModificacion", query = "SELECT p FROM Pais p WHERE p.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Pais.findByCodigoIdentificacionPais", query = "SELECT p FROM Pais p WHERE p.codigoIdentificacionPais = :codigoIdentificacionPais")})
public class Pais implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoPais")
    private Integer codigoPais;
    @Size(max = 150)
    @Column(name = "nombrePais")
    private String nombrePais;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @Size(max = 20)
    @Column(name = "codigoIdentificacionPais")
    private String codigoIdentificacionPais;
    @OneToMany(mappedBy = "codigoPais")
    private List<Manualtarifas> manualtarifasList;
    @OneToMany(mappedBy = "codigoPais")
    private List<ClasificacionEps> clasificacionEpsList;
    @OneToMany(mappedBy = "codigoPais")
    private List<Eps> epsList;
    @OneToMany(mappedBy = "codigoPais")
    private List<Usuario> usuarioList;
    @OneToMany(mappedBy = "codigoPais")
    private List<TipoManual> tipoManualList;
    @OneToMany(mappedBy = "codigoPais")
    private List<EpsSede> epsSedeList;
    @OneToMany(mappedBy = "codigoPais")
    private List<Sede> sedeList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "codigoPais")
    private List<Departamento> departamentoList;

    public Pais() {
    }

    public Pais(Integer codigoPais) {
        this.codigoPais = codigoPais;
    }

    public Integer getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Integer codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
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

    public String getCodigoIdentificacionPais() {
        return codigoIdentificacionPais;
    }

    public void setCodigoIdentificacionPais(String codigoIdentificacionPais) {
        this.codigoIdentificacionPais = codigoIdentificacionPais;
    }

    @XmlTransient
    public List<Manualtarifas> getManualtarifasList() {
        return manualtarifasList;
    }

    public void setManualtarifasList(List<Manualtarifas> manualtarifasList) {
        this.manualtarifasList = manualtarifasList;
    }

    @XmlTransient
    public List<ClasificacionEps> getClasificacionEpsList() {
        return clasificacionEpsList;
    }

    public void setClasificacionEpsList(List<ClasificacionEps> clasificacionEpsList) {
        this.clasificacionEpsList = clasificacionEpsList;
    }

    @XmlTransient
    public List<Eps> getEpsList() {
        return epsList;
    }

    public void setEpsList(List<Eps> epsList) {
        this.epsList = epsList;
    }

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @XmlTransient
    public List<TipoManual> getTipoManualList() {
        return tipoManualList;
    }

    public void setTipoManualList(List<TipoManual> tipoManualList) {
        this.tipoManualList = tipoManualList;
    }

    @XmlTransient
    public List<EpsSede> getEpsSedeList() {
        return epsSedeList;
    }

    public void setEpsSedeList(List<EpsSede> epsSedeList) {
        this.epsSedeList = epsSedeList;
    }

    @XmlTransient
    public List<Sede> getSedeList() {
        return sedeList;
    }

    public void setSedeList(List<Sede> sedeList) {
        this.sedeList = sedeList;
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
    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPais != null ? codigoPais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pais)) {
            return false;
        }
        Pais other = (Pais) object;
        if ((this.codigoPais == null && other.codigoPais != null) || (this.codigoPais != null && !this.codigoPais.equals(other.codigoPais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return nombrePais;
    }
    
}
