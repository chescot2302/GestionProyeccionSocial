/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Estado;
import com.gps.logic.LogicEstado;
import com.gps.tablemodel.TablaModelEstado;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import javax.swing.JButton;
import javax.swing.JDialog;

/**
 *
 * @author Administrador
 */
public class ViewEstado extends JDialog {
    private FrmPanel panelMolde=new FrmPanel(ViewLogin.picture.getIconEstado32(),"Estados del Proyecto");    
    private TablaModelEstado model=new TablaModelEstado(panelMolde.getTablaDatos());
    private JButton btnAdd=new JButton(ViewLogin.picture.getIconAgregar());
    private JButton btnCommit=new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback=new JButton(ViewLogin.picture.getIconRollBack());
    public ViewEstado(){        
        initComponents();
    }
    
    private void initComponents(){
        this.getContentPane().setLayout(new BorderLayout());
        this.add(panelMolde);
        model.setData(LogicEstado.listarEstado());
        btnAdd.setToolTipText("Agregar nuevo registro");
        btnCommit.setToolTipText("Confirmar operaciones");
        btnRollback.setToolTipText("Actualizar");
        panelMolde.getBarraBusqueda().add(btnAdd);
        panelMolde.getBarraBusqueda().add(btnCommit);
        panelMolde.getBarraBusqueda().add(btnRollback);
        btnAdd.addActionListener(actionListener);
        btnRollback.addActionListener(actionListener);
        btnCommit.addActionListener(actionListener);
    }
    
    private void nuevoRegistro(){
        try{                     
                Estado est=new Estado();                
                est.setId(BigDecimal.valueOf(new java.util.Date().getTime()));                
                est.setEstadoOperacion("i");                
                model.getData().add(est);
                model.fireTableDataChanged();   
                panelMolde.getTablaDatos().changeSelection(model.getRowCount()-1, 1, true, true);
                panelMolde.getTablaDatos().editCellAt(model.getRowCount()-1, 1);                                
                }catch(Exception ex){}
    }
    
    private void actualizarTabla(){
        model.setData(LogicEstado.listarEstado());
        model.fireTableDataChanged();            
    }
    
    ActionListener actionListener=new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            if(btnAdd.equals(e.getSource())){
                nuevoRegistro();
            }else if(btnRollback.equals(e.getSource())){
                actualizarTabla();
            }else if(btnCommit.equals(e.getSource())){
                LogicEstado.mantenimientoEstado(model.getListaCommit());
                model.getListaCommit().clear();
                actualizarTabla();               
            }
        }
        
    };
      public FrmPanel getPanelEstado() {
        return panelMolde;
    }
    
}
