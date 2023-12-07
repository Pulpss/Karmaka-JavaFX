package karmaka.classes.cartes;



import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Joueur;
import karmaka.classes.Partie;
import karmaka.classes.Pile;
import karmaka.classes.piles.Fosse;

public class Panique extends Carte {
    public Panique() {
        super("Panique", Couleur.ROUGE,
                "Défaussez la première carte de la Pile d'un joueur. Vous pouvez ensuite jouer une autre carte.", 1);
    }

    public void pouvoir()  {
        // TODO: tester
        Joueur joueur = Partie.getInstance().getJoueur(Partie.getInstance().getTour());
    	joueur.afficher("La carte Panique va être jouée !");
        Fosse fosse = Partie.getInstance().getFosse();
        Pile pileAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getDeck();
        if (pileAdv.size() > 0) {
            fosse.ajouter(pileAdv.piocher());
            joueur.afficher("La carte a été ajouté à la fosse. Veuillez rejouer.");
        } else {
            joueur.afficher("L'adversaire n'avait pas de cartes dans sa pile. Vous pouvez quand même rejouer.");
        }
        Partie.getInstance().setEtape(Partie.Etape.PROPOSER_CARTE_REJOUER);
        Partie.getInstance().tour();
    }
}
