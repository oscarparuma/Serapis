/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "ClasificacionManual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClasificacionManual.findAll", query = "SELECT c FROM ClasificacionManual c")
    , @NamedQuery(name = "ClasificacionManual.findByCodigoClasificacionManual", query = "SELECT c FROM ClasificacionManual c WHERE c.codigoClasificacionManual = :codigoClasificacionManual")
    , @NamedQuery(name = "ClasificacionManual.findByNombreClasificacionManual", query = "SELECT c FROM ClasificacionManual c WHERE c.nombreClasificacionManual = :nombreClasificacionManual")
    , @NamedQuery(name = "ClasificacionManual.findByActivo", query = "SELECT c FROM ClasificacionManual c WHERE c.activo = :activo")})
public class ClasificacionManual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoClasificacionManual")
    private Integer codigoClasificacionManual;
    @Size(max = 250)
    @Column(name = "nombreClasificacionManual")
    private String nombreClasificacionManual;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "codigoClasificacionManual")
    private List<Manualtarifas> manualtarifasList;
    @OneToMany(mappedBy = "codigoClasificacionManual")
    private List<Procedimientosede> procedimientosedeList;
    @JoinColumn(name = "codigoManualTarifario", referencedColumnName = "codigoManual")
    @ManyToOne
    private TipoManual codigoManualTarifario;

    public ClasificacionManual() {
    }

    public ClasificacionManual(Integer codigoClasificacionManual) {
        this.codigoClasificacionManual = codigoClasificacionManual;
    }

    public Integer getCodigoClasificacionManual() {
        return codigoClasificacionManual;
    }

    public void setCodigoClasificacionManual(Integer codigoClasificacionManual) {
        this.codigoClasificacionManual = codigoClasificacionManual;
    }

    public String getNombreClasificacionManual() {
        return nombreClasificacionManual;
    }

    public void setNombreClasificacionManual(String nombreClasificacionManual) {
        this.nombreClasificacionManual = nombreClasificacionManual;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    @XmlTransient
    public List<Manualtarifas> getManualtarifasList() {
        return manualtarifasList;
    }

    public void setManualtarifasList(List<Manualtarifas> manualtarifasList) {
        this.manualtarifasList = manualtarifasList;
    }

    @XmlTransient
    public List<Procedimientosede> getProcedimientosedeList() {
        return procedimientosedeList;
    }

    public void setProcedimientosedeList(List<Procedimientosede> procedimientosedeList) {
        this.procedimientosedeList = procedimientosedeList;
    }

    public TipoManual getCodigoManualTarifario() {
        return codigoManualTarifario;
    }

    public void setCodigoManualTarifario(TipoManual codigoManualTarifario) {
        this.codigoManualTarifario = codigoManualTarifario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoClasificacionManual != null ? codigoClasificacionManual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClasificacionManual)) {
            return false;
        }
        ClasificacionManual other = (ClasificacionManual) object;
        if ((this.codigoClasificacionManual == null && other.codigoClasificacionManual != null) || (this.codigoClasificacionManual != null && !this.codigoClasificacionManual.equals(other.codigoClasificacionManual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.ClasificacionManual[ codigoClasificacionManual=" + codigoClasificacionManual + " ]";
    }
    
}
