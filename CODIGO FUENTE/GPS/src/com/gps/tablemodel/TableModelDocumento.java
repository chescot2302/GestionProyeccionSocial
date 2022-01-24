/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.tablemodel;

import com.gps.bean.Documento;
import com.gps.bean.TipoDocumento;
import java.math.BigDecimal;
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
public class TableModelDocumento extends AbstractTableModel {

    private ArrayList<String> headerTable = new ArrayList();
    private ArrayList<Documento> data = new ArrayList();
    private ArrayList<TableModelListener> listeners = new ArrayList();
    private JTable tablaDatos;    
    private HashSet <Documento> listaCommit=new HashSet ();    
    public TableModelDocumento(JTable tabla) {
        tablaDatos=tabla;
        createHeaders();        
    }
    
    public TableModelDocumento() {        
        createHeaders();          
    }

    public TableModelDocumento(JTable tabla,ArrayList<Documento> lista) {
        tablaDatos=tabla;
        data = lista;
        createHeaders();        
    }

    private void createHeaders() {
        headerTable.add("");        
        headerTable.add("TIPO DOC");
        headerTable.add("SERIE");
        headerTable.add("NUMERO");     
        headerTable.add("PROCEDENCIA");
        headerTable.add("FECHA ADJUNTA");
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

    public void insertar(Documento i) {
        data.add(i);
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1, this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
        avisaSuscriptores(evento);
        fireTableDataChanged();
    }

    public void update(Documento i, int fila) {
        data.remove(fila);
        data.add(fila, i);
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

    public Documento getValue(int rowIndex) {
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
                return data.get(rowIndex).getEstadoOperacion();//data.get(rowIndex).getCompania();            
            case 1:                
                 return data.get(rowIndex).getTipoDocumento().getDescripcion();                   
            case 2:
                return data.get(rowIndex).getAnno();
            case 3:
                return data.get(rowIndex).getNumero();                                  
            case 4:
                return data.get(rowIndex).getProcedencia();                                  
            case 5:
                return data.get(rowIndex).getFecha();                                  
            case 6:
                return data.get(rowIndex).isSeleccion();
            default:
                return null;
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) { 
        String aux;
        String valor;                
        Documento auxObj=null;
        try{
        auxObj=data.get(rowIndex);                
        }catch(Exception ex){}
        TipoDocumento tip=null;        
        TipoDocumento auxTip=null;
        boolean eliminar=false;
       switch (columnIndex) {
            case 0:                
                break;
            case 1: 
                try{
                auxTip=(TipoDocumento)aValue;
                System.out.println("Este es el valor: "+auxTip.getId());
                if(aValue!=null){
                if(!data.get(rowIndex).getEstadoOperacion().equals("i")){
                tip=data.get(rowIndex).getTipoDocumento();                   
                data.get(rowIndex).setTipoDocumento(auxTip);                
                if(auxTip.equals(tip)){
                    data.get(rowIndex).setEstadoOperacion("n");
                }else{
                    data.get(rowIndex).setEstadoOperacion("a");
                }
                }else{                    
                    data.get(rowIndex).setTipoDocumento(auxTip);                                    
                }
                }
                break;                                                                
                }catch(Exception ex){break;}                
            case 2:  
                try{
                valor=String.valueOf(aValue);
                if(!data.get(rowIndex).getEstadoOperacion().equals("i")){
                break;
                }else{
                    data.get(rowIndex).setAnno((BigDecimal.valueOf(Double.valueOf(valor)).setScale(0)));
                }                
                break;
                }catch(Exception ex){break;}
                    
            case 3:                   
                try{
                valor=String.valueOf(aValue);
                if(!data.get(rowIndex).getEstadoOperacion().equals("i")){
                break;
                }else{
                    data.get(rowIndex).setNumero((BigDecimal.valueOf(Double.valueOf(valor))).setScale(0));
                }                
                break;
                }catch(Exception ex){break;}
            case 4:                   
                valor=String.valueOf(aValue);                
                if(!data.get(rowIndex).getEstadoOperacion().equals("i")){
                aux=String.valueOf(data.get(rowIndex).getProcedencia());                
                data.get(rowIndex).setProcedencia(valor);
                if(aux.equals(valor)){
                    data.get(rowIndex).setEstadoOperacion("n");
                }else{
                    data.get(rowIndex).setEstadoOperacion("a");
                }
                }else{
                    data.get(rowIndex).setProcedencia(valor);
                }                
                break;
            case 5:                   
                java.sql.Date fecha=(java.sql.Date)aValue;                
                if(!data.get(rowIndex).getEstadoOperacion().equals("i")){
                java.sql.Date aucfech=data.get(rowIndex).getFecha();                
                data.get(rowIndex).setFecha(fecha);
                if(aucfech.equals(fecha)){
                    data.get(rowIndex).setEstadoOperacion("n");
                }else{
                    data.get(rowIndex).setEstadoOperacion("a");
                }
                }else{
                    data.get(rowIndex).setFecha(fecha);
                }                
                break;
            case 6: 
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
               Documento bean=(Documento)iterar.next();               
               System.out.println(bean.getEstadoOperacion()+"-"+bean.getAnno()+"-"+bean.getNumero()+"-"+bean.getTipoDocumento().getId()+
                       "-"+bean.getFecha()+"-"+bean.getProcedencia()+"-"+bean.getObjProyecto().getId());
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
                return BigDecimal.class;
            case 3:
                return BigDecimal.class;           
            case 4:
                return String.class;           
            case 5:
                return java.sql.Date.class;           
            case 6:
                return Boolean.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(data.get(rowIndex).getEstadoOperacion().equals("e")){
            switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return false;            
            case 4:
                return false;           
            case 5:
                return false;            
            case 6:
                return true;
            default:
                return false;
            }
        }else if(data.get(rowIndex).getEstadoOperacion().equals("i")){
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
            case 5:
                return true;            
            case 6:
                return true;
            default:
                return false;
        }
        }else{
        switch (columnIndex) {
            case 0:
                return false;
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return false;
            case 4:
                return true;
            case 5:
                return true;            
            case 6:
                return true;
            default:
                return false;
        }
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

    public ArrayList<Documento> getData() {
        return data;
    }

    public void setData(ArrayList<Documento> data) {
        this.data = data;
        tablaDatos.setModel(this);                                
        //tablaDatos.getColumnModel().getColumn(3).setCellEditor(new CellEditorCbFacultad());                
        //tablaDatos.getColumnModel().getColumn(1).setPreferredWidth(30);
        //tablaDatos.getColumnModel().getColumn(4).setPreferredWidth(200);          
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public HashSet<Documento> getListaCommit() {
        return listaCommit;
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

    public JTable getTablaDatos() {
        return tablaDatos;
    }

    public void setTablaDatos(JTable tablaDatos) {
        this.tablaDatos = tablaDatos;        
    }
    
    
    
}
