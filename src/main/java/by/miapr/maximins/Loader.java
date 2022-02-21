package by.miapr.maximins;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public abstract class Loader {

    public static FXMLLoader loadFXML(final String fxml) {
        return new FXMLLoader(Loader.class.getResource(fxml + ".fxml"));
    }

    public static Parent loadFXMLScene(final String fxml) throws IOException {
        return loadFXML(fxml).load();
    }
}
