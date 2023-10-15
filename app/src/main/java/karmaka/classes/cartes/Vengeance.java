package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Vengeance extends Carte {
    public Vengeance() {
        super("Vengeance", Couleur.ROUGE, "Défaussez l’Oeuvre Exposée d’un rival.", 3);
    }

    public void action() {
        System.out.println("Vengeance");
    }
}
