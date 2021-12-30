package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

import static java.lang.System.out;

public class App extends Application implements IPositionChangeObserver {
    private GridPane[] grids = {new GridPane(), new GridPane()};
    private LineChart lineChart1;
    private ArrayList<LineChart> charts = new ArrayList<LineChart>();
    private ArrayList<AbstractWorldMap> maps = new ArrayList<AbstractWorldMap>();
    private SimulationEngine engine;
    private Thread engineThread;
    private Scene scene;
    private Stage primaryStage;
    private Button pause = new Button("Pause");
    private Button renew = new Button("Continue");
    private boolean simulationRunning = true;
    private LineChart lineChart;
    @Override
    public void start(Stage primaryStage) {
        GridPane g = new GridPane();
        GridPane grid = new GridPane();
        g.setPadding(new Insets(10, 10, 10, 10));
        g.setVgap(10);
        g.setHgap(10);
        TextField mapWidthInput = new TextField("10");
        GridPane.setConstraints(mapWidthInput, 1, 0);
        TextField mapHeightInput = new TextField("10");
        GridPane.setConstraints(mapHeightInput, 1, 1);
        TextField jungleRatioInput = new TextField("0.5");
        GridPane.setConstraints(jungleRatioInput, 1, 2);
        TextField startAnimalsInput = new TextField("12");
        GridPane.setConstraints(startAnimalsInput, 1, 3);
        TextField plantEnergyInput = new TextField("50");
        GridPane.setConstraints(plantEnergyInput, 1, 4);
        TextField moveEnergyInput = new TextField("1");
        GridPane.setConstraints(moveEnergyInput, 1, 5);
        TextField startGrassesInput = new TextField("5");
        GridPane.setConstraints(startGrassesInput, 1, 6);
        TextField startEnergyInput = new TextField("100");
        GridPane.setConstraints(startEnergyInput, 1, 7);
        TextField refreshTimeInput = new TextField("100");
        GridPane.setConstraints(refreshTimeInput, 1, 8);
        Label label1 = new Label("Map width: ");
        GridPane.setConstraints(label1, 0, 0);
        Label label2 = new Label("Map height: ");
        GridPane.setConstraints(label2, 0, 1);
        Label label3 = new Label("Jungle ratio: ");
        GridPane.setConstraints(label3, 0, 2);
        Label label5 = new Label("Start animals: ");
        GridPane.setConstraints(label5, 0, 3);
        Label label6 = new Label("Plant energy: ");
        GridPane.setConstraints(label6, 0, 4);
        Label label7 = new Label("Move energy: ");
        GridPane.setConstraints(label7, 0, 5);
        Label label8 = new Label("Start plants: ");
        GridPane.setConstraints(label8, 0, 6);
        Label label9 = new Label("Start energy: ");
        GridPane.setConstraints(label9, 0, 7);
        Button start = new Button("Start");
        GridPane.setConstraints(start, 0, 9);
        Label label10 = new Label("Refresh time: ");
        GridPane.setConstraints(label10, 0, 8);
        g.getChildren().addAll(mapWidthInput, mapHeightInput, jungleRatioInput, startAnimalsInput,
                plantEnergyInput, moveEnergyInput, startGrassesInput, startEnergyInput, refreshTimeInput,label1, label2,
                label3, label5, label6, label7, label8, label9, label10, start);
        start.setOnAction(e -> {
            int mapHeight = Integer.parseInt(mapHeightInput.getText());
            int mapWidth = Integer.parseInt(mapWidthInput.getText());
            float jungleRatio = Float.parseFloat(jungleRatioInput.getText());
            int jungleHeight = (int) (mapHeight * jungleRatio);
            int jungleWidth = (int) (mapWidth * jungleRatio);
            int startAnimals = Integer.parseInt(startAnimalsInput.getText());
            int plantEnergy = Integer.parseInt(plantEnergyInput.getText());
            int moveEnergy = Integer.parseInt(moveEnergyInput.getText());
            int startGrasses = Integer.parseInt(startGrassesInput.getText());
            int startEnergy = Integer.parseInt(startEnergyInput.getText());
            long refreshTime = Long.parseLong(refreshTimeInput.getText());
            AbstractWorldMap map1 = new JungleMap(mapWidth, mapHeight, jungleWidth, jungleHeight, startAnimals,
                    plantEnergy, moveEnergy, startGrasses, startEnergy);
            AbstractWorldMap map2 = new RoundJungleMap(mapWidth, mapHeight, jungleWidth, jungleHeight, startAnimals,
                    plantEnergy, moveEnergy, startGrasses, startEnergy);
            this.maps.add(map1);
            this.maps.add(map2);
            this.engine = new SimulationEngine(this.maps, this, refreshTime);
            this.engineThread = new Thread(engine);
            NumberAxis yAxis = new NumberAxis();
            NumberAxis xAxis = new NumberAxis();
            xAxis.setLabel("Day");
            this.charts.add(new LineChart(xAxis, yAxis));
            this.charts.get(0).setTitle("Flat Earth");
            NumberAxis y1Axis = new NumberAxis();
            NumberAxis x1Axis = new NumberAxis();
            x1Axis.setLabel("Day");
            this.charts.add( new LineChart(x1Axis, y1Axis));
            this.charts.get(1).setTitle("Round Earth");
            createChart(this.charts.get(0), this.maps.get(0));
            createChart(this.charts.get(1), this.maps.get(1));
            grid.setPadding(new Insets(0, 10, 0, 10));
            VBox v = new VBox();
            v.setPadding(new Insets(10, 10, 10, 10));
            v.getChildren().addAll(this.pause, this.renew);
            this.draw();
            grid.setVgap(10);
            grid.setHgap(40);
            grid.setPadding(new Insets(10, 20, 10, 20));
            GridPane.setConstraints(this.charts.get(0), 0, 0);
            GridPane.setConstraints(this.grids[0], 1, 0);
            GridPane.setConstraints(v, 2, 0);
            GridPane.setConstraints(this.grids[1], 1, 1);
            GridPane.setConstraints(this.charts.get(1), 0, 1);
            grid.getChildren().addAll(this.charts.get(0), this.grids[0], v, this.grids[1], this.charts.get(1));
            this.scene = new Scene(grid,1100, 900);
            this.primaryStage.setScene(scene);
            this.primaryStage.show();
            this.engineThread.start();
        });

        this.pause.setOnAction(r -> {
            this.simulationRunning = false;
        });
        this.renew.setOnAction(r -> {
            this.simulationRunning = true;
            this.engine.renewSimulation();
        });
        g.setAlignment(Pos.CENTER);
        this.primaryStage = primaryStage;
        this.scene = new Scene(g,300, 500);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
    @Override
    public void positionChanged(Vector2d oldPosition, Animal animal) {
        Platform.runLater(() -> {
            for (GridPane gridPane : this.grids) {
                gridPane.getChildren().clear();
                gridPane.getRowConstraints().clear();
                gridPane.getColumnConstraints().clear();
            }
            if (this.maps.get(0).getTime() % 5 == 0) {
                for (int i = 0; i < this.charts.size(); i++) {
                    this.updateChart(this.charts.get(i), this.maps.get(i));
                }
            }
            this.draw();
        });
    }

    public void draw() {
        for (int i = 0; i < this.maps.size(); i++) {
            AbstractWorldMap map = this.maps.get(i);
            GridPane g = this.grids[i];
            Vector2d jungleUpperRight = map.getJungleUpperRight();
            Vector2d jungleLowerLeft = map.getJungleLowerLeft();
            int w = map.getMapWidth() + 1;
            int h = map.getMapHeight() + 1;
            int sx = 0;
            int sy = 0;
            int ex = map.getMapWidth();
            int ey = map.getMapHeight();
            float columnWidth = 360/((float) (w + 1)) - 0.5f;
            float rowHeight = 360/((float) (w + 1)) - 0.5f;
            for (int x=0; x <= w; x++) {
                g.getColumnConstraints().add(new ColumnConstraints(columnWidth));
            }
            for (int y=0; y <= h; y++) {
                g.getRowConstraints().add(new RowConstraints(rowHeight));
            }
            Label label;
            label = new Label("x/y");
            label.setFont(new Font("Arial", rowHeight/3));
            g.add(label, 0, 0, 1, 1);
            GridPane.setHalignment(label, HPos.CENTER);
            for (int x=sx; x <= ex; x++) {
                label = new Label(String.valueOf(x));
                label.setFont(new Font("Arial", rowHeight/3));
                g.add(label, x - sx + 1, 0, 1, 1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
            for (int y=sy; y <= ey; y++) {
                label = new Label(String.valueOf(y));
                label.setFont(new Font("Arial", rowHeight/3));
                g.add(label, 0, ey - y + 1, 1, 1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
            AbstractWorldMapElement element;
            for (int x = sx; x <= ex; x++) {
                for (int y = sy; y <= ey; y++) {
                    Vector2d pos = new Vector2d(x, y);
                    Rectangle r = new Rectangle(columnWidth, rowHeight);
                    if (pos.precedes(jungleUpperRight) && pos.follows(jungleLowerLeft)) r.setFill(Color.DARKGREEN);
                    else r.setFill(Color.GREEN);
                    g.add(r,x - sx + 1, ey - y + 1, 1, 1);
                    element = map.objectAt(new Vector2d(x, y));
                    if (element != null) {
                        element.getGuiElementBox().setSize(columnWidth, rowHeight);
                        g.add(element.getGuiElementBox().getStackPane(), x - sx + 1, ey - y + 1, 1, 1);
                    }
                }
            }
            g.setGridLinesVisible(false);
            g.setGridLinesVisible(true);
        }
    }

    public boolean getSimulationRunning() {
        return this.simulationRunning;
    }
    public void createChart(LineChart lineChart, AbstractWorldMap map) {
        XYChart.Series animalNumber = new XYChart.Series(), grassNumber = new XYChart.Series(),
                averageHealth = new XYChart.Series(), averageLifeTime = new XYChart.Series() ,
                averageChildrenNumber = new XYChart.Series();
        animalNumber.setName("Living animals");
        grassNumber.setName("Plants");
        averageHealth.setName("Average health");
        averageLifeTime.setName("Average lifetime");
        averageChildrenNumber.setName("Average children amount");
        animalNumber.getData().add(new XYChart.Data<>(map.getTime(), map.animalsNumber()));
        grassNumber.getData().add(new XYChart.Data<>(map.getTime(), map.grassNumber()));
        averageHealth.getData().add(new XYChart.Data<>(map.getTime(), map.averageEnergy()));
        averageLifeTime.getData().add(new XYChart.Data<>(map.getTime(), map.averageLifeTime()));
        averageChildrenNumber.getData().add(new XYChart.Data<>(map.getTime(), map.averageChildrenAmount()));
        lineChart.createSymbolsProperty().set(false);
        lineChart.getData().addAll(animalNumber, grassNumber, averageHealth, averageLifeTime,
                averageChildrenNumber);
    }
    public void updateChart(LineChart lineChart, AbstractWorldMap map) {
        int[] averages = {map.animalsNumber(), map.grassNumber(), map.averageEnergy(), map.averageLifeTime(),
                map.averageChildrenAmount()};
        XYChart.Series s;
        for (int i = 0; i < lineChart.getData().size(); i++) {
            s = (XYChart.Series) lineChart.getData().get(i);
            s.getData().add(new XYChart.Data<>(map.getTime(), averages[i]));
        }
    }
}
