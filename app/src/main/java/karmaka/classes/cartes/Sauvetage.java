package karmaka.classes.cartes;

import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.view.Router;

public class Sauvetage extends Carte {
    public Sauvetage() {
        super("Sauvetage", Couleur.VERT, "Ajoutez à votre Main une des 3 dernières cartes de la Fosse.", 2);
    }

    public void pouvoir() {
        // TODO: tester
    	Router.getInstance().instructions("La carte Sauvetage va être jouée !");
        Fosse fosse = Partie.getInstance().getFosse();
        Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        ArrayList<Carte> troisCartes = new ArrayList<Carte>(
                fosse.getCartes().subList(Math.max(0, fosse.size() - 3 - 1), fosse.size() - 1));
        if (troisCartes.size() > 0) {
            Carte c = Router.getInstance().choix("Choisissez une carte à ajouter à votre main.", troisCartes);
            main.ajouter(fosse.piocher(c));
        } else {
            System.out.println("La fosse est vide.");
        }
    }
}
