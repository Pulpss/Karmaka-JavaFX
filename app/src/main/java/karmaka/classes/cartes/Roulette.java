package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Source;

public class Roulette extends Carte {
    public Roulette() {
        super("Roulette", Couleur.ROUGE,
                "Défaussez jusqu’à 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de carte(s) + 1.",
                2);
    }

    public void pouvoir() {
        // TODO: tester
        Joueur joueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
        joueur.afficher("La carte Roulette va être jouée !");
        Main main = joueur.getMain();
        int nbCartes = 0;
        if (main.size() > 0) {
            String[] possibles;
            if (main.size() == 1) {
                possibles = new String[] { "1" };
            } else {
                possibles = new String[] { "1", "2" };
            }
            String choix = joueur.choix("Combien de cartes voulez vous defausser de votre main ?", possibles);
            nbCartes = Integer.parseInt(choix);
        } else {
            joueur.afficher("Votre main est vide.");
        }
        Deck deck = joueur.getDeck();
        Fosse fosse = Partie.getInstance().getFosse();
        Source source = Partie.getInstance().getSource();
        for (int i = 0; i < Math.min(nbCartes, main.size()); i++) {
            Carte c = joueur.choix("Choisissez une carte à défausser.", main.getCartes());
            fosse.ajouter(main.piocher(c));
        }
        if (source.size() > 0) {
            deck.ajouter(source.piocher(Math.min(source.size(), nbCartes + 1)));
            joueur.afficher("Les cartes ont été ajoutées à votre deck.");
        } else {
            joueur.afficher("La source est vide.");
        }
    }
}
