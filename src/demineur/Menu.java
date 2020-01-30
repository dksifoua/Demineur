/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

/**
 *
 * @author kemga
 */
public class Menu{
    
    private final JMenuBar barreMenus = new JMenuBar();
    
    private final JMenu menuPartie = new JMenu("Partie");
    private final JMenu menuAide = new JMenu("Aide");
    private final JMenu itemNvllePartie = new JMenu("Nouvelle partie");
    
    private final JMenuItem itemQuitter = new JMenuItem("Quitter");
    private final JMenuItem itemReglesJeu = new JMenuItem("Règles du jeu");
    private final JMenuItem itemAPropos = new JMenuItem("A propos");
    
    private final ButtonGroup groupe = new ButtonGroup();
    
    private final JRadioButton debutant = new JRadioButton("Débutant");
    private final JRadioButton intermediaire = new JRadioButton("Intermédiaire", true);
    private final JRadioButton expert = new JRadioButton("Expert");
    
    public Menu(Demineur d){
        this.groupe.add(this.debutant);
        this.groupe.add(this.intermediaire);
        this.groupe.add(this.expert);
        
        this.itemNvllePartie.add(debutant);
        this.itemNvllePartie.add(intermediaire);
        this.itemNvllePartie.add(expert);
        
        this.menuPartie.add(this.itemNvllePartie);
        this.menuPartie.add(this.itemQuitter);
        this.menuAide.add(this.itemReglesJeu);
        this.menuAide.add(this.itemAPropos);
        
        this.barreMenus.add(this.menuPartie);
        this.barreMenus.add(this.menuAide);
        
        d.setJMenuBar(this.barreMenus);
        
    }

    public JMenuBar getBarreMenus() {
        return barreMenus;
    }

    public JMenu getMenuPartie() {
        return menuPartie;
    }

    public JMenu getMenuAide() {
        return menuAide;
    }

    public JMenu getItemNvllePartie() {
        return itemNvllePartie;
    }

    public JMenuItem getItemQuitter() {
        return itemQuitter;
    }

    public JMenuItem getItemReglesJeu() {
        return itemReglesJeu;
    }

    public JMenuItem getItemAPropos() {
        return itemAPropos;
    }

    public JRadioButton getDebutant() {
        return debutant;
    }

    public JRadioButton getIntermediaire() {
        return intermediaire;
    }

    public JRadioButton getExpert() {
        return expert;
    }
    
}
