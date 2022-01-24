/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Decano;
import com.gps.bean.Docente;
import com.gps.bean.Escuela;
import com.gps.bean.Facultad;
import com.gps.bean.Universidad;
import com.gps.comboboxmodel.CbModelFacuDecano;
import com.gps.logic.LogicDecano;
import com.gps.logic.LogicDocente;
import com.gps.logic.LogicEscuela;
import com.gps.logic.LogicFacultad;
import com.gps.logic.LogicUniversidad;
import com.gps.tablemodel.TableModelDecano;
import com.gps.tablemodel.TableModelDocente;
import com.gps.util.BeanTreeModel;
import com.gps.util.BeanTreeRenderer;
import com.gps.util.CellRendererImageEstado;
import com.gps.util.TreeEntryBean;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.RowFilter;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Administrador
 */
public class ViewDecano {

    private PanelMolde molde = new PanelMolde();
    private BeanTreeModel arbolContenido = new BeanTreeModel();
    private BeanTreeRenderer render = new BeanTreeRenderer();
    private JTree arbol = new JTree();
    private JScrollPane arbolScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    public static JScrollPane contenidoScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JSplitPane contenido = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, arbolScrollPane, contenidoScrollPane);
    private JPanel panelData = new JPanel();
    private JPanel panelTabla = new JPanel();
    private JPanel panelBusqueda = new JPanel();
    private JScrollPane scrollTabla = new JScrollPane();
    private TableModelDecano modelDecano = new TableModelDecano(LogicDecano.listarDecano());
    private JTable tablaDecano = new JTable();
    private JButton btnPreview = new JButton(ViewLogin.picture.getIconPreview());
    private JButton btnPrint = new JButton(ViewLogin.picture.getIconPrint());
    private JButton btnPDF = new JButton(ViewLogin.picture.getIconPdf());
    private JButton btnEXCEL = new JButton(ViewLogin.picture.getIconExcel());
    private JToolBar tool = new JToolBar();
    private FormDecano frmDecano = new FormDecano(tablaDecano, modelDecano);
    private FrmInfDecano frminfdec = new FrmInfDecano();
    private JLabel lblApPaterno = new JLabel("Ap. Paterno");
    private JLabel lblApMaterno = new JLabel("Ap. Materno");
    private JLabel lblNombres = new JLabel("Nombres");
    private JTextField buscaApPaterno = new JTextField();
    private JTextField buscaApMaterno = new JTextField();
    private JTextField buscaNombres = new JTextField();
    private ViewBusquedaDocente busquedaDocente;
    private Docente profesor;

    public ViewDecano() {
        initComponents();
    }

    private void initComponents() {
        btnPrint.setVisible(false);
        btnPDF.setVisible(false);
        btnEXCEL.setVisible(false);
        btnPreview.setVisible(false);
        panelData.setLayout(new BorderLayout());
        panelData.add(panelTabla, BorderLayout.CENTER);
        panelData.add(panelBusqueda, BorderLayout.NORTH);
        panelTabla.setLayout(new BorderLayout());
        panelBusqueda.setLayout(new BorderLayout());
        tool.add(btnPrint);
        tool.add(btnPreview);
        tool.add(btnPDF);
        tool.add(btnEXCEL);
        tool.addSeparator();
        tool.add(lblApPaterno);
        tool.add(buscaApPaterno);
        tool.addSeparator();
        tool.add(lblApMaterno);
        tool.add(buscaApMaterno);
        tool.addSeparator();
        tool.add(lblNombres);
        tool.add(buscaNombres);
        panelBusqueda.add(tool);
        panelTabla.add(scrollTabla);
        scrollTabla.setViewportView(tablaDecano);
        arbolScrollPane.setViewportView(arbol);
        contenidoScrollPane.getViewport().setLayout(new BorderLayout());
        contenidoScrollPane.setViewportView(panelData);
        contenido.setContinuousLayout(true);
        contenido.setOneTouchExpandable(true);
        contenido.setDividerLocation(250);
        contenido.setPreferredSize(new Dimension(400, 200));
        molde.getPanelinf().setLayout(new BorderLayout());
        molde.getPanelinf().add(frmDecano);
        molde.getPanelfrm().add(frminfdec);
        molde.getPanelarbol().setLayout(new BorderLayout());
        molde.getPanelarbol().setVisible(false);
        molde.getPaneltabla().setLayout(new BorderLayout());
        molde.getPaneltabla().add(contenido);
        construirArbol();
        arbol.addTreeSelectionListener(treeSeleccionListener);
        buscaApPaterno.addKeyListener(keyfiltrar);
        buscaApMaterno.addKeyListener(keyfiltrar);
        buscaNombres.addKeyListener(keyfiltrar);
        tablaDecano.getColumnModel().getColumn(0).setCellRenderer(new CellRendererImageEstado());
        tablaDecano.addMouseListener(mouseListener);
        frmDecano.getBtnAddDecano().addActionListener(actionListener);
        frmDecano.getBtnGuardar().addActionListener(actionListener);
        frmDecano.getBtnCancelar().addActionListener(actionListener);
        tablaDecano.setAutoCreateRowSorter(true);
        tablaDecano.addKeyListener(keyfiltrar);
    }
    
    private void cleaning(){
        profesor=null;   
        frmDecano.getTxtDecano().setText(null);
        frmDecano.getTxtfechainicio().setDate(new Date());
        frmDecano.getTxtfechafin().setDate(new Date());
        frmDecano.getBtnGuardar().setText("Guardar");
        frmDecano.getChkestado().setSelected(false);
        frmDecano.getLblId().setText(null);
    }
    
    private void cargarDatosUpdate() {
        frmDecano.getBtnGuardar().setText("Actualizar");
        try {
            int fila = tablaDecano.getSelectedRow();
            int modelrow = tablaDecano.convertRowIndexToModel(fila);
            Decano obj = ((TableModelDecano) tablaDecano.getModel()).getValue(modelrow);
            frmDecano.getLblId().setText(obj.getId().toString());            
            frmDecano.getCbFacultad().setSelectedItem(obj.getFacultad().getSiglas()+"  /  "+obj.getFacultad().getDescripcion());            
            frmDecano.getCbFacultad().getEditor().setItem(obj.getFacultad().getSiglas()+"  /  "+obj.getFacultad().getDescripcion());                        
            frmDecano.getFechainicio().setDate(obj.getFechaini());
            frmDecano.getFechafin().setDate(obj.getFechafin());
            if(obj.getEstadoActual().equals("A")){
                frmDecano.getChkestado().setSelected(true);
            }else{
                frmDecano.getChkestado().setSelected(false);
            }          
            profesor=obj.getDocente();
            frmDecano.getTxtDecano().setText(profesor.getAppaterno()+" "+profesor.getAppaterno()+", "+profesor.getNombres());
        } catch (Exception ex) {
            ViewApplication.mensajeEstado.setText("Error");
            ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconBad());
        }
    }
    
    private void mantenimiento(){
        BigDecimal id=null;
        BigDecimal idProfesor=null;  
        BigDecimal idFacultad=null;
        java.sql.Date ini=null;
        java.sql.Date fin=null;                
        String estadoDecano=null;        
        BigDecimal estado=null;
        switch (frmDecano.getBtnGuardar().getText()) {
            case "Guardar":
                try{
                ini = new java.sql.Date(frmDecano.getFechainicio().getDate().getTime());
                fin = new java.sql.Date(frmDecano.getFechafin().getDate().getTime());
                Facultad obj = ((CbModelFacuDecano) frmDecano.getCbFacultad().getModel()).getElement(frmDecano.getCbFacultad().getSelectedIndex());                
                idFacultad=obj.getId();
                idProfesor=profesor.getId();
                if(frmDecano.getChkestado().isSelected()){
                    estadoDecano="A";
                }else{
                    estadoDecano="D";
                }                
                estado=LogicDecano.registrarDecano(idProfesor,idFacultad,ini,fin,estadoDecano);                                                                                                
                if(estado.compareTo(BigDecimal.ONE)==0){
                    modelDecano.setData(LogicDecano.listarDecano());
                    modelDecano.fireTableDataChanged();
                    cleaning();
                }
                }catch(Exception ex){
                    ViewApplication.mensajeEstado.setText("Error");
                    ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconBad());
                }
                break;
            case "Actualizar":    
                try{
                id=BigDecimal.valueOf(Double.valueOf(frmDecano.getLblId().getText()));
                ini = new java.sql.Date(frmDecano.getFechainicio().getDate().getTime());
                fin = new java.sql.Date(frmDecano.getFechafin().getDate().getTime());
                Facultad obj = ((CbModelFacuDecano) frmDecano.getCbFacultad().getModel()).getElement(frmDecano.getCbFacultad().getSelectedIndex());                
                idFacultad=obj.getId();
                idProfesor=profesor.getId();
                if(frmDecano.getChkestado().isSelected()){
                    estadoDecano="A";
                }else{
                    estadoDecano="D";
                }                
                estado=LogicDecano.actualizarDecano(id,idProfesor,idFacultad,ini,fin,estadoDecano);                                                                                                
                if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                    modelDecano.setData(LogicDecano.listarDecano());
                    modelDecano.fireTableDataChanged();
                    cleaning();
                }
                }catch(Exception ex){
                    ViewApplication.mensajeEstado.setText("Error");
                    ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconBad());
                }
                break;
                }

        }
    
    
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(frmDecano.getBtnAddDecano())) {
                try {
                    Facultad obj = ((CbModelFacuDecano) frmDecano.getCbFacultad().getModel()).getElement(frmDecano.getCbFacultad().getSelectedIndex());
                    viewPanelBusquedaSolicitante();
                    busquedaDocente.getSearchDocente().getModelDocente().setData(LogicDocente.listarDocente(obj.getId()));
                    busquedaDocente.getSearchDocente().getModelDocente().fireTableDataChanged();
                    filtrarTabla(busquedaDocente.getSearchDocente().getTablaDocente(), busquedaDocente.getSearchDocente().getModelDocente(), obj);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Seleccionar una facultad correcta", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }else if(e.getSource().equals(frmDecano.getBtnGuardar())){
                try{
                mantenimiento();
                }catch(Exception ex){}
            }else if(e.getSource().equals(frmDecano.getBtnCancelar())){
                try{
                cleaning();
                }catch(Exception ex){}
            }            
        }
    };
    
    private void eliminar() {
        int fila = tablaDecano.getSelectedRow();
        int modelrow = tablaDecano.convertRowIndexToModel(fila);
        Decano decano = ((TableModelDecano) tablaDecano.getModel()).getValue(modelrow);
        if (LogicDecano.eliminarDecano(decano.getId())) {
            ((TableModelDecano) tablaDecano.getModel()).borrar(modelrow);
            ViewApplication.mensajeEstado.setText("Eliminado correctamente");
            ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconGood());
        } else {
            ViewApplication.mensajeEstado.setText("Error");
            ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconBad());
        }
    }

    private void cargarInfDecano() {
        int fila = tablaDecano.getSelectedRow();
        int modelrow = tablaDecano.convertRowIndexToModel(fila);
        Decano obj = ((TableModelDecano) tablaDecano.getModel()).getValue(modelrow);
        frminfdec.getLblFacultad().setText(obj.getFacultad().getDescripcion());
        frminfdec.getDocente().setText(obj.getDocente().getAppaterno() + " " + obj.getDocente().getApmaterno() + ", " + obj.getDocente().getNombres());
        frminfdec.getEscuela().setText(obj.getDocente().getEscuela().getDescripcion());
        frminfdec.getInicio().setText(obj.getFechaini().toString());
        frminfdec.getFin().setText(obj.getFechafin().toString());
        if (obj.getEstadoActual().equals("A")) {
            frminfdec.getEstado().setText("");
            frminfdec.getEstado().setIcon(ViewLogin.picture.getIconActivado());
        } else {
            frminfdec.getEstado().setText("");
            frminfdec.getEstado().setIcon(ViewLogin.picture.getIconDesactivado());
        }
        frminfdec.getLblGrado().setText(obj.getDocente().getGrado().getSiglas());
    }

    private void viewPanelBusquedaSolicitante() {
        try {
            busquedaDocente.getSearchDocente().getTablaDocente().addMouseListener(mouseListener);
            busquedaDocente.setVisible(true);            
        } catch (Exception ex) {
            busquedaDocente = new ViewBusquedaDocente();
            busquedaDocente.getSearchDocente().getTablaDocente().addMouseListener(mouseListener);
            busquedaDocente.setTitle("Buscar Docentes");
            //busquedaDocente.pack();            
            busquedaDocente.setSize(800, 500);
            busquedaDocente.setLocationRelativeTo(null);
            busquedaDocente.setVisible(true);            
        }
    }

    private void filtrarTabla(JTable tabla, TableModelDocente modelTablaSol, Facultad obj) {
        TableRowSorter<TableModelDocente> sorter = new TableRowSorter(modelTablaSol);
        RowFilter<TableModelDocente, Object> rf1 = null;
        RowFilter<TableModelDocente, Object> rf2 = null;
        RowFilter<TableModelDocente, Object> rf3 = null;
        RowFilter<TableModelDocente, Object> rf4 = null;
        List<RowFilter<TableModelDocente, Object>> rfs = new ArrayList(4);
        String facu = obj.getSiglas();
        try {
            rf1 = RowFilter.regexFilter(busquedaDocente.getSearchDocente().getBuscaApPaterno().getText(), 1);
            rf2 = RowFilter.regexFilter(busquedaDocente.getSearchDocente().getBuscaApMaterno().getText(), 2);
            rf3 = RowFilter.regexFilter(busquedaDocente.getSearchDocente().getBuscaNombres().getText(), 3);
            rf4 = RowFilter.regexFilter(facu, 4);
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);
            rfs.add(rf4);
            RowFilter<TableModelDocente, Object> af = RowFilter.andFilter(rfs);
            sorter.setRowFilter(af);
            tabla.setRowSorter(sorter);
        } catch (Exception ex) {
        }
    }
    MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource().equals(tablaDecano)) {
                if (e.getClickCount() == 1) {
                    cargarInfDecano();
                }else if(e.getClickCount() == 2){
                    cargarDatosUpdate();
                } 
            }else if (e.getSource().equals(busquedaDocente.getSearchDocente().getTablaDocente())) {
                    if (e.getClickCount() == 2) {
                        int fila = busquedaDocente.getSearchDocente().getTablaDocente().getSelectedRow();
                        int modelrow = busquedaDocente.getSearchDocente().getTablaDocente().convertRowIndexToModel(fila);
                        Docente docente = ((TableModelDocente) busquedaDocente.getSearchDocente().getTablaDocente().getModel()).getValue(modelrow);
                        profesor=docente;
                        frmDecano.getTxtDecano().setText(profesor.getAppaterno()+" "+profesor.getAppaterno()+", "+profesor.getNombres());
                        busquedaDocente.setVisible(false);            
                    }
                }            
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    };

    public void construirArbol() {
        Universidad uni = LogicUniversidad.beanUniversidad(BigDecimal.ONE);
        TreeEntryBean nodoRoot = new TreeEntryBean(uni.getSiglas(), uni, ViewLogin.picture.getIconUniversidad32());
        ArrayList<Facultad> facultades = LogicFacultad.listarFacultad(uni.getId());
        Iterator i = facultades.iterator();
        while (i.hasNext()) {
            Facultad fac = (Facultad) i.next();
            TreeEntryBean hijoRoot = new TreeEntryBean(fac.getSiglas(), fac, ViewLogin.picture.getIconFacultas32());
            ArrayList<Escuela> escuelas = LogicEscuela.listarEscuela(fac.getId());
            Iterator j = escuelas.iterator();
            while (j.hasNext()) {
                Escuela esc = (Escuela) j.next();
                TreeEntryBean hijoNodo = new TreeEntryBean(esc.getDescripcion(), esc, ViewLogin.picture.getIconEscuela32());
                hijoRoot.getHijos().add(hijoNodo);
            }
            nodoRoot.getHijos().add(hijoRoot);
        }
        arbolContenido.setRoot(nodoRoot);
        arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        arbol.setModel(arbolContenido);
        arbol.setCellRenderer(render);
        arbol.setOpaque(false);
        arbol.setRootVisible(true);
        arbol.setRowHeight(40);
        arbol.setExpandsSelectedPaths(true);
        arbol.setBackground(null);
    }

    private void filtrarTabla(JTable tabla, TableModelDecano modelTablaSol) {
        TableRowSorter<TableModelDecano> sorter = new TableRowSorter(modelTablaSol);
        RowFilter<TableModelDecano, Object> rf1 = null;
        RowFilter<TableModelDecano, Object> rf2 = null;
        RowFilter<TableModelDecano, Object> rf3 = null;
        RowFilter<TableModelDecano, Object> rf4 = null;
        RowFilter<TableModelDecano, Object> rf5 = null;
        List<RowFilter<TableModelDecano, Object>> rfs = new ArrayList(5);
        String facu = "";
        String esc = "";
        try {
            for (int i = 0; i < arbol.getSelectionPath().getPathCount(); i++) {
                if (i == 1) {
                    facu = ((TreeEntryBean) arbol.getSelectionPath().getPathComponent(i)).getTitulo();
                } else if (i == 2) {
                    esc = ((TreeEntryBean) arbol.getSelectionPath().getPathComponent(i)).getTitulo();
                }
            }
            rf1 = RowFilter.regexFilter(buscaApPaterno.getText(), 1);
            rf2 = RowFilter.regexFilter(buscaApMaterno.getText(), 2);
            rf3 = RowFilter.regexFilter(buscaNombres.getText(), 3);
            rf4 = RowFilter.regexFilter(facu, 4);
            rf5 = RowFilter.regexFilter(esc, 5);
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);
            rfs.add(rf4);
            rfs.add(rf5);
            RowFilter<TableModelDecano, Object> af = RowFilter.andFilter(rfs);
            sorter.setRowFilter(af);
            tabla.setRowSorter(sorter);
        } catch (Exception ex) {
            rf1 = RowFilter.regexFilter(buscaApPaterno.getText(), 1);
            rf2 = RowFilter.regexFilter(buscaApMaterno.getText(), 2);
            rf3 = RowFilter.regexFilter(buscaNombres.getText(), 3);
            rf4 = RowFilter.regexFilter(facu, 4);
            rf5 = RowFilter.regexFilter(esc, 5);
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);
            rfs.add(rf4);
            rfs.add(rf5);
            RowFilter<TableModelDecano, Object> af = RowFilter.andFilter(rfs);
            sorter.setRowFilter(af);
            tabla.setRowSorter(sorter);
        }
    }
    TreeSelectionListener treeSeleccionListener = new TreeSelectionListener() {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            filtrarTabla(tablaDecano, modelDecano);
        }
    };
    KeyListener keyfiltrar = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getSource().equals(tablaDecano)) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    int n = JOptionPane.showConfirmDialog(null, "¿Desea en realidad eliminar el registro?", "Information", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        eliminar();
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (buscaApPaterno.equals(e.getSource())) {
                filtrarTabla(tablaDecano, modelDecano);
            } else if (buscaApMaterno.equals(e.getSource())) {
                filtrarTabla(tablaDecano, modelDecano);
            } else if (buscaNombres.equals(e.getSource())) {
                filtrarTabla(tablaDecano, modelDecano);
            }
        }
    };

    public JTree getArbol() {
        return arbol;
    }

    public void setArbol(JTree arbol) {
        this.arbol = arbol;
    }

    public BeanTreeModel getArbolContenido() {
        return arbolContenido;
    }

    public void setArbolContenido(BeanTreeModel arbolContenido) {
        this.arbolContenido = arbolContenido;
    }

    public JScrollPane getArbolScrollPane() {
        return arbolScrollPane;
    }

    public void setArbolScrollPane(JScrollPane arbolScrollPane) {
        this.arbolScrollPane = arbolScrollPane;
    }

    public JButton getBtnEXCEL() {
        return btnEXCEL;
    }

    public void setBtnEXCEL(JButton btnEXCEL) {
        this.btnEXCEL = btnEXCEL;
    }

    public JButton getBtnPDF() {
        return btnPDF;
    }

    public void setBtnPDF(JButton btnPDF) {
        this.btnPDF = btnPDF;
    }

    public JButton getBtnPreview() {
        return btnPreview;
    }

    public void setBtnPreview(JButton btnPreview) {
        this.btnPreview = btnPreview;
    }

    public JButton getBtnPrint() {
        return btnPrint;
    }

    public void setBtnPrint(JButton btnPrint) {
        this.btnPrint = btnPrint;
    }

    public JTextField getBuscaApMaterno() {
        return buscaApMaterno;
    }

    public void setBuscaApMaterno(JTextField buscaApMaterno) {
        this.buscaApMaterno = buscaApMaterno;
    }

    public JTextField getBuscaApPaterno() {
        return buscaApPaterno;
    }

    public void setBuscaApPaterno(JTextField buscaApPaterno) {
        this.buscaApPaterno = buscaApPaterno;
    }

    public JTextField getBuscaNombres() {
        return buscaNombres;
    }

    public void setBuscaNombres(JTextField buscaNombres) {
        this.buscaNombres = buscaNombres;
    }

    public JSplitPane getContenido() {
        return contenido;
    }

    public void setContenido(JSplitPane contenido) {
        this.contenido = contenido;
    }

    public static JScrollPane getContenidoScrollPane() {
        return contenidoScrollPane;
    }

    public static void setContenidoScrollPane(JScrollPane contenidoScrollPane) {
        ViewDecano.contenidoScrollPane = contenidoScrollPane;
    }

    public FormDecano getFrmDecano() {
        return frmDecano;
    }

    public void setFrmDecano(FormDecano frmDecano) {
        this.frmDecano = frmDecano;
    }

    public KeyListener getKeyfiltrar() {
        return keyfiltrar;
    }

    public void setKeyfiltrar(KeyListener keyfiltrar) {
        this.keyfiltrar = keyfiltrar;
    }

    public JLabel getLblApMaterno() {
        return lblApMaterno;
    }

    public void setLblApMaterno(JLabel lblApMaterno) {
        this.lblApMaterno = lblApMaterno;
    }

    public JLabel getLblApPaterno() {
        return lblApPaterno;
    }

    public void setLblApPaterno(JLabel lblApPaterno) {
        this.lblApPaterno = lblApPaterno;
    }

    public JLabel getLblNombres() {
        return lblNombres;
    }

    public void setLblNombres(JLabel lblNombres) {
        this.lblNombres = lblNombres;
    }

    public TableModelDecano getModelDecano() {
        return modelDecano;
    }

    public void setModelDecano(TableModelDecano modelDecano) {
        this.modelDecano = modelDecano;
    }

    public PanelMolde getMolde() {
        return molde;
    }

    public void setMolde(PanelMolde molde) {
        this.molde = molde;
    }

    public JPanel getPanelBusqueda() {
        return panelBusqueda;
    }

    public void setPanelBusqueda(JPanel panelBusqueda) {
        this.panelBusqueda = panelBusqueda;
    }

    public JPanel getPanelData() {
        return panelData;
    }

    public void setPanelData(JPanel panelData) {
        this.panelData = panelData;
    }

    public JPanel getPanelTabla() {
        return panelTabla;
    }

    public void setPanelTabla(JPanel panelTabla) {
        this.panelTabla = panelTabla;
    }

    public BeanTreeRenderer getRender() {
        return render;
    }

    public void setRender(BeanTreeRenderer render) {
        this.render = render;
    }

    public JScrollPane getScrollTabla() {
        return scrollTabla;
    }

    public void setScrollTabla(JScrollPane scrollTabla) {
        this.scrollTabla = scrollTabla;
    }

    public JTable getTablaDecano() {
        return tablaDecano;
    }

    public void setTablaDecano(JTable tablaDecano) {
        this.tablaDecano = tablaDecano;
    }

    public JToolBar getTool() {
        return tool;
    }

    public void setTool(JToolBar tool) {
        this.tool = tool;
    }

    public TreeSelectionListener getTreeSeleccionListener() {
        return treeSeleccionListener;
    }

    public void setTreeSeleccionListener(TreeSelectionListener treeSeleccionListener) {
        this.treeSeleccionListener = treeSeleccionListener;
    }
}
