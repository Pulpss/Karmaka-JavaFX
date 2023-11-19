package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Oeuvres;
import karmaka.view.Router;

public class Incarnation extends Carte {
    public Incarnation() {
        super("Incarnation", Couleur.MOSAIQUE, "Choisissez une de vos Oeuvres. Copiez son pouvoir.", 1);
    }

    public void pouvoir() throws IOException {
        System.out.println("Incarnation");
        Oeuvres oeuvres = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getOeuvres();
        if (oeuvres.size() > 0) {
            Carte choix = Router.getInstance().choix(
                    "Veuillez choisir la carte de votre pile Oeuvres que vous voulez copier : ",
                    oeuvres.getCartes());
            choix.pouvoir();
        } else {
            Router.getInstance().instructions("Vous n'aviez pas de cartes dans votre pile Oeuvres !");
        }
        Partie.getInstance().tourSuivant();
    }
}
