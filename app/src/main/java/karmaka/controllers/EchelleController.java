package karmaka.controllers;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import karmaka.classes.Partie;
import karmaka.view.Router;


public class EchelleController implements Initializable {
    @FXML
    private AnchorPane j1Pion, j2Pion;

    @FXML
    public void handleRetourButton() throws IOException {
        Router.getInstance().setScene("plateau");
    }

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println(Partie.getInstance().toString());
        Partie partie = Partie.getInstance();
        int j1 = partie.getJoueur(0).getPoints();
        int j2 = partie.getJoueur(1).getPoints();
        System.out.println("J1 : " + j1 + " J2 : " + j2);
        if (j1 < 4) {
            j1Pion.setLayoutY(400);
        } else if (j1 < 5) {
            j1Pion.setLayoutY(310);
        } else if (j1 < 6) {
            j1Pion.setLayoutY(220);
        } else if (j1 < 7) {
            j1Pion.setLayoutY(130);
        } else {
            j1Pion.setLayoutY(40);
        }
        if (j2 < 4) {
            j2Pion.setLayoutY(400);
        } else if (j2 < 5) {
            j2Pion.setLayoutY(310);
        } else if (j2 < 6) {
            j2Pion.setLayoutY(220);
        } else if (j2 < 7) {
            j2Pion.setLayoutY(130);
        } else {
            j2Pion.setLayoutY(40);
        }
    }
}
