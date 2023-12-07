package karmaka.view;



import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import karmaka.classes.Carte;

public class CarteView extends ImageView {
    public CarteView (Carte c)  {
        super();
        super.setFitHeight(160);
        super.setFitWidth(115);
        super.setImage(new Image("/images/cartes/" + c.getNom() + ".png"));
    }
}
