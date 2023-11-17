package karmaka.classes.cartes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Main;
import karmaka.view.Router;

public class Duperie extends Carte {
    public Duperie() {
        super("Duperie", Couleur.BLEU, "Regardez 3 cartes de la Main d’un rival; ajoutez-en une à votre Main.", 3);
    }

    public void pouvoir() throws IOException {
        // TODO: tester
        System.out.println("Duperie");
        Random random = new Random();
        Main mainAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getMain();
        Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        ArrayList<Integer> randomPrec = new ArrayList<Integer>();
        ArrayList<Carte> carteTemp = new ArrayList<Carte>();
        for (int i = 0; i < Math.min(3, mainAdv.size()); i++) {
            int newRandom = random.nextInt(mainAdv.size());
            while (randomPrec.contains(newRandom)) {
                newRandom = random.nextInt(mainAdv.size());
            }
            randomPrec.add(newRandom);
            carteTemp.add(mainAdv.getCartes().get(newRandom));
        }
        Carte c = Router.getInstance().choix("Voici 3 cartes aléatoires de la main de votre adversaire, choisissez-en une.", carteTemp);
        main.ajouter(mainAdv.piocher(c));
        Router.getInstance().instructions("La carte a été ajoutée à votre main.");
        Partie.getInstance().tourSuivant();
    }
}
