package agh.ics.oop;

import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    Vector2d[] t = {new Vector2d(1, 2), new Vector2d(3, 4),
            new Vector2d(5, 8), new Vector2d(-15, 8), new Vector2d(-23, -31),
            new Vector2d(34, 65), new Vector2d(12, -7), new Vector2d(65, 34),
            new Vector2d(-5,-8)};
    @Test
    public void upperRightTest() {
        for (int i = 0; i < t.length - 1; i++) {
            assertEquals(t[i].upperRight(t[i + 1]), new Vector2d(Math.max(t[i].x, t[i + 1].x), Math.max(t[i].y, t[i + 1].y)));
        }
    }
    @Test
    public void lowerLeftTest() {
        for (int i = 0; i < t.length - 1; i++) {
            assertEquals(t[i].lowerLeft(t[i + 1]), new Vector2d(Math.min(t[i].x, t[i + 1].x), Math.min(t[i].y, t[i + 1].y)));
        }
    }
    @Test
    public void oppositeTest() {
        for (Vector2d v : t) {
            Vector2d op = v.opposite();
            assertEquals(op, new Vector2d(-v.x, -v.y));
        }
    }
    @Test
    public void substractTest() {
        for (int i = 0; i < t.length - 1; i++) {
            assertEquals(new Vector2d(t[i].x - t[i+1].x, t[i].y - t[i+1].y), t[i].substract(t[i + 1]));
        }
    }
    @Test
    public void addTest() {
        for (int i = 0; i < t.length - 1; i++) {
            assertEquals(new Vector2d(t[i].x + t[i+1].x, t[i].y + t[i+1].y), t[i].add(t[i + 1]));
        }
    }
    @Test
    public void precedesTest() {
        for (int i = 0; i < t.length - 1; i++) {
//            assertEquals(t[i].x <= t[i+1].x && t[i].y <= t[i+1].y, t[i].precedes(t[i+1]));
            if(t[i].x <= t[i+1].x && t[i].y <= t[i+1].y) assertTrue(t[i].precedes(t[i + 1]));
            else assertFalse(t[i].precedes(t[i + 1]));
        }
    }
    @Test
    public void followsTest() {
        for (int i = 0; i < t.length - 1; i++) {
            if (t[i].x >= t[i + 1].x && t[i].y >= t[i + 1].y) assertTrue(t[i].follows(t[i + 1]));
            else assertFalse(t[i].follows(t[i + 1]));
        }
    }
    @Test
    public void EqualsTest() {
        for (Vector2d v : t) {
            assertTrue(v.equals(v));
            assertTrue(v.equals(new Vector2d(v.x, v.y)));
            assertFalse(v.equals(new Vector2d(v.x + 1, v.y + 1)));
        }
    }
    @Test
    public void toStringTest() {
        int[] tab = {1, 2, 4, 5, 2, 7, 89, 65, 23, 12, 0, -34, 12, 98, -76, -43, -23, -56, -24, 56, 43, 129, 657, 32};
        for (int i = 0; i < tab.length - 1; i++) {
            assertEquals(new Vector2d(tab[i], tab[i + 1]).toString(), "(" + tab[i] + ", " + tab[i + 1] + ")");
        }
    }
}
