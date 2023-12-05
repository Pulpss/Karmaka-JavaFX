package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
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
        Joueur joueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
        Joueur joueurAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2);
    	joueur.afficher("La carte Dernier Souffle va être jouée !");
        String choixJoueur = joueur.choix("Choisissez le joueur qui va défausser une carte de sa main", "Adversaire", "Moi");
        if (choixJoueur == "Adversaire") {
            Main mainAdv = joueurAdv.getMain();
            Fosse fosse = Partie.getInstance().getFosse();
            if (mainAdv.size() > 0) {
                // Dans le cas ou l'adversaire doit défausser une carte, l'adversaire choisit quelle carte il doit défausser.
                Router.getInstance().setScene("plateauPlaceholder");
                joueur.afficher("Laissez votre adversaire choisir une carte à défausser.");
                Carte carte = joueurAdv.choix("Veuillez choisir une carte à défausser", mainAdv.getCartes());
                fosse.ajouter(mainAdv.piocher(carte));
                joueurAdv.afficher("Une carte a été défaussée ! Redonnez la main à votre adversaire.");
                Router.getInstance().setScene("plateau");
            } else {
                joueur.afficher("L'adversaire n'a pas de carte en main !");
            }
        }
        else {
            Main main = joueur.getMain();
            Fosse fosse = Partie.getInstance().getFosse();
            if (main.size() > 0) {
                Carte c = joueur.choix("Choisissez une carte à défausser.", main.getCartes());
                fosse.ajouter(main.piocher(c));
                joueur.afficher("Une carte a été défaussée !");
            } else {
                joueur.afficher("Vous n'avez pas de carte en main !");
            }
        }
    }

}
