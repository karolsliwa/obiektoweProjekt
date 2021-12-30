package agh.ics.oop;

import com.sun.source.tree.NewArrayTree;

public enum MapDirection {
    NORTH,
    NORTH_WEST,
    NORTH_EAST,
    SOUTH,
    SOUTH_WEST,
    SOUTH_EAST,
    WEST,
    EAST;

    public String toString() {
        return switch (this) {
            case EAST -> "E";
            case WEST -> "W";
            case NORTH -> "N";
            case SOUTH -> "S";
            case SOUTH_EAST -> "SE";
            case SOUTH_WEST -> "SW";
            case NORTH_EAST -> "NE";
            case NORTH_WEST -> "NW";
        };
    }

    public MapDirection next() {
        return switch (this) {
            case EAST -> SOUTH_EAST;
            case WEST -> NORTH_WEST;
            case NORTH -> NORTH_EAST;
            case SOUTH -> SOUTH_WEST;
            case SOUTH_EAST -> SOUTH;
            case SOUTH_WEST -> WEST;
            case NORTH_EAST -> EAST;
            case NORTH_WEST -> NORTH;
        };
    }

    public MapDirection previous() {
        return switch (this) {
            case EAST -> NORTH_EAST;
            case WEST -> SOUTH_WEST;
            case NORTH -> NORTH_WEST;
            case SOUTH -> SOUTH_EAST;
            case SOUTH_EAST -> EAST;
            case SOUTH_WEST -> SOUTH;
            case NORTH_EAST -> NORTH;
            case NORTH_WEST -> WEST;
        };
    }
    public MapDirection turn(int d) {
        MapDirection dir = this;
        for (int i = 0; i < d; i++) {
            dir = dir.next();
        }
        return dir;
    }
    public Vector2d toUnitVector() {
        return switch (this) {
            case EAST -> new Vector2d(1, 0);
            case WEST -> new Vector2d(-1, 0);
            case NORTH -> new Vector2d(0, 1);
            case SOUTH -> new Vector2d(0, -1);
            case SOUTH_EAST -> new Vector2d(1, -1);
            case SOUTH_WEST -> new Vector2d(-1, -1);
            case NORTH_EAST -> new Vector2d(1, 1);
            case NORTH_WEST -> new Vector2d(-1, 1);
        };
    }
}
