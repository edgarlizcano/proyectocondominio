/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Edgar
 */
@Embeddable
public class VehiculosPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idVehiculos")
    private int idVehiculos;
    @Basic(optional = false)
    @Column(name = "Habitantes_idPersona")
    private int habitantesidPersona;
    @Basic(optional = false)
    @Column(name = "Habitantes_Casas_idCasa")
    private int habitantesCasasidCasa;

    public VehiculosPK() {
    }

    public VehiculosPK(int idVehiculos, int habitantesidPersona, int habitantesCasasidCasa) {
        this.idVehiculos = idVehiculos;
        this.habitantesidPersona = habitantesidPersona;
        this.habitantesCasasidCasa = habitantesCasasidCasa;
    }

    public int getIdVehiculos() {
        return idVehiculos;
    }

    public void setIdVehiculos(int idVehiculos) {
        this.idVehiculos = idVehiculos;
    }

    public int getHabitantesidPersona() {
        return habitantesidPersona;
    }

    public void setHabitantesidPersona(int habitantesidPersona) {
        this.habitantesidPersona = habitantesidPersona;
    }

    public int getHabitantesCasasidCasa() {
        return habitantesCasasidCasa;
    }

    public void setHabitantesCasasidCasa(int habitantesCasasidCasa) {
        this.habitantesCasasidCasa = habitantesCasasidCasa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idVehiculos;
        hash += (int) habitantesidPersona;
        hash += (int) habitantesCasasidCasa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VehiculosPK)) {
            return false;
        }
        VehiculosPK other = (VehiculosPK) object;
        if (this.idVehiculos != other.idVehiculos) {
            return false;
        }
        if (this.habitantesidPersona != other.habitantesidPersona) {
            return false;
        }
        if (this.habitantesCasasidCasa != other.habitantesCasasidCasa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.VehiculosPK[ idVehiculos=" + idVehiculos + ", habitantesidPersona=" + habitantesidPersona + ", habitantesCasasidCasa=" + habitantesCasasidCasa + " ]";
    }
    
}
