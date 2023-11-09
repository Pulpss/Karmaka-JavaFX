package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Longevite extends Carte {
    public Longevite() {
        super("Longevite", Couleur.VERT, "Placez 2 cartes puisées à la Source sur la Pile d'un joueur.", 2);
    }
    
    public void pouvoir() {
        System.out.println("Longevite");
    }
}
