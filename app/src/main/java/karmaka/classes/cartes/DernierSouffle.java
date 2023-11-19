package karmaka.classes.cartes;

import java.io.IOException;
import java.util.Random;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.view.Router;

public class DernierSouffle extends Carte {
    public DernierSouffle() {
        super("DernierSouffle", Couleur.ROUGE, "Le joueur de votre choix défausse une carte de sa Main.", 1);
    }

    public void pouvoir() throws IOException {
        // TODO : tester
        System.out.println("DernierSouffle");
        String choixJoueur = Router.getInstance().choix("Choisissez le joueur qui va défausser une carte de sa main", "Adversaire", "Adversaire", "Moi");
        if (choixJoueur == "Adversaire") {
            Main mainAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getMain();
            Fosse fosse = Partie.getInstance().getFosse();
            if (mainAdv.size() > 0) {
                // Dans le cas ou l'adversaire doit défausser une carte, l'adversaire choisit quelle carte il doit défausser.
                Router.getInstance().setScene("plateauPlaceholder");
                Router.getInstance().instructions("Laissez votre adversaire choisir une carte à défausser.");
                Carte carte = Router.getInstance().choix("Veuillez choisir une carte à défausser", mainAdv.getCartes());
                fosse.ajouter(mainAdv.piocher(carte));
                Router.getInstance().instructions("Une carte a été défaussée ! Redonnez la main à votre adversaire.");
                Router.getInstance().setScene("plateau");
            } else {
                Router.getInstance().instructions("L'adversaire n'a pas de carte en main !");
            }
        }
        else {
            Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
            Fosse fosse = Partie.getInstance().getFosse();
            if (main.size() > 0) {
                Carte c = Router.getInstance().choix("Choisissez une carte à défausser.", main.getCartes());
                fosse.ajouter(main.piocher(c));
                Router.getInstance().instructions("Une carte a été défaussée !");
            } else {
                Router.getInstance().instructions("Vous n'avez pas de carte en main !");
            }
        }
        Partie.getInstance().tourSuivant();
    }

}
