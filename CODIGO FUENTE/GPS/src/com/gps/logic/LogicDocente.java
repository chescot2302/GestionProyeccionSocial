/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Docente;
import com.gps.bean.Escuela;
import com.gps.bean.Facultad;
import com.gps.bean.Grado;
import com.gps.bean.Universidad;
import com.gps.dao.Conexion;
import com.gps.dao.DaoDocente;
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
public class LogicDocente {
    public static BigDecimal registrarDocente(
            String nombres,            
            String apPaterno,
            String apMaterno,
            BigDecimal idEscuela,
            BigDecimal idGrado
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
            param.add(idEscuela);  
            param.add(idGrado);  
            param.add(1);
            ArrayList objetos = DaoDocente.mantDocente(param);
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
    
    public static BigDecimal actualizarDocente(
            BigDecimal id,
            String nombres,            
            String apPaterno,
            String apMaterno,
            BigDecimal idEscuela,
            BigDecimal idGrado
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
            param.add(idEscuela);
            param.add(idGrado);
            param.add(2);
            ArrayList objetos = DaoDocente.mantDocente(param);
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
    
    public static boolean eliminarDocente(BigDecimal id) {
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
            param.add(OracleTypes.NUMBER);
            param.add(OracleTypes.NUMBER);
            param.add(3);
            ArrayList objetos = DaoDocente.mantDocente(param);
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
    
    public static Docente beanDocente(BigDecimal id) {
        Docente objDocente = new Docente();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(id);
            ArrayList objetos = DaoDocente.beanDocente(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx= (Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    objDocente.setId(rs.getBigDecimal(1));
                    objDocente.setNombres(rs.getString(2));
                    objDocente.setAppaterno(rs.getString(3));
                    objDocente.setApmaterno(rs.getString(4));
                    objDocente.setEscuela(LogicEscuela.beanEscuela(rs.getBigDecimal(5)));
                    objDocente.setGrado(LogicGrado.beanGrado(rs.getBigDecimal(6)));
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
        return objDocente;
    }
    
    public static ArrayList<Docente> listarDocente(BigDecimal idFacultad) {
        ArrayList<Docente> lista=new ArrayList();        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);        
            param.add(idFacultad);        
            ArrayList objetos = DaoDocente.listarDocente(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx= (Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                while (rs.next()) {
                    Docente objDocente = new Docente();
                    objDocente.setId(rs.getBigDecimal(1));
                    objDocente.setNombres(rs.getString(2));
                    objDocente.setAppaterno(rs.getString(3));
                    objDocente.setApmaterno(rs.getString(4));
                    Escuela objEscuela=new Escuela();
                    objEscuela.setId(rs.getBigDecimal(5));
                    objEscuela.setDescripcion(rs.getString(6));
                    Facultad objFacultad=new Facultad();
                    objFacultad.setId(rs.getBigDecimal(7));
                    objFacultad.setDescripcion(rs.getString(8));
                    objFacultad.setSiglas(rs.getString(9));
                    Universidad objUniversidad=new Universidad();
                    objUniversidad.setId(rs.getBigDecimal(10));
                    objUniversidad.setDescripcion(rs.getString(11));
                    objUniversidad.setSiglas(rs.getString(12));
                    objFacultad.setUniversidad(objUniversidad);
                    objEscuela.setFacultad(objFacultad);
                    objDocente.setEscuela(objEscuela);
                    Grado objGrado=new Grado();
                    objGrado.setId(rs.getBigDecimal(13));
                    objGrado.setDescripcion(rs.getString(14));
                    objGrado.setSiglas(rs.getString(15));  
                    objDocente.setGrado(objGrado);                    
                    lista.add(objDocente);
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
