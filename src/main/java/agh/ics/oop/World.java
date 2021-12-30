package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

import static java.lang.System.out;

public class World {

    private static int[] genotype;

    public static void main(String[] arg) {
        String [] args = {};
        out.println("System wystartował");
        try {
            Application.launch(App.class, args);
        } catch (IllegalArgumentException ex) {
            out.println(ex.toString());
        }
        out.println("System zakończył działanie");
    }

}

