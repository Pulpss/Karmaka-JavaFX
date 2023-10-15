package karmaka.classes;

public abstract class Carte {
    public String nom;
    public Couleur couleur;
    public String description;
    public int points;

    public Carte(String nom, Couleur couleur, String description, int points) {
        this.nom = nom;
        this.couleur = couleur;
        this.description = description;
        this.points = points;
    }

    public abstract void action();
}
