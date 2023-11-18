package karmaka.classes.cartes;

import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Oeuvres;
import karmaka.view.Router;

import java.io.IOException;
import java.util.Scanner;
public class Jubile extends Carte {
    public Jubile() {
        super("Jubile", Couleur.VERT, "Placez jusqu’à 2 cartes de votre Main sur vos Oeuvres", 3);
    }

    public void pouvoir() throws IOException{
    	//TODO : Completer
        System.out.println("Jubile");
        Scanner scanner = new Scanner(System.in); //Je ne suis pas sur que cela fonctionne dans une interface graphique
        Router.getInstance().instructions("Indiquez si vous souhaitez placer 0, 1 ou 2 cartes dans vos Oeuvres");
        int valeurChoisie = scanner.nextInt();
        while (valeurChoisie > 2 || valeurChoisie < 0) {
        	Router.getInstance().instructions("Valeur incohérente, réessayez.");
        	valeurChoisie = scanner.nextInt();
        }
        scanner.close(); //Normalement on sait à partir de cette ligne le nombre de cartes à retirer de la main.
        for (int i = 0; i < valeurChoisie; i++) {
        	Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        	Oeuvres oeuvre = Partie.getInstance().getPile(); //Pas encore définis ?
        	if (main.size() > 0) {
        		Carte c = Router.getInstance().choix("Choisissez une carte à défausser.", main.getCartes());
                oeuvre.ajouter(main.piocher(c));
                Router.getInstance().instructions("Une carte a été défaussée !");
                }
                else {
                	Router.getInstance().instructions("Vous n'avez pas de carte en main !");
                }
        }
        Partie.getInstance().tourSuivant();
    }
}
