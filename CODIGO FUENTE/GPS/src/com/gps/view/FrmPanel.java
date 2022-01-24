/*
 * and open the template in the editor.
 */
package com.gps.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.accessibility.AccessibleContext;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

public class FrmPanel extends JPanel {

    private JPanel panelBarra = new JPanel();
    private JPanel panelContenido = new JPanel();
    private JPanel panelFormulario = new JPanel();
    private JPanel panelFrm = new JPanel();
    private JPanel panelData = new JPanel();
    private JPanel panelBusqueda = new JPanel();
    private JPanel panelTabla = new JPanel();
    private JToolBar barra = new JToolBar();
    private JToolBar barraBusqueda = new JToolBar();
    private JLabel ficha = new JLabel();
    private JButton nuevo = new JButton();
    
      private JButton AgregarFilas = new JButton();
         private JButton Confirmar = new JButton();
      
    private JButton actualizar = new JButton();
    private JButton eliminar = new JButton();
    private JScrollPane scrollTable = new JScrollPane();
    private JTable tablaDatos = new JTable();

    public FrmPanel(ImageIcon imgFicha, String Titulo) {
        ficha.setIcon(imgFicha);
        ficha.setText(Titulo);
        construir();
    }

    private void construir() {
        this.setLayout(new BorderLayout());
        this.add("North", panelBarra);
        this.add("Center", panelContenido);
        panelContenido.setLayout(new BorderLayout());
        //panelFrm.setLayout(new BorderLayout());
        //panelFrm.add("North", panelFormulario);
        panelFormulario.setLayout(new GridBagLayout());
        panelContenido.add("North", panelFormulario);
        panelContenido.add("Center", panelData);
        panelData.setLayout(new BorderLayout());
        panelData.add("North", panelBusqueda);
        panelBusqueda.setLayout(new BorderLayout());
        panelBusqueda.add(barraBusqueda);
        barraBusqueda.setFloatable(false);
        panelData.add("Center", scrollTable);
        Border etchedBdr = BorderFactory.createEtchedBorder();
        panelBarra.setBorder(etchedBdr);
        panelBarra.setLayout(new FlowLayout());
        panelBarra.add(barra);
        Font fuente = new Font("Monospaced", Font.BOLD, 18);
        ficha.setFont(fuente);
        tablaDatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        barra.add(ficha);
        barra.addSeparator();
        nuevo.setIcon(null);
        nuevo.setToolTipText("Nuevo");
        actualizar.setIcon(null);
        actualizar.setToolTipText("Actualizar");
        eliminar.setIcon(null);
        eliminar.setToolTipText("Eliminar");
        barra.add(nuevo);
        barra.add(actualizar);
        barra.add(eliminar);
        barra.addSeparator();
        barra.setFloatable(false);
        panelFormulario.setBorder(etchedBdr);
        panelBusqueda.setBorder(etchedBdr);
        scrollTable.setViewportView(tablaDatos);
    }

    public JButton getActualizar() {
        return actualizar;
    }

    public void setActualizar(JButton actualizar) {
        this.actualizar = actualizar;
    }

    public JToolBar getBarra() {
        return barra;
    }

    public void setBarra(JToolBar barra) {
        this.barra = barra;
    }

    public JToolBar getBarraBusqueda() {
        return barraBusqueda;
    }

    public void setBarraBusqueda(JToolBar barraBusqueda) {
        this.barraBusqueda = barraBusqueda;
    }

    public JButton getEliminar() {
        return eliminar;
    }

    public void setEliminar(JButton eliminar) {
        this.eliminar = eliminar;
    }

    public JLabel getFicha() {
        return ficha;
    }

    public void setFicha(JLabel ficha) {
        this.ficha = ficha;
    }

    public JButton getNuevo() {
        return nuevo;
    }

    public void setNuevo(JButton nuevo) {
        this.nuevo = nuevo;
    }

    public JPanel getPanelBarra() {
        return panelBarra;
    }

    public void setPanelBarra(JPanel panelBarra) {
        this.panelBarra = panelBarra;
    }

    public JPanel getPanelBusqueda() {
        return panelBusqueda;
    }

    public void setPanelBusqueda(JPanel panelBusqueda) {
        this.panelBusqueda = panelBusqueda;
    }

    public JPanel getPanelContenido() {
        return panelContenido;
    }

    public void setPanelContenido(JPanel panelContenido) {
        this.panelContenido = panelContenido;
    }

    public JPanel getPanelData() {
        return panelData;
    }

    public void setPanelData(JPanel panelData) {
        this.panelData = panelData;
    }

    public JPanel getPanelFormulario() {
        return panelFormulario;
    }

    public void setPanelFormulario(JPanel panelFormulario) {
        this.panelFormulario = panelFormulario;
    }

    public JPanel getPanelTabla() {
        return panelTabla;
    }

    public void setPanelTabla(JPanel panelTabla) {
        this.panelTabla = panelTabla;
    }

    public JScrollPane getScrollTable() {
        return scrollTable;
    }

    public void setScrollTable(JScrollPane scrollTable) {
        this.scrollTable = scrollTable;
    }

    public JTable getTablaDatos() {
        return tablaDatos;
    }

    public void setTablaDatos(JTable tablaDatos) {
        this.tablaDatos = tablaDatos;
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

    public JPanel getPanelFrm() {
        return panelFrm;
    }

    public void setPanelFrm(JPanel panelFrm) {
        this.panelFrm = panelFrm;
    }

    public static String getTOOL_TIP_TEXT_KEY() {
        return TOOL_TIP_TEXT_KEY;
    }

    public static int getUNDEFINED_CONDITION() {
        return UNDEFINED_CONDITION;
    }

    public static int getWHEN_ANCESTOR_OF_FOCUSED_COMPONENT() {
        return WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
    }

    public static int getWHEN_FOCUSED() {
        return WHEN_FOCUSED;
    }

    public static int getWHEN_IN_FOCUSED_WINDOW() {
        return WHEN_IN_FOCUSED_WINDOW;
    }

    public static float getBOTTOM_ALIGNMENT() {
        return BOTTOM_ALIGNMENT;
    }

    public static float getCENTER_ALIGNMENT() {
        return CENTER_ALIGNMENT;
    }

    public static float getLEFT_ALIGNMENT() {
        return LEFT_ALIGNMENT;
    }

    public static float getRIGHT_ALIGNMENT() {
        return RIGHT_ALIGNMENT;
    }

    public static float getTOP_ALIGNMENT() {
        return TOP_ALIGNMENT;
    }

    public static int getABORT() {
        return ABORT;
    }

    public static int getALLBITS() {
        return ALLBITS;
    }

    public static int getERROR() {
        return ERROR;
    }

    public static int getFRAMEBITS() {
        return FRAMEBITS;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static int getPROPERTIES() {
        return PROPERTIES;
    }

    public static int getSOMEBITS() {
        return SOMEBITS;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }

    /**
     * @return the AgregarFilas
     */
    public JButton getAgregarFilas() {
        return AgregarFilas;
    }

    /**
     * @param AgregarFilas the AgregarFilas to set
     */
    public void setAgregarFilas(JButton AgregarFilas) {
        this.AgregarFilas = AgregarFilas;
    }

    /**
     * @return the Confirmar
     */
    public JButton getConfirmar() {
        return Confirmar;
    }

    /**
     * @param Confirmar the Confirmar to set
     */
    public void setConfirmar(JButton Confirmar) {
        this.Confirmar = Confirmar;
    }
}
