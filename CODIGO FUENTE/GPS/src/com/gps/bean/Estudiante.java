/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
   
    private BigDecimal id;
    private String nombres;
    private String appaterno;
    private String apmaterno;
    private String codigouniv;
    private Collection<Proyecto> proyectoCollection;
    private Escuela escuela;

    public Estudiante() {
    }

    public Estudiante(BigDecimal id) {
        this.id = id;
    }

    public Estudiante(BigDecimal id, String nombres, String appaterno, String apmaterno, String codigouniv) {
        this.id = id;
        this.nombres = nombres;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
        this.codigouniv = codigouniv;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAppaterno() {
        return appaterno;
    }

    public void setAppaterno(String appaterno) {
        this.appaterno = appaterno;
    }

    public String getApmaterno() {
        return apmaterno;
    }

    public void setApmaterno(String apmaterno) {
        this.apmaterno = apmaterno;
    }

    public String getCodigouniv() {
        return codigouniv;
    }

    public void setCodigouniv(String codigouniv) {
        this.codigouniv = codigouniv;
    }

 
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
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
        if (!(object instanceof Estudiante)) {
            return false;
        }
        Estudiante other = (Estudiante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.Estudiante[ id=" + id + " ]";
    }
    
}
