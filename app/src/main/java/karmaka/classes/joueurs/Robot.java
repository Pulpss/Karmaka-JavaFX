package karmaka.classes.joueurs;

import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.view.Router;

public class Robot extends Joueur {
    private enum Strategie {
        AGRESSIF, DEFENSIF, FERMIER
    }

    private Strategie strategie;

    public Robot(String nom) {
        super(nom);
        strategie = Strategie.AGRESSIF;
    }

    public Carte choix(String message, ArrayList<Carte> cartes) {
        Carte c = cartes.get(0);
        if (message.contains("choisit une carte de sa main pour la jouer")) {
            switch (strategie) {
                case AGRESSIF:
                    // premiere carte rouge ou premiere carte
                    c = cartes.stream().filter(carte -> carte.getCouleur() == Couleur.ROUGE).findFirst()
                           .orElse(cartes.get(0));
                    break;
                case DEFENSIF:
                    // premiere carte bleu ou premiere carte
                    c = cartes.stream().filter(carte -> carte.getCouleur() == Couleur.BLEU).findFirst()
                            .orElse(cartes.get(0));
                    break;
                case FERMIER:
                    // premiere carte verte ou premiere carte
                    c = cartes.stream().filter(carte -> carte.getCouleur() == Couleur.VERT).findFirst()
                            .orElse(cartes.get(0));
                    break;
            }
        }
        return c;
    }

    public String choix(String message, String... choix) {
        if (message.contains("va choisir une utilisation pour la carte")) {
            Couleur couleur = Partie.getInstance().getCarteChoisie().getCouleur();
            switch (strategie) {
                case AGRESSIF:
                    // premiere utilisation rouge ou premiere utilisation
                    return couleur == Couleur.ROUGE ? "Pouvoir" : "Points";
                case DEFENSIF:
                    // premiere utilisation bleu ou premiere utilisation
                    return couleur == Couleur.BLEU ? "Pouvoir" : "Points";
                case FERMIER:
                    // premiere utilisation verte ou premiere utilisation
                    return couleur == Couleur.VERT ? "Points" : "Futur";
                default:
                    break;
            }
        }
        else if (message.contains("Le bot adverse va vérifier s'il accepte la carte")) {
            Couleur couleur = Partie.getInstance().getCarteChoisie().getCouleur();
            switch (strategie) {
                case AGRESSIF:
                    // premiere utilisation rouge ou premiere utilisation
                    return couleur == Couleur.ROUGE ? "Accepter" : "Refuser";
                case DEFENSIF:
                    // premiere utilisation bleu ou premiere utilisation
                    return couleur == Couleur.BLEU ? "Accepter" : "Refuser";
                case FERMIER:
                    // premiere utilisation verte ou premiere utilisation
                    return couleur == Couleur.VERT ? "Accepter" : "Refuser";
                default:
                    break;
            }
        }
        return choix[(int) (Math.random() * choix.length)];
    }

    public void afficherCartes(String message, ArrayList<Carte> cartes) {
        return;
    }

    public void afficher(String message) {
        Router.getInstance().afficher(message);
        return;
    }

    public boolean isRobot() {
        return true;
    }
}
