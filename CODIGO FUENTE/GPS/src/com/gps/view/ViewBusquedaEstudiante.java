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
public class ViewBusquedaEstudiante extends JDialog{
    private ViewEstudiante searchEstudiante=new ViewEstudiante();
   
    public ViewBusquedaEstudiante(){
        initComponents();
    }
    
    private void initComponents(){
        this.setLayout(new BorderLayout());
        this.getContentPane().add(searchEstudiante.getMolde().getPaneldata());                
    }

    public ViewEstudiante getSearchEstudiante() {
        return searchEstudiante;
    }

    public void setSearchEstudiante(ViewEstudiante searchEstudiante) {
        this.searchEstudiante = searchEstudiante;
    }

   
    
}
