/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;



/**
 *
 * @author kemga
 */
public class Fonction{
    
    private int lig;
    private int col;
    private int isFirst = 0;
    private final JButton b = new JButton();
    
    public Fonction(){
        
    }
    
    public final void setDrapeau(Demineur d){
        d.getPanel().getContenu().setDrapeaux(new int[d.getNbrLignes()][d.getNbrColones()]);
        for (int i = 0; i < d.getNbrLignes(); i++) {
            for (int j = 0; j < d.getNbrColones(); j++) {   
                d.getPanel().getContenu().setValeurDrapeaux(i, j, 0);
            }
        }
    }
    
    public final void setMines(Demineur d){
        ArrayList<Integer> numeroPlateau = new ArrayList<>();
        d.getPanel().getContenu().getMines().removeAll(d.getPanel().getContenu().getMines());
        Random r = new Random();
        int var, ligne, colone, numero = 0, cmpt = 0;
        for (int i = 0; i < d.getNbrLignes(); i++) {
            d.getPanel().getContenu().getMines().add(new ArrayList<>());
            for (int j = 0; j < d.getNbrColones(); j++) {
                d.getPanel().getContenu().getMines().get(i).add(0);
                numeroPlateau.add(numero);
                numero++;
            }
        }
        while(cmpt < d.getNbrMines()){
            var = r.nextInt(numeroPlateau.size());
            ligne = numeroPlateau.get(var) / d.getNbrColones();
            colone = numeroPlateau.get(var) % d.getNbrColones();
            d.getPanel().getContenu().getMines().get(ligne).set(colone, -1);
            numeroPlateau.remove(var);
            cmpt++;
        }
        for(int i = 0; i < d.getNbrLignes();i ++){
            d.getPanel().getContenu().getNbrMinesParCase().add(new ArrayList<>());
            for(int j = 0; j < d.getNbrColones(); j++)
                d.getPanel().getContenu().getNbrMinesParCase().get(i).add(-1);
        }
        this.calculNbrMinesParCase(d);
    }
    
    public final void loadPictures(Demineur d){
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\1.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\2.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\3.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\4.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\5.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\6.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\7.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\8.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\drapeau.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\erreur.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\mine.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\mine.png"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\nouveauJeu.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\press.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\logo.gif"));
        d.getPanel().getContenu().getImages().add(new ImageIcon(".\\images\\inter.gif"));
    }
    
    public void whereClick(MouseEvent e, Demineur d){
        for(int i = 0; i < d.getNbrLignes(); i++){
            for(int j = 0; j < d.getNbrColones(); j++){
                int a = d.getNbrColones() * i + j;
                if(e.getSource().equals(d.getPanel().getPanelMilieu().getComponent(a))){
                    this.lig = i;
                    this.col = j;
                }
            }
        }
    }
    
    public void affichDrapeau(Demineur d, int l, int c){
        //int pos = d.getNbrColones() * l + c;
        switch (d.getPanel().getContenu().getValeurDrapeaux(l, c)) {
            case 0:
                d.getPanel().getContenu().getButtons().get(l).get(c).setIcon(d.getPanel().getContenu().getImages().get(8));
                d.getPanel().getContenu().setValeurDrapeaux(l, c, 1);
                d.setScore(d.getScore() - 1);
                d.getPanel().getContenu().getLabelScore().setText(Integer.toString(d.getScore()));
                break;
            case 1:
                d.getPanel().getContenu().getButtons().get(l).get(c).setIcon(d.getPanel().getContenu().getImages().get(15));
                d.getPanel().getContenu().setValeurDrapeaux(l, c, 2);
                d.setScore(d.getScore() + 1);
                d.getPanel().getContenu().getLabelScore().setText(Integer.toString(d.getScore()));
                break;
            default:
                d.getPanel().getContenu().getButtons().get(l).get(c).setIcon(null);
                d.getPanel().getContenu().setValeurDrapeaux(l, c, 0);
                break;
        }
        d.getPanel().getContenu().getLabelScore().revalidate();
    }
    
    public void newGame(Demineur d){
        d.getPanel().getPanelMilieu().removeAll();
        for(int i = 0; i < d.getNbrLignes(); i++){
            for(int j = 0; j < d.getNbrColones(); j++){
                d.getPanel().getPanelMilieu().add(d.getPanel().getContenu().getButtons().get(i).get(j));
                d.getPanel().getContenu().getButtons().get(i).get(j).addMouseListener(new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        d.getFonction().whereClick(e, d);
                        if(SwingUtilities.isLeftMouseButton(e)){
                            if(d.getFonction().getIsFirst() == 0){
                                d.getFonction().setIsFirst(1);
                                //Demineur.this.monThread = new MonThread(Demineur.this);
                            }
                            if(d.getPanel().getContenu().getMines().get(d.getFonction().getLig()).get(d.getFonction().getCol()).equals(-1))
                                d.getFonction().affichMines(d, d.getFonction().getLig(), d.getFonction().getCol());
                            else{
                                d.getFonction().affichNbrMines(d, d.getFonction().getLig(), d.getFonction().getCol());
                                if(d.getFonction().win(d) == 1)
                                    JOptionPane.showMessageDialog(null, "Félicitations\n"
                                        + "\n"
                                        + "Vous avez gagné !\n", "Bravo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        if(SwingUtilities.isRightMouseButton(e)){
                            d.getFonction().affichDrapeau(d, d.getFonction().getLig(), d.getFonction().getCol());
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        d.getPanel().getContenu().getLabelImage().setIcon(new ImageIcon(".\\images\\erreur.gif"));
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        d.getPanel().getContenu().getLabelImage().setIcon(new ImageIcon(".\\images\\nouveauJeu.gif"));
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
            }
        }
        d.getPanel().getPanelMilieu().revalidate();
    }
    
    public void setWindows(Demineur d){
        switch(d.getNiveau()){
            case 1 :
                d.setNbrLignes(9);
                d.setNbrColones(9);
                d.setNbrMines(10);
                d.setScore(d.getNbrMines());
                d.getPanel().setLongueurEcran(260);
                d.getPanel().setHauteurEcran(350);
                d.getPanel().getPanelHaut().setSize(d.getPanel().getLongueurEcran() - 8, 50);
                d.getPanel().getPanelMilieu().setLayout(new GridLayout(d.getNbrLignes(), d.getNbrColones()));
                d.getPanel().getPanelMilieu().setSize(200, 200);
                d.getPanel().getPanelMilieu().setLocation(28, 60);   
                d.getPanel().getPanelBas().setSize(260, 30);
                d.getPanel().getPanelBas().setLocation(0, 265);
                break;
            case 3 :
                d.setNbrLignes(16);
                d.setNbrColones(30);
                d.setNbrMines(99);
                d.setScore(d.getNbrMines());
                d.getPanel().setLongueurEcran(662);
                d.getPanel().setHauteurEcran(500);
                d.getPanel().getPanelHaut().setSize(d.getPanel().getLongueurEcran() - 8, 50);
                d.getPanel().getPanelMilieu().setLayout(new GridLayout(d.getNbrLignes(), d.getNbrColones()));
                d.getPanel().getPanelMilieu().setSize(657, 350);
                d.getPanel().getPanelMilieu().setLocation(0, 60); 
                d.getPanel().getPanelBas().setSize(662, 30);
                d.getPanel().getPanelBas().setLocation(0, 415);  
                break;
            default:
                d.setNbrLignes(16);
                d.setNbrColones(16);
                d.setNbrMines(40);
                d.setScore(d.getNbrMines());
                d.getPanel().setLongueurEcran(355);
                d.getPanel().setHauteurEcran(500);
                d.getPanel().getPanelHaut().setSize(d.getPanel().getLongueurEcran() - 8, 50);
                d.getPanel().getPanelMilieu().setLayout(new GridLayout(d.getNbrLignes(), d.getNbrColones()));
                d.getPanel().getPanelMilieu().setSize(350, 350);
                d.getPanel().getPanelMilieu().setLocation(0, 60); 
                d.getPanel().getPanelBas().setSize(355, 30);
                d.getPanel().getPanelBas().setLocation(0, 415);  
        }
        d.getPanel().getContenu().getLabelScore().setText(Integer.toString(d.getScore()));
        
        d.setSize(d.getPanel().getLongueurEcran(), d.getPanel().getHauteurEcran());
        d.getPanel().getPanelPrinc().setSize(d.getPanel().getLongueurEcran(), d.getPanel().getHauteurEcran());
        
        d.getPanel().getPanelHaut().setLayout(null);
        d.getPanel().getContenu().getLabelScore().setSize(d.getPanel().getLongueurEcran() / 5, 46);
        d.getPanel().getContenu().getLabelScore().setLocation(2, 2);
        d.getPanel().getContenu().getLabelImage().setSize(3 * d.getPanel().getLongueurEcran() / 5, 46);
        d.getPanel().getContenu().getLabelImage().setLocation(d.getPanel().getLongueurEcran() / 5 + 2, 2);
        d.getPanel().getContenu().getLabelTemps().setSize(d.getPanel().getLongueurEcran() / 5 - 10, 46);
        d.getPanel().getContenu().getLabelTemps().setLocation(4 * d.getPanel().getLongueurEcran() / 5 + 2, 2);
        d.getPanel().getPanelHaut().add(d.getPanel().getContenu().getLabelScore());
        d.getPanel().getPanelHaut().add(d.getPanel().getContenu().getLabelImage());
        d.getPanel().getPanelHaut().add(d.getPanel().getContenu().getLabelTemps());
        d.getPanel().getPanelHaut().setBorder(BorderFactory.createLoweredBevelBorder());
        
        d.getPanel().getPanelBas().setLayout(new BorderLayout());
        d.getPanel().getPanelBas().add(d.getPanel().getContenu().getLabelBasPage(), BorderLayout.CENTER);
        
    }

    public void affichMines(Demineur d, int l, int c){
        int pos = d.getNbrColones() * l + c;
        for(int i = 0; i < d.getNbrLignes(); i++){
            for(int j = 0; j < d.getNbrColones(); j++){
                int a = d.getNbrColones() * i + j;
                if(d.getPanel().getContenu().getMines().get(i).get(j).equals(-1)){
                    JLabel bombe = new JLabel();
                    if(a == pos)
                        bombe.setIcon(d.getPanel().getContenu().getImages().get(10));
                    else
                        bombe.setIcon(d.getPanel().getContenu().getImages().get(11));
                    bombe.setOpaque(true);
                    bombe.setVisible(true);
                    d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(a));
                    d.getPanel().getPanelMilieu().add(bombe, a);
                    d.getPanel().getPanelMilieu().revalidate();
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Désolé,\n"
                    + "\n"
                    + "Vous avez perdu !", "Dommage", JOptionPane.ERROR_MESSAGE);
    }
    
    public void calculNbrMinesParCase(Demineur d){
        for(int i = 0; i < d.getNbrLignes(); i++){
            for(int j = 0; j < d.getNbrColones(); j++){
                if(d.getPanel().getContenu().getMines().get(i).get(j).equals(-1))
                    d.getPanel().getContenu().getNbrMinesParCase().get(i).set(j, -1);
                else
                    d.getPanel().getContenu().getNbrMinesParCase().get(i).set(j, this.calculNbrMinesAutour(d, i, j));
            }
        }
        for(int i = 0; i < d.getNbrLignes();i ++){
            for(int j = 0; j < d.getNbrColones(); j++){
                System.out.print(d.getPanel().getContenu().getNbrMinesParCase().get(i).get(j) + " ");
            }
            System.out.println("");
        }
    }
    
    public int calculNbrMinesAutour(Demineur d, int l, int c){
        int nbrMinestrouv = 0;
        if(l == 0 && c == 0){
            if(d.getPanel().getContenu().getMines().get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c + 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
        }else if(l == 0 && c > 0 && c < d.getNbrColones() - 1){
            if(d.getPanel().getContenu().getMines().get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c + 1).equals(-1)) nbrMinestrouv++;
        }else if(l == 0 && c == d.getNbrColones() - 1){
            if(d.getPanel().getContenu().getMines().get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
        }else if(l > 0 && l < d.getNbrLignes()- 1 && c == d.getNbrColones() - 1){
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
        }else if(l == d.getNbrLignes() - 1 && c == d.getNbrColones() - 1){
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
        }else if(l == d.getNbrLignes() - 1 && c > 0 && c < d.getNbrColones() - 1){
            if(d.getPanel().getContenu().getMines().get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c + 1).equals(-1)) nbrMinestrouv++;
        }else if(l == d.getNbrLignes() - 1 && c == 0){
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c + 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
        }else if(l > 0 && l < d.getNbrLignes() - 1 && c == 0){
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c + 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c + 1).equals(-1)) nbrMinestrouv++;
        }else{
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l - 1).get(c + 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c - 1).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
            if(d.getPanel().getContenu().getMines().get(l + 1).get(c + 1).equals(-1)) nbrMinestrouv++;
        }
        return nbrMinestrouv;
    }
    
    public JLabel setJLabel(Demineur d, int l, int c){
        JLabel label;
        switch(d.getPanel().getContenu().getNbrMinesParCase().get(l).get(c)){
            case 1 :
                label = new JLabel(d.getPanel().getContenu().getImages().get(0), JLabel.CENTER);
                break;
            case 2 :
                label = new JLabel(d.getPanel().getContenu().getImages().get(1), JLabel.CENTER);
                break;
            case 3 :
                label = new JLabel(d.getPanel().getContenu().getImages().get(2), JLabel.CENTER);
                break;
            case 4 :
                label = new JLabel(d.getPanel().getContenu().getImages().get(3), JLabel.CENTER);
                break;
            case 5 :
                label = new JLabel(d.getPanel().getContenu().getImages().get(4), JLabel.CENTER);
                break;
            case 6 :
                label = new JLabel(d.getPanel().getContenu().getImages().get(5), JLabel.CENTER);
                break;
            case 7 :
                label = new JLabel(d.getPanel().getContenu().getImages().get(6), JLabel.CENTER);
                break;
            default:
                label = new JLabel(d.getPanel().getContenu().getImages().get(7), JLabel.CENTER);
                break;
        }
        return label;
    }
    
    public void affichNbrMines(Demineur d, int l, int c){
        if(d.getPanel().getContenu().getNbrMinesParCase().get(l).get(c).equals(0)){
            this.affichNbrMinesAutour(d, l, c);
        }else{
            int pos = d.getNbrColones() * l + c;
            JLabel bombe = this.setJLabel(d, l, c);
            bombe.setOpaque(true);
            bombe.setVisible(true);
            d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(pos));
            d.getPanel().getPanelMilieu().add(bombe, pos);
            d.getPanel().getPanelMilieu().revalidate();
        }
    }
    
    public void affichNbrMinesAutour(Demineur d, int l, int c){
        if(d.getPanel().getContenu().getNbrMinesParCase().get(l).get(c).equals(0)){
            if(d.getPanel().getPanelMilieu().getComponent(d.getNbrColones() * l + c).getClass().equals(this.b.getClass())){
                int pos = d.getNbrColones() * l + c;
                JLabel bombe = new JLabel("");
                bombe.setOpaque(true);
                bombe.setVisible(true);
                d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(pos));
                d.getPanel().getPanelMilieu().add(bombe, pos);
                d.getPanel().getPanelMilieu().revalidate();
                // En haut
                if(l - 1 >= 0 && d.getPanel().getPanelMilieu().getComponent(d.getNbrColones() * (l - 1) + c).getClass().equals(this.b.getClass())){
                    if(d.getPanel().getContenu().getNbrMinesParCase().get(l - 1).get(c).equals(0)){
                        this.affichNbrMinesAutour(d, l - 1, c);
                    }else{
                        pos = d.getNbrColones() * (l - 1) + c;
                        JLabel nbr = this.setJLabel(d, l - 1, c);
                        nbr.setOpaque(true);
                        nbr.setVisible(true);
                        d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(pos));
                        d.getPanel().getPanelMilieu().add(nbr, pos);
                        d.getPanel().getPanelMilieu().revalidate();
                    }
                }
                // En bas
                if(l + 1 <= d.getNbrLignes() - 1 && d.getPanel().getPanelMilieu().getComponent(d.getNbrColones() * (l + 1) + c).getClass().equals(this.b.getClass())){
                    if(d.getPanel().getContenu().getNbrMinesParCase().get(l + 1).get(c).equals(0)){
                        this.affichNbrMinesAutour(d, l + 1, c);
                    }else{
                        pos = d.getNbrColones() * (l + 1) + c;
                        JLabel nbr = this.setJLabel(d, l + 1, c);
                        nbr.setOpaque(true);
                        nbr.setVisible(true);
                        d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(pos));
                        d.getPanel().getPanelMilieu().add(nbr, pos);
                        d.getPanel().getPanelMilieu().revalidate();
                    }
                }
                // A droite
                if(c + 1 <= d.getNbrColones() - 1 && d.getPanel().getPanelMilieu().getComponent(d.getNbrColones() * l + (c + 1)).getClass().equals(this.b.getClass())){
                    if(d.getPanel().getContenu().getNbrMinesParCase().get(l).get(c + 1).equals(0)){
                        this.affichNbrMinesAutour(d, l, c + 1);
                    }else{
                        pos = d.getNbrColones() * l + (c + 1);
                        JLabel nbr = this.setJLabel(d, l, c + 1);
                        nbr.setOpaque(true);
                        nbr.setVisible(true);
                        d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(pos));
                        d.getPanel().getPanelMilieu().add(nbr, pos);
                        d.getPanel().getPanelMilieu().revalidate();
                    }
                }
                // A gauche
                if(c - 1 >= 0 && d.getPanel().getPanelMilieu().getComponent(d.getNbrColones() * l + (c - 1)).getClass().equals(this.b.getClass())){
                    if(d.getPanel().getContenu().getNbrMinesParCase().get(l).get(c - 1).equals(0)){
                        this.affichNbrMinesAutour(d, l, c - 1);
                    }else{
                        pos = d.getNbrColones() * l + (c - 1);
                        JLabel nbr = this.setJLabel(d, l, c - 1);
                        nbr.setOpaque(true);
                        nbr.setVisible(true);
                        d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(pos));
                        d.getPanel().getPanelMilieu().add(nbr, pos);
                        d.getPanel().getPanelMilieu().revalidate();
                    }
                }
                // En haut à gauche
                if(l - 1 >= 0 && c - 1 >= 0 && d.getPanel().getPanelMilieu().getComponent(d.getNbrColones() * (l - 1) + (c - 1)).getClass().equals(this.b.getClass())){
                    if(d.getPanel().getContenu().getNbrMinesParCase().get(l - 1).get(c - 1).equals(0)){
                        this.affichNbrMinesAutour(d, l - 1, c - 1);
                    }else{
                        pos = d.getNbrColones() * (l - 1) + (c - 1);
                        JLabel nbr = this.setJLabel(d, l - 1, c - 1);
                        nbr.setOpaque(true);
                        nbr.setVisible(true);
                        d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(pos));
                        d.getPanel().getPanelMilieu().add(nbr, pos);
                        d.getPanel().getPanelMilieu().revalidate();
                    }
                }
                // En haut à droite
                if(l - 1 >= 0 && c + 1 <= d.getNbrColones() - 1 && d.getPanel().getPanelMilieu().getComponent(d.getNbrColones() * (l - 1) + (c + 1)).getClass().equals(this.b.getClass())){
                    if(d.getPanel().getContenu().getNbrMinesParCase().get(l - 1).get(c + 1).equals(0)){
                        this.affichNbrMinesAutour(d, l - 1, c + 1);
                    }else{
                        pos = d.getNbrColones() * (l - 1) + (c + 1);
                        JLabel nbr = this.setJLabel(d, l - 1, c + 1);
                        nbr.setOpaque(true);
                        nbr.setVisible(true);
                        d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(pos));
                        d.getPanel().getPanelMilieu().add(nbr, pos);
                        d.getPanel().getPanelMilieu().revalidate();
                    }
                }
                // En bas à gauche
                if(l + 1 <= d.getNbrLignes() - 1 && c - 1 >= 0 && d.getPanel().getPanelMilieu().getComponent(d.getNbrColones() * (l + 1) + (c - 1)).getClass().equals(this.b.getClass())){
                    if(d.getPanel().getContenu().getNbrMinesParCase().get(l + 1).get(c - 1).equals(0)){
                        this.affichNbrMinesAutour(d, l + 1, c - 1);
                    }else{
                        pos = d.getNbrColones() * (l + 1) + (c - 1);
                        JLabel nbr = this.setJLabel(d, l + 1, c - 1);
                        nbr.setOpaque(true);
                        nbr.setVisible(true);
                        d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(pos));
                        d.getPanel().getPanelMilieu().add(nbr, pos);
                        d.getPanel().getPanelMilieu().revalidate();
                    }
                }
                // En bas à droite
                if(l + 1 <= d.getNbrLignes() - 1 && c + 1 <= d.getNbrColones() - 1 && d.getPanel().getPanelMilieu().getComponent(d.getNbrColones() * (l + 1) + (c + 1)).getClass().equals(this.b.getClass())){
                    if(d.getPanel().getContenu().getNbrMinesParCase().get(l + 1).get(c + 1).equals(0)){
                        this.affichNbrMinesAutour(d, l + 1, c + 1);
                    }else{
                        pos = d.getNbrColones() * (l + 1) + (c + 1);
                        JLabel nbr = this.setJLabel(d, l + 1, c + 1);
                        nbr.setOpaque(true);
                        nbr.setVisible(true);
                        d.getPanel().getPanelMilieu().remove(d.getPanel().getPanelMilieu().getComponent(pos));
                        d.getPanel().getPanelMilieu().add(nbr, pos);
                        d.getPanel().getPanelMilieu().revalidate();
                    }
                }
            }
        }
    }
    
    public int win(Demineur d){
        int cmpt = 0;
        for(int i = 0; i < d.getNbrLignes(); i++){
            for(int j = 0; j < d.getNbrColones(); j++){
                if(d.getPanel().getPanelMilieu().getComponent(d.getNbrColones() * i + j).getClass().equals(this.b.getClass())){
                    cmpt++;
                }
            }
        }
        if(cmpt == d.getNbrMines())
            return 1;
        else
            return 0;
    }
    
    public int getLig() {
        return lig;
    }

    public void setLig(int lig) {
        this.lig = lig;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public JButton getB() {
        return b;
    }

    public int getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(int isFirst) {
        this.isFirst = isFirst;
    }
    
}
