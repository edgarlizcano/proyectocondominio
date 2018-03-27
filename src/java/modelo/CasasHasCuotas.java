/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "casas_has_cuotas")
@NamedQueries({
    @NamedQuery(name = "CasasHasCuotas.findAll", query = "SELECT c FROM CasasHasCuotas c")})
public class CasasHasCuotas implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private Casas casas;
    @JoinColumn(name = "Cuotas_idCuotas", referencedColumnName = "idCuotas", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cuotas cuotas;
    @JoinColumn(name = "Pagos_idPagos", referencedColumnName = "idPagos", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pagos pagos;

    public CasasHasCuotas() {
    }

    public CasasHasCuotas(Casas casas, Cuotas cuotas, Pagos pagos) {
        this.casas = casas;
        this.cuotas = cuotas;
        this.pagos = pagos;
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

    public Pagos getPagos() {
        return pagos;
    }

    public void setPagos(Pagos pagos) {
        this.pagos = pagos;
    }
    
}
