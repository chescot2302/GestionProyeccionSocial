/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Escuela;
import com.gps.bean.Facultad;
import com.gps.bean.Universidad;
import com.gps.logic.LogicEscuela;
import com.gps.logic.LogicEstudiante;
import com.gps.logic.LogicFacultad;
import com.gps.logic.LogicUniversidad;
import com.gps.tablemodel.TableModelEscuela;
import com.gps.tablemodel.TableModelEstudiante;
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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.TableRowSorter;
import javax.swing.tree.TreeSelectionModel;
import com.gps.tablemodel.TableModelProyecto;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import com.gps.bean.Estudiante;
import com.gps.logic.LogicProyecto;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class ViewEstudiante {
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
    private TableModelEstudiante modelEstudiante=new TableModelEstudiante(LogicEstudiante.listarEstudiante());
    private JTable tablaEstudiante=new JTable();
    private JButton btnPreview=new JButton(ViewLogin.picture.getIconPreview());
    private JButton btnPrint=new JButton(ViewLogin.picture.getIconPrint());
    private JButton btnPDF=new JButton(ViewLogin.picture.getIconPdf());
    private JButton btnEXCEL=new JButton(ViewLogin.picture.getIconExcel());
    private JToolBar tool=new JToolBar();
    private FormEstudiante frmEstudiante=new FormEstudiante(tablaEstudiante,modelEstudiante);
    private JLabel lblApPaterno=new JLabel("Ap. Paterno");
    private JLabel lblApMaterno=new JLabel("Ap. Materno");
    private JLabel lblNombres=new JLabel("Nombres");
    private JTextField buscaApPaterno=new JTextField();
    private JTextField buscaApMaterno=new JTextField();
    private JTextField buscaNombres=new JTextField();  
    private TableModelProyecto modelProyecto = new TableModelProyecto();
    private JTable tablaProyecto = new JTable();
    private JScrollPane scrollTablaProy = new JScrollPane();
    private JToolBar toolEst=new JToolBar();
    private JLabel lblEst=new JLabel("Estudiante: ");
    private JLabel lblInformacion=new JLabel();
    public ViewEstudiante(){
        initComponents();
    }
    
    private void initComponents(){ 
        Dimension d=new Dimension(444, 252);
        Border etchedBdr = BorderFactory.createEtchedBorder();        
        panelDataProy.setLayout(new BorderLayout());
        panelDataProy.add(panelTablaProy,BorderLayout.CENTER);
        panelDataProy.add(panelBusquedaProy,BorderLayout.NORTH);
        panelDataProy.setPreferredSize(d);
        panelTablaProy.setBorder(etchedBdr);
        panelBusquedaProy.setBorder(etchedBdr);        
        panelTablaProy.setLayout(new BorderLayout());
        panelBusquedaProy.setLayout(new BorderLayout());
         panelTablaProy.add(scrollTablaProy);
        tablaProyecto.setModel(modelProyecto);
        scrollTablaProy.setViewportView(tablaProyecto);        
        btnPrint.setVisible(false);
        btnPDF.setVisible(false);
        btnEXCEL.setVisible(false);
        panelData.setLayout(new BorderLayout());
        panelData.add(panelTabla,BorderLayout.CENTER);
        panelData.add(panelBusqueda,BorderLayout.NORTH);
        panelTabla.setLayout(new BorderLayout());
        panelBusqueda.setLayout(new BorderLayout());
        toolEst.add(lblEst);
        toolEst.add(lblInformacion);
        panelBusquedaProy.add(toolEst);        
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
        scrollTabla.setViewportView(tablaEstudiante);
        tablaEstudiante.setAutoCreateRowSorter(true);
        arbolScrollPane.setViewportView(arbol);
        contenidoScrollPane.getViewport().setLayout(new BorderLayout());
        contenidoScrollPane.setViewportView(panelData);
        contenido.setContinuousLayout(true);
        contenido.setOneTouchExpandable(true);
        contenido.setDividerLocation(250);
        contenido.setPreferredSize(new Dimension(400, 200));
        molde.getPanelfrm().add(frmEstudiante);
        molde.getPanelarbol().setLayout(new BorderLayout());                
        molde.getPanelarbol().setVisible(false);
        molde.getPaneltabla().setLayout(new BorderLayout());
        molde.getPaneltabla().add(contenido);
        molde.getPanelinf().setLayout(new BorderLayout());
        molde.getPanelinf().add(panelDataProy);       
        construirArbol();        
        arbol.addTreeSelectionListener(treeSeleccionListener);
        buscaApPaterno.addKeyListener(keyfiltrar);
        buscaApMaterno.addKeyListener(keyfiltrar);
        buscaNombres.addKeyListener(keyfiltrar);
        frmEstudiante.getBtnAddEscuela().addActionListener(actionListener);
        frmEstudiante.getBtnAddFacultad().addActionListener(actionListener); 
        btnPreview.addActionListener(actionListener);
    }
    
    
    
    private void verProyectoEstudiante(){
        int fila = tablaEstudiante.getSelectedRow();        
        if (fila > -1) {
            int modelrow = tablaEstudiante.convertRowIndexToModel(fila);
            Estudiante obj = ((TableModelEstudiante) tablaEstudiante.getModel()).getValue(modelrow);  
            modelProyecto.setData(LogicProyecto.listarProyectoEstudiante(obj.getId()));            
            modelProyecto.fireTableDataChanged();
            tablaProyecto.setModel(modelProyecto);
            lblInformacion.setText(obj.getAppaterno()+" "+obj.getApmaterno()+", "+obj.getNombres());
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la lista de estudiantes", "Aviso", JOptionPane.INFORMATION_MESSAGE);
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
    
    private void filtrarTabla(JTable tabla, TableModelEstudiante modelTablaSol) {
        TableRowSorter<TableModelEstudiante> sorter = new TableRowSorter(modelTablaSol);
        RowFilter<TableModelEstudiante, Object> rf1 = null;
        RowFilter<TableModelEstudiante, Object> rf2 = null;
        RowFilter<TableModelEstudiante, Object> rf3 = null;
        RowFilter<TableModelEstudiante, Object> rf4 = null;
        RowFilter<TableModelEstudiante, Object> rf5 = null;        
        List<RowFilter<TableModelEstudiante, Object>> rfs = new ArrayList(5);
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
            rf4 = RowFilter.regexFilter(facu, 5);
            rf5 = RowFilter.regexFilter(esc, 6);            
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);
            rfs.add(rf4);            
            rfs.add(rf5);                        
            RowFilter<TableModelEstudiante, Object> af = RowFilter.andFilter(rfs);
            sorter.setRowFilter(af);
            tabla.setRowSorter(sorter);
        } catch (Exception ex) {
            rf1 = RowFilter.regexFilter(buscaApPaterno.getText(), 1);            
            rf2 = RowFilter.regexFilter(buscaApMaterno.getText(), 2);            
            rf3 = RowFilter.regexFilter(buscaNombres.getText(), 3);            
            rf4 = RowFilter.regexFilter(facu, 5);
            rf5 = RowFilter.regexFilter(esc, 6);            
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);
            rfs.add(rf4);            
            rfs.add(rf5);                        
            RowFilter<TableModelEstudiante, Object> af = RowFilter.andFilter(rfs);
            sorter.setRowFilter(af);
            tabla.setRowSorter(sorter);
        }
    }
    
    private void viewPanelEscuela(){
        try {
            frmEstudiante.getPanelEscuela().setVisible(true);
        } catch (Exception ex) {
            frmEstudiante.setPanelEscuela(new ViewEscuela(this));
            frmEstudiante.getPanelEscuela().setTitle("Mantenimiento de Escuelas Profesionales");
            frmEstudiante.getPanelEscuela().pack();
            frmEstudiante.getPanelEscuela().setLocationRelativeTo(null);            
            frmEstudiante.getPanelEscuela().setVisible(true);
        }
    }
        
    
    private void viewPanelFacultad(){
        try {
            frmEstudiante.getPanelFacultad().setVisible(true);
        } catch (Exception ex) {
            frmEstudiante.setPanelFacultad(new ViewFacultad(this));
            frmEstudiante.getPanelFacultad().setTitle("Mantenimiento de Facultades");
            //frmEstudiante.getPanelFacultad().pack();
            frmEstudiante.getPanelFacultad().setSize(700, 500);
            frmEstudiante.getPanelFacultad().setLocationRelativeTo(null);            
            frmEstudiante.getPanelFacultad().setVisible(true);
        }
    }
    
    TreeSelectionListener treeSeleccionListener=new TreeSelectionListener(){

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            filtrarTabla(tablaEstudiante,modelEstudiante);
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
                filtrarTabla(tablaEstudiante,modelEstudiante);
            }else if (buscaApMaterno.equals(e.getSource())) {
                filtrarTabla(tablaEstudiante,modelEstudiante);
            }else if (buscaNombres.equals(e.getSource())) {
                filtrarTabla(tablaEstudiante,modelEstudiante);
            } 
        }
    };
    
    ActionListener actionListener=new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            if(frmEstudiante.getBtnAddEscuela().equals(e.getSource())){
                viewPanelEscuela();
            }else if(frmEstudiante.getBtnAddFacultad().equals(e.getSource())){
                viewPanelFacultad();
            }else if(e.getSource().equals(btnPreview)){
                verProyectoEstudiante();
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
        ViewEstudiante.contenidoScrollPane = contenidoScrollPane;
    }

    public FormEstudiante getFrmEstudiante() {
        return frmEstudiante;
    }

    public void setFrmEstudiante(FormEstudiante frmEstudiante) {
        this.frmEstudiante = frmEstudiante;
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

    public TableModelEstudiante getModelEstudiante() {
        return modelEstudiante;
    }

    public void setModelEstudiante(TableModelEstudiante modelEstudiante) {
        this.modelEstudiante = modelEstudiante;
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

    public JTable getTablaEstudiante() {
        return tablaEstudiante;
    }

    public void setTablaEstudiante(JTable tablaEstudiante) {
        this.tablaEstudiante = tablaEstudiante;
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
