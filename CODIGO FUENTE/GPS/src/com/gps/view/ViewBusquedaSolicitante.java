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
public class ViewBusquedaSolicitante extends JDialog{
    private ViewSolicitante searchSolicitante=new ViewSolicitante();
    
    public ViewBusquedaSolicitante(){
        initComponents();
    }
    
    private void initComponents(){
        this.setLayout(new BorderLayout());
        this.getContentPane().add(searchSolicitante.getMolde().getMolde());
        searchSolicitante.getMolde().getPanelformulario().setVisible(false);
    }

    public ViewSolicitante getSearchSolicitante() {
        return searchSolicitante;
    }

    public void setSearchSolicitante(ViewSolicitante searchSolicitante) {
        this.searchSolicitante = searchSolicitante;
    }

    
}
