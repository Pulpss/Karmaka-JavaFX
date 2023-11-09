package karmaka.controllers;

import java.net.URL;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import karmaka.classes.Action;
import karmaka.classes.Partie;
import karmaka.view.Router;

public class PlateauController implements Initializable {
    // Cards dimensions are 115x160
    @FXML
    private ImageView source, fosse, adversaireDeck, adversaireVieFuture, vieFuture, deck;

    @FXML
    private Text adversaireVieFutureQte, adversaireDeckQte, sourceQte, fosseQte, deckQte, vieFutureQte;

    @FXML
    private HBox main, oeuvres, adversaireOeuvres;

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

    public ImageView carte(String url) {
        ImageView carte = new ImageView();
        carte.setFitHeight(160);
        carte.setFitWidth(115);
        carte.setImage(new Image(url));
        return carte;
    }

    private void initMain() {
        Partie partie = Partie.getInstance();
        ArrayList<ImageView> cartes = new ArrayList<ImageView>();
        partie.getJoueur(partie.getTour()).getMain().getCartes().iterator()
                .forEachRemaining(c -> cartes.add(carte("/images/cartes/" + c.getNom() + ".png")));
        main.getChildren().addAll(cartes);
    }

    private void initOeuvres() {
        // joueur actuel
        Partie partie = Partie.getInstance();
        ArrayList<ImageView> cartes = new ArrayList<ImageView>();
        partie.getJoueur(partie.getTour()).getOeuvres().getCartes().iterator()
                .forEachRemaining(c -> cartes.add(carte("/images/cartes/" + c.getNom() + ".png")));
        oeuvres.getChildren().addAll(cartes);
        // joueur adversaire
        cartes.clear();
        partie.getJoueur((partie.getTour() + 1) % 2).getOeuvres().getCartes().iterator()
                .forEachRemaining(c -> cartes.add(carte("/images/cartes/" + c.getNom() + ".png")));
        adversaireOeuvres.getChildren().addAll(cartes);
    }

    private void initFosse() {
        Partie partie = Partie.getInstance();
        if (partie.getFosse().size() > 0) {
            fosse.setVisible(true);
            fosseQte.setVisible(true);
            fosse.setImage(new Image("/images/cartes/" + partie.getFosse().getCartes().get(0).getNom() + ".png"));
            fosseQte.setText(Integer.toString(partie.getFosse().size()));
        } else {
            fosse.setVisible(false);
            fosseQte.setVisible(false);
        }
    }

    private void initVieFuture() {
        Partie partie = Partie.getInstance();
        // joueur actuel vie future
        if (partie.getJoueur(partie.getTour()).getVieFuture().size() > 0) {
            vieFuture.setVisible(true);
            vieFutureQte.setVisible(true);
            vieFuture.setImage(new Image("/images/cartes/"
                    + partie.getJoueur(partie.getTour()).getVieFuture().getCartes().get(0).getNom() + ".png"));
            vieFutureQte.setText(Integer.toString(partie.getJoueur(partie.getTour()).getVieFuture().size()));
        } else {
            vieFuture.setVisible(false);
            vieFutureQte.setVisible(false);
        }
        // adversaire vie future
        if (partie.getJoueur((partie.getTour() + 1) % 2).getVieFuture().size() > 0) {
            adversaireVieFuture.setVisible(true);
            adversaireVieFutureQte.setVisible(true);
            adversaireVieFutureQte
                    .setText(Integer.toString(partie.getJoueur((partie.getTour() + 1) % 2).getVieFuture().size()));
        } else {
            adversaireVieFuture.setVisible(false);
            adversaireVieFutureQte.setVisible(false);
        }
    }

    private void initDeck() {
        Partie partie = Partie.getInstance();
        // joueur actuel deck
        if (partie.getJoueur(partie.getTour()).getDeck().size() > 0) {
            deck.setVisible(true);
            deckQte.setVisible(true);
            deckQte.setText(Integer.toString(partie.getJoueur(partie.getTour()).getDeck().size()));
        } else {
            deckQte.setVisible(false);
            deck.setVisible(false);
        }
        // adversaire deck
        if (partie.getJoueur((partie.getTour() + 1) % 2).getDeck().size() > 0) {
            adversaireDeck.setVisible(true);
            adversaireDeckQte.setVisible(true);
            adversaireDeckQte.setText(Integer.toString(partie.getJoueur((partie.getTour() + 1) % 2).getDeck().size()));
        } else {
            adversaireDeck.setVisible(false);
            adversaireDeckQte.setVisible(false);
        }
    }

    private void initSource() {
        Partie partie = Partie.getInstance();
        if (partie.getSource().size() > 0) {
            source.setVisible(true);
            sourceQte.setText(Integer.toString(partie.getSource().size()));
        } else {
            source.setVisible(false);
            sourceQte.setVisible(false);
        }
    }

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
        initSource();
        initMain();
        initOeuvres();
        initFosse();
        initVieFuture();
        initDeck();
    }
}
