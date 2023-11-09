package karmaka.classes;

import java.util.ArrayList;

import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Source;
import karmaka.view.Router;

public final class Partie {
    private static Joueur[] joueurs = new Joueur[2];
    private static Source source = new Source();
    private static Fosse fosse = new Fosse();
    private static int anneaux = 12;
    private static Partie instance = null;
    private static ArrayList<Action> actionsPossibles = new ArrayList<Action>();
    private static int tour = 0; // 0 = j1, 1 = j2
    private static Etape etape = Etape.DEBUT;

    private enum Etape {
        DEBUT, PIOCHER_DECK, JOUER_CARTE
    }

    private Partie(Joueur j1, Joueur j2) {
        joueurs[0] = j1;
        joueurs[1] = j2;
        distribuer();
        tour();
    }

    public static void init(Joueur joueur1, Joueur joueur2) {
        if (instance == null) {
            instance = new Partie(joueur1, joueur2);
        }
    }

    public static Partie getInstance() {
        return instance;
    }

    public ArrayList<Action> getActionsPossibles() {
        return actionsPossibles;
    }

    public Joueur getJoueur(int i) {
        return joueurs[i];
    }

    public Source getSource() {
        return source;
    }

    public Fosse getFosse() {
        return fosse;
    }

    public int getTour() {
        return tour;
    }

    public static void distribuer() {
        for (int i = 0; i < 2; i++) {
            joueurs[i].getMain().ajouter(source.piocher(4));
            joueurs[i].getDeck().ajouter(source.piocher(2));
        }
    }

    public static void tourSuivant() {
        tour = (tour + 1) % 2;
    }

    public static void tour() {
        Deck deck = joueurs[tour].getDeck();
        Main main = joueurs[tour].getMain();
        switch (etape) {
            case DEBUT:
                if (deck.getCartes().size() != 0) {
                    Router.getInstance().instructions("Veuillez piocher une carte dans votre deck");
                    actionsPossibles.clear();
                    actionsPossibles.add(Action.PIOCHER_DECK);
                    etape = Etape.PIOCHER_DECK;
                } else {
                    etape = Etape.JOUER_CARTE;
                    tour();
                }
                break;
            case PIOCHER_DECK:
                main.ajouter(deck.piocher(1));
        }
    }

}
