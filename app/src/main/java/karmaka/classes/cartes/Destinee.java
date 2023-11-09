package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;

public class Destinee extends Carte {
    public Destinee() {
        super("Destinee", Couleur.BLEU,
                "Regardez les 3 premières cartes de la Source; ajoutez-en jusqu’à 2 à votre Vie Future. Replacez le reste dans l'ordre souhaité.",
                2);
    }

    public void pouvoir() {
        System.out.println("Destinee");
    }
}
