package exercise;

import java.util.Arrays;

// BEGIN
class MaxThread extends Thread {
    private int[] arr;
    private int max;

    public MaxThread(int[] array) {
        arr = array;
    }

    public int getMax() {
        return max;
    }

    @Override
    public void run() {
        int maxElem = Arrays.stream(arr).sorted().max().getAsInt();
        max = maxElem;
    }
}
// END
