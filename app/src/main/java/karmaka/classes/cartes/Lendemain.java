package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Source;
import karmaka.view.Router;

public class Lendemain extends Carte {
    public Lendemain() {
        super("Lendemain", Couleur.VERT, "Puisez une carte à la Source. Vous pouvez ensuite jouer une autre carte.", 1);
    }

    public void pouvoir() throws IOException {
        // TODO: tester
        System.out.println("Lendemain");
        Source source = Partie.getInstance().getSource();
        Deck deck = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getDeck();
        if (source.size() > 0) {
            deck.ajouter(source.piocher());
        } else {
            System.out.println("La source est vide.");
        }
        Router.getInstance().instructions("Vous pouvez rejouer.");
        Partie.getInstance().setEtape(Partie.Etape.CHOISIR_CARTE_MAIN);
        Partie.getInstance().tour();
    }
}
