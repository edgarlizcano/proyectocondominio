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
@Table(name = "calles")
@NamedQueries({
    @NamedQuery(name = "Calles.findAll", query = "SELECT c FROM Calles c")})
public class Calles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCalles")
    private Integer idCalles;
    @Basic(optional = false)
    @Column(name = "nombreCalle")
    private String nombreCalle;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calles")
    private List<Casas> casasList;

    public Calles() {
    }

    public Calles(Integer idCalles) {
        this.idCalles = idCalles;
    }

    public Calles(Integer idCalles, String nombreCalle) {
        this.idCalles = idCalles;
        this.nombreCalle = nombreCalle;
    }

    public Integer getIdCalles() {
        return idCalles;
    }

    public void setIdCalles(Integer idCalles) {
        this.idCalles = idCalles;
    }

    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public List<Casas> getCasasList() {
        return casasList;
    }

    public void setCasasList(List<Casas> casasList) {
        this.casasList = casasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCalles != null ? idCalles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calles)) {
            return false;
        }
        Calles other = (Calles) object;
        if ((this.idCalles == null && other.idCalles != null) || (this.idCalles != null && !this.idCalles.equals(other.idCalles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Calles[ idCalles=" + idCalles + " ]";
    }
    
}
