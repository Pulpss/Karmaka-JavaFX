package karmaka.classes.cartes;

import java.io.IOException;
import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Source;
import karmaka.view.Router;

public class Voyage extends Carte {
    public Voyage() {
        super("Voyage", Couleur.VERT, "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.", 3);
    }

    public void pouvoir() throws IOException {
        // TODO: tester
        System.out.println("Voyage");
        Source source = Partie.getInstance().getSource();
        Deck deck = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getDeck();
        if (source.size() > 0) {
            deck.ajouter(source.piocher(Math.min(3, source.size())));
        } else {
            Router.getInstance().instructions("La source est vide.");
        }
        Router.getInstance().instructions("Vous pouvez rejouer.");
        Partie.getInstance().setEtape(Partie.Etape.CHOISIR_CARTE_MAIN);
        Partie.getInstance().tour();
    }
}
