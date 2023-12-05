package karmaka.classes;

import java.io.Serializable;
import java.util.ArrayList;

import karmaka.classes.Partie.Etape;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Source;

public class GameData implements Serializable {
    public Joueur[] joueurs = new Joueur[2];
    public Source source = new Source();
    public Fosse fosse = new Fosse();
    public ArrayList<Action> actionsPossibles = new ArrayList<Action>();
    public int tour = 0; // 0 = j1, 1 = j2
    public int gagnant;
    public Etape etape = Etape.DEBUT;
    public Carte carteChoisie = null;
}
