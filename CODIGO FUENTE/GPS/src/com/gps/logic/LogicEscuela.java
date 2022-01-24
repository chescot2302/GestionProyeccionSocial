/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Escuela;
import com.gps.bean.Facultad;
import com.gps.bean.Universidad;
import com.gps.dao.Conexion;
import com.gps.dao.DaoEscuela;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class LogicEscuela {

    public static void mantenimientoEscuela(HashSet<Escuela> esc) {
        Iterator i = esc.iterator();
        Escuela bean;
        int contarEliminados = 0;
        int contarInsertados = 0;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        Conexion cnx = null;
        BigDecimal estado = null;
        try{
        while (i.hasNext()) {
            bean = (Escuela) i.next();
            switch (bean.getEstadoOperacion()) {
                case "e":
                    param.add(OracleTypes.NUMBER);
                    param.add(bean.getId());
                    param.add(OracleTypes.VARCHAR);
                    param.add(OracleTypes.NUMBER);
                    param.add(3);
                    objetos = DaoEscuela.mantEscuela(param);
                    estado = (BigDecimal) objetos.get(0);
                    cst = (CallableStatement) objetos.get(1);
                    cnx = (Conexion) objetos.get(2);
                    if (estado != null && cst != null) {
                        if (estado.compareTo(BigDecimal.valueOf(3)) == 0) {
                            contarEliminados = contarEliminados + 1;
                            param.clear();
                        }
                        cst.close();
                        cnx.destroy();
                    }
                    break;
                case "a":
                    param.add(OracleTypes.NUMBER);
                    param.add(bean.getId());
                    param.add(bean.getDescripcion().toUpperCase());
                    param.add(bean.getFacultad().getId());
                    param.add(2);
                    objetos = DaoEscuela.mantEscuela(param);
                    estado = (BigDecimal) objetos.get(0);
                    cst = (CallableStatement) objetos.get(1);
                    cnx = (Conexion) objetos.get(2);
                    if (estado != null && cst != null) {
                        if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                            contarActualizados = contarActualizados + 1;
                        param.clear();
                        }
                        cst.close();
                        cnx.destroy();
                    }
                    break;
                case "i":
                    param.add(OracleTypes.NUMBER);
                    param.add(OracleTypes.NUMBER);
                    param.add(bean.getDescripcion().toUpperCase());
                    param.add(bean.getFacultad().getId());
                    param.add(1);
                    objetos = DaoEscuela.mantEscuela(param);
                    estado = (BigDecimal) objetos.get(0);
                    cst = (CallableStatement) objetos.get(1);
                    cnx = (Conexion) objetos.get(2);
                    if (estado != null && cst != null) {
                        if (estado.compareTo(BigDecimal.ONE) == 0) {
                            contarInsertados = contarInsertados + 1;
                            param.clear();
                        }
                        cst.close();
                        cnx.destroy();
                    }
                    break;
            }            
        }   
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }finally {
            try {                
                if (cst != null) {
                    cst.close();
                }
                if (cnx != null) {
                    cnx.destroy();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        JOptionPane.showMessageDialog(null, "Se han insert"
                + "ado: " + contarInsertados + " registros \n"
                + "Se han actualizado: " + contarActualizados + " registros \n"
                + "Se han eliminado: " + contarEliminados + " registros", "Informe de Operación", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void registrarEscuela(
            String descripcion,
            BigDecimal idFacultad) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(OracleTypes.INTEGER);
            param.add(descripcion.toUpperCase());
            param.add(idFacultad);
            param.add(1);
            ArrayList objetos = DaoEscuela.mantEscuela(param);
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
                if (cst != null) {
                    cst.close();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    public static void actualizarEscuela(
            BigDecimal id,
            String descripcion,
            BigDecimal idFacultad) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);
            param.add(descripcion.toUpperCase());
            param.add(idFacultad);
            param.add(2);
            ArrayList objetos = DaoEscuela.mantEscuela(param);
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
                if (cst != null) {
                    cst.close();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    public static void eliminarEscuela(BigDecimal id) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);
            param.add(OracleTypes.VARCHAR);
            param.add(OracleTypes.NUMBER);
            param.add(3);
            ArrayList objetos = DaoEscuela.mantEscuela(param);
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
                if (cst != null) {
                    cst.close();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    public static Escuela beanEscuela(BigDecimal id) {
        Escuela objEscuela = new Escuela();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(id);
            ArrayList objetos = DaoEscuela.beanEscuela(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx = (Conexion) objetos.get(2);
            if (rs != null && cst != null && cnx != null) {
                while (rs.next()) {
                    objEscuela.setId(rs.getBigDecimal(1));
                    objEscuela.setDescripcion(rs.getString(2));
                    objEscuela.setFacultad(LogicFacultad.beanFacultad(rs.getBigDecimal(3)));
                }
                rs.close();
                cst.close();
                cnx.destroy();
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cst != null) {
                    cst.close();
                }
                if (cnx != null) {
                    cnx.destroy();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return objEscuela;
    }

    public static ArrayList<Escuela> listarEscuela(BigDecimal idFacultad) {
        ArrayList<Escuela> lista = new ArrayList();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(idFacultad);
            ArrayList objetos = DaoEscuela.listarEscuela(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx = (Conexion) objetos.get(2);
            if (rs != null && cst != null && cnx != null) {
                while (rs.next()) {
                    Escuela objEscuela = new Escuela();
                    objEscuela.setId(rs.getBigDecimal(1));
                    objEscuela.setDescripcion(rs.getString(2));
                    Facultad objFacultad = new Facultad();
                    objFacultad.setId(rs.getBigDecimal(3));
                    objFacultad.setDescripcion(rs.getString(4));
                    objFacultad.setSiglas(rs.getString(5));
                    Universidad objUniversidad = new Universidad();
                    objUniversidad.setId(rs.getBigDecimal(6));
                    objUniversidad.setDescripcion(rs.getString(7));
                    objUniversidad.setSiglas(rs.getString(8));
                    objFacultad.setUniversidad(objUniversidad);
                    objEscuela.setFacultad(objFacultad);
                    lista.add(objEscuela);
                }
                rs.close();
                cst.close();
                cnx.destroy();
            }
        } catch (SQLException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (cst != null) {
                    cst.close();
                }
                if (cnx != null) {
                    cnx.destroy();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
        return lista;
    }
}
