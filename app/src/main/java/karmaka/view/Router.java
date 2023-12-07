package karmaka.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import karmaka.classes.Carte;
import karmaka.classes.GameData;

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

    public void setScene(String sceneName)  {
        scene = sceneName;
        stage.setTitle(sceneName);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource(scenes.get(sceneName)))));
        } catch (Exception e) {
            System.out.println("Scene not found: " + sceneName);
            e.printStackTrace();
        }
        stage.show();
        System.out.println("Scene changed to " + scene);
    }

    public void update() {
        stage.setTitle(scene);
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getClassLoader().getResource(scenes.get(scene)))));
        } catch (Exception e) {
            System.out.println("Scene not found: " + scene);
            e.printStackTrace();
        }
        stage.show();
        System.out.println("Scene updated to " + scene);
    }

    public void afficher(String inst) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Instructions");
        alert.setHeaderText(null);
        alert.setContentText(inst);

        alert.showAndWait();
    }

    public String choix(String message, String... choix) {
        ChoiceDialog<String> dialog = new ChoiceDialog<String>(choix[0], choix);
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

    public void afficherCartes(String message, ArrayList<Carte> cartes) {
        AffichageCartes alert = new AffichageCartes(message, cartes);

        alert.showAndWait();
    }

    private void savePartieToFile(GameData gameData, File file) {
        try {
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameData);
            out.close();
            fileOut.close();
        } catch (IOException ex) {
            System.out.println("probleme");
        }
    }

    public void sauvegarder(GameData gameData) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sauvegarde karmaka (.krmk)", "*.krmk"));
        fileChooser.setInitialFileName("partie.krmk");
        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            savePartieToFile(gameData, file);
        }
    }

    private GameData loadPartieFromFile(File file) {
        GameData gameData = null;
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            System.out.println(in);
            gameData = (GameData) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException ex) {
            System.out.println("probleme");
        } catch (ClassNotFoundException ex) {
            System.out.println("probleme");
        }
        return gameData;
    }

    public GameData charger() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Sauvegarde karmaka (.krmk)", "*.krmk"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            return loadPartieFromFile(file);
        }
        return null;
    }
}
