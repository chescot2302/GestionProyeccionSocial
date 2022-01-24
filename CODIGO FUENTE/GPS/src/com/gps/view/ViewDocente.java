/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Escuela;
import com.gps.bean.Facultad;
import com.gps.bean.Universidad;
import com.gps.logic.LogicDocente;
import com.gps.logic.LogicEscuela;
import com.gps.logic.LogicFacultad;
import com.gps.logic.LogicUniversidad;
import com.gps.tablemodel.TableModelDocente;
import com.gps.util.BeanTreeModel;
import com.gps.util.BeanTreeRenderer;
import com.gps.util.TreeEntryBean;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.TreeSelectionModel;
import com.gps.bean.Docente;
import com.gps.logic.LogicProyecto;
import com.gps.tablemodel.TableModelProyecto;
import javax.swing.JOptionPane;
/**
 *
 * @author Administrador
 */
public class ViewDocente {
    private JPanel panelDataProy = new JPanel();
    private JPanel panelTablaProy = new JPanel();
    private JPanel panelBusquedaProy = new JPanel();    
    private PanelMolde molde=new PanelMolde();    
    private BeanTreeModel arbolContenido = new BeanTreeModel();
    private BeanTreeRenderer render = new BeanTreeRenderer();
    private JTree arbol = new JTree();
    private JScrollPane arbolScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);    
    public static JScrollPane contenidoScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JSplitPane contenido = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, arbolScrollPane, contenidoScrollPane);    
    private JPanel panelData=new JPanel();
    private JPanel panelTabla=new JPanel();
    private JPanel panelBusqueda=new JPanel();
    private JScrollPane scrollTabla=new JScrollPane();
    private TableModelDocente modelDocente=new TableModelDocente(LogicDocente.listarDocente(BigDecimal.ZERO));
    private JTable tablaDocente=new JTable();
    private JButton btnPreview=new JButton(ViewLogin.picture.getIconPreview());
    private JButton btnPrint=new JButton(ViewLogin.picture.getIconPrint());
    private JButton btnPDF=new JButton(ViewLogin.picture.getIconPdf());
    private JButton btnEXCEL=new JButton(ViewLogin.picture.getIconExcel());
    private JToolBar tool=new JToolBar();
    private FormDocente frmDocente=new FormDocente(tablaDocente,modelDocente);
    private JLabel lblApPaterno=new JLabel("Ap. Paterno");
    private JLabel lblApMaterno=new JLabel("Ap. Materno");
    private JLabel lblNombres=new JLabel("Nombres");
    private JTextField buscaApPaterno=new JTextField();
    private JTextField buscaApMaterno=new JTextField();
    private JTextField buscaNombres=new JTextField();    
    private TableModelProyecto modelProyecto = new TableModelProyecto();
    private JTable tablaProyecto = new JTable();
    private JScrollPane scrollTablaProy = new JScrollPane();
    private JToolBar toolDoc=new JToolBar();
    private JLabel lblDoc=new JLabel("Docente: ");
    private JLabel lblInformacion=new JLabel();
    public ViewDocente(){
        initComponents();
    }
    
    private void initComponents(){ 
        btnPrint.setVisible(false);
        btnPDF.setVisible(false);
        btnEXCEL.setVisible(false);
        panelData.setLayout(new BorderLayout());
        panelData.add(panelTabla,BorderLayout.CENTER);
        panelData.add(panelBusqueda,BorderLayout.NORTH);
        panelTabla.setLayout(new BorderLayout());
        panelBusqueda.setLayout(new BorderLayout());
        molde.getPanelinf().setLayout(new BorderLayout());
        molde.getPanelinf().add(panelDataProy);       
        Border etchedBdr = BorderFactory.createEtchedBorder();        
        panelDataProy.setLayout(new BorderLayout());
        panelDataProy.add(panelTablaProy,BorderLayout.CENTER);
        panelDataProy.add(panelBusquedaProy,BorderLayout.NORTH);
        Dimension d=new Dimension(428, 247);
        panelDataProy.setPreferredSize(d);
        panelTablaProy.setBorder(etchedBdr);
        panelBusquedaProy.setBorder(etchedBdr);                
        panelTablaProy.setLayout(new BorderLayout());
        panelBusquedaProy.setLayout(new BorderLayout());
        panelBusquedaProy.add(toolDoc);
        toolDoc.add(lblDoc);
        toolDoc.add(lblInformacion);
         panelTablaProy.add(scrollTablaProy);
        tablaProyecto.setModel(modelProyecto);
        scrollTablaProy.setViewportView(tablaProyecto);        
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
        scrollTabla.setViewportView(tablaDocente);
        tablaDocente.setAutoCreateRowSorter(true);
        arbolScrollPane.setViewportView(arbol);
        contenidoScrollPane.getViewport().setLayout(new BorderLayout());
        contenidoScrollPane.setViewportView(panelData);
        contenido.setContinuousLayout(true);
        contenido.setOneTouchExpandable(true);
        contenido.setDividerLocation(250);
        contenido.setPreferredSize(new Dimension(400, 200));
        molde.getPanelfrm().add(frmDocente);        
        molde.getPanelarbol().setLayout(new BorderLayout());                
        molde.getPanelarbol().setVisible(false);
        molde.getPaneltabla().setLayout(new BorderLayout());
        molde.getPaneltabla().add(contenido);
        construirArbol();        
        arbol.addTreeSelectionListener(treeSeleccionListener);
        buscaApPaterno.addKeyListener(keyfiltrar);
        buscaApMaterno.addKeyListener(keyfiltrar);
        buscaNombres.addKeyListener(keyfiltrar);
        frmDocente.getBtnAddEscuela().addActionListener(actionListener);
        frmDocente.getBtnAddFacultad().addActionListener(actionListener);
        frmDocente.getBtnAddGrado().addActionListener(actionListener);
        btnPreview.addActionListener(actionListener);        
    }
    
    private void verProyectoDocente(){
        int fila = tablaDocente.getSelectedRow();        
        if (fila > -1) {
            int modelrow = tablaDocente.convertRowIndexToModel(fila);
            Docente obj = ((TableModelDocente) tablaDocente.getModel()).getValue(modelrow);  
            modelProyecto.setData(LogicProyecto.listarProyecto(obj.getId()));            
            modelProyecto.fireTableDataChanged();
            tablaProyecto.setModel(modelProyecto);
            lblInformacion.setText(obj.getAppaterno()+" "+obj.getApmaterno()+", "+obj.getNombres());
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la lista de docentes", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
            
    public void construirArbol(){
        Universidad uni=LogicUniversidad.beanUniversidad(BigDecimal.ONE);
        TreeEntryBean nodoRoot=new TreeEntryBean(uni.getSiglas(),uni,ViewLogin.picture.getIconUniversidad32());        
        ArrayList<Facultad> facultades=LogicFacultad.listarFacultad(uni.getId());
        Iterator i=facultades.iterator();
        while(i.hasNext()){
            Facultad fac=(Facultad)i.next();
            TreeEntryBean hijoRoot=new TreeEntryBean(fac.getSiglas(),fac,ViewLogin.picture.getIconFacultas32());                    
            ArrayList<Escuela> escuelas=LogicEscuela.listarEscuela(fac.getId());
            Iterator j=escuelas.iterator();
            while(j.hasNext()){
                Escuela esc=(Escuela)j.next();
                TreeEntryBean hijoNodo=new TreeEntryBean(esc.getDescripcion(),esc,ViewLogin.picture.getIconEscuela32());        
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
    
    private void filtrarTabla(JTable tabla, TableModelDocente modelTablaSol) {
        TableRowSorter<TableModelDocente> sorter = new TableRowSorter(modelTablaSol);
        RowFilter<TableModelDocente, Object> rf1 = null;
        RowFilter<TableModelDocente, Object> rf2 = null;
        RowFilter<TableModelDocente, Object> rf3 = null;
        RowFilter<TableModelDocente, Object> rf4 = null;
        RowFilter<TableModelDocente, Object> rf5 = null;        
        List<RowFilter<TableModelDocente, Object>> rfs = new ArrayList(5);
        String facu="";
        String esc="";
        try {            
            for(int i=0;i<arbol.getSelectionPath().getPathCount();i++){
                if(i==1){
                 facu=((TreeEntryBean)arbol.getSelectionPath().getPathComponent(i)).getTitulo();               
                }else if(i==2){
                 esc=((TreeEntryBean)arbol.getSelectionPath().getPathComponent(i)).getTitulo();               
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
            RowFilter<TableModelDocente, Object> af = RowFilter.andFilter(rfs);
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
            RowFilter<TableModelDocente, Object> af = RowFilter.andFilter(rfs);
            sorter.setRowFilter(af);
            tabla.setRowSorter(sorter);
        }
    }
    
    private void viewPanelEscuela(){
        try {
            frmDocente.getPanelEscuela().setVisible(true);
        } catch (Exception ex) {
            frmDocente.setPanelEscuela(new ViewEscuela(this));
            frmDocente.getPanelEscuela().setTitle("Mantenimiento de Escuelas Profesionales");
            frmDocente.getPanelEscuela().pack();
            frmDocente.getPanelEscuela().setLocationRelativeTo(null);            
            frmDocente.getPanelEscuela().setVisible(true);
        }
    }
    
    private void viewPanelGrado(){
        try {
            frmDocente.getPanelGrado().setVisible(true);
        } catch (Exception ex) {
            frmDocente.setPanelGrado(new ViewGrado(this));
            frmDocente.getPanelGrado().setTitle("Mantenimiento de Grados Academicos");
            frmDocente.getPanelGrado().pack();            
            frmDocente.getPanelGrado().setLocationRelativeTo(null);            
            frmDocente.getPanelGrado().setVisible(true);
        }
    }
    
    private void viewPanelFacultad(){
        try {
            frmDocente.getPanelFacultad().setVisible(true);
        } catch (Exception ex) {
            frmDocente.setPanelFacultad(new ViewFacultad(this));
            frmDocente.getPanelFacultad().setTitle("Mantenimiento de Facultades");
            //frmDocente.getPanelFacultad().pack();
            frmDocente.getPanelFacultad().setSize(700, 500);
            frmDocente.getPanelFacultad().setLocationRelativeTo(null);            
            frmDocente.getPanelFacultad().setVisible(true);
        }
    }
    
    TreeSelectionListener treeSeleccionListener=new TreeSelectionListener(){

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            filtrarTabla(tablaDocente,modelDocente);
        }
        
    };
    
    KeyListener keyfiltrar = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (buscaApPaterno.equals(e.getSource())) {
                filtrarTabla(tablaDocente,modelDocente);
            }else if (buscaApMaterno.equals(e.getSource())) {
                filtrarTabla(tablaDocente,modelDocente);
            }else if (buscaNombres.equals(e.getSource())) {
                filtrarTabla(tablaDocente,modelDocente);
            } 
        }
    };
    
    ActionListener actionListener=new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            if(frmDocente.getBtnAddEscuela().equals(e.getSource())){
                viewPanelEscuela();
            }else if(frmDocente.getBtnAddFacultad().equals(e.getSource())){
                viewPanelFacultad();
            }else if(frmDocente.getBtnAddGrado().equals(e.getSource())){
                viewPanelGrado();
            }else if(btnPreview.equals(e.getSource())){
                verProyectoDocente();
            }
        }
        
    };
              
    public PanelMolde getMolde() {
        return molde;
    }

    public JTree getArbol() {
        return arbol;
    }

    public void setArbol(JTree arbol) {
        this.arbol = arbol;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
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
        ViewDocente.contenidoScrollPane = contenidoScrollPane;
    }

    public FormDocente getFrmDocente() {
        return frmDocente;
    }

    public void setFrmDocente(FormDocente frmDocente) {
        this.frmDocente = frmDocente;
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

    public TableModelDocente getModelDocente() {
        return modelDocente;
    }

    public void setModelDocente(TableModelDocente modelDocente) {
        this.modelDocente = modelDocente;
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

    public JTable getTablaDocente() {
        return tablaDocente;
    }

    public void setTablaDocente(JTable tablaDocente) {
        this.tablaDocente = tablaDocente;
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
