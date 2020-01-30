/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author kemga
 */
public class Demineur extends JFrame implements ActionListener{

    /**
     * @param args the command line arguments
     */
    
    private int nbrLignes;
    private int nbrColones;
    private int nbrMines;
    private int niveau;
    private int score;
    
    private Menu menu;
    private final Panel panel = new Panel();
    private final Fonction fonction = new Fonction();
    private MonThread monThread;
    
    public Demineur(){
        initUI();
    }
    
    public final void initUI(){
        // Le titre et le logo
        this.setTitle("Démineur");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images\\logo.jpg"));
        
        // Le menu
        menu = new Menu(this);
        this.menu.getDebutant().addActionListener(this);
        this.menu.getIntermediaire().addActionListener(this);
        this.menu.getExpert().addActionListener(this);
        this.menu.getItemNvllePartie().addActionListener(this);
        this.menu.getItemQuitter().addActionListener(this);
        this.menu.getItemReglesJeu().addActionListener(this);
        this.menu.getItemAPropos().addActionListener(this);
        
        // La fenêtre
        fonction.setWindows(this);
        
        this.getPanel().getContenu().getLabelImage().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Demineur.this.setBoutons();
                Demineur.this.fonction.newGame(Demineur.this);
                Demineur.this.fonction.setMines(Demineur.this);
                Demineur.this.fonction.setDrapeau(Demineur.this);
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
        });
        
        // Les boutons
        this.setBoutons();
        
        // Les initialisations
        this.fonction.setMines(this);
        this.fonction.setDrapeau(this);
        this.fonction.loadPictures(this);
        
        // La fenêtre
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(this.panel.getPanelPrinc());
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public void setBoutons(){
        this.panel.getContenu().getButtons().removeAll(this.panel.getContenu().getButtons());
        this.panel.getContenu().createButtons(this);
        this.panel.getPanelMilieu().removeAll();
        for(int i = 0; i < this.nbrLignes; i++){
            for(int j = 0; j < this.nbrColones; j++){
                this.panel.getPanelMilieu().add(this.panel.getContenu().getButtons().get(i).get(j));
                this.panel.getContenu().getButtons().get(i).get(j).addMouseListener(new MouseListener(){
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        Demineur.this.fonction.whereClick(e, Demineur.this);
                        if(SwingUtilities.isLeftMouseButton(e)){
                            if(Demineur.this.fonction.getIsFirst() == 0){
                                Demineur.this.fonction.setIsFirst(1);
                            }
                            if(Demineur.this.panel.getContenu().getMines().get(Demineur.this.fonction.getLig()).get(Demineur.this.fonction.getCol()).equals(-1))
                                Demineur.this.fonction.affichMines(Demineur.this, Demineur.this.fonction.getLig(), Demineur.this.fonction.getCol());
                            else{
                                Demineur.this.fonction.affichNbrMines(Demineur.this, Demineur.this.fonction.getLig(), Demineur.this.fonction.getCol());
                                if(Demineur.this.fonction.win(Demineur.this) == 1)
                                    JOptionPane.showMessageDialog(null, "Félicitations\n"
                                        + "\n"
                                        + "Vous avez gagné !\n", "Bravo", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                        if(SwingUtilities.isRightMouseButton(e)){
                            Demineur.this.fonction.affichDrapeau(Demineur.this, Demineur.this.fonction.getLig(), Demineur.this.fonction.getCol());
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        Demineur.this.getPanel().getContenu().getLabelImage().setIcon(new ImageIcon(".\\images\\erreur.gif"));
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        Demineur.this.getPanel().getContenu().getLabelImage().setIcon(new ImageIcon(".\\images\\nouveauJeu.gif"));
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
    }
    
    public static void main(String[] args){
        // TODO code application logic here
        Demineur demineur = new Demineur();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == this.menu.getDebutant() && this.niveau != 1){
            this.niveau = 1;
            fonction.setWindows(this);
            this.setBoutons();
            this.fonction.setMines(this);
            this.fonction.setDrapeau(this);
        }
        if(e.getSource() == this.menu.getIntermediaire() && this.niveau != 2){
            this.niveau = 2;
            fonction.setWindows(this);
            this.setBoutons();
            this.fonction.setMines(this);
            this.fonction.setDrapeau(this);
        }
        if(e.getSource() == this.menu.getExpert() && this.niveau != 3){
            this.niveau = 3;
            fonction.setWindows(this);
            this.setBoutons();
            this.fonction.setMines(this);
            this.fonction.setDrapeau(this);
        }
        if(e.getSource() == this.menu.getDebutant() && this.niveau == 1){
            this.setBoutons();
            this.fonction.newGame(this);
            this.fonction.setMines(this);
            this.fonction.setDrapeau(this);
        }
        if(e.getSource() == this.menu.getIntermediaire() && this.niveau == 2){
            this.setBoutons();
            this.fonction.newGame(this);
            this.fonction.setMines(this);
            this.fonction.setDrapeau(this);
        }
        if(e.getSource() == this.menu.getExpert()&& this.niveau == 3){
            this.setBoutons();
            this.fonction.newGame(this);
            this.fonction.setMines(this);
            this.fonction.setDrapeau(this);
        }
        if(e.getSource() == this.menu.getItemQuitter()){
            this.dispose();
        }
        // A finaliser
        if(this.menu.getItemReglesJeu() == e.getSource()){
            JOptionPane.showMessageDialog(null, "=> Le but du jeu est de découvrir toutes les cases libres sans faire exploser les mines.\n"
            + "=> Pour libérer une case, faire un clic gauche (clic normal).\n"
            + "=> Pour marquer une mine, faire un clic droit, qui fera apparaître un drapeau.\n"
            + "=> Le compteur en haut à gauche indique le nombre de mines qu'il reste à trouver.\n"
            + "=> Le chiffre qui s'affiche sur les cases cliquées indique le nombre de mines se trouvant à proximité : \n"
                    + "à gauche ou à droite, en haut ou en bas, ou en diagonale.\n"
            + "=> Grâce aux indications données par les chiffres, vous pouvez libérer d'autres cases.\n"
                    + "Dans cet exemple, le \"1\" en haut à droite permet de deviner qu'il y a une mine sur la seule case qui l'entoure : là où il y a le drapeau\n"
            + "=> Une fois le drapeau placé, vous pouvez deviner qu'il n'y a pas de mines aux endroits marqués, puisque les \"1\" situés à coté ont déjà une mine sur les cases qui les entourent.\n"
                    + "Vous pouvez donc faire un clic gauche (clic normal) sur ces deux cases.", 
                    "Règles du jeu", JOptionPane.INFORMATION_MESSAGE);
        }
        if(this.menu.getItemAPropos() == e.getSource()){
            JOptionPane.showMessageDialog(null, "Version : 1.0\n"
                    + "Réaliser par : Dimitri KEMGANG SIFOUA\n"
                    + "Elève ingénieur en informatique\n"
                    + "Ecole d'Ingénieurs du Littoral Côte d'Opale", "A propos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public int getNbrLignes() {
        return nbrLignes;
    }

    public int getNbrColones() {
        return nbrColones;
    }

    public int getNbrMines() {
        return nbrMines;
    }

    public int getNiveau() {
        return niveau;
    }

    public Menu getMenu() {
        return menu;
    }

    public Panel getPanel() {
        return panel;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public void setNbrLignes(int nbrLignes) {
        this.nbrLignes = nbrLignes;
    }

    public void setNbrColones(int nbrColones) {
        this.nbrColones = nbrColones;
    }

    public void setNbrMines(int nbrMines) {
        this.nbrMines = nbrMines;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}
