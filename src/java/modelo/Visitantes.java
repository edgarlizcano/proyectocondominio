/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "visitantes")
@NamedQueries({
    @NamedQuery(name = "Visitantes.findAll", query = "SELECT v FROM Visitantes v")})
public class Visitantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idVisitantes")
    private Integer idVisitantes;
    @Basic(optional = false)
    @Column(name = "cedula")
    private String cedula;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visitantes")
    private List<AutorizacionesHasVisitantes> autorizacionesHasVisitantesList;

    public Visitantes() {
    }

    public Visitantes(Integer idVisitantes) {
        this.idVisitantes = idVisitantes;
    }

    public Visitantes(Integer idVisitantes, String cedula, String nombre, String apellido) {
        this.idVisitantes = idVisitantes;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Integer getIdVisitantes() {
        return idVisitantes;
    }

    public void setIdVisitantes(Integer idVisitantes) {
        this.idVisitantes = idVisitantes;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<AutorizacionesHasVisitantes> getAutorizacionesHasVisitantesList() {
        return autorizacionesHasVisitantesList;
    }

    public void setAutorizacionesHasVisitantesList(List<AutorizacionesHasVisitantes> autorizacionesHasVisitantesList) {
        this.autorizacionesHasVisitantesList = autorizacionesHasVisitantesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVisitantes != null ? idVisitantes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visitantes)) {
            return false;
        }
        Visitantes other = (Visitantes) object;
        if ((this.idVisitantes == null && other.idVisitantes != null) || (this.idVisitantes != null && !this.idVisitantes.equals(other.idVisitantes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Visitantes[ idVisitantes=" + idVisitantes + " ]";
    }
    
}
