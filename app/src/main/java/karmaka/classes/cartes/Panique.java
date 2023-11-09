package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Panique extends Carte {
    public Panique() {
        super("Panique", Couleur.ROUGE,
                "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte.", 1);
    }

    public void pouvoir() {
        System.out.println("Panique");
    }
}
