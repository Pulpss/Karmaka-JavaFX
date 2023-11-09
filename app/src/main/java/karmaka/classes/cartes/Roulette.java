package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Roulette extends Carte {
    public Roulette() {
        super("Roulette", Couleur.ROUGE,
                "Défaussez jusqu’à 2 cartes de votre Main. Vous pouvez ensuite puiser à la Source autant de carte(s) + 1.",
                2);
    }

    public void pouvoir() {
        System.out.println("Roulette");
    }
}
