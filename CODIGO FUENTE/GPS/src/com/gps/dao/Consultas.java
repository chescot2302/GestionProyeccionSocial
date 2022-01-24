/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.dao;

import com.gps.view.ViewApplication;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author JONATHAN
 */
public class Consultas {    
    /*Este metodo se puede utilizar para listar tablas que no necesitan 
     * condiciones, a traves de un procedimiento almacenado
     * ojo solo funciona en sqlserver y mysql hasta ahora probados
     */
    public static ResultSet listarSQLSinParametros(String procedimientoAlmacenado,Connection cnx) {
        CallableStatement cst;
        ResultSet rs = null;
        try {
            cst = cnx.prepareCall(procedimientoAlmacenado);
            rs = cst.executeQuery();
            cnx.commit();
        } catch (Exception ex) {                        
                System.out.println("Error en la conexion");                
        }
        return rs;
    }        
   /*Este metodo se puede utilizar para listar tablas que necesitan 
     * condiciones, a traves de un procedimiento almacenado
     * ojo solo funciona en sqlserver y mysql hasta ahora probados
     */
    public static ResultSet listarSQLConParametros(String procedimientoAlmacenado, ArrayList parametros,Connection cnx) {
        CallableStatement cst;
        ResultSet rs = null;
        try {
            cst = cnx.prepareCall(procedimientoAlmacenado);
            if (numeroParametros(procedimientoAlmacenado) == parametros.size()) {
                for (int i = 0; i < parametros.size(); i++) {
                    if (obtenerTipo(parametros.get(i)).equals("java.lang.Integer")) {
                        int param = Integer.parseInt(String.valueOf(parametros.get(i)));
                        cst.setInt(i + 1, param);
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.String")) {
                        String param = String.valueOf(parametros.get(i));
                        cst.setString(i + 1, param);
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.Double")) {
                        double param = Double.parseDouble(String.valueOf(parametros.get(i)));
                        cst.setDouble(i + 1, param);
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.Float")) {
                        float param = Float.parseFloat(String.valueOf(parametros.get(i)));
                        cst.setFloat(i + 1, param);
                    } else if (obtenerTipo(parametros.get(i)).equals("java.math.BigDecimal")) {
                        BigDecimal param = BigDecimal.valueOf(Double.parseDouble(String.valueOf(parametros.get(i))));
                        cst.setBigDecimal(i + 1, param);
                    }
                }
                rs = cst.executeQuery();
                cnx.commit();
            }
        } catch (Exception ex) {            
                System.out.println("Error en la conexion");            
        }
        return rs;
    }
    /*Este metodo se puede utilizar para hacer insert, update y delete en la bd
     * funcionamiento probado sql, mysql
     */
    public static boolean listaSQLConParametros(String procedimientoAlmacenado, ArrayList parametros,Connection cnx) {
        CallableStatement cst;
        boolean estado = false;
        try {
            cst = cnx.prepareCall(procedimientoAlmacenado);
            if (numeroParametros(procedimientoAlmacenado) == parametros.size()) {
                for (int i = 0; i < parametros.size(); i++) {
                    if (obtenerTipo(parametros.get(i)).equals("java.lang.Integer")) {
                        int param = Integer.parseInt(String.valueOf(parametros.get(i)));
                        cst.setInt(i + 1, param);
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.String")) {
                        String param = String.valueOf(parametros.get(i));
                        cst.setString(i + 1, param);
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.Double")) {
                        double param = Double.parseDouble(String.valueOf(parametros.get(i)));
                        cst.setDouble(i + 1, param);
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.Float")) {
                        float param = Float.parseFloat(String.valueOf(parametros.get(i)));
                        cst.setFloat(i + 1, param);
                    } else if (obtenerTipo(parametros.get(i)).equals("java.math.BigDecimal")) {
                        BigDecimal param = (BigDecimal) parametros.get(i);
                        cst.setBigDecimal(i + 1, param);
                    }
                }
                cst.executeUpdate();
                estado = true;
            }
        } catch (Exception ex) {        
                System.out.println("Error en la conexion");            
        }
        return estado;
    }
    /*Este metodo se puede utilizar para hacer insert, update y delete en la bd
     * funcionamiento probado sql, mysql
     */
    public static Object procesoMantenimiento(String procedimientoAlmacenado, ArrayList parametros,Connection cnx) {
        CallableStatement cst;
        Object rs=null;
        int i = 0;
        try {
            cst = cnx.prepareCall(procedimientoAlmacenado);
            if (numeroParametros(procedimientoAlmacenado) == parametros.size()) {
                for (i = 0; i < parametros.size() - 1; i++) {
                    if (obtenerTipo(parametros.get(i)).equals("java.lang.Integer")) {
                        int param = Integer.parseInt(String.valueOf(parametros.get(i)));
                        cst.setInt(i + 1, param);                       
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.String")) {
                        String param = String.valueOf(parametros.get(i));
                        cst.setString(i + 1, param);                        
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.Double")) {
                        double param = Double.parseDouble(String.valueOf(parametros.get(i)));
                        cst.setDouble(i + 1, param);                       
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.Float")) {
                        float param = Float.parseFloat(String.valueOf(parametros.get(i)));
                        cst.setFloat(i + 1, param);                        
                    } else if (obtenerTipo(parametros.get(i)).equals("java.math.BigDecimal")) {
                        BigDecimal param = (BigDecimal) parametros.get(i);
                        cst.setBigDecimal(i + 1, param);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.util.Date")) {
                        java.util.Date param = (java.util.Date)parametros.get(i);                        
                        java.sql.Date date=new java.sql.Date(param.getTime());                        
                        cst.setDate(i + 1, date);                        
                    }
                }
                if (obtenerTipo(parametros.get(i)).equals("java.lang.Integer")) {
                    int param = Integer.parseInt(String.valueOf(parametros.get(i)));
                    cst.registerOutParameter(i + 1, param);                    
                }
                cst.execute();
                rs = cst.getObject(i+1);                  
            }
        } catch (Exception ex) {                        
                System.out.println("Error en la conexion");            
        }
        return rs;
    }
    
    
    /*METODO ESPECIFICO DE TIPOS PARA SER USADO EN CUALQUIER BASE DE DATOS
     PUEDE SER USADA PARA CONSULTAS SELECT, INSERT, UPDATE Y DELETE
     */
    public static ArrayList funcionConsultar(String procedimientoAlmacenado, ArrayList parametros,Connection cnx) {
        ArrayList objetos=new ArrayList();
        CallableStatement cst=null;
        Object objeto=null;
        int i = 0;
        try {
            cst = cnx.prepareCall(procedimientoAlmacenado);
            
            if (numeroParametros(procedimientoAlmacenado) == parametros.size()) {
                if (obtenerTipo(parametros.get(i)).equals("java.lang.Integer")) {                    
                    int param = Integer.parseInt(String.valueOf(parametros.get(i)));
                    i=i+1;
                    cst.registerOutParameter(i, param);                    
                }
                for (i = i; i < parametros.size(); i++) {
                    try{
                    if (obtenerTipo(parametros.get(i)).equals("java.lang.Byte")) {
                        byte param = (Byte)parametros.get(i);
                        cst.setByte(i + 1, param);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.lang.Short")) {
                        short param = (Short)parametros.get(i);
                        cst.setShort(i + 1, param);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.lang.Integer")) {
                        int param = (Integer)parametros.get(i);
                        cst.setInt(i + 1, param);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.lang.Long")) {
                        long param = (Long)parametros.get(i);
                        cst.setLong(i + 1, param);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.lang.Double")) {
                        double param = (Double)parametros.get(i);
                        cst.setDouble(i + 1, param);                        
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.Float")) {
                        float param = (Float)parametros.get(i);
                        cst.setFloat(i + 1, param);                        
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.Boolean")) {
                        boolean param = (Boolean)parametros.get(i);
                        cst.setBoolean(i + 1, param);
                    }else if (obtenerTipo(parametros.get(i)).equals("java.lang.Character")) {
                        String param = String.valueOf(parametros.get(i));
                        cst.setString(i + 1, param);                        
                    } else if (obtenerTipo(parametros.get(i)).equals("java.lang.String")) {
                        String param = String.valueOf(parametros.get(i));
                        cst.setString(i + 1, param);                        
                    } else if (obtenerTipo(parametros.get(i)).equals("java.math.BigDecimal")) {
                        BigDecimal param = (BigDecimal) parametros.get(i);
                        cst.setBigDecimal(i + 1, param);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.util.Arrays")) {
                        Array param = (Array) parametros.get(i);
                        cst.setArray(i + 1, param);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.util.Date")) {                        
                        java.util.Date param = (java.util.Date)parametros.get(i);                        
                        java.sql.Date date=new java.sql.Date(param.getTime());                        
                        cst.setDate(i + 1, date);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.sql.Date")) {                        
                        java.sql.Date param = (java.sql.Date) parametros.get(i);
                        cst.setDate(i + 1, param);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.sql.Timestamp")) {
                        Timestamp param = (Timestamp) parametros.get(i);
                        cst.setTimestamp(i + 1, param);
                    }else if (obtenerTipo(parametros.get(i)).equals("java.sql.Time")) {
                        Time param = (Time) parametros.get(i);
                        cst.setTime(i + 1, param);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.sql.Clob")) {
                        Clob param = (Clob) parametros.get(i);
                        cst.setClob(i + 1, param);                        
                    }else if (obtenerTipo(parametros.get(i)).equals("java.lang.Object")) {
                        Object param = (Object) parametros.get(i);
                        cst.setObject(i + 1, param);                               
                    }
                    }catch(Exception ne){
                        cst.setNull(i+1, Types.NULL);                        
                    }
                }                
                cst.execute();
                objeto = cst.getObject(1);
            }
        } catch (Exception ex) { 
               ViewApplication.mensajeEstado.setText("Error al conectar a la base de datos");
                System.out.println("Error en la conexion");            
        }
        objetos.add(objeto);
        objetos.add(cst);
        return objetos;
    }
    
    /*METODO GENERICO DE TIPOS PARA SER USADO EN CUALQUIER BASE DE DATOS
     PUEDE SER USADA PARA CONSULTAS SELECT, INSERT, UPDATE Y DELETE
     * {call procedure(?,?,?))}
     */
    public static Object funcionConsultaGenerica(String procedimientoAlmacenado, ArrayList parametros,Connection cnx) {
        CallableStatement cst;
        Object objeto=null;
        int i = 0;
        try {
            cst = cnx.prepareCall(procedimientoAlmacenado);
            if (numeroParametros(procedimientoAlmacenado) == parametros.size()) {
                for (i = 0; i < parametros.size() - 1; i++) {                    
                        Object param = (Object) parametros.get(i);
                        cst.setObject(i + 1, param);                    
                }
                if (obtenerTipo(parametros.get(i)).equals("java.lang.Integer")) {
                    int param = Integer.parseInt(String.valueOf(parametros.get(i)));
                    cst.registerOutParameter(i + 1, param);
                }
                cst.execute();
                objeto = cst.getObject(i + 1);
            }
        } catch (Exception ex) {      
            ViewApplication.mensajeEstado.setText("Error al conectar a la base de datos");
                System.out.println("Error en la conexion");            
        }
        return objeto;
    }
    
    
    public static ArrayList funcion(String procedimientoAlmacenado, ArrayList parametros,Connection cnx) {
        ArrayList objetos=new ArrayList();
        CallableStatement cst=null;
        Object objeto=null;
        int i = 0;
        try {
            cst = cnx.prepareCall(procedimientoAlmacenado);                
            if (numeroParametros(procedimientoAlmacenado) == parametros.size()) {
                if (obtenerTipo(parametros.get(i)).equals("java.lang.Integer")) {                    
                    int param = Integer.parseInt(String.valueOf(parametros.get(i)));
                    i=i+1;
                    cst.registerOutParameter(i, param);                    
                }
                for (i=i; i <parametros.size(); i++) {                    
                        Object param = (Object) parametros.get(i);                        
                        cst.setObject(i+ 1, param);                                            
                }
                
                cst.execute();
                objeto = cst.getObject(1);
            }
        } catch (Exception ex) {          
                ViewApplication.mensajeEstado.setText("Error al conectar a la base de datos");
                System.out.println("Error en la conexion"+ex.getMessage());            
                System.out.println("Error en la conexion"+ex.getLocalizedMessage());            
                System.out.println("Error en la conexion"+ex.fillInStackTrace());            
        }
        objetos.add(objeto);
        objetos.add(cst);
        return objetos;
    }
    
    public static Object consultaPreparada(String procedimientoAlmacenado, ArrayList parametros,Connection cnx) {
        CallableStatement cst;
        Object objeto=null;        
        try {
            cst = cnx.prepareCall(procedimientoAlmacenado);                
            if (numeroParametros(procedimientoAlmacenado) == parametros.size()) {                
                for (int i=0; i <parametros.size(); i++) {                    
                        Object param = (Object) parametros.get(i);                        
                        cst.setObject(i+ 1, param);                                            
                }                                
                objeto = cst.executeQuery();
            }
        } catch (Exception ex) {                        
                System.out.println("Error en la conexion");            
        }
        return objeto;
    }

    public static int numeroParametros(String consulta) {
        int contador = 0;
        for (int i = 0; i < consulta.length(); i++) {
            if (String.valueOf(consulta.charAt(i)).equals("?")) {
                contador = contador + 1;
            }
        }
        return contador;
    }

    public static String obtenerTipo(Object parametro) {        
        return parametro.getClass().getName();
    }
}
