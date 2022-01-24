/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Decano;
import com.gps.bean.Docente;
import com.gps.bean.Escuela;
import com.gps.bean.Facultad;
import com.gps.bean.Grado;
import com.gps.bean.Universidad;
import com.gps.dao.Conexion;
import com.gps.dao.DaoDecano;
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
public class LogicDecano {
    public static BigDecimal registrarDecano(
            BigDecimal idDocente,            
            BigDecimal idFacultad,
            java.sql.Date fechaInicio,
            java.sql.Date fechFin,
            String  estadoDecano
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);    
            param.add(OracleTypes.NUMBER);    
            param.add(idDocente);            
            param.add(idFacultad);            
            param.add(fechaInicio);            
            param.add(fechFin);  
            param.add(estadoDecano.toUpperCase());  
            param.add(1);
            ArrayList objetos = DaoDecano.mantDecano(param);
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
    
    public static BigDecimal actualizarDecano(
            BigDecimal id,
            BigDecimal idDocente,            
            BigDecimal idFacultad,
            java.sql.Date fechaInicio,
            java.sql.Date fechFin,
            String  estadoDecano
            ) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);            
            param.add(idDocente);                      
            param.add(idFacultad);                      
            param.add(fechaInicio);                      
            param.add(fechFin);
            param.add(estadoDecano);
            param.add(2);
            ArrayList objetos = DaoDecano.mantDecano(param);
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
    
    public static boolean eliminarDecano(BigDecimal id) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        boolean oper=false;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);            
            param.add(OracleTypes.NUMBER);                      
            param.add(OracleTypes.NUMBER);                      
            param.add(new java.sql.Date(new Date().getTime()));                      
            param.add(new java.sql.Date(new Date().getTime()));                      
            param.add(OracleTypes.VARCHAR);
            param.add(3);
            ArrayList objetos = DaoDecano.mantDecano(param);
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
    
    public static Decano beanDecano(BigDecimal id) {
        Decano objDecano = new Decano();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(id);
            ArrayList objetos = DaoDecano.beanDecano(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx= (Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    objDecano.setId(rs.getBigDecimal(1));
                    objDecano.setDocente(LogicDocente.beanDocente(rs.getBigDecimal(2)));
                    objDecano.setFacultad(LogicFacultad.beanFacultad(rs.getBigDecimal(3)));
                    objDecano.setFechaini(rs.getDate(4));
                    objDecano.setFechafin(rs.getDate(5));
                    objDecano.setEstadoActual(rs.getString(6));
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
        return objDecano;
    }
    
    public static ArrayList<Decano> listarDecano() {
        ArrayList<Decano> lista=new ArrayList();        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);        
            ArrayList objetos = DaoDecano.listarDecano(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx= (Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    Decano objDecano = new Decano();
                    objDecano.setId(rs.getBigDecimal(1));
                    objDecano.setFechaini(rs.getDate(2));
                    objDecano.setFechafin(rs.getDate(3));
                    objDecano.setEstadoActual(rs.getString(4));
                    Docente objDocente=new Docente();
                    objDocente.setId(rs.getBigDecimal(5));
                    objDocente.setAppaterno(rs.getString(6));
                    objDocente.setApmaterno(rs.getString(7));
                    objDocente.setNombres(rs.getString(8));                    
                    Escuela objEscuela=new Escuela();
                    objEscuela.setId(rs.getBigDecimal(9));
                    objEscuela.setDescripcion(rs.getString(10));
                    Facultad objFacultad=new Facultad();
                    objFacultad.setId(rs.getBigDecimal(11));
                    objFacultad.setDescripcion(rs.getString(12));
                    objFacultad.setSiglas(rs.getString(13));
                    objDecano.setFacultad(objFacultad);
                    Universidad objUniversidad=new Universidad();
                    objUniversidad.setId(rs.getBigDecimal(14));
                    objUniversidad.setDescripcion(rs.getString(15));
                    objUniversidad.setSiglas(rs.getString(16));
                    objFacultad.setUniversidad(objUniversidad);
                    objEscuela.setFacultad(objFacultad);
                    objDocente.setEscuela(objEscuela);
                    Grado objGrado=new Grado();
                    objGrado.setId(rs.getBigDecimal(17));
                    objGrado.setDescripcion(rs.getString(18));
                    objGrado.setSiglas(rs.getString(19));  
                    objDocente.setGrado(objGrado);    
                    objDecano.setDocente(objDocente);
                    lista.add(objDecano);
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
