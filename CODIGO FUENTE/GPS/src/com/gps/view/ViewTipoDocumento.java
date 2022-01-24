/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Estado;
import com.gps.bean.TipoDocumento;
import com.gps.logic.LogicEstado;
import com.gps.logic.LogicTipoDocumento;
import com.gps.tablemodel.TablaModelEstado;
import com.gps.tablemodel.TablaModelTipoDocumento;
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
public class ViewTipoDocumento extends JDialog {
    private FrmPanel panelMolde=new FrmPanel(ViewLogin.picture.getIconTipodocumento32(),"Mantenimiento tipo de Documento");    
    private TablaModelTipoDocumento model=new TablaModelTipoDocumento(panelMolde.getTablaDatos());
    private JButton btnAdd=new JButton(ViewLogin.picture.getIconAgregar());
    private JButton btnCommit=new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback=new JButton(ViewLogin.picture.getIconRollBack());
    public ViewTipoDocumento(){        
        initComponents();
    }
    
    private void initComponents(){
        this.getContentPane().setLayout(new BorderLayout());
        this.add(panelMolde);
        model.setData(LogicTipoDocumento.listarTipoDocumento());

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
                TipoDocumento est=new TipoDocumento();
                est.setId(BigDecimal.valueOf(new java.util.Date().getTime()));                
                est.setEstadoOperacion("i");                
                model.getData().add(est);
                model.fireTableDataChanged();   
                panelMolde.getTablaDatos().changeSelection(model.getRowCount()-1, 1, true, true);
                panelMolde.getTablaDatos().editCellAt(model.getRowCount()-1, 1);                                
                }catch(Exception ex){}
    }
    
    private void actualizarTabla(){
        model.setData(LogicTipoDocumento.listarTipoDocumento());
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
                LogicTipoDocumento.mantenimientoEstado(model.getListaCommit());
                model.getListaCommit().clear();
                actualizarTabla();               
            }
        }
        
    };
      public FrmPanel getPanelEstado() {
        return panelMolde;
    }
    
}
