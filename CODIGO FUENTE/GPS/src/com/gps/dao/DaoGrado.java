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
public class DaoGrado {
    public static ArrayList mantGrado(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos;
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=mantgrado(?,?,?,?)}";
                objetos=Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);            
        }         
        return null;
    }

    public static ArrayList beanGrado(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos;
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=beangrado(?)}";
                objetos= Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
    public static ArrayList listarGrado(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos;
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=listarGrado()}";
                objetos= Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
}
