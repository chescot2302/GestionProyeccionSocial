/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.EstProy;
import com.gps.bean.EstProyPK;
import com.gps.bean.Estado;
import com.gps.bean.Proyecto;
import com.gps.dao.Conexion;
import com.gps.dao.DaoEstproy;
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
public class LogicEstProy {
    
    public static void mantenimientoEstadoProyecto(HashSet<EstProy> esc) {
        Iterator i = esc.iterator();
        EstProy bean;
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
                bean = (EstProy) i.next();
                switch (bean.getEstadoOperacion()) {
                    case "e":
                        param.add(OracleTypes.NUMBER);
                        param.add(bean.getEstado().getId());
                        param.add(bean.getProyecto().getId());
                        param.add(OracleTypes.VARCHAR);
                        param.add(new java.sql.Date(new java.util.Date().getTime()));                                                
                        param.add(3);
                        objetos = DaoEstproy.mantEstproy(param);
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
                        param.add(bean.getEstado().getId());
                        param.add(bean.getProyecto().getId());
                        param.add(bean.getEstadoactual());
                        param.add(bean.getFecha());                                                
                        param.add(2);
                        objetos = DaoEstproy.mantEstproy(param);
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
                        param.add(bean.getEstado().getId());
                        param.add(bean.getProyecto().getId());
                        param.add(bean.getEstadoactual());
                        param.add(bean.getFecha());                                                
                        param.add(1);
                        objetos = DaoEstproy.mantEstproy(param);
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
    
    public static void registrarEstadoProyecto(
            BigDecimal idEstado,
            BigDecimal idProyecto,
            String estadoActual,
            java.sql.Date fecha
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);    
            param.add(idEstado);    
            param.add(idProyecto); 
            param.add(estadoActual.toUpperCase());
            param.add(fecha);
            param.add(1);
            ArrayList objetos = DaoEstproy.mantEstproy(param);
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
    
    public static void actualizarEstadoProyecto(
            BigDecimal idEstado,
            BigDecimal idProyecto,
            String estadoActual,
            java.sql.Date fecha
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);    
            param.add(idEstado);    
            param.add(idProyecto); 
            param.add(estadoActual.toUpperCase());
            param.add(fecha);
            param.add(2);
            ArrayList objetos = DaoEstproy.mantEstproy(param);
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
    
    public static void eliminarEstadoProyecto(BigDecimal idEstado, BigDecimal idProyecto) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(idEstado);            
            param.add(idProyecto);            
            param.add(OracleTypes.VARCHAR);                                    
            param.add(new java.sql.Date(new java.util.Date().getTime()));                                    
            param.add(3);
            ArrayList objetos = DaoEstproy.mantEstproy(param);
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
    
    public static EstProy beanEstado(BigDecimal idEstado,BigDecimal idProyecto) {
        EstProy objEstproy = new EstProy();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(idEstado);
            param.add(idProyecto);
            ArrayList objetos = DaoEstproy.beanEstproy(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                EstProyPK obj=new EstProyPK();
                while (rs.next()) {
                    obj.setIdEstado(rs.getBigDecimal(1));
                    obj.setIdProyecto(rs.getBigDecimal(2));
                    objEstproy.setEstProyPK(obj);
                    objEstproy.setEstado(LogicEstado.beanEstado(obj.getIdEstado()));
                    objEstproy.setProyecto(LogicProyecto.beanProyecto(obj.getIdProyecto()));
                    objEstproy.setEstadoactual(rs.getString(3));
                    objEstproy.setFecha(rs.getDate(4));
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
        return objEstproy;
    }
    
    public static ArrayList<EstProy> listarEstadoProy(BigDecimal idProyecto) {
        ArrayList<EstProy> lista=new ArrayList();        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);        
            param.add(idProyecto);        
            ArrayList objetos = DaoEstproy.listarEstProy(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx= (Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {                
                while (rs.next()) {                    
                    EstProy obj = new EstProy();   
                    obj.setEstadoactual(rs.getString(1));
                    EstProyPK pk=new EstProyPK();
                    pk.setIdEstado(rs.getBigDecimal(2));                    
                    Estado objEstado=new Estado();
                    objEstado.setId(rs.getBigDecimal(2));
                    objEstado.setDescripcion(rs.getString(3));
                    obj.setFecha(rs.getDate(4));
                    Proyecto objProy=new Proyecto();
                    objProy.setId(rs.getBigDecimal(5));
                    obj.setEstProyPK(pk);
                    obj.setEstado(objEstado);
                    obj.setProyecto(objProy);
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
