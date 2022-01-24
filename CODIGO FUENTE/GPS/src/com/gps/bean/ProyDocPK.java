/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class ProyDocPK implements Serializable {
    
    private BigDecimal idProyecto;
    private BigDecimal idDocente;

    public ProyDocPK() {
    }

    public ProyDocPK(BigDecimal idProyecto, BigDecimal idDocente) {
        this.idProyecto = idProyecto;
        this.idDocente = idDocente;
    }

    public BigDecimal getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigDecimal idProyecto) {
        this.idProyecto = idProyecto;
    }

    public BigDecimal getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(BigDecimal idDocente) {
        this.idDocente = idDocente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        hash += (idDocente != null ? idDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyDocPK)) {
            return false;
        }
        ProyDocPK other = (ProyDocPK) object;
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        if ((this.idDocente == null && other.idDocente != null) || (this.idDocente != null && !this.idDocente.equals(other.idDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.ProyDocPK[ idProyecto=" + idProyecto + ", idDocente=" + idDocente + " ]";
    }
    
}
