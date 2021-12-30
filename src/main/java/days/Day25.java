package days;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day25 extends Day {
    enum Orientation {
        EAST,
        SOUTH
    }

    public Day25(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        HashMap<Point, Orientation> map = new HashMap<>();

        for (int r = 0; r < input.length; r++) {
            String line = input[r];
            for (int c = 0; c < line.length(); c++) {
                switch (line.charAt(c)) {
                    case '>':
                        map.put(new Point(c, r), Orientation.EAST);
                        break;
                    case 'v':
                        map.put(new Point(c, r), Orientation.SOUTH);
                        break;
                    default:
                }
            }
        }

        int rows = input.length, cols = input[0].length();
        printMap(map, rows, cols);


        boolean hasMoved;
        int moves=0;
        do {
            hasMoved = false;
            moves++;
            List<Map.Entry<Point, Orientation>> eastOnes = map.entrySet().stream().filter(e -> e.getValue().equals(Orientation.EAST)).collect(Collectors.toList());
            List<Map.Entry<Point, Orientation>> southOnes = map.entrySet().stream().filter(e -> e.getValue().equals(Orientation.SOUTH)).collect(Collectors.toList());
            HashMap<Point, Orientation> newMap = new HashMap<>();
            for (Map.Entry<Point, Orientation> eastOne : eastOnes) {
                Point curr = eastOne.getKey();
                Point next = new Point((curr.x + 1) % cols, curr.y);
                if (map.containsKey(next)) {
                    newMap.put(curr, eastOne.getValue());
                } else {
                    newMap.put(next, eastOne.getValue());
                    hasMoved=true;
                }
            }

            for (Map.Entry<Point, Orientation> southOne : southOnes) {
                Point curr = southOne.getKey();
                Point next = new Point(curr.x, (curr.y + 1) % rows);
                if (!newMap.containsKey(next) && !(map.containsKey(next) && map.get(next).equals(Orientation.SOUTH))) {
                    newMap.put(next, southOne.getValue());
                    hasMoved = true;
                } else {
                    newMap.put(curr, southOne.getValue());
                }
            }
            map =newMap;
        } while (hasMoved);

        System.out.printf("First step on which no sea cucumbers move: %d%n", moves);
        return String.valueOf(moves);
    }

    private void printMap(HashMap<Point, Orientation> map, int rows, int cols) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Point p = new Point(c, r);
                if (map.containsKey(p))
                    System.out.printf("%s", map.get(p).equals(Orientation.EAST) ? ">" : "v");
                else
                    System.out.print(".");
            }
            System.out.println();
        }
        System.out.println("----------------------------");
    }


    public String part2() {
        String[] input = this.getInput().split("\r\n");

        int result = input.length;
        System.out.printf("Smallest model number: %d%n", result);
        return String.valueOf(result);
    }
}