package agh.ics.oop;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class OptionsParserTest {
    @Test
    public void parseTest() {
        String[] st1 = {"f", "backward", "dgjl", "b", "r", "left", "vfs"};
        String[] st2 = {"f", "forward", "backward", "b", "right", "r", "left", "l"};
        String[] st3 = {"vfs", "c", "h", "dsa"};
        MoveDirection[] d1 = {MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        MoveDirection[] d2 = {MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.LEFT};
        MoveDirection[] d3 = {};
        assertArrayEquals(d1, new OptionsParser().parse(st1));
        assertArrayEquals(d2, new OptionsParser().parse(st2));
        assertArrayEquals(d3, new OptionsParser().parse(st3));


    }
}
