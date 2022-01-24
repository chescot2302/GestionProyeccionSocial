package com.gps.dao;

import com.gps.view.ViewApplication;

/**
 *
 * @author JONATHAN
 */
public class ObjetoConexion {    
    private  Conexion oracle;                   
    
    public  Conexion conectarORACLE() {
        try {
            oracle = new Conexion("127.0.0.1", "1521", "XE", "pulpin", "pulpin", "ORACLE");
            boolean estado=oracle.conectarBD();
            if(estado){
            System.out.println("CONECTADO CORRECTAMENTE");
            return oracle;
            }else{
            System.out.println("ERROR AL CONECTAR");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("ERROR AL CONECTAR: "+ex.getCause());
            ViewApplication.mensajeEstado.setText("Error al conectar a la base de datos");
            return null;
        }
    }    

    public Conexion getOracle() {
        return oracle;
    }
    
}
