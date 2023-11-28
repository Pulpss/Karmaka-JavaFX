package karmaka.classes.cartes;

import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.VieFuture;
import karmaka.view.Router;

public class Recyclage extends Carte {
    public Recyclage() {
        super("Recyclage", Couleur.VERT, "Ajoutez à votre Vie Future une des 3 dernières cartes de la Fosse.", 1);
    }

    public void pouvoir() {
        // TODO: tester
    	Router.getInstance().instructions("La carte Recyclage va être jouée !");
        Fosse fosse = Partie.getInstance().getFosse();
        VieFuture vieFuture = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getVieFuture();
        ArrayList<Carte> troisCartes = new ArrayList<Carte>(
                fosse.getCartes().subList(Math.max(0, fosse.size() - 3 - 1), fosse.size() - 1));
        if (troisCartes.size() > 0) {
            Carte c = Router.getInstance().choix("Choisissez une carte à ajouter à votre vie future.", troisCartes);
            vieFuture.ajouter(fosse.piocher(c));
        } else {
            System.out.println("La fosse est vide.");
        }
    }
}
