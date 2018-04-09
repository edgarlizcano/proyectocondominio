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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "habitantes")
@NamedQueries({
    @NamedQuery(name = "Habitantes.findAll", query = "SELECT h FROM Habitantes h")})
public class Habitantes implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHabitante")
    private int idHabitante;
    
    @Basic(optional = false)
    @Column(name = "cedula")
    private String cedula;
    
    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;
    
    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;
    
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    
    @Basic(optional = false)
    @Column(name = "celular")
    private String celular;
    
    @Basic(optional = false)
    @Column(name = "fechaNacimiento")
    private String fechaNacimiento;
    
    @JoinColumn(name = "Casas_idCasa", referencedColumnName = "idCasa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Casas casas;
    
    @JoinColumn(name = "Usuarios_idUsuario", referencedColumnName = "idUsuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuarios usuarios;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habitantes")
    private List<Vehiculos> vehiculosList;
    
    private boolean estatus;

    public Habitantes() {
    }


    public Habitantes(int idHabitante, String cedula, String nombres, String apellidos, String fechaNacimiento) {
        this.idHabitante = idHabitante;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public int getIdHabitante() {
        return idHabitante;
    }

    public void setIdHabitante(int idHabitante) {
        this.idHabitante = idHabitante;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Casas getCasas() {
        return casas;
    }

    public void setCasas(Casas casas) {
        this.casas = casas;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public List<Vehiculos> getVehiculosList() {
        return vehiculosList;
    }

    public void setVehiculosList(List<Vehiculos> vehiculosList) {
        this.vehiculosList = vehiculosList;
    }
}
