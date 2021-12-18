package days;

import java.awt.Point;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day17 extends Day {

    static class Probe {
        private final Point targetMin;
        private final Point targetMax;

        public Probe(int minX, int maxX, int minY, int maxY) {
            targetMin = new Point(minX, minY);
            targetMax = new Point(maxX, maxY);
        }

        public int getVelocityCount() {
            int goodCount = 0;
            int lowerX = (int) Math.ceil((Math.sqrt(1 + 8 * targetMin.getX()) - 1) / 2);
            int upperX = (int) targetMax.getX();

            int lowerY = (int) targetMin.getY();
            int upperY = (int) Math.max(-1 * targetMin.getY() - 1, targetMax.getY());

            for (int vx = lowerX; vx <= upperX; vx++) {
                for (int vy = lowerY; vy <= upperY; vy++) {
                    if (attempt(vx, vy)) goodCount++;
                }
            }

            return goodCount;
        }

        private boolean attempt(int vx, int vy) {
            Point point = new Point(0, 0);
            while (point.x <= this.targetMax.getX() &&
                    point.y >= this.targetMin.getY()) {
                point.move(point.x + vx, point.y + vy);
                vx = Math.max(0, vx - 1);
                vy--;
                if (inRange(point)) {
                    return true;
                }
            }
            return false;
        }

        private boolean inRange(Point point) {
            return (point.x >= this.targetMin.getX() &&
                    point.x <= this.targetMax.getX() &&
                    point.y >= this.targetMin.getY() &&
                    point.y <= this.targetMax.getY());
        }
    }

    public Day17(Boolean isTest) {
        super(isTest);
    }

    public String part1() {
        String[] input = this.getInput().split("\r\n");
        Pattern pattern = Pattern.compile("target area: x=(-?\\d*\\.?\\d)..(-?\\d*\\.?\\d), y=(-?\\d*\\.?\\d)..(-?\\d*\\.?\\d)");
        Matcher m = pattern.matcher(input[0]);
        long maxHeight = 0;
        if (m.find()) {
            int minY = Integer.parseInt(m.group(3));
            maxHeight = (long) (Math.abs(minY)) * (Math.abs(minY) - 1) / 2;
        }

        System.out.printf("Highest y position: %d%n", maxHeight);
        return String.valueOf(maxHeight);
    }


    public String part2() {
        String[] input = this.getInput().split("\r\n");
        Pattern pattern = Pattern.compile("target area: x=(-?\\d*\\.?\\d)..(-?\\d*\\.?\\d), y=(-?\\d*\\.?\\d)..(-?\\d*\\.?\\d)");
        Matcher m = pattern.matcher(input[0]);
        int result = 0;
        if (m.find()) {

            int minX = Integer.parseInt(m.group(1)),
                    maxX = Integer.parseInt(m.group(2)),
                    minY = Integer.parseInt(m.group(3)),
                    maxY = Integer.parseInt(m.group(4));

            System.out.printf("xMin:%s,xMax:%s,%nyMin:%s,yMax:%s%n", minX, maxX, minY, maxY);

            Probe probe = new Probe(minX, maxX, minY, maxY);
            result = probe.getVelocityCount();

        }

        System.out.printf("Distinct Initial Velocity values: %d%n", result);
        return String.valueOf(result);
    }

}