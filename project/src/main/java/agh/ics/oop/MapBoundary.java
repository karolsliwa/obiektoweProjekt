package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    final GrassField map;
    Comparator<AbstractWorldMapElement> xComparator = new Comparator<AbstractWorldMapElement>() {
        @Override
        public int compare(AbstractWorldMapElement e1, AbstractWorldMapElement e2) {
            if (e1.getPosition().x == e2.getPosition().x) return e1.getPosition().y - e2.getPosition().y;
            return e1.getPosition().x - e2.getPosition().x;
        }
    };
    Comparator<AbstractWorldMapElement> yComparator = new Comparator<AbstractWorldMapElement>() {
        public int compare(AbstractWorldMapElement e1, AbstractWorldMapElement e2) {
            if (e1.getPosition().y == e2.getPosition().y) return e1.getPosition().x - e2.getPosition().x;
            return e1.getPosition().y - e2.getPosition().y;
        }
    };
    SortedSet<AbstractWorldMapElement> xAxis = new TreeSet<AbstractWorldMapElement>(xComparator);
    SortedSet<AbstractWorldMapElement> yAxis = new TreeSet<AbstractWorldMapElement>(yComparator);
    public MapBoundary(GrassField map) {
        this.map = map;
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.map.animals.get(newPosition);
        this.xAxis.removeIf(el -> !el.toString().equals("*") && el.getPosition().equals(newPosition));
        this.yAxis.removeIf(el -> !el.toString().equals("*") && el.getPosition().equals(newPosition));
        this.xAxis.add(animal);
        this.yAxis.add(animal);
    }
    public Vector2d leftCorner() {
        return this.xAxis.first().getPosition().lowerLeft(this.yAxis.first().getPosition());
    }
    public Vector2d rightCorner() {
        return this.xAxis.last().getPosition().upperRight(this.yAxis.last().getPosition());
    }
    public void append(AbstractWorldMapElement el) {
        this.xAxis.add(el);
        this.yAxis.add(el);
    }
}
