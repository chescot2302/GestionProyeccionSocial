/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.util.Collection;


public class Ubigeo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigo;
    private String departamento;
    private String provincia;
    private String distrito;
    private Collection<Solicitante> solicitanteCollection;

    public Ubigeo() {
    }

    public Ubigeo(String codigo) {
        this.codigo = codigo;
    }

    public Ubigeo(String codigo, String departamento, String provincia, String distrito) {
        this.codigo = codigo;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    
    public Collection<Solicitante> getSolicitanteCollection() {
        return solicitanteCollection;
    }

    public void setSolicitanteCollection(Collection<Solicitante> solicitanteCollection) {
        this.solicitanteCollection = solicitanteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ubigeo)) {
            return false;
        }
        Ubigeo other = (Ubigeo) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.Ubigeo[ codigo=" + codigo + " ]";
    }
    
}
