package karmaka.classes.cartes;

import java.io.IOException;
import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.VieFuture;
import karmaka.view.Router;

public class Fournaise extends Carte {
    public Fournaise() {
        super("Fournaise", Couleur.ROUGE, "Défaussez les 2 premières cartes de la Vie Future d'un rival.", 2);
    }

    public void pouvoir() throws IOException {
        // TODO : tester
        System.out.println("Fournaise");
        VieFuture vieFutureAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2)
                .getVieFuture();
        Fosse fosse = Partie.getInstance().getFosse();
        ArrayList<Carte> cartes = vieFutureAdv.piocher(Math.min(2, vieFutureAdv.size()));
        if (vieFutureAdv.size() > 0) {
            fosse.ajouter(cartes);
        } else {
            Router.getInstance().instructions("L'adversaire n'a pas de carte dans sa vie future !");
        }
        Partie.getInstance().tourSuivant();
    }
}
