package karmaka.classes;

public abstract class Carte {
    private String nom;
    private Couleur couleur;
    private String description;
    private int points;

    public Carte(String nom, Couleur couleur, String description, int points) {
        this.nom = nom;
        this.couleur = couleur;
        this.description = description;
        this.points = points;
    }

    public abstract void action();
}
