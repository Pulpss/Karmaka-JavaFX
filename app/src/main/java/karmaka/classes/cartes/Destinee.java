package karmaka.classes.cartes;

import java.io.IOException;
import java.util.ArrayList;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Source;
import karmaka.classes.piles.VieFuture;
import karmaka.view.Router;

public class Destinee extends Carte {
    public Destinee() {
        super("Destinee", Couleur.BLEU,
                "Regardez les 3 premières cartes de la Source; ajoutez-en jusqu’à 2 à votre Vie Future. Replacez le reste dans l'ordre souhaité.",
                2);
    }

    public void pouvoir() throws IOException {
    	//TODO : tester
        System.out.println("Destinee");
    	Source source = Partie.getInstance().getSource();
        VieFuture vieFuture = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getVieFuture();
        ArrayList<Carte> troisCartes = new ArrayList<Carte>(
        		source.getCartes().subList(Math.max(0, source.size() - 3), source.size() - 1));
        for (int i = 0; i<2;i++) {
        if (troisCartes.size() > 0) {
            Carte c = Router.getInstance().choix("Choisissez une carte à ajouter à votre vie future.", troisCartes);
            vieFuture.ajouter(source.piocher(c));
        } else {
            System.out.println("La fosse est vide.");
        }
        }
        Partie.getInstance().tourSuivant();
    
}
}
