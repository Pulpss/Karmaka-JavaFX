package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Fournaise extends Carte {
    public Fournaise() {
        super("Fournaise", Couleur.ROUGE, "Défaussez les 2 premières cartes de la Vie Future d'un rival.", 2);
    }
    
    public void pouvoir() {
        System.out.println("Fournaise");
    }
}
