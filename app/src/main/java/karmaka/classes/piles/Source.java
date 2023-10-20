package karmaka.classes.piles;

import java.util.ArrayList;
import java.util.Arrays;

import karmaka.classes.Carte;
import karmaka.classes.Pile;
import karmaka.classes.cartes.*;

public class Source extends Pile {
    static ArrayList<Carte> cartesInit = new ArrayList<Carte>(Arrays.asList(
        new Transmigration(),
        new Transmigration(),
        new Transmigration(),
        new CoupDoeil(),
        new CoupDoeil(),
        new CoupDoeil(),
        new RevesBrises(),
        new RevesBrises(),
        new RevesBrises(),
        new Deni(),
        new Deni(),
        new Deni(),
        new Vol(),
        new Vol(),
        new Lendemain(),
        new Lendemain(),
        new Lendemain(),
        new Sauvetage(),
        new Sauvetage(),
        new Sauvetage(),
        new Longevite(),
        new Longevite(),
        new Longevite(),
        new Voyage(),
        new Voyage(),
        new Jubile(),
        new Jubile(),
        new DernierSouffle(),
        new DernierSouffle(),
        new DernierSouffle(),
        new Crise(),
        new Crise(),
        new Crise(),
        new Fournaise(),
        new Fournaise(),
        new Fournaise(),
        new Vengeance(),
        new Vengeance(),
        new Incarnation(),
        new Incarnation(),
        new Incarnation(),
        new Incarnation(),
        new Incarnation(),
        new Mimetisme(),
        new Mimetisme(),
        new Destinee(),
        new Destinee(),
        new Destinee(),
        new Duperie(),
        new Duperie(),
        new Recyclage(),
        new Recyclage(),
        new Recyclage(),
        new Semis(),
        new Semis(),
        new Semis(),
        new Panique(),
        new Panique(),
        new Panique(),
        new Roulette(),
        new Roulette(),
        new Roulette(),
        new Bassesse(),
        new Bassesse(),
        new Bassesse()
    ));
    public Source() {
        super(cartesInit);
        melanger();
    }
}
