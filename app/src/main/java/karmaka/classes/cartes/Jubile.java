package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Oeuvres;
import karmaka.view.Router;

public class Jubile extends Carte {
    public Jubile() {
        super("Jubile", Couleur.VERT, "Placez jusqu’à 2 cartes de votre Main sur vos Oeuvres", 3);
    }

    public void pouvoir() {
        // TODO : tester
        System.out.println("Jubile");
        String choix = Router.getInstance().choix("Combien de carte de votre main voulez vous placez votre", "0", "0",
                "1", "2");
        int valeurChoisie = Integer.parseInt(choix);
        Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        Oeuvres oeuvres = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getOeuvres();
        for (int i = 0; i < Math.min(valeurChoisie, main.size()); i++) {
            Carte c = Router.getInstance().choix("Choisissez une carte à placer sur vos oeuvres.", main.getCartes());
            oeuvres.ajouter(main.piocher(c));
        }
    }
}
