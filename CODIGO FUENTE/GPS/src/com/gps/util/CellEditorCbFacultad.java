/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import com.gps.comboboxmodel.CbModelFacultad;
import com.gps.logic.LogicFacultad;
import java.awt.Component;
import java.math.BigDecimal;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Administrador
 */
public class CellEditorCbFacultad extends AbstractCellEditor implements TableCellEditor{
    private JComboBoxAutocomplete cbFacultad = new JComboBoxAutocomplete();
    private CbModelFacultad mdcbfacultad = new CbModelFacultad();
    public CellEditorCbFacultad(){        
            mdcbfacultad.setData(LogicFacultad.listarFacultad(BigDecimal.ONE));        
            cbFacultad.setModel(mdcbfacultad);
    }
    @Override
    public Object getCellEditorValue() {
        try{
            //mdcbfacultad.setData(LogicFacultad.listarFacultad(BigDecimal.ONE));        
            //cbFacultad.setModel(mdcbfacultad);
            return mdcbfacultad.getElement(cbFacultad.getSelectedIndex());
        }catch(Exception ex){
            return null;
        }        
    }

    @Override        
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {        
            mdcbfacultad.setData(LogicFacultad.listarFacultad(BigDecimal.ONE));        
            cbFacultad.setModel(mdcbfacultad);
            return cbFacultad;
                
    }
    
}
