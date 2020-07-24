

import java.util.Random;

/**
 * Comparing to two sorting method runtime,
 * by making them to sort a said size array filled with random Integers.
 * @author Zichang Liu
 * @version July 20
 */
public class SortTester {
    /**
     * Partition for Quick Sort.
     * Retrieved from http://www.algolist.net/Algorithms/Sorting/Quicksort.
     * @param arr array to be sorted.
     * @param left Part of the array that is smaller than pivot.
     * @param right Part of the array that is larger than pivot.
     * @return Index of the new pivot.
     */
    public int partition(int[] arr, int left, int right) {
        int i = left;
        int j = right;
        int tmp;
        int pivot = arr[(left + right) / 2];
        while (i <= j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                i++;
                j--;
            }
        }
        return i;
    }

    /**
     * Wrapper function for Quick Sort, keep tracks of the runtime.
     * @param arr array to be sorted.
     * @param left Part of the array that is smaller than pivot.
     * @param right Part of the array that is larger than pivot.
     * @return the total run time of Quick Sort.
     */
    public long timeLogSort(int[] arr, int left, int right) {
        long startTime = System.nanoTime();
        logarithmicSort(arr, left, right - 1);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * Quick sort.
     * Retrieved from http://www.algolist.net/Algorithms/Sorting/Quicksort.
     * @param arr array to be sorted.
     * @param left Part of the array that is smaller than pivot.
     * @param right Part of the array that is larger than pivot.
     */
    public void logarithmicSort(int[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            logarithmicSort(arr, left, index - 1);
        }
        if (index < right) {
            logarithmicSort(arr, index, right);
        }

    }

    /**
     * Selection Sort.
     * Retrieved from https://www.geeksforgeeks.org/selection-sort/.
     * @param arr Array to be sort.
     * @return Total run time of Selection Sort.
     */
    public long quadraticSort(int[] arr) {
        long startTime = System.nanoTime();
        int n = arr.length;
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int minimumIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minimumIndex]) {
                    minimumIndex = j;
                }
            }
            // Swap the found minimum element with the first
            // element
            int temp = arr[minimumIndex];
            arr[minimumIndex] = arr[i];
            arr[i] = temp;
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * Setting up the array for testing.
     * @param setup Array to be set up.
     * @param size size to be set up.
     * @return Array that completed the set up.
     */
    public int[] setUp(int[] setup, int size) {
        Random setUpInt = new Random();
        for (int i = 0; i < size; i++) {
            setup[i] = setUpInt.nextInt(size);
        }
        return setup;
    }
}
