package agh.ics.oop;
import agh.ics.oop.gui.GuiElementBox;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.Random;

public class Animal extends AbstractWorldMapElement{
    private MapDirection direction;
    final AbstractWorldMap map;
    private int[] genotype;
    private  int age = 0, children = 0, birthDate;
    private int health;
    Random generator = new Random();
    public Animal(AbstractWorldMap map, Vector2d initialPosition, int health) {
        this.map = map;
        this.birthDate = this.map.getTime();
        this.direction = MapDirection.NORTH.turn(generator.nextInt(8));
        this.health = health;
        this.position = initialPosition;
        this.guiElementBox = new GuiElementBox(this);
        this.addObserver(this.guiElementBox);
        this.addObserver(map);
        this.genotype = new int[32];
        for (int i = 0; i < 32; i++) this.genotype[i] = generator.nextInt(8);
        Arrays.sort(this.genotype);
    }
    public Animal(AbstractWorldMap map, Vector2d initialPosition, int health, int[] genotype) {
        this.map = map;
        this.birthDate = this.map.getTime();
        this.health = health;
        this.direction = MapDirection.NORTH.turn(generator.nextInt(8));
        this.position = initialPosition;
        this.guiElementBox = new GuiElementBox(this);
        this.addObserver(this.guiElementBox);
        this.addObserver(map);
        this.genotype = genotype;
    }
    public String toString() {
        return this.direction.toString();
    }

    public void move() {
        Vector2d oldPosition = this.position;
        Vector2d newPositionf = this.position.add(this.direction.toUnitVector());
        Vector2d newPositionb = this.position.substract(this.direction.toUnitVector());
        if (this.map instanceof RoundJungleMap) {
            if (newPositionf.follows(this.map.getUpperRight()) || newPositionf.precedes(this.map.getLowerLeft()))
                ((RoundJungleMap) this.map).outOfTheWorld(newPositionf);
            if (newPositionb.follows(this.map.getUpperRight()) || newPositionb.precedes(this.map.getLowerLeft()))
                ((RoundJungleMap) this.map).outOfTheWorld(newPositionb);
        }
        int d = this.genotype[generator.nextInt(32)];
        if (d == 0 && this.map.canMoveTo(newPositionf)) this.position = newPositionf;
        else if (d == 4 && this.map.canMoveTo(newPositionb)) this.position = newPositionb;
        else this.direction = this.direction.turn(d);
        this.positionChanged(oldPosition);
    }
    public void positionChanged(Vector2d oldPosition) {
        for (IPositionChangeObserver observer: this.observers) {
            observer.positionChanged(oldPosition, this);
        }
    }

    public MapDirection getDirection() {
        return this.direction;
    }
    public Animal reproduce(Animal animal) {
        this.children += 1;
        animal.children += 1;
        int sum = this.health + animal.health;
        int g = Math.round(((float)this.health)/sum * 32);
        int[] childGenotype = new int[32];
        if (generator.nextInt(2) == 1) {
            if (g >= 0) System.arraycopy(this.genotype, 0, childGenotype, 0, g);
            if (32 - g >= 0) System.arraycopy(animal.genotype, g, childGenotype, g, 32 - g);
        }
        else {
            if (32 - g >= 0) System.arraycopy(animal.genotype, 0, childGenotype, 0, 32 - g);
            if (32 - (32 - g) >= 0) System.arraycopy(this.genotype, 32 - g, childGenotype, 32 - g, 32 - (32 - g));
        }
        Arrays.sort(childGenotype);
        this.health = (int) Math.round(0.75 * this.health);
        animal.health = (int) Math.round(0.75 * animal.health);
        return new Animal(this.map, this.position, Math.round(sum/4), childGenotype);
    }
    public boolean isDead() {
        if (this.health <= 0) this.health = 0;
        return (this.health == 0);
    }
    public int getHealth() {
        return this.health;
    }
    public void eat(int energy) {
        this.health += energy;
    }
    public void useEnergy(int energy) {
        this.health -= energy;
    }
    public int getAge() {
        return this.age - this.birthDate;
    }
    public int getChildrenCounter() {
        return this.children;
    }
    public void age() {
        this.age = map.getTime() - this.birthDate;
    }
    public int[] getGenotype() {return this.genotype;}
    public Color getColor() {
        if (this.health < 0.25 * this.map.startEnergy) return Color.rgb(242, 174, 111);
        if (this.health < 0.5 * this.map.startEnergy) return Color.rgb(176, 104, 39);
        if (this.health < 0.75 * this.map.startEnergy) return Color.rgb(133, 80, 32);
        if (this.health < this.map.startEnergy) return Color.rgb(107, 60, 18);
        if (this.health < 1.5 * this.map.startEnergy) return Color.rgb(92, 48, 9);
        if (this.health < 2 * this.map.startEnergy) return Color.rgb(71, 38, 8);
        if (this.health < 3 * this.map.startEnergy) return Color.rgb(64, 34, 6);
        else return Color.rgb(43, 22, 3);
    }
}
