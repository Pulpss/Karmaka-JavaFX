package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Semis extends Carte {
    public Semis() {
        super("Semis", Couleur.VERT,
                "Puisez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main.", 2);
    }

    public void pouvoir() {
        System.out.println("Semis");
    }
}
