package de.dhbw.javafx_hello_world;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hauptklasse dieser JavaFX-Anwendung. Hier wird das UI erzeugt und angezeigt.
 * Die Definition des UIs ist in "Other Sources" --> "src/main/resources" --> "fxml"
 * --> "Scene.fxml".<br><br>
 * 
 * Im Gegensatz zum JavaFX_Hello_World-Projekt besteht die JavaFX-Szene aus einer
 * einzigen WebView, in der eine HTML-Seite angezeigt wird. Dies ermöglicht es
 * HTML/CSS/JavaScript zum Entwickeln der Benutzeroberfläche zu verwenden.
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
     * aus einer FXML-Datei geladen und anschließend angezeigt.
     * 
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/scene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/fxml/style.css");

        stage.setTitle("Hallo JavaFX");
        stage.setScene(scene);
        stage.show();
    }

}
