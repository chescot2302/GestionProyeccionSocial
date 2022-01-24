/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.tablemodel;

import com.gps.bean.Solicitante;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrador
 */
public class TableModelSolicitante extends AbstractTableModel {

    private ArrayList<String> headerTable = new ArrayList();
    private ArrayList<Solicitante> data = new ArrayList();
    private ArrayList<TableModelListener> listeners = new ArrayList();

    public TableModelSolicitante() {
        createHeaders();
    }

    public TableModelSolicitante(ArrayList<Solicitante> lista) {
        data = lista;
        createHeaders();
    }

    private void createHeaders() {
        headerTable.add("ID");
        headerTable.add("RUC");
        headerTable.add("RAZON SOCIAL");
        headerTable.add("DIRECCION");
        headerTable.add("DEPARTAMENTO");
        headerTable.add("PROVINCIA");
        headerTable.add("DISTRITO");        
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

    public void insertar(Solicitante obj) {
        data.add(obj);
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    public void updateo(Solicitante obj, int fila) {
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

    public Solicitante getValue(int rowIndex) {
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
                return data.get(rowIndex).getRuc();
            case 2:
                return data.get(rowIndex).getDescripcion();
            case 3:
                return data.get(rowIndex).getDireccion();
            case 4:
                return data.get(rowIndex).getUbigeo().getDepartamento();
            case 5:
                return data.get(rowIndex).getUbigeo().getProvincia();
            case 6:
                return data.get(rowIndex).getUbigeo().getDistrito();            
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
                return BigDecimal.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;            
            case 5:
                return String.class;            
            case 6:
                return String.class;            
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

    public ArrayList<Solicitante> getData() {
        return data;
    }

    public void setData(ArrayList<Solicitante> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}
