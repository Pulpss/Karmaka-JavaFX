package karmaka.classes.cartes;

import java.io.IOException;
import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.view.Router;

public class Sauvetage extends Carte {
    public Sauvetage() {
        super("Sauvetage", Couleur.VERT, "Ajoutez à votre Main une des3 dernières cartes de la Fosse.", 2);
    }

    public void pouvoir() throws IOException {
        // TODO: tester
        System.out.println("Sauvetage");
        Fosse fosse = Partie.getInstance().getFosse();
        Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        ArrayList<Carte> troisCartes = new ArrayList<Carte>(fosse.getCartes().subList(Math.max(0, fosse.size() - 3), fosse.size()));
        if (troisCartes.size() > 0) {
            Carte c = Router.getInstance().choix("Choisissez une carte à ajouter à votre main.", troisCartes);
            main.ajouter(fosse.piocher(c));
        } else {
            System.out.println("La fosse ne contient pas assez de cartes.");
        }
        Partie.getInstance().tourSuivant();
    }
}
