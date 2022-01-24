/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author JONA
 */
public class JComboBoxAutocomplete extends JComboBox implements CellEditorListener, TableCellEditor {

    private ArrayList<CellEditorListener> suscriptores = new ArrayList();

    public JComboBoxAutocomplete() {
        super();
        this.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                editado(true);
            }
        });

        this.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                //editado(false);
            }

            @Override
            public void focusLost(FocusEvent e) {
                //editado(false);
            }
        });
    }

    @Override
    public boolean isEditable() {
        return true;
    }

    @Override
    public void addActionListener(ActionListener l) {
        listenerList.add(ActionListener.class, l);
    }

    public void editado(boolean cambiado) {
        ChangeEvent evento = new ChangeEvent(this);
        int i;
        for (i = 0; i < suscriptores.size(); i++) {
            CellEditorListener aux = (CellEditorListener) suscriptores.get(i);
            if (cambiado) {
                aux.editingStopped(evento);
            } else {
                aux.editingCanceled(evento);
            }
        }
    }

    @Override
    public void editingStopped(ChangeEvent e) {
        System.out.println("Stop");
    }

    @Override
    public void editingCanceled(ChangeEvent e) {
        System.out.println("Cancel");
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this;
    }

    @Override
    public Object getCellEditorValue() {
        return this.getItemAt(this.getSelectedIndex());
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }

    @Override
    public void cancelCellEditing() {
        System.out.println("Cancel");
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        suscriptores.add(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        suscriptores.remove(l);
    }
}
