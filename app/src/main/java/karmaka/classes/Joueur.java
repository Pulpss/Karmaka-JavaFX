package karmaka.classes;

public abstract class Joueur {
    private String nom;
    private int score;
    private int nbAnneaux;
    private int points;

    public Joueur(String nom) {
        this.nom = nom;
        this.score = 0;
        this.nbAnneaux = 0;
        this.points = 0;
    }
}
