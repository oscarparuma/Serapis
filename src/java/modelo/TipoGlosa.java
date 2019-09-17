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
import javax.persistence.Lob;
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
@Table(name = "TipoGlosa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoGlosa.findAll", query = "SELECT t FROM TipoGlosa t")
    , @NamedQuery(name = "TipoGlosa.findByCodigoGlosa", query = "SELECT t FROM TipoGlosa t WHERE t.codigoGlosa = :codigoGlosa")
    , @NamedQuery(name = "TipoGlosa.findByCodigoGlosaEps", query = "SELECT t FROM TipoGlosa t WHERE t.codigoGlosaEps = :codigoGlosaEps")
    , @NamedQuery(name = "TipoGlosa.findByCodigoEps", query = "SELECT t FROM TipoGlosa t WHERE t.codigoEps = :codigoEps")
    , @NamedQuery(name = "Pais.findByActivo", query = "SELECT p FROM Pais p WHERE p.activo = :activo")
    , @NamedQuery(name = "TipoGlosa.findByCodigoEpsSede", query = "SELECT t FROM TipoGlosa t WHERE t.codigoEpsSede = :codigoEpsSede")})
public class TipoGlosa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoGlosa")
    private Integer codigoGlosa;
    @Size(max = 50)
    @Column(name = "codigoGlosaEps")
    private String codigoGlosaEps;
    @Column(name = "codigoEps")
    private Integer codigoEps;
    @Column(name = "codigoEpsSede")
    private Integer codigoEpsSede;
    @Lob
    @Column(name = "descripcionGlosa")
    private String descripcionGlosa;
    @OneToMany(mappedBy = "codigoTipoGlosa")
    private List<GlosaEps> glosaEpsList;
    @JoinColumn(name = "codigoSede", referencedColumnName = "codigoSede")
    @ManyToOne
    private Sede codigoSede;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;

    public TipoGlosa() {
    }

    public TipoGlosa(Integer codigoGlosa) {
        this.codigoGlosa = codigoGlosa;
    }

    public Integer getCodigoGlosa() {
        return codigoGlosa;
    }

    public void setCodigoGlosa(Integer codigoGlosa) {
        this.codigoGlosa = codigoGlosa;
    }

    public String getCodigoGlosaEps() {
        return codigoGlosaEps;
    }

    public void setCodigoGlosaEps(String codigoGlosaEps) {
        this.codigoGlosaEps = codigoGlosaEps;
    }

    public Integer getCodigoEps() {
        return codigoEps;
    }

    public void setCodigoEps(Integer codigoEps) {
        this.codigoEps = codigoEps;
    }

    public Integer getCodigoEpsSede() {
        return codigoEpsSede;
    }

    public void setCodigoEpsSede(Integer codigoEpsSede) {
        this.codigoEpsSede = codigoEpsSede;
    }

    public String getDescripcionGlosa() {
        return descripcionGlosa;
    }

    public void setDescripcionGlosa(String descripcionGlosa) {
        this.descripcionGlosa = descripcionGlosa;
    }

    public Sede getCodigoSede() {
        return codigoSede;
    }

    public void setCodigoSede(Sede codigoSede) {
        this.codigoSede = codigoSede;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }
    
    

    @XmlTransient
    public List<GlosaEps> getGlosaEpsList() {
        return glosaEpsList;
    }

    public void setGlosaEpsList(List<GlosaEps> glosaEpsList) {
        this.glosaEpsList = glosaEpsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoGlosa != null ? codigoGlosa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoGlosa)) {
            return false;
        }
        TipoGlosa other = (TipoGlosa) object;
        if ((this.codigoGlosa == null && other.codigoGlosa != null) || (this.codigoGlosa != null && !this.codigoGlosa.equals(other.codigoGlosa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcionGlosa;
    }

}
