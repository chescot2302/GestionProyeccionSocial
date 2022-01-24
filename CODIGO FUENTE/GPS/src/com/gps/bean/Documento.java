/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.bean;

import java.io.Serializable;
import java.math.BigDecimal;



public class Documento implements Serializable {
    private static final long serialVersionUID = 1L;    
    private BigDecimal anno;
    private BigDecimal numero;
    private java.sql.Date fecha;
    private String procedencia;
    private BigDecimal id;    
    private TipoDocumento tipoDocumento;
    private Proyecto objProyecto;
    private String estadoOperacion="n";
   private boolean seleccion;

    public Documento() {
    }

    public Documento(BigDecimal id) {
        this.id = id;
    }

    public Documento(BigDecimal id, BigDecimal anno, BigDecimal numero, java.sql.Date fecha, String procedencia) {
        this.id = id;
        this.anno = anno;
        this.numero = numero;
        this.fecha = fecha;
        this.procedencia = procedencia;
    }    

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }
    
    

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gps.bean.Documento[ id=" + id + " ]";
    }

    public BigDecimal getAnno() {
        return anno;
    }

    public void setAnno(BigDecimal anno) {
        this.anno = anno;
    }

    public BigDecimal getNumero() {
        return numero;
    }

    public void setNumero(BigDecimal numero) {
        this.numero = numero;
    }

    public Proyecto getObjProyecto() {
        return objProyecto;
    }

    public void setObjProyecto(Proyecto objProyecto) {
        this.objProyecto = objProyecto;
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
