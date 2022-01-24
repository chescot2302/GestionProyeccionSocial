/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Escuela;
import com.gps.bean.Estudiante;
import com.gps.bean.Facultad;
import com.gps.bean.ProyEst;
import com.gps.bean.ProyEstPK;
import com.gps.bean.Proyecto;
import com.gps.dao.Conexion;
import com.gps.dao.DaoProyest;
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
public class LogicProyEst {
    
    public static void mantenimientoProyEst(HashSet<ProyEst> esc) {
        Iterator i = esc.iterator();
        ProyEst bean;
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
                bean = (ProyEst) i.next();
                switch (bean.getEstadoOperacion()) {
                    case "e":
                        param.add(OracleTypes.NUMBER);
                        param.add(bean.getProyEstPK().getIdProyecto());
                        param.add(bean.getProyEstPK().getIdEstudiante());                        
                        param.add(3);
                        objetos = DaoProyest.mantProyest(param);
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
                        param.add(bean.getProyEstPK().getIdProyecto());
                        param.add(bean.getProyEstPK().getIdEstudiante());                        
                        param.add(2);
                        objetos = DaoProyest.mantProyest(param);
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
                        param.add(bean.getProyEstPK().getIdProyecto());
                        param.add(bean.getProyEstPK().getIdEstudiante());                        
                        param.add(1);
                        objetos = DaoProyest.mantProyest(param);                        
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
    
    public static void registrarProyectoEstudiante(
            
            BigDecimal idProyecto,
            BigDecimal idEstudiante
                  ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);    
                
            param.add(idProyecto); 
            param.add(idEstudiante);
            param.add(1);
            ArrayList objetos = DaoProyest.mantProyest(param);
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
    
    public static void actualizarProyectoEstudiante(
            
            BigDecimal idProyecto,
            BigDecimal idEstudiante
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);               
            param.add(idProyecto); 
            param.add(idEstudiante);
            param.add(2);
            ArrayList objetos = DaoProyest.mantProyest(param);
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
    
    public static void eliminarProyectoEstudiante(BigDecimal idProyecto, BigDecimal idEstudiante) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(idProyecto);                                               
            param.add(idEstudiante);
            param.add(3);
            ArrayList objetos = DaoProyest.mantProyest(param);
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
    
    public static ProyEst beanProyEst(BigDecimal idProyecto,BigDecimal idEstudiante) {
        ProyEst objProyest = new ProyEst();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(idProyecto);
            param.add(idEstudiante);
            ArrayList objetos = DaoProyest.mantProyest(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                ProyEstPK obj=new ProyEstPK();
                while (rs.next()) {
                    obj.setIdProyecto(rs.getBigDecimal(1));
                    obj.setIdEstudiante(rs.getBigDecimal(2));
                    objProyest.setProyEstPK(obj);
                    objProyest.setProyecto(LogicProyecto.beanProyecto(obj.getIdProyecto()));
                    objProyest.setEstudiante(LogicEstudiante.beanEstudiante(obj.getIdEstudiante()));
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
        return objProyest;
    }
    
    public static ArrayList<ProyEst> listarProyEst(BigDecimal idProyecto) {
        ArrayList<ProyEst> lista=new ArrayList();        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);     
            param.add(idProyecto);     
            ArrayList objetos = DaoProyest.listarProyEst(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx= (Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    ProyEst obj=new ProyEst();
                    ProyEstPK pk=new ProyEstPK();
                    Estudiante objEstudiante = new Estudiante();
                    pk.setIdEstudiante(rs.getBigDecimal(1));
                    objEstudiante.setId(rs.getBigDecimal(1));
                    objEstudiante.setNombres(rs.getString(2));
                    objEstudiante.setAppaterno(rs.getString(3));
                    objEstudiante.setApmaterno(rs.getString(4));
                    objEstudiante.setCodigouniv(rs.getString(5));
                    Escuela objEscuela=new Escuela();
                    objEscuela.setId(rs.getBigDecimal(6));
                    objEscuela.setDescripcion(rs.getString(7));
                    Facultad objFacultad=new Facultad();
                    objFacultad.setId(rs.getBigDecimal(8));                    
                    objFacultad.setSiglas(rs.getString(9));
                    Proyecto objProyecto=new Proyecto();
                    pk.setIdProyecto(rs.getBigDecimal(10));
                    objProyecto.setId(rs.getBigDecimal(10));                    
                    objEscuela.setFacultad(objFacultad);
                    objEstudiante.setEscuela(objEscuela);   
                    obj.setProyEstPK(pk);
                    obj.setEstudiante(objEstudiante);
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

