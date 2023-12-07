package karmaka.classes;


import java.io.Serializable;
import java.util.ArrayList;

import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Oeuvres;
import karmaka.classes.piles.VieFuture;

public abstract class Joueur implements Serializable {
    private String nom;
    private boolean mort;
    private int echelleKarmique;
    private int nbAnneaux;
    private Main main;
    private Deck deck;
    private VieFuture vieFuture;
    private Oeuvres oeuvres;
    
    public Joueur(String nom) {
        this.nom = nom;
        this.echelleKarmique = 4;
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

    public boolean isMort() {
        return mort;
    }

    public void setMort(boolean mort) {
        this.mort = mort;
    }

    abstract public Carte choix(String message, ArrayList<Carte> cartes);
    abstract public String choix(String message, String... options);
    abstract public void afficherCartes(String message, ArrayList<Carte> cartes);
    abstract public void afficher(String message);
}
