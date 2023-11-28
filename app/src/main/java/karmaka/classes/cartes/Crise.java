package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Oeuvres;
import karmaka.view.Router;

public class Crise extends Carte {
    public Crise() {
        super("Crise", Couleur.ROUGE, "Le rival de votre choix défausse une de ses Oeuvres.", 2);
    }

    public void pouvoir() throws IOException {
    	Router.getInstance().instructions("La carte Crise va être jouée !");
        Oeuvres oeuvresAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getOeuvres();
        Fosse fosse = Partie.getInstance().getFosse();
        if (oeuvresAdv.size() > 0) {
            Router.getInstance().setScene("plateauPlaceholder");
            Router.getInstance().instructions("Laissez votre adversaire choisir une carte à défausser.");
            Carte carte = Router.getInstance().choix("Veuillez choisir une carte à défausser de vos oeuvres.", oeuvresAdv.getCartes());
            fosse.ajouter(oeuvresAdv.piocher(carte));
            Router.getInstance().instructions("Une carte a été défaussée ! Redonnez la main à votre adversaire.");
            Router.getInstance().setScene("plateau");
        } else {
            Router.getInstance().instructions("L'adversaire n'a pas d'oeuvres.");
        }
    }
}
