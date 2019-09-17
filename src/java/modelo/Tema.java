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
@Table(name = "tema")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tema.findAll", query = "SELECT t FROM Tema t")
    , @NamedQuery(name = "Tema.findByTema", query = "SELECT t FROM Tema t WHERE t.tema = :tema")
    , @NamedQuery(name = "Tema.findByNombreTema", query = "SELECT t FROM Tema t WHERE t.nombreTema = :nombreTema")
    , @NamedQuery(name = "Tema.findByActivo", query = "SELECT t FROM Tema t WHERE t.activo = :activo")
    , @NamedQuery(name = "Tema.findByFechaRegistro", query = "SELECT t FROM Tema t WHERE t.fechaRegistro = :fechaRegistro")})
public class Tema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tema")
    private String tema;
    @Size(max = 100)
    @Column(name = "nombreTema")
    private String nombreTema;
    @Size(max = 1)
    @Column(name = "activo")
    private String activo;
    @Column(name = "fechaRegistro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @OneToMany(mappedBy = "codigoTema")
    private List<Usuario> usuarioList;
    @JoinColumn(name = "codigousuario", referencedColumnName = "codigoUsuario")
    @ManyToOne
    private Usuario codigousuario;

    public Tema() {
    }

    public Tema(String tema) {
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getNombreTema() {
        return nombreTema;
    }

    public void setNombreTema(String nombreTema) {
        this.nombreTema = nombreTema;
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

    @XmlTransient
    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Usuario getCodigousuario() {
        return codigousuario;
    }

    public void setCodigousuario(Usuario codigousuario) {
        this.codigousuario = codigousuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tema != null ? tema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tema)) {
            return false;
        }
        Tema other = (Tema) object;
        if ((this.tema == null && other.tema != null) || (this.tema != null && !this.tema.equals(other.tema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Tema[ tema=" + tema + " ]";
    }
    
}
