package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day22 extends Day {

    static class Cube {
        int xMin;
        int xMax;
        int yMin;
        int yMax;
        int zMin;
        int zMax;

        public Cube(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax) {
            this.xMin = xMin;
            this.xMax = xMax;
            this.yMin = yMin;
            this.yMax = yMax;
            this.zMin = zMin;
            this.zMax = zMax;
        }

        public long volume() {
            return (long)
                    Math.abs(xMax - xMin) *
                    Math.abs(yMax - yMin) *
                    Math.abs(zMax - zMin);
        }

        public boolean contains(Cube cube) {
            return this.xMin <= cube.xMin &&
                    this.xMax >= cube.xMax &&
                    this.yMin <= cube.yMin &&
                    this.yMax >= cube.yMax &&
                    this.zMin <= cube.zMin &&
                    this.zMax >= cube.zMax;
        }

        public boolean doesIntersect(Cube cube) {
            return this.xMin <= (cube.xMax - 1) &&
                    (this.xMax - 1) >= cube.xMin &&
                    this.yMin <= (cube.yMax - 1) &&
                    (this.yMax - 1) >= cube.yMin &&
                    this.zMin <= (cube.zMax - 1) &&
                    (this.zMax - 1) >= cube.zMin;
        }

        public List<Cube> subtract(Cube other) {
            List<Cube> newCubes = new ArrayList<>();
            if (!doesIntersect(other)) {
                newCubes.add(this);
                return newCubes;
            }

            if (other.contains(this)) return new ArrayList<>();

            List<int[]> xPairs = getPairs(this.xMin,this.xMax, other.xMin, other.xMax);
            List<int[]> yPairs = getPairs(this.yMin,this.yMax, other.yMin, other.yMax);
            List<int[]> zPairs = getPairs(this.zMin,this.zMax, other.zMin, other.zMax);

            for (int[] x:xPairs) {
                for (int[] y: yPairs) {
                    for (int[] z: zPairs) {
                        Cube candidate = new Cube(x[0],x[1],y[0],y[1],z[0],z[1]);
                        if(this.contains(candidate) && !candidate.doesIntersect(other))
                            newCubes.add(candidate);
                    }
                }
            }
            return newCubes;
        }

        private List<int[]> getPairs(int x0, int x1, int x2, int x3) {
            int[] arr = new int[]{x0, x1, x2, x3};
            Arrays.sort(arr);
            return IntStream
                    .range(0, arr.length - 1)
                    .mapToObj(i -> new int[]{arr[i], arr[i + 1]}).collect(Collectors.toList());
        }

        @Override
        public String toString() {
            return "Cube{" +
                    "xMin=" + xMin +
                    ", xMax=" + xMax +
                    ", yMin=" + yMin +
                    ", yMax=" + yMax +
                    ", zMin=" + zMin +
                    ", zMax=" + zMax +
                    '}';
        }
    }

    public Day22(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        Pattern onPattern= Pattern.compile("(on|off) x=(-?\\d*\\.?\\d)..(-?\\d*\\.?\\d),y=(-?\\d*\\.?\\d)..(-?\\d*\\.?\\d),z=(-?\\d*\\.?\\d)..(-?\\d*\\.?\\d)");

        List<Cube> cubes = new ArrayList<>();
        for (String line: input) {
            Matcher matcher = onPattern.matcher(line);
            if (matcher.find()) {
                String operation = matcher.group(1);
                int xMin = Integer.parseInt(matcher.group(2));
                int xMax = Integer.parseInt(matcher.group(3));
                int yMin = Integer.parseInt(matcher.group(4));
                int yMax = Integer.parseInt(matcher.group(5));
                int zMin = Integer.parseInt(matcher.group(6));
                int zMax = Integer.parseInt(matcher.group(7));

                if (Math.abs(xMin) > 50 || Math.abs(xMax) > 50 || Math.abs(yMin) > 50 ||
                        Math.abs(yMax) > 50 || Math.abs(zMax) > 50 || Math.abs(zMin) > 50)
                    continue;
                Cube current = new Cube(xMin, xMax+1, yMin, yMax+1, zMin, zMax+1);
                List<Cube> list= new ArrayList<>();
                for (Cube other:cubes
                     ) {
                    list.addAll(other.subtract(current));
                }
                if(operation.equals("on")){
                    list.add(current);
                }
                cubes = list;
            }
        }

        long result=0;
        for (Cube cube: cubes) {
            result += cube.volume();
        }

        System.out.printf("Number of on cubes: %d%n", result);
        return String.valueOf(result);
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");
        Pattern onPattern= Pattern.compile("(on|off) x=(-?\\d*\\.?\\d)..(-?\\d*\\.?\\d),y=(-?\\d*\\.?\\d)..(-?\\d*\\.?\\d),z=(-?\\d*\\.?\\d)..(-?\\d*\\.?\\d)");

        List<Cube> cubes = new ArrayList<>();
        for (String line: input) {
            Matcher matcher = onPattern.matcher(line);
            if (matcher.find()) {
                String operation = matcher.group(1);
                int xMin = Integer.parseInt(matcher.group(2));
                int xMax = Integer.parseInt(matcher.group(3));
                int yMin = Integer.parseInt(matcher.group(4));
                int yMax = Integer.parseInt(matcher.group(5));
                int zMin = Integer.parseInt(matcher.group(6));
                int zMax = Integer.parseInt(matcher.group(7));

                Cube current = new Cube(xMin, xMax+1, yMin, yMax+1, zMin, zMax+1);
                List<Cube> list= new ArrayList<>();
                for (Cube other:cubes
                ) {
                    list.addAll(other.subtract(current));
                }
                if(operation.equals("on")){
                    list.add(current);
                }
                cubes = list;
            }
        }

        long result=0;
        for (Cube cube: cubes) {
            result += cube.volume();
        }

        System.out.printf("Number of on cubes: %d%n", result);
        return String.valueOf(result);
    }

}