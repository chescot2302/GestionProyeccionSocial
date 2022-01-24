/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class ProyEst implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected ProyEstPK proyEstPK;
    private Proyecto proyecto;
    private Estudiante estudiante;
    private String estadoOperacion="n";
    private boolean seleccion;
    public ProyEst() {
    }

    public ProyEst(ProyEstPK proyEstPK) {
        this.proyEstPK = proyEstPK;
    }

    public ProyEst(BigDecimal idProyecto, BigDecimal idEstudiante) {
        this.proyEstPK = new ProyEstPK(idProyecto,idEstudiante);
    }

    public ProyEstPK getProyEstPK() {
        return proyEstPK;
    }

    public void setProyEstPK(ProyEstPK proyEstPK) {
        this.proyEstPK = proyEstPK;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyEstPK != null ? proyEstPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyEst)) {
            return false;
        }
        ProyEst other = (ProyEst) object;
        if ((this.proyEstPK == null && other.proyEstPK != null) || (this.proyEstPK != null && !this.proyEstPK.equals(other.proyEstPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.ProyEst[ proyEstPK=" + proyEstPK + " ]";
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