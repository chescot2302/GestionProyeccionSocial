/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Proyecto;
import com.gps.dao.Conexion;
import com.gps.dao.DaoProyecto;
import com.gps.view.ViewApplication;
import com.gps.view.ViewLogin;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import oracle.jdbc.OracleTypes;
import org.openide.util.Exceptions;
/**
 *
 * @author Administrador
 */
public class LogicProyecto {
    public static BigDecimal registrarProyecto(
            BigDecimal idsolicitante,
            String descripcion,
            java.sql.Date fechasol            
            ) 
    {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER); 
            param.add(OracleTypes.NUMBER); 
            param.add(idsolicitante);    
            param.add(descripcion.toUpperCase());
            param.add(fechasol);            
            param.add(1);
            ArrayList objetos = DaoProyecto.mantProyecto(param);
            estado = (BigDecimal) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (estado != null && cst != null) {
                if (estado.compareTo(BigDecimal.ONE) == 0) {
                    ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconGood());
                    ViewApplication.mensajeEstado.setText("REGISTRADO CORRECTAMENTE");
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
        return estado;
    }
    
    
    public static BigDecimal actualizarProyecto(
            BigDecimal id,
            BigDecimal idsolicitante,
            String descripcion,
            java.sql.Date fechasol           
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER); 
            param.add(id); 
            param.add(idsolicitante);
            param.add(descripcion.toUpperCase());   
            param.add(fechasol);            
            param.add(2);
            ArrayList objetos = DaoProyecto.mantProyecto(param);
            estado = (BigDecimal) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (estado != null && cst != null) {
            if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconUpdate());
                    ViewApplication.mensajeEstado.setText("ACTUALIZADO CORRECTAMENTE");
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
        return estado;
    }
    
    public static boolean eliminarProyecto(BigDecimal id) {
        BigDecimal estado = null;
        CallableStatement cst = null;
         boolean oper=false;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);            
            param.add(OracleTypes.NUMBER);
            param.add(OracleTypes.VARCHAR);  
            param.add(new java.sql.Date(new Date().getTime()));            
            param.add(3);
            ArrayList objetos = DaoProyecto.mantProyecto(param);
            estado = (BigDecimal) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            if (estado != null && cst != null) {
                if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                    oper=true;
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
         return oper;
    }
    
    public static Proyecto beanProyecto(BigDecimal id) {
        Proyecto objProyecto = new Proyecto();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(id);
            ArrayList objetos = DaoProyecto.beanProyecto(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                
                objProyecto.setId(rs.getBigDecimal(1));
                objProyecto.setSolicitante(LogicSolicitante.beanSolicitante(rs.getBigDecimal(2)));
                objProyecto.setDescripcion(rs.getString(3));
                objProyecto.setFechasol(rs.getDate(4));                
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
        return objProyecto;
    }

    public static ArrayList<Proyecto> listarProyecto() {
        ArrayList<Proyecto> lista=new ArrayList();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoProyecto.listarProyecto(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                Proyecto objProyecto = new Proyecto();
                objProyecto.setId(rs.getBigDecimal(1));
                objProyecto.setSolicitante(LogicSolicitante.beanSolicitante(rs.getBigDecimal(2)));
                objProyecto.setDescripcion(rs.getString(3));
                objProyecto.setFechasol(rs.getDate(4));                
                lista.add(objProyecto);
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
    
    public static ArrayList<Proyecto> listarProyecto(BigDecimal idDocente) {
        ArrayList<Proyecto> lista=new ArrayList();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(idDocente);
            ArrayList objetos = DaoProyecto.listarProyectoDocente(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                Proyecto objProyecto = new Proyecto();
                objProyecto.setId(rs.getBigDecimal(1));
                objProyecto.setSolicitante(LogicSolicitante.beanSolicitante(rs.getBigDecimal(2)));
                objProyecto.setDescripcion(rs.getString(3));
                objProyecto.setFechasol(rs.getDate(4));                
                lista.add(objProyecto);
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
    
    public static ArrayList<Proyecto> listarProyectoEstudiante(BigDecimal idEstudiante) {
        ArrayList<Proyecto> lista=new ArrayList();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(idEstudiante);
            ArrayList objetos = DaoProyecto.listarProyectoEstudiante(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                Proyecto objProyecto = new Proyecto();
                objProyecto.setId(rs.getBigDecimal(1));
                objProyecto.setSolicitante(LogicSolicitante.beanSolicitante(rs.getBigDecimal(2)));
                objProyecto.setDescripcion(rs.getString(3));
                objProyecto.setFechasol(rs.getDate(4));                
                lista.add(objProyecto);
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
    
    public static ArrayList<Proyecto> listarProyectoSolicitante(BigDecimal idSolicitante) {
        ArrayList<Proyecto> lista=new ArrayList();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(idSolicitante);
            ArrayList objetos = DaoProyecto.listarProyectoSolicitante(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                Proyecto objProyecto = new Proyecto();
                objProyecto.setId(rs.getBigDecimal(1));
                objProyecto.setSolicitante(LogicSolicitante.beanSolicitante(rs.getBigDecimal(2)));
                objProyecto.setDescripcion(rs.getString(3));
                objProyecto.setFechasol(rs.getDate(4));                
                lista.add(objProyecto);
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

