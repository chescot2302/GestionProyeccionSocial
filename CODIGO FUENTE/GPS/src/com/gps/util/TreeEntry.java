/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * @author Jona
 */
public class TreeEntry {

    private String titulo;
    private ImageIcon icono;
    private ArrayList<TreeEntry> hijos;
    private String identificadorUnico;

    public String getIdentificadorUnico() {
        return identificadorUnico;
    }

    public void setIdentificadorUnico(String identificadorUnico) {
        this.identificadorUnico = identificadorUnico;
    }

    public TreeEntry(String titulo, ImageIcon icon, String id) {
        this();
        setTitle(titulo);
        setIcon(icon);
        setIdentificadorUnico(id);
    }

    public ArrayList<TreeEntry> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<TreeEntry> hijos) {
        this.hijos = hijos;
    }

    private TreeEntry() {
        this.hijos = new ArrayList<TreeEntry>();
    }

    private void setTitle(String titulo) {
        this.titulo = titulo;
    }

    private void setIcon(ImageIcon icon) {
        icono = icon;
    }

    public String getTitulo() {
        return titulo;
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public ArrayList<TreeEntry> getEntries() {
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

    public void add(TreeEntry treeEntry) {
        hijos.add(treeEntry);
    }
}
