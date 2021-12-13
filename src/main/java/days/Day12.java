package days;

import java.util.*;

public class Day12 extends Day {
    public Day12(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        int pathCount = 0;
        Cave cave = new Cave();

        for (String line : input
        ) {
            String[] nodes = line.split("-");
            for (String n : nodes
            ) {
                cave.addNode(n);
            }
            cave.addEdge(nodes[0], nodes[1]);
        }
        //List<List<String>> paths = cave.allPaths();
        List<List<String>> paths = cave.paths("start","end",false);

        System.out.printf("Total paths: %d%n", paths.size());
        return String.valueOf(paths.size());
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        int pathCount = 0;
        Cave cave = new Cave();

        for (String line : input
        ) {
            String[] nodes = line.split("-");
            for (String n : nodes
            ) {
                cave.addNode(n);
            }
            cave.addEdge(nodes[0], nodes[1]);
        }

        List<List<String>> paths = cave.paths("start","end",true);
        //System.out.println(paths);


        System.out.printf("Total paths: %d%n", paths.size());
        return String.valueOf(paths.size());
    }

}
