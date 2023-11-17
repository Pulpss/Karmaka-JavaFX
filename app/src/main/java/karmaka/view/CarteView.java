package karmaka.view;

import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import karmaka.classes.Carte;

public class CarteView extends ImageView {
    public CarteView (Carte c) throws IOException {
        super();
        super.setFitHeight(160);
        super.setFitWidth(115);
        super.setImage(new Image("/images/cartes/" + c.getNom() + ".png"));
    }
}
