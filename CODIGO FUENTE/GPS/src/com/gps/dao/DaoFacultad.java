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
public class DaoFacultad {
    public static ArrayList mantFacultad(ArrayList param) {
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        ArrayList objetos;
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=mantfacultad(?,?,?,?,?)}";
                objetos=Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);            
        }         
        return null;
    }

    public static ArrayList beanFacultad(ArrayList param) {
        ArrayList objetos;
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=beanfacultad(?)}";
                objetos=Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
    
    public static ArrayList listarFacultad(ArrayList param) {
        ArrayList objetos;
        ObjetoConexion cnxOra = new ObjetoConexion();
        Conexion objCnx = cnxOra.conectarORACLE();
        try {
            if (!objCnx.getCnx().isClosed()) {
                String proc = "{call ?:=listarFacultad(?)}";
                objetos=Consultas.funcion(proc, param, objCnx.getCnx());
                objetos.add(objCnx);
                return objetos;
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        }
        return null;
    }
}
