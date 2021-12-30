package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.lang.System.out;

public class SimulationEngineTest {
    String[] args = {"f", "b", "r", "l"};
    MoveDirection[] directions = new OptionsParser().parse(args);
    AbstractWorldMap map = new RectangularMap(7, 7);
    Vector2d pos1 = new Vector2d(2,2);
    Vector2d pos2 = new Vector2d(3,4);
    Vector2d[] positions = { pos1, pos2 };
//    IEngine engine = new SimulationEngine(directions, map, positions);
//    @Test
//    public void runTest() {
//        engine.run();
//        assertEquals(engine.animals.get(0).getDirection().toString() + " " + engine.animals.get(0).getPosition().toString(),
//                "E (2, 3)");
//        assertEquals(engine.animals.get(1).getDirection().toString() + " " + engine.animals.get(1).getPosition().toString(),
//                "W (3, 3)");
//    }
}
