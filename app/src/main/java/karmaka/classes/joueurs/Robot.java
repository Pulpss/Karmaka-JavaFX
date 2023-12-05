package karmaka.classes.joueurs;

import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Joueur;

public class Robot extends Joueur {
    public Robot(String nom) {
        super(nom);
    }

    public Carte choix(String message, ArrayList<Carte> cartes) {
        return cartes.get((int) (Math.random() * cartes.size()));
    }

    public String choix(String message, String... choix) {
        return choix[(int) (Math.random() * choix.length)];
    }

    public void afficherCartes(String message, ArrayList<Carte> cartes) {
        return;
    }

    public void afficher(String message) {
        return;
    }
}
