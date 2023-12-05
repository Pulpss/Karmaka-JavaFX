package karmaka.classes.cartes;

import java.io.IOException;
import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Source;

public class Voyage extends Carte {
    public Voyage() {
        super("Voyage", Couleur.VERT, "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.", 3);
    }

    public void pouvoir() throws IOException {
        // TODO: tester
        Joueur joueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
    	joueur.afficher("La carte Voyage va être jouée !");
        Source source = Partie.getInstance().getSource();
        Deck deck = joueur.getDeck();
        if (source.size() > 0) {
            deck.ajouter(source.piocher(Math.min(3, source.size())));
        } else {
            joueur.afficher("La source est vide.");
        }
        Partie.getInstance().setEtape(Partie.Etape.PROPOSER_CARTE_REJOUER);
        Partie.getInstance().tour();
    }
}
