package days;

import java.util.*;
import java.util.stream.Collectors;

public class Day09 extends Day {
    public Day09(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        int riskLevels = 0;
        List<List<Integer>> heatMap = new ArrayList<>();
        for (String line : input
        ) {
            heatMap.add(Arrays.stream(line.split("\\B"))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList()));
        }

        int r = heatMap.size();
        int c = heatMap.get(0).size();
        int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int current = heatMap.get(i).get(j);
                boolean foundLower = false;
                for (int[] direction : directions) {
                    int cx = j + direction[0];
                    int cy = i + direction[1];
                    if (cy >= 0 && cy < r)
                        if (cx >= 0 && cx < c) {
                            if(heatMap.get(cy).get(cx) <= current){
                                foundLower= true;
                                break;
                            }
                        }
                }
                if(!foundLower) {
                    riskLevels += (current + 1);
                }
            }
        }
        System.out.printf("Sum of the risk levels: %d%n", riskLevels);
        return String.valueOf(riskLevels);
    }


    public String part2() {
        String[] input = this.getInput().split("\r\n");
        int sizeOfBasin = 1;
        List<List<Integer>> heatMap = new ArrayList<>();
        for (String line : input
        ) {
            heatMap.add(Arrays.stream(line.split("\\B"))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList()));
        }

        int r = heatMap.size();
        int c = heatMap.get(0).size();

        List<Integer> basinSizes = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int current = heatMap.get(i).get(j);
                List<int[]> adjCells = adjacentCells(j,i,r,c);
                boolean foundLower = false;
                for (int[] direction : adjCells) {
                    int cx = direction[0];
                    int cy = direction[1];

                    if (heatMap.get(cy).get(cx) <= current) {
                        foundLower = true;
                        break;
                    }
                }
                if (!foundLower) {
                    int basinSize = getBasinSize(j, i, adjCells, heatMap, r, c);
                    basinSizes.add(basinSize);
                }
            }
        }

        basinSizes.sort(Collections.reverseOrder());
        for (int i = 0; i < 3; i++) {
            sizeOfBasin*= basinSizes.get(i);
        }

        System.out.printf("Sizes of the three largest basins: %d%n", sizeOfBasin);
        return String.valueOf(sizeOfBasin);
    }

    private int getBasinSize(int i, int j, List<int[]> adjCells, List<List<Integer>> heatMap,int r, int c) {
        Queue<int[]> candidates = new LinkedList<>(adjCells);
        HashSet<String> visited= new HashSet<>();
        visited.add(String.format("[%d, %d]", i,j));
        int size=1;
        while (!candidates.isEmpty()){
            int[] current = candidates.remove();
            int cx=current[0],cy=current[1];
            if(cx==i && cy==j || visited.contains(Arrays.toString(current))) continue;
            visited.add(Arrays.toString(current));
            if(heatMap.get(cy).get(cx)!=9){
                size++;
                List<int[]> newCells = adjacentCells(cx, cy, r, c);
                newCells.removeIf(cell -> visited.contains(Arrays.toString(cell)));
                candidates.addAll(newCells);
            }
        }

        return size;
    }

    private List<int[]> adjacentCells(int i,int j,int r, int c) {
        int[][] directions = new int[][]{{-1,0,},{0,-1},{0,1},{1,0}};
        List<int[]> result = new ArrayList<>();
        for (int[] direction : directions) {
            int cx = i + direction[0];
            int cy = j + direction[1];
            if (cy >= 0 && cy < r)
                if (cx >= 0 && cx < c) {
                    result.add(new int[]{cx, cy});
                }
        }
        return result;
    }
}
