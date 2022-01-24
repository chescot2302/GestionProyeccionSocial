/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;


public class TipoDocumento implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private BigDecimal id;
    private String descripcion;
    private String siglas;
    private String estadoOperacion = "n";
    private boolean seleccion;
    private Collection<Documento> documentoCollection;

    public TipoDocumento() {
    }

    public TipoDocumento(BigDecimal id) {
        this.id = id;
    }

    public TipoDocumento(BigDecimal id, String descripcion) {
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

   
    public Collection<Documento> getDocumentoCollection() {
        return documentoCollection;
    }

    public void setDocumentoCollection(Collection<Documento> documentoCollection) {
        this.documentoCollection = documentoCollection;
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
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.TipoDocumento[ id=" + id + " ]";
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

    public String getSiglas() {
        return siglas;
    }

    public void setSiglas(String siglas) {
        this.siglas = siglas;
    }
    
    
}
