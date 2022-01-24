/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Estado;
import com.gps.bean.AnnoCurso;
import com.gps.logic.LogicEstado;
import com.gps.logic.LogicAnnoCurso;
import com.gps.tablemodel.TablaModelEstado;
import com.gps.tablemodel.TablaModelAnnoCurso;
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
public class ViewAnnoCurso extends JDialog {
    private FrmPanel panelMolde=new FrmPanel(ViewLogin.picture.getIconAnno32(),"Descripcion de Años");    
    private TablaModelAnnoCurso model=new TablaModelAnnoCurso(panelMolde.getTablaDatos());
    private JButton btnAdd=new JButton(ViewLogin.picture.getIconAgregar());
    private JButton btnCommit=new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback=new JButton(ViewLogin.picture.getIconRollBack());
    public ViewAnnoCurso(){        
        initComponents();
    }
    
    private void initComponents(){
        this.getContentPane().setLayout(new BorderLayout());
        this.add(panelMolde);
        model.setData(LogicAnnoCurso.listarAnnoCurso());

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
                AnnoCurso est=new AnnoCurso();
                est.setAuxid(BigDecimal.valueOf(new java.util.Date().getTime()));
                est.setEstadoOperacion("i");                
                model.getData().add(est);
                model.fireTableDataChanged();   
                panelMolde.getTablaDatos().changeSelection(model.getRowCount()-1, 1, true, true);
                panelMolde.getTablaDatos().editCellAt(model.getRowCount()-1, 1);                                
                }catch(Exception ex){}
    }
    
    private void actualizarTabla(){
        model.setData(LogicAnnoCurso.listarAnnoCurso());
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
                LogicAnnoCurso.mantenimientoEstado(model.getListaCommit());
                model.getListaCommit().clear();
                actualizarTabla();               
            }
        }
        
    };
      public FrmPanel getPanelEstado() {
        return panelMolde;
    }
    
}
