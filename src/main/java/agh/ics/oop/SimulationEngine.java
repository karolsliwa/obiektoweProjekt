package agh.ics.oop;
//import agh.ics.oop.gui.App;
import agh.ics.oop.gui.App;

import java.util.ArrayList;

public class SimulationEngine implements Runnable, IEngine {
    final ArrayList<AbstractWorldMap> maps;
    private  long refreshTime;
    final App app;
    public SimulationEngine (ArrayList<AbstractWorldMap> maps, App app, long refreshTime) {
        this.maps = maps;
        this.app = app;
        this.refreshTime = refreshTime;
    }
    @Override
    public void run() {
        Animal animal = new Animal(this.maps.get(0), new Vector2d(2 ,2) ,2);
        Vector2d vector = new Vector2d(2, 2);
        while (true) {
            try {
                this.pauseCondition();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (AbstractWorldMap map : this.maps) {
                map.anotherDay();
            }
            try {
                Thread.sleep(this.refreshTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.app.positionChanged(vector, animal);
        }
    }
    public synchronized void pauseCondition() throws  InterruptedException {
        while (!this.app.getSimulationRunning()) {
            wait();
        }
    }
    public synchronized void renewSimulation() {
        notify();
    }
}
