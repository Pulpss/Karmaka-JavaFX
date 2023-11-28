package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Main;
import karmaka.view.Router;

public class CoupDoeil extends Carte {
    public CoupDoeil() {
        super("CoupDoeil", Couleur.BLEU, "Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte.", 1);
    }

    public void pouvoir() throws IOException {
        // TODO: tester
    	Router.getInstance().instructions("La carte Coup d'oeil va être jouée !");
        Main mainAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getMain();
        Router.getInstance().afficher("Voici la main de votre adversaire.", mainAdv.getCartes());
        Partie.getInstance().setEtape(Partie.Etape.PROPOSER_CARTE_REJOUER);
        Partie.getInstance().tour();
    }
}
