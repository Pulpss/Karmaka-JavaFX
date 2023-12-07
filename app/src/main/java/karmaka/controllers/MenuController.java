package karmaka.controllers;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import karmaka.classes.Partie;
import karmaka.view.Router;

public class MenuController implements Initializable {
    @FXML
    private void handlePlay()  {
        Router.getInstance().setScene("createGame");
    }

    @FXML
    private void handleLoad()  {
        Partie.init();
        Partie.getInstance().charger();
    }

    @FXML
    public void initialize(URL arg0, ResourceBundle arg1) {
    }
}
