package exercise;

// BEGIN
class Circle {
    Point center;
    int radius;

    public Circle(Point center, int radius) {
        this.radius = radius;
        this.center = center;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        }
        return Math.PI * radius * radius;
    }

}
// END
