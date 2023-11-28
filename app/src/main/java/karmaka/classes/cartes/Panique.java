package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.Pile;
import karmaka.classes.piles.Fosse;
import karmaka.view.Router;

public class Panique extends Carte {
    public Panique() {
        super("Panique", Couleur.ROUGE,
                "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte.", 1);
    }

    public void pouvoir() throws IOException {
        // TODO: tester
    	Router.getInstance().instructions("La carte Panique va être jouée !");
        Fosse fosse = Partie.getInstance().getFosse();
        Pile pileAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getDeck();
        if (pileAdv.size() > 0) {
            fosse.ajouter(pileAdv.piocher());
            Router.getInstance().instructions("La carte a été ajouté à la fosse. Veuillez rejouer.");
        } else {
            Router.getInstance()
                    .instructions("L'adversaire n'avait pas de cartes dans sa pile. Vous pouvez quand même rejouer.");
        }
        Partie.getInstance().setEtape(Partie.Etape.PROPOSER_CARTE_REJOUER);
        Partie.getInstance().tour();
    }
}
