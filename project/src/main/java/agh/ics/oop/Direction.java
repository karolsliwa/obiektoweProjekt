package agh.ics.oop;

//public class Direction { }
enum Direction {
    FORWARD,
    BACKWARD,
    RIGHT,
    LEFT,
    UNKNOWN
}
//enum MapDirection {
//    NORTH,
//    SOUTH,
//    WEST,
//    EAST;
//    public String toString() {
//        return switch (this) {
//            case EAST -> "Wschód";
//            case WEST -> "Zachód";
//            case NORTH -> "Północ";
//            case SOUTH -> "Południe";
//        };
//    }
//    public MapDirection next() {
//        return switch (this) {
//            case EAST -> SOUTH;
//            case WEST -> NORTH;
//            case NORTH -> EAST;
//            case SOUTH -> WEST;
//        };
//    }
//    public MapDirection previous() {
//        return switch (this) {
//            case EAST -> NORTH;
//            case WEST -> SOUTH;
//            case NORTH -> WEST;
//            case SOUTH -> EAST;
//        };
//    }
//    public World.Vector2d toUnitVector(){
//        return switch (this) {
//            case EAST -> new World.Vector2d(1, 0);
//            case WEST -> new World.Vector2d(-1, 0);
//            case NORTH -> new World.Vector2d(0, 1);
//            case SOUTH -> new World.Vector2d(0,-1);
//        };
//    }
//}