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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ANPILU
 */
@Entity
@Table(name = "descripcioncie10")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Descripcioncie10.findAll", query = "SELECT d FROM Descripcioncie10 d")
    , @NamedQuery(name = "Descripcioncie10.findByCodigoDescripcionCie10", query = "SELECT d FROM Descripcioncie10 d WHERE d.codigoDescripcionCie10 = :codigoDescripcionCie10")
    , @NamedQuery(name = "Descripcioncie10.findByNombreDescripcion", query = "SELECT d FROM Descripcioncie10 d WHERE d.nombreDescripcion = :nombreDescripcion")
    , @NamedQuery(name = "Descripcioncie10.findByActivo", query = "SELECT d FROM Descripcioncie10 d WHERE d.activo = :activo")
    , @NamedQuery(name = "Descripcioncie10.findByFechaRegistro", query = "SELECT d FROM Descripcioncie10 d WHERE d.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "Descripcioncie10.findByFechaModificacion", query = "SELECT d FROM Descripcioncie10 d WHERE d.fechaModificacion = :fechaModificacion")})
public class Descripcioncie10 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Size(min = 1, max = 10)
    @Column(name = "codigoDescripcionCie10")
    private String codigoDescripcionCie10;
    @Size(max = 350)
    @Column(name = "nombreDescripcion")
    private String nombreDescripcion;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Column(name = "fechaModificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "clasificacionCie10A")
    private List<DiagnosticoIngreso> diagnosticoIngresoList;
    @OneToMany(mappedBy = "clasificacionCie10F")
    private List<DiagnosticoIngreso> diagnosticoIngresoList1;
    @OneToMany(mappedBy = "clasificacionCie10C")
    private List<DiagnosticoIngreso> diagnosticoIngresoList2;
    @OneToMany(mappedBy = "clasificacionCie10D")
    private List<DiagnosticoIngreso> diagnosticoIngresoList3;
    @OneToMany(mappedBy = "clasificacionCie10E")
    private List<DiagnosticoIngreso> diagnosticoIngresoList4;
    @OneToMany(mappedBy = "clasificacionCie10")
    private List<DiagnosticoIngreso> diagnosticoIngresoList5;
    @OneToMany(mappedBy = "clasificacionCie10B")
    private List<DiagnosticoIngreso> diagnosticoIngresoList6;
    @OneToMany(mappedBy = "clasificacionCie10G")
    private List<DiagnosticoIngreso> diagnosticoIngresoList7;
    @JoinColumn(name = "codigoCie10", referencedColumnName = "codigoCie10")
    @ManyToOne
    private Cie10 codigoCie10;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigoUsuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuarioModifico;

    public Descripcioncie10() {
    }

    public Descripcioncie10(String codigoDescripcionCie10) {
        this.codigoDescripcionCie10 = codigoDescripcionCie10;
    }

    public String getCodigoDescripcionCie10() {
        return codigoDescripcionCie10;
    }

    public void setCodigoDescripcionCie10(String codigoDescripcionCie10) {
        this.codigoDescripcionCie10 = codigoDescripcionCie10;
    }

    public String getNombreDescripcion() {
        return nombreDescripcion;
    }

    public void setNombreDescripcion(String nombreDescripcion) {
        this.nombreDescripcion = nombreDescripcion;
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

    public Cie10 getCodigoCie10() {
        return codigoCie10;
    }

    public void setCodigoCie10(Cie10 codigoCie10) {
        this.codigoCie10 = codigoCie10;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoDescripcionCie10 != null ? codigoDescripcionCie10.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Descripcioncie10)) {
            return false;
        }
        Descripcioncie10 other = (Descripcioncie10) object;
        if ((this.codigoDescripcionCie10 == null && other.codigoDescripcionCie10 != null) || (this.codigoDescripcionCie10 != null && !this.codigoDescripcionCie10.equals(other.codigoDescripcionCie10))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoDescripcionCie10 + " " + nombreDescripcion;
    }

}
