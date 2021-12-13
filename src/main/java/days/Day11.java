package days;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day11 extends Day {
    public Day11(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");

        long flashes=0;
        int r = input.length;
        int c = input[0].length();
        Octopus[][] grid = new Octopus[r][c];
        for (int y = 0; y < input.length; y++) {
            String line = input[y];
            for (int x= 0; x < line.length(); x++) {
                grid[y][x]=new Octopus(Integer.parseInt(String.valueOf(line.charAt(x))));
            }
        }

        int steps = 100;
        while (--steps>=0){
            flashes += nextStep(grid,r,c);
        }

        System.out.printf("Total flashes after 100 steps: %d%n", flashes);
        return String.valueOf(flashes);
    }


    public String part2() {
        String[] input = this.getInput().split("\r\n");
        long flashes;
        int r = input.length;
        int c = input[0].length();
        Octopus[][] grid = new Octopus[r][c];
        int octopusCount =0;
        for (int y = 0; y < input.length; y++) {
            String line = input[y];
            for (int x = 0; x < line.length(); x++) {
                grid[y][x] = new Octopus(Integer.parseInt(String.valueOf(line.charAt(x))));
                octopusCount++;
            }
        }

        int steps = 0;
        do {
            flashes = nextStep(grid, r, c);
            steps++;
        } while (flashes != octopusCount);
        System.out.printf("First step during which all octopuses flash: %d%n", steps);
        return String.valueOf(steps);
    }

    private int nextStep(Octopus[][] grid, int r, int c) {
        Set<Octopus> flashed= new HashSet<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i,j});
                while (!queue.isEmpty()) {
                    int[] cell=queue.poll();
                    int cx = cell[0],cy= cell[1];
                    Octopus octopus = grid[cx][cy];
                    if(flashed.contains(octopus)) continue;
                    if(octopus.step()) {
                        flashed.add(octopus);
                        queue.addAll(getNeighbours(cy, cx, r, c));
                    }
                }
            }
        }

        return flashed.size();
    }

    private List<int[]> getNeighbours(int i,int j,int r, int c) {
        int[][] directions = new int[][]{{-1, -1,}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};
        List<int[]> result = new ArrayList<>();
        for (int[] direction : directions) {
            int cx = j + direction[0];
            int cy = i + direction[1];
            if (cy >= 0 && cy < r)
                if (cx >= 0 && cx < c) {
                    result.add(new int[]{cx, cy});
                }
        }
        return result;
    }
}
