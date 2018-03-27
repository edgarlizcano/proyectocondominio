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
@Table(name = "bancos")
@NamedQueries({
    @NamedQuery(name = "Bancos.findAll", query = "SELECT b FROM Bancos b")})
public class Bancos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idBancos")
    private Integer idBancos;
    @Basic(optional = false)
    @Column(name = "nombreBanco")
    private String nombreBanco;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bancosidBancos")
    private List<Pagos> pagosList;

    public Bancos() {
    }

    public Bancos(Integer idBancos) {
        this.idBancos = idBancos;
    }

    public Bancos(Integer idBancos, String nombreBanco) {
        this.idBancos = idBancos;
        this.nombreBanco = nombreBanco;
    }

    public Integer getIdBancos() {
        return idBancos;
    }

    public void setIdBancos(Integer idBancos) {
        this.idBancos = idBancos;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public List<Pagos> getPagosList() {
        return pagosList;
    }

    public void setPagosList(List<Pagos> pagosList) {
        this.pagosList = pagosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBancos != null ? idBancos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bancos)) {
            return false;
        }
        Bancos other = (Bancos) object;
        if ((this.idBancos == null && other.idBancos != null) || (this.idBancos != null && !this.idBancos.equals(other.idBancos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.Bancos[ idBancos=" + idBancos + " ]";
    }
    
}
