package karmaka.view;

import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import karmaka.classes.Carte;

public class CarteView extends ImageView {
    public CarteView (Carte c) throws IOException {
        ImageView carte = new ImageView();
        carte.setFitHeight(160);
        carte.setFitWidth(115);
        carte.setImage(new Image("/images/cartes/" + c.getNom() + ".png"));
    }
}
