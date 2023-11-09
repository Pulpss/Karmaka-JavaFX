package karmaka.classes;

import java.io.IOException;
import java.util.ArrayList;

import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Oeuvres;
import karmaka.classes.piles.Source;
import karmaka.classes.piles.VieFuture;
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
    private static String dernierChoix = "";
    private static Carte carteChoisie = null;

    private enum Etape {
        DEBUT, PIOCHER_DECK, JOUER_CARTE, CHOISIR_CARTE_MAIN, CHOISIR_UTILISATION_CARTE
    }

    private Partie(Joueur j1, Joueur j2) throws IOException {
        joueurs[0] = j1;
        joueurs[1] = j2;
        distribuer();
        tour();
    }

    public static void init(Joueur joueur1, Joueur joueur2) throws IOException {
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

    public void setDernierChoix(String choix) {
        dernierChoix = choix;
    }

    public void setCarteChoisie(Carte carte) {
        carteChoisie = carte;
    }

    private void distribuer() {
        for (int i = 0; i < 2; i++) {
            joueurs[i].getMain().ajouter(source.piocher(4));
            joueurs[i].getDeck().ajouter(source.piocher(2));
        }
    }

    public void tourSuivant() {
        tour = (tour + 1) % 2;
    }

    public void tour() throws IOException {
        Deck deck = joueurs[tour].getDeck();
        Main main = joueurs[tour].getMain();
        Oeuvres oeuvres = joueurs[tour].getOeuvres();
        VieFuture vieFuture = joueurs[tour].getVieFuture();
        switch (etape) {
            case DEBUT:
                if (deck.getCartes().size() != 0) {
                    Router.getInstance().instructions("Veuillez piocher une carte dans votre deck");
                    actionsPossibles.clear();
                    actionsPossibles.add(Action.PIOCHER_DECK);
                    etape = Etape.PIOCHER_DECK;
                } else {
                    etape = Etape.CHOISIR_CARTE_MAIN;
                    tour();
                }
                break;
            case PIOCHER_DECK:
                main.ajouter(deck.piocher(1));
                Router.getInstance().update();
                etape = Etape.CHOISIR_CARTE_MAIN;
                tour();
                break;
            case CHOISIR_CARTE_MAIN:
                Router.getInstance().instructions("Veuillez choisir une carte dans votre main.");
                actionsPossibles.clear();
                actionsPossibles.add(Action.CHOISIR_CARTE_MAIN);
                etape = Etape.CHOISIR_UTILISATION_CARTE;
                break;
            case CHOISIR_UTILISATION_CARTE:
                Router.getInstance().choix("Veuillez choisir une utilisation pour la carte " + carteChoisie.getNom(),
                        "Points", "Points", "Pouvoir", "Futur");
                System.out.println(dernierChoix);
                if (dernierChoix == null) {
                    etape = Etape.CHOISIR_CARTE_MAIN;
                    tour();
                    break;
                }
                switch (dernierChoix) {
                    case "Points":
                        oeuvres.ajouter(main.piocher(carteChoisie));
                        joueurs[tour].setPoints(joueurs[tour].getPoints() + carteChoisie.getPoints());
                        break;
                    case "Pouvoir":
                        carteChoisie.pouvoir();
                        break;
                    case "Futur":
                        vieFuture.ajouter(main.piocher(carteChoisie));
                        break;
                }
                Router.getInstance().update();
                break;
        }
    }

}
