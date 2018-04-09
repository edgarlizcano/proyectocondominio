/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name = "pagos")
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int idPagos;
    @Basic(optional = false)
    @Column(name = "monto")
    private double monto;
    
    private boolean estatus;
    
    @Basic(optional = false)
    @Column(name = "fecha")
    private String fecha;
    @Basic(optional = false)
    @Column(name = "cedulaDepositante")
    private String cedulaDepositante;
    @Basic(optional = false)
    @Column(name = "nombre_apellido")
    private String nombreApellido;
    @Column(name = "referencia")
    private String referencia;
    @JoinColumn(name = "banco", referencedColumnName = "idBancos")
    @ManyToOne(optional = false)
    private Bancos banco;

    public Pagos() {
    }

    public Pagos(int idPagos, double monto, String fecha, String cedulaDepositante, String nombreApellido, String referencia, Bancos bancosidBancos) {
        this.idPagos = idPagos;
        this.monto = monto;
        this.fecha = fecha;
        this.cedulaDepositante = cedulaDepositante;
        this.nombreApellido = nombreApellido;
        this.referencia = referencia;
        this.banco = bancosidBancos;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }
    
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCedulaDepositante() {
        return cedulaDepositante;
    }

    public void setCedulaDepositante(String cedulaDepositante) {
        this.cedulaDepositante = cedulaDepositante;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public Bancos getBanco() {
        return banco;
    }

    public void setBanco(Bancos banco) {
        this.banco = banco;
    }

    public int getIdPagos() {
        return idPagos;
    }

    public void setIdPagos(int idPagos) {
        this.idPagos = idPagos;
    }
    
}
