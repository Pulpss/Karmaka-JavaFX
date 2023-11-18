package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Source;
import karmaka.view.Router;

public class Longevite extends Carte {
    public Longevite() {
        super("Longevite", Couleur.VERT, "Placez 2 cartes puisées à la Source sur la Pile d'un joueur.", 2);
    }

    public void pouvoir() throws IOException{
        System.out.println("Longevite");
        //TODO : Test
        Source source = Partie.getInstance().getSource();
        // Je n'ai pas implémenté l'option où on veut que l'adversaire pioche 2 cartes de la Source (elle me semblait inutile)
        Deck deck = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getDeck();
        Carte carte = source.getCartes().get(0);
        source.piocher(carte);
        deck.ajouter(carte);
        carte = source.getCartes().get(0);
        source.piocher(carte);
        deck.ajouter(carte);
    	Router.getInstance().instructions("Les deux cartes au dessus de la source ont été piochées !");
    	Partie.getInstance().tourSuivant();
    }
}
