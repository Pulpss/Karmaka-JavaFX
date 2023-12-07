package karmaka.classes.joueurs;

import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;

public class Robot extends Joueur {
    private enum Strategie {
        AGRESSIF, DEFENSIF, FERMIER
    }

    private Strategie strategie;

    public Robot(String nom) {
        super(nom);
    }

    public Carte choix(String message, ArrayList<Carte> cartes) {
        Carte c;
        if (message == "robot choisit carte main") { 
            switch (strategie) {
                case AGRESSIF:
                    // premiere carte rouge ou premiere carte
                    c = cartes.stream().filter(carte -> carte.getCouleur() == Couleur.ROUGE).findFirst().orElse(cartes.get(0));
                    break;
                case DEFENSIF:
                    // premiere carte bleu ou premiere carte
                    c = cartes.stream().filter(carte -> carte.getCouleur() == Couleur.BLEU).findFirst().orElse(cartes.get(0));
                case FERMIER:
                    // premiere carte verte ou premiere carte
                    c = cartes.stream().filter(carte -> carte.getCouleur() == Couleur.VERT).findFirst().orElse(cartes.get(0));
                default:
                    break;
            }
        }
        return cartes.get((int) (Math.random() * cartes.size()));
    }

    public String choix(String message, String... choix) {
        if (message.contains("Veuillez choisir une utilisation pour la carte")) {
            switch (Partie.getInstance().getCarteChoisie().getCouleur()) {
                case Couleur.BLEU:
                    return "Utiliser le pouvoir";
                    break;
            
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
        return;
    }

    public boolean isRobot() {
        return true;
    }
}
