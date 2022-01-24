package com.gps.view;

import com.gps.logic.LogicDecano;
import com.gps.logic.LogicProyecto;
import com.gps.util.IconTreeModel;
import com.gps.util.IconTreeRenderer;
import com.gps.util.TreeEntry;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import org.netbeans.validation.api.ui.ValidationPanel;
import org.openide.util.Exceptions;

public class ViewApplication extends JFrame {

    private JMenuBar menu = new JMenuBar();
    private JMenu menuMantenimiento = new JMenu("MANTENIMIENTO");
    private JMenu menuProyectos = new JMenu("PROYECTOS");    
    private JMenu menuParticipante = new JMenu("PARTICIPANTES");
    private JMenu menuAbout=new JMenu("ABOUT");
    //PARTICIPANTES
    // private JMenuItem menuItemUbigeo = new JMenuItem("UBIGEO");
    private JMenuItem menuItemAbout=new JMenuItem("ABOUT");
    private JMenuItem menuItemEstado = new JMenuItem("ESTADOS");
    private JMenuItem menuItemEscuela = new JMenuItem("ESCUELA");
    private JMenuItem menuItemFactultad = new JMenuItem("FACTULTAD");
    private JMenuItem menuItemGrado = new JMenuItem("GRADO");
    private JMenuItem menuItemAno = new JMenuItem("AÑO");
    private JMenuItem menuItemTipoDocumento = new JMenuItem("TIPO DE DOCUMENTO");
    private JMenuItem menuItemProyecto = new JMenuItem("PROYECTO");
    private JMenuItem menuItemSolicitante = new JMenuItem("SOLICITANTE");    
    private JMenuItem menuItemDecano = new JMenuItem("DECANO");
    private JMenuItem menuItemDocente = new JMenuItem("DOCENTE");
    private JMenuItem menuItemEstudiante = new JMenuItem("ESTUDIANTE");
    private IconTreeModel arbolContenido = new IconTreeModel();
    private IconTreeRenderer render = new IconTreeRenderer();
    private JTree arbol = new JTree();
    private JScrollPane arbolScrollPane = new JScrollPane(arbol);
    public static JScrollPane contenidoScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    private JSplitPane contenido = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, arbolScrollPane, contenidoScrollPane);
    private JToolBar barra = new JToolBar();
    private JLabel msgBienvenida = new JLabel("  Bienvenido: ");
    private JLabel lblusuario = new JLabel();
    private JPanel panelEstado = new JPanel();
    public static JLabel mensajeEstado = new JLabel("Barra de Estado");
    private ValidationPanel pnl = new ValidationPanel();
    private JPanel panelPrincipal = new JPanel();
    public static JDialog popupMensaje = new JDialog();
    private Locale locale = new Locale("es", "es_PE");
    public static Date hoy = new Date();
    private JPanel panelInicio = new JPanel();
    private ViewSolicitante viewSolicitante;
    private ViewDocente viewDocente;
    private ViewEstudiante viewEstudiante;
    private ViewEstado viewEstado;
    private ViewDecano viewDecano;
    private JToolBar barraHerramienta = new JToolBar();
    private JButton bhEstado = new JButton();
    private JButton bhEscuela = new JButton();
    private JButton bhFactultad = new JButton();
    private JButton bhGrado = new JButton();
    private JButton bhAnno = new JButton();
    //private JButton bhUbigeo = new JButton();
    private JButton bhTipoDocumento = new JButton();
    private ViewEstado dialogEstado;
    private ViewTipoDocumento dialogTipoDocumento;
    private ViewEscuela dialogEscuela;
    private ViewAnnoCurso dialogAnnoCurso;
    private ViewFacultad dialogFactultad;
    private ViewGrado dialogGrado;
     private ViewProyecto viewProyecto;
     private About acerca=new About();
     private JDialog aboutInf;
    public ViewApplication() {
        construyendo();
    }

    private void construyendo() {
        bhEstado.setToolTipText("ESTADO");
        bhEstado.setIcon(ViewLogin.picture.getIconEstado32());
        barraHerramienta.add(bhEstado);
        bhEstado.addActionListener(actionListener);
        bhEscuela.setToolTipText("ESCUELA");
        bhEscuela.setIcon(ViewLogin.picture.getIconEscuela32());
        barraHerramienta.add(bhEscuela);
        bhFactultad.setToolTipText("FACULTAD");
        bhFactultad.setIcon(ViewLogin.picture.getIconFacultas32());
        barraHerramienta.add(bhFactultad);
        bhGrado.setToolTipText("GRADO");
        bhGrado.setIcon(ViewLogin.picture.getIconGrado32());
        barraHerramienta.add(bhGrado);
        bhAnno.setToolTipText("AÑO");
        bhAnno.setIcon(ViewLogin.picture.getIconAnno32());
        barraHerramienta.add(bhAnno);
        //   bhUbigeo.setToolTipText("UBIGEO");
        //  bhUbigeo.setIcon(ViewLogin.picture.getIconUbigeo32());
        //  barraHerramienta.add(bhUbigeo);
        bhTipoDocumento.setToolTipText("TIPO DE DOCUMENTO");
        bhTipoDocumento.setIcon(ViewLogin.picture.getIconTipodocumento32());
        barraHerramienta.add(bhTipoDocumento);
        menuProyectos.setIcon(ViewLogin.picture.getIconProject16());

        menuMantenimiento.setIcon(ViewLogin.picture.getIconMantenimientos16());
        Border borde = BorderFactory.createEtchedBorder();
        DateFormat fmt = new SimpleDateFormat("dd/MMMMM/yyyy hh:mm aaa", locale);
        String fecha = fmt.format(hoy);
        this.setContentPane(pnl);
        panelPrincipal.setLayout(new BorderLayout());
        contenidoScrollPane.getViewport().setLayout(new BorderLayout());
        menu.add(menuMantenimiento);
        menu.setBorder(borde);
        //  menuMantenimiento.add(menuItemUbigeo);
        //    menuItemUbigeo.setIcon(ViewLogin.picture.getIconUbigeo16());
        menuMantenimiento.add(menuItemEstado);
        menuItemEstado.setIcon(ViewLogin.picture.getIconEstado16());
        menuMantenimiento.add(menuItemEscuela);
        menuItemEscuela.setIcon(ViewLogin.picture.getIconEscuela16());
        menuMantenimiento.add(menuItemFactultad);
        menuItemFactultad.setIcon(ViewLogin.picture.getIconFacultas16());
        menuMantenimiento.add(menuItemGrado);
        menuItemGrado.setIcon(ViewLogin.picture.getIconGrado16());
        menuMantenimiento.add(menuItemAno);
        menuItemAno.setIcon(ViewLogin.picture.getIconAnno16());
        menuMantenimiento.add(menuItemTipoDocumento);
        menuItemTipoDocumento.setIcon(ViewLogin.picture.getIconTipodocumento16());

        menu.add(menuProyectos);

        menuItemProyecto.setIcon(ViewLogin.picture.getIconProyecto16());
        menuProyectos.add(menuItemProyecto);
        menuItemSolicitante.setIcon(ViewLogin.picture.getIconSolicitante16());
        menuProyectos.add(menuItemSolicitante);
        menuParticipante.setIcon(ViewLogin.picture.getIconPeople16());
        menu.add(menuParticipante);

        menuItemDecano.setIcon(ViewLogin.picture.getIconDocumento16());
        menuParticipante.add(menuItemDecano);

        menuItemDocente.setIcon(ViewLogin.picture.getIconDocente16());
        menuParticipante.add(menuItemDocente);
        menuItemEstudiante.setIcon(ViewLogin.picture.getIconEstudiante16());
        menuParticipante.add(menuItemEstudiante);
        menu.add(menuAbout);
        menuAbout.add(menuItemAbout);
        this.setJMenuBar(menu);
        arbol.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        arbol.setModel(arbolContenido);
        arbol.setCellRenderer(render);
        arbol.setOpaque(false);
        arbol.setRootVisible(false);
        arbol.setRowHeight(40);
        arbol.setExpandsSelectedPaths(true);
        arbol.setBackground(null);
        contenidoScrollPane.setViewportView(panelInicio);
        contenido.setContinuousLayout(true);
        contenido.setOneTouchExpandable(true);
        contenido.setDividerLocation(200);
        contenido.setPreferredSize(new Dimension(400, 200));
        Font fuente = new Font("Monospaced", Font.BOLD, 18);
        mensajeEstado.setFont(fuente);
        msgBienvenida.setFont(fuente);
        lblusuario.setText("Usuario" + "  " + fecha);
        lblusuario.setFont(fuente);

        barra.add(msgBienvenida);
        barra.add(lblusuario);
        barra.add(barraHerramienta);
        panelPrincipal.add(barra, "North");
        panelPrincipal.add(panelEstado, "South");
        panelPrincipal.add(contenido, "Center");
        panelEstado.add(mensajeEstado);
        Border etchedBdr = BorderFactory.createEtchedBorder();
        panelEstado.setBorder(etchedBdr);
        barra.setFloatable(false);
        pnl.setInnerComponent(panelPrincipal);
        this.setIconImage(ViewLogin.picture.getIcono());
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("SISTEMA DE GESTION DE PROYECCION SOCIAL");
        pack();
        this.setLocationRelativeTo(null);
        arbol.addTreeSelectionListener(listenArbol);
        menuItemDecano.addActionListener(actionListener);
        menuItemTipoDocumento.addActionListener(actionListener);
        menuItemEstado.addActionListener(actionListener);
        menuItemDocente.addActionListener(actionListener);
        menuItemEstudiante.addActionListener(actionListener);
        menuItemEstado.addActionListener(actionListener);
        bhTipoDocumento.addActionListener(actionListener);
        menuItemEscuela.addActionListener(actionListener);
        bhEscuela.addActionListener(actionListener);
        menuItemSolicitante.addActionListener(actionListener);
        menuItemProyecto.addActionListener(actionListener);
        menuItemFactultad.addActionListener(actionListener);
        menuItemAbout.addActionListener(actionListener);
        bhFactultad.addActionListener(actionListener);

        menuItemGrado.addActionListener(actionListener);
        bhGrado.addActionListener(actionListener);
        bhAnno.addActionListener(actionListener);
        menuItemAno.addActionListener(actionListener);
    }
    ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(menuItemDecano)) {
                try {
                    viewPanelMoldeDecano();
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
            } else if (e.getSource().equals(menuItemDocente)) {
                try {
                    viewPanelMoldeDocente();
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
            } else if (e.getSource().equals(menuItemEstudiante)) {
                try {
                    viewPanelMoldeEstudiante();
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
            } else if (e.getSource().equals(menuItemEstado)) {
                viewPanelEstado();
            } else if (e.getSource().equals(bhEstado)) {
                viewPanelEstado();
            } else if (e.getSource().equals(menuItemTipoDocumento)) {
                viewPanelTipoDocumento();
            } else if (e.getSource().equals(bhTipoDocumento)) {
                viewPanelTipoDocumento();
            } else if (e.getSource().equals(menuItemAno)) {
                viewPanelAnno();
            } else if (e.getSource().equals(bhAnno)) {
                viewPanelAnno();
            } else if (e.getSource().equals(menuItemEscuela)) {
                viewPanelEscuela();
            } else if (e.getSource().equals(bhEscuela)) {
                viewPanelEscuela();
            } else if (e.getSource().equals(menuItemFactultad)) {
                viewPanelFactultad();
            } else if (e.getSource().equals(bhFactultad)) {
                viewPanelFactultad();
            } else if (e.getSource().equals(menuItemGrado)) {
                viewPanelGrado();
            }else if(e.getSource().equals(menuItemAbout)){
                viewPanelAbout();
            } 
            else if (e.getSource().equals(bhGrado)) {
                viewPanelGrado();
            }else if(e.getSource().equals(menuItemSolicitante)){
                try {
                    viewPanelMoldeSolicitante();
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }else if(e.getSource().equals(menuItemProyecto)){
                try {                    
                    viewPanelMoldeProyecto();
                } catch (SQLException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }

        }
    };
    
    private void viewPanelAbout() {
        try {
            aboutInf.setVisible(true);
        } catch (Exception ex) {
            aboutInf = new JDialog();
            aboutInf.setTitle("Acerca del Software");
            aboutInf.getContentPane().add(acerca);
            aboutInf.pack();
            aboutInf.setLocationRelativeTo(null);
            aboutInf.setVisible(true);
        }
    }

    private void viewPanelEstado() {
        try {
            dialogEstado.setVisible(true);
        } catch (Exception ex) {
            dialogEstado = new ViewEstado();
            dialogEstado.setTitle("Mantenimiento de los Estados del Proyecto");
            dialogEstado.pack();
            dialogEstado.setLocationRelativeTo(null);
            dialogEstado.setVisible(true);
        }
    }

    private void viewPanelEscuela() {
        try {
            dialogEscuela.setVisible(true);
        } catch (Exception ex) {
            dialogEscuela = new ViewEscuela();
            dialogEscuela.setTitle("Mantenimiento de Escuela");
            dialogEscuela.pack();
            dialogEscuela.setLocationRelativeTo(null);
            dialogEscuela.setVisible(true);
        }
    }

    private void viewPanelGrado() {
        try {
            dialogGrado.setVisible(true);
        } catch (Exception ex) {
            dialogGrado = new ViewGrado();
            dialogGrado.setTitle("Mantenimiento de Grado");
            dialogGrado.pack();
            dialogGrado.setLocationRelativeTo(null);
            dialogGrado.setVisible(true);
        }
    }

    private void viewPanelFactultad() {
        try {
            dialogFactultad.setVisible(true);
        } catch (Exception ex) {
            dialogFactultad = new ViewFacultad();
            dialogFactultad.setTitle("Mantenimiento de Factultad");
            dialogFactultad.pack();
            dialogFactultad.setLocationRelativeTo(null);
            dialogFactultad.setVisible(true);
        }
    }

    private void viewPanelTipoDocumento() {
        try {
            dialogTipoDocumento.setVisible(true);
        } catch (Exception ex) {
            dialogTipoDocumento = new ViewTipoDocumento();
            dialogTipoDocumento.setTitle("Mantenimiento Tipo de Documento");
            dialogTipoDocumento.pack();
            dialogTipoDocumento.setLocationRelativeTo(null);
            dialogTipoDocumento.setVisible(true);
        }
    }

    private void viewPanelAnno() {
        try {
            dialogAnnoCurso.setVisible(true);
        } catch (Exception ex) {
            dialogAnnoCurso = new ViewAnnoCurso();
            dialogAnnoCurso.setTitle("Mantenimiento Año de Curso");
            dialogAnnoCurso.pack();
            dialogAnnoCurso.setLocationRelativeTo(null);
            dialogAnnoCurso.setVisible(true);
        }
    }

    private void viewPanelMoldeSolicitante() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(viewSolicitante.getMolde().getMolde());
        } catch (Exception ex) {
            viewSolicitante = new ViewSolicitante();
            contenidoScrollPane.setViewportView(viewSolicitante.getMolde().getMolde());
        }
    }

    private void viewPanelMoldeDecano() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(viewDecano.getMolde().getMolde());
            viewDecano.getModelDecano().setData(LogicDecano.listarDecano());
            viewDecano.getModelDecano().fireTableDataChanged();
            viewDecano.construirArbol();
        } catch (Exception ex) {
            viewDecano = new ViewDecano();
            contenidoScrollPane.setViewportView(viewDecano.getMolde().getMolde());
            viewDecano.getModelDecano().setData(LogicDecano.listarDecano());
            viewDecano.getModelDecano().fireTableDataChanged();
            viewDecano.construirArbol();
        }
    }

    private void viewPanelMoldeDocente() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(viewDocente.getMolde().getMolde());
            viewDocente.getFrmDocente().getPanelFacultad().actualizarTabla();
            viewDocente.getFrmDocente().getPanelEscuela().actualizarTablaEscuela();
        } catch (Exception ex) {
            viewDocente = new ViewDocente();
            contenidoScrollPane.setViewportView(viewDocente.getMolde().getMolde());
            try{
            viewDocente.getFrmDocente().getPanelFacultad().actualizarTabla();
            viewDocente.getFrmDocente().getPanelEscuela().actualizarTablaEscuela();
            }catch(Exception e){}
        }
    }

    private void viewPanelMoldeEstudiante() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(viewEstudiante.getMolde().getMolde());
            viewEstudiante.getFrmEstudiante().getPanelFacultad().actualizarTabla();
            viewEstudiante.getFrmEstudiante().getPanelEscuela().actualizarTablaEscuela();
        } catch (Exception ex) {
            viewEstudiante = new ViewEstudiante();
            contenidoScrollPane.setViewportView(viewEstudiante.getMolde().getMolde());
            try{
            viewEstudiante.getFrmEstudiante().getPanelFacultad().actualizarTabla();
            viewEstudiante.getFrmEstudiante().getPanelEscuela().actualizarTablaEscuela();
            }catch(Exception e){}
        }
    }

    private void viewPanelMoldeProyecto() throws SQLException {
        try {
            contenidoScrollPane.setViewportView(viewProyecto.getMolde().getMolde());            
            viewProyecto.getModelProyecto().setData(LogicProyecto.listarProyecto());
            viewProyecto.getModelProyecto().fireTableDataChanged();
        } catch (Exception ex) {
            viewProyecto = new ViewProyecto();
            contenidoScrollPane.setViewportView(viewProyecto.getMolde().getMolde());            
            viewProyecto.getModelProyecto().setData(LogicProyecto.listarProyecto());
            viewProyecto.getModelProyecto().fireTableDataChanged();
        }
    }
    
    TreeSelectionListener listenArbol = new TreeSelectionListener() {

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            TreePath tp = e.getPath();
            TreeEntry nodo = (TreeEntry) tp.getLastPathComponent();
            if (nodo.getIdentificadorUnico().equals("MANTSOL")) {
                try {
                    viewPanelMoldeSolicitante();
                } catch (Exception ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("MANTDOCENTE")) {
                try {
                    viewPanelMoldeDocente();
                } catch (Exception ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("MANTEST")) {
                try {
                    viewPanelMoldeEstudiante();
                } catch (Exception ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("MANTDEC")) {
                try {
                    viewPanelMoldeDecano();
                } catch (Exception ex) {
                    System.err.print(ex.getCause());
                }
            } else if (nodo.getIdentificadorUnico().equals("MANTPROY")) {
                try {
                    viewPanelMoldeProyecto();
                } catch (Exception ex) {
                    System.err.print(ex.getCause());
                }
            }

        }
    };
}
