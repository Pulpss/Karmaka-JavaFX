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

    public void pouvoir() throws IOException {
        System.out.println("Mimetisme");
        Oeuvres oeuvresAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getOeuvres();
        if (oeuvresAdv.size() > 0) {
            Carte choix = Router.getInstance().choix("Veuillez choisir la carte de l'adversaire que vous voulez copier: ",
                    oeuvresAdv.getCartes());
            choix.pouvoir();
        } else {
            Router.getInstance().instructions("L'adversaire n'avait pas de cartes dans ses oeuvres.");
        }
    }
}
