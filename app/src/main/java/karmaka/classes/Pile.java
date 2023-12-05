package karmaka.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Pile implements Serializable {
    private ArrayList<Carte> cartes;

    public Pile(ArrayList<Carte> cartesInit) {
        cartes = cartesInit;
    }

    public void melanger() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = cartes.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
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
        int iMax = qte <= cartes.size() ? qte : cartes.size() - 1;
        for (int i = 0; i < iMax; i++) {
            cartesPiochees.add(cartes.remove(cartes.size() - 1));
        }
        return cartesPiochees;
    }

    public Carte piocher() {
        return cartes.size() != 0 ? cartes.remove(cartes.size() - 1) : null;
    }

    public Carte piocher(Carte c) {
        boolean sup = cartes.remove(c);
        return sup ? c : null;
    }

    public void ajouter(ArrayList<Carte> cartesAjoutees) {
        cartes.addAll(cartesAjoutees);
    }

    public void ajouter(Carte c) {
        cartes.add(c);
    }

    public int size() {
        return cartes.size();
    }

    public String[] getNoms() {
        String[] noms = new String[cartes.size()];
        for (int i = 0; i < cartes.size(); i++) {
            noms[i] = cartes.get(i).getNom();
        }
        return noms;
    }

}
