package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Vol extends Carte {
    public Vol() {
        super("Vol", Couleur.BLEU, "Placez dans votre Main l’Oeuvre Exposée d'un rival.", 3);
    }
    
    public void action() {
        System.out.println("Vol");
    }
}
