package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.VieFuture;
import karmaka.view.Router;

public class RevesBrises extends Carte {
    public RevesBrises() {
        super("RevesBrises", Couleur.BLEU, "Placez la première carte de la Vie Future d'un rival sur la vôtre.", 2);
    }

    public void pouvoir() throws IOException {
        // TODO: tester
        System.out.println("RevesBrises");
        VieFuture vieFutureAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1)%2).getVieFuture();
        VieFuture vieFuture = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getVieFuture();
        if (vieFutureAdv.size() > 0) {
            vieFuture.ajouter(vieFutureAdv.piocher());
            Router.getInstance().instructions("La carte a été ajouté à votre vie future.");
        } else {
            Router.getInstance().instructions("L'adversaire n'avait pas de cartes dans sa vie future.");
        }
        Partie.getInstance().tourSuivant();
    }
}
