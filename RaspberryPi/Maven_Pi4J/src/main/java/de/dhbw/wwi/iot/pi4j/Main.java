/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package de.dhbw.wwi.iot.pi4j;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Dieses Programm zeigt ein einfaches Beispiel zur Nutzung von Pi4J. Damit es
 * funktioniert, muss in der Firmware wiringpi installiert sein. Entsprechend
 * der Vorlage aus dem Projekt Maven_RemoteDebugging lässt sich das Programm von
 * NetBeans direkt auf den Pi übertragen und dort starten.
 */
public class Main {

    private static boolean exit = false;
    private static double pushTime = 0;

    public static void main(String[] args) throws FileNotFoundException {
        // System.in/.out.err umleiten
        String ttyFile = System.getenv("REDIRECT_TTY");

        if (ttyFile != null && !ttyFile.strip().isEmpty()) {
            ttyFile = ttyFile.strip();

            System.setOut(new PrintStream(ttyFile));
            System.setErr(new PrintStream(ttyFile));
            System.setIn(new FileInputStream(ttyFile));
        }

        // GPIO-Test
        final GpioController gpio = GpioFactory.getInstance();

        GpioPinDigitalInput button = gpio.provisionDigitalInputPin(RaspiPin.GPIO_27, "Button");
        GpioPinDigitalOutput redLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_28, "Red LED");
        GpioPinDigitalOutput greenLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_29, "Green LED");

        button.setShutdownOptions(true);
        redLED.setShutdownOptions(true);
        greenLED.setShutdownOptions(true);

        greenLED.high();
        redLED.low();

        System.out.println("Alles klar!");

        button.addListener((GpioPinListenerDigital) (GpioPinDigitalStateChangeEvent event) -> {
            if (event.getState().isHigh()) {
                pushTime = System.currentTimeMillis();
                System.out.println("Button wurde gedrückt.");

                if (greenLED.getState().isHigh()) {
                    System.out.println("Grün -> Rot");

                    greenLED.low();
                    redLED.high();
                } else {
                    System.out.println("Rot -> Grün");

                    greenLED.high();
                    redLED.low();
                }
            } else {
                if (System.currentTimeMillis() - pushTime >= 1000) {
                    greenLED.blink(250);
                    redLED.blink(250);
                    
                    exit = true;
                }
            }
        });

        while (!exit) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                // Egal
            }
        }

        System.out.println("Bye!");
        gpio.shutdown();
    }
}
