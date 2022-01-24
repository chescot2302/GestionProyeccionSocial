/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Facultad;
import com.gps.bean.Universidad;
import com.gps.dao.Conexion;
import com.gps.dao.DaoFacultad;
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
public class LogicFacultad {

    public static void mantenimientoFacultad(HashSet<Facultad> fac) {
        Iterator i = fac.iterator();
        Facultad bean;
        int contarEliminados = 0;
        int contarInsertados = 0;
        int contarActualizados = 0;
        ArrayList param = new ArrayList();
        ArrayList objetos;
        CallableStatement cst = null;
        Conexion cnx = null;
        BigDecimal estado = null;
        try {
            while (i.hasNext()) {
                bean = (Facultad) i.next();
                switch (bean.getEstadoOperacion()) {
                    case "e":
                        param.add(OracleTypes.NUMBER);
                        param.add(bean.getId());
                        param.add(OracleTypes.VARCHAR);
                        param.add(OracleTypes.VARCHAR);
                        param.add(OracleTypes.NUMBER);
                        param.add(3);
                        objetos = DaoFacultad.mantFacultad(param);
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
                        param.add(bean.getSiglas().toUpperCase());
                        param.add(bean.getUniversidad().getId());
                        param.add(2);
                        objetos = DaoFacultad.mantFacultad(param);
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
                        param.add(OracleTypes.INTEGER);
                        param.add(bean.getDescripcion().toUpperCase());
                        param.add(bean.getSiglas().toUpperCase());
                        param.add(bean.getUniversidad().getId());
                        param.add(1);
                        objetos = DaoFacultad.mantFacultad(param);
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
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
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

    public static void registrarFacultad(
            String descripcion,
            String siglas,
            BigDecimal idUniversidad) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(OracleTypes.INTEGER);
            param.add(descripcion.toUpperCase());
            param.add(siglas.toUpperCase());
            param.add(idUniversidad);
            param.add(1);
            ArrayList objetos = DaoFacultad.mantFacultad(param);
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
                if (cst != null) {
                    cst.close();
                }
            } catch (SQLException ex) {
                Exceptions.printStackTrace(ex);
            }
        }
    }

    public static void actualizarFacultad(
            BigDecimal id,
            String descripcion,
            String siglas,
            BigDecimal idUniversidad) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);
            param.add(descripcion.toUpperCase());
            param.add(siglas.toUpperCase());
            param.add(idUniversidad);
            param.add(2);
            ArrayList objetos = DaoFacultad.mantFacultad(param);
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

    public static void eliminarFacultad(BigDecimal id) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(id);
            param.add(OracleTypes.VARCHAR);
            param.add(OracleTypes.VARCHAR);
            param.add(OracleTypes.NUMBER);
            param.add(3);
            ArrayList objetos = DaoFacultad.mantFacultad(param);
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

    public static Facultad beanFacultad(BigDecimal id) {
        Facultad objFacultad = new Facultad();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(id);
            ArrayList objetos = DaoFacultad.beanFacultad(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx = (Conexion) objetos.get(2);
            if (rs != null && cst != null && cnx != null) {
                while (rs.next()) {
                    objFacultad.setId(rs.getBigDecimal(1));
                    objFacultad.setDescripcion(rs.getString(2));
                    objFacultad.setSiglas(rs.getString(3));
                    objFacultad.setUniversidad(LogicUniversidad.beanUniversidad(rs.getBigDecimal(4)));
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
        return objFacultad;
    }

    public static ArrayList<Facultad> listarFacultad(BigDecimal idUniversida) {
        ArrayList<Facultad> lista = new ArrayList();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(idUniversida);
            ArrayList objetos = DaoFacultad.listarFacultad(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx = (Conexion) objetos.get(2);
            if (rs != null && cst != null && cnx != null) {
                while (rs.next()) {
                    Facultad objFacultad = new Facultad();
                    Universidad objUniversidad = new Universidad();
                    objFacultad.setId(rs.getBigDecimal(1));
                    objFacultad.setDescripcion(rs.getString(2));
                    objFacultad.setSiglas(rs.getString(3));
                    objUniversidad.setId(rs.getBigDecimal(4));
                    objUniversidad.setDescripcion(rs.getString(5));
                    objUniversidad.setSiglas(rs.getString(6));
                    objFacultad.setUniversidad(objUniversidad);
                    lista.add(objFacultad);
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
