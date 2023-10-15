package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Bassesse extends Carte {
    public Bassesse() {
        super("Bassesse", Couleur.ROUGE, "Défaussez au hasard 2 cartes de la Main d’un rival.", 3);
    }

    public void action() {
        System.out.println("Bassesse");
    }
}
