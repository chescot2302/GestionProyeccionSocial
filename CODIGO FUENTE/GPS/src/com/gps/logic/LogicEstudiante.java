/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Escuela;
import com.gps.bean.Estudiante;
import com.gps.bean.Facultad;
import com.gps.bean.Universidad;
import com.gps.dao.Conexion;
import com.gps.dao.DaoEstudiante;
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
public class LogicEstudiante {
    public static BigDecimal registrarEstudiante(
            String nombres,            
            String apPaterno,
            String apMaterno,           
            String codigoUniversitario,
            BigDecimal idEscuela
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);    
            param.add(OracleTypes.NUMBER);    
            param.add(nombres.toUpperCase());            
            param.add(apPaterno.toUpperCase());            
            param.add(apMaterno.toUpperCase());                         
            param.add(codigoUniversitario.toUpperCase());  
            param.add(idEscuela); 
            param.add(1);
            ArrayList objetos = DaoEstudiante.mantEstudiante(param);
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
    
    public static BigDecimal actualizarEstudiante(
            BigDecimal id,
            String nombres,            
            String apPaterno,
            String apMaterno,           
            String codigoUniversitario,
            BigDecimal idEscuela
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);            
            param.add(nombres.toUpperCase());                      
            param.add(apPaterno.toUpperCase());                      
            param.add(apMaterno.toUpperCase());                                  
            param.add(codigoUniversitario);
            param.add(idEscuela);
            param.add(2);
            ArrayList objetos = DaoEstudiante.mantEstudiante(param);
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
    
    public static boolean eliminarEstudiante(BigDecimal id) {
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
            param.add(OracleTypes.NUMBER);
            param.add(3);
            ArrayList objetos = DaoEstudiante.mantEstudiante(param);
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
    
    public static Estudiante beanEstudiante(BigDecimal id) {
        Estudiante objEstudiante = new Estudiante();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(id);
            ArrayList objetos = DaoEstudiante.beanEstudiante(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx= (Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    objEstudiante.setId(rs.getBigDecimal(1));
                    objEstudiante.setNombres(rs.getString(2));
                    objEstudiante.setAppaterno(rs.getString(3));
                    objEstudiante.setApmaterno(rs.getString(4));
                    objEstudiante.setCodigouniv(rs.getString(5));
                    objEstudiante.setEscuela(LogicEscuela.beanEscuela(rs.getBigDecimal(6)));
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
        return objEstudiante;
    }
    
    public static ArrayList<Estudiante> listarEstudiante() {
        ArrayList<Estudiante> lista=new ArrayList();        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);        
            ArrayList objetos = DaoEstudiante.listarEstudiante(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx= (Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    Estudiante objEstudiante = new Estudiante();
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
                    objFacultad.setDescripcion(rs.getString(9));
                    objFacultad.setSiglas(rs.getString(10));
                    Universidad objUniversidad=new Universidad();
                    objUniversidad.setId(rs.getBigDecimal(11));
                    objUniversidad.setDescripcion(rs.getString(12));
                    objUniversidad.setSiglas(rs.getString(13));
                    objFacultad.setUniversidad(objUniversidad);
                    objEscuela.setFacultad(objFacultad);
                    objEstudiante.setEscuela(objEscuela);                                                            
                    lista.add(objEstudiante);
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
