package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Transmigration extends Carte {
    public Transmigration() {
        super("Transmigration", Couleur.BLEU, "Placez dans votre Main n’importequelle carte de votre Vie Future.", 1);
    }

    public void action() {
        System.out.println("Transmigration");
    }
}
