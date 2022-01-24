/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Grado;
import com.gps.comboboxmodel.CbModelGrado;
import com.gps.logic.LogicGrado;
import com.gps.tablemodel.TableModelGrado;
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
public class ViewGrado extends JDialog {
    private FrmPanel panelMolde=new FrmPanel(ViewLogin.picture.getIconGrado32(),"Grados Académicos");
    private ViewDocente objPadre;
    private TableModelGrado model=new TableModelGrado(panelMolde.getTablaDatos());
    private JButton btnAdd=new JButton(ViewLogin.picture.getIconAgregar());
    private JButton btnCommit=new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback=new JButton(ViewLogin.picture.getIconRollBack());
    public ViewGrado(ViewDocente padre){
        objPadre=padre;
        initComponents();
    }
      public ViewGrado(){

        initComponents();
    }
    
    private void initComponents(){
        this.getContentPane().setLayout(new BorderLayout());
        this.add(panelMolde);
        model.setData(LogicGrado.listarGrado());
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
                Grado grad=new Grado();                
                grad.setId(BigDecimal.valueOf(new java.util.Date().getTime()));                
                grad.setEstadoOperacion("i");                
                model.getData().add(grad);
                model.fireTableDataChanged();   
                panelMolde.getTablaDatos().changeSelection(model.getRowCount()-1, 1, true, true);
                panelMolde.getTablaDatos().editCellAt(model.getRowCount()-1, 1);                                
                }catch(Exception ex){}
    }
    
    private void actualizarTabla(){
        model.setData(LogicGrado.listarGrado());
        model.fireTableDataChanged();    
        objPadre.getArbol().removeAll();
        objPadre.construirArbol();
        objPadre.getArbol().updateUI();
        ((CbModelGrado)objPadre.getFrmDocente().getCbGrado().getModel()).setData(LogicGrado.listarGrado());
        objPadre.getFrmDocente().getCbGrado().updateUI();
        objPadre.getFrmDocente().getCbGrado().getEditor().setItem(null);
    }
    
    ActionListener actionListener=new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            if(btnAdd.equals(e.getSource())){
                nuevoRegistro();
            }else if(btnRollback.equals(e.getSource())){
                actualizarTabla();
            }else if(btnCommit.equals(e.getSource())){
                LogicGrado.mantenimientoGrado(model.getListaCommit());
                model.getListaCommit().clear();
                actualizarTabla();               
            }
        }
        
    };
        
    
}
