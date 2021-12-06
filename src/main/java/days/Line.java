package days;

public class Line {
    private Point start;
    private Point end;

    public Line(String line){
        String[] points = line.split(" -> ");
        this.start = new Point(points[0]);
        this.end = new Point(points[1]);
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }
}
