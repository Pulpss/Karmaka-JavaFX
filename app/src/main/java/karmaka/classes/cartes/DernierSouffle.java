package karmaka.classes.cartes;

import java.io.IOException;
import java.util.Random;

import karmaka.classes.Action;
import karmaka.classes.Carte;
import karmaka.classes.Couleur;
import karmaka.classes.Partie;
import karmaka.classes.piles.Fosse;
import karmaka.classes.piles.Main;
import karmaka.classes.piles.Oeuvres;
import karmaka.view.Router;

public class DernierSouffle extends Carte {
    public DernierSouffle() {
        super("DernierSouffle", Couleur.ROUGE, "Le joueur de votre choix défausse une carte de sa Main.", 1);
    }

    public void pouvoir() throws IOException{
        System.out.println("DernierSouffle");
        
        // TODO : Ajouter l'option qui permet de se désigner soi-même ou l'adversaire
        // Je pense qu'il faut créer une nouvelle méthode .choix qui va permettre à l'utilisateur de choisir entre les 2 joueurs celui qu'il désigne.
        Router.getInstance().instructions("Choisissez le joueur qui va défausser une carte de sa main");
        int joueur = 0; //Ligne à enlever une fois solution trouvée
        if (joueur == 0) { //Condition à modifier une fois solution trouvée
        //Cas défausse adversaire
        Random random = new Random();
        Main mainAdv = Partie.getInstance().getJoueur((Partie.getInstance().getTour() + 1) % 2).getMain();
        Fosse fosse = Partie.getInstance().getFosse();
        if (mainAdv.size() > 0) {
        Carte carte = mainAdv.getCartes().get(random.nextInt(mainAdv.size()));
        mainAdv.piocher(carte);
        fosse.ajouter(carte);
        Router.getInstance().instructions("Une carte a été défaussée !");
        }
        else {
        	Router.getInstance().instructions("L'adversaire n'a pas de carte en main !");
        }
        }
      //Cas défausse soi-même
        else {
        	Main main = Partie.getInstance().getJoueur(Partie.getInstance().getTour()).getMain();
        	Fosse fosse = Partie.getInstance().getFosse();
        	if (main.size() > 0) {
        		Carte c = Router.getInstance().choix("Choisissez une carte à défausser.", main.getCartes());
                fosse.ajouter(main.piocher(c));
                Router.getInstance().instructions("Une carte a été défaussée !");
                }
                else {
                	Router.getInstance().instructions("Vous n'avez pas de carte en main !");
                }
        }
        Partie.getInstance().tourSuivant();
    }
    
}
