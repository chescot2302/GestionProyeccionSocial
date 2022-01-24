/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;


public class Proyecto implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal id;
    private String descripcion;
    private java.sql.Date fechasol;
    private Collection<Estudiante> estudianteCollection;
    private Collection<Documento> documentoCollection;
    private Collection<EstProy> estProyCollection;
    private Collection<ProyDoc> proyDocCollection;
    private Solicitante solicitante;    

    public Proyecto() {
    }

    public Proyecto(BigDecimal id) {
        this.id = id;
    }

    public Proyecto(BigDecimal id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
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

    
    public Collection<Estudiante> getEstudianteCollection() {
        return estudianteCollection;
    }

    public void setEstudianteCollection(Collection<Estudiante> estudianteCollection) {
        this.estudianteCollection = estudianteCollection;
    }

    
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
    }

    
    public Collection<EstProy> getEstProyCollection() {
        return estProyCollection;
    }

    public void setEstProyCollection(Collection<EstProy> estProyCollection) {
        this.estProyCollection = estProyCollection;
    }

    
    public Collection<ProyDoc> getProyDocCollection() {
        return proyDocCollection;
    }

    public void setProyDocCollection(Collection<ProyDoc> proyDocCollection) {
        this.proyDocCollection = proyDocCollection;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
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
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.Proyecto[ id=" + id + " ]";
    }

    public Date getFechasol() {
        return fechasol;
    }

    public void setFechasol(Date fechasol) {
        this.fechasol = fechasol;
    }
                    
}
