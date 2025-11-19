import java.util.Scanner;

public class QuickSort {

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // Partition using first element as pivot. Returns final pivot index.
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low;
        int j = high;

        while (i < j) {
            // Move i forward until arr[i] > pivot
            while (i <= high - 1 && arr[i] <= pivot) {
                i++;
            }

            // Move j backward until arr[j] <= pivot
            while (j >= low + 1 && arr[j] > pivot) {
                j--;
            }

            if (i < j) {
                swap(arr, i, j);
            }
        }

        // place pivot at its correct position
        swap(arr, low, j);
        return j;
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        if (n <= 0) {
            System.out.println("No elements to sort.");
            sc.close();
            return;
        }

        int[] arr = new int[n];
        System.out.println("Enter " + n + " integers:");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Before:");
        for (int v : arr)
            System.out.print(v + " ");
        System.out.println();

        quickSort(arr, 0, n - 1);

        System.out.println("After:");
        for (int v : arr)
            System.out.print(v + " ");
        System.out.println();

        sc.close();
    }
}
