package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Sphere;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiElementBox implements IPositionChangeObserver {
//    private IMapElement mapElement;
    private Label label;
    private Shape shape;
    private StackPane stackPane;
    private AbstractWorldMapElement mapElement;
    private FileInputStream fileInputStream;
    public GuiElementBox(AbstractWorldMapElement mapElement) {
        this.mapElement = mapElement;
        this.stackPane = new StackPane();
        if (mapElement instanceof Animal) {
            this.label = new Label(mapElement.toString());
            Circle c = new Circle();
            stackPane.getChildren().addAll(c, label);
            this.shape = c;
        }
        else if (mapElement instanceof Grass){
            Rectangle r = new Rectangle();
            r.setFill(Color.GREENYELLOW);
            this.shape = r;
            this.stackPane.getChildren().add(this.shape);
        }
        stackPane.setAlignment(Pos.CENTER);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Animal animal) {
        this.stackPane.getChildren().clear();
//        this.vBox.getChildren().clear();
        this.label = new Label(this.mapElement.toString());
        this.label.setFont(new Font("Arial", this.shape.getLayoutX()/2));
        this.shape.setFill(((Animal) mapElement).getColor());
        this.stackPane.getChildren().addAll(this.shape, this.label);
//        this.vBox.getChildren().addAll(this.stackPane);

    }
    public void setSize(float width, float height) {
        if (this.shape instanceof Rectangle) {
            ((Rectangle) this.shape).setHeight(height);
            ((Rectangle) this.shape).setWidth(width);
        }
        else if (this.shape instanceof Circle) {
            this.stackPane.getChildren().clear();
            ((Circle) this.shape).setRadius(0.5 * width - 0.5f);
            this.label.setFont(new Font("Arial", this.shape.getLayoutX()/2));
            this.stackPane.getChildren().addAll(this.shape, this.label);
//            this.label.textProperty().setValue(this.mapElement.toString());
        };
    }
    public StackPane getStackPane() {
        return this.stackPane;
    }

}
