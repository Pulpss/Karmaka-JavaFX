package karmaka.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javax.swing.text.html.Option;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import karmaka.classes.Carte;
import karmaka.classes.Partie;

public final class Router {
    private static String scene;
    private static Stage stage;
    private static Router instance = null;
    private static HashMap<String, String> scenes = new HashMap<String, String>();

    private Router(Stage initStage) {
        stage = initStage;
        addScenes();
    }

    private void addScenes() {
        scenes.put("menu", "fxml/Menu.fxml");
        scenes.put("createGame", "fxml/CreateGame.fxml");
        scenes.put("plateau", "fxml/Plateau.fxml");
        scenes.put("plateauPlaceholder", "fxml/PlateauPlaceholder.fxml");
        scenes.put("echelle", "fxml/Echelle.fxml");
    }

    public static void init(Stage stage) {
        if (instance == null) {
            instance = new Router(stage);
        }
    }

    public static Router getInstance() {
        return instance;
    }

    public void setScene(String sceneName) throws IOException {
        scene = sceneName;
        stage.setTitle(sceneName);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource(scenes.get(scene)))));
        stage.show();
        System.out.println("Scene changed to " + scene);
    }

    public void update() throws IOException {
        stage.setTitle(scene);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource(scenes.get(scene)))));
        stage.show();
        System.out.println("Scene updated to " + scene);
    }

    public void instructions(String inst) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Instructions");
        alert.setHeaderText(null);
        alert.setContentText(inst);

        alert.showAndWait();
    }

    public String choix(String message, String choixDefault, String... choix) {
        ChoiceDialog<String> dialog = new ChoiceDialog<String>(choixDefault, choix);
        dialog.setTitle("Veuillez faire un choix.");
        dialog.setHeaderText(null);
        dialog.setContentText(message);

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            return result.get();
        } else {
            return null;
        }
    }

    public Carte choix(String message, ArrayList<Carte> cartes) {
        ChoixCarte alert = new ChoixCarte(message, cartes);

        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            return alert.getCarteSel();
        } else {
            return null;
        }
    }

    public void afficher(String message, ArrayList<Carte> cartes) {
        AffichageCartes alert = new AffichageCartes(message, cartes);

        alert.showAndWait();
    }
}
