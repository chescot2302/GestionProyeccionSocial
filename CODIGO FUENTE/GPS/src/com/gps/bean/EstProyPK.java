/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class EstProyPK implements Serializable {
    
    private BigDecimal idProyecto;
    private BigDecimal idEstado;

    public EstProyPK() {
    }

    public EstProyPK(BigDecimal idEstado, BigDecimal idProyecto) {
        this.idProyecto = idProyecto;
        this.idEstado = idEstado;
    }

    public BigDecimal getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigDecimal idProyecto) {
        this.idProyecto = idProyecto;
    }

    public BigDecimal getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(BigDecimal idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstado != null ? idEstado.hashCode() : 0);
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstProyPK)) {
            return false;
        }
        EstProyPK other = (EstProyPK) object;
        
        if ((this.idEstado == null && other.idEstado != null) || (this.idEstado != null && !this.idEstado.equals(other.idEstado))) {
            return false;
        }
        
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.EstProyPK[ idEstado=" + idEstado + ", idProyecto=" + idProyecto + " ]";
    }
    
}

