package karmaka.classes.piles;

import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Pile;

public class Oeuvres extends Pile {
    public Oeuvres() {
        super(new ArrayList<Carte>());
    }

    public int calculerPoints() {
        // 0: Rouge, 1: Vert, 2: Bleu, 3: Mosaique
        int[] points = { 0, 0, 0, 0 };
        for (Carte c : super.getCartes()) {
            points[c.getCouleur().ordinal()] += c.getPoints();
        }
        return Math.max(points[0], Math.max(points[1], points[2])) + points[3];
    }
}
