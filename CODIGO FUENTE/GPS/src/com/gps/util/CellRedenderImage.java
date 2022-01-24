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
public class CellRedenderImage implements TableCellRenderer {

    private JLabel lblimage = new JLabel();

    public CellRedenderImage() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {        
        value=String.valueOf(value);
        if (value.equals("n")) {
            lblimage.setIcon(null);
        } else if (value.equals("i")) {
            lblimage.setIcon(ViewLogin.picture.getIconI());
        } else if (value.equals("e")) {
            lblimage.setIcon(ViewLogin.picture.getIconE());
        } else if (value.equals("a")) {
            lblimage.setIcon(ViewLogin.picture.getIconA());
        }
        return lblimage;
    }
}
