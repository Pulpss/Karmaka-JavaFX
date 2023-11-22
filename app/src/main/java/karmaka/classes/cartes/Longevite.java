package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Source;
import karmaka.view.Router;

public class Longevite extends Carte {
    public Longevite() {
        super("Longevite", Couleur.VERT, "Placez 2 cartes puisées à la Source sur la Pile d'un joueur.", 2);
    }

    public void pouvoir() {
        // TODO : tester
        System.out.println("Longevite");
        Source source = Partie.getInstance().getSource();
        String choixJoueur = Router.getInstance().choix(
                "Choisissez le joueur sur lequel vous allez placez les 2 cartes de la source", "Adversaire",
                "Adversaire", "Moi");
        if (choixJoueur == "Adverdaire") {
            Deck deckAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getDeck();
            deckAdv.ajouter(source.piocher(Math.min(2, source.size())));
        } else {
            Deck deck = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getDeck();
            deck.ajouter(source.piocher(Math.min(2, source.size())));
        }
        Router.getInstance().instructions("Les cartes ont étés ajoutées à la pile du Joueur !");
    }
}
