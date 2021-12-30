package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    AbstractWorldMap map = new RectangularMap(7, 7);
    Vector2d pos1 = new Vector2d(2,2);
    Vector2d pos2 = new Vector2d(3,4);

    @Test
    public void objectAtTest() {
        map.place(new Animal(map, pos1));
        map.place(new Animal(map, pos2));
        assertEquals(map.animals.get(pos1), map.objectAt(pos1));
        assertNull(map.objectAt(new Vector2d(5, 5)));
    }
    @Test
    public void canMoveToTest() {
        map.place(new Animal(map, pos1));
        map.place(new Animal(map, pos2));
        assertFalse(map.canMoveTo(new Vector2d(11, 0)));
        assertFalse(map.canMoveTo(new Vector2d(-1, -1)));
        assertTrue(map.canMoveTo(new Vector2d(1, 1)));
    }
    @Test
    public void isOccupiedTest() {
        map.place(new Animal(map, pos1));
        map.place(new Animal(map, pos2));
        assertFalse(map.isOccupied(new Vector2d(1, 1)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }
    @Test
    public void placeTest() {
        map.place(new Animal(map, pos1));
        map.place(new Animal(map, pos2));
        assertFalse(map.place(new Animal(map, new Vector2d(2, 2))));
        assertTrue(map.place(new Animal(map, new Vector2d(4, 4))));
    }
}
