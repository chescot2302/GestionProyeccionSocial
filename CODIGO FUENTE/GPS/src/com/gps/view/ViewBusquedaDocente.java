/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import java.awt.BorderLayout;
import javax.swing.JDialog;

/**
 *
 * @author Administrador
 */
public class ViewBusquedaDocente extends JDialog{
    private ViewDocente searchDocente=new ViewDocente();
    
    public ViewBusquedaDocente(){
        initComponents();
    }
    
    private void initComponents(){
        this.setLayout(new BorderLayout());
        this.getContentPane().add(searchDocente.getPanelData());                
    }

    public ViewDocente getSearchDocente() {
        return searchDocente;
    }

    public void setSearchDocente(ViewDocente searchDocente) {
        this.searchDocente = searchDocente;
    }
    
    
}
