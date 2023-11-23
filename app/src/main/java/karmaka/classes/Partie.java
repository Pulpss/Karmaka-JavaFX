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
    private static Partie instance = null;
    private static ArrayList<Action> actionsPossibles = new ArrayList<Action>();
    private static int tour = 0; // 0 = j1, 1 = j2
    private static int gagnant;

    private static Etape etape = Etape.DEBUT;
    private static Carte carteChoisie = null;

    public enum Etape {
        DEBUT, PIOCHER_DECK, JOUER_CARTE, CHOISIR_CARTE_MAIN, CHOISIR_UTILISATION_CARTE, PROPOSER_CARTE,
        PROPOSER_CARTE_REJOUER, TOUR_SUIVANT, MORT, GAGNANT
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

    public void setCarteChoisie(Carte carte) {
        carteChoisie = carte;
    }

    public void setEtape(Etape et) {
        etape = et;
    }

    private void distribuer() {
        for (int i = 0; i < 2; i++) {
            joueurs[i].getMain().ajouter(source.piocher(4));
            joueurs[i].getDeck().ajouter(source.piocher(2));
        }
    }

    public void tour() throws IOException {
        Deck deck = joueurs[tour].getDeck();
        Main main = joueurs[tour].getMain();
        Oeuvres oeuvres = joueurs[tour].getOeuvres();
        VieFuture vieFuture = joueurs[tour].getVieFuture();
        switch (etape) {
            case DEBUT:
                if (deck.size() > 0) {
                    // Cas normal de jeu
                    Router.getInstance().instructions("Veuillez piocher une carte dans votre deck");
                    actionsPossibles.clear();
                    actionsPossibles.add(Action.PIOCHER_DECK);
                    etape = Etape.PIOCHER_DECK;
                } else {
                    if (main.size() > 0) {
                        // Le joueur n'a plus de carte dans son deck mais en a dans sa main donc il joue
                        etape = Etape.CHOISIR_CARTE_MAIN;
                        tour();
                    } else {
                        // Le joueur n'a plus rien donc il meuurt dans d'atroces souffrances !
                        etape = Etape.MORT;
                        tour();
                    }
                }
                break;
            case MORT:
                int points = oeuvres.calculerPoints();
                if (joueurs[tour].getNbAnneaux() > 0) {
                    String[] choixPossibles = new String[joueurs[tour].getNbAnneaux() + 1];
                    for (int i = 0; i < joueurs[tour].getNbAnneaux() + 1; i++) {
                        choixPossibles[i] = Integer.toString(i);
                    }
                    int choixPoints = Integer.parseInt(Router.getInstance().choix("Vous etes mort. Vous marquez " + points
                            + " points. Combien d'anneaux karmiques voulez vous utiliser ?", "0", choixPossibles));
                    points += choixPoints;
                    joueurs[tour].setNbAnneaux(joueurs[tour].getNbAnneaux() - choixPoints);
                } else {
                    Router.getInstance().instructions(
                            "Vous etes mort. Vous marquez " + points + " points. Vous n'avez pas d'anneaux karmiques.");
                }
                // Affecter les points ou ajouter un anneau
                if (points > 7) {
                    gagnant = tour;
                    etape = Etape.GAGNANT;
                    tour();
                    break;
                } else if (joueurs[tour].getPoints() <= points && joueurs[tour].getPoints() >= 4) {
                    joueurs[tour].setPoints(points);
                } else {
                    joueurs[tour].addAnneau();
                }
                // TODO : regler le pb où toutes les oeuvres ne sont pas défaussées
                fosse.ajouter(oeuvres.piocher(oeuvres.size()));
                System.out.println(main.size());
                System.out.println(vieFuture.size());
                main.ajouter(vieFuture.piocher(vieFuture.size()));
                System.out.println(main.size());
                System.out.println(vieFuture.size());
                if (main.size() < 6) {
                    deck.ajouter(source.piocher(6 - main.size()));
                    Router.getInstance().instructions("Vous avez moins de 6 cartes dans votre main. Vous piochez "
                            + (6 - main.size()) + " cartes.");
                }
                etape = Etape.TOUR_SUIVANT;
                tour();
                break;
            case PIOCHER_DECK:
                main.ajouter(deck.piocher());
                Router.getInstance().update();
                etape = Etape.CHOISIR_CARTE_MAIN;
                tour();
                break;
            case CHOISIR_CARTE_MAIN:
                Router.getInstance().instructions("Veuillez choisir une carte dans votre main ou passez votre tour.");
                actionsPossibles.clear();
                actionsPossibles.add(Action.CHOISIR_CARTE_MAIN);
                if (deck.size() > 0) {
                    actionsPossibles.add(Action.PASSER);
                }
                etape = Etape.CHOISIR_UTILISATION_CARTE;
                break;
            case CHOISIR_UTILISATION_CARTE:
                String choix = Router.getInstance().choix(
                        "Veuillez choisir une utilisation pour la carte " + carteChoisie.getNom(),
                        "Points", "Points", "Pouvoir", "Futur");
                System.out.println(choix);
                if (choix == null) {
                    etape = Etape.CHOISIR_CARTE_MAIN;
                    tour();
                    break;
                }
                switch (choix) {
                    case "Points":
                        oeuvres.ajouter(main.piocher(carteChoisie));
                        etape = Etape.TOUR_SUIVANT;
                        tour();
                        break;
                    case "Pouvoir":
                        carteChoisie.pouvoir();
                        etape = Etape.PROPOSER_CARTE;
                        tour();
                        break;
                    case "Futur":
                        vieFuture.ajouter(main.piocher(carteChoisie));
                        etape = Etape.TOUR_SUIVANT;
                        tour();
                        break;
                }
                Router.getInstance().update();
                break;
            case PROPOSER_CARTE:
                Router.getInstance().setScene("plateauPlaceholder");
                Router.getInstance()
                        .instructions("Veuillez laisser votre adversaire choisir d'accepter ou non la carte.");
                String choixAdversaire = Router.getInstance().choix(
                        "Voulez vous accepter la carte " + carteChoisie.getNom(), "Accepter", "Accepter", "Refuser");
                if (choixAdversaire == "Accepter") {
                    VieFuture vieFutureAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2)
                            .getVieFuture();
                    vieFutureAdv.ajouter(main.piocher(carteChoisie));
                } else {
                    fosse.ajouter(carteChoisie);
                }
                etape = Etape.TOUR_SUIVANT;
                tour();
                break;
            case PROPOSER_CARTE_REJOUER:
                Router.getInstance().setScene("plateauPlaceholder");
                Router.getInstance()
                        .instructions("Veuillez laisser votre adversaire choisir d'accepter ou non la carte.");
                choixAdversaire = Router.getInstance().choix(
                        "Voulez vous accepter la carte " + carteChoisie.getNom(), "Accepter", "Accepter", "Refuser");
                if (choixAdversaire == "Accepter") {
                    VieFuture vieFutureAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2)
                            .getVieFuture();
                    vieFutureAdv.ajouter(main.piocher(carteChoisie));
                } else {
                    fosse.ajouter(carteChoisie);
                }
                Router.getInstance().instructions("Le joueur peut rejouer une carte. Laissez-le continuer.");
                Router.getInstance().setScene("plateau");
                etape = Etape.CHOISIR_CARTE_MAIN;
                tour();
                break;
            case TOUR_SUIVANT:
                tour = (tour + 1) % 2;
                etape = Etape.DEBUT;
                actionsPossibles.clear();
                Router.getInstance().setScene("plateauPlaceholder");
                Router.getInstance().instructions("Changement de joueur ! Ne trichez pas !");
                Router.getInstance().setScene("plateau");
                tour();
                break;
            case GAGNANT:
                Router.getInstance().instructions("Le joueur " + joueurs[(gagnant + 1)].getNom() + " a gagné !");
                break;
        }
        return;
    }
}
