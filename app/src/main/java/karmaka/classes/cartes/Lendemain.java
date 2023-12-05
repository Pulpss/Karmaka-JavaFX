package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Source;

public class Lendemain extends Carte {
    public Lendemain() {
        super("Lendemain", Couleur.VERT, "Puisez une carte à la Source. Vous pouvez ensuite jouer une autre carte.", 1);
    }

    public void pouvoir() throws IOException {
        // TODO: tester
        Joueur joueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
        joueur.afficher("La carte Lendemain va être jouée !");
        Source source = Partie.getInstance().getSource();
        Deck deck = joueur.getDeck();
        if (source.size() > 0) {
            deck.ajouter(source.piocher());
        } else {
            System.out.println("La source est vide.");
        }
        joueur.afficher("Vous pouvez rejouer.");
        Partie.getInstance().setEtape(Partie.Etape.PROPOSER_CARTE_REJOUER);
        Partie.getInstance().tour();
    }
}
