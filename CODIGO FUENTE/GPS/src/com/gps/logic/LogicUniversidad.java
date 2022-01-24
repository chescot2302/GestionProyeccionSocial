/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Universidad;
import com.gps.dao.Conexion;
import com.gps.dao.DaoUniversidad;
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
public class LogicUniversidad {
    public static void registrarUniversidad(
            String descripcion,
            String siglas      
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);    
            param.add(OracleTypes.INTEGER);    
            param.add(descripcion.toUpperCase());
            param.add(siglas.toUpperCase());            
            param.add(1);
            ArrayList objetos = DaoUniversidad.mantUniversidad(param);
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
                if(cst!=null){
                cst.close();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    
    public static void actualizarUniversidad(
            BigDecimal id,
            String descripcion,
            String siglas      
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);            
            param.add(descripcion.toUpperCase());
            param.add(siglas.toUpperCase());            
            param.add(2);
            ArrayList objetos = DaoUniversidad.mantUniversidad(param);
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
                if(cst!=null){
                cst.close();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    
    public static void eliminarUniversidad(BigDecimal id) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);            
            param.add(OracleTypes.VARCHAR);            
            param.add(OracleTypes.VARCHAR);            
            param.add(3);
            ArrayList objetos = DaoUniversidad.mantUniversidad(param);
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
                if(cst!=null){
                 cst.close();   
                }                
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }
    
    public static Universidad beanUniversidad(BigDecimal id) {
        Universidad objUniversidad = new Universidad();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(id);
            ArrayList objetos = DaoUniversidad.beanUniversidad(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    objUniversidad.setId(rs.getBigDecimal(1));
                    objUniversidad.setDescripcion(rs.getString(2));
                    objUniversidad.setSiglas(rs.getString(3));
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
        return objUniversidad;
    }
}
