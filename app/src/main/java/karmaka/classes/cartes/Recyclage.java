package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Recyclage extends Carte {
    public Recyclage() {
        super("Recyclage", Couleur.VERT, "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse.", 1);
    }

    public void pouvoir() {
        System.out.println("Recyclage");
    }
}
