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
@Table(name = "MedioPago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedioPago.findAll", query = "SELECT m FROM MedioPago m")
    , @NamedQuery(name = "MedioPago.findByCodigoMedioPago", query = "SELECT m FROM MedioPago m WHERE m.codigoMedioPago = :codigoMedioPago")
    , @NamedQuery(name = "MedioPago.findByNombre", query = "SELECT m FROM MedioPago m WHERE m.nombre = :nombre")
    , @NamedQuery(name = "MedioPago.findByActivo", query = "SELECT m FROM MedioPago m WHERE m.activo = :activo")
    , @NamedQuery(name = "MedioPago.findByFechaRegistro", query = "SELECT m FROM MedioPago m WHERE m.fechaRegistro = :fechaRegistro")
    , @NamedQuery(name = "MedioPago.findByFechaModificacion", query = "SELECT m FROM MedioPago m WHERE m.fechaModificacion = :fechaModificacion")})
public class MedioPago implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codigoMedioPago")
    private Integer codigoMedioPago;
    @Size(max = 250)
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
    @OneToMany(mappedBy = "codigoMedioPago")
    private List<CarteraSede> carteraSedeList;
    @JoinColumn(name = "codigoUsuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigoUsuario;
    @JoinColumn(name = "codigousuarioModifico", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigousuarioModifico;

    public MedioPago() {
    }

    public MedioPago(Integer codigoMedioPago) {
        this.codigoMedioPago = codigoMedioPago;
    }

    public Integer getCodigoMedioPago() {
        return codigoMedioPago;
    }

    public void setCodigoMedioPago(Integer codigoMedioPago) {
        this.codigoMedioPago = codigoMedioPago;
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

    @XmlTransient
    public List<CarteraSede> getCarteraSedeList() {
        return carteraSedeList;
    }

    public void setCarteraSedeList(List<CarteraSede> carteraSedeList) {
        this.carteraSedeList = carteraSedeList;
    }

    public Usuario getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Usuario codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public Usuario getCodigousuarioModifico() {
        return codigousuarioModifico;
    }

    public void setCodigousuarioModifico(Usuario codigousuarioModifico) {
        this.codigousuarioModifico = codigousuarioModifico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoMedioPago != null ? codigoMedioPago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedioPago)) {
            return false;
        }
        MedioPago other = (MedioPago) object;
        if ((this.codigoMedioPago == null && other.codigoMedioPago != null) || (this.codigoMedioPago != null && !this.codigoMedioPago.equals(other.codigoMedioPago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
         return nombre;
    }

}
