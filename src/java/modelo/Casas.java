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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
@Table(name = "casas")
@NamedQueries({
    @NamedQuery(name = "Casas.findAll", query = "SELECT c FROM Casas c")})
public class Casas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected int idCasas;

    @Basic(optional = false)
    @Column(name = "nombreCasa")
    private String nombreCasa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "casas")
    private List<Habitantes> habitantesList;
    @JoinColumn(name = "Calles_idCalles", referencedColumnName = "idCalles", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Calles calles;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "casas")
    private List<CasasHasCuotas> casasHasCuotasList;

    public Casas() {
    }

    public Casas(int idCasas, String nombreCasa) {
        this.idCasas = idCasas;
        this.nombreCasa = nombreCasa;
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
