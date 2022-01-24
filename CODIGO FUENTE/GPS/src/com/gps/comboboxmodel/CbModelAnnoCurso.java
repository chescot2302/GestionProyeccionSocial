/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.comboboxmodel;

import com.gps.bean.EstadoAnno;
import com.gps.bean.EstadoAnno;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Administrador
 */
public class CbModelAnnoCurso extends AbstractListModel implements MutableComboBoxModel{
    private Object selectedItem;
    private ArrayList<EstadoAnno> data = new ArrayList();

    public CbModelAnnoCurso() {
    }

    public CbModelAnnoCurso(ArrayList<EstadoAnno> datos) {
        data = datos;        
    }

    @Override
    public int getSize() {
        try {
            return data.size();
        } catch (NullPointerException ex) {
            return 0;
        }
    }

    @Override
    public Object getElementAt(int index) {
        return (Object) data.get(index).getEstado();
    }

    public EstadoAnno getElement(int index) {
        return data.get(index);
    }

    @Override
    public void addElement(Object obj) {
        data.add((EstadoAnno) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((EstadoAnno) obj);
        if (index != -1) {
            data.remove((EstadoAnno) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (EstadoAnno) obj);
        fireIntervalAdded(this, index, index);
    }

    @Override
    public void removeElementAt(int index) {
        if (getSize() >= index) {
            data.remove(index);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem = anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }

    public ArrayList<EstadoAnno> getData() {
        return data;
    }

    public void setData(ArrayList<EstadoAnno> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}

