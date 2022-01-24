/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


public class Docente implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    private BigDecimal id;
    private String nombres;
    private String appaterno;
    private String apmaterno;
    private Grado grado;
    private Escuela escuela;  
    private Collection<Decano> decanoCollection;   
    private Collection<ProyDoc> proyDocCollection;

    public Docente() {
    }

    public Docente(BigDecimal id) {
        this.id = id;
    }

    public Docente(BigDecimal id, String nombres, String appaterno, String apmaterno) {
        this.id = id;
        this.nombres = nombres;
        this.appaterno = appaterno;
        this.apmaterno = apmaterno;
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

    public Grado getGrado() {
        return grado;
    }

    public void setGrado(Grado grado) {
        this.grado = grado;
    }

    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }

    
    public Collection<Decano> getDecanoCollection() {
        return decanoCollection;
    }

    public void setDecanoCollection(Collection<Decano> decanoCollection) {
        this.decanoCollection = decanoCollection;
    }


    public Collection<ProyDoc> getProyDocCollection() {
        return proyDocCollection;
    }

    public void setProyDocCollection(Collection<ProyDoc> proyDocCollection) {
        this.proyDocCollection = proyDocCollection;
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
        if (!(object instanceof Docente)) {
            return false;
        }
        Docente other = (Docente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.Docente[ id=" + id + " ]";
    }
    
    
    
}
