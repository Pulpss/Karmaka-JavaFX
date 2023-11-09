package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class DernierSouffle extends Carte {
    public DernierSouffle() {
        super("DernierSouffle", Couleur.ROUGE, "Le joueur de votre choix défausse une carte de sa Main.", 1);
    }

    public void pouvoir() {
        System.out.println("DernierSouffle");
    }
}
