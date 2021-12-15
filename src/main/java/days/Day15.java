package days;

import java.util.*;

public class Day15 extends Day {

    static int[][] offset = { {-1,0}, {0,1}, {1,0}, {0,-1} };

    static class Cell
    {
        int x;
        int y;
        int risk;

        Cell(int x, int y, int risk)
        {
            this.x = x;
            this.y = y;
            this.risk = risk;
        }
    }

    static class riskComparator implements Comparator<Cell> {
        public int compare(Cell a, Cell b) {
            return Integer.compare(a.risk, b.risk);
        }
    }

    public Day15(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        int[][] grid = new int[input.length][input[0].length()];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length(); j++) {
                grid[i][j]=Integer.parseInt(String.valueOf(input[i].charAt(j)));
            }
        }
        int result =shortestPath(grid,grid.length,grid[0].length);
        System.out.printf("Lowest total risk: %d%n", result);
        return String.valueOf(result);
    }


    static boolean isValid(int i, int j, int ROW, int COL)
    {
        return (i >= 0 && i < ROW &&
                j >= 0 && j < COL);
    }

    int shortestPath(int[][] grid, int rows, int cols) {
        int[][] dist = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        dist[0][0] = grid[0][0];

        PriorityQueue<Cell> queue = new PriorityQueue<>(rows * cols, new riskComparator());

        queue.add(new Cell(0, 0, dist[0][0]));
        while (!queue.isEmpty()) {
            Cell curr = queue.poll();
            for (int i = 0; i < 4; i++) {
                int r = curr.x + offset[i][0];
                int c = curr.y + offset[i][1];

                if (isValid(r, c, rows, cols)) {
                    if (dist[r][c] > dist[curr.x][curr.y] + grid[r][c]) {
                        if (dist[r][c] != Integer.MAX_VALUE) {
                            queue.remove(new Cell(r, c, dist[r][c]));
                        }

                        dist[r][c] = dist[curr.x][curr.y] + grid[r][c];

                        queue.add(new Cell(r, c, dist[r][c]));
                    }
                }
            }
        }

        return dist[rows - 1][cols - 1] - dist[0][0];
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        int[][] grid = new int[input.length * 5][input[0].length() * 5];
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input[0].length(); j++) {
                grid[i][j] = Integer.parseInt(String.valueOf(input[i].charAt(j)));
            }
        }

        int c = input[0].length();

        for (int offset = c; offset < c*5; offset++) {
            for (int j = 0; j < input[0].length(); j++) {
                grid[j][offset] = (grid[j][offset - c] + 1) > 9 ? 1 : (grid[j][offset - c] + 1);
            }
        }
        int r = input.length;
        for (int rOffset = r; rOffset < r*5; rOffset++) {
            for (int cOffset = 0; cOffset < c * 5; cOffset++) {
                grid[rOffset][cOffset] = (grid[rOffset - r][cOffset] + 1) > 9 ? 1 : (grid[rOffset - r][cOffset] + 1);
            }
        }
        int result =shortestPath(grid,grid.length,grid[0].length);
        System.out.printf("Lowest total risk: %d%n", shortestPath(grid, grid.length, grid[0].length));
        return String.valueOf(result);
    }
}
