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
    private ImageView source, fosse, adversaireDeck, adversaireOeuvres, adversaireVieFuture, deck, oeuvres, vieFuture,
            main;

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println(Partie.getInstance().toString());
        source.setImage(new Image("/images/cartes/Back.png"));
        fosse.setImage(new Image("/images/cartes/Bassesse.png"));
        adversaireDeck.setImage(new Image("/images/cartes/Back.png"));
        adversaireOeuvres.setImage(new Image("/images/cartes/Bassesse.png"));
        adversaireVieFuture.setImage(new Image("/images/cartes/Bassesse.png"));
        deck.setImage(new Image("/images/cartes/Back.png"));
        oeuvres.setImage(new Image("/images/cartes/Bassesse.png"));
        vieFuture.setImage(new Image("/images/cartes/Bassesse.png"));
        main.setImage(new Image("/images/cartes/Bassesse.png"));
    }
}
