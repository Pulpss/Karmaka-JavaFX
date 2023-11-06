package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Jubile extends Carte {
    public Jubile() {
        super("Jubile", Couleur.VERT, "Placez jusqu’à 2 cartes de votre Main sur vos Oeuvres", 3);
    }
    
    public void action() {
        System.out.println("Jubile");
    }
}
