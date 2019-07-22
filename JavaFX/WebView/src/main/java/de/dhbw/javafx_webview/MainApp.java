package de.dhbw.javafx_webview;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hauptklasse dieser JavaFX-Anwendung. Hier wird das UI erzeugt und angezeigt.
 * Die Definition des UIs ist in "Other Sources" --> "src/main/resources" --> "fxml"
 * --> "Scene.fxml".
 */
public class MainApp extends Application {

    /**
     * Fallback für IDEs, die nicht mit JavaFX umgehen können. In JavaFX hat die
     * main()-Methode eigentlich keine Bedeutung und wird ignoriert!
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Die eigentliche Start-Methode einer JavaFX-Anwendung. Hier wird das UI
     * aus einer FXML-Datei geladen und anschließend angezeigt. Zusätzlich wird
     * hier ein Stylesheet eingebunden, über das verschiedene Layout-Eigenschaften
     * des UI definiert werden können (vergleichbar mit HTML-Stylesheets).
     * 
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/fxml/style.css");

        stage.setTitle("JavaFX und HTML");
        stage.setScene(scene);
        stage.show();
    }

}
