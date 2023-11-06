package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Deni extends Carte {
    public Deni() {
        super("Deni", Couleur.BLEU, "Défaussez une carte de votre Main. Copiez le pouvoir de cette carte.", 2);
    }

    public void action() {
        System.out.println("Deni");
    }
}
