package karmaka.controllers;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import karmaka.classes.Action;
import karmaka.classes.Partie;
import karmaka.view.Router;

public class PlateauController implements Initializable {
    // Cards dimensions are 160x115
    @FXML
    private ImageView source, fosse, adversaireDeck, adversaireOeuvres, adversaireVieFuture, deck, oeuvres, vieFuture,
            main;

    @FXML
    private Text adversaireVieFutureQte, adversaireDeckQte, sourceQte, fosseQte, deckQte, vieFutureQte;
    
    @FXML
    public void handleEchelleButton() throws IOException {
        Router.getInstance().setScene("echelle");
    }

    @FXML
    public void handleDeck() {
        ArrayList<Action> actionsPossibles = Partie.getInstance().getActionsPossibles();
        if (actionsPossibles.contains(Action.PIOCHER_DECK)) {
            Partie.tour();
        }
    }

    private void initPileQte() {
        Partie partie = Partie.getInstance();
        System.out.println(partie.getJoueur(1).getDeck().toString());
        adversaireVieFutureQte.setText(Integer.toString(partie.getJoueur((partie.getTour()+1) % 2).getVieFuture().size()));
        adversaireDeckQte.setText(Integer.toString(partie.getJoueur((partie.getTour()+1) % 2).getDeck().size()));
        sourceQte.setText(Integer.toString(partie.getSource().size()));
        fosseQte.setText(Integer.toString(partie.getFosse().size()));
        deckQte.setText(Integer.toString(partie.getJoueur(partie.getTour()).getDeck().size()));
        vieFutureQte.setText(Integer.toString(partie.getJoueur(partie.getTour()).getVieFuture().size()));
    }

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println(Partie.getInstance().toString());
        initPileQte();
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
