package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Oeuvres;
import karmaka.view.Router;

public class Vol extends Carte {
    public Vol() {
        super("Vol", Couleur.BLEU, "Placez dans votre Main l’Oeuvre Exposée d'un rival.", 3);
    }

    public void pouvoir() throws IOException {
        // TODO: tester
        System.out.println("Vol");
        Oeuvres oeuvresAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getOeuvres();
        Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        if (oeuvresAdv.size() > 0) {
            Carte c = Router.getInstance().choix("Choisissez une carte parmis les oeuvres de votre adversaire.",
                    oeuvresAdv.getCartes());
            main.ajouter(oeuvresAdv.piocher(c));
        } else {
            Router.getInstance().instructions("Votre adversaire n'a pas d'oeuvres.");
        }
        Partie.getInstance().tourSuivant();
    }
}
