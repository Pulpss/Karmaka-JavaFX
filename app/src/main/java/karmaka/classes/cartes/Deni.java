package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.view.Router;

public class Deni extends Carte {
    public Deni() {
        super("Deni", Couleur.BLEU, "Défaussez une carte de votre Main. Copiez le pouvoir de cette carte.", 2);
    }

    public void pouvoir() throws IOException {
        // TODO : tester
        System.out.println("Deni");
        Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        if (main.size() > 0) {
            Fosse fosse = Partie.getInstance().getFosse();
            Carte c = Router.getInstance().choix("Choisissez une carte à défausser.", main.getCartes());
            fosse.ajouter(main.piocher(c));
            c.pouvoir();// Active le pouvoir de la carte défaussée
        } else {
            Router.getInstance().instructions("Vous n'avez pas de carte dans votre main.");
        }
        Partie.getInstance().tourSuivant();
    }
}
