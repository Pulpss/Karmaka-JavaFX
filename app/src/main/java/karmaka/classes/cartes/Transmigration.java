package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.view.Router;

public class Transmigration extends Carte {
    public Transmigration() {
        super("Transmigration", Couleur.BLEU, "Placez dans votre Main n’importequelle carte de votre Vie Future.", 1);
    }

    public void pouvoir() throws IOException {
        System.out.println("Transmigration");
        Joueur j = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
        if (j.getVieFuture().size() != 0) {
            Carte choix = Router.getInstance().choix("Veuillez choisir une carte dans votre Vie Future.", j.getVieFuture().getCartes());
            j.getMain().ajouter(j.getVieFuture().piocher(choix));
        } else {
            Router.getInstance().instructions("Il n'y a pas de cartes dans votre vie future.");
        }
        Partie.getInstance().tourSuivant();
    }
}
