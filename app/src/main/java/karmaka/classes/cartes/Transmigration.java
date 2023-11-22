package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.VieFuture;
import karmaka.view.Router;

public class Transmigration extends Carte {
    public Transmigration() {
        super("Transmigration", Couleur.BLEU, "Placez dans votre Main n’importequelle carte de votre Vie Future.", 1);
    }

    public void pouvoir() {
        System.out.println("Transmigration");
        VieFuture vieFuture = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getVieFuture();
        Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        if (vieFuture.size() != 0) {
            Carte choix = Router.getInstance().choix("Veuillez choisir une carte dans votre Vie Future.",
                    vieFuture.getCartes());
            main.ajouter(vieFuture.piocher(choix));
        } else {
            Router.getInstance().instructions("Il n'y a pas de cartes dans votre vie future.");
        }
    }
}
