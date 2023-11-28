package karmaka.classes.cartes;
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

    public void pouvoir()  {
        // TODO : tester
    	Router.getInstance().instructions("La carte Destinée va être jouée !");
        Source source = Partie.getInstance().getSource();
        VieFuture vieFuture = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getVieFuture();
        ArrayList<Carte> troisCartes = new ArrayList<Carte>(
                source.getCartes().subList(Math.max(0, source.size() - 3 - 1), source.size() - 1));
        if (source.size() > 0) {
            Router.getInstance().afficher("Voici les 3 premières cartes de la source.", troisCartes);
            int choix = Integer.parseInt(Router.getInstance().choix(
                    "Combien de cartes voulez vous ajouter à votre vie future ?", "0", "0", "1",
                    "2"));
            for (int i = 0; i < choix; i++) {
                Carte c = Router.getInstance().choix("Choisissez la carte à ajouter à votre vie future.",
                        troisCartes);
                vieFuture.ajouter(source.piocher(c));
                troisCartes.remove(c);
            }
            for (int i = 0; i < troisCartes.size(); i++) {
                Carte c = Router.getInstance().choix("Choisissez la carte à replacer dans la source en position " + (i+1),
                        troisCartes);
                source.ajouter(source.piocher(c));
                troisCartes.remove(c);
            }

        } else {
            Router.getInstance().instructions("La source est vide.");
        }

    }
}
