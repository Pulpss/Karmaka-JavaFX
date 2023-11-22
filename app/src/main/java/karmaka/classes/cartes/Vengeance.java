package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Oeuvres;
import karmaka.view.Router;

public class Vengeance extends Carte {
    public Vengeance() {
        super("Vengeance", Couleur.ROUGE, "Défaussez l’Oeuvre Exposée d’un rival.", 3);
    }

    public void pouvoir() {
        // TODO: tester
        System.out.println("Vengeance");
        Oeuvres oeuvresAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getOeuvres();
        Fosse fosse = Partie.getInstance().getFosse();
        if (oeuvresAdv.size() > 0) {
            Carte c = Router.getInstance().choix("Choisissez une carte parmis les oeuvres de votre adversaire.",
                    oeuvresAdv.getCartes());
            fosse.ajouter(oeuvresAdv.piocher(c));
        } else {
            Router.getInstance().instructions("Votre adversaire n'a pas d'oeuvres.");
        }
    }
}
