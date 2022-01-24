/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.tablemodel;

import com.gps.bean.Estudiante;
import com.gps.bean.ProyEst;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JTable;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Administrador
 */
public class TableModelAlumno extends AbstractTableModel {
    private ArrayList<String> headerTable = new ArrayList();
    private ArrayList<ProyEst> data = new ArrayList();
    private ArrayList<TableModelListener> listeners = new ArrayList();
    private JTable tablaDatos;    
    private HashSet <ProyEst> listaCommit=new HashSet ();                    
    
    public TableModelAlumno(JTable tabla) {
        tablaDatos=tabla;
        createHeaders();        
    }
    
    public TableModelAlumno() {        
        createHeaders();          
    }

    public TableModelAlumno(JTable tabla,ArrayList<ProyEst> lista) {
        tablaDatos=tabla;
        data = lista;
        createHeaders();        
    }   

    public TableModelAlumno(ArrayList<ProyEst> lista) {
        data = lista;
        createHeaders();        
    }

    private void createHeaders() {     
        headerTable.add("");
        headerTable.add("FACULTAD");
        headerTable.add("ESCUELA");           
        headerTable.add("CODIGO UNIV");        
        headerTable.add("ESTUDIANTE");                
        headerTable.add("ELIMINAR");                
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

    public void insertar(ProyEst obj) {
        data.add(obj);
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    public void updateo(ProyEst obj, int fila) {
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

    public ProyEst getValue(int rowIndex) {
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
                return data.get(rowIndex).getEstadoOperacion();
            case 1:
                return data.get(rowIndex).getEstudiante().getEscuela().getFacultad().getSiglas();
            case 2:
                return data.get(rowIndex).getEstudiante().getEscuela().getDescripcion();            
            case 3:
                return data.get(rowIndex).getEstudiante().getCodigouniv();            
            case 4:
                return data.get(rowIndex).getEstudiante().getAppaterno()+" "+data.get(rowIndex).getEstudiante().getApmaterno()+" "+data.get(rowIndex).getEstudiante().getNombres();                        
            case 5:
                return data.get(rowIndex).isSeleccion();
            default:
                return null;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) { 
        String aux;
        String valor;                
        ProyEst auxObj=data.get(rowIndex);                        
        boolean eliminar=false;
       switch (columnIndex) {                        
            case 5: 
                if(!data.get(rowIndex).getEstadoOperacion().equals("i")){
                    data.get(rowIndex).setSeleccion((Boolean)aValue);
                if(data.get(rowIndex).isSeleccion()){
                data.get(rowIndex).setEstadoOperacion("e");                
                }else{
                data.get(rowIndex).setEstadoOperacion("n");
                }
                }else{
                    data.remove(rowIndex);        
                    listaCommit.remove(auxObj);   
                    eliminar=true;
                }                
                break;
            default:
                break;
        }              
       try{
           if(!eliminar){
               if(data.get(rowIndex).getEstadoOperacion().equals("n")){
               listaCommit.remove(auxObj);
           }else{
               listaCommit.remove(auxObj);
               listaCommit.add(data.get(rowIndex));                                              
           }
           Iterator iterar=listaCommit.iterator();
           System.out.println("TAMAÑO: "+listaCommit.size());
           while(iterar.hasNext()){
               ProyEst bean=(ProyEst)iterar.next();               
               System.out.println(bean.getEstadoOperacion());
           }
           }
           System.out.println("TAMAÑO: "+listaCommit.size());
       }catch(Exception ex){}
       TableModelEvent evento;
        evento = new TableModelEvent(this, rowIndex, rowIndex, TableModelEvent.ALL_COLUMNS, TableModelEvent.ALL_COLUMNS);
        avisaSuscriptores(evento);
        fireTableDataChanged();
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
                return String.class;                       
            case 5:
                return Boolean.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {        
            switch (columnIndex) {            
            case 5:
                return true;
            default:
                return false;
            }        
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listenerList.add(TableModelListener.class, l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listenerList.remove(TableModelListener.class, l);
    }

    public ArrayList<ProyEst> getData() {
        return data;
    }

    public void setData(ArrayList<ProyEst> data) {
        this.data = data;
        tablaDatos.setModel(this);  
    }

    public ArrayList<String> getHeaderTable() {
        return headerTable;
    }

    public void setHeaderTable(ArrayList<String> headerTable) {
        this.headerTable = headerTable;
    }

    public ArrayList<TableModelListener> getListeners() {
        return listeners;
    }

    public void setListeners(ArrayList<TableModelListener> listeners) {
        this.listeners = listeners;
    }
    

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public HashSet<ProyEst> getListaCommit() {
        return listaCommit;
    }

    public void setListaCommit(HashSet<ProyEst> listaCommit) {
        this.listaCommit = listaCommit;
    }

    public JTable getTablaDatos() {
        return tablaDatos;
    }

    public void setTablaDatos(JTable tablaDatos) {
        this.tablaDatos = tablaDatos;
    }
    
    
}
