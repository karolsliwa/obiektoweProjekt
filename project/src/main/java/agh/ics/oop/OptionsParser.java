package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {

    public MoveDirection[] parse(String[] args) {
        List<MoveDirection> l = new ArrayList<>();
        for (String d: args) {
            switch (d) {
                case "f", "forward" ->  l.add(MoveDirection.FORWARD);
                case "b", "backward" -> l.add(MoveDirection.BACKWARD);
                case "r", "right" -> l.add(MoveDirection.RIGHT);
                case "l", "left" -> l.add(MoveDirection.LEFT);
                default -> throw new IllegalArgumentException(d + " is not legal move specification");
            }
        }
        MoveDirection[] r = new MoveDirection[l.size()];
        Integer i = 0;
        for (MoveDirection dir: l) {
            r[i] = dir;
            i += 1;
        }
        return r;
    }
}
