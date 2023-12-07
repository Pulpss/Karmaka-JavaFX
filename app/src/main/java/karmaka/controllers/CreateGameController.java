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

public class CreateGameController implements Initializable {
    @FXML
    private RadioButton j1Humain, j1Robot, j2Humain, j2Robot;
    @FXML
    private TextField j1Nom, j2Nom;

    @FXML
    public void initialize(java.net.URL arg0, java.util.ResourceBundle arg1) {
    }

    @FXML
    private void handleJ1Humain() {
        j1Robot.setSelected(false);
    }
    @FXML
    private void handleJ1Robot() {
        j1Humain.setSelected(false);
    }
    @FXML
    private void handleJ2Humain() {
        j2Robot.setSelected(false);
    }
    @FXML
    private void handleJ2Robot() {
        j2Humain.setSelected(false);
    }
    @FXML
    private void handleAnnuler()  {
        Router.getInstance().setScene("menu");
    }
    @FXML
    private void handleCreer()  {
        Router.getInstance().setScene("plateauPlaceholder");
        Joueur j1 = j1Humain.isSelected() ? new Humain(j1Nom.getText()) : new Robot(j1Nom.getText());
        Joueur j2 = j2Humain.isSelected() ? new Humain(j2Nom.getText()) : new Robot(j2Nom.getText());
        Partie.init(j1, j2);
        Router.getInstance().setScene("plateau");
    }
}
