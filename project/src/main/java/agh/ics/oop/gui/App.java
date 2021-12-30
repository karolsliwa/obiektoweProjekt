package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.util.Arrays;

import static java.lang.System.arraycopy;
import static java.lang.System.out;

public class App extends Application implements IPositionChangeObserver {
    private GridPane grid= new GridPane();
    private GrassField map;
    private Thread engineThread;
    private Scene scene;
    private Stage primaryStage;
    @Override
    public void start(Stage primaryStage) {
        String [] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
//        String[] args = {"f", "b", "f", "b"};
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        this.primaryStage = primaryStage;
        this.grid.setGridLinesVisible(true);
        MoveDirection[] directions = new OptionsParser().parse(args);
        this.map = new GrassField(10);
        SimulationEngine engine = new SimulationEngine(directions, this.map, positions, this);
        this.engineThread = new Thread(engine);
        this.map.draw(grid);
        this.scene = new Scene(this.grid,400, 400);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
//        this.engineThread.start();
//        this.map.draw(grid);
//        Button button = new Button("Start");
//        TextField argsInput = new TextField("Args");
//        button.setOnAction( e -> out.println(argsInput.getText()));
//        HBox input = new HBox();
//        input.setPadding(new Insets(20, 20, 20, 20));
//        input.getChildren().addAll(button, argsInput);
//        VBox body = new VBox();
//        body.getChildren().addAll(input, this.grid);
//        this.scene = new Scene(this.grid,400, 400);
//        this.primaryStage.setScene(scene);
//        this.primaryStage.show();
//        this.engineThread.stop();

    }
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
//        long moveDelay = 300;
////        out.println("czekam iii");
////        Platform.runLater(() -> {
//////            long moveDelay = 300;
//        try {
//            Thread.sleep(moveDelay);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        out.println("jeb");
//////            this.primaryStage.hide();
//
//        this.grid.getChildren().clear();
////            this.grid = new GridPane();
//        this.grid.setGridLinesVisible(false);
//        this.grid.setGridLinesVisible(true);
////
//////            this.grid.setGridLinesVisible(true);
//        this.map.draw(this.grid);
////            this.scene = new Scene(this.grid,400, 400);
////            this.primaryStage.setScene(this.scene);
////            this.primaryStage.show();
////            this.primaryStage.setScene(this.scene);
//        });
    }
}
