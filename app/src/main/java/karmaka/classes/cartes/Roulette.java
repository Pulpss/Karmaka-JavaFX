package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Source;
import karmaka.view.Router;

public class Roulette extends Carte {
    public Roulette() {
        super("Roulette", Couleur.ROUGE,
                "Défaussez jusqu’à 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de carte(s) + 1.",
                2);
    }

    public void pouvoir() {
        // TODO: tester
        System.out.println("Roulette");
        String choix = Router.getInstance().choix("Combien de cartes voulez vous defausser de votre main ?", "0", "0",
                "1", "2");
        int nbCartes = Integer.parseInt(choix);
        Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        Deck deck = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getDeck();
        Fosse fosse = Partie.getInstance().getFosse();
        Source source = Partie.getInstance().getSource();
        for (int i = 0; i < Math.min(nbCartes, main.size()); i++) {
            Carte c = Router.getInstance().choix("Choisissez une carte à défausser.", main.getCartes());
            fosse.ajouter(main.piocher(c));
        }
        if (source.size() > 0) {
            deck.ajouter(source.piocher(Math.min(source.size(), nbCartes + 1)));
            Router.getInstance().instructions("Les cartes ont été ajoutées à votre deck.");
        } else {
            System.out.println("La source est vide.");
        }
    }
}
