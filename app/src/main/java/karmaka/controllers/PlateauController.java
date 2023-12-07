package karmaka.controllers;

import java.net.URL;

import java.util.ResourceBundle;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import karmaka.classes.Action;
import karmaka.classes.Carte;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.view.CarteView;
import karmaka.view.Router;

public class PlateauController implements Initializable {
    // Cards dimensions are 115x160
    @FXML
    private ImageView source, fosse, adversaireDeck, adversaireVieFuture, vieFuture, deck;

    @FXML
    private Text adversaireVieFutureQte, adversaireDeckQte, sourceQte, fosseQte, deckQte, vieFutureQte,
            adversairePoints, points;

    @FXML
    private HBox main, oeuvres, adversaireOeuvres;

    @FXML
    private Button passer;

    @FXML
    public void handlePasser() {
        ArrayList<Action> actionsPossibles = Partie.getInstance().getActionsPossibles();
        if (actionsPossibles.contains(Action.PASSER)) {
            Partie.getInstance().setEtape(Partie.Etape.TOUR_SUIVANT);
            Partie.getInstance().tour();
        } else {
            Joueur joueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
            joueur.afficher(
                    "Vous ne pouvez pas passer votre tour. (C'est sans doute parce que vous n'avez pas encore piocher.)");
        }
    }

    @FXML
    public void handleEchelleButton() {
        Router.getInstance().setScene("echelle");
    }

    @FXML
    public void handleDeck() {
        ArrayList<Action> actionsPossibles = Partie.getInstance().getActionsPossibles();
        System.out.println(actionsPossibles);
        if (actionsPossibles.contains(Action.PIOCHER_DECK)) {
            Partie.getInstance().tour();
        }
    }

    @FXML
    public void handleSave() {
        Partie.getInstance().sauvegarder();
    }

    @FXML
    public void handleLoad() {
        Partie.getInstance().charger();
    }

    private void handleMouseClicked(Carte c) {
        ArrayList<Action> actionsPossibles = Partie.getInstance().getActionsPossibles();
        System.out.println(actionsPossibles);
        // Si on peut choisir une carte de la main et si cette carte est dans la main.
        if (actionsPossibles.contains(Action.CHOISIR_CARTE_MAIN)
                && Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain().getCartes().contains(c)) {
            Partie.getInstance().setCarteChoisie(c);
            Partie.getInstance().tour();
        }
    }

    private void initMain() {
        Partie partie = Partie.getInstance();
        partie.getJoueur(partie.getTour()).getMain().getCartes().iterator()
                .forEachRemaining(c -> {
                    ImageView tempView = new CarteView(c);
                    tempView.setOnMouseClicked(e -> {
                        handleMouseClicked(c);
                    });
                    main.getChildren().add(tempView);
                });
    }

    private void initOeuvres() {
        // joueur actuel
        Partie partie = Partie.getInstance();
        ArrayList<ImageView> cartes = new ArrayList<ImageView>();
        partie.getJoueur(partie.getTour()).getOeuvres().getCartes().iterator()
                .forEachRemaining(c -> {
                    ImageView tempView = new CarteView(c);
                    tempView.setOnMouseClicked(e -> {
                        handleMouseClicked(c);
                    });
                    cartes.add(tempView);
                });
        oeuvres.getChildren().addAll(cartes);
        // joueur adversaire
        cartes.clear();
        partie.getJoueur((partie.getTour() + 1) % 2).getOeuvres().getCartes().iterator()
                .forEachRemaining(c -> {
                    ImageView tempView = new CarteView(c);
                    tempView.setOnMouseClicked(e -> {
                        handleMouseClicked(c);
                    });
                    cartes.add(tempView);
                });
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
            ArrayList<Carte> vf = partie.getJoueur(partie.getTour()).getVieFuture().getCartes();
            vieFuture.setImage(new Image("/images/cartes/"
                    + vf.get(vf.size() - 1).getNom() + ".png"));
            vieFutureQte.setText(Integer.toString(vf.size()));
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

    private void initPoints() {
        Partie partie = Partie.getInstance();
        points.setText(Integer.toString(partie.getJoueur(partie.getTour()).getEchelleKarmique()));
        adversairePoints.setText(Integer.toString(partie.getJoueur((partie.getTour() + 1) % 2).getEchelleKarmique()));
    }

    private void initPasser() {
        Deck deck = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getDeck();
        if (deck.size() > 0) {
            passer.setVisible(true);
        } else {
            passer.setVisible(false);
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
        initPoints();
        initPasser();
    }
}
