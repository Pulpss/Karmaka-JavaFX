package karmaka.classes;

import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Source;

public class Partie {
    private Joueur joueur1;
    private Joueur joueur2;
    private Source source;
    private Fosse fosse;

    public Partie(Joueur joueur1, Joueur joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }
}
