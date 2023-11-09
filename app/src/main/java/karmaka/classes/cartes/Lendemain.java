package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Lendemain extends Carte {
    public Lendemain() {
        super("Lendemain", Couleur.VERT, "Puisez une carte à la Source. Vous pouvez ensuite jouer une autre carte.", 1);
    }

    public void pouvoir() {
        System.out.println("Lendemain");
    }
}
