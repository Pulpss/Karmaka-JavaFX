package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class CoupDoeil extends Carte {
    public CoupDoeil() {
        super("CoupDoeil", Couleur.BLEU, "Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte.", 1);
    }

    public void pouvoir() {
        System.out.println("CoupDoeil");
    }
}
