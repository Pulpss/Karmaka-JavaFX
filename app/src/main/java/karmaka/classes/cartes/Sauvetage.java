package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Sauvetage extends Carte {
    public Sauvetage() {
        super("Sauvetage", Couleur.VERT, "Ajoutez à votre Main une des3 dernières cartes de la Fosse.", 2);
    }

    public void action() {
        System.out.println("Sauvetage");
    }
}
