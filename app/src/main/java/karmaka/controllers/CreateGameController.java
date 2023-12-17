package karmaka.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.classes.joueurs.Humain;
import karmaka.classes.joueurs.Robot;
import karmaka.view.Router;

/**
 * La classe CreateGameController gère les actions et les interactions de l'interface utilisateur pour la création d'une nouvelle partie du jeu.
 */
public class CreateGameController implements Initializable {
    @FXML
    private RadioButton j1Humain, j1Robot, j2Humain, j2Robot, j1RobotOff, j1RobotDef, j1RobotFerm, j2RobotOff, j2RobotDef, j2RobotFerm;
    @FXML
    private TextField j1Nom, j2Nom;

    /**
     * Initialise le contrôleur après que son élément racine ait été complètement traité.
     *
     * @param arg0 URL utilisée pour résoudre les chemins relatifs aux ressources.
     * @param arg1 ResourceBundle qui contient les paramètres régionaux actuels.
     */
    @FXML
    public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
    }

    /**
     * Gère l'action lorsque le joueur 1 est défini comme humain.
     */
    @FXML
    private void handleJ1Humain() {
        j1Robot.setSelected(false);
    }

    /**
     * Gère l'action lorsque le joueur 1 est défini comme robot.
     */
    @FXML
    private void handleJ1Robot() {
        j1Humain.setSelected(false);
    }

    /**
     * Gère l'action lorsque le joueur 2 est défini comme humain.
     */
    @FXML
    private void handleJ2Humain() {
        j2Robot.setSelected(false);
    }

    /**
     * Gère l'action lorsque le joueur 2 est défini comme robot.
     */
    @FXML
    private void handleJ2Robot() {
        j2Humain.setSelected(false);
    }

    /**
     * Gère l'action lorsque le joueur 1 robot est configuré en mode "Offensif".
     */
    @FXML
    private void j1RobotOff() {
    	j1RobotDef.setSelected(false);
    	j1RobotFerm.setSelected(false);
    }

    /**
     * Gère l'action lorsque le joueur 1 robot est configuré en mode "Défensif".
     */
    @FXML
    private void j1RobotDef() {
    	j1RobotOff.setSelected(false);
    	j1RobotFerm.setSelected(false);
    }
    /**
     * Gère l'action lorsque le joueur 1 robot est configuré en mode "Fermier".
     */
    @FXML
    private void j1RobotFerm() {
    	j1RobotDef.setSelected(false);
    	j1RobotOff.setSelected(false);
    }
    
    /**
     * Gère l'action lorsque le joueur 2 robot est configuré en mode "Offensif".
     */
    @FXML
    private void j2RobotOff() {
    	j2RobotDef.setSelected(false);
    	j2RobotFerm.setSelected(false);
    }

    /**
     * Gère l'action lorsque le joueur 2 robot est configuré en mode "Défensif".
     */
    @FXML
    private void j2RobotDef() {
    	j2RobotOff.setSelected(false);
    	j2RobotFerm.setSelected(false);
    }
    /**
     * Gère l'action lorsque le joueur 2 robot est configuré en mode "Fermier".
     */
    @FXML
    private void j2RobotFerm() {
    	j2RobotDef.setSelected(false);
    	j2RobotOff.setSelected(false);
    }
    
    /**
     * Gère l'action d'annulation pour revenir au menu principal.
     */
    @FXML
    private void handleAnnuler() {
        Router.getInstance().setScene("menu");
    }

    /**
     * Gère l'action de création d'une nouvelle partie avec les joueurs configurés
     * et redirige vers la scène du plateau de jeu.
     */
    @FXML
    private void handleCreer() {
        Router.getInstance().setScene("plateauPlaceholder");
        Joueur j1 = j1Humain.isSelected() ? new Humain(j1Nom.getText()) : new Robot(j1Nom.getText());
        Joueur j2 = j2Humain.isSelected() ? new Humain(j2Nom.getText()) : new Robot(j2Nom.getText());
        if (j1Robot.isSelected()){
        	
        }
        if (j2Robot.isSelected()){
        	
        }

        Partie.init(j1, j2);
        Router.getInstance().setScene("plateau");
        Partie.getInstance().tour();
    }
}
