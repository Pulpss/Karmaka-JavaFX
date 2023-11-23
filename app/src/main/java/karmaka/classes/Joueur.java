package karmaka.classes;

import karmaka.classes.cartes.Transmigration;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Oeuvres;
import karmaka.classes.piles.VieFuture;

public abstract class Joueur {
    private String nom;
    private int score;
    private int nbAnneaux;
    private int points;
    private Main main;
    private Deck deck;
    private VieFuture vieFuture;
    private Oeuvres oeuvres;
    //J'ai juste rajouté la mort
    boolean mort = false; 

    
    
    
    public Joueur(String nom) {
    	this.mort = false;
        this.nom = nom;
        this.score = 0;
        this.nbAnneaux = 0;
        this.points = 0;
        this.main = new Main();
        this.deck = new Deck();
        this.vieFuture = new VieFuture();
        this.oeuvres = new Oeuvres();
        // this.main.ajouter(new Transmigration());
        // this.vieFuture.ajouter(new Transmigration());
        // this.vieFuture.ajouter(new Transmigration());
        // this.vieFuture.ajouter(new Transmigration());
        // this.vieFuture.ajouter(new Transmigration());
        // this.vieFuture.ajouter(new Transmigration());
        // this.vieFuture.ajouter(new Transmigration());
        // this.vieFuture.ajouter(new Transmigration());
        // this.vieFuture.ajouter(new Transmigration());
        // this.vieFuture.ajouter(new Transmigration());
    }

    public Main getMain() {
        return main;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getPoints() {
        return points;
    }

    public VieFuture getVieFuture() {
        return vieFuture;
    }

    public Oeuvres getOeuvres() {
        return oeuvres;
    }

    public int getNbAnneaux() {
        return nbAnneaux;
    }

    public void setNbAnneaux(int nbAnneaux) {
        this.nbAnneaux = nbAnneaux;
    }

    public String getNom() {
        return nom;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addAnneau() {
        this.nbAnneaux += 1;
    }

}
