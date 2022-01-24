    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;

import java.util.ArrayList;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author Jona
 */
public class IconTreeModel implements TreeModel {

    private TreeEntry root;
    protected EventListenerList listenerList = new EventListenerList();

    public IconTreeModel() {
        root = new TreeEntry("_ROOT_", null, "root");
        ArrayList<TreeEntry> hijosRoot = new ArrayList();
        Imagenes picture = new Imagenes();
        
        /*Modulos */
        TreeEntry moduloMantenimiento = new TreeEntry("MODULO SGPS", picture.getIconSGPS(), "MANT"); 
        ArrayList<TreeEntry> hijosModuloMantenimiento = new ArrayList();
        hijosRoot.add(moduloMantenimiento);
        
        TreeEntry moduloOtros = new TreeEntry("OTROS", picture.getIconDocumento32(), "OTROS");         
        hijosRoot.add(moduloOtros);
        root.setHijos(hijosRoot);                        
        
        TreeEntry moduloProyecto = new TreeEntry("PROYECTOS", picture.getIconProject(), "MANTINTERIORPROYECTOS"); 
        ArrayList<TreeEntry> hijosModuloProyecto = new ArrayList();
        hijosModuloMantenimiento.add(moduloProyecto);        
        
        TreeEntry moduloParticipante = new TreeEntry("PARTICIPANTES", picture.getIconPeople(), "MANTINTERIORPARTICIPANTES"); 
        ArrayList<TreeEntry> hijosModuloParticipante = new ArrayList();
        hijosModuloMantenimiento.add(moduloParticipante);
        moduloMantenimiento.setHijos(hijosModuloMantenimiento);
        
              
                
        
        //TreeEntry estado= new TreeEntry("ESTADO", picture.getIconEstado32(), "MANTESTADO");
        //hijosModuloMantenimientointerior1.add(estado);
        TreeEntry proyecto= new TreeEntry("PROYECTO", picture.getIconProyecto32(), "MANTPROY");
        hijosModuloProyecto.add(proyecto);                
        TreeEntry solicitante= new TreeEntry("SOLICITANTE", picture.getIconSolicitante32(), "MANTSOL");
        hijosModuloProyecto.add(solicitante);                
                    
                    
       
        moduloProyecto.setHijos(hijosModuloProyecto);
        moduloMantenimiento.setHijos(hijosModuloMantenimiento);
        
        
        
        
        
        
        
         TreeEntry decano= new TreeEntry("DECANO", picture.getIconDecano32(), "MANTDEC");
        hijosModuloParticipante.add(decano);                
        TreeEntry docente= new TreeEntry("DOCENTE", picture.getIconDocente32(), "MANTDOCENTE");
        hijosModuloParticipante.add(docente);         
        TreeEntry estudiante= new TreeEntry("ESTUDIANTE", picture.getIconEstudiante32(), "MANTEST");
        hijosModuloParticipante.add(estudiante);                
      
        moduloParticipante.setHijos(hijosModuloParticipante);                                
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        return ((TreeEntry) parent).get(index);
    }

    @Override
    public int getChildCount(Object parent) {
        return ((TreeEntry) parent).size();
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((TreeEntry) node).size() == 0;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        return ((TreeEntry) parent).indexOf(child);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listenerList.add(TreeModelListener.class, l);
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listenerList.remove(TreeModelListener.class, l);
    }
}