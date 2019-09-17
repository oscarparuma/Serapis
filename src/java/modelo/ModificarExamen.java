/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "ModificarExamen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ModificarExamen.findAll", query = "SELECT m FROM ModificarExamen m")
    , @NamedQuery(name = "ModificarExamen.findByCodigoExamen", query = "SELECT m FROM ModificarExamen m WHERE m.codigoExamen = :codigoExamen")
    , @NamedQuery(name = "ModificarExamen.findByFechaRegistro", query = "SELECT m FROM ModificarExamen m WHERE m.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ModificarExamen.findByActivo", query = "SELECT m FROM ModificarExamen m WHERE m.activo = :activo")
    , @NamedQuery(name = "ModificarExamen.findByEstadoAnterior", query = "SELECT m FROM ModificarExamen m WHERE m.estadoAnterior = :estadoAnterior")})
public class ModificarExamen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoExamen")
    private Long codigoExamen;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Size(max = 1)
    @Column(name = "estadoAnterior")
    private String estadoAnterior;
    @Lob
    @Column(name = "justificacion")
    private String justificacion;
    @JoinColumn(name = "codigoProcedimintoSede", referencedColumnName = "codigoValorConsulta")
    @ManyToOne(cascade = CascadeType.ALL)
    private Procedimientosede codigoProcedimintoSede;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;

    public ModificarExamen() {
    }

    public ModificarExamen(Long codigoExamen) {
        this.codigoExamen = codigoExamen;
    }

    public Long getCodigoExamen() {
        return codigoExamen;
    }

    public void setCodigoExamen(Long codigoExamen) {
        this.codigoExamen = codigoExamen;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(String estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    public String getJustificacion() {
        return justificacion;
    }

    public void setJustificacion(String justificacion) {
        this.justificacion = justificacion;
    }

    public Procedimientosede getCodigoProcedimintoSede() {
        return codigoProcedimintoSede;
    }

    public void setCodigoProcedimintoSede(Procedimientosede codigoProcedimintoSede) {
        this.codigoProcedimintoSede = codigoProcedimintoSede;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoExamen != null ? codigoExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModificarExamen)) {
            return false;
        }
        ModificarExamen other = (ModificarExamen) object;
        if ((this.codigoExamen == null && other.codigoExamen != null) || (this.codigoExamen != null && !this.codigoExamen.equals(other.codigoExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ModificarExamen[ codigoExamen=" + codigoExamen + " ]";
    }

}
