package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Source;

public class Longevite extends Carte {
    public Longevite() {
        super("Longevite", Couleur.VERT, "Placez 2 cartes puisées à la Source sur la Pile d'un joueur.", 2);
    }

    public void pouvoir() {
        // TODO : tester
        Joueur joueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
        joueur.afficher("La carte Longévité va être jouée !");
        Source source = Partie.getInstance().getSource();
        String choixJoueur = joueur.choix(
                "Choisissez le joueur sur lequel vous allez placez les 2 cartes de la source", "Adversaire", "Moi");
        if (choixJoueur == "Adverdaire") {
            Deck deckAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getDeck();
            deckAdv.ajouter(source.piocher(Math.min(2, source.size())));
        } else {
            Deck deck = joueur.getDeck();
            deck.ajouter(source.piocher(Math.min(2, source.size())));
        }
        joueur.afficher("Les cartes ont étés ajoutées à la pile du Joueur !");
    }
}
