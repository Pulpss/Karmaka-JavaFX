package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Voyage extends Carte {
    public Voyage() {
        super("Voyage", Couleur.VERT, "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.", 3);
    }

    public void action() {
        System.out.println("Voyage");
    }
}
