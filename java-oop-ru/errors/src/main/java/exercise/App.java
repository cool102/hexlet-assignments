package exercise;

// BEGIN
class App {
    static void printSquare(Circle circle) throws NegativeRadiusException {
        try {
            double result;
            result = circle.getSquare();
            System.out.println((int) Math.round(result));
        } catch (NegativeRadiusException nre) {
            System.out.println(nre.getMessage());
        } finally {
            System.out.println("Вычисление окончено");
        }


    }

    public static void main(String[] args) throws NegativeRadiusException {
        Point point = new Point(5, 7);
        Circle circle = new Circle(point, 4);
        App.printSquare(circle);

        Circle circle1 = new Circle(point, -2);
        App.printSquare(circle1);
    }
}
// END
