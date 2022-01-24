/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import com.gps.view.ViewLogin;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Administrador
 */
public class CellRendererImageEstado implements TableCellRenderer {

    private JLabel lblimage = new JLabel();

    public CellRendererImageEstado() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {        
        value=String.valueOf(value);
        if (value.equals("A")) {
            lblimage.setIcon(ViewLogin.picture.getIconActivado());
        } else if(value.equals("D")){
            lblimage.setIcon(ViewLogin.picture.getIconDesactivado());
        }else{
              lblimage.setIcon(null);
        }
        return lblimage;
    }
}
