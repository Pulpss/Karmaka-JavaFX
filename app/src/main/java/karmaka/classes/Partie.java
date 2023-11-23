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
    private static Carte carteChoisie = null;

    public enum Etape {
        DEBUT, PIOCHER_DECK, JOUER_CARTE, CHOISIR_CARTE_MAIN, CHOISIR_UTILISATION_CARTE, PROPOSER_CARTE,
        PROPOSER_CARTE_REJOUER, TOUR_SUIVANT
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
        
        // Probleme si meme attribut pour 2 joueurs ???
        int compteurRouge;
        int compteurVert;
        int compteurBleu;
        int compteurMosaique;
        int compteurMax;
        
        Oeuvres oeuvres = joueurs[tour].getOeuvres();
        VieFuture vieFuture = joueurs[tour].getVieFuture();
        switch (etape) {
            case DEBUT:
                if (deck.getCartes().size() > 0) {
                    Router.getInstance().instructions("Veuillez piocher une carte dans votre deck");
                    actionsPossibles.clear();
                    actionsPossibles.add(Action.PIOCHER_DECK);
                    etape = Etape.PIOCHER_DECK;
                    etape = Etape.CHOISIR_CARTE_MAIN;
                    tour();
                	}                     
                else if(joueurs[tour].mort == false && main.getCartes().size() > 0){
                	etape = Etape.CHOISIR_CARTE_MAIN;
                	tour();

                	}
                else if(joueurs[tour].mort == false && main.getCartes().size() == 0) { //Je sais pas si joueurs[tour].mort fonctionne
                	Router.getInstance().instructions("Vous etes mort.");
                	joueurs[tour].mort = true;
                	etape = Etape.TOUR_SUIVANT;
            		}
                else if(joueurs[tour].mort == true) {
                	Router.getInstance().instructions("Vous etes sur le point de vous reincarner, nous comptons vos points.");
                	compteurRouge = compteurVert = compteurBleu = compteurMosaique = 0;
                	// On recup la pile oeuvre du joueur
                	Fosse fosse = Partie.getInstance().getFosse();
                	Oeuvres oeuvreJoueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getOeuvres();
                	// On compte les points tout en défaussant la pile dans la fosse.
                	for (int i = 0; i < oeuvreJoueur.size(); i++) {
                		// Defausse la carte
                		fosse.ajouter(oeuvreJoueur.piocher());
                		// On recupere la carte de la defausse et on inspecte sa couleur et sa valeur
                		Carte CarteRetiree = fosse.getCartes().get(0);
                		if (CarteRetiree.Couleur == ROUGE) { 
                			compteurRouge += CarteRetiree.points; // Je n'arrive pas a recuperer les attributs des cartes (visibilité ?)
                		}
                		else if (CarteRetiree.Couleur == BLEU) {
                			compteurBleu += CarteRetiree.points;
                		}
                		else if (CarteRetiree.Couleur == VERT) {
                			compteurVert += CarteRetiree.points;
                		}
                		else {
                			compteurMosaique += CarteRetiree.points;
                		}
                		
                	}
                	//On comptabilise les points
                	compteurMax = compteurRouge;
                	if (compteurRouge < compteurBleu && compteurBleu >= compteurVert) {
                		compteurMax = compteurBleu;
                	}
                	else if (compteurRouge < compteurVert && compteurBleu <= compteurVert)
                	{
                		compteurMax = compteurVert;
                	}
                	compteurMax += compteurMosaique;
                	if (compteurMax >= joueurs[tour].score) {
                		joueurs[tour].score += 1; //Probleme de visibilité ?
                		if (joueurs[tour].score > 8) {
                			// Le dernier niveau est le niveau 8
                			Router.getInstance().instructions("Vous avez gagné la partie !");
                			// TODO : FIN DE PARTIE
                		}
                		else {
                			Router.getInstance().instructions("Vous gagnez un niveau, vous êtes niveau " + joueurs[tour].score + " !");
                		}
                	}
                	else if (compteurMax + joueurs[tour].nbAnneaux  >= joueurs[tour].score) {
                		int difference = joueurs[tour].score - compteurMax;
                		Router.getInstance().instructions("Vous pouvez gagner un niveau si vous dépensez " + difference + " anneaux karmiques. Le souhaitez vous ?");
                		//Je ne sais pas comment gérer ce choix, si le joueur refuse, il gagne un anneau karmique, sinon il perd le nb d'anneaux indiqué et gagne un niveau
                	}
                	else {
                		Router.getInstance().instructions("Vous n'aviez pas assez de points ! Prenez un anneau karmique en compensation.");
                		joueurs[tour].nbAnneaux += 1;
                	}
                	Router.getInstance().instructions("Nous allons maintenant recompléter votre main et votre deck.");
                	while (vieFuture.size() != 0) {
                		main.ajouter(vieFuture.piocher());
                		}
                	while ((main.size()+deck.size()) <= 6) {
                		deck.ajouter(source.piocher());
                	}
                	Router.getInstance().instructions("Votre reincarnation a pris du temps, vous passez votre tour !");
                	etape = Etape.TOUR_SUIVANT;
                }
                break;

            case PIOCHER_DECK:
                main.ajouter(deck.piocher());
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
                        joueurs[tour].setPoints(joueurs[tour].getPoints() + carteChoisie.getPoints());
                        etape = Etape.TOUR_SUIVANT;
                        tour();
                        break;
                    case "Pouvoir":
                        carteChoisie.pouvoir();
                        etape = Etape.PROPOSER_CARTE;
                        tour();
                        // Encore non fonctionnel, s'occupe des cas après l'utilisation du pouvoir de la
                        // carte
                        // L'adversaire accepte ou non la carte jouée, s'il accete, elle va dans sa vie
                        // future, sinon elle va dans la fosse.
                        /*
                         * boolean AccepteCarte = true;
                         * if (AccepteCarte == true) {
                         * VieFuture viefutureadv =
                         * Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) %
                         * 2).getVieFuture();
                         * viefuturadv.ajouter(main.piocher(carteChoisie));
                         * }
                         * else {
                         * fosse.ajouter(carteChoisie);
                         * }
                         */
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
        }
        return;
    }
}
