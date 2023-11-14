package karmaka.view;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import karmaka.classes.Carte;

public class ChoixCarte extends Alert {
    private Carte carteSel;

    public ChoixCarte(String message, ArrayList<Carte> cartes) {
        super(AlertType.NONE);
        ArrayList<ImageView> cartesView = new ArrayList<ImageView>();
        // weird trick to make it possible to change the selected card in the
        // onMouseClicked handler
        Label carteSelLabel = new Label("La carte sélectionnée actuellement est: Aucune");
        cartes.iterator()
                .forEachRemaining(c -> {
                    try {
                        ImageView tempView = new CarteView(c);
                        tempView.setOnMouseClicked(e -> {
                            carteSel = c;
                            carteSelLabel.setText("La carte sélectionnée actuellement est: " + c.getNom());
                        });
                        cartesView.add(tempView);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        // HBox containing all the cards
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        hbox.setPrefWidth(00);
        hbox.getChildren().addAll(cartesView);
        // ScrollPane containing the HBox
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(hbox);
        Label label = new Label(message);
        // VBox containing the label and the ScrollPane
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.getChildren().addAll(label, scrollPane, carteSelLabel);
        // DialogPane containing the VBox (necessary for alert)
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContent(vbox);
        // Alert containing the DialogPane
        super.setTitle("Veuillez faire un choix.");
        super.setDialogPane(dialogPane);
        super.getButtonTypes().add(ButtonType.OK);
    }

    public Carte getCarteSel() {
        return carteSel;
    }
}