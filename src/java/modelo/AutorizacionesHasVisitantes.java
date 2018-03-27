/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Edgar
 */
@Entity
@Table(name = "autorizaciones_has_visitantes")
@NamedQueries({
    @NamedQuery(name = "AutorizacionesHasVisitantes.findAll", query = "SELECT a FROM AutorizacionesHasVisitantes a")})
public class AutorizacionesHasVisitantes implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AutorizacionesHasVisitantesPK autorizacionesHasVisitantesPK;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @JoinColumn(name = "Autorizaciones_idAutorizaciones", referencedColumnName = "idAutorizaciones", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Autorizaciones autorizaciones;
    @JoinColumn(name = "Visitantes_idVisitantes", referencedColumnName = "idVisitantes", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Visitantes visitantes;

    public AutorizacionesHasVisitantes() {
    }

    public AutorizacionesHasVisitantes(AutorizacionesHasVisitantesPK autorizacionesHasVisitantesPK) {
        this.autorizacionesHasVisitantesPK = autorizacionesHasVisitantesPK;
    }

    public AutorizacionesHasVisitantes(int autorizacionesidAutorizaciones, int visitantesidVisitantes) {
        this.autorizacionesHasVisitantesPK = new AutorizacionesHasVisitantesPK(autorizacionesidAutorizaciones, visitantesidVisitantes);
    }

    public AutorizacionesHasVisitantesPK getAutorizacionesHasVisitantesPK() {
        return autorizacionesHasVisitantesPK;
    }

    public void setAutorizacionesHasVisitantesPK(AutorizacionesHasVisitantesPK autorizacionesHasVisitantesPK) {
        this.autorizacionesHasVisitantesPK = autorizacionesHasVisitantesPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Autorizaciones getAutorizaciones() {
        return autorizaciones;
    }

    public void setAutorizaciones(Autorizaciones autorizaciones) {
        this.autorizaciones = autorizaciones;
    }

    public Visitantes getVisitantes() {
        return visitantes;
    }

    public void setVisitantes(Visitantes visitantes) {
        this.visitantes = visitantes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (autorizacionesHasVisitantesPK != null ? autorizacionesHasVisitantesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AutorizacionesHasVisitantes)) {
            return false;
        }
        AutorizacionesHasVisitantes other = (AutorizacionesHasVisitantes) object;
        if ((this.autorizacionesHasVisitantesPK == null && other.autorizacionesHasVisitantesPK != null) || (this.autorizacionesHasVisitantesPK != null && !this.autorizacionesHasVisitantesPK.equals(other.autorizacionesHasVisitantesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelo.AutorizacionesHasVisitantes[ autorizacionesHasVisitantesPK=" + autorizacionesHasVisitantesPK + " ]";
    }
    
}
