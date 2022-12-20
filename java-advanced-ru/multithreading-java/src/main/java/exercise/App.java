package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    public static void main(String[] args) {
        int[] numbers = {10, -4, 67, 100, -100, 8};

        System.out.println(App.getMinMax(numbers));
    }

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] arr) {
        Map<String, Integer> result = new HashMap<>();

        MaxThread maxThread = new MaxThread(arr);
        MinThread minThread = new MinThread(arr);

        maxThread.start();
        LOGGER.info("Thread " + maxThread.getName() + " started");
        result.put("max", maxThread.getMax());
        LOGGER.info("Thread " + maxThread.getName() + " finished");

        minThread.start();
        LOGGER.info("Thread " + minThread.getName() + " started");
        result.put("min", minThread.getMin());
        LOGGER.info("Thread " + minThread.getName() + " finished");
        return result;

    }
    // END
}
