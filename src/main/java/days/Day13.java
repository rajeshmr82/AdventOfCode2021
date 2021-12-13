package days;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.awt.Point;

public class Day13 extends Day {

    public Day13(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        int dotCount;
        Set<Point> dots = new HashSet<>();
        List<String> instructions = new ArrayList<>();
        for (String line : input
        ) {
            if(line.isEmpty()) continue;

            if (line.startsWith("fold")) {
                instructions.add(line);
            } else {
                String[] parts = line.split(",");
                dots.add(new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            }
        }

        Pattern pattern = Pattern.compile("fold along ([a-zA-Z]+)=([0-9]+)");
        for (int i = 0; i < 1; i++) {
            String inst = instructions.get(0);
            Matcher m = pattern.matcher(inst);
            if(m.find()){
                String axis = m.group(1);
                int coordinate = Integer.parseInt(m.group(2));

                if(axis.equals("y")){
                    dots =  foldUp(coordinate,dots);
                }else{
                    dots = foldLeft(coordinate,dots);
                }
            }
        }

        dotCount = dots.size();

        System.out.printf("Dots that are visible: %d%n", dotCount);
        return String.valueOf(dotCount);
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        int dotCount;
        Set<Point> dots = new HashSet<>();
        List<String> instructions = new ArrayList<>();
        for (String line : input
        ) {
            if(line.isEmpty()) continue;

            if (line.startsWith("fold")) {
                instructions.add(line);
            } else {
                String[] parts = line.split(",");
                dots.add(new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            }
        }

        Pattern pattern = Pattern.compile("fold along ([a-zA-Z]+)=([0-9]+)");
        for (String inst:instructions
             ) {
            Matcher m = pattern.matcher(inst);
            if(m.find()){
                String axis = m.group(1);
                int coordinate = Integer.parseInt(m.group(2));

                if(axis.equals("y")){
                    dots = foldUp(coordinate,dots);
                }else{
                    dots = foldLeft(coordinate,dots);
                }
            }
        }
        print(dots);
        System.out.println("---------------------");
        dotCount = dots.size();


        System.out.printf("Dots that are visible: %d%n", dotCount);
        return String.valueOf(dotCount);
    }

    private void print(Set<Point> dots) {
        int maxX = dots.stream().mapToInt(d->d.x).max().getAsInt();
        int maxY = dots.stream().mapToInt(d->d.y).max().getAsInt();

        for (int r = 0; r <= maxY; r++) {
            for (int c = 0; c <= maxX; c++) {
                if(dots.contains(new Point(c,r)))
                    System.out.printf("%c",0x2593);
                else
                    System.out.printf("%c",0x2591);
            }

            System.out.println();
        }
    }

    private Set<Point> foldUp(int coordinate, Set<Point> dots) {
        Set<Point> newPaper = dots.stream().filter(d -> d.getY() < coordinate).collect(Collectors.toCollection(HashSet::new));
        List<Point> fold = dots.stream().filter(d -> d.getY() > coordinate).collect(Collectors.toList());

        for (Point dot : fold
        ) {
            newPaper.add(new Point(dot.x, coordinate - (dot.y - coordinate)));
        }

        return newPaper;
    }

    private Set<Point> foldLeft(int coordinate, Set<Point> dots) {
        Set<Point> newPaper = dots.stream().filter(d -> d.getX() < coordinate).collect(Collectors.toCollection(HashSet::new));
        List<Point> fold = dots.stream().filter(d -> d.getX() > coordinate).collect(Collectors.toList());

        for (Point dot : fold
        ) {
            newPaper.add(new Point(coordinate - (dot.x - coordinate), dot.y));
        }

        return newPaper;
    }

}
