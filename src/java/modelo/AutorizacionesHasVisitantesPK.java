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
public class AutorizacionesHasVisitantesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "Autorizaciones_idAutorizaciones")
    private int autorizacionesidAutorizaciones;
    @Basic(optional = false)
    @Column(name = "Visitantes_idVisitantes")
    private int visitantesidVisitantes;

    public AutorizacionesHasVisitantesPK() {
    }

    public AutorizacionesHasVisitantesPK(int autorizacionesidAutorizaciones, int visitantesidVisitantes) {
        this.autorizacionesidAutorizaciones = autorizacionesidAutorizaciones;
        this.visitantesidVisitantes = visitantesidVisitantes;
    }

    public int getAutorizacionesidAutorizaciones() {
        return autorizacionesidAutorizaciones;
    }

    public void setAutorizacionesidAutorizaciones(int autorizacionesidAutorizaciones) {
        this.autorizacionesidAutorizaciones = autorizacionesidAutorizaciones;
    }

    public int getVisitantesidVisitantes() {
        return visitantesidVisitantes;
    }

    public void setVisitantesidVisitantes(int visitantesidVisitantes) {
        this.visitantesidVisitantes = visitantesidVisitantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) autorizacionesidAutorizaciones;
        hash += (int) visitantesidVisitantes;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorizacionesHasVisitantesPK)) {
            return false;
        }
        AutorizacionesHasVisitantesPK other = (AutorizacionesHasVisitantesPK) object;
        if (this.autorizacionesidAutorizaciones != other.autorizacionesidAutorizaciones) {
            return false;
        }
        if (this.visitantesidVisitantes != other.visitantesidVisitantes) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AutorizacionesHasVisitantesPK[ autorizacionesidAutorizaciones=" + autorizacionesidAutorizaciones + ", visitantesidVisitantes=" + visitantesidVisitantes + " ]";
    }
    
}
