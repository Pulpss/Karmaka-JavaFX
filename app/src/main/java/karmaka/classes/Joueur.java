package karmaka.classes;

import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Oeuvres;
import karmaka.classes.piles.VieFuture;

public abstract class Joueur {
    private String nom;
    private int score;
    private int nbAnneaux;
    private int points;
    private Main main = new Main();
    private Deck deck = new Deck();
    private VieFuture vieFuture = new VieFuture();
    private Oeuvres oeuvres = new Oeuvres();

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
        this.nbAnneaux = 0;
        this.points = 0;
    }

    public Main getMain() {
        return main;
    }

    public Deck getDeck() {
        return deck;
    }
}
