package de.dhbw.javafx_webview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

/**
 * Controller-Klasse für das UI aus der Daten Scene.fxml. In der Datei befindet
 * sich eine Verknüpfung zu dieser Klasse im AnchorPane-Element: <br><br>
 *
 * <tt>
 * <VBox id="AnchorPane" ... fx:controller="de.dhbw.javafx_hello_world.FXMLController">
 * </tt>
 */
public class WebViewController implements Initializable {

    /**
     * Das WebView-Element aus der FXML-Datei. Die Variable muss gleich der ID
     * des FXML-Elements heißen und mit @FXML ausgezeichnet werden.
     */
    @FXML
    private WebView webView;
    private WebEngine webEngine;

    /**
     * Initialisierung des Objekts. Hier wird die anzuzeigende Webseite gesetzt.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.webEngine = webView.getEngine();

        // Übergabe eines Callback-Objekts, mit dem aus JavaScript heraus der
        // Java-Teil der Anwendung aufgerufen werden kann. Hierfür wird ein
        // ChangeListener verwendet, der immer aufgerufen wird, wenn eine neue
        // Seite geladen wurde.
        this.webEngine.getLoadWorker().stateProperty().addListener(
                (ObservableValue<? extends State> ov, State oldState, State newState) -> {
                    if (newState == State.SUCCEEDED) {
                        // Hier wird das Callback-Objekt übergeben. Es kann im JavaScript
                        // über die Variable "java" angesprochen werden.   
                        JSObject jsWindow = (JSObject) this.webEngine.executeScript("window");
                        jsWindow.setMember("java", this);
                    }
                }
        );
        
        this.webEngine.setJavaScriptEnabled(true);

        // HTML-Seite laden und anzeigen
        String startPage = getClass().getResource("/html/index.html").toExternalForm();
        this.webEngine.load(startPage);
    }
    
    /**
     * Callback-Methode, aus JavaScript heraus aufgerufen.
     */
    public void onButtonClicked() {
        System.out.println("Button clicked!");
        this.webEngine.executeScript("showText('JavaFX sagt Hallo!')");
    }
}

