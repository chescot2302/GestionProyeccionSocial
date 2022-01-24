/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import com.gps.bean.Estado;
import com.gps.comboboxmodel.CbModelEstado;
import com.gps.dao.Conexion;
import com.gps.dao.ObjetoConexion;
import com.gps.logic.LogicEstado;
import com.gps.util.Exportar;
import com.gps.util.JComboBoxAutocomplete;
import com.gps.util.JFileChooserModel;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Administrador
 */
public class ViewReport extends JDialog{
    private JLabel lblEstado=new JLabel("Seleccionar Estado");
    private JComboBoxAutocomplete cbEstado = new JComboBoxAutocomplete();
    private CbModelEstado mdcbEstado = new CbModelEstado(LogicEstado.listarEstado());
    private JDateChooser fechainicio;
    private JDateChooser fechafin;
    private JSpinnerDateEditor txtfechainicio = new JSpinnerDateEditor();
    private JSpinnerDateEditor txtfechafin = new JSpinnerDateEditor();
    private JLabel lblInicio = new JLabel("Fecha Inicio");
    private JLabel lblFin = new JLabel("Fecha Fin");
    //private JButton btnGenerarPDF=new JButton("Generar");
    private JButton btnGenerarWORD=new JButton("Generar");
    //FileNameExtensionFilter filterPDF = new FileNameExtensionFilter("pdf", "pdf");
    FileNameExtensionFilter filterDOC = new FileNameExtensionFilter("docx", "docx");
    private JFileChooserModel fileChooser = new JFileChooserModel();
    public ViewReport(){
        initComponents();
    }
    
    
    private void initComponents(){
        fechainicio = new JDateChooser(txtfechainicio);
        fechafin = new JDateChooser(txtfechafin);
        fechainicio.setDate(new Date());
        fechafin.setDate(new Date());
        cbEstado.setModel(mdcbEstado);
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth=2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(lblEstado,gbc);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth=2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(cbEstado,gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(lblInicio,gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(fechainicio,gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(lblFin,gbc);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(fechafin,gbc);
/*        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(btnGenerarPDF,gbc);*/
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth=1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        this.getContentPane().add(btnGenerarWORD,gbc);
        //btnGenerarPDF.setIcon(ViewLogin.picture.getIconPdf());
        btnGenerarWORD.setIcon(ViewLogin.picture.getIconWord16());
        //btnGenerarPDF.addActionListener(actionListener);
        btnGenerarWORD.addActionListener(actionListener);
    }
    ActionListener actionListener=new ActionListener(){

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(btnGenerarWORD)){
                exportarDOC();
            }/*else if(e.getSource().equals(btnGenerarPDF)){
                exportarPDF();
            }*/
        }
    };
    private void generarReporte(File archivo, String tipoExportacion, Map parameters, Connection cnx, String rutaJasper) {
        Exportar ex = new Exportar(archivo, parameters, tipoExportacion, cnx, rutaJasper);
        ex.show();        
    }
    
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
    
    /*private void exportarReporteAPDF(BigDecimal idEstado,String fechaIni,String fechaFin,String estado,Connection cnx) throws SQLException {
        File archivo = seleccionarArchivo(filterPDF, "proyecto", "pdf");
        if (archivo != null) {                                                                        
                Map parameters = new HashMap();
                parameters.put("ID_ESTADO", idEstado);                                                
                parameters.put("FECHA_INI", fechaIni);                                                
                parameters.put("FECHA_FIN", fechaFin);                                                
                parameters.put("DESC_ESTADO", estado);                                                
                String rutaJasper = "/com/gps/report/rptProyectoxEstado.jasper";
                generarReporte(archivo, "pdf", parameters, cnx, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");            
        }

    }*/
    
    private void exportarReporteADOC(BigDecimal idEstado,String fechaIni,String fechaFin,String estado,Connection cnx) throws SQLException {
        File archivo = seleccionarArchivo(filterDOC, "proyecto", "docx");
        if (archivo != null) {                                                                        
                Map parameters = new HashMap();
                parameters.put("ID_ESTADO", idEstado);                                                
                parameters.put("FECHA_INI", fechaIni);                                                
                parameters.put("FECHA_FIN", fechaFin);                                                
                parameters.put("DESC_ESTADO", estado);                                                
                String rutaJasper = "/com/gps/report/rptProyectoxEstado.jasper";
                generarReporte(archivo, "docx", parameters, cnx, rutaJasper);
                ViewApplication.mensajeEstado.setText(null);
                ViewApplication.mensajeEstado.setText("REPORTE GENERADO CORRECTAMENTE :)");            
        }

    }
    
/*    class WorkerReportePDF extends SwingWorker<Boolean, Integer> {

        private BigDecimal idEstado;
        private String ini;
        private String fin;
        private String est;
        private Connection cnx;
        public WorkerReportePDF(BigDecimal id,String fechaini,String fechafin, String estado,Connection con) {
            idEstado = id;
            ini=fechaini;
            fin=fechafin;
            est=estado;
            cnx=con;
        }
 
        @Override
        protected Boolean doInBackground() {
            try {
                exportarReporteAPDF(idEstado,ini,fin,est,cnx);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }*/
     
     class WorkerReporteDOC extends SwingWorker<Boolean, Integer> {

        private BigDecimal idEstado;
        private String ini;
        private String fin;
        private String est;
        private Connection cnx;
        public WorkerReporteDOC(BigDecimal id,String fechaini,String fechafin, String estado,Connection con) {
            idEstado = id;
            ini=fechaini;
            fin=fechafin;
            est=estado;
            cnx=con;
        }
 
        @Override
        protected Boolean doInBackground() {
            try {
                exportarReporteADOC(idEstado,ini,fin,est,cnx);
                return true;
            } catch (SQLException ex) {
                System.err.println(ex.getCause());
                return false;
            }
        }
    }
     
     private void exportarDOC(){              
         try{
         java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
         String ini = sdf.format(fechainicio.getDate());
         String fin = sdf.format(fechafin.getDate());
         Estado obj=mdcbEstado.getElement(cbEstado.getSelectedIndex());
         ObjetoConexion cnxOra = new ObjetoConexion();
         Conexion objCnx = cnxOra.conectarORACLE();
         WorkerReporteDOC worker=new WorkerReporteDOC(obj.getId(),ini,fin,obj.getDescripcion(),objCnx.getCnx());
         worker.execute();                               
         }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "Seleccione datos correctos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
         }
     }
     
     /*private void exportarPDF(){
         try{
         java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("dd/MM/yyyy");
         String ini = sdf.format(fechainicio.getDate());
         String fin = sdf.format(fechafin.getDate());
         Estado obj=mdcbEstado.getElement(cbEstado.getSelectedIndex());
         ObjetoConexion cnxOra = new ObjetoConexion();
         Conexion objCnx = cnxOra.conectarORACLE();
         WorkerReportePDF worker=new WorkerReportePDF(obj.getId(),ini,fin,obj.getDescripcion(),objCnx.getCnx());
         worker.execute();                               
         }catch(Exception ex){
         JOptionPane.showMessageDialog(null, "Seleccione datos correctos", "Aviso", JOptionPane.INFORMATION_MESSAGE);
         }
     }*/
    
}
