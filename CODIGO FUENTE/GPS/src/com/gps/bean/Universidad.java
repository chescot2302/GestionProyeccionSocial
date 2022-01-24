/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 *
 * @author Abraham
 */
public class Universidad implements Serializable {
    private static final long serialVersionUID = 1L;   
    private BigDecimal id;    
    private String descripcion;    
    private String siglas;    
    private Collection<Facultad> facultadCollection;

    public Universidad() {
    }

    public Universidad(BigDecimal id) {
        this.id = id;
    }

    public Universidad(BigDecimal id, String descripcion, String siglas) {
        this.id = id;
        this.descripcion = descripcion;
        this.siglas = siglas;
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

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }
    
    public Collection<Facultad> getFacultadCollection() {
        return facultadCollection;
    }

    public void setFacultadCollection(Collection<Facultad> facultadCollection) {
        this.facultadCollection = facultadCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Universidad)) {
            return false;
        }
        Universidad other = (Universidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.Universidad[ id=" + id + " ]";
    }
    
}
