/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Docente;
import com.gps.bean.Escuela;
import com.gps.bean.Facultad;
import com.gps.bean.ProyDoc;
import com.gps.bean.ProyDocPK;
import com.gps.bean.Proyecto;
import com.gps.dao.Conexion;
import com.gps.dao.DaoProydoc;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class LogicProyDoc {
    
    public static void mantenimientoProyDoc(HashSet<ProyDoc> esc) {
        Iterator i = esc.iterator();
        ProyDoc bean;
        int contarEliminados = 0;
        int contarInsertados = 0;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        Conexion cnx = null;
        BigDecimal estado = null;
        try {
            while (i.hasNext()) {
                bean = (ProyDoc) i.next();
                switch (bean.getEstadoOperacion()) {
                    case "e":
                        param.add(OracleTypes.NUMBER);
                        param.add(bean.getProyDocPK().getIdProyecto());
                        param.add(bean.getProyDocPK().getIdDocente());  
                        param.add(OracleTypes.VARCHAR);  
                        param.add(3);
                        objetos = DaoProydoc.mantProydoc(param);
                        estado = (BigDecimal) objetos.get(0);
                        cst = (CallableStatement) objetos.get(1);
                        cnx = (Conexion) objetos.get(2);
                        if (estado != null && cst != null) {
                            if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                                contarEliminados = contarEliminados + 1;
                                param.clear();
                            }
                            cst.close();
                            cnx.destroy();
                        }
                        break;
                    case "a":
                        param.add(OracleTypes.NUMBER);
                        param.add(bean.getProyDocPK().getIdProyecto());
                        param.add(bean.getProyDocPK().getIdDocente()); 
                        param.add(OracleTypes.VARCHAR);  
                        param.add(2);
                        objetos = DaoProydoc.mantProydoc(param);
                        estado = (BigDecimal) objetos.get(0);
                        cst = (CallableStatement) objetos.get(1);
                        cnx = (Conexion) objetos.get(2);
                        if (estado != null && cst != null) {
                            if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                                contarActualizados = contarActualizados + 1;
                                param.clear();
                            }
                            cst.close();
                            cnx.destroy();
                        }
                        break;
                    case "i":
                        param.add(OracleTypes.NUMBER);
                        param.add(bean.getProyDocPK().getIdProyecto());
                        param.add(bean.getProyDocPK().getIdDocente()); 
                        param.add("RESPONSABLE");  
                        param.add(1);
                        objetos = DaoProydoc.mantProydoc(param);
                        estado = (BigDecimal) objetos.get(0);
                        cst = (CallableStatement) objetos.get(1);
                        cnx = (Conexion) objetos.get(2);
                        if (estado != null && cst != null) {
                            if (estado.compareTo(BigDecimal.ONE) == 0) {
                                contarInsertados = contarInsertados + 1;
                                param.clear();
                            }
                            cst.close();
                            cnx.destroy();
                        }
                        break;
                }
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                if (cst != null) {
                    cst.close();
                }
                if (cnx != null) {
                    cnx.destroy();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        JOptionPane.showMessageDialog(null, "Se han insert"
                + "ado: " + contarInsertados + " registros \n"
                + "Se han actualizado: " + contarActualizados + " registros \n"
                + "Se han eliminado: " + contarEliminados + " registros", "Informe de Operación", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void registrarProyectoDocente(
            BigDecimal idProyecto,
            BigDecimal idDocente,
            String calidad
            ) 
    {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);             
            param.add(idProyecto);    
            param.add(idDocente);    
            param.add(calidad.toUpperCase());
            param.add(1);
            ArrayList objetos = DaoProydoc.mantProydoc(param);
            estado = (BigDecimal) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (estado != null && cst != null) {
                if (estado.compareTo(BigDecimal.ONE) == 0) {
                    System.out.print("Registrado Correctamente");
                }
                cst.close();
            }
        } catch (SQLException ex) {            
                Exceptions.printStackTrace(ex);            
        } finally {
            try {               
                if(cst !=null){
                    cst.close();
                }                                
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    
    
    public static void actualizarProyectoDocente(
            BigDecimal idProyecto,
            BigDecimal idDocente,
            String calidad
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER); 
            param.add(idProyecto); 
            param.add(idDocente);
            param.add(calidad.toUpperCase());    
            param.add(2);
            ArrayList objetos = DaoProydoc.mantProydoc(param);
            estado = (BigDecimal) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (estado != null && cst != null) {
            if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    System.out.print("Actualizado Correctamente");
                }
                cst.close();
            }
        } catch (SQLException ex) {            
                Exceptions.printStackTrace(ex);            
        } finally {
            try {                
                if(cst !=null){
                    cst.close();
                }                                
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    
    public static void eliminarProyectoDocente(BigDecimal idProyecto,BigDecimal idDocente) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(idProyecto);            
            param.add(idDocente);
            param.add(OracleTypes.VARCHAR);    
            param.add(3);
            ArrayList objetos = DaoProydoc.mantProydoc(param);
            estado = (BigDecimal) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (estado != null && cst != null) {
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    System.out.print("Eliminado Correctamente");
                }
                cst.close();
            }
        } catch (SQLException ex) {            
                Exceptions.printStackTrace(ex);            
        } finally {
            try {                
                if(cst !=null){
                    cst.close();
                }                                
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    
    public static ProyDoc beanProyDoc(BigDecimal idProyecto,BigDecimal idDocente) {
        ProyDoc objProydoc = new ProyDoc();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(idProyecto);
            param.add(idDocente);            
            ArrayList objetos = DaoProydoc.beanProydoc(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                ProyDocPK obj=new ProyDocPK();
                while (rs.next()) {                
                obj.setIdProyecto(rs.getBigDecimal(1));
                obj.setIdDocente(rs.getBigDecimal(2));
                objProydoc.setProyDocPK(obj);
                objProydoc.setProyecto(LogicProyecto.beanProyecto(obj.getIdProyecto()));
                objProydoc.setDocente(LogicDocente.beanDocente(obj.getIdDocente()));
                objProydoc.setCalidad(rs.getString(3));
                }
                rs.close();
                cst.close();
                cnx.destroy();
            }
        } catch (SQLException ex) {            
                Exceptions.printStackTrace(ex);            
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }                
                if(cst !=null){
                    cst.close();
                }                
                if(cnx != null){
                    cnx.destroy();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return objProydoc;
    }
    
    public static ArrayList<ProyDoc> listarProyDoc(BigDecimal idProyecto) {
        ArrayList<ProyDoc> lista=new ArrayList();        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);     
            param.add(idProyecto);     
            ArrayList objetos = DaoProydoc.listarProyDoc(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx= (Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    ProyDoc obj=new ProyDoc();
                    ProyDocPK pk=new ProyDocPK();
                    Docente objDocente = new Docente();
                    pk.setIdDocente(rs.getBigDecimal(1));
                    objDocente.setId(rs.getBigDecimal(1));
                    objDocente.setNombres(rs.getString(2));
                    objDocente.setAppaterno(rs.getString(3));
                    objDocente.setApmaterno(rs.getString(4));                    
                    Escuela objEscuela=new Escuela();
                    objEscuela.setId(rs.getBigDecimal(5));
                    objEscuela.setDescripcion(rs.getString(6));
                    Facultad objFacultad=new Facultad();
                    objFacultad.setId(rs.getBigDecimal(7));                    
                    objFacultad.setSiglas(rs.getString(8));
                    Proyecto objProyecto=new Proyecto();
                    pk.setIdProyecto(rs.getBigDecimal(9));
                    objProyecto.setId(rs.getBigDecimal(9));                    
                    objEscuela.setFacultad(objFacultad);
                    objDocente.setEscuela(objEscuela);   
                    obj.setProyDocPK(pk);
                    obj.setDocente(objDocente);
                    obj.setProyecto(objProyecto);
                    lista.add(obj);
                }                
                rs.close();
                cst.close();
                cnx.destroy();
            }
        } catch (SQLException ex) {            
                Exceptions.printStackTrace(ex);            
        } finally {
            try {
                if (rs != null){
                    rs.close();
                }                
                if(cst !=null){
                    cst.close();
                }                
                if(cnx != null){
                    cnx.destroy();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return lista;
    }
    
}

