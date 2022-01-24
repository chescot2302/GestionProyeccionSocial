/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;
public class AnnoCurso implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation    
    private BigDecimal id;
    private BigDecimal auxid;
    private String descripcion;
    private String estadoActual;
    private String estadoOperacion = "n";
    private boolean seleccion;

    public AnnoCurso() {
    }

    public AnnoCurso(BigDecimal id) {
        this.id = id;
    }

    public AnnoCurso(BigDecimal id, String descripcion, String estadoActual) {
        this.id = id;
        this.descripcion = descripcion;
        this.estadoActual = estadoActual;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(String estadoActual) {
        this.estadoActual = estadoActual;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnnoCurso)) {
            return false;
        }
        AnnoCurso other = (AnnoCurso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.AnnoCurso[ id=" + id + " ]";
    }

    /**
     * @return the estadoOperacion
     */
    public String getEstadoOperacion() {
        return estadoOperacion;
    }

    /**
     * @param estadoOperacion the estadoOperacion to set
     */
    public void setEstadoOperacion(String estadoOperacion) {
        this.estadoOperacion = estadoOperacion;
    }

    /**
     * @return the seleccion
     */
    public boolean isSeleccion() {
        return seleccion;
    }

    /**
     * @param seleccion the seleccion to set
     */
    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    /**
     * @return the auxid
     */
    public BigDecimal getAuxid() {
        return auxid;
    }

    /**
     * @param auxid the auxid to set
     */
    public void setAuxid(BigDecimal auxid) {
        this.auxid = auxid;
    }
}
