package karmaka.classes;


import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Oeuvres;
import karmaka.classes.piles.VieFuture;

public abstract class Joueur {
    private String nom;
    private int echelleKarmique;
    private int nbAnneaux;
    private Main main;
    private Deck deck;
    private VieFuture vieFuture;
    private Oeuvres oeuvres;


    
    
    
    public Joueur(String nom) {
        this.nom = nom;
        this.echelleKarmique = 0;
        this.nbAnneaux = 0;
        this.main = new Main();
        this.deck = new Deck();
        this.vieFuture = new VieFuture();
        this.oeuvres = new Oeuvres();
    }

    public Main getMain() {
        return main;
    }
    
    public int getEchelleKarmique() {
        return echelleKarmique;
    }
    
    public Deck getDeck() {
        return deck;
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
    
    public void setEchelleKarmique(int echelleKarmique) {
        this.echelleKarmique = echelleKarmique;
    }
    
    public String getNom() {
        return nom;
    }

    public void addAnneau() {
        this.nbAnneaux += 1;
    }

}
