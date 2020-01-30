/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author kemga
 */
public class Panel {
    
    private int longueurEcran;
    private int hauteurEcran;
    
    private final JPanel panelPrinc = new JPanel();
    private final JPanel panelHaut = new JPanel();
        private final JPanel panelMilieu = new JPanel();
    private final JPanel panelBas = new JPanel();
    
    private final Contenu contenu = new Contenu();
    
    public Panel(){

        this.panelPrinc.add(panelHaut, BorderLayout.NORTH);
        this.panelPrinc.add(panelMilieu, BorderLayout.CENTER);
        this.panelPrinc.add(panelBas, BorderLayout.SOUTH);
        this.panelPrinc.setLayout(new BorderLayout());
        
    }

    public JPanel getPanelPrinc() {
        return panelPrinc;
    }

    public JPanel getPanelHaut() {
        return panelHaut;
    }

    public JPanel getPanelMilieu() {
        return panelMilieu;
    }

    public JPanel getPanelBas() {
        return panelBas;
    }

    public Contenu getContenu() {
        return contenu;
    }

    public int getLongueurEcran() {
        return longueurEcran;
    }

    public void setLongueurEcran(int longueurEcran) {
        this.longueurEcran = longueurEcran;
    }

    public int getHauteurEcran() {
        return hauteurEcran;
    }

    public void setHauteurEcran(int hauteurEcran) {
        this.hauteurEcran = hauteurEcran;
    }
    
}
