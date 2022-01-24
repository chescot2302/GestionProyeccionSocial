/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrador
 */
public class TreeEntryBean {
    private String titulo;
    private Object bean;
    private ImageIcon icono;
    private ArrayList<TreeEntryBean> hijos=new ArrayList();

    public TreeEntryBean(String titulo, Object bean, ImageIcon icono, ArrayList<TreeEntryBean> hijos) {
        this.titulo = titulo;
        this.bean = bean;
        this.icono = icono;
        this.hijos = hijos;
    }

    public TreeEntryBean(String titulo, Object bean, ImageIcon icono) {
        this.titulo = titulo;
        this.bean = bean;
        this.icono = icono;
    }

    public TreeEntryBean(String titulo, Object bean) {
        this.titulo = titulo;
        this.bean = bean;
    }
        

    public ArrayList<TreeEntryBean> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<TreeEntryBean> hijos) {
        this.hijos = hijos;
    }        
    

    public ImageIcon getIcono() {
        return icono;
    }

    public ArrayList<TreeEntryBean> getEntries() {
        return hijos;
    }

    public int indexOf(Object child) {
        return hijos.indexOf(child);
    }

    public int size() {
        return hijos.size();
    }

    public Object get(int index) {
        return hijos.get(index);
    }

    public void add(TreeEntryBean treeEntry) {
        hijos.add(treeEntry);
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    
}
