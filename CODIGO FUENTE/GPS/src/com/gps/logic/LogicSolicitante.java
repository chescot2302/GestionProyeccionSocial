/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Solicitante;
import com.gps.bean.Ubigeo;
import com.gps.dao.Conexion;
import com.gps.dao.DaoSolicitante;
import com.gps.view.ViewApplication;
import com.gps.view.ViewLogin;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import oracle.jdbc.OracleTypes;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class LogicSolicitante {
    public static BigDecimal registrarSolicitante(
            String descripcion,
            String ruc,
            String direccion,
            String codigoUbigeo      
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);    
            param.add(OracleTypes.INTEGER);    
            param.add(descripcion.toUpperCase());
            param.add(ruc.toUpperCase());
            param.add(direccion.toUpperCase());
            param.add(codigoUbigeo);            
            param.add(1);
            ArrayList objetos = DaoSolicitante.mantSocilitante(param);
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
    
    public static BigDecimal actualizarSolicitante(
            BigDecimal id,
            String descripcion,
            String ruc,
            String direccion,
            String codigoUbigeo      
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);            
            param.add(descripcion.toUpperCase());
            param.add(ruc.toUpperCase());
            param.add(direccion.toUpperCase());
            param.add(codigoUbigeo);            
            param.add(2);
            ArrayList objetos = DaoSolicitante.mantSocilitante(param);
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
    
    public static boolean eliminarSolicitante(BigDecimal id) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        boolean oper=false;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);            
            param.add(OracleTypes.VARCHAR);            
            param.add(OracleTypes.VARCHAR);            
            param.add(OracleTypes.VARCHAR);            
            param.add(OracleTypes.VARCHAR);            
            param.add(3);
            ArrayList objetos = DaoSolicitante.mantSocilitante(param);
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
    
    public static Solicitante beanSolicitante(BigDecimal id) {
        Solicitante objSolicitante = new Solicitante();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(id);
            ArrayList objetos = DaoSolicitante.beanSolicitante(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    objSolicitante.setId(rs.getBigDecimal(1));         
                    objSolicitante.setDescripcion(rs.getString(2));
                    objSolicitante.setUbigeo(LogicUbigeo.beanUbigeo(rs.getString(3)));
                    objSolicitante.setRuc(rs.getString(4));
                    objSolicitante.setDireccion(rs.getString(5));
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
        return objSolicitante;
    }
    
    public static ArrayList<Solicitante> listarSolicitante() {        
        ArrayList<Solicitante> lista=new ArrayList();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);            
            ArrayList objetos = DaoSolicitante.listarSolicitante(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    Solicitante objSolicitante = new Solicitante();
                    objSolicitante.setId(rs.getBigDecimal(1));         
                    objSolicitante.setDescripcion(rs.getString(2));
                    Ubigeo objUbigeo=new Ubigeo();
                    objUbigeo.setCodigo(rs.getString(3));
                    objUbigeo.setDepartamento(rs.getString(4));
                    objUbigeo.setProvincia(rs.getString(5));
                    objUbigeo.setDistrito(rs.getString(6));
                    objSolicitante.setUbigeo(objUbigeo);
                    objSolicitante.setRuc(rs.getString(7));
                    objSolicitante.setDireccion(rs.getString(8));
                    lista.add(objSolicitante);
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
