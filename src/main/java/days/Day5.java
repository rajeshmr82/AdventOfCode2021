package days;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 extends Day {
    public Day5(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        int overlapMoreThan2=0;
        HashMap<String,Integer> map= new HashMap<>();
        List<Line> lines = Arrays.stream(input).map(Line::new).collect(Collectors.toList());
        for (Line line: lines) {
            int startX = line.getStart().getX(), startY = line.getStart().getY(),
                    endX = line.getEnd().getX(), endY = line.getEnd().getY();

            if(startX==endX){
                for (int i = Math.min(startY,endY); i <=Math.max(startY,endY) ; i++) {
                    String point = startX + "," + i;
                    map.put(point, map.getOrDefault(point, 0) + 1);
                    if (map.get(point) == 2) overlapMoreThan2++;
                }
            }else if(startY==endY){
                for (int i = Math.min(startX,endX); i <=Math.max(startX,endX) ; i++) {
                    String point = i + "," + startY;
                    map.put(point, map.getOrDefault(point, 0) + 1);
                    if (map.get(point) == 2) overlapMoreThan2++;
                }
            }

        }

        System.out.printf("Points do at least two lines overlap: %d%n",overlapMoreThan2);
        return String.valueOf(overlapMoreThan2);
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        int overlapMoreThan2=0;
        HashMap<String,Integer> map= new HashMap<>();
        List<Line> lines = Arrays.stream(input).map(Line::new).collect(Collectors.toList());
        for (Line line: lines) {
            int startX = line.getStart().getX(), startY = line.getStart().getY(),
                    endX = line.getEnd().getX(), endY = line.getEnd().getY();

            if(startX==endX){
                for (int i = Math.min(startY,endY); i <=Math.max(startY,endY) ; i++) {
                    String point = startX + "," + i;
                    map.put(point, map.getOrDefault(point, 0) + 1);
                    if (map.get(point) == 2) overlapMoreThan2++;
                }
            }else if(startY==endY){
                for (int i = Math.min(startX,endX); i <=Math.max(startX,endX) ; i++) {
                    String point = i + "," + startY;
                    map.put(point, map.getOrDefault(point, 0) + 1);
                    if (map.get(point) == 2) overlapMoreThan2++;
                }
            }else if ((startX < endX && startY < endY) || (endX < startX && endY < startY)) {
                int diff = Math.max(startX, endX) - Math.min(startX, endX);
                for (int i = 0; i <= diff; i++) {
                    String point = (Math.min(startX, endX) + i) + "," + (Math.min(startY, endY) + i);
                    map.put(point, map.getOrDefault(point, 0) + 1);
                    if (map.get(point) == 2) overlapMoreThan2++;
                }
            } else {
                int diff = Math.max(startY, endY) - Math.min(startY, endY);
                for (int i = 0; i <= diff; i++) {
                    String point = (Math.min(startX, endX) + i) + "," + (Math.max(startY, endY) - i);
                    map.put(point, map.getOrDefault(point, 0) + 1);
                    if (map.get(point) == 2) overlapMoreThan2++;
                }
            }

        }

        System.out.printf("Points do at least two lines overlap: %d%n",overlapMoreThan2);
        return String.valueOf(overlapMoreThan2);
    }
}
