package karmaka.classes.cartes;

import java.io.IOException;
import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Deck;
import karmaka.classes.piles.Source;

public class Voyage extends Carte {
    public Voyage() {
        super("Voyage", Couleur.VERT, "Puisez 3 cartes à la Source. Vous pouvez ensuite jouer une autre carte.", 3);
    }

    public void pouvoir() {
        System.out.println("Voyage");
        Source source = Partie.getInstance().getSource();
        Deck deck = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getDeck();
        ArrayList<Carte> troisCartes = new ArrayList<Carte>(source.getCartes().subList(Math.max(0, source.size() - 3), source.size()));
        if (troisCartes.size() > 0) {
            deck.ajouter(troisCartes);
        } else {
            System.out.println("La source ne contient pas assez de cartes.");
        }
    }
}
