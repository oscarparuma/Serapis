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
@Table(name = "causaexterna")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Causaexterna.findAll", query = "SELECT c FROM Causaexterna c")
    , @NamedQuery(name = "Causaexterna.findByCodigo", query = "SELECT c FROM Causaexterna c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Causaexterna.findByNombre", query = "SELECT c FROM Causaexterna c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Causaexterna.findByActivo", query = "SELECT c FROM Causaexterna c WHERE c.activo = :activo")
    , @NamedQuery(name = "Causaexterna.findByCodigoCausaExternaRips", query = "SELECT c FROM Causaexterna c WHERE c.codigoCausaExternaRips = :codigoCausaExternaRips")
    , @NamedQuery(name = "Causaexterna.findByFechaRegistro", query = "SELECT c FROM Causaexterna c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Causaexterna.findByFechaModificacion", query = "SELECT c FROM Causaexterna c WHERE c.fechaModificacion = :fechaModificacion")})
public class Causaexterna implements Serializable {

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
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "modificadoPor", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario modificadoPor;
    @OneToMany(mappedBy = "codigoCausa")
    private List<Procedimiento> procedimientoList;
    @OneToMany(mappedBy = "codigoCausa")
    private List<Consultamedica> consultamedicaList;
    @Size(max = 3)
    @Column(name = "codigoCausaExternaRips")
    private String codigoCausaExternaRips;

    public Causaexterna() {
    }

    public Causaexterna(Integer codigo) {
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

    public String getCodigoCausaExternaRips() {
        return codigoCausaExternaRips;
    }

    public void setCodigoCausaExternaRips(String codigoCausaExternaRips) {
        this.codigoCausaExternaRips = codigoCausaExternaRips;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Causaexterna)) {
            return false;
        }
        Causaexterna other = (Causaexterna) object;
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
