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
@Table(name = "cie10")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cie10.findAll", query = "SELECT c FROM Cie10 c")
    , @NamedQuery(name = "Cie10.findByCodigoCie10", query = "SELECT c FROM Cie10 c WHERE c.codigoCie10 = :codigoCie10")
    , @NamedQuery(name = "Cie10.findByNombre", query = "SELECT c FROM Cie10 c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cie10.findByActivo", query = "SELECT c FROM Cie10 c WHERE c.activo = :activo")
    , @NamedQuery(name = "Cie10.findByFechaRegistro", query = "SELECT c FROM Cie10 c WHERE c.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Cie10.findByFechaModificacion", query = "SELECT c FROM Cie10 c WHERE c.fechaModificacion = :fechaModificacion")})
public class Cie10 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Size(min = 1, max = 5)
    @Column(name = "codigoCie10")
    private String codigoCie10;
    @Size(max = 350)
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
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;
    @OneToMany(mappedBy = "codigoCie10F")
    private List<DiagnosticoIngreso> diagnosticoIngresoList;
    @OneToMany(mappedBy = "codigoCie10D")
    private List<DiagnosticoIngreso> diagnosticoIngresoList1;
    @OneToMany(mappedBy = "codigoCie10E")
    private List<DiagnosticoIngreso> diagnosticoIngresoList2;
    @OneToMany(mappedBy = "codigoCie10G")
    private List<DiagnosticoIngreso> diagnosticoIngresoList3;
    @OneToMany(mappedBy = "codigoCie10A")
    private List<DiagnosticoIngreso> diagnosticoIngresoList4;
    @OneToMany(mappedBy = "codigoCie10B")
    private List<DiagnosticoIngreso> diagnosticoIngresoList5;
    @OneToMany(mappedBy = "codigoCie10C")
    private List<DiagnosticoIngreso> diagnosticoIngresoList6;
    @OneToMany(mappedBy = "codigoCie10")
    private List<DiagnosticoIngreso> diagnosticoIngresoList7;
    @OneToMany(mappedBy = "codigoCie10")
    private List<Descripcioncie10> descripcioncie10List;

    public Cie10() {
    }

    public Cie10(String codigoCie10) {
        this.codigoCie10 = codigoCie10;
    }

    public String getCodigoCie10() {
        return codigoCie10;
    }

    public void setCodigoCie10(String codigoCie10) {
        this.codigoCie10 = codigoCie10;
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

    public Usuario getCodigoUsuarioModifico() {
        return codigoUsuarioModifico;
    }

    public void setCodigoUsuarioModifico(Usuario codigoUsuarioModifico) {
        this.codigoUsuarioModifico = codigoUsuarioModifico;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList() {
        return diagnosticoIngresoList;
    }

    public void setDiagnosticoIngresoList(List<DiagnosticoIngreso> diagnosticoIngresoList) {
        this.diagnosticoIngresoList = diagnosticoIngresoList;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList1() {
        return diagnosticoIngresoList1;
    }

    public void setDiagnosticoIngresoList1(List<DiagnosticoIngreso> diagnosticoIngresoList1) {
        this.diagnosticoIngresoList1 = diagnosticoIngresoList1;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList2() {
        return diagnosticoIngresoList2;
    }

    public void setDiagnosticoIngresoList2(List<DiagnosticoIngreso> diagnosticoIngresoList2) {
        this.diagnosticoIngresoList2 = diagnosticoIngresoList2;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList3() {
        return diagnosticoIngresoList3;
    }

    public void setDiagnosticoIngresoList3(List<DiagnosticoIngreso> diagnosticoIngresoList3) {
        this.diagnosticoIngresoList3 = diagnosticoIngresoList3;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList4() {
        return diagnosticoIngresoList4;
    }

    public void setDiagnosticoIngresoList4(List<DiagnosticoIngreso> diagnosticoIngresoList4) {
        this.diagnosticoIngresoList4 = diagnosticoIngresoList4;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList5() {
        return diagnosticoIngresoList5;
    }

    public void setDiagnosticoIngresoList5(List<DiagnosticoIngreso> diagnosticoIngresoList5) {
        this.diagnosticoIngresoList5 = diagnosticoIngresoList5;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList6() {
        return diagnosticoIngresoList6;
    }

    public void setDiagnosticoIngresoList6(List<DiagnosticoIngreso> diagnosticoIngresoList6) {
        this.diagnosticoIngresoList6 = diagnosticoIngresoList6;
    }

    @XmlTransient
    public List<DiagnosticoIngreso> getDiagnosticoIngresoList7() {
        return diagnosticoIngresoList7;
    }

    public void setDiagnosticoIngresoList7(List<DiagnosticoIngreso> diagnosticoIngresoList7) {
        this.diagnosticoIngresoList7 = diagnosticoIngresoList7;
    }

    @XmlTransient
    public List<Descripcioncie10> getDescripcioncie10List() {
        return descripcioncie10List;
    }

    public void setDescripcioncie10List(List<Descripcioncie10> descripcioncie10List) {
        this.descripcioncie10List = descripcioncie10List;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCie10 != null ? codigoCie10.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cie10)) {
            return false;
        }
        Cie10 other = (Cie10) object;
        if ((this.codigoCie10 == null && other.codigoCie10 != null) || (this.codigoCie10 != null && !this.codigoCie10.equals(other.codigoCie10))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoCie10 + " " + nombre;
    }

}
