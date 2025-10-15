package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneController {
    private static Stage stage;

    public static void setStage(Stage s) { stage = s; }

    public static void switchTo(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneController.class.getResource("/view/" + fxml));
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(SceneController.class.getResource("/view/style.css").toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
