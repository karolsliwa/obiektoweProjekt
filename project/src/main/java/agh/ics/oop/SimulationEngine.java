package agh.ics.oop;
import agh.ics.oop.gui.App;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SimulationEngine implements Runnable, IEngine {
    final MoveDirection[] directions;
    final AbstractWorldMap map;
    final List<Animal> animals = new ArrayList<Animal>();
    final App app;
    public SimulationEngine (MoveDirection[] directions, AbstractWorldMap map, Vector2d[] positions, App app) {
        this.directions = directions;
        this.map = map;
        this.app = app;
        for (Vector2d pos: positions) {
            Animal creature = new Animal(this.map, pos);
            if(this.map.place(creature)) this.animals.add(creature);
        }
    }
    @Override
    public void run() {
        for (int i = 0; i < this.directions.length; i++) {
            Animal animal = this.animals.get(i % this.animals.size());
            Vector2d oldPosition = animal.getPosition();
            if (animal.move(this.directions[i])) {
                Platform.runLater(() -> {app.positionChanged(oldPosition, animal.getPosition());});
            }
        }
    }

}
