/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Edgar
 */

public class CasasHasCuotas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Casas casas;
    private Cuotas cuotas;
    private boolean estadoPago;

    public CasasHasCuotas() {
    }

    public CasasHasCuotas(Casas casas, Cuotas cuotas, boolean estadoPagos) {
        this.casas = casas;
        this.cuotas = cuotas;
        this.estadoPago = estadoPagos;
    }

    public Casas getCasas() {
        return casas;
    }

    public void setCasas(Casas casas) {
        this.casas = casas;
    }

    public Cuotas getCuotas() {
        return cuotas;
    }

    public void setCuotas(Cuotas cuotas) {
        this.cuotas = cuotas;
    }

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }
    
}
