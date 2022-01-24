/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class DaoDocente {
    public static ArrayList mantDocente(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=mantdocente(?,?,?,?,?,?,?)}";
                return Consultas.funcion(proc, param, objCnx.getCnx());
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);            
        } finally {
            try {
                objCnx.destroy();
                
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }        
        return null;
    }

    public static ArrayList beanDocente(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos;
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=beandocente(?)}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
    public static ArrayList listarDocente(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos;
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=listarDocente(?)}";
                objetos = Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
}
