package agh.ics.oop;
import agh.ics.oop.gui.GuiElementBox;

class Animal extends AbstractWorldMapElement{
    private MapDirection direction = MapDirection.NORTH;
//    private Vector2d position = new Vector2d(2, 2);
    final AbstractWorldMap map;
//    public Animal(IWorldMap map) {this.map = map;}
    public Animal(AbstractWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.guiElementBox = new GuiElementBox(this);
        this.addObserver(map);
        this.guiElementBox = new GuiElementBox(this);
        this.addObserver(this.guiElementBox);

    }
    public String toString() {
        return this.direction.toString();
    }
    public boolean move(MoveDirection dir) {
        Vector2d oldPosition = this.position;
        Vector2d newPositionf = this.position.add(this.direction.toUnitVector());
        Vector2d newPositionb = this.position.substract(this.direction.toUnitVector());
        switch (dir) {
            case FORWARD:
                if (this.map.canMoveTo(newPositionf)) {
                    this.position = newPositionf;
                }
                break;
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case BACKWARD:
                if (this.map.canMoveTo(newPositionb)) {
                    this.position = newPositionb;
                }
        }
        if (!this.isAt(oldPosition)) {
            this.positionChanged(oldPosition);
            return true;
        }
        return false;
    }
    public void positionChanged(Vector2d oldPosition) {
        for (IPositionChangeObserver observer: this.observers) {
            observer.positionChanged(oldPosition, this.position);
        }
    }

    public MapDirection getDirection() {
        return this.direction;
    }

}
