/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Administrador
 */
public class BeanTreeModel implements TreeModel {
    private TreeEntryBean root;    
    protected EventListenerList listenerList = new EventListenerList();
    
    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((TreeEntryBean) parent).get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((TreeEntryBean) parent).size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((TreeEntryBean) node).size() == 0;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((TreeEntryBean) parent).indexOf(child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listenerList.add(TreeModelListener.class, l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listenerList.remove(TreeModelListener.class, l);
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public void setRoot(TreeEntryBean root) {
        this.root = root;
    }
    
    
}
