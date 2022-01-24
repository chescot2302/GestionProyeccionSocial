/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.comboboxmodel;

import com.gps.bean.Facultad;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Administrador
 */
public class CbModelFacultad extends AbstractListModel implements MutableComboBoxModel{
    private Object selectedItem;
    private ArrayList<Facultad> data = new ArrayList();

    public CbModelFacultad() {
    }

    public CbModelFacultad(ArrayList<Facultad> datos) {
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
        return (Object) data.get(index).getSiglas();
    }

    public Facultad getElement(int index) {
        return data.get(index);
    }

    @Override
    public void addElement(Object obj) {
        data.add((Facultad) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((Facultad) obj);
        if (index != -1) {
            data.remove((Facultad) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (Facultad) obj);
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

    public ArrayList<Facultad> getData() {
        return data;
    }

    public void setData(ArrayList<Facultad> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}
