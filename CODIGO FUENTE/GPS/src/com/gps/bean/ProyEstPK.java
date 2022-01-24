/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class ProyEstPK implements Serializable {
    
    private BigDecimal idProyecto;
    private BigDecimal idEstudiante;

    public ProyEstPK() {
    }

    public ProyEstPK(BigDecimal idProyecto, BigDecimal idEstudiante) {
        this.idProyecto = idProyecto;
        this.idEstudiante = idEstudiante;
    }

    public BigDecimal getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigDecimal idProyecto) {
        this.idProyecto = idProyecto;
    }

    public BigDecimal getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(BigDecimal idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        
        hash += (idProyecto != null ? idProyecto.hashCode() : 0);
        hash += (idEstudiante != null ? idEstudiante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyEstPK)) {
            return false;
        }
        ProyEstPK other = (ProyEstPK) object;
        
        
        if ((this.idProyecto == null && other.idProyecto != null) || (this.idProyecto != null && !this.idProyecto.equals(other.idProyecto))) {
            return false;
        }
        if ((this.idEstudiante == null && other.idEstudiante != null) || (this.idEstudiante != null && !this.idEstudiante.equals(other.idEstudiante))) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.ProyEstPK[ idProyecto=" + idProyecto + ", idEstudiante="+ idEstudiante +" ]";
    }
    
}
