package agh.ics.oop;

import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    protected Map<Vector2d, Grass> grasses = new HashMap<>();
    protected Vector2d upperRight;
    protected Vector2d lowerLeft;
//    final MapBoundary border = new MapBoundary(this);
    MapVisualizer mapvis = new MapVisualizer(this);
    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.precedes(this.upperRight) && position.follows(this.lowerLeft)
                && !isOccupied(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        Object o = objectAt(position);
        if (o != null) return !o.toString().equals("*");
        else return false;
    }
    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        return  this.animals.get(position);
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            this.animals.put(animal.getPosition(), animal);
            return true;
        }
        throw new IllegalArgumentException("Position " + animal.getPosition().toString() + " is not available");
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
    }

    public String toString() {
        return mapvis.draw(this.lowerLeft, this.upperRight);
    }


}
