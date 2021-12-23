package days;

class Point3D {
    int x;
    int y;
    int z;

    public Point3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getAxis(Day19.Axes axis) {
        switch (axis) {
            case X_AXIS:
                return x;
            case Y_AXIS:
                return y;
            case Z_AXIS:
                return z;
            default:
                return -1;
        }
    }

    public void update(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return String.format("{%d,%d,%d}", x, y, z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point3D point = (Point3D) o;

        return x == point.x && y == point.y && z == point.z;
    }

    @Override
    public int hashCode() {
        return this.x + this.y + this.z;
    }

}
