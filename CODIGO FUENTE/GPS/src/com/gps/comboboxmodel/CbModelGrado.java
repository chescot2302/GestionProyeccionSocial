/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.comboboxmodel;

import com.gps.bean.Grado;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Administrador
 */
public class CbModelGrado extends AbstractListModel implements MutableComboBoxModel{
    private Object selectedItem;
    private ArrayList<Grado> data = new ArrayList();

    public CbModelGrado() {
    }

    public CbModelGrado(ArrayList<Grado> datos) {
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
        return (Object) data.get(index).getDescripcion();
    }

    public Grado getElement(int index) {
        return data.get(index);
    }

    @Override
    public void addElement(Object obj) {
        data.add((Grado) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((Grado) obj);
        if (index != -1) {
            data.remove((Grado) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (Grado) obj);
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

    public ArrayList<Grado> getData() {
        return data;
    }

    public void setData(ArrayList<Grado> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}
