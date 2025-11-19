import java.util.ArrayList;
import java.util.Scanner;

public class Merge_sort {

    // Helper function to merge two sorted subarrays
    public static void merge(int[] arr, int low, int mid, int high) {
        // 1. Create temporary list and pointers
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low;
        int right = mid + 1;

        // 2. Comparison and addition to temp
        while (left <= mid && right <= high) {
            if (arr[left] <= arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        // 3. Copy remaining elements (if any)
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // 4. Copy temp back to the original array (A)
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low); // i - low maps temp's 0-index to A's low-index
        }
    }

    // Main MergeSort Function
    public static void mergeSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
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
        System.out.println("Enter " + n + " integers (separated by space or newline):");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Before:");
        for (int v : arr)
            System.out.print(v + " ");
        System.out.println();

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("After:");
        for (int v : arr)
            System.out.print(v + " ");
        System.out.println();

        sc.close();
    }
}
