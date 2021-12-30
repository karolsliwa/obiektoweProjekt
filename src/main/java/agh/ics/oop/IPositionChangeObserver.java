package agh.ics.oop;

public interface IPositionChangeObserver {

    void positionChanged(Vector2d oldPosition, Animal animal);
        /**
         * Place an animal with changed position on the map.
         *
         * @param oldPosition
         * Position of an animal before it moved.
         *
         * @param newPosition
         * The new position of an animal to place on the map.
         *
         */
}
