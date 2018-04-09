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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    
    private Integer idPerfil;
    
    @Basic(optional = false)
    @Column(name = "nombreUsuario")
    private String nombreUsuario;
    
    @Basic(optional = false)
    @Column(name = "clave")
    private String clave;
    
    @Basic(optional = false)
    @Column(name = "ultimaConexion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaConexion;
    
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "estatus")
    private boolean estatus;
    
    
    private Habitantes habitante;

    public Usuarios() {
    }

    public Usuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(Integer idUsuario, String nombreUsuario, String clave, Date ultimaConexion, String email, boolean estatus) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.ultimaConexion = ultimaConexion;
        this.email = email;
        this.estatus = estatus;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }
    
    

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Habitantes getHabitante() {
        return habitante;
    }

    public void setHabitante(Habitantes h) {
        this.habitante = h;
    }   
}
