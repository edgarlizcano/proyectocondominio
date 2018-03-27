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
@Table(name = "cuotas")
@NamedQueries({
    @NamedQuery(name = "Cuotas.findAll", query = "SELECT c FROM Cuotas c")})
public class Cuotas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCuotas")
    private Integer idCuotas;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "fecha")
    private String fecha;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cuotas")
    private List<CasasHasCuotas> casasHasCuotasList;

    public Cuotas() {
    }

    public Cuotas(Integer idCuotas) {
        this.idCuotas = idCuotas;
    }

    public Cuotas(Integer idCuotas, String nombre, String fecha, double monto) {
        this.idCuotas = idCuotas;
        this.nombre = nombre;
        this.fecha = fecha;
        this.monto = monto;
    }

    public Integer getIdCuotas() {
        return idCuotas;
    }

    public void setIdCuotas(Integer idCuotas) {
        this.idCuotas = idCuotas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public List<CasasHasCuotas> getCasasHasCuotasList() {
        return casasHasCuotasList;
    }

    public void setCasasHasCuotasList(List<CasasHasCuotas> casasHasCuotasList) {
        this.casasHasCuotasList = casasHasCuotasList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuotas != null ? idCuotas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuotas)) {
            return false;
        }
        Cuotas other = (Cuotas) object;
        if ((this.idCuotas == null && other.idCuotas != null) || (this.idCuotas != null && !this.idCuotas.equals(other.idCuotas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Cuotas[ idCuotas=" + idCuotas + " ]";
    }
    
}
