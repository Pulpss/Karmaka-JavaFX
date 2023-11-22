package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.view.Router;

import java.util.Random;

public class Bassesse extends Carte {
    public Bassesse() {
        super("Bassesse", Couleur.ROUGE, "Défaussez au hasard 2 cartes de la Main d’un rival.", 3);
    }

    public void pouvoir()  {
        // TODO : tester
        System.out.println("Bassesse");
        Random random = new Random();
        Main mainAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getMain();
        Fosse fosse = Partie.getInstance().getFosse();
        for (int i = 0; i < Math.min(2, mainAdv.size()); i++) {
            Carte cartePioche = mainAdv.getCartes().get(random.nextInt(mainAdv.size()));
            fosse.ajouter(mainAdv.piocher(cartePioche));
        }
        Router.getInstance().instructions("Si l'adversaire avait des cartes dans sa main elles ont été défaussées !");
    }
}
