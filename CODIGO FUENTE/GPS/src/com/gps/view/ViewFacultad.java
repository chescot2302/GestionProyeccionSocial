/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Facultad;
import com.gps.bean.Universidad;
import com.gps.comboboxmodel.CbModelFacultad;
import com.gps.logic.LogicFacultad;
import com.gps.logic.LogicUniversidad;
import com.gps.tablemodel.TableModelFacultad;
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
public class ViewFacultad extends JDialog {
    private FrmPanel panelMolde=new FrmPanel(ViewLogin.picture.getIconFacultas32(),"Facultad");
    private ViewDocente objPadreDocente;
    private ViewEstudiante objPadreEstudiante;
    private TableModelFacultad model=new TableModelFacultad(panelMolde.getTablaDatos());
    private JButton btnAdd=new JButton(ViewLogin.picture.getIconAgregar());
    private JButton btnCommit=new JButton(ViewLogin.picture.getIconCommit());
    private JButton btnRollback=new JButton(ViewLogin.picture.getIconRollBack());
    public ViewFacultad(ViewDocente padre){
        objPadreDocente=padre;
        initComponents();
    }
    
    public ViewFacultad(ViewEstudiante padre){
        objPadreEstudiante=padre;
        initComponents();
    }

     public ViewFacultad(){
       
        initComponents();
    }
    
    private void initComponents(){
        this.getContentPane().setLayout(new BorderLayout());
        this.add(panelMolde);
        model.setData(LogicFacultad.listarFacultad(BigDecimal.ONE));
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
                Universidad uni=LogicUniversidad.beanUniversidad(BigDecimal.ONE);
                Facultad fac=new Facultad();                
                fac.setId(BigDecimal.valueOf(new java.util.Date().getTime()));
                fac.setUniversidad(uni);
                fac.setEstadoOperacion("i");                
                model.getData().add(fac);
                model.fireTableDataChanged();   
                panelMolde.getTablaDatos().changeSelection(model.getRowCount()-1, 1, true, true);
                panelMolde.getTablaDatos().editCellAt(model.getRowCount()-1, 1);                                
                }catch(Exception ex){}
    }
    
    public void actualizarTabla(){
        model.setData(LogicFacultad.listarFacultad(BigDecimal.ONE));
        model.fireTableDataChanged(); 
        try{
        objPadreDocente.construirArbol();
        objPadreDocente.getArbol().updateUI();
        ((CbModelFacultad)objPadreDocente.getFrmDocente().getCbFacultad().getModel()).setData(LogicFacultad.listarFacultad(BigDecimal.ONE));
        objPadreDocente.getFrmDocente().getCbFacultad().updateUI();
        objPadreDocente.getFrmDocente().getCbFacultad().getEditor().setItem(null);                       
        }catch(Exception ex){}
    }
    
    ActionListener actionListener=new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            if(btnAdd.equals(e.getSource())){
                nuevoRegistro();
            }else if(btnRollback.equals(e.getSource())){
                actualizarTabla();
            }else if(btnCommit.equals(e.getSource())){
                LogicFacultad.mantenimientoFacultad(model.getListaCommit());
                model.getListaCommit().clear();
                actualizarTabla();                  
            }
        }
        
    };
        
    
}
