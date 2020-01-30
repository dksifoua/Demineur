/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demineur;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author kemga
 */
public final class ReglesJeu extends JFrame {
    
    private final JPanel panel = new JPanel();
    private final JLabel texte = new JLabel("Le but du jeu est de découvrir toutes les cases libres sans faire exploser les mines.\n"
            + "Pour libérer une case, faire un clic gauche (clic normal).\n"
            + "Pour marquer une mine, faire un clic droit, qui fera apparaître un drapeau.\n"
            + "Le compteur en haut à gauche indique le nombre de mines qu'il reste à trouver.\n"
            + "Le chiffre qui s'affiche sur les cases cliquées indique le nombre de mines se trouvant à proximité : à gauche ou à droite, en haut ou en bas, ou en diagonale.\n"
            + "Grâce aux indications données par les chiffres, vous pouvez libérer d'autres cases. Dans cet exemple, le \"1\" en haut à droite permet de deviner qu'il y a une mine sur la seule case qui l'entoure : là où il y a le drapeau\n"
            + "Une fois le drapeau placé, vous pouvez deviner qu'il n'y a pas de mines aux endroits marqués, puisque les \"1\" situés à coté ont déjà une mine sur les cases qui les entourent. Vous pouvez donc faire un clic gauche (clic normal) sur ces deux cases.");
    
    public ReglesJeu(){
        UI();
    }
    
    public void UI(){
        this.setTitle("Règles du jeu");
        this.setMinimumSize(new Dimension(800, 300));
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\images\\logo.jpg"));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.panel.add(texte);
        
        this.setContentPane(panel);
        this.setVisible(true);
    }
    
}
