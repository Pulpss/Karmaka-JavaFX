package karmaka.classes.cartes;



import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Main;

public class CoupDoeil extends Carte {
    public CoupDoeil() {
        super("CoupDoeil", Couleur.BLEU, "Regardez la Main d’un rival. Vous pouvez ensuite jouer une autre carte.", 1);
    }

    public void pouvoir()  {
        // TODO: tester
        Joueur joueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
    	joueur.afficher("La carte Coup d'oeil va être jouée !");
        Main mainAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getMain();
        joueur.afficherCartes("Voici la main de votre adversaire.", mainAdv.getCartes());
        Partie.getInstance().setEtape(Partie.Etape.PROPOSER_CARTE_REJOUER);
        Partie.getInstance().tour();
    }
}
