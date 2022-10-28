package exercise;

import java.util.ArrayList;
import java.util.List;

// BEGIN
class App {
    public static List<String> buildAppartmentsList(List<Home> properties, int n) {
        properties.sort(Home::compareTo);
        List<Home> collect = properties.stream().limit(n).toList();
        List<String> collect1 = collect.stream().map(Object::toString).toList();
        return collect1;
    }

    public static void main(String[] args) {
        List<Home> appartments = new ArrayList<>(List.of(
                new Flat(41, 3, 10),
                new Cottage(125.5, 2),
                new Flat(80, 10, 2),
                new Cottage(150, 3)
        ));

        List<String> result = App.buildAppartmentsList(appartments, 3);
        System.out.println(result);

    }


}
// END
