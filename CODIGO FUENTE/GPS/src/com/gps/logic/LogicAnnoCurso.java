/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.AnnoCurso;
import com.gps.dao.Conexion;
import com.gps.dao.DaoAnnoCurso;
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
public class LogicAnnoCurso {

    public static void mantenimientoEstado(HashSet<AnnoCurso> anno) {
        Iterator i = anno.iterator();
        AnnoCurso bean;
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
                    bean = (AnnoCurso) i.next();
                    switch (bean.getEstadoOperacion()) {
                        case "e":
                            param.add(OracleTypes.NUMBER);
                            param.add(bean.getId());
                            param.add(OracleTypes.VARCHAR);
                            param.add(OracleTypes.VARCHAR);
                            param.add(3);
                            objetos = DaoAnnoCurso.mantAnnoCurso(param);
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
                            param.add(bean.getEstadoActual());
                            param.add(2);
                            objetos = DaoAnnoCurso.mantAnnoCurso(param);
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
                            param.add(bean.getId());
                            param.add(bean.getDescripcion().toUpperCase());
                            param.add(bean.getEstadoActual());
                            param.add(1);
                            objetos = DaoAnnoCurso.mantAnnoCurso(param);
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
                

            } catch  (Exception ex) {
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

    public static void registrarAnnoCurso(
            BigDecimal anno,
            String descripcion,
            String estadoAnno) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(anno);
            param.add(descripcion.toUpperCase());
            param.add(estadoAnno.toUpperCase());
            param.add(1);
            ArrayList objetos = DaoAnnoCurso.mantAnnoCurso(param);
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

    public static void actualizarAnnoCurso(
            BigDecimal anno,
            String descripcion,
            String estadoAnno) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(anno);
            param.add(descripcion.toUpperCase());
            param.add(estadoAnno.toUpperCase());
            param.add(2);
            ArrayList objetos = DaoAnnoCurso.mantAnnoCurso(param);
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

    public static void eliminarAnnoCurso(BigDecimal anno) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(anno);
            param.add(OracleTypes.VARCHAR);
            param.add(OracleTypes.VARCHAR);
            param.add(3);
            ArrayList objetos = DaoAnnoCurso.mantAnnoCurso(param);
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

    public static AnnoCurso beanAnnoCurso(BigDecimal id) {
        AnnoCurso objAnno = new AnnoCurso();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(id);
            ArrayList objetos = DaoAnnoCurso.beanAnnoCurso(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx = (Conexion) objetos.get(2);
            if (rs != null && cst != null && cnx != null) {
                while (rs.next()) {
                    objAnno.setId(rs.getBigDecimal(1));
                    objAnno.setDescripcion(rs.getString(2));
                    objAnno.setEstadoActual(rs.getString(3));
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
        return objAnno;
    }

    public static ArrayList<AnnoCurso> listarAnnoCurso() {
        ArrayList<AnnoCurso> lista = new ArrayList();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            ArrayList objetos = DaoAnnoCurso.listarAnnoCurso(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx = (Conexion) objetos.get(2);
            if (rs != null && cst != null && cnx != null) {
                while (rs.next()) {
                    AnnoCurso objGrado = new AnnoCurso();
                    objGrado.setId(rs.getBigDecimal(1));
                    objGrado.setDescripcion(rs.getString(2));
                    objGrado.setEstadoActual(rs.getString(3));
                    lista.add(objGrado);
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
