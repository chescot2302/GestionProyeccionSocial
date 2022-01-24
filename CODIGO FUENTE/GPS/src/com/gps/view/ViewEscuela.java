/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Escuela;
import com.gps.bean.Facultad;
import com.gps.comboboxmodel.CbModelEscuela;
import com.gps.logic.LogicEscuela;
import com.gps.tablemodel.TableModelEscuela;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;


/**
 *
 * @author Administrador
 */
public class ViewEscuela extends JDialog {
    private FrmPanel panelMolde=new FrmPanel(ViewLogin.picture.getIconEscuela32(),"Escuela");
    private ViewDocente objPadreDocente;
    private ViewEstudiante objPadreEstudiante;
    private TableModelEscuela modelEscuela=new TableModelEscuela(panelMolde.getTablaDatos());
    private JButton btnAdd=new JButton(ViewLogin.picture.getIconAgregar());
    private JButton btnCommit=new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback=new JButton(ViewLogin.picture.getIconRollBack());
    public ViewEscuela(ViewDocente padre){
        objPadreDocente=padre;
        initComponents();
    }
    
    public ViewEscuela(ViewEstudiante padre){
        objPadreEstudiante=padre;
        initComponents();
    }

     public ViewEscuela(){
     
        initComponents();
    }
    
    private void initComponents(){
        this.getContentPane().setLayout(new BorderLayout());
        this.add(panelMolde);
        modelEscuela.setData(LogicEscuela.listarEscuela(BigDecimal.ZERO));
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
                Facultad fac=new Facultad();
                Escuela esc=new Escuela();
                esc.setId(BigDecimal.valueOf(new java.util.Date().getTime()));
                esc.setFacultad(fac);
                esc.setEstadoOperacion("i");                
                modelEscuela.getData().add(esc);
                modelEscuela.fireTableDataChanged();   
                panelMolde.getTablaDatos().changeSelection(modelEscuela.getRowCount()-1, 1, true, true);
                panelMolde.getTablaDatos().editCellAt(modelEscuela.getRowCount()-1, 1);                                
                }catch(Exception ex){}
    }
    
    public void actualizarTablaEscuela(){        
        modelEscuela.setData(LogicEscuela.listarEscuela(BigDecimal.ZERO));
        modelEscuela.fireTableDataChanged();    
        try{
        objPadreDocente.getArbol().removeAll();
        objPadreDocente.construirArbol();
        objPadreDocente.getArbol().updateUI();        
        ((CbModelEscuela)objPadreDocente.getFrmDocente().getCbEscuela().getModel()).setData(new ArrayList<Escuela>());
        objPadreDocente.getFrmDocente().getCbEscuela().updateUI();
        objPadreDocente.getFrmDocente().getCbEscuela().getEditor().setItem(null);
        objPadreDocente.getFrmDocente().getCbFacultad().updateUI();
        objPadreDocente.getFrmDocente().getCbFacultad().getEditor().setItem(null);                
        }catch(Exception ex){        
        }
    }
    
    ActionListener actionListener=new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            if(btnAdd.equals(e.getSource())){
                nuevoRegistro();
            }else if(btnRollback.equals(e.getSource())){
                actualizarTablaEscuela();
            }else if(btnCommit.equals(e.getSource())){
                LogicEscuela.mantenimientoEscuela(modelEscuela.getListaCommit());
                modelEscuela.getListaCommit().clear();
                actualizarTablaEscuela();                
            }
        }
        
    };
        
    
}
