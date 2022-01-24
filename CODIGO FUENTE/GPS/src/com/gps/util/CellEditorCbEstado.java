/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import com.gps.comboboxmodel.CbModelEstado;
import com.gps.logic.LogicEstado;
import com.gps.logic.LogicTipoDocumento;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Administrador
 */
public class CellEditorCbEstado extends AbstractCellEditor implements TableCellEditor{
    private JComboBoxAutocomplete cbEstado = new JComboBoxAutocomplete();
    private CbModelEstado mdcbEstado = new CbModelEstado();
    public CellEditorCbEstado(){        
            mdcbEstado.setData(LogicEstado.listarEstado());        
            cbEstado.setModel(mdcbEstado);
    }
    @Override
    public Object getCellEditorValue() {
        try{
            //mdcbTipodoc.setData(LogicTipoDocumento.listarTipoDocumento());        
            //cbTipoDoc.setModel(mdcbTipodoc);
            return mdcbEstado.getElement(cbEstado.getSelectedIndex());
        }catch(Exception ex){
            return null;
        }        
    }

    @Override        
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {        
            mdcbEstado.setData(LogicEstado.listarEstado());        
            cbEstado.setModel(mdcbEstado);
            return cbEstado;
                
    }
    
}
