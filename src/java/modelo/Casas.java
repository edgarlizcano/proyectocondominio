/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;

/**
 *
 * @author Edgar
 */

public class Casas implements Serializable {

    @EmbeddedId
    protected int idCasas;

    @Basic(optional = false)
    @Column(name = "nombreCasa")
    private String nombreCasa;

    private List<Habitantes> habitantesList;
    
    private Calles calles;

    private List<CasasHasCuotas> casasHasCuotasList;
    
    private Cuentas cuenta;

    public Casas() {
    }

    public Casas(int idCasas, String nombreCasa) {
        this.idCasas = idCasas;
        this.nombreCasa = nombreCasa;
    }

    public Cuentas getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuentas cuenta) {
        this.cuenta = cuenta;
    }
    
    public int getIdCasas() {
        return idCasas;
    }

    public void setIdCasas(int idCasas) {
        this.idCasas = idCasas;
    }

    public String getNombreCasa() {
        return nombreCasa;
    }

    public void setNombreCasa(String nombreCasa) {
        this.nombreCasa = nombreCasa;
    }

    public List<Habitantes> getHabitantesList() {
        return habitantesList;
    }

    public void setHabitantesList(List<Habitantes> habitantesList) {
        this.habitantesList = habitantesList;
    }

    public Calles getCalles() {
        return calles;
    }

    public void setCalles(Calles calles) {
        this.calles = calles;
    }

    public List<CasasHasCuotas> getCasasHasCuotasList() {
        return casasHasCuotasList;
    }

    public void setCasasHasCuotasList(List<CasasHasCuotas> casasHasCuotasList) {
        this.casasHasCuotasList = casasHasCuotasList;
    }

}
