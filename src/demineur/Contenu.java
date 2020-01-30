/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author kemga
 */
public class Contenu {
    
    private final JLabel labelScore = new JLabel();
    private final JLabel labelImage = new JLabel(new ImageIcon(".\\images\\nouveauJeu.gif"));
    private final JLabel labelTemps = new JLabel("Temps");
    private final JLabel labelBasPage = new JLabel("Copyright © 2017, Tous droits réservés", JLabel.CENTER);
    
    private final ArrayList<ArrayList<JButton>> buttons = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> mines = new ArrayList<>();
    private final ArrayList<ImageIcon> images = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> nbrMinesParCase = new ArrayList<>();
    
    private int drapeaux[][];
    
    public Contenu(){
        this.labelScore.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
        this.labelScore.setOpaque(true);
        this.labelScore.setBackground(Color.BLACK);
        this.labelScore.setForeground(Color.RED);
        this.labelScore.setHorizontalAlignment(JLabel.CENTER);
        
        this.labelImage.setOpaque(true);
        
        this.labelTemps.setFont(new Font("DigtalFont.TTF", Font.BOLD, 14));
        this.labelTemps.setOpaque(true);
        this.labelTemps.setBackground(Color.BLACK);
        this.labelTemps.setForeground(Color.RED);
        this.labelTemps.setHorizontalAlignment(JLabel.CENTER);
        
    }
    
    // Création des boutons
    public void createButtons(Demineur d){
        this.buttons.removeAll(this.buttons);
        for (int i = 0; i < d.getNbrLignes(); i++) {
            this.buttons.add(new ArrayList<>());
            for (int j = 0; j < d.getNbrColones(); j++) {
                this.buttons.get(i).add(new JButton());
            }
        }
    }

    public JLabel getLabelScore() {
        return labelScore;
    }

    public JLabel getLabelImage() {
        return labelImage;
    }

    public JLabel getLabelBasPage() {
        return labelBasPage;
    }

    public ArrayList<ArrayList<JButton>> getButtons() {
        return buttons;
    }

    public ArrayList<ImageIcon> getImages() {
        return images;
    }

    public ArrayList<ArrayList<Integer>> getMines() {
        return mines;
    }

    public void setMines(ArrayList<ArrayList<Integer>> mines) {
        this.mines = mines;
    }

    public int[][] getDrapeaux() {
        return drapeaux;
    }

    public void setDrapeaux(int[][] drapeaux) {
        this.drapeaux = drapeaux;
    }
    
    public int getValeurDrapeaux(int i, int j) {
        return this.drapeaux[i][j];
    }
    
    public void setValeurDrapeaux(int i, int j, int valeur) {
        this.drapeaux[i][j] = valeur;
    }

    public ArrayList<ArrayList<Integer>> getNbrMinesParCase() {
        return nbrMinesParCase;
    }

    public void setNbrMinesParCase(ArrayList<ArrayList<Integer>> nbrMinesParCase) {
        this.nbrMinesParCase = nbrMinesParCase;
    }

    public JLabel getLabelTemps() {
        return labelTemps;
    }
    
}
