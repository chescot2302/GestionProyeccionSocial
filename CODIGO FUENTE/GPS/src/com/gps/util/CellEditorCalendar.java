/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.Component;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Administrador
 */
public class CellEditorCalendar extends AbstractCellEditor implements TableCellEditor{    
    private JSpinnerDateEditor fecha = new JSpinnerDateEditor();    
    private JDateChooser calfecha=new JDateChooser(fecha);    
    public CellEditorCalendar(){        
            calfecha.setDate(new Date());        
    }
    @Override
    public Object getCellEditorValue() {
        try{       
            java.sql.Date date=new java.sql.Date(calfecha.getDate().getTime());
            return date;
        }catch(Exception ex){
            return null;
        }        
    }

    @Override        
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {                    
            return calfecha;
                
    }
    
}
