/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "vehiculos")
@NamedQueries({
    @NamedQuery(name = "Vehiculos.findAll", query = "SELECT v FROM Vehiculos v")})
public class Vehiculos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VehiculosPK vehiculosPK;
    @Basic(optional = false)
    @Column(name = "modelo")
    private String modelo;
    @Basic(optional = false)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "placa")
    private String placa;
    @JoinColumns({
        @JoinColumn(name = "Habitantes_idPersona", referencedColumnName = "idHabitante", insertable = false, updatable = false)
        , @JoinColumn(name = "Habitantes_Casas_idCasa", referencedColumnName = "Casas_idCasa", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Habitantes habitantes;

    public Vehiculos() {
    }

    public Vehiculos(VehiculosPK vehiculosPK) {
        this.vehiculosPK = vehiculosPK;
    }

    public Vehiculos(VehiculosPK vehiculosPK, String modelo, String marca, String placa) {
        this.vehiculosPK = vehiculosPK;
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
    }

    public Vehiculos(int idVehiculos, int habitantesidPersona, int habitantesCasasidCasa) {
        this.vehiculosPK = new VehiculosPK(idVehiculos, habitantesidPersona, habitantesCasasidCasa);
    }

    public VehiculosPK getVehiculosPK() {
        return vehiculosPK;
    }

    public void setVehiculosPK(VehiculosPK vehiculosPK) {
        this.vehiculosPK = vehiculosPK;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Habitantes getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(Habitantes habitantes) {
        this.habitantes = habitantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vehiculosPK != null ? vehiculosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiculos)) {
            return false;
        }
        Vehiculos other = (Vehiculos) object;
        if ((this.vehiculosPK == null && other.vehiculosPK != null) || (this.vehiculosPK != null && !this.vehiculosPK.equals(other.vehiculosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Vehiculos[ vehiculosPK=" + vehiculosPK + " ]";
    }
    
}
