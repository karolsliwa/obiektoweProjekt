package agh.ics.oop.gui;

import agh.ics.oop.AbstractWorldMapElement;
import agh.ics.oop.IPositionChangeObserver;
import agh.ics.oop.Vector2d;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.function.UnaryOperator;

public class GuiElementBox implements IPositionChangeObserver {
//    public IMapElement mapElement;
    final VBox vBox;
    private ImageView imageView;
    private Label label;
    private Image image;
    private AbstractWorldMapElement mapElement;
    private FileInputStream fileInputStream;
    public GuiElementBox(AbstractWorldMapElement mapElement) {
        this.mapElement = mapElement;
        this.fileInputStream = null;
        try {
            this.fileInputStream = new FileInputStream(mapElement.imagePath());
        } catch (FileNotFoundException e) {
                e.printStackTrace();
        }
        this.vBox = new VBox();
        this.image = new Image(this.fileInputStream);
        this.imageView = new ImageView(image);
        this.imageView.setFitWidth(20);
        this.imageView.setFitHeight(20);
        if (mapElement.toString().equals("*")) this.label = new Label("Trawa");
        else this.label = new Label(mapElement.getPosition().toString());
        this.vBox.getChildren().addAll(this.imageView, this.label);
        this.vBox.setAlignment(Pos.CENTER);
//        this.vBox.getChildren().remove(this.label);
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.vBox.getChildren().clear();
        this.fileInputStream = null;
        try {
            this.fileInputStream = new FileInputStream(this.mapElement.imagePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.label = new Label(newPosition.toString());
        this.image = new Image(this.fileInputStream);
        this.imageView.setImage(this.image);
        this.vBox.getChildren().addAll(this.imageView, this.label);

    }
    public VBox getVBox() {
        return this.vBox;
    }
}
