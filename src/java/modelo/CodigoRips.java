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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "CodigoRips")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CodigoRips.findAll", query = "SELECT c FROM CodigoRips c")
    , @NamedQuery(name = "CodigoRips.findByCodigoRipsSede", query = "SELECT c FROM CodigoRips c WHERE c.codigoRipsSede = :codigoRipsSede")
    , @NamedQuery(name = "CodigoRips.findByCodigoRips", query = "SELECT c FROM CodigoRips c WHERE c.codigoRips = :codigoRips")
    , @NamedQuery(name = "CodigoRips.findByCodigoRipsAnterior", query = "SELECT c FROM CodigoRips c WHERE c.codigoRipsAnterior = :codigoRipsAnterior")
    , @NamedQuery(name = "CodigoRips.findByFechaRegistro", query = "SELECT c FROM CodigoRips c WHERE c.fechaRegistro = :fechaRegistro")})
public class CodigoRips implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoRipsSede")
    private Long codigoRipsSede;
    @Size(max = 50)
    @Column(name = "codigoRips")
    private String codigoRips;
    @Size(max = 50)
    @Column(name = "codigoRipsAnterior")
    private String codigoRipsAnterior;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @JoinColumn(name = "codigoProcedimintoSede", referencedColumnName = "codigoValorConsulta")
     @ManyToOne(cascade = CascadeType.ALL)
    private Procedimientosede codigoProcedimintoSede;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;

    public CodigoRips() {
    }

    public CodigoRips(Long codigoRipsSede) {
        this.codigoRipsSede = codigoRipsSede;
    }

    public Long getCodigoRipsSede() {
        return codigoRipsSede;
    }

    public void setCodigoRipsSede(Long codigoRipsSede) {
        this.codigoRipsSede = codigoRipsSede;
    }

    public String getCodigoRips() {
        return codigoRips;
    }

    public void setCodigoRips(String codigoRips) {
        this.codigoRips = codigoRips;
    }

    public String getCodigoRipsAnterior() {
        return codigoRipsAnterior;
    }

    public void setCodigoRipsAnterior(String codigoRipsAnterior) {
        this.codigoRipsAnterior = codigoRipsAnterior;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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
        hash += (codigoRipsSede != null ? codigoRipsSede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CodigoRips)) {
            return false;
        }
        CodigoRips other = (CodigoRips) object;
        if ((this.codigoRipsSede == null && other.codigoRipsSede != null) || (this.codigoRipsSede != null && !this.codigoRipsSede.equals(other.codigoRipsSede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       return codigoRipsSede + " ";
    }

}
