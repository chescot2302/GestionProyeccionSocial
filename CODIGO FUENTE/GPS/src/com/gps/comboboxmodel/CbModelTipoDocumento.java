/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.comboboxmodel;

import com.gps.bean.TipoDocumento;
import java.util.ArrayList;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;
import javax.swing.event.EventListenerList;

/**
 *
 * @author Administrador
 */
public class CbModelTipoDocumento extends AbstractListModel implements MutableComboBoxModel{
    private Object selectedItem;
    private ArrayList<TipoDocumento> data = new ArrayList();

    public CbModelTipoDocumento() {
    }

    public CbModelTipoDocumento(ArrayList<TipoDocumento> datos) {
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

    public TipoDocumento getElement(int index) {
        return data.get(index);
    }

    @Override
    public void addElement(Object obj) {
        data.add((TipoDocumento) obj);
        int length = getSize();
        fireIntervalAdded(this, length - 1, length - 1);
    }

    @Override
    public void removeElement(Object obj) {
        int index = data.indexOf((TipoDocumento) obj);
        if (index != -1) {
            data.remove((TipoDocumento) obj);
            fireIntervalRemoved(this, index, index);
        }
    }

    @Override
    public void insertElementAt(Object obj, int index) {
        data.add(index, (TipoDocumento) obj);
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

    public ArrayList<TipoDocumento> getData() {
        return data;
    }

    public void setData(ArrayList<TipoDocumento> data) {
        this.data = data;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }
}
