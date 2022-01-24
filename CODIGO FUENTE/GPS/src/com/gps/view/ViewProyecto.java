/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Docente;
import com.gps.bean.Documento;
import com.gps.bean.EstProy;
import com.gps.bean.EstProyPK;
import com.gps.bean.Estado;
import com.gps.bean.Estudiante;
import com.gps.bean.ProyDoc;
import com.gps.bean.ProyDocPK;
import com.gps.bean.ProyEst;
import com.gps.bean.ProyEstPK;
import com.gps.bean.Solicitante;
import com.gps.bean.Proyecto;
import com.gps.bean.TipoDocumento;
import com.gps.dao.Conexion;
import com.gps.dao.ObjetoConexion;
import com.gps.logic.LogicDocumento;
import com.gps.logic.LogicEstProy;
import com.gps.logic.LogicProyDoc;
import com.gps.logic.LogicProyEst;
import com.gps.logic.LogicSolicitante;
import com.gps.logic.LogicProyecto;
import com.gps.tablemodel.TableModelDocente;
import com.gps.tablemodel.TableModelEstudiante;
import com.gps.tablemodel.TableModelProyecto;
import com.gps.tablemodel.TableModelSolicitante;
import com.gps.util.CellEditorOption;
import com.gps.util.Exportar;
import com.gps.util.JFileChooserModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableRowSorter;
import org.openide.util.Exceptions;

/**
 *
 * @author Administrador
 */
public class ViewProyecto {

    private PanelMolde molde = new PanelMolde();
    private JPanel panelData = new JPanel();
    private JPanel panelTabla = new JPanel();
    private JPanel panelBusqueda = new JPanel();
    private JScrollPane scrollTabla = new JScrollPane();
    private TableModelProyecto modelProyecto = new TableModelProyecto(LogicProyecto.listarProyecto());
    private JTable tablaProyecto = new JTable();
    private JButton btnPreview = new JButton(ViewLogin.picture.getIconPreview());
    private JButton btnPrint = new JButton(ViewLogin.picture.getIconReport16());
    private JButton btnPDF = new JButton(ViewLogin.picture.getIconPdf());
    private JButton btnEXCEL = new JButton(ViewLogin.picture.getIconWord16());
    private JToolBar tool = new JToolBar();
    private FormProyecto frmProyecto = new FormProyecto(tablaProyecto, modelProyecto);
    private FrmInfProyecto frminfproy = new FrmInfProyecto();
    private JLabel lblApPaterno = new JLabel("Proyecto");
    private JLabel lblApMaterno = new JLabel("Solicitante");
    private JLabel lblNombres = new JLabel("Fecha");
    private JTextField buscaProyecto = new JTextField();
    private JTextField buscaSolicitante = new JTextField();
    private JTextField buscaFecha = new JTextField();
    private ViewBusquedaSolicitante busquedaSolicitante;
    private Solicitante Solicitante;
    private Proyecto objProyecto = null;
    private ViewBusquedaEstudiante busquedaEstudiante;
    private ViewBusquedaProfesor busquedaDocente;
    private JFileChooserModel fileChooser = new JFileChooserModel();
    FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterDOC = new FileNameExtensionFilter("docx", "docx");
    private ViewReport viewReport;
    public ViewProyecto() {
        initComponents();
    }

    private void initComponents() {
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
        tool.add(buscaProyecto);
        tool.addSeparator();
        tool.add(lblApMaterno);
        tool.add(buscaSolicitante);
        tool.addSeparator();
        tool.add(lblNombres);
        tool.add(buscaFecha);
        panelBusqueda.add(tool);
        panelTabla.add(scrollTabla);
        scrollTabla.setViewportView(tablaProyecto);
        molde.getPanelarbol().setVisible(false);
        molde.getPanelinf().setLayout(new BorderLayout());
        molde.getPanelinf().add(frmProyecto);
        molde.getPanelfrm().add(frminfproy);
        molde.getPanelarbol().setLayout(new BorderLayout());
        molde.getPaneltabla().setLayout(new BorderLayout());
        molde.getPaneltabla().add(panelData);
        tablaProyecto.addMouseListener(mouseListener);
        frmProyecto.getBtnAddSolicitante().addActionListener(actionListener);
        frmProyecto.getBtnGuardar().addActionListener(actionListener);
        frmProyecto.getBtnCancelar().addActionListener(actionListener);
        tablaProyecto.setAutoCreateRowSorter(true);
        frminfproy.getBtnAddDoc().addActionListener(actionListener);
        frminfproy.getBtnCommitDoc().addActionListener(actionListener);
        frminfproy.getBtnRollbackDoc().addActionListener(actionListener);
        frminfproy.getBtnAddEst().addActionListener(actionListener);
        frminfproy.getBtnCommitEst().addActionListener(actionListener);
        frminfproy.getBtnRollbackEst().addActionListener(actionListener);
        frminfproy.getBtnAddDoce().addActionListener(actionListener);
        frminfproy.getBtnCommitDoce().addActionListener(actionListener);
        frminfproy.getBtnRollbackDoce().addActionListener(actionListener);
        frminfproy.getBtnAddEsta().addActionListener(actionListener);
        frminfproy.getBtnCommitEsta().addActionListener(actionListener);
        frminfproy.getBtnRollbackEsta().addActionListener(actionListener);
        //btnPrint.setVisible(false);
        btnPrint.setToolTipText("Generar reportes de proyecto");
        btnPreview.setVisible(false);
        btnPDF.addActionListener(actionListener);
        btnEXCEL.addActionListener(actionListener);
        btnPDF.setToolTipText("Ver Información del proyecto");
        btnEXCEL.setToolTipText("Ver Información del proyecto");
        buscaProyecto.addKeyListener(keyfiltrar);
        buscaSolicitante.addKeyListener(keyfiltrar);
        buscaFecha.addKeyListener(keyfiltrar);
        btnPrint.addActionListener(actionListener);
        tablaProyecto.addKeyListener(keyListener);
    }
    
    KeyListener keyListener = new KeyListener() {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getSource().equals(tablaProyecto)) {
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
        }
    };
    
     private void viewPanelReportes() {
        try {
            viewReport.setVisible(true);
        } catch (Exception ex) {
            viewReport = new ViewReport();
            viewReport.setTitle("Generar Reportes");
            viewReport.pack();
            viewReport.setLocationRelativeTo(null);
            viewReport.setVisible(true);
        }
    }
    
    private void filtrarTabla(JTable tabla, TableModelProyecto modelTablaSol) {
        TableRowSorter<TableModelProyecto> sorter = new TableRowSorter(modelTablaSol);
        RowFilter<TableModelProyecto, Object> rf1 = null;
        RowFilter<TableModelProyecto, Object> rf2 = null;
        RowFilter<TableModelProyecto, Object> rf3 = null;        
        List<RowFilter<TableModelProyecto, Object>> rfs = new ArrayList(3);
        try {            
            rf1 = RowFilter.regexFilter(buscaProyecto.getText(), 1);            
            rf2 = RowFilter.regexFilter(buscaSolicitante.getText(), 3);            
            rf3 = RowFilter.regexFilter(buscaFecha.getText(), 4);                        
            rfs.add(rf1);
            rfs.add(rf2);
            rfs.add(rf3);            
            RowFilter<TableModelProyecto, Object> af = RowFilter.andFilter(rfs);
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
            if (buscaProyecto == e.getSource()) {
                filtrarTabla(tablaProyecto,modelProyecto);
            }else if (buscaSolicitante == e.getSource()) {
                filtrarTabla(tablaProyecto,modelProyecto);
            }else if (buscaFecha == e.getSource()) {
                filtrarTabla(tablaProyecto,modelProyecto);
            } 
        }
    };
    
    private File seleccionarArchivo(FileNameExtensionFilter tipoFiltro, String nombreLibro, String tipoExportacion) {
        fileChooser.setFileFilter(tipoFiltro);
        Date hoy = new Date();
        File archivo = new File(nombreLibro + hoy.getTime() + "." + tipoExportacion);
        fileChooser.setSelectedFile(archivo);
        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            JOptionPane.showMessageDialog(null, "No seleccionó ningún fichero", "Diálogo cerrado o cancelado", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }

    }
    
    private void generarReporte(File archivo, String tipoExportacion, Map parameters, Connection cnx, String rutaJasper) {
        Exportar ex = new Exportar(archivo, parameters, tipoExportacion, cnx, rutaJasper);
        ex.show();        
    }
    
    private void exportarReporteAPDF(BigDecimal idProyecto,Connection cnx) throws SQLException {
        File archivo = seleccionarArchivo(filterPDF, "proyecto", "pdf");
        if (archivo != null) {                                                                        
                Map parameters = new HashMap();
                parameters.put("ID_PROYECTO", idProyecto);                                                
                String rutaJasper = "/com/gps/report/rptProject.jasper";
                generarReporte(archivo, "pdf", parameters, cnx, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");            
        }

    }
    
    private void exportarReporteADOC(BigDecimal idProyecto,Connection cnx) throws SQLException {
        File archivo = seleccionarArchivo(filterDOC, "proyecto", "docx");
        if (archivo != null) {                                                                        
                Map parameters = new HashMap();
                parameters.put("ID_PROYECTO", idProyecto);                                                
                String rutaJasper = "/com/gps/report/rptProject.jasper";
                generarReporte(archivo, "docx", parameters, cnx, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");            
        }

    }
    
     class WorkerReportePDF extends SwingWorker<Boolean, Integer> {

        private BigDecimal idProyecto;
        private Connection cnx;
        public WorkerReportePDF(BigDecimal id,Connection con) {
            idProyecto = id;
            cnx=con;
        }
 
        @Override
        protected Boolean doInBackground() {
            try {
                exportarReporteAPDF(idProyecto,cnx);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }
     
     class WorkerReporteDOC extends SwingWorker<Boolean, Integer> {

        private BigDecimal idProyecto;
        private Connection cnx;
        public WorkerReporteDOC(BigDecimal id,Connection con) {
            idProyecto = id;
            cnx=con;
        }
 
        @Override
        protected Boolean doInBackground() {
            try {
                exportarReporteADOC(idProyecto,cnx);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }

    private void cleaning() {
        Solicitante = null;
        frmProyecto.getTxtProyecto().setText(null);
        frmProyecto.getTxtSolicitante().setText(null);
        frmProyecto.getBtnGuardar().setText("Guardar");
        frmProyecto.getLblId().setText(null);
    }
    
    private void exportarPDF(){
        int fila = tablaProyecto.getSelectedRow();        
        if (fila > -1) {
            int modelrow = tablaProyecto.convertRowIndexToModel(fila);
            Proyecto obj = ((TableModelProyecto) tablaProyecto.getModel()).getValue(modelrow);                       
                ObjetoConexion cnxOra = new ObjetoConexion();
                Conexion objCnx = cnxOra.conectarORACLE();
                WorkerReportePDF worker=new WorkerReportePDF(obj.getId(),objCnx.getCnx());
                worker.execute();                               
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la lista de proyectos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void exportarDOC(){
        int fila = tablaProyecto.getSelectedRow();        
        if (fila > -1) {
            int modelrow = tablaProyecto.convertRowIndexToModel(fila);
            Proyecto obj = ((TableModelProyecto) tablaProyecto.getModel()).getValue(modelrow);                       
                ObjetoConexion cnxOra = new ObjetoConexion();
                Conexion objCnx = cnxOra.conectarORACLE();
                WorkerReporteDOC worker=new WorkerReporteDOC(obj.getId(),objCnx.getCnx());
                worker.execute();                                      
        }else{
            JOptionPane.showMessageDialog(null, "Seleccione un registro de la lista de proyectos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cargarDatosUpdate() {
        frmProyecto.getBtnGuardar().setText("Actualizar");
        try {
            int fila = tablaProyecto.getSelectedRow();
            int modelrow = tablaProyecto.convertRowIndexToModel(fila);
            Proyecto obj = ((TableModelProyecto) tablaProyecto.getModel()).getValue(modelrow);
            frmProyecto.getLblId().setText(obj.getId().toString());
            frmProyecto.getTxtProyecto().setText(obj.getDescripcion());
            Solicitante = obj.getSolicitante();
            frmProyecto.getTxtSolicitante().setText(Solicitante.getDescripcion());
            frmProyecto.getFechainicio().setDate(obj.getFechasol());
        } catch (Exception ex) {
            ViewApplication.mensajeEstado.setText("Error");
            ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconBad());
        }
    }

    private void mantenimiento() {
        BigDecimal id = null;
        String descripcion = null;
        BigDecimal idSolicitante = null;
        BigDecimal estado = null;
        java.sql.Date ini = null;
        switch (frmProyecto.getBtnGuardar().getText()) {
            case "Guardar":
                try {
                    ini = new java.sql.Date(frmProyecto.getFechainicio().getDate().getTime());
                    idSolicitante = Solicitante.getId();
                    descripcion = frmProyecto.getTxtProyecto().getText();
                    estado = LogicProyecto.registrarProyecto(idSolicitante, descripcion, ini);
                    if (estado.compareTo(BigDecimal.ONE) == 0) {
                        modelProyecto.setData(LogicProyecto.listarProyecto());
                        modelProyecto.fireTableDataChanged();
                        cleaning();
                    }
                } catch (Exception ex) {
                    ViewApplication.mensajeEstado.setText("Error");
                    ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconBad());
                }
                break;
            case "Actualizar":
                try {
                    ini = new java.sql.Date(frmProyecto.getFechainicio().getDate().getTime());
                    id = BigDecimal.valueOf(Double.valueOf(frmProyecto.getLblId().getText()));
                    idSolicitante = Solicitante.getId();
                    descripcion = frmProyecto.getTxtProyecto().getText();
                    estado = LogicProyecto.actualizarProyecto(id, idSolicitante, descripcion, ini);
                    if (estado.compareTo(BigDecimal.valueOf(2)) == 0) {
                        modelProyecto.setData(LogicProyecto.listarProyecto());
                        modelProyecto.fireTableDataChanged();
                        cleaning();
                    }
                } catch (Exception ex) {
                    ViewApplication.mensajeEstado.setText("Error");
                    ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconBad());
                }
                break;
        }

    }

    private void viewPanelBusquedaEstudiante() {
        try {
            busquedaEstudiante.getSearchEstudiante().getTablaEstudiante().addMouseListener(mouseListener);
            busquedaEstudiante.setVisible(true);
        } catch (Exception ex) {
            busquedaEstudiante = new ViewBusquedaEstudiante();
            busquedaEstudiante.getSearchEstudiante().getTablaEstudiante().addMouseListener(mouseListener);
            busquedaEstudiante.setTitle("Buscar Estudiantes");
            busquedaEstudiante.setSize(900, 400);
            busquedaEstudiante.setLocationRelativeTo(null);
            busquedaEstudiante.setVisible(true);
        }
    }
    
    private void viewPanelBusquedaProfesor() {
        try {
            busquedaDocente.getSearchDocente().getTablaDocente().addMouseListener(mouseListener);
            busquedaDocente.setVisible(true);
        } catch (Exception ex) {
            busquedaDocente = new ViewBusquedaProfesor();
            busquedaDocente.getSearchDocente().getTablaDocente().addMouseListener(mouseListener);
            busquedaDocente.setTitle("Buscar Docentes");
            busquedaDocente.setSize(900, 400);
            busquedaDocente.setLocationRelativeTo(null);
            busquedaDocente.setVisible(true);
        }
    }

    private void nuevoRegistroEstudiantes(Proyecto proy) {
        try {
            if (proy != null) {
                viewPanelBusquedaEstudiante();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un proyecto", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
    }
    
    private void nuevoRegistroDocente(Proyecto proy) {
        try {
            if (proy != null) {
                viewPanelBusquedaProfesor();
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un proyecto", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
    }

    private void nuevoRegistroDocumentos(Proyecto proy) {
        try {
            if (proy != null) {
                Proyecto p = new Proyecto();
                p = proy;
                TipoDocumento td = new TipoDocumento();
                Documento doc = new Documento();
                doc.setTipoDocumento(td);
                doc.setObjProyecto(p);
                doc.setEstadoOperacion("i");
                doc.setId(BigDecimal.valueOf((new java.util.Date()).getTime()));
                frminfproy.getModeldoc().getData().add(doc);
                frminfproy.getModeldoc().fireTableDataChanged();                
                frminfproy.getPanelDocumentos().getTablaDatos().changeSelection(frminfproy.getModeldoc().getRowCount() - 1, 1, true, true);
                frminfproy.getPanelDocumentos().getTablaDatos().editCellAt(frminfproy.getModeldoc().getRowCount() - 1, 1);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un proyecto", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
        }
    }
    
    private void nuevoRegistroEstadoProy(Proyecto proy) {
        try {
            if (proy != null) {
                Proyecto p = new Proyecto();
                p = proy;
                Estado est = new Estado();                                
                EstProy ep = new EstProy();   
                EstProyPK pk=new EstProyPK();
                pk.setIdEstado(BigDecimal.valueOf((new java.util.Date()).getTime()));
                pk.setIdProyecto(BigDecimal.valueOf((new java.util.Date()).getTime()));
                ep.setEstado(est);
                ep.setProyecto(p);                
                ep.setEstProyPK(pk);
                ep.setEstadoOperacion("i");                   
                frminfproy.getModelestproy().getData().add(ep);
                frminfproy.getModelestproy().fireTableDataChanged();                
                frminfproy.getPanelEstados().getTablaDatos().changeSelection(frminfproy.getModelestproy().getRowCount() - 1, 1, true, true);
                frminfproy.getPanelEstados().getTablaDatos().editCellAt(frminfproy.getModelestproy().getRowCount() - 1, 1);
            } else {
                JOptionPane.showMessageDialog(null, "Seleccione un proyecto", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
        }
    }
    
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(frmProyecto.getBtnAddSolicitante())) {
                try {
                    viewPanelBusquedaSolicitante();
                    busquedaSolicitante.getSearchSolicitante().getModelTablaSol().setData(LogicSolicitante.listarSolicitante());
                    busquedaSolicitante.getSearchSolicitante().getModelTablaSol().fireTableDataChanged();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Seleccionar una facultad correcta", "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (e.getSource().equals(frmProyecto.getBtnGuardar())) {
                try {
                    mantenimiento();
                } catch (Exception ex) {
                }
            } else if (e.getSource().equals(frmProyecto.getBtnCancelar())) {
                try {
                    cleaning();
                } catch (Exception ex) {
                }
            } else if (e.getSource().equals(frminfproy.getBtnAddDoc())) {
                try {
                    nuevoRegistroDocumentos(objProyecto);
                } catch (Exception ex) {
                }
            } else if (e.getSource().equals(frminfproy.getBtnCommitDoc())) {                 
                    LogicDocumento.mantenimientoDocumento(frminfproy.getModeldoc().getListaCommit());
                    frminfproy.getModeldoc().getListaCommit().clear();                                        
                    frminfproy.getModeldoc().setData(LogicDocumento.listarDocumento(objProyecto.getId()));                    
                    frminfproy.getModeldoc().fireTableDataChanged();                                    
            }else if(e.getSource().equals(frminfproy.getBtnRollbackDoc())){
                frminfproy.getModeldoc().setData(LogicDocumento.listarDocumento(objProyecto.getId()));                    
                    frminfproy.getModeldoc().fireTableDataChanged();
            }else if(e.getSource().equals(frminfproy.getBtnRollbackEsta())){
                frminfproy.getModelestproy().setData(LogicEstProy.listarEstadoProy(objProyecto.getId()));                    
                    frminfproy.getModelestproy().fireTableDataChanged();                                    
            } else if (e.getSource().equals(frminfproy.getBtnCommitEsta())) {                 
                    LogicEstProy.mantenimientoEstadoProyecto(frminfproy.getModelestproy().getListaCommit());                    
                    frminfproy.getModelestproy().getListaCommit().clear();                                        
                    frminfproy.getModelestproy().setData(LogicEstProy.listarEstadoProy(objProyecto.getId()));                    
                    frminfproy.getModelestproy().fireTableDataChanged();                                    
            }else if(e.getSource().equals(frminfproy.getBtnRollbackEst())){
                frminfproy.getModelalum().setData(LogicProyEst.listarProyEst(objProyecto.getId()));
                frminfproy.getModelalum().fireTableDataChanged();
            }else if (e.getSource().equals(frminfproy.getBtnAddEst())) {
                try {
                    nuevoRegistroEstudiantes(objProyecto);
                } catch (Exception ex) {
                }
            }else if(e.getSource().equals(frminfproy.getBtnCommitEst())){
                LogicProyEst.mantenimientoProyEst(frminfproy.getModelalum().getListaCommit());
                frminfproy.getModelalum().getListaCommit().clear();
                frminfproy.getModelalum().setData(LogicProyEst.listarProyEst(objProyecto.getId()));
                frminfproy.getModelalum().fireTableDataChanged();
            }else if(e.getSource().equals(frminfproy.getBtnRollbackDoce())){
            frminfproy.getModelprof().setData(LogicProyDoc.listarProyDoc(objProyecto.getId()));
                frminfproy.getModelprof().fireTableDataChanged();
            }else if(e.getSource().equals(frminfproy.getBtnCommitDoce())){
                LogicProyDoc.mantenimientoProyDoc(frminfproy.getModelprof().getListaCommit());
                frminfproy.getModelprof().getListaCommit().clear();
                frminfproy.getModelprof().setData(LogicProyDoc.listarProyDoc(objProyecto.getId()));
                frminfproy.getModelprof().fireTableDataChanged();
            }else if (e.getSource().equals(frminfproy.getBtnAddDoce())) {
                try {
                    nuevoRegistroDocente(objProyecto);
                } catch (Exception ex) {
                }
            }else if (e.getSource().equals(frminfproy.getBtnAddEsta())) {
                try {
                    nuevoRegistroEstadoProy(objProyecto);
                } catch (Exception ex) {
                }
            }else if(e.getSource().equals(btnPDF)){
                    exportarPDF();
            }else if(e.getSource().equals(btnEXCEL)){
                    exportarDOC();
            }else if(e.getSource().equals(btnPrint)){
                viewPanelReportes();
            }
        }
    };

    private void eliminar() {
        int fila = tablaProyecto.getSelectedRow();
        int modelrow = tablaProyecto.convertRowIndexToModel(fila);
        Proyecto proy = ((TableModelProyecto) tablaProyecto.getModel()).getValue(modelrow);
        if (LogicProyecto.eliminarProyecto(proy.getId())) {
            ((TableModelProyecto) tablaProyecto.getModel()).borrar(modelrow);
            ViewApplication.mensajeEstado.setText("Eliminado correctamente");
            ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconGood());
        } else {
            ViewApplication.mensajeEstado.setText("Error");
            ViewApplication.mensajeEstado.setIcon(ViewLogin.picture.getIconBad());
        }
    }

    private void cargarInfProyecto() {
        int fila = tablaProyecto.getSelectedRow();
        int modelrow = tablaProyecto.convertRowIndexToModel(fila);
        objProyecto = ((TableModelProyecto) tablaProyecto.getModel()).getValue(modelrow);
        frminfproy.getPanelDocumentos().getFicha().setText("PROYECTO N° 0" + objProyecto.getId());
        frminfproy.getPanelEstudiantes().getFicha().setText("PROYECTO N° 0" + objProyecto.getId());
        frminfproy.getPanelDocentes().getFicha().setText("PROYECTO N° 0" + objProyecto.getId());
        frminfproy.getPanelEstados().getFicha().setText("PROYECTO N° 0" + objProyecto.getId());
        frminfproy.getModeldoc().setData(LogicDocumento.listarDocumento(objProyecto.getId()));
        frminfproy.getModeldoc().fireTableDataChanged();
        frminfproy.getModelalum().setData(LogicProyEst.listarProyEst(objProyecto.getId()));
        frminfproy.getModelalum().fireTableDataChanged();
        frminfproy.getModelprof().setData(LogicProyDoc.listarProyDoc(objProyecto.getId()));
        frminfproy.getModelprof().fireTableDataChanged();
        frminfproy.getModelestproy().setData(LogicEstProy.listarEstadoProy(objProyecto.getId()));
        frminfproy.getModelestproy().fireTableDataChanged();
        //frminfproy.getPanelEstados().getTablaDatos().getColumnModel().getColumn(1).setCellEditor(new CellEditorOption(LogicEstProy.listarEstadoProy(objProyecto.getId())));
    }

    private void viewPanelBusquedaSolicitante() {
        try {
            busquedaSolicitante.getSearchSolicitante().getTabDataSol().addMouseListener(mouseListener);
            busquedaSolicitante.setVisible(true);
        } catch (Exception ex) {
            busquedaSolicitante = new ViewBusquedaSolicitante();
            busquedaSolicitante.getSearchSolicitante().getTabDataSol().addMouseListener(mouseListener);
            busquedaSolicitante.setTitle("Buscar Solicitantes");
            //busquedaSolicitante.pack();
            busquedaSolicitante.setSize(800, 500);
            busquedaSolicitante.setLocationRelativeTo(null);
            busquedaSolicitante.setVisible(true);
        }
    }
    MouseListener mouseListener = new MouseListener() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
                if (e.getSource().equals(tablaProyecto)) {
                    cargarInfProyecto();
                }
            } else if (e.getClickCount() == 2) {
                if (e.getSource().equals(tablaProyecto)) {
                    cargarDatosUpdate();
                }
                try {
                    if (e.getSource().equals(busquedaEstudiante.getSearchEstudiante().getTablaEstudiante())) {
                        int fila = busquedaEstudiante.getSearchEstudiante().getTablaEstudiante().getSelectedRow();
                        int modelrow = busquedaEstudiante.getSearchEstudiante().getTablaEstudiante().convertRowIndexToModel(fila);
                        Estudiante est = ((TableModelEstudiante) busquedaEstudiante.getSearchEstudiante().getTablaEstudiante().getModel()).getValue(modelrow);
                        ProyEst py = new ProyEst();
                        ProyEstPK pk = new ProyEstPK();
                        pk.setIdEstudiante(est.getId());
                        pk.setIdProyecto(objProyecto.getId());
                        py.setProyEstPK(pk);
                        py.setEstadoOperacion("i");
                        py.setEstudiante(est);
                        py.setProyecto(objProyecto);
                        int old = frminfproy.getModelalum().getListaCommit().size();
                        frminfproy.getModelalum().getListaCommit().add(py);
                        int nuevo = frminfproy.getModelalum().getListaCommit().size();
                        if (nuevo != old) {
                            if (!frminfproy.getModelalum().getData().contains(py)) {
                                frminfproy.getModelalum().getData().add(py);
                                frminfproy.getModelalum().fireTableDataChanged();
                                frminfproy.getPanelEstudiantes().getTablaDatos().changeSelection(frminfproy.getModelalum().getRowCount() - 1, 1, true, true);
                                frminfproy.getPanelEstudiantes().getTablaDatos().editCellAt(frminfproy.getModelalum().getRowCount() - 1, 1);
                            }else{
                                frminfproy.getModelalum().getListaCommit().remove(py);
                            }
                        }
                    }
                } catch (Exception ex) {
                }
                try {
                    if (e.getSource().equals(busquedaDocente.getSearchDocente().getTablaDocente())) {
                        int fila = busquedaDocente.getSearchDocente().getTablaDocente().getSelectedRow();
                        int modelrow = busquedaDocente.getSearchDocente().getTablaDocente().convertRowIndexToModel(fila);
                        Docente est = ((TableModelDocente) busquedaDocente.getSearchDocente().getTablaDocente().getModel()).getValue(modelrow);
                        ProyDoc py = new ProyDoc();
                        ProyDocPK pk = new ProyDocPK();
                        pk.setIdDocente(est.getId());
                        pk.setIdProyecto(objProyecto.getId());
                        py.setProyDocPK(pk);
                        py.setEstadoOperacion("i");
                        py.setDocente(est);
                        py.setProyecto(objProyecto);
                        int old = frminfproy.getModelprof().getListaCommit().size();
                        frminfproy.getModelprof().getListaCommit().add(py);
                        int nuevo = frminfproy.getModelprof().getListaCommit().size();
                        if (nuevo != old) {
                            if (!frminfproy.getModelprof().getData().contains(py)) {
                                frminfproy.getModelprof().getData().add(py);
                                frminfproy.getModelprof().fireTableDataChanged();
                                frminfproy.getPanelDocentes().getTablaDatos().changeSelection(frminfproy.getModelprof().getRowCount() - 1, 1, true, true);
                                frminfproy.getPanelDocentes().getTablaDatos().editCellAt(frminfproy.getModelprof().getRowCount() - 1, 1);
                            }else{
                                frminfproy.getModelprof().getListaCommit().remove(py);
                            }
                        }
                    }
                } catch (Exception ex) {
                }
                try {
                    if (e.getSource().equals(busquedaSolicitante.getSearchSolicitante().getTabDataSol())) {
                        int fila = busquedaSolicitante.getSearchSolicitante().getTabDataSol().getSelectedRow();
                        int modelrow = busquedaSolicitante.getSearchSolicitante().getTabDataSol().convertRowIndexToModel(fila);
                        Solicitante Sol = ((TableModelSolicitante) busquedaSolicitante.getSearchSolicitante().getTabDataSol().getModel()).getValue(modelrow);
                        Solicitante = Sol;
                        frmProyecto.getTxtSolicitante().setText(Solicitante.getDescripcion());
                        busquedaSolicitante.setVisible(false);
                    }
                } catch (Exception ex) {
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

    public Solicitante getSolicitante() {
        return Solicitante;
    }

    public void setSolicitante(Solicitante Solicitante) {
        this.Solicitante = Solicitante;
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

    public JTextField getbuscaSolicitante() {
        return buscaSolicitante;
    }

    public void setbuscaSolicitante(JTextField buscaSolicitante) {
        this.buscaSolicitante = buscaSolicitante;
    }

    public JTextField getbuscaProyecto() {
        return buscaProyecto;
    }

    public void setbuscaProyecto(JTextField buscaProyecto) {
        this.buscaProyecto = buscaProyecto;
    }

    public JTextField getbuscaFecha() {
        return buscaFecha;
    }

    public void setbuscaFecha(JTextField buscaFecha) {
        this.buscaFecha = buscaFecha;
    }

    public ViewBusquedaSolicitante getBusquedaSolicitante() {
        return busquedaSolicitante;
    }

    public void setBusquedaSolicitante(ViewBusquedaSolicitante busquedaSolicitante) {
        this.busquedaSolicitante = busquedaSolicitante;
    }

    public FormProyecto getFrmProyecto() {
        return frmProyecto;
    }

    public void setFrmProyecto(FormProyecto frmProyecto) {
        this.frmProyecto = frmProyecto;
    }

    public FrmInfProyecto getFrminfproy() {
        return frminfproy;
    }

    public void setFrminfproy(FrmInfProyecto frminfproy) {
        this.frminfproy = frminfproy;
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

    public TableModelProyecto getModelProyecto() {
        return modelProyecto;
    }

    public void setModelProyecto(TableModelProyecto modelProyecto) {
        this.modelProyecto = modelProyecto;
    }

    public PanelMolde getMolde() {
        return molde;
    }

    public void setMolde(PanelMolde molde) {
        this.molde = molde;
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }

    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
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

    public JScrollPane getScrollTabla() {
        return scrollTabla;
    }

    public void setScrollTabla(JScrollPane scrollTabla) {
        this.scrollTabla = scrollTabla;
    }

    public JTable getTablaProyecto() {
        return tablaProyecto;
    }

    public void setTablaProyecto(JTable tablaProyecto) {
        this.tablaProyecto = tablaProyecto;
    }

    public JToolBar getTool() {
        return tool;
    }

    public void setTool(JToolBar tool) {
        this.tool = tool;
    }
}
