package days;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day19 extends Day {

    static class Scanner {
        int id;
        List<Point3D> beacons = new ArrayList<>();
        Point3D position;

        public Scanner(int id) {
            this.id = id;
        }

        public void addBeacon(Point3D point) {
            this.beacons.add(point);
        }

        public int getId() {
            return id;
        }

        public Point3D getPosition() {
            return position;
        }

        public void setPosition(Point3D position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return String.format("--- scanner %d ---%n%s%n", id, beacons);
        }

        public int distance(Scanner scanner) {
            return Math.abs(scanner.position.x - this.position.x) +
                    Math.abs(scanner.position.y - this.position.y) +
                    Math.abs(scanner.position.z - this.position.z);
        }
    }

    static class Match {
        Axes axis;
        int orientation;
        int diff;

        public Match(Axes axis, int orientation, int diff) {
            this.axis = axis;
            this.orientation = orientation;
            this.diff = diff;
        }

        @Override
        public String toString() {
            return "{" +
                    "axis=" + axis +
                    ", orientation=" + orientation +
                    ", diff=" + diff +
                    '}';
        }
    }

    static int[] orientations = new int[]{-1,1};
    //static int[] axes = new int[]{0,1,2};
    public enum Axes {
        X_AXIS,
        Y_AXIS,
        Z_AXIS
    }

    public Day19(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");

        LinkedList<Scanner> scanners = new LinkedList<>();
        readScanners(input, scanners);
        Set<Point3D> allBeacons = matchBeacons(scanners);

        System.out.printf("Beacon Count: %d%n",allBeacons.size());
        return String.valueOf(allBeacons.size());
    }

    private Set<Point3D> matchBeacons(LinkedList<Scanner> scanners) {
        HashMap<Integer,Scanner> dictionary = (HashMap<Integer, Scanner>) scanners.stream().collect(Collectors.toMap(s ->s.id, s->s));
        Set<Point3D> allBeacons = new HashSet<>(scanners.getFirst().beacons);
        scanners.getFirst().setPosition(new Point3D(0,0,0));
        Queue<Scanner> queue = new ArrayDeque<>();
        queue.add(scanners.getFirst());

        while (!queue.isEmpty()) {
            Scanner source = queue.poll();

            HashMap<Integer, Match> mapX = new HashMap<>();
            HashMap<Integer, Match> mapY = new HashMap<>();
            HashMap<Integer, Match> mapZ = new HashMap<>();
            findMatchesAlongAxes(source, dictionary, mapX, mapY, mapZ);


            for (Map.Entry<Integer, Match> entry : mapX.entrySet()
            ) {
                Match xDiff = mapX.get(entry.getKey());
                Match yDiff = mapY.get(entry.getKey());
                Match zDiff = mapZ.get(entry.getKey());

                //Now get the adjacent scanner based on match
                Scanner next = dictionary.get(entry.getKey());
                dictionary.remove(entry.getKey());
                next.setPosition(new Point3D(xDiff.diff,yDiff.diff,zDiff.diff));
                //Orienting the found point to align with the reference
                for (Point3D p : next.beacons
                ) {
                    p.update(xDiff.diff + xDiff.orientation * p.getAxis(xDiff.axis),
                            yDiff.diff + yDiff.orientation * p.getAxis(yDiff.axis),
                            zDiff.diff + zDiff.orientation * p.getAxis(zDiff.axis));
                }
                allBeacons.addAll(next.beacons);
                queue.add(next);
            }
        }
        return allBeacons;
    }

    private void findMatchesAlongAxes(Scanner source,
                                      HashMap<Integer,Scanner> dictionary,
                                      HashMap<Integer, Match> mapX,
                                      HashMap<Integer, Match> mapY,
                                      HashMap<Integer, Match> mapZ
                                                     ) {
        List<Scanner> otherScanners = new ArrayList<>(dictionary.values());

        for (Scanner other : otherScanners) {
            for (Axes axis : Axes.values()) {
                for (int ori : orientations) {
                    HashMap<Integer, Integer> xDiffMap = new HashMap<>();
                    for (Point3D srcPoint : source.beacons) {
                        for (Point3D otherPoint : other.beacons) {
                            int xDiff = srcPoint.x - otherPoint.getAxis(axis) * ori;
                            xDiffMap.put(xDiff, xDiffMap.getOrDefault(xDiff, 0) + 1);
                        }
                    }

                    LinkedHashMap<Integer, Integer> collect = xDiffMap.entrySet().stream()
                            .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                            .limit(1)
                            .collect(Collectors.toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (a, b) -> a,
                                    LinkedHashMap::new));

                    Map.Entry<Integer, Integer> mostFrequent = (Map.Entry<Integer, Integer>) collect.entrySet().toArray()[0];
                    if (mostFrequent.getValue() >= 12) {
                        mapX.put(other.id, new Match(axis, ori, mostFrequent.getKey()));
                    }
                }
            }
        }

        for (Map.Entry<Integer, Match> candidate : mapX.entrySet()) {
            Scanner other = dictionary.get(candidate.getKey());
            for (Axes axis : Axes.values()) {
                for (int ori : orientations) {
                    HashMap<Integer, Integer> yDiffMap = new HashMap<>();
                    HashMap<Integer, Integer> zDiffMap = new HashMap<>();

                    for (Point3D srcPoint : source.beacons) {
                        for (Point3D othPoint : other.beacons) {
                            int yDiff = srcPoint.y - othPoint.getAxis(axis) * ori;
                            yDiffMap.put(yDiff, yDiffMap.getOrDefault(yDiff, 0) + 1);
                            int zDiff = srcPoint.z - othPoint.getAxis(axis) * ori;
                            zDiffMap.put(zDiff, zDiffMap.getOrDefault(zDiff, 0) + 1);
                        }
                    }

                    LinkedHashMap<Integer, Integer> yFreq = yDiffMap.entrySet().stream()
                            .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                            .limit(1)
                            .collect(Collectors.toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (a, b) -> a,
                                    LinkedHashMap::new));

                    Map.Entry<Integer, Integer> mostFrequentY = (Map.Entry<Integer, Integer>) yFreq.entrySet().toArray()[0];
                    if (mostFrequentY.getValue() >= 12) {
                        mapY.put(other.id, new Match(axis, ori, mostFrequentY.getKey()));
                    }

                    LinkedHashMap<Integer, Integer> zFreq = zDiffMap.entrySet().stream()
                            .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                            .limit(1)
                            .collect(Collectors.toMap(Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (a, b) -> a,
                                    LinkedHashMap::new));

                    Map.Entry<Integer, Integer> mostFrequentZ = (Map.Entry<Integer, Integer>) zFreq.entrySet().toArray()[0];
                    if (mostFrequentZ.getValue() >= 12) {
                        mapZ.put(other.id, new Match(axis, ori, mostFrequentZ.getKey()));
                    }
                }
            }
        }
    }


    private void readScanners(String[] input, LinkedList<Scanner> scanners) {
        Scanner scanner = null;
        int id=0;
        for (String line : input
             ) {
            if(line.startsWith("---")){
                scanner =  new Scanner(id++);
                continue;
            }
            if(line.isEmpty()) {
                scanners.add(scanner);
                continue;
            }
            Pattern pattern = Pattern.compile("(-?\\d*\\.?\\d),(-?\\d*\\.?\\d),(-?\\d*\\.?\\d)");
            Matcher matcher = pattern.matcher(line);
            if(matcher.find()) {
                scanner.addBeacon(new Point3D(
                        Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2)),
                        Integer.parseInt(matcher.group(3))
                ));
            }
        }
        scanners.add(scanner);
    }

    public String part2() {
        String[] input = this.getInput().split("\r\n");

        LinkedList<Scanner> scanners = new LinkedList<>();
        readScanners(input, scanners);
        Set<Point3D> allBeacons = matchBeacons(scanners);
        int maxDistance=0;
        for (int i = 0; i < scanners.size(); i++) {
            for (int j = i+1; j < scanners.size(); j++)
                maxDistance = Math.max(maxDistance,
                        scanners.get(i).distance(scanners.get(j)));
        }
        System.out.printf("Largest Manhattan distance between any two scanners: %d%n",maxDistance);
        return String.valueOf(maxDistance);
    }

}