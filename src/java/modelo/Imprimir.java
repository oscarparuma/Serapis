/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "Imprimir")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Imprimir.findAll", query = "SELECT i FROM Imprimir i")
    , @NamedQuery(name = "Imprimir.findByCodigoImprimir", query = "SELECT i FROM Imprimir i WHERE i.codigoImprimir = :codigoImprimir")
    , @NamedQuery(name = "Imprimir.findByFinalizada", query = "SELECT i FROM Imprimir i WHERE i.finalizada = :finalizada")
    , @NamedQuery(name = "Imprimir.findByFechaFinalizacion", query = "SELECT i FROM Imprimir i WHERE i.fechaFinalizacion = :fechaFinalizacion")})
public class Imprimir implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoImprimir")
    private Long codigoImprimir;
    @Size(max = 1)
    @Column(name = "finalizada")
    private String finalizada;
    @Column(name = "fechaFinalizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFinalizacion;
    @JoinColumn(name = "codigoCita", referencedColumnName = "codigoCitaPersona")
    @ManyToOne
    private Citapersona codigoCita;
    @JoinColumn(name = "codigoPersona", referencedColumnName = "codigoPersona")
    @ManyToOne
    private Datospersona codigoPersona;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;

    public Imprimir() {
    }

    public Imprimir(Long codigoImprimir) {
        this.codigoImprimir = codigoImprimir;
    }

    public Long getCodigoImprimir() {
        return codigoImprimir;
    }

    public void setCodigoImprimir(Long codigoImprimir) {
        this.codigoImprimir = codigoImprimir;
    }

    public String getFinalizada() {
        return finalizada;
    }

    public void setFinalizada(String finalizada) {
        this.finalizada = finalizada;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public Citapersona getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(Citapersona codigoCita) {
        this.codigoCita = codigoCita;
    }

    public Datospersona getCodigoPersona() {
        return codigoPersona;
    }

    public void setCodigoPersona(Datospersona codigoPersona) {
        this.codigoPersona = codigoPersona;
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
        hash += (codigoImprimir != null ? codigoImprimir.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Imprimir)) {
            return false;
        }
        Imprimir other = (Imprimir) object;
        if ((this.codigoImprimir == null && other.codigoImprimir != null) || (this.codigoImprimir != null && !this.codigoImprimir.equals(other.codigoImprimir))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Imprimir[ codigoImprimir=" + codigoImprimir + " ]";
    }
    
}
