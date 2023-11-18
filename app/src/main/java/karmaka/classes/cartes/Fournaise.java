package karmaka.classes.cartes;

import java.io.IOException;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.VieFuture;
import karmaka.view.Router;

public class Fournaise extends Carte {
    public Fournaise() {
        super("Fournaise", Couleur.ROUGE, "Défaussez les 2 premières cartes de la Vie Future d'un rival.", 2);
    }
    
    public void pouvoir() throws IOException{
    	//TODO : TEST
        System.out.println("Fournaise");
        VieFuture viefutureadv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getVieFuture();
        Fosse fosse = Partie.getInstance().getFosse();
        if (viefutureadv.size() > 1) {
        Carte carte = viefutureadv.getCartes().get(0); //Prend la 1ere carte
        viefutureadv.piocher(carte);
        fosse.ajouter(carte);
        carte = viefutureadv.getCartes().get(0); //Prend la nouvelle 1ere carte
        viefutureadv.piocher(carte);
        fosse.ajouter(carte);
        Router.getInstance().instructions("Deux cartes ont été défaussées !");
        }
        else if (viefutureadv.size() == 1) {
            fosse.ajouter(viefutureadv.piocher());
        Router.getInstance().instructions("Une carte a été défaussée !");
        }
        else {
        	Router.getInstance().instructions("L'adversaire n'a pas de carte dans sa vie future !");
        }
        Partie.getInstance().tourSuivant();
    }
}
