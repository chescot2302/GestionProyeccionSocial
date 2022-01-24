/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.util;


import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 *
 * @author JONA
 */
public class Exportar {

    private File archivoDestino;
    private Map parameters = new HashMap();
    private String tipoExpor = "";
    private Connection cnx=null;
    private JRDataSource ds=null;
    private String rutaJasper;

    public Exportar(File fileDestino, Map parametros, String tipoExportacion,Connection conexion,String rutaRptJasper) {
        archivoDestino = fileDestino;
        parameters = parametros;
        tipoExpor = tipoExportacion;
        cnx=conexion;
        rutaJasper=rutaRptJasper;
    }
    
    public Exportar(File fileDestino, Map parametros, String tipoExportacion,JRDataSource dataSource,String rutaRptJasper) {
        archivoDestino = fileDestino;
        parameters = parametros;
        tipoExpor = tipoExportacion;
        ds=dataSource;
        rutaJasper=rutaRptJasper;
    }

    public void show() {
        try {
            try (OutputStream out = new FileOutputStream(archivoDestino)) {
                InputStream entrada = this.getClass().getResourceAsStream(rutaJasper);
                JasperPrint jasper=null;
                if(cnx!=null){
                    jasper = JasperFillManager.fillReport(entrada, parameters,cnx);
                }else if(ds!=null){
                    jasper = JasperFillManager.fillReport(entrada, parameters,ds);
                }
                switch (tipoExpor) {
                    case "pdf":
                        JRPdfExporter exporter = new JRPdfExporter();
                        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
                        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
                        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                        exporter.exportReport();
                        break;
                    case "xlsx":
                        //JExcelApiExporter xlsExporter = new JExcelApiExporter();
                        JRXlsxExporter xlsExporter=new JRXlsxExporter();
                        xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT,jasper);
                        xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
                        xlsExporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS,Boolean.TRUE);
                        xlsExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
                        xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
                        xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
                        xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,out);
                        xlsExporter.exportReport();
                        break;
                    case "docx":
                        JRDocxExporter docexporter = new JRDocxExporter();
                        docexporter.setParameter(JRExporterParameter.JASPER_PRINT, jasper);
                        docexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
                        docexporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                        docexporter.exportReport();
                        break;
                }
                out.flush();
            }
            Desktop.getDesktop().open(archivoDestino.getAbsoluteFile());
        } catch (IOException ex) {
            System.err.println(ex.getCause());
        } catch (JRException ex) {
            System.err.println(ex.getCause());
        }
    }
}
