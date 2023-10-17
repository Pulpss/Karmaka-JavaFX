package karmaka.classes;

import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Source;

public final class Partie {
    private static Joueur joueur1;
    private static Joueur joueur2;
    private static Source source;
    private static Fosse fosse;
    private static Partie instance = null;

    private Partie(Joueur j1, Joueur j2) {
        joueur1 = j1;
        joueur2 = j2;
    }

    public static void init(Joueur joueur1, Joueur joueur2) {
        if (instance == null) {
            instance = new Partie(joueur1, joueur2);
        }
    }

    public Partie getInstance() {
        return instance;
    }
}
