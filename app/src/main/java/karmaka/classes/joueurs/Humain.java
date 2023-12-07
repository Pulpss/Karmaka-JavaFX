package karmaka.classes.joueurs;

import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Joueur;
import karmaka.view.Router;

public class Humain extends Joueur {
    public Humain(String nom) {
        super(nom);
    }

    public Carte choix(String message, ArrayList<Carte> cartes) {
        return Router.getInstance().choix(message, cartes);
    }

    public String choix(String message, String... choix) {
        return Router.getInstance().choix(message, choix);
    }

    public void afficherCartes(String message, ArrayList<Carte> cartes) {
        Router.getInstance().afficherCartes(message, cartes);
    }

    public void afficher(String message) {
        Router.getInstance().afficher(message);
    }

    public boolean isRobot() {
        return false;
    }
}
