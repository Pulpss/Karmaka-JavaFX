package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Crise extends Carte {
    public Crise() {
        super("Crise", Couleur.ROUGE, "Le rival de votre choix défausse une de ses Oeuvres.", 2);
    }

    public void action() {
        System.out.println("Crise");
    }
}
