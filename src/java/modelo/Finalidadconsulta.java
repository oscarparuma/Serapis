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
@Table(name = "finalidadconsulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Finalidadconsulta.findAll", query = "SELECT f FROM Finalidadconsulta f")
    , @NamedQuery(name = "Finalidadconsulta.findByCodigo", query = "SELECT f FROM Finalidadconsulta f WHERE f.codigo = :codigo")
    , @NamedQuery(name = "Finalidadconsulta.findByNombre", query = "SELECT f FROM Finalidadconsulta f WHERE f.nombre = :nombre")
    , @NamedQuery(name = "Finalidadconsulta.findByActivo", query = "SELECT f FROM Finalidadconsulta f WHERE f.activo = :activo")
    , @NamedQuery(name = "Finalidadconsulta.findByCodigoFinalidadRips", query = "SELECT f FROM Finalidadconsulta f WHERE f.codigoFinalidadRips = :codigoFinalidadRips")
    , @NamedQuery(name = "Finalidadconsulta.findByFechaRegistro", query = "SELECT f FROM Finalidadconsulta f WHERE f.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Finalidadconsulta.findByFechaModificacion", query = "SELECT f FROM Finalidadconsulta f WHERE f.fechaModificacion = :fechaModificacion")})
public class Finalidadconsulta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigo")
    private Integer codigo;
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
    @OneToMany(mappedBy = "codigoFinalidad")
    private List<Procedimiento> procedimientoList;
    @OneToMany(mappedBy = "codigoFinalidad")
    private List<Consultamedica> consultamedicaList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @Size(max = 3)
    @Column(name = "codigoFinalidadRips")
    private String codigoFinalidadRips;

    public Finalidadconsulta() {
    }

    public Finalidadconsulta(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getCodigoFinalidadRips() {
        return codigoFinalidadRips;
    }

    public void setCodigoFinalidadRips(String codigoFinalidadRips) {
        this.codigoFinalidadRips = codigoFinalidadRips;
    }

    
    
    @XmlTransient
    public List<Procedimiento> getProcedimientoList() {
        return procedimientoList;
    }

    public void setProcedimientoList(List<Procedimiento> procedimientoList) {
        this.procedimientoList = procedimientoList;
    }

    @XmlTransient
    public List<Consultamedica> getConsultamedicaList() {
        return consultamedicaList;
    }

    public void setConsultamedicaList(List<Consultamedica> consultamedicaList) {
        this.consultamedicaList = consultamedicaList;
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
        if (!(object instanceof Finalidadconsulta)) {
            return false;
        }
        Finalidadconsulta other = (Finalidadconsulta) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
