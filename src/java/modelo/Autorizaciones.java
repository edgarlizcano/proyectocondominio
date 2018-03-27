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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "autorizaciones")
@NamedQueries({
    @NamedQuery(name = "Autorizaciones.findAll", query = "SELECT a FROM Autorizaciones a")})
public class Autorizaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idAutorizaciones")
    private Integer idAutorizaciones;
    @JoinColumns({
        @JoinColumn(name = "Habitantes_idPersona", referencedColumnName = "idHabitante")
        , @JoinColumn(name = "Habitantes_Casas_idCasa", referencedColumnName = "Casas_idCasa")
        , @JoinColumn(name = "Habitantes_Usuarios_idUsuario", referencedColumnName = "Usuarios_idUsuario")})
    @ManyToOne(optional = false)
    private Habitantes habitantes;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "autorizaciones")
    private List<AutorizacionesHasVisitantes> autorizacionesHasVisitantesList;

    public Autorizaciones() {
    }

    public Autorizaciones(Integer idAutorizaciones) {
        this.idAutorizaciones = idAutorizaciones;
    }

    public Integer getIdAutorizaciones() {
        return idAutorizaciones;
    }

    public void setIdAutorizaciones(Integer idAutorizaciones) {
        this.idAutorizaciones = idAutorizaciones;
    }

    public Habitantes getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(Habitantes habitantes) {
        this.habitantes = habitantes;
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
        hash += (idAutorizaciones != null ? idAutorizaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Autorizaciones)) {
            return false;
        }
        Autorizaciones other = (Autorizaciones) object;
        if ((this.idAutorizaciones == null && other.idAutorizaciones != null) || (this.idAutorizaciones != null && !this.idAutorizaciones.equals(other.idAutorizaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Autorizaciones[ idAutorizaciones=" + idAutorizaciones + " ]";
    }
    
}
