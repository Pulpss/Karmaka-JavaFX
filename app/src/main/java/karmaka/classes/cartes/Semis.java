package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Source;
import karmaka.classes.piles.VieFuture;
import karmaka.view.Router;

public class Semis extends Carte {
    public Semis() {
        super("Semis", Couleur.VERT,
                "Puisez 2 cartes à la Source, puis placez sur votre Vie Future 2 cartes de votre Main.", 2);
    }

    public void pouvoir() {
        // TODO: tester
        System.out.println("Semis");
        Source source = Partie.getInstance().getSource();
        if (source.size() > 0) {
            source.piocher(Math.min(2, source.size()));
            Router.getInstance().instructions("Les cartes ont été puisées.");
        } else {
            Router.getInstance().instructions("La source est vide.");
        }
        Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        VieFuture vieFuture = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getVieFuture();
        if (main.size() > 0) {
            Carte choix = Router.getInstance().choix(
                    "Veuillez choisir une première carte dans votre main à mettre sur votre vie future.",
                    main.getCartes());
            vieFuture.ajouter(main.piocher(choix));
            if (main.size() > 0) {
                choix = Router.getInstance().choix(
                        "Veuillez choisir une deuxième carte dans votre main à mettre sur votre vie future.",
                        main.getCartes());
                vieFuture.ajouter(main.piocher(choix));
            } else {
                Router.getInstance().instructions("Il n'y a plus de cartes dans votre main.");
            }
        } else {
            Router.getInstance().instructions("Il n'y a pas de cartes dans votre main.");
        }
    }
}
