package agh.ics.oop;

import agh.ics.oop.gui.GuiElementBox;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractWorldMapElement implements IMapElement{
    protected Vector2d position;
    protected List<IPositionChangeObserver> observers = new ArrayList<>();
    protected GuiElementBox guiElementBox = null;
    @Override
    public boolean isAt(Vector2d pos) {return this.position.equals(pos);}
    @Override
    public Vector2d getPosition() {
        return this.position;
    }
    @Override
    public void addObserver(IPositionChangeObserver observer) {
        this.observers.add(observer);
    }
    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        this.observers.remove(observer);
    }
    @Override
    public String imagePath() {
        String path;
        switch (this.toString()) {
            case "*" -> path = "src/main/resources/grass.png";
            case "N" -> path = "src/main/resources/up.png";
            case "S" -> path = "src/main/resources/down.png";
            case "E" -> path = "src/main/resources/right.png";
            case "W" -> path = "src/main/resources/left.png";
            default -> path = "";
        }
        return path;
    }

}
