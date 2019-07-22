package de.dhbw.javafx_hello_world;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * Controller-Klasse für das UI aus der Datei "Scene.fxml". In der Datei befindet
 * sich eine Verknüpfung zu dieser Klasse im VBox-Element: <br><br>
 * 
 * <tt>
 *   <VBox id="AnchorPane" ... fx:controller="de.dhbw.javafx_hello_world.FXMLController">
 * </tt>
 */
public class FXMLController implements Initializable {
    
    /**
     * Das Label aus der FXML-Datei. Die Variable muss gleich der ID des
     * FXML-Elements heißen und mit @FXML ausgezeichnet werden.
     */
    @FXML
    private Label label;
    
    /**
     * Initialisierung des Konstrukturs. Aus dem ResourceBundle könnte man
     * übersetzte Texte laden, wenn die Anwendung für die Mehrsprachigkeit
     * ausgelegt ist.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    /**
     * Event Handler, der bei Klick auf den "Klick mich!"-Button aufgerufen
     * wird. Der Bezug zu dieser Methode ist im FXML wie folgt hergestellt:
     * 
     * <tt>
     *   <Button ... onAction="#onButtonClick"/>
     * </tt>
     * @param event 
     */
    @FXML
    private void onButtonClick(ActionEvent event) {
        System.out.println("Der Button wurde gedrückt!");
        label.setText("Hello World!");
    }  
}
