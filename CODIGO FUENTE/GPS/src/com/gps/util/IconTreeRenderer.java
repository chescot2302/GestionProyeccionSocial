/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Jona
 */
public class IconTreeRenderer extends DefaultTreeCellRenderer {

    private final JLabel label;
    private final Font orgFont;
    private final Font boldFont;
    private TreeEntry te;

    public IconTreeRenderer() {
        label = new JLabel();
        label.setBackground(null);

        orgFont = label.getFont();
        boldFont = label.getFont().deriveFont(label.getFont().getStyle() ^ Font.BOLD);
    }

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        te = (TreeEntry) value;

        label.setText(te.getTitulo());
        label.setToolTipText(te.getTitulo());
        if (sel) {
            label.setFont(boldFont);
            label.setIcon(te.getIcono());
        } else {
            label.setFont(orgFont);
            label.setIcon(te.getIcono());
        }
        label.setPreferredSize(new Dimension(200, 20));
        return label;
    }

    public TreeEntry getTe() {
        return te;
    }

    public void setTe(TreeEntry te) {
        this.te = te;
    }

    public boolean isHasFocus() {
        return hasFocus;
    }

    public void setHasFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    public ComponentUI getUi() {
        return ui;
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui;
    }
}
