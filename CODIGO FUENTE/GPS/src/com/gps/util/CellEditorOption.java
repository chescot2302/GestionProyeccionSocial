/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import com.gps.bean.AnnoCurso;
import com.gps.bean.EstProy;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Administrador
 */
public class CellEditorOption extends AbstractCellEditor implements TableCellEditor {

    private JCheckBox check = new JCheckBox();
    //private CbModelAnnoCurso mdcbempresas = new CbModelAnnoCurso();
    ArrayList<EstProy> datos;

    public CellEditorOption(ArrayList<EstProy> data) {
        datos = data;
    }

    @Override
    public Object getCellEditorValue() {
        try {
            if (check.isSelected()) {
                  Iterator j = datos.iterator();
                  while(j.hasNext()){
                      EstProy ob=(EstProy)j.next();
                      ob.setEstadoactual("D");
                  }
                return "A";
            } else {
                return "D";
            }

        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return check;

    }
}
