package exercise;

import java.util.Arrays;

// BEGIN
class MinThread extends Thread {
    int[] arr;
    int min;

    public MinThread(int[] array) {
        arr = array;
    }

    public int getMin() {
        return min;
    }

    @Override
    public void run() {
        int minElem = Arrays.stream(arr).sorted().min().getAsInt();
        min = minElem;
    }
}
// END
