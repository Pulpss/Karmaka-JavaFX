package karmaka.classes;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Pile {
    private ArrayList<Carte> cartes;
    public Pile(ArrayList<Carte> cartesInit) {
        cartes = cartesInit;
    }

    public void melanger() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = cartes.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i+1);
            Carte a = cartes.get(index);
            cartes.set(index, cartes.get(i));
            cartes.set(i, a);
        }
    }

    public ArrayList<Carte> getCartes() {
        return cartes;
    }

    public ArrayList<Carte> piocher(int qte) {
        ArrayList<Carte> cartesPiochees = new ArrayList<Carte>();
        for (int i = 0; i < (qte < cartes.size() ? qte : cartes.size() - 1); i++) {
            cartesPiochees.add(cartes.remove(0));
        }
        return cartesPiochees;
    }

    public void ajouter(ArrayList<Carte> cartesAjoutees) {
        cartes.addAll(cartesAjoutees);
    }

    public int size() {
        return cartes.size();
    }


}
