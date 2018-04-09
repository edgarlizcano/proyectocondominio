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
    private int idVehiculos;
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

    public Vehiculos(int idVehiculos) {
        this.idVehiculos = idVehiculos;
    }

    public Vehiculos(int idVehiculos, String modelo, String marca, String placa) {
        this.idVehiculos = idVehiculos;
        this.modelo = modelo;
        this.marca = marca;
        this.placa = placa;
    }


    public int getidVehiculos() {
        return idVehiculos;
    }

    public void setidVehiculos(int idVehiculos) {
        this.idVehiculos = idVehiculos;
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
    
}
