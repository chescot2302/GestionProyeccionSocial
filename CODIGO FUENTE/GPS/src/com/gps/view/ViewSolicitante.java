/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Ubigeo;
import com.gps.comboboxmodel.CbModelDepartamento;
import com.gps.comboboxmodel.CbModelDistrito;
import com.gps.comboboxmodel.CbModelProvincia;
import com.gps.logic.LogicSolicitante;
import com.gps.logic.LogicUbigeo;
import com.gps.tablemodel.TableModelSolicitante;
import com.gps.util.JComboBoxAutocomplete;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.border.Border;
import javax.swing.table.TableRowSorter;
import com.gps.tablemodel.TableModelProyecto;
import com.gps.bean.Solicitante;
import com.gps.logic.LogicProyecto;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
/**
 *
 * @author Administrador
 */
public class ViewSolicitante {
    private JPanel panelDataProy = new JPanel();
    private JPanel panelTablaProy = new JPanel();
    private JPanel panelBusquedaProy = new JPanel();    
    private PanelMolde molde=new PanelMolde();    
    private JScrollPane scrollTableSol=new JScrollPane();
    private JTable tabDataSol=new JTable();
    private JScrollPane scrollTableInf=new JScrollPane();
    private JTable tabDataInf=new JTable();
    private JComboBoxAutocomplete cbDepartamento=new JComboBoxAutocomplete();
    private JComboBoxAutocomplete cbProvincia=new JComboBoxAutocomplete();
    private JComboBoxAutocomplete cbDistrito=new JComboBoxAutocomplete();
    private JLabel lDepartamento = new JLabel("Departamento");
    private JLabel lProvincia = new JLabel("Provincia");
    private JLabel lDistrito = new JLabel("Distrito");
    private CbModelDepartamento cbmoddep=new CbModelDepartamento(LogicUbigeo.ListarDepartamentos());
    private CbModelProvincia cbmodprov=new CbModelProvincia();
    private CbModelDistrito cbmoddist=new CbModelDistrito();    
    private JPanel panelSol=new JPanel();
    private JPanel panelBusq=new JPanel();
    private JLabel lblBuscarSol=new JLabel("Razón Social: ");
    private JLabel lblBuscarRuc=new JLabel("Ruc: ");
    private JLabel lblBuscarDir=new JLabel("Dirección: ");
    private JTextField txtBuscarSol=new JTextField();
    private JTextField txtBuscarRuc=new JTextField();
    private JTextField txtBuscarDir=new JTextField();    
    private JButton btnPreview=new JButton(ViewLogin.picture.getIconPreview());
    private JButton btnPrint=new JButton(ViewLogin.picture.getIconPrint());
    private JButton btnPDF=new JButton(ViewLogin.picture.getIconPdf());
    private JButton btnEXCEL=new JButton(ViewLogin.picture.getIconExcel());
    private JToolBar tool=new JToolBar();
    private TableModelSolicitante modelTablaSol=new TableModelSolicitante();   
    private FormSolicitante frmSolicitante=new FormSolicitante(tabDataSol,modelTablaSol);
    private TableModelProyecto modelProyecto = new TableModelProyecto();
    private JTable tablaProyecto = new JTable();
    private JScrollPane scrollTablaProy = new JScrollPane();
    private JLabel lblSol=new JLabel("Solicitante: ");
    private JLabel lblInformacion=new JLabel();
    private JToolBar toolSol=new JToolBar();
    public ViewSolicitante(){
        initComponents();
    }
    
    private void initComponents(){
        toolSol.add(lblSol);
        toolSol.add(lblInformacion);
        Border etchedBdr = BorderFactory.createEtchedBorder();
        Dimension di=new Dimension(340, 245);
        panelDataProy.setLayout(new BorderLayout());
        panelDataProy.add(panelTablaProy,BorderLayout.CENTER);
        panelDataProy.add(panelBusquedaProy,BorderLayout.NORTH);
        panelDataProy.setPreferredSize(di);
        panelTablaProy.setBorder(etchedBdr);
        panelBusquedaProy.setBorder(etchedBdr);        
        panelTablaProy.setLayout(new BorderLayout());
        panelBusquedaProy.setLayout(new BorderLayout());
        panelBusquedaProy.add(toolSol);
         panelTablaProy.add(scrollTablaProy);
        tablaProyecto.setModel(modelProyecto);
        scrollTablaProy.setViewportView(tablaProyecto);        
        btnPrint.setVisible(false);
        btnPDF.setVisible(false);
        btnEXCEL.setVisible(false);        
        molde.getPanelfrm().add(frmSolicitante);
        GridBagLayout gbl=new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        molde.getPanelbarra().setLayout(gbl);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        molde.getPanelbarra().add(lDepartamento, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        molde.getPanelbarra().add(cbDepartamento, gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        molde.getPanelbarra().add(lProvincia, gbc);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        molde.getPanelbarra().add(cbProvincia, gbc);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        molde.getPanelbarra().add(lDistrito, gbc);        
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        molde.getPanelbarra().add(cbDistrito, gbc);
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        molde.getPanelarbol().setVisible(false);   
        molde.getPaneltabla().setLayout(new BorderLayout());
        molde.getPaneltabla().add(panelSol,BorderLayout.CENTER);
        molde.getPaneltabla().add(panelBusq,BorderLayout.NORTH);
        panelSol.setLayout(new BorderLayout());
        panelSol.add(scrollTableSol);
        scrollTableSol.setViewportView(tabDataSol);
        modelTablaSol.setData(LogicSolicitante.listarSolicitante());
        tabDataSol.setModel(modelTablaSol);
        tabDataSol.setAutoCreateRowSorter(true);
        molde.getPanelinf().setLayout(new BorderLayout());
        molde.getPanelinf().add(panelDataProy);       
        Dimension d=new Dimension(200,20);
        cbDepartamento.setPreferredSize(d);
        cbProvincia.setPreferredSize(d);
        cbDistrito.setPreferredSize(d);
        cbDepartamento.setModel(cbmoddep); 
        cbDepartamento.addActionListener(actionListener);        
        cbProvincia.addActionListener(actionListener);
        cbDistrito.addActionListener(actionListener);
        cbProvincia.setEnabled(false);
        cbDistrito.setEnabled(false);
        panelBusq.setBorder(etchedBdr);
        panelSol.setBorder(etchedBdr);
        panelBusq.setLayout(new BorderLayout());
        panelBusq.add(tool,BorderLayout.CENTER);
        btnPreview.setToolTipText("Vista previa");
        btnPrint.setToolTipText("Imprimir vista de Tabla");
        btnEXCEL.setToolTipText("Exportar a EXCEL");
        btnPDF.setToolTipText("Exportar a PDF");
        tool.add(btnPrint);
        tool.add(btnPreview);
        tool.add(btnPDF);
        tool.add(btnEXCEL);
        tool.addSeparator();
        tool.add(lblBuscarRuc);
        tool.add(txtBuscarRuc);
        tool.addSeparator();
        tool.add(lblBuscarSol);
        tool.add(txtBuscarSol);
        tool.addSeparator();
        tool.add(lblBuscarDir);
        tool.add(txtBuscarDir);        
        txtBuscarSol.addKeyListener(keyfiltrar);
        txtBuscarRuc.addKeyListener(keyfiltrar);
        txtBuscarDir.addKeyListener(keyfiltrar);     
        btnPreview.addActionListener(actionListener);
    }
    
    private void verProyectoSolicitante(){
        int fila = tabDataSol.getSelectedRow();        
        if (fila > -1) {
            int modelrow = tabDataSol.convertRowIndexToModel(fila);
            Solicitante obj = ((TableModelSolicitante) tabDataSol.getModel()).getValue(modelrow);  
            modelProyecto.setData(LogicProyecto.listarProyectoSolicitante(obj.getId()));            
            modelProyecto.fireTableDataChanged();
            tablaProyecto.setModel(modelProyecto);
            lblInformacion.setText(obj.getRuc()+" : "+obj.getDescripcion());
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la lista de solicitantes", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void filtrarTabla(JTable tabla, TableModelSolicitante modelTablaSol) {
        TableRowSorter<TableModelSolicitante> sorter = new TableRowSorter(modelTablaSol);
        RowFilter<TableModelSolicitante, Object> rf1 = null;
        RowFilter<TableModelSolicitante, Object> rf2 = null;
        RowFilter<TableModelSolicitante, Object> rf3 = null;
        RowFilter<TableModelSolicitante, Object> rf4 = null;
        RowFilter<TableModelSolicitante, Object> rf5 = null;
        RowFilter<TableModelSolicitante, Object> rf6 = null;        
        List<RowFilter<TableModelSolicitante, Object>> rfs = new ArrayList(6);
        try {
            String dep=String.valueOf(cbDepartamento.getEditor().getItem());            
            String prov=String.valueOf(cbProvincia.getEditor().getItem());            
            String dist=String.valueOf(cbDistrito.getEditor().getItem());            
            rf1 = RowFilter.regexFilter(txtBuscarRuc.getText(), 1);            
            rf2 = RowFilter.regexFilter(txtBuscarSol.getText(), 2);            
            rf3 = RowFilter.regexFilter(txtBuscarDir.getText(), 3);            
            rf4 = RowFilter.regexFilter(dep, 4);
            rf5 = RowFilter.regexFilter(prov, 5);
            rf6 = RowFilter.regexFilter(dist, 6);           
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);
            rfs.add(rf4);            
            rfs.add(rf5);            
            rfs.add(rf6);            
            RowFilter<TableModelSolicitante, Object> af = RowFilter.andFilter(rfs);
            sorter.setRowFilter(af);
            tabla.setRowSorter(sorter);
        } catch (Exception ex) {
            return;
        }
    }        
    
    KeyListener keyfiltrar = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (txtBuscarSol == e.getSource()) {
                filtrarTabla(tabDataSol,modelTablaSol);
            }else if (txtBuscarDir == e.getSource()) {
                filtrarTabla(tabDataSol,modelTablaSol);
            }else if (txtBuscarRuc == e.getSource()) {
                filtrarTabla(tabDataSol,modelTablaSol);
            } 
        }
    };
                    
    ActionListener actionListener=new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(cbDepartamento)){                  
                cbDepartamento.getEditor().setItem(cbDepartamento.getSelectedItem());
                cbmodprov.setData(LogicUbigeo.ListarProvincias(cbDepartamento.getSelectedItem().toString()));
                cbProvincia.setModel(cbmodprov);        
                cbProvincia.updateUI();    
                cbProvincia.getEditor().setItem(null);
                cbmoddist.setData(new ArrayList<Ubigeo>());
                cbDistrito.setModel(cbmoddist);
                cbDistrito.updateUI();    
                cbDistrito.getEditor().setItem(null);
                cbProvincia.setEnabled(true);
                cbDistrito.setEnabled(false);          
                filtrarTabla(tabDataSol,modelTablaSol);
            }else if(e.getSource().equals(cbProvincia)){                
                cbProvincia.getEditor().setItem(cbProvincia.getSelectedItem());
                cbmoddist.setData(LogicUbigeo.ListarDistritos(cbDepartamento.getSelectedItem().toString(),cbProvincia.getSelectedItem().toString()));
                cbDistrito.setModel(cbmoddist);        
                cbDistrito.updateUI();    
                cbDistrito.getEditor().setItem(null);                
                cbDistrito.setEnabled(true);           
                filtrarTabla(tabDataSol,modelTablaSol);
            }else if(e.getSource().equals(cbDistrito)){                
                cbDistrito.getEditor().setItem(cbDistrito.getSelectedItem());                                
                filtrarTabla(tabDataSol,modelTablaSol);
            }else if(btnPreview.equals(e.getSource())){
                verProyectoSolicitante();
            }
        }
        
    };
    
    
                        
    public PanelMolde getMolde() {
        return molde;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
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

    public JComboBoxAutocomplete getCbDepartamento() {
        return cbDepartamento;
    }

    public void setCbDepartamento(JComboBoxAutocomplete cbDepartamento) {
        this.cbDepartamento = cbDepartamento;
    }

    public JComboBoxAutocomplete getCbDistrito() {
        return cbDistrito;
    }

    public void setCbDistrito(JComboBoxAutocomplete cbDistrito) {
        this.cbDistrito = cbDistrito;
    }

    public JComboBoxAutocomplete getCbProvincia() {
        return cbProvincia;
    }

    public void setCbProvincia(JComboBoxAutocomplete cbProvincia) {
        this.cbProvincia = cbProvincia;
    }

    public CbModelDepartamento getCbmoddep() {
        return cbmoddep;
    }

    public void setCbmoddep(CbModelDepartamento cbmoddep) {
        this.cbmoddep = cbmoddep;
    }

    public CbModelDistrito getCbmoddist() {
        return cbmoddist;
    }

    public void setCbmoddist(CbModelDistrito cbmoddist) {
        this.cbmoddist = cbmoddist;
    }

    public CbModelProvincia getCbmodprov() {
        return cbmodprov;
    }

    public void setCbmodprov(CbModelProvincia cbmodprov) {
        this.cbmodprov = cbmodprov;
    }

    public FormSolicitante getFrmSolicitante() {
        return frmSolicitante;
    }

    public void setFrmSolicitante(FormSolicitante frmSolicitante) {
        this.frmSolicitante = frmSolicitante;
    }

    public KeyListener getKeyfiltrar() {
        return keyfiltrar;
    }

    public void setKeyfiltrar(KeyListener keyfiltrar) {
        this.keyfiltrar = keyfiltrar;
    }

    public JLabel getlDepartamento() {
        return lDepartamento;
    }

    public void setlDepartamento(JLabel lDepartamento) {
        this.lDepartamento = lDepartamento;
    }

    public JLabel getlDistrito() {
        return lDistrito;
    }

    public void setlDistrito(JLabel lDistrito) {
        this.lDistrito = lDistrito;
    }

    public JLabel getlProvincia() {
        return lProvincia;
    }

    public void setlProvincia(JLabel lProvincia) {
        this.lProvincia = lProvincia;
    }

    public JLabel getLblBuscarDir() {
        return lblBuscarDir;
    }

    public void setLblBuscarDir(JLabel lblBuscarDir) {
        this.lblBuscarDir = lblBuscarDir;
    }

    public JLabel getLblBuscarRuc() {
        return lblBuscarRuc;
    }

    public void setLblBuscarRuc(JLabel lblBuscarRuc) {
        this.lblBuscarRuc = lblBuscarRuc;
    }

    public JLabel getLblBuscarSol() {
        return lblBuscarSol;
    }

    public void setLblBuscarSol(JLabel lblBuscarSol) {
        this.lblBuscarSol = lblBuscarSol;
    }

    public TableModelSolicitante getModelTablaSol() {
        return modelTablaSol;
    }

    public void setModelTablaSol(TableModelSolicitante modelTablaSol) {
        this.modelTablaSol = modelTablaSol;
    }

    public JPanel getPanelBusq() {
        return panelBusq;
    }

    public void setPanelBusq(JPanel panelBusq) {
        this.panelBusq = panelBusq;
    }

    public JPanel getPanelSol() {
        return panelSol;
    }

    public void setPanelSol(JPanel panelSol) {
        this.panelSol = panelSol;
    }

    public JScrollPane getScrollTableInf() {
        return scrollTableInf;
    }

    public void setScrollTableInf(JScrollPane scrollTableInf) {
        this.scrollTableInf = scrollTableInf;
    }

    public JScrollPane getScrollTableSol() {
        return scrollTableSol;
    }

    public void setScrollTableSol(JScrollPane scrollTableSol) {
        this.scrollTableSol = scrollTableSol;
    }

    public JTable getTabDataInf() {
        return tabDataInf;
    }

    public void setTabDataInf(JTable tabDataInf) {
        this.tabDataInf = tabDataInf;
    }

    public JTable getTabDataSol() {
        return tabDataSol;
    }

    public void setTabDataSol(JTable tabDataSol) {
        this.tabDataSol = tabDataSol;
    }

    public JToolBar getTool() {
        return tool;
    }

    public void setTool(JToolBar tool) {
        this.tool = tool;
    }

    public JTextField getTxtBuscarDir() {
        return txtBuscarDir;
    }

    public void setTxtBuscarDir(JTextField txtBuscarDir) {
        this.txtBuscarDir = txtBuscarDir;
    }

    public JTextField getTxtBuscarRuc() {
        return txtBuscarRuc;
    }

    public void setTxtBuscarRuc(JTextField txtBuscarRuc) {
        this.txtBuscarRuc = txtBuscarRuc;
    }

    public JTextField getTxtBuscarSol() {
        return txtBuscarSol;
    }

    public void setTxtBuscarSol(JTextField txtBuscarSol) {
        this.txtBuscarSol = txtBuscarSol;
    }

    
                
}
