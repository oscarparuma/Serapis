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
@Table(name = "tipoexamen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoexamen.findAll", query = "SELECT t FROM Tipoexamen t")
    , @NamedQuery(name = "Tipoexamen.findByCodigo", query = "SELECT t FROM Tipoexamen t WHERE t.codigo = :codigo")
    , @NamedQuery(name = "Tipoexamen.findByNombreExamene", query = "SELECT t FROM Tipoexamen t WHERE t.nombreExamene = :nombreExamene")
    , @NamedQuery(name = "Tipoexamen.findByActivo", query = "SELECT t FROM Tipoexamen t WHERE t.activo = :activo")
    , @NamedQuery(name = "Tipoexamen.findByFechaRegistro", query = "SELECT t FROM Tipoexamen t WHERE t.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Tipoexamen.findByFechaModificacion", query = "SELECT t FROM Tipoexamen t WHERE t.fechaModificacion = :fechaModificacion")})
public class Tipoexamen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 250)
    @Column(name = "nombreExamene")
    private String nombreExamene;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "tipoExamen")
    private List<Clasificacioncopago> clasificacioncopagoList;
    @OneToMany(mappedBy = "codigoExamenRealizar")
    private List<Citapersona> citapersonaList;
    @JoinColumn(name = "codigoArea", referencedColumnName = "codigoArea")
    @ManyToOne
    private Area codigoArea;
    @JoinColumn(name = "codigoDependencia", referencedColumnName = "codigoDependencia")
    @ManyToOne
    private Dependencia codigoDependencia;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public Tipoexamen() {
    }

    public Tipoexamen(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreExamene() {
        return nombreExamene;
    }

    public void setNombreExamene(String nombreExamene) {
        this.nombreExamene = nombreExamene;
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
    public List<Clasificacioncopago> getClasificacioncopagoList() {
        return clasificacioncopagoList;
    }

    public void setClasificacioncopagoList(List<Clasificacioncopago> clasificacioncopagoList) {
        this.clasificacioncopagoList = clasificacioncopagoList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    public Area getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(Area codigoArea) {
        this.codigoArea = codigoArea;
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

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoexamen)) {
            return false;
        }
        Tipoexamen other = (Tipoexamen) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreExamene;
    }
    
}
