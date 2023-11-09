package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Mimetisme extends Carte {
    public Mimetisme() {
        super("Mimetisme", Couleur.MOSAIQUE, "Choisissez un Rival. Copiez le pouvoir de son Oeuvre Exposée.", 1);
    }

    public void pouvoir() {
        System.out.println("Mimetisme");
    }
}
