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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "TipoManual")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoManual.findAll", query = "SELECT t FROM TipoManual t")
    , @NamedQuery(name = "TipoManual.findByCodigoManual", query = "SELECT t FROM TipoManual t WHERE t.codigoManual = :codigoManual")
    , @NamedQuery(name = "TipoManual.findByNombreManual", query = "SELECT t FROM TipoManual t WHERE t.nombreManual = :nombreManual")
    , @NamedQuery(name = "TipoManual.findByActivo", query = "SELECT t FROM TipoManual t WHERE t.activo = :activo")})
public class TipoManual implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoManual")
    private Integer codigoManual;
    @Size(max = 250)
    @Column(name = "nombreManual")
    private String nombreManual;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "codigoManualTarifario")
    private List<Manualtarifas> manualtarifasList;
    @OneToMany(mappedBy = "codigoManualTarifario")
    private List<Procedimientosede> procedimientosedeList;
    @OneToMany(mappedBy = "codigoManualTarifario")
    private List<ClasificacionManual> clasificacionManualList;
    @JoinColumn(name = "codigoPais", referencedColumnName = "codigoPais")
    @ManyToOne
    private Pais codigoPais;

    public TipoManual() {
    }

    public TipoManual(Integer codigoManual) {
        this.codigoManual = codigoManual;
    }

    public Integer getCodigoManual() {
        return codigoManual;
    }

    public void setCodigoManual(Integer codigoManual) {
        this.codigoManual = codigoManual;
    }

    public String getNombreManual() {
        return nombreManual;
    }

    public void setNombreManual(String nombreManual) {
        this.nombreManual = nombreManual;
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

    @XmlTransient
    public List<ClasificacionManual> getClasificacionManualList() {
        return clasificacionManualList;
    }

    public void setClasificacionManualList(List<ClasificacionManual> clasificacionManualList) {
        this.clasificacionManualList = clasificacionManualList;
    }

    public Pais getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Pais codigoPais) {
        this.codigoPais = codigoPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoManual != null ? codigoManual.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoManual)) {
            return false;
        }
        TipoManual other = (TipoManual) object;
        if ((this.codigoManual == null && other.codigoManual != null) || (this.codigoManual != null && !this.codigoManual.equals(other.codigoManual))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreManual;
    }

}
