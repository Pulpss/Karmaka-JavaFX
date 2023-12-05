package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Oeuvres;

public class Incarnation extends Carte {
    public Incarnation() {
        super("Incarnation", Couleur.MOSAIQUE, "Choisissez une de vos Oeuvres. Copiez son pouvoir.", 1);
    }

    public void pouvoir() throws IOException {
        Joueur joueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
    	joueur.afficher("La carte Incarnation va être jouée !");
        Oeuvres oeuvres = joueur.getOeuvres();
        if (oeuvres.size() > 0) {
            Carte choix = joueur.choix(
                    "Veuillez choisir la carte de votre pile Oeuvres que vous voulez copier : ",
                    oeuvres.getCartes());
            choix.pouvoir();
        } else {
            joueur.afficher("Vous n'aviez pas de cartes dans votre pile Oeuvres !");
        }
    }
}
