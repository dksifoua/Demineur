///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package demineur;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.util.ArrayList;
//import java.util.Random;
//import javax.swing.*;
//
///**
// *
// * @author kemga
// */
//public class Evenement extends JFrame implements ActionListener {
//
//    /**
//     * @param args the command line arguments
//     */
//    
//    private int nbrMines;
//    
//    private int lig;
//    private int col;
//    private int score;
//    
//    //private final Evenement action = new Evenement();
//    
//    private final JMenuBar barreMenus = new JMenuBar();
//    
//    private final JMenu menuPartie = new JMenu("Partie");
//    private final JMenu menuAide = new JMenu("Aide");
//    
//    private final JMenuItem itemNvllePartie = new JMenuItem("Nouvelle partie");
//    private final JMenuItem itemQuitter = new JMenuItem("Quitter");
//    private final JMenuItem itemReglesJeu = new JMenuItem("Règles du jeu");
//    private final JMenuItem itemAPropos = new JMenuItem("A propos");
//    
//    private final JPanel panelPrinc = new JPanel();
//    private final JPanel panelHaut = new JPanel();
//    private final JPanel panelMilieu = new JPanel();
//    private final JPanel panelBas = new JPanel();
//            
//    private JLabel labelScore = new JLabel();
//    private final JLabel labelImage = new JLabel(new ImageIcon(".\\images\\nouveauJeu.gif"));
//    private final JLabel labelBasPage = new JLabel("Copyright © 2017, Tous droits réservés", JLabel.CENTER);
//    
//    private ArrayList<ArrayList<JButton>> buttons = new ArrayList<>();
//    private JButton boutonTest = new JButton();
//    
//    private ArrayList<ArrayList<Integer>> mines = new ArrayList<>();
//    private ArrayList<ArrayList<Integer>> nbrMinesParCase = new ArrayList<>();
//    
//    private int drapeaux[][] = new int[16][16];
//    
//    private final ArrayList<ImageIcon> images = new ArrayList<>();
//    
//    public Evenement(){
//        nbrMines = 40;
//        score = nbrMines;
//        this.loadPictures();
//        this.initUI();
//        this.setMines();
//        this.setDrapeau();
//    }
//    
//    public final void loadPictures(){
//        this.images.add(new ImageIcon(".\\images\\1.gif"));
//        this.images.add(new ImageIcon(".\\images\\2.gif"));
//        this.images.add(new ImageIcon(".\\images\\3.gif"));
//        this.images.add(new ImageIcon(".\\images\\4.gif"));
//        this.images.add(new ImageIcon(".\\images\\5.gif"));
//        this.images.add(new ImageIcon(".\\images\\6.gif"));
//        this.images.add(new ImageIcon(".\\images\\7.gif"));
//        this.images.add(new ImageIcon(".\\images\\8.gif"));
//        this.images.add(new ImageIcon(".\\images\\drapeau.gif"));
//        this.images.add(new ImageIcon(".\\images\\erreur.gif"));
//        this.images.add(new ImageIcon(".\\images\\mine.gif"));
//        this.images.add(new ImageIcon(".\\images\\mine.png"));
//        this.images.add(new ImageIcon(".\\images\\nouveauJeu.gif"));
//        this.images.add(new ImageIcon(".\\images\\press.gif"));
//        this.images.add(new ImageIcon(".\\images\\logo.gif"));
//        this.images.add(new ImageIcon(".\\images\\inter.gif"));
//    }
//    
//    public final void initUI(){
//        this.setTitle("Démineur");
//        this.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images\\logo.jpg"));
//        this.setSize(400, 450);
//        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
//                
//        this.menuPartie.add(itemNvllePartie);
//        this.menuPartie.add(itemQuitter);
//        this.menuAide.add(itemReglesJeu);
//        this.menuAide.add(itemAPropos);
//        
//        itemNvllePartie.addActionListener(this);
//        itemQuitter.addActionListener(this);
//        itemReglesJeu.addActionListener(this);
//        itemAPropos.addActionListener(this);
//        
//        this.barreMenus.add(menuPartie);
//        this.barreMenus.add(menuAide);
//        
//        this.setJMenuBar(barreMenus);
//        
//        this.labelScore.setSize(80, 50);
//        this.labelScore.setText(Integer.toString(this.score));
//        this.labelScore.setFont(new Font("DigtalFont.TTF", Font.BOLD, 25));
//        this.labelScore.setOpaque(true);
//        this.labelScore.setBackground(Color.BLACK);
//        this.labelScore.setForeground(Color.RED);
//        
//        this.labelImage.setSize(80, 50);
//        //this.labelImage.setLocation(80, 5);
//        this.labelImage.setBackground(Color.GREEN);
//        this.labelImage.setOpaque(true);
//        
//        this.panelHaut.setLayout(new BorderLayout());
//        this.panelHaut.setSize(380, 50);
//        this.panelHaut.setLocation(8, 5);
//        this.panelHaut.add(labelScore, BorderLayout.LINE_START);
//        this.panelHaut.add(labelImage, BorderLayout.LINE_END);
//        this.panelHaut.setBorder(BorderFactory.createLoweredBevelBorder());
//        
//        for (int i = 0; i < 16; i++) {
//            this.buttons.add(new ArrayList<>());
//            for (int j = 0; j < 16; j++) {
//                this.buttons.get(i).add(new JButton());
//            }
//        }
//        
//        this.panelMilieu.setLayout(new GridLayout(16, 16));
//        this.panelMilieu.setSize(300, 300);
//        this.panelMilieu.setLocation(50, 60);
//        for(int i = 0; i < 16; i++){
//            for(int j = 0; j < 16; j++){
//                this.panelMilieu.add(buttons.get(i).get(j));
//                buttons.get(i).get(j).addMouseListener(new MouseListener(){
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        whereClick(e);
//                        if(SwingUtilities.isLeftMouseButton(e)){
//                            if(mines.get(getLig()).get(getCol()).equals(-1))
//                                affichMines(getLig(), getCol());
//                            else
//                                affichNbrMines(getLig(), getCol());
//                        }
//                        if(SwingUtilities.isRightMouseButton(e)){
//                            affichDrapeau(getLig(), getCol());
//                        }
//                    }
//
//                    @Override
//                    public void mousePressed(MouseEvent e) {
//                        labelImage.setIcon(new ImageIcon(".\\images\\erreur.gif"));
//                    }
//
//                    @Override
//                    public void mouseReleased(MouseEvent e) {
//                        labelImage.setIcon(new ImageIcon(".\\images\\nouveauJeu.gif"));
//                    }
//
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//                    }
//
//                    @Override
//                    public void mouseExited(MouseEvent e) {
//                    }
//                });
//            }
//        }
//        //this.panelMilieu.setBorder(BorderFactory.createLoweredBevelBorder());
//        
//        this.panelBas.setLayout(new BorderLayout());
//        this.panelBas.setSize(380, 30);
//        this.panelBas.setLocation(8, 360);
//        this.panelBas.add(labelBasPage, BorderLayout.CENTER);
//        //this.panelBas.setBorder(BorderFactory.createLoweredBevelBorder());
//        
//        this.panelPrinc.setSize(400, 450);
//        this.panelPrinc.add(panelHaut, BorderLayout.NORTH);
//        this.panelPrinc.add(panelMilieu, BorderLayout.CENTER);
//        this.panelPrinc.add(panelBas, BorderLayout.SOUTH);
//        this.panelPrinc.setLayout(new BorderLayout());
//        
//        this.setContentPane(panelPrinc);
//        
//        this.setResizable(false);
//        this.setVisible(true);
//                
//    }
//    
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if(e.getSource() == itemNvllePartie){
//            ;
//        }
//        if(e.getSource() == itemQuitter){
//            this.dispose();
//        }
//        // A finaliser
//        if(e.getSource() == itemReglesJeu){
//            JOptionPane.showMessageDialog(null, "=> Le but du jeu est de découvrir toutes les cases libres sans faire exploser les mines.\n"
//            + "=> Pour libérer une case, faire un clic gauche (clic normal).\n"
//            + "=> Pour marquer une mine, faire un clic droit, qui fera apparaître un drapeau.\n"
//            + "=> Le compteur en haut à gauche indique le nombre de mines qu'il reste à trouver.\n"
//            + "=> Le chiffre qui s'affiche sur les cases cliquées indique le nombre de mines se trouvant à proximité : \n"
//                    + "à gauche ou à droite, en haut ou en bas, ou en diagonale.\n"
//            + "=> Grâce aux indications données par les chiffres, vous pouvez libérer d'autres cases.\n"
//                    + "Dans cet exemple, le \"1\" en haut à droite permet de deviner qu'il y a une mine sur la seule case qui l'entoure : là où il y a le drapeau\n"
//            + "=> Une fois le drapeau placé, vous pouvez deviner qu'il n'y a pas de mines aux endroits marqués, puisque les \"1\" situés à coté ont déjà une mine sur les cases qui les entourent.\n"
//                    + "Vous pouvez donc faire un clic gauche (clic normal) sur ces deux cases.", 
//                    "Règles du jeu", JOptionPane.INFORMATION_MESSAGE);
//        }
//        if(e.getSource() == itemAPropos){
//            JOptionPane.showMessageDialog(null, "Version : 1.0\n"
//                    + "Réaliser par : Dimitri KEMGANG SIFOUA\n"
//                    + "Elève ingénieur en informatique\n"
//                    + "Ecole d'Ingénieurs du Littoral Côte d'Opale", "A propos", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
//    
//    public final void setMines(){
//        ArrayList<Integer> numeroPlateau = new ArrayList<>();
//        Random r = new Random();
//        int var, ligne, colone, numero = 0, cmpt = 0;
//        for (int i = 0; i < 16; i++) {
//            this.mines.add(new ArrayList<>());
//            for (int j = 0; j < 16; j++) {
//                this.mines.get(i).add(0);
//                numeroPlateau.add(numero);
//                numero++;
//            }
//        }
//        while(cmpt < this.nbrMines){
//            var = r.nextInt(numeroPlateau.size());
//            ligne = numeroPlateau.get(var) / 16;
//            colone = numeroPlateau.get(var) % 16;
//            this.mines.get(ligne).set(colone, -1);
//            numeroPlateau.remove(var);
//            cmpt++;
//        }
//        for(int i = 0; i < 16;i ++){
//            this.nbrMinesParCase.add(new ArrayList<>());
//            for(int j = 0; j < 16; j++)
//                this.nbrMinesParCase.get(i).add(-1);
//        }
//        this.calculNbrMinesParCase();
//    }
//    
//    public final void setDrapeau(){
//        for (int i = 0; i < 16; i++) {
//            for (int j = 0; j < 16; j++) {
//                this.drapeaux[i][j] = 0;
//            }
//        }
//    }
//    
//    public void whereClick(MouseEvent e){
//        for(int i = 0; i < 16; i++){
//            for(int j = 0; j < 16; j++){
//                int a = 16 * i + j;
//                if(e.getSource().equals(panelMilieu.getComponent(a))){
//                    this.lig = i;
//                    this.col = j;
//                }
//            }
//        }
//    }
//    
//    public void affichDrapeau(int l, int c){
//        int pos = 16 * l + c;
//        switch (this.drapeaux[l][c]) {
//            case 0:
//                if(this.score > 0){
//                    this.buttons.get(l).get(c).setIcon(images.get(8));
//                    this.drapeaux[l][c] = 1;
//                    this.score--;
//                    this.labelScore.setText(Integer.toString(this.score));
//                }
//                break;
//            case 1:
//                this.buttons.get(l).get(c).setIcon(images.get(15));
//                this.drapeaux[l][c] = 2;
//                this.score++;
//                this.labelScore.setText(Integer.toString(this.score));
//                break;
//            default:
//                this.buttons.get(l).get(c).setIcon(null);
//                this.drapeaux[l][c] = 0;
//                break;
//        }
//        this.labelScore.revalidate();
//    }
//    
//    public void affichNbrMines(int l, int c){
//        if(!this.nbrMinesParCase.get(l).get(c).equals(0)){
//            int a = 16 * l + c;
//            JLabel image = new JLabel(Integer.toString(this.nbrMinesParCase.get(l).get(c)), JLabel.CENTER);
//            image.setOpaque(true);
//            image.setVisible(true);
//            this.panelMilieu.remove(panelMilieu.getComponent(a));
//            this.panelMilieu.add(image, a);
//            this.panelMilieu.revalidate();
//        }else{
//            int a = 16 * l + c;
//            JLabel image = new JLabel("");
//            image.setOpaque(true);
//            image.setVisible(true);
//            this.panelMilieu.remove(panelMilieu.getComponent(a));
//            this.panelMilieu.add(image, a);
//            this.panelMilieu.revalidate();
//            if (c > 0){ // A droite
//                if(this.panelMilieu.getComponent(a - 1).getClass().equals(boutonTest.getClass()))
//                    this.affichNbrMines(l, c - 1);
//            }
//            if (c < 15){ // A gauche
//                if(this.panelMilieu.getComponent(a + 1).getClass().equals(boutonTest.getClass()))
//                    this.affichNbrMines(l, c + 1);
//            }
//            if (l < 15){ // En bas
//                if(this.panelMilieu.getComponent(a + 16).getClass().equals(boutonTest.getClass()))
//                    this.affichNbrMines(l + 1, c);
//            }
//            if (l > 0){ // En haut
//                if(this.panelMilieu.getComponent(a - 16).getClass().equals(boutonTest.getClass()))
//                    this.affichNbrMines(l - 1, c);
//            }
//            if (l < 15 && c < 15){ // En bas à droite
//                if(this.panelMilieu.getComponent(a  + 16 + 1).getClass().equals(boutonTest.getClass()))
//                    this.affichNbrMines(l + 1, c + 1);
//            }
//            if (l < 15 && c > 0){ // En bas à gauche
//                if(this.panelMilieu.getComponent(a + 16 - 1).getClass().equals(boutonTest.getClass()))
//                    this.affichNbrMines(l - 1, c + 1);
//            }
//            if (l > 0 && c > 0){ // En haut à gauche
//                if(this.panelMilieu.getComponent(a - 16 - 1).getClass().equals(boutonTest.getClass()))
//                    this.affichNbrMines(l - 1, c - 1);
//            }
//            if (l > 0 && c < 15){ // En haut à droite
//                if(this.panelMilieu.getComponent(a - 16 + 1).getClass().equals(boutonTest.getClass()))
//                    this.affichNbrMines(l + 1, c - 1);
//            }
//        }
//    }
//    
//    public void affichMines(int l, int c){
//        int pos = 16 * l + c;
//        for(int i = 0; i < 16; i++){
//            for(int j = 0; j < 16; j++){
//                int a = 16 * i + j;
//                if(this.mines.get(i).get(j).equals(-1)){
//                    JLabel bombe = new JLabel();
//                    if(a == pos)
//                        bombe.setIcon(images.get(10));
//                    else
//                        bombe.setIcon(images.get(11));
//                    bombe.setOpaque(true);
//                    bombe.setVisible(true);
//                    this.panelMilieu.remove(panelMilieu.getComponent(a));
//                    this.panelMilieu.add(bombe, a);
//                    this.panelMilieu.revalidate();
//                }else{
//                    JLabel couleur = new JLabel();
//                    couleur.setOpaque(true);
//                    couleur.setVisible(true);
//                    couleur.setBackground(new Color(228, 230, 232));
//                    this.panelMilieu.remove(panelMilieu.getComponent(a));
//                    this.panelMilieu.add(couleur, a);
//                    this.panelMilieu.revalidate();
//                }
//            }
//        }
//        JOptionPane.showMessageDialog(null, "Désolé,\n"
//                    + "\n"
//                    + "Vous avez perdu !", "Dommage", JOptionPane.ERROR_MESSAGE);
//    }
//    
//    public void calculNbrMinesParCase(){
//        for(int i = 0; i < 16;i ++){
//            for(int j = 0; j < 16; j++){
//                if(this.mines.get(i).get(j).equals(-1))
//                    this.nbrMinesParCase.get(i).set(j, -1);
//                else
//                    this.nbrMinesParCase.get(i).set(j, this.calculNbrMinesAutour(i, j));
//            }
//        }
//        for(int i = 0; i < 16;i ++){
//            for(int j = 0; j < 16; j++){
//                System.out.print(this.nbrMinesParCase.get(i).get(j) + " ");
//            }
//            System.out.println("");
//        }
//    }
//    
//    public int calculNbrMinesAutour(int l, int c){
//        int nbrMinestrouv = 0;
//        if(l == 0 && c == 0){
//            if(this.mines.get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c + 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
//        }else if(l == 0 && c > 0 && c < 15){
//            if(this.mines.get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c + 1).equals(-1)) nbrMinestrouv++;
//        }else if(l == 0 && c == 15){
//            if(this.mines.get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
//        }else if(l > 0 && l < 15 && c == 15){
//            if(this.mines.get(l - 1).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
//        }else if(l == 15 && c == 15){
//            if(this.mines.get(l - 1).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
//        }else if(l == 15 && c > 0 && c < 15){
//            if(this.mines.get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l - 1).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l - 1).get(c + 1).equals(-1)) nbrMinestrouv++;
//        }else if(l == 15 && c == 0){
//            if(this.mines.get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l - 1).get(c + 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
//        }else if(l > 0 && l < 15 && c == 0){
//            if(this.mines.get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l - 1).get(c + 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c + 1).equals(-1)) nbrMinestrouv++;
//        }else{
//            if(this.mines.get(l - 1).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l - 1).get(c).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l - 1).get(c + 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l).get(c + 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c - 1).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c).equals(-1)) nbrMinestrouv++;
//            if(this.mines.get(l + 1).get(c + 1).equals(-1)) nbrMinestrouv++;
//        }
//        return nbrMinestrouv;
//    }
//
//    public int getNbrMines() {
//        return nbrMines;
//    }
//
//    public ArrayList<ArrayList<Integer>> getMines() {
//        return mines;
//    }
//
//    public int getLig() {
//        return lig;
//    }
//
//    public int getCol() {
//        return col;
//    }
//    
//}
