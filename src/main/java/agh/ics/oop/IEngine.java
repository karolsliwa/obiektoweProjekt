package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

/**
 * The interface responsible for managing the moves of the animals.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IEngine {
    final List<Animal> animals = new ArrayList<Animal>();
//    final IWorldMap map = new RectangularMap(4, 4);
    /**
     * Move the animal on the map according to the provided move directions. Every
     * n-th direction should be sent to the n-th animal on the map.
     *
     */
    void run();
}