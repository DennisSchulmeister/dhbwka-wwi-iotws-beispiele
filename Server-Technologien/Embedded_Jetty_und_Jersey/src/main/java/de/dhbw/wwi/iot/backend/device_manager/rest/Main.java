/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package de.dhbw.wwi.iot.backend.device_manager.rest;

import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Hauptklasse zum Starten des eingebetteten Webservers für den REST-Webservice.
 * An dieser Stelle wird einfach ein Jetty-Webcontainer gestartet, so dass sich
 * der Webservice gemäß den 12-Faktor-Regeln als Stand-Alone-Programm starten
 * und verwalten lässt.
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
// TODO: Konfiguration aus Properties-File und/oder Env-Variablen lesen
// TODO: SSL ermöglichen, wenn eine Keystore-Datei vorhanden ist
        try {
            // Server soll auf Port 8080 lauschen und Servlets ausführen
            Server server = new Server(8080);

            // Servlet-Handling aktivieren. Alternativ bietet Jetty auch eine eigene
            // API, um HTTP-Handler zu schreiben. Servlets kennen wir aber schon. :-)
            // Vgl. http://stackoverflow.com/questions/20207477/serving-static-files-from-alternate-path-in-embedded-jetty
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
            context.setResourceBase(".");
            server.setHandler(context);

            // Default-Servlet zur Bereitstellung statischer Dateien
            // Siehe "Other Sources --> src/main/resources --> static" in Netbeans!
            ServletHolder staticFileServletHolder = new ServletHolder("default", DefaultServlet.class);

            String staticPath = Main.class.getResource("/static").getPath();
            staticPath = URLDecoder.decode(staticPath, "UTF-8");

            staticFileServletHolder.setInitParameter("resourceBase", staticPath);
            staticFileServletHolder.setInitParameter("dirAllowed", "true");
            context.addServlet(staticFileServletHolder, "/");

            // Jersey-Servlet für den REST-Webservice
            ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/api/*");

            jerseyServlet.setInitOrder(0);
            jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", HelloService.class.getCanonicalName());

            // HTTP-Server nun starten
            server.start();
            server.join();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception", ex);
        }
    }
}
