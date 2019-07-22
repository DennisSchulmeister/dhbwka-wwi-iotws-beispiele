/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package de.dhbw.wwi.iot.remotedebug;

/**
 * <pre>
 * Diese Minianwendung zeigt, wie NetBeans dazu genutzt werden kann, den Quellcode
 * der Anwendung zu Testzwecken automatisch auf den Pi zu übertragen und remote zu
 * debuggen.
 *
 *   1. Wählen Sie hierfür in der Toolbar die Konfiguration "debug-on-pi" aus.
 *
 *   2. Führen Sie dann ein "Clean and Build" aus, um die Anwendung zu starten.
 *
 *   3. Sobald die Meldung "Warte auf Debugger" erscheint, wählen Sie den
 *      Menüeintrag "Debug -> Attach debugger" aus, um sich mit dem Pi zu
 *      verbinden.
 *
 *   4. Geben Sie in dem Popup folgende Werte ein:
 *
 *        * Connector: SocketAttach
 *        * Transport: dt_socker
 *        * Host: IP-Adresse des Pi
 *        * Port: 8000
 *
 * Ggf. müssen Sie, damit alles klappt, in der pom.xml die IP-Adresse des Pi
 * anpassen.
 * </pre>
 */
public class Main {

    public static void main(String[] args) {
        // Test für Remote Debugging
        String name = "Welt";
        String text = "Hallo " + name + "!";
        System.out.println(text);
    }
}
