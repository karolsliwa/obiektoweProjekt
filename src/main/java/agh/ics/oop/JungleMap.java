package agh.ics.oop;

import javafx.scene.layout.VBox;

import java.util.*;

import static java.lang.Math.*;

public class JungleMap extends AbstractWorldMap {
    Random generator = new Random();
    public JungleMap(int mapWidth, int mapHeight, int jungleWidth, int jungleHeight, int startAnimals, int plantEnergy,
                     int moveEnergy, int startGrasses, int startEnergy) {
        this.mapWidth = mapWidth;
        this.startEnergy = startEnergy;
        this.moveEnergy = moveEnergy;
        this.plantEnergy = plantEnergy;
        this.mapHeight = mapHeight;
        this.jungleWidth = jungleWidth;
        this.jungleHeight = jungleHeight;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(mapWidth, mapHeight);
        int jw = (int) Math.round((mapWidth + 1 - jungleWidth) / 2.0);
        int jh = (int) Math.round((mapHeight + 1 - jungleHeight) / 2.0);
        this.jungleLowerLeft = new Vector2d(jw, jh);
        this.jungleUpperRight = new Vector2d(jw + jungleWidth - 1, jh + jungleHeight - 1);
        int i = 0;
        while (i < startAnimals) {
            int x = generator.nextInt(mapWidth);
            int y = generator.nextInt(mapHeight);
            if (this.place(new Animal(this, new Vector2d(x, y), startEnergy))) i += 1;
        }
        i = 0;
        while (i < startGrasses) {
            int x = generator.nextInt(mapWidth);
            int y = generator.nextInt(mapHeight);
            if (this.place(new Grass(new Vector2d(x, y)))) i += 1;
        }
    }
}

