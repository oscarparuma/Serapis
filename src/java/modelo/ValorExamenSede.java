/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "ValorExamenSede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ValorExamenSede.findAll", query = "SELECT v FROM ValorExamenSede v")
    , @NamedQuery(name = "ValorExamenSede.findByCodigoValorExamen", query = "SELECT v FROM ValorExamenSede v WHERE v.codigoValorExamen = :codigoValorExamen")
    , @NamedQuery(name = "ValorExamenSede.findByFechaRegistro", query = "SELECT v FROM ValorExamenSede v WHERE v.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "ValorExamenSede.findByValorAnterior", query = "SELECT v FROM ValorExamenSede v WHERE v.valorAnterior = :valorAnterior")
    , @NamedQuery(name = "ValorExamenSede.findByValor", query = "SELECT v FROM ValorExamenSede v WHERE v.valor = :valor")})
public class ValorExamenSede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoValorExamen")
    private Long codigoValorExamen;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "valorAnterior")
    private BigDecimal valorAnterior;
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "codigoProcedimintoSede", referencedColumnName = "codigoValorConsulta")
    @ManyToOne
    private Procedimientosede codigoProcedimintoSede;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;

    public ValorExamenSede() {
    }

    public ValorExamenSede(Long codigoValorExamen) {
        this.codigoValorExamen = codigoValorExamen;
    }

    public Long getCodigoValorExamen() {
        return codigoValorExamen;
    }

    public void setCodigoValorExamen(Long codigoValorExamen) {
        this.codigoValorExamen = codigoValorExamen;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public BigDecimal getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(BigDecimal valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
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
        hash += (codigoValorExamen != null ? codigoValorExamen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ValorExamenSede)) {
            return false;
        }
        ValorExamenSede other = (ValorExamenSede) object;
        if ((this.codigoValorExamen == null && other.codigoValorExamen != null) || (this.codigoValorExamen != null && !this.codigoValorExamen.equals(other.codigoValorExamen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ValorExamenSede[ codigoValorExamen=" + codigoValorExamen + " ]";
    }

}
