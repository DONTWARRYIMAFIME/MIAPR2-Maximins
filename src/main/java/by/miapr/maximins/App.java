package by.miapr.maximins;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Maximins algorithm");
        Parent root = Loader.loadFXMLScene("scenes/main");
        stage.setScene(new Scene(root, 1230, 900));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}