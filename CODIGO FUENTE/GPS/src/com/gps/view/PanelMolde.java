/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gps.view;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author Administrador
 */
public class PanelMolde {
private JPanel molde = new JPanel();
    private JPanel panelbarra = new JPanel();
    private JPanel paneldata = new JPanel();
        private JPanel panelarbol = new JPanel();
        private JPanel paneltabla = new JPanel();
    private JPanel panelformulario = new JPanel();
        private JPanel panelfrm = new JPanel();
        private JPanel panelinf = new JPanel();
        
  public PanelMolde () {
      initComponents();
           
  }     

  private void initComponents(){
      molde.setLayout(new BorderLayout());
      molde.add(panelbarra,BorderLayout.NORTH);
      molde.add(paneldata,BorderLayout.CENTER);
      molde.add(panelformulario,BorderLayout.SOUTH);
      paneldata.setLayout(new BorderLayout());
      paneldata.add(panelarbol,BorderLayout.WEST);
      paneldata.add(paneltabla,BorderLayout.CENTER);
      panelformulario.setLayout(new BorderLayout());
      panelformulario.add(panelfrm,BorderLayout.WEST);
      panelformulario.add(panelinf,BorderLayout.CENTER);
      Border etchedBdr = BorderFactory.createEtchedBorder();
      //molde.setBorder(etchedBdr);
      panelbarra.setBorder(etchedBdr);
      paneldata.setBorder(etchedBdr);
      //panelformulario.setBorder(etchedBdr);
      panelarbol.setBorder(etchedBdr);
      //paneltabla.setBorder(etchedBdr);
      panelfrm.setBorder(etchedBdr);
      panelinf.setBorder(etchedBdr);
  }

    public JPanel getMolde() {
        return molde;
    }

    public JPanel getPanelarbol() {
        return panelarbol;
    }

    public JPanel getPanelbarra() {
        return panelbarra;
    }

    public JPanel getPaneldata() {
        return paneldata;
    }

    public JPanel getPanelformulario() {
        return panelformulario;
    }

    public JPanel getPanelfrm() {
        return panelfrm;
    }

    public JPanel getPanelinf() {
        return panelinf;
    }

    public JPanel getPaneltabla() {
        return paneltabla;
    }
    
    

}
