package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.view.Router;

import java.io.IOException;
import java.util.Random;
public class Bassesse extends Carte {
    public Bassesse() {
        super("Bassesse", Couleur.ROUGE, "Défaussez au hasard 2 cartes de la Main d’un rival.", 3);
    }

    public void pouvoir() throws IOException{
    	//TODO : tester
        System.out.println("Bassesse");
        Random random = new Random();
        Main mainAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getMain();
        Fosse fosse = Partie.getInstance().getFosse();
        if (mainAdv.size() > 1) {
        Carte carte = mainAdv.getCartes().get(random.nextInt(mainAdv.size()));
        mainAdv.piocher(carte);
        fosse.ajouter(carte);
        carte = mainAdv.getCartes().get(random.nextInt(mainAdv.size()));
        mainAdv.piocher(carte);
        fosse.ajouter(carte);
        Router.getInstance().instructions("Deux cartes ont été défaussées !");
        }
        else if (mainAdv.size() == 1) {
            fosse.ajouter(mainAdv.piocher());
        Router.getInstance().instructions("Une carte a été défaussée !");
        }
        else {
        	Router.getInstance().instructions("L'adversaire n'a pas de carte en main !");
        }
        Partie.getInstance().tourSuivant();
    }
}
