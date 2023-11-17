package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Duperie extends Carte {
    public Duperie() {
        super("Duperie", Couleur.BLEU, "Regardez 3 cartes de la Main d’un rival; ajoutez-en une à votre Main.", 3);
    }

    public void pouvoir() {
        System.out.println("Duperie");
    }
}
