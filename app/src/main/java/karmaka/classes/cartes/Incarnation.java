package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Incarnation extends Carte {
    public Incarnation() {
        super("Incarnation", Couleur.MOSAIQUE, "Choisissez une de vos Oeuvres. Copiez son pouvoir.", 1);
    }
    
    public void action() {
        System.out.println("Incarnation");
    }
}
