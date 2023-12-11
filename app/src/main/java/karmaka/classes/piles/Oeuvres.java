package karmaka.classes.piles;

import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Pile;

/**
 * La classe Oeuvres représente les paquets de cartes associés aux différents joueurs jouant à Karmaka (leurs packets Oeuvres).
 * Elle étend la classe abstraite Pile et utilise une liste d'objets Carte pour stocker les cartes du paquet.
 * @see Pile
 * @see Carte
 */
public class Oeuvres extends Pile {
	/**
     * Constructeur par défaut de la classe Oeuvres.
     * Initialise le paquet de cartes en utilisant une liste vide d'objets Carte.
     */
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
