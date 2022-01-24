/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import com.gps.comboboxmodel.CbModelTipoDocumento;
import com.gps.logic.LogicTipoDocumento;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Administrador
 */
public class CellEditorCbTipoDoc extends AbstractCellEditor implements TableCellEditor{
    private JComboBoxAutocomplete cbTipoDoc = new JComboBoxAutocomplete();
    private CbModelTipoDocumento mdcbTipodoc = new CbModelTipoDocumento();
    public CellEditorCbTipoDoc(){        
            mdcbTipodoc.setData(LogicTipoDocumento.listarTipoDocumento());        
            cbTipoDoc.setModel(mdcbTipodoc);
    }
    @Override
    public Object getCellEditorValue() {
        try{
            //mdcbTipodoc.setData(LogicTipoDocumento.listarTipoDocumento());        
            //cbTipoDoc.setModel(mdcbTipodoc);
            return mdcbTipodoc.getElement(cbTipoDoc.getSelectedIndex());
        }catch(Exception ex){
            return null;
        }        
    }

    @Override        
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {        
            mdcbTipodoc.setData(LogicTipoDocumento.listarTipoDocumento());        
            cbTipoDoc.setModel(mdcbTipodoc);
            return cbTipoDoc;
                
    }
    
}
