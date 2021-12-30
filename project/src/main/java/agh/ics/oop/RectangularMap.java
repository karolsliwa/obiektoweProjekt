package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {
    final int height;
    final int width;
    public RectangularMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.upperRight = new Vector2d(this.width, this.height);
        this.lowerLeft = new Vector2d(0, 0);
    }
    MapVisualizer mapvis = new MapVisualizer(this);
}
