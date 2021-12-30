package agh.ics.oop;

import agh.ics.oop.gui.GuiElementBox;

public class Grass extends AbstractWorldMapElement{
    public Grass(Vector2d position) {
        this.position = position;
        this.guiElementBox = new GuiElementBox(this);
    }
    public String toString() {
        return "*";
    }
}
