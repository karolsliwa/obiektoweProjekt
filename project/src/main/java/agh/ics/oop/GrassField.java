package agh.ics.oop;

import agh.ics.oop.gui.App;
import agh.ics.oop.gui.GuiElementBox;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.*;

import static java.lang.Math.sqrt;


public class GrassField extends AbstractWorldMap{
    final int n;
    final MapBoundary border = new MapBoundary(this);
    Random generator = new Random();
    public GrassField(int number) {
        this.n = number;
        for (int i = 0; i < number; i++) {
            int x = generator.nextInt((int)sqrt(this.n * 10) + 1);
            int y = generator.nextInt((int)sqrt(this.n * 10) + 1);
            Vector2d newGrassPos = new Vector2d(x, y);
            if (this.objectAt(newGrassPos) == null) {
                Grass newGrass =  new Grass(newGrassPos, this.border);
                this.grasses.put(newGrassPos, newGrass);
                this.border.append(newGrass);
            } else  i -= 1;
        }
        this.lowerLeft = this.border.leftCorner();
        this.upperRight = this.border.rightCorner();
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public AbstractWorldMapElement objectAt(Vector2d position) {
        if (this.animals.get(position) != null) {
            return this.animals.get(position);
        } else return this.grasses.get(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
        this.border.positionChanged(oldPosition, newPosition);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        if (this.canMoveTo(pos)) {
            this.animals.put(pos, animal);
            this.border.append(animal);
            return true;
        }
        throw new IllegalArgumentException("Position " + animal.getPosition().toString() + " is not available");
    }
    @Override
    public String toString() {
        this.lowerLeft = this.border.leftCorner();
        this.upperRight = this.border.rightCorner();
        return mapvis.draw(this.lowerLeft, this.upperRight);
    }
    public void draw(GridPane grid) {
        this.lowerLeft = this.border.leftCorner();
        this.upperRight = this.border.rightCorner();
        int w = this.upperRight.x - this.lowerLeft.x + 1;
        int h = this.upperRight.y - this.lowerLeft.y + 1;
        int sx = this.lowerLeft.x;
        int sy = this.lowerLeft.y;
        int ex = this.upperRight.x;
        int ey = this.upperRight.y;
        for (int x=0; x <= w; x++) {
            grid.getColumnConstraints().add(new ColumnConstraints(400/(float) (w + 1) - 0.5f));
        }
        for (int y=0; y <= h; y++) {
            grid.getRowConstraints().add(new RowConstraints(400/(float) (h + 1) - 0.5f));
        }
        Label label;
        for (int x=sx; x <= ex; x++) {
            label = new Label(String.valueOf(x));
            grid.add(label, x - sx + 1, 0, 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        for (int y=sy; y <= ey; y++) {
            label = new Label(String.valueOf(y));
            grid.add(label, 0, ey - y + 1, 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }
        AbstractWorldMapElement element;
        for (int x = sx; x <= ex; x++) {
            for (int y = sy; y <= ey; y++) {
                element = this.objectAt(new Vector2d(x, y));
                if (element != null) {
                    grid.add(element.guiElementBox.getVBox(), x - sx + 1, ey - y + 1, 1, 1);
                }
            }
        }
    }
}
