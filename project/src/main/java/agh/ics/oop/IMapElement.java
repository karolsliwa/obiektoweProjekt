package agh.ics.oop;

public interface IMapElement {
//    final GuiElementBox guiElementBox = null;
    /**
     *
     * @param pos
     *          The position to check
     * @return True if checked position is position of an element
     */
    boolean isAt(Vector2d pos);
    /**
     *
     * @return Position of a map element
     */
    Vector2d getPosition();
    /**
     * Add an observer to the observers list
     * @param observer
     *              The observer to add
     */
    void addObserver(IPositionChangeObserver observer);

    /**
     * Remove an observer from the observers list
     * @param observer
     *              The observer to remove
     */
    void removeObserver(IPositionChangeObserver observer);

    /**
     * Return path to the right image based on element's type and direction
     * @return path to the image
     */
    String imagePath();
}
