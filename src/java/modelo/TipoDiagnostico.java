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
@Table(name = "TipoDiagnostico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDiagnostico.findAll", query = "SELECT t FROM TipoDiagnostico t")
    , @NamedQuery(name = "TipoDiagnostico.findByCodigoTipoDiagnostico", query = "SELECT t FROM TipoDiagnostico t WHERE t.codigoTipoDiagnostico = :codigoTipoDiagnostico")
    , @NamedQuery(name = "TipoDiagnostico.findByNombre", query = "SELECT t FROM TipoDiagnostico t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoDiagnostico.findByActivo", query = "SELECT t FROM TipoDiagnostico t WHERE t.activo = :activo")})
public class TipoDiagnostico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "codigoTipoDiagnostico")
    private String codigoTipoDiagnostico;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @OneToMany(mappedBy = "codigoTipoDiagnostico")
    private List<DiagnosticoIngreso> diagnosticoIngresoList;

    public TipoDiagnostico() {
    }

    public TipoDiagnostico(String codigoTipoDiagnostico) {
        this.codigoTipoDiagnostico = codigoTipoDiagnostico;
    }

    public String getCodigoTipoDiagnostico() {
        return codigoTipoDiagnostico;
    }

    public void setCodigoTipoDiagnostico(String codigoTipoDiagnostico) {
        this.codigoTipoDiagnostico = codigoTipoDiagnostico;
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

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList() {
        return diagnosticoIngresoList;
    }

    public void setDiagnosticoIngresoList(List<DiagnosticoIngreso> diagnosticoIngresoList) {
        this.diagnosticoIngresoList = diagnosticoIngresoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoTipoDiagnostico != null ? codigoTipoDiagnostico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDiagnostico)) {
            return false;
        }
        TipoDiagnostico other = (TipoDiagnostico) object;
        if ((this.codigoTipoDiagnostico == null && other.codigoTipoDiagnostico != null) || (this.codigoTipoDiagnostico != null && !this.codigoTipoDiagnostico.equals(other.codigoTipoDiagnostico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
