package karmaka.classes;

import java.util.ArrayList;

import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Source;

public final class Partie {
    private static Joueur[] joueurs = new Joueur[2];
    private static Source source = new Source();
    private static Fosse fosse = new Fosse();
    private static int anneaux = 12;
    private static Partie instance = null;
    private static int tour = 0; // 0 = j1, 1 = j2

    private Partie(Joueur j1, Joueur j2) {
        joueurs[0] = j1;
        joueurs[1] = j2;
        distribuer();
    }

    public static void init(Joueur joueur1, Joueur joueur2) {
        if (instance == null) {
            instance = new Partie(joueur1, joueur2);
        }
    }

    public static Partie getInstance() {
        return instance;
    }

    public static Joueur getJoueur(int i) {
        return joueurs[i];
    }
    public static void distribuer() {
        for (int i = 0; i < 2; i++) {
            Main cartesMain = joueurs[i].getMain();
            Deck cartesDeck = joueurs[i].getDeck();
            cartesMain.ajouter(source.piocher(4));
            cartesDeck.ajouter(source.piocher(2));
        }
    }
    public static void tourSuivant() {
        tour = (tour + 1) % 2;
    }
    public static void tour() {
        Deck deck = joueurs[tour].getDeck();
        Main main = joueurs[tour].getMain();
        if (deck.getCartes().size() != 0) main.ajouter(deck.piocher(1));
        // Ici il faut faire choisir une carte de sa main au joueur
    }

}
