package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class RevesBrises extends Carte {
    public RevesBrises() {
        super("RevesBrises", Couleur.BLEU, "Placez la première carte de la Vie Future d'un rival sur la vôtre.", 2);
    }

    public void action() {
        System.out.println("RevesBrises");
    }
}
