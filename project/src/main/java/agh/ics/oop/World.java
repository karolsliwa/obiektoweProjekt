package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Application;

import java.util.Scanner;

import static java.lang.System.out;

public class World {
    public static void main(String[] arg) {
        String [] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
//        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
//        System.out.println("System wystartował");
//        try {
//            MoveDirection[] directions = new OptionsParser().parse(args);
////            AbstractWorldMap map = new RectangularMap(10,5);
//            AbstractWorldMap map = new GrassField(10);
//            IEngine engine = new SimulationEngine(directions, map, positions);
//            out.println(map.toString());
//            engine.run();
//            out.println(map.toString());
//        } catch (IllegalArgumentException ex) {
//            out.println(ex.toString());
//        }
        Application.launch(App.class, args);
//        System.out.println("System zakończył działanie");
    }

}

