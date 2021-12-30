package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
//    IWorldMap map = new RectangularMap(4, 4);
    AbstractWorldMap map = new RectangularMap(4, 4);
    Animal creature = new Animal(map, new Vector2d(2, 2));
//    @Test
//    public void toStringTest() {
//        Animal creature1 = new Animal(map, new Vector2d(2, 2));
//        assertEquals(creature1.toString(), creature1.getDirection().toString() + " " + creature1.getPosition().toString());
//        creature1.move(MoveDirection.FORWARD);
//        assertEquals(creature1.toString(), creature1.getDirection().toString() + " " + creature1.getPosition().toString());
//    }
    @Test
    public void isAtTest() {
        assertTrue(creature.isAt(new Vector2d(2, 2)));
        assertFalse(creature.isAt(new Vector2d(2, 3)));
        creature.move(MoveDirection.FORWARD);
        assertTrue(creature.isAt(new Vector2d(2, 3)));
        assertFalse(creature.isAt(new Vector2d(2, 2)));
        creature.move(MoveDirection.BACKWARD);
    }
    @Test
    public void moveBorderTest() {
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.LEFT, MoveDirection.RIGHT};
        Vector2d pos = creature.getPosition();
        MapDirection dir = creature.getDirection();
        for (int i = 0;i<6;i++) {
            creature.move(MoveDirection.FORWARD);
        }
        assertTrue(creature.getPosition().precedes(new Vector2d(4, 4)));
        for (int i = 0;i<12;i++) {
            creature.move(MoveDirection.BACKWARD);
        }
        assertTrue(creature.getPosition().follows(new Vector2d(0, 0)));
    }
    @Test
    public void moveForwardTest() {
        Vector2d pos = creature.getPosition();
        MapDirection dir = creature.getDirection();
        creature.move(MoveDirection.FORWARD);
        assertEquals(dir, creature.getDirection());
        assertEquals(pos.add(dir.toUnitVector()), creature.getPosition());
    }
    @Test
    public void moveBackwardTest() {
        Vector2d pos = creature.getPosition();
        MapDirection dir = creature.getDirection();
        creature.move(MoveDirection.BACKWARD);
        assertEquals(dir, creature.getDirection());
        assertEquals(pos.substract(dir.toUnitVector()), creature.getPosition());
    }
    @Test
    public void moveLeftTest() {
        Vector2d pos = creature.getPosition();
        MapDirection dir = creature.getDirection();
        creature.move(MoveDirection.LEFT);
        assertEquals(dir.previous(), creature.getDirection());
        assertEquals(pos, creature.getPosition());
    }
    @Test
    public void moveRightTest() {
        Vector2d pos = creature.getPosition();
        MapDirection dir = creature.getDirection();
        creature.move(MoveDirection.RIGHT);
        assertEquals(dir.next(), creature.getDirection());
        assertEquals(pos, creature.getPosition());
    }
}
