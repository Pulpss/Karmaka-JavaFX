package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;

public class Deni extends Carte {
    public Deni() {
        super("Deni", Couleur.BLEU, "Défaussez une carte de votre Main. Copiez le pouvoir de cette carte.", 2);
    }

    public void pouvoir() throws IOException {
        // TODO : tester
        Joueur joueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
    	joueur.afficher("La carte Deni va être jouée !");
        Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        if (main.size() > 0) {
            Fosse fosse = Partie.getInstance().getFosse();
            Carte c = joueur.choix("Choisissez une carte à défausser.", main.getCartes());
            fosse.ajouter(main.piocher(c));
            c.pouvoir();// Active le pouvoir de la carte défaussée
        } else {
            joueur.afficher("Vous n'avez pas de carte dans votre main.");
        }
    }
}
