/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class EstProy implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected EstProyPK estProyPK;
    private String estadoactual="D";
    private java.sql.Date fecha;
    private Proyecto proyecto;
    private Estado estado;
    private String estadoOperacion="n";
    private boolean seleccion;

    public EstProy() {
    }

    public EstProy(EstProyPK estProyPK) {
        this.estProyPK = estProyPK;
    }

    public EstProy(BigDecimal idEstado, BigDecimal idProyecto) {
        this.estProyPK = new EstProyPK(idEstado, idProyecto);
    }

    public EstProyPK getEstProyPK() {
        return estProyPK;
    }

    public void setEstProyPK(EstProyPK estProyPK) {
        this.estProyPK = estProyPK;
    }

    public String getEstadoactual() {
        return estadoactual;
    }

    public void setEstadoactual(String estadoactual) {
        this.estadoactual = estadoactual;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estProyPK != null ? estProyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstProy)) {
            return false;
        }
        EstProy other = (EstProy) object;
        if ((this.estProyPK == null && other.estProyPK != null) || (this.estProyPK != null && !this.estProyPK.equals(other.estProyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.EstProy[ estProyPK=" + estProyPK + " ]";
    }

    public String getEstadoOperacion() {
        return estadoOperacion;
    }

    public void setEstadoOperacion(String estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }
    
}
