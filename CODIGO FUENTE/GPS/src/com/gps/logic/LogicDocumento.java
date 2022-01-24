/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.logic;

import com.gps.bean.Documento;
import com.gps.bean.Proyecto;
import com.gps.bean.TipoDocumento;
import com.gps.dao.Conexion;
import com.gps.dao.DaoDocumento;
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
public class LogicDocumento {

    public static void mantenimientoDocumento(HashSet<Documento> esc) {
        Iterator i = esc.iterator();
        Documento bean;
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
                bean = (Documento) i.next();
                switch (bean.getEstadoOperacion()) {
                    case "e":
                        param.add(OracleTypes.NUMBER);
                        param.add(bean.getAnno());
                        param.add(bean.getNumero());
                        param.add(new java.sql.Date(new java.util.Date().getTime()));
                        param.add(OracleTypes.VARCHAR);
                        param.add(bean.getTipoDocumento().getId());
                        param.add(OracleTypes.VARCHAR);
                        param.add(3);
                        objetos = DaoDocumento.mantDocumento(param);
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
                        param.add(bean.getAnno());
                        param.add(bean.getNumero());
                        param.add(bean.getFecha());
                        param.add(bean.getProcedencia().toUpperCase());
                        param.add(bean.getTipoDocumento().getId());
                        param.add(bean.getObjProyecto().getId());
                        param.add(2);
                        objetos = DaoDocumento.mantDocumento(param);
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
                        param.add(bean.getAnno());
                        param.add(bean.getNumero());
                        param.add(bean.getFecha());
                        param.add(bean.getProcedencia().toUpperCase());
                        param.add(bean.getTipoDocumento().getId());
                        param.add(bean.getObjProyecto().getId());
                        param.add(1);
                        objetos = DaoDocumento.mantDocumento(param);
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

    public static void registrarDocumento(
            BigDecimal anno,
            BigDecimal numero,
            java.sql.Date fecha,
            String procedencia,
            BigDecimal idTipoDocumento,
            BigDecimal idProyecto) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(anno);
            param.add(numero);
            param.add(fecha);
            param.add(procedencia.toUpperCase());
            param.add(idTipoDocumento);
            param.add(idProyecto);
            param.add(1);
            ArrayList objetos = DaoDocumento.mantDocumento(param);
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

    public static void actualizarDocumento(
            BigDecimal anno,
            BigDecimal numero,
            java.sql.Date fecha,
            String procedencia,
            BigDecimal idTipoDocumento,
            BigDecimal idProyecto) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(anno);
            param.add(numero);
            param.add(fecha);
            param.add(procedencia.toUpperCase());
            param.add(idTipoDocumento);
            param.add(idProyecto);
            param.add(2);
            ArrayList objetos = DaoDocumento.mantDocumento(param);
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

    public static void eliminarDocumento(BigDecimal anno, BigDecimal numero, BigDecimal idtipodoc) {
        BigDecimal estado = null;
        CallableStatement cst = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.NUMBER);
            param.add(anno);
            param.add(numero);
            param.add(new java.sql.Date(new java.util.Date().getTime()));
            param.add(OracleTypes.VARCHAR);
            param.add(idtipodoc);
            param.add(OracleTypes.VARCHAR);
            param.add(3);
            ArrayList objetos = DaoDocumento.mantDocumento(param);
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

    public static Documento beanDocumento(BigDecimal anno, BigDecimal numero, BigDecimal idtipodoc) {
        Documento objDocumento = new Documento();
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx = null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);
            param.add(anno);
            param.add(numero);
            param.add(idtipodoc);
            ArrayList objetos = DaoDocumento.beanDocumento(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx = (Conexion) objetos.get(2);
            if (rs != null && cst != null && cnx != null) {
                while (rs.next()) {
                    objDocumento.setId(rs.getBigDecimal(1));
                    objDocumento.setAnno(rs.getBigDecimal(2));
                    objDocumento.setNumero(rs.getBigDecimal(3));
                    objDocumento.setFecha(rs.getDate(4));
                    objDocumento.setProcedencia(rs.getString(5));
                    objDocumento.setTipoDocumento(LogicTipoDocumento.beanTipoDocumento(rs.getBigDecimal(6)));
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
        return objDocumento;
    }
    
    public static ArrayList<Documento> listarDocumento(BigDecimal idProyecto) {
        ArrayList<Documento> lista=new ArrayList();        
        ResultSet rs = null;
        CallableStatement cst = null;
        Conexion cnx=null;
        try {
            ArrayList param = new ArrayList();
            param.add(OracleTypes.CURSOR);        
            param.add(idProyecto);        
            ArrayList objetos = DaoDocumento.listarDocumento(param);
            rs = (ResultSet) objetos.get(0);
            cst = (CallableStatement) objetos.get(1);
            cnx= (Conexion)objetos.get(2);
            if (rs != null && cst != null && cnx!=null) {
                BigDecimal cont=BigDecimal.ZERO;
                while (rs.next()) {
                    cont=cont.add(BigDecimal.ONE);
                    Documento objDocumento = new Documento();                    
                    objDocumento.setId(cont);
                    objDocumento.setAnno(rs.getBigDecimal(1));
                    objDocumento.setNumero(rs.getBigDecimal(2));
                    objDocumento.setFecha(rs.getDate(3));
                    objDocumento.setProcedencia(rs.getString(4));
                    TipoDocumento td=new TipoDocumento();
                    td.setId(rs.getBigDecimal(5));
                    td.setDescripcion(rs.getString(6));
                    td.setSiglas(rs.getString(7));
                    objDocumento.setTipoDocumento(td);
                    Proyecto proy=new Proyecto();
                    proy.setId(rs.getBigDecimal(8));
                    proy.setDescripcion(rs.getString(9));
                    proy.setFechasol(rs.getDate(10));
                    objDocumento.setObjProyecto(proy);                    
                    lista.add(objDocumento);
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
