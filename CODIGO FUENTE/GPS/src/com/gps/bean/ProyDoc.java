/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;


public class ProyDoc implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected ProyDocPK proyDocPK;
    private String calidad;
    private Proyecto proyecto;
    private Docente docente;
    private String estadoOperacion="n";
    private boolean seleccion;

    public ProyDoc() {
    }

    public ProyDoc(ProyDocPK proyDocPK) {
        this.proyDocPK = proyDocPK;
    }

    public ProyDoc(BigDecimal idProyecto, BigDecimal idDocente) {
        this.proyDocPK = new ProyDocPK(idProyecto, idDocente);
    }

    public ProyDocPK getProyDocPK() {
        return proyDocPK;
    }

    public void setProyDocPK(ProyDocPK proyDocPK) {
        this.proyDocPK = proyDocPK;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyDocPK != null ? proyDocPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProyDoc)) {
            return false;
        }
        ProyDoc other = (ProyDoc) object;
        if ((this.proyDocPK == null && other.proyDocPK != null) || (this.proyDocPK != null && !this.proyDocPK.equals(other.proyDocPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.ProyDoc[ proyDocPK=" + proyDocPK + " ]";
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
