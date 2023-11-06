package karmaka.classes.piles;

import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Pile;

public class Deck extends Pile {
    static ArrayList<Carte> cartesInit = new ArrayList<Carte>();

    public Deck() {
        super(cartesInit);
    }
}
