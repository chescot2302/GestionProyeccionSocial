/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Ubigeo;
import com.gps.dao.Conexion;
import com.gps.dao.DaoUbigeo;
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
public class LogicUbigeo {

    public static void registrarUbigeo(
            String codigo,
            String departamento,
            String provincia,
            String distrito) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(codigo);
            param.add(departamento.toUpperCase());
            param.add(provincia.toUpperCase());
            param.add(distrito.toUpperCase());
            param.add(1);
            ArrayList objetos = DaoUbigeo.mantUbigeo(param);
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

    public static void actualizarUbigeo(
            String codigo,
            String departamento,
            String provincia,
            String distrito) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(codigo);
            param.add(departamento.toUpperCase());
            param.add(provincia.toUpperCase());
            param.add(distrito.toUpperCase());
            param.add(2);
            ArrayList objetos = DaoUbigeo.mantUbigeo(param);
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

    public static void eliminarUbigeo(String codigo) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(codigo);
            param.add(OracleTypes.VARCHAR);
            param.add(OracleTypes.VARCHAR);
            param.add(OracleTypes.VARCHAR);
            param.add(3);
            ArrayList objetos = DaoUbigeo.mantUbigeo(param);
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

    public static Ubigeo beanUbigeo(String codigo) {
        Ubigeo objUbigeo = new Ubigeo();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(codigo);
            ArrayList objetos = DaoUbigeo.beanUbigeo(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    objUbigeo.setCodigo(rs.getString(1));
                    objUbigeo.setDepartamento(rs.getString(2));
                    objUbigeo.setProvincia(rs.getString(3));
                    objUbigeo.setDistrito(rs.getString(4));                    
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
        return objUbigeo;
    }
    
    public static ArrayList<Ubigeo> ListarDepartamentos() {        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        ArrayList<Ubigeo> arrayUbigeo=new ArrayList();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add("0");
            param.add("0");
            param.add("0");            
            ArrayList objetos = DaoUbigeo.ListarUbigeo(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    Ubigeo objUbigeo = new Ubigeo();
                    objUbigeo.setDepartamento(rs.getString(1));                    
                    arrayUbigeo.add(objUbigeo);
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
        return arrayUbigeo;
    }
    
    public static ArrayList<Ubigeo> ListarProvincias(String departamento) {        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        ArrayList<Ubigeo> arrayUbigeo=new ArrayList();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(departamento);
            param.add("0");
            param.add("0");            
            ArrayList objetos = DaoUbigeo.ListarUbigeo(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    Ubigeo objUbigeo = new Ubigeo();                    
                    objUbigeo.setProvincia(rs.getString(1));                    
                    arrayUbigeo.add(objUbigeo);
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
        return arrayUbigeo;
    }
    
    public static ArrayList<Ubigeo> ListarDistritos(String departamento,String provincia) {        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        ArrayList<Ubigeo> arrayUbigeo=new ArrayList();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(departamento);
            param.add(provincia);
            param.add("0");            
            ArrayList objetos = DaoUbigeo.ListarUbigeo(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    Ubigeo objUbigeo = new Ubigeo();                    
                    objUbigeo.setDistrito(rs.getString(1)); 
                    arrayUbigeo.add(objUbigeo);
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
        return arrayUbigeo;
    }
    
    public static Ubigeo listarUbigeo(String departamento,String provincia,String distrito) {        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;        
        Ubigeo objUbigeo = new Ubigeo();
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(departamento);
            param.add(provincia);
            param.add(distrito);            
            ArrayList objetos = DaoUbigeo.ListarUbigeo(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx=(Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {                    
                    objUbigeo.setCodigo(rs.getString(1));
                    objUbigeo.setDepartamento(rs.getString(2));
                    objUbigeo.setProvincia(rs.getString(3));
                    objUbigeo.setDistrito(rs.getString(4));                     
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
        return objUbigeo;
    }
}
