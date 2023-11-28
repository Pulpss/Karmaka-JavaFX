package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Oeuvres;
import karmaka.view.Router;

public class Mimetisme extends Carte {
    public Mimetisme() {
        super("Mimetisme", Couleur.MOSAIQUE, "Choisissez un Rival. Copiez le pouvoir de son Oeuvre Exposée.", 1);
    }
    // TODO : TEST
    public void pouvoir() throws IOException {
    	Router.getInstance().instructions("La carte Mimetisme va être jouée !");
        Oeuvres oeuvresAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getOeuvres();
        if (oeuvresAdv.size() > 0) {
        	Router.getInstance().instructions("Vous copiez l'oeuvre exposée de votre adversaire !");
            Carte c = oeuvresAdv.getCartes().get(oeuvresAdv.size()-1);
            c.pouvoir();
        } else {
            Router.getInstance().instructions("L'adversaire n'avait pas de cartes dans ses oeuvres.");
        }
    }
}
