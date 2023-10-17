package karmaka;

import javafx.application.Application;
import javafx.stage.Stage;
import karmaka.view.Router;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Router.init(primaryStage);
        Router.getInstance().setScene("menu");
    }

    public static void main(String[] args) {
        launch(args);
    }
}