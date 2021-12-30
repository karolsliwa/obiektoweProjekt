package agh.ics.oop;

import agh.ics.oop.gui.GuiElementBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractWorldMapElement implements IMapElement{
    protected Vector2d position;
    protected List<IPositionChangeObserver> observers = new ArrayList<>();
    protected GuiElementBox guiElementBox;
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

    public GuiElementBox getGuiElementBox() {
        return this.guiElementBox;
    }
}
