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
@Table(name = "clasificacionmedicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Clasificacionmedicamento.findAll", query = "SELECT c FROM Clasificacionmedicamento c")
    , @NamedQuery(name = "Clasificacionmedicamento.findByCodigoClasificacionmedicamento", query = "SELECT c FROM Clasificacionmedicamento c WHERE c.codigoClasificacionmedicamento = :codigoClasificacionmedicamento")
    , @NamedQuery(name = "Clasificacionmedicamento.findByNombre", query = "SELECT c FROM Clasificacionmedicamento c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Clasificacionmedicamento.findByActivo", query = "SELECT c FROM Clasificacionmedicamento c WHERE c.activo = :activo")
    , @NamedQuery(name = "Clasificacionmedicamento.findByFechaRegistro", query = "SELECT c FROM Clasificacionmedicamento c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Clasificacionmedicamento.findByFechaModificacion", query = "SELECT c FROM Clasificacionmedicamento c WHERE c.fechaModificacion = :fechaModificacion")
    , @NamedQuery(name = "Clasificacionmedicamento.findByCodigoSede", query = "SELECT c FROM Clasificacionmedicamento c WHERE c.codigoSede = :codigoSede")})
public class Clasificacionmedicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoClasificacionmedicamento")
    private Integer codigoClasificacionmedicamento;
    @Size(max = 250)
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
    @Column(name = "codigoSede")
    private Integer codigoSede;
    @OneToMany(mappedBy = "codigoClasificacionMedicamento")
    private List<Formulamedica> formulamedicaList;
    @OneToMany(mappedBy = "codigoTipoMedicamento")
    private List<Plm> plmList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;

    public Clasificacionmedicamento() {
    }

    public Clasificacionmedicamento(Integer codigoClasificacionmedicamento) {
        this.codigoClasificacionmedicamento = codigoClasificacionmedicamento;
    }

    public Integer getCodigoClasificacionmedicamento() {
        return codigoClasificacionmedicamento;
    }

    public void setCodigoClasificacionmedicamento(Integer codigoClasificacionmedicamento) {
        this.codigoClasificacionmedicamento = codigoClasificacionmedicamento;
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

    public Integer getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Integer codigoSede) {
        this.codigoSede = codigoSede;
    }

    @XmlTransient
    public List<Formulamedica> getFormulamedicaList() {
        return formulamedicaList;
    }

    public void setFormulamedicaList(List<Formulamedica> formulamedicaList) {
        this.formulamedicaList = formulamedicaList;
    }

    @XmlTransient
    public List<Plm> getPlmList() {
        return plmList;
    }

    public void setPlmList(List<Plm> plmList) {
        this.plmList = plmList;
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
        hash += (codigoClasificacionmedicamento != null ? codigoClasificacionmedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clasificacionmedicamento)) {
            return false;
        }
        Clasificacionmedicamento other = (Clasificacionmedicamento) object;
        if ((this.codigoClasificacionmedicamento == null && other.codigoClasificacionmedicamento != null) || (this.codigoClasificacionmedicamento != null && !this.codigoClasificacionmedicamento.equals(other.codigoClasificacionmedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
