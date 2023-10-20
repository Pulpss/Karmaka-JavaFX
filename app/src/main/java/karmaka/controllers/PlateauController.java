package karmaka.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import karmaka.classes.Partie;

public class PlateauController implements Initializable {
    // Cards dimensions are 160x115
    @FXML
    private ImageView source;

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println(Partie.getInstance().toString());
        source.setImage(new Image("/images/cartes/Bassesse.png"));
    }
}
