/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.gps.tablemodel;

import com.gps.bean.Proyecto;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class TableModelProyecto extends AbstractTableModel {

    private ArrayList<String> headerTable = new ArrayList();
    private ArrayList<Proyecto> data = new ArrayList();
    private ArrayList<TableModelListener> listeners = new ArrayList();

    public TableModelProyecto() {
        createHeaders();
    }

    public TableModelProyecto(ArrayList<Proyecto> lista) {
        data = lista;
        createHeaders();
    }

    private void createHeaders() {
        headerTable.add("ID");
        headerTable.add("DESCRIPCION DEL PROYECTO");
        headerTable.add("RUC");
        headerTable.add("SOLICITANTE");
        headerTable.add("FECHA DE SOLICITUD");
    }

    public void borrar(int fila) {
        try {
            data.remove(fila);
            TableModelEvent evento;
            evento = new TableModelEvent(this, fila, fila, TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
            avisaSuscriptores(evento);
            fireTableDataChanged();
        } catch (Exception ex) {
        }
    }

    public void insertar(Proyecto obj) {
        data.add(obj);
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    public void updateo(Proyecto obj, int fila) {
        data.remove(fila);
        data.add(fila, obj);
        TableModelEvent evento;
        evento = new TableModelEvent(this, fila, fila, TableModelEvent.ALL_COLUMNS, TableModelEvent.UPDATE);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return headerTable.get(column);
    }

    @Override
    public int getRowCount() {
        try {
            return data.size();
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int getColumnCount() {
        return headerTable.size();
    }

    public Proyecto getValue(int rowIndex) {
        try {
            return data.get(rowIndex);
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return data.get(rowIndex).getId();
            case 1:
                return data.get(rowIndex).getDescripcion();
            case 2:
                return data.get(rowIndex).getSolicitante().getRuc();
            case 3:
                return data.get(rowIndex).getSolicitante().getDescripcion();
            case 4:
                return data.get(rowIndex).getFechasol();
            default:
                return null;
        }
    }

    private void avisaSuscriptores(TableModelEvent evento) {
        Iterator i = listeners.iterator();
        while (i.hasNext()) {
            ((TableModelListener) i.next()).tableChanged(evento);
        }
    }

    @Override
    public int findColumn(String columnName) {
        for (int i = 0; i < headerTable.size(); i++) {
            if (columnName.equals(headerTable.get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return java.sql.Date.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listenerList.add(TableModelListener.class, l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listenerList.remove(TableModelListener.class, l);
    }

    public ArrayList<Proyecto> getData() {
        return data;
    }

    public void setData(ArrayList<Proyecto> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}
