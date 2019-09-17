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
@Table(name = "TipoArchivoRips")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoArchivoRips.findAll", query = "SELECT t FROM TipoArchivoRips t")
    , @NamedQuery(name = "TipoArchivoRips.findByCodigoArchivoRips", query = "SELECT t FROM TipoArchivoRips t WHERE t.codigoArchivoRips = :codigoArchivoRips")
    , @NamedQuery(name = "TipoArchivoRips.findByNombre", query = "SELECT t FROM TipoArchivoRips t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TipoArchivoRips.findByAcivoVarchar", query = "SELECT t FROM TipoArchivoRips t WHERE t.acivoVarchar = :acivoVarchar")})
public class TipoArchivoRips implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoArchivoRips")
    private Integer codigoArchivoRips;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 1)
    @Column(name = "acivoVarchar")
    private String acivoVarchar;
    @OneToMany(mappedBy = "codigoRipsAH")
    private List<Citapersona> citapersonaList;
    @OneToMany(mappedBy = "codigoRipsAM")
    private List<Citapersona> citapersonaList1;
    @OneToMany(mappedBy = "codigoRipsAN")
    private List<Citapersona> citapersonaList2;
    @OneToMany(mappedBy = "codigoRipsAT")
    private List<Citapersona> citapersonaList3;
    @OneToMany(mappedBy = "codigoRipsAC")
    private List<Citapersona> citapersonaList4;
    @OneToMany(mappedBy = "codigoRipsCT")
    private List<Citapersona> citapersonaList5;
    @OneToMany(mappedBy = "codigoRipsUS")
    private List<Citapersona> citapersonaList6;
    @OneToMany(mappedBy = "codigoRipsAP")
    private List<Citapersona> citapersonaList7;
    @OneToMany(mappedBy = "codigoRipsAU")
    private List<Citapersona> citapersonaList8;
    @OneToMany(mappedBy = "codigoRipsAF")
    private List<Citapersona> citapersonaList9;
    @OneToMany(mappedBy = "codigoArchivoRips")
    private List<Procedimientosede> procedimientosedeList;

    public TipoArchivoRips() {
    }

    public TipoArchivoRips(Integer codigoArchivoRips) {
        this.codigoArchivoRips = codigoArchivoRips;
    }

    public Integer getCodigoArchivoRips() {
        return codigoArchivoRips;
    }

    public void setCodigoArchivoRips(Integer codigoArchivoRips) {
        this.codigoArchivoRips = codigoArchivoRips;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcivoVarchar() {
        return acivoVarchar;
    }

    public void setAcivoVarchar(String acivoVarchar) {
        this.acivoVarchar = acivoVarchar;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList() {
        return citapersonaList;
    }

    public void setCitapersonaList(List<Citapersona> citapersonaList) {
        this.citapersonaList = citapersonaList;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList1() {
        return citapersonaList1;
    }

    public void setCitapersonaList1(List<Citapersona> citapersonaList1) {
        this.citapersonaList1 = citapersonaList1;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList2() {
        return citapersonaList2;
    }

    public void setCitapersonaList2(List<Citapersona> citapersonaList2) {
        this.citapersonaList2 = citapersonaList2;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList3() {
        return citapersonaList3;
    }

    public void setCitapersonaList3(List<Citapersona> citapersonaList3) {
        this.citapersonaList3 = citapersonaList3;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList4() {
        return citapersonaList4;
    }

    public void setCitapersonaList4(List<Citapersona> citapersonaList4) {
        this.citapersonaList4 = citapersonaList4;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList5() {
        return citapersonaList5;
    }

    public void setCitapersonaList5(List<Citapersona> citapersonaList5) {
        this.citapersonaList5 = citapersonaList5;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList6() {
        return citapersonaList6;
    }

    public void setCitapersonaList6(List<Citapersona> citapersonaList6) {
        this.citapersonaList6 = citapersonaList6;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList7() {
        return citapersonaList7;
    }

    public void setCitapersonaList7(List<Citapersona> citapersonaList7) {
        this.citapersonaList7 = citapersonaList7;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList8() {
        return citapersonaList8;
    }

    public void setCitapersonaList8(List<Citapersona> citapersonaList8) {
        this.citapersonaList8 = citapersonaList8;
    }

    @XmlTransient
    public List<Citapersona> getCitapersonaList9() {
        return citapersonaList9;
    }

    public void setCitapersonaList9(List<Citapersona> citapersonaList9) {
        this.citapersonaList9 = citapersonaList9;
    }

    @XmlTransient
    public List<Procedimientosede> getProcedimientosedeList() {
        return procedimientosedeList;
    }

    public void setProcedimientosedeList(List<Procedimientosede> procedimientosedeList) {
        this.procedimientosedeList = procedimientosedeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoArchivoRips != null ? codigoArchivoRips.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoArchivoRips)) {
            return false;
        }
        TipoArchivoRips other = (TipoArchivoRips) object;
        if ((this.codigoArchivoRips == null && other.codigoArchivoRips != null) || (this.codigoArchivoRips != null && !this.codigoArchivoRips.equals(other.codigoArchivoRips))) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return nombre;
    }
    
}
