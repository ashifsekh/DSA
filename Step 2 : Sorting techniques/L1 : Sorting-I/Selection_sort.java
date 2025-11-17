public class Selection_sort {

    public static void selectionSort (int arr[], int n){
        for (int i = 0; i < n - 2; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n - 1; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        int arr[] = {64, 25, 12, 22, 11};
        int n = arr.length;
        n++; // Adjusting n to match the selectionSort method's expectation

        selectionSort(arr, n);

        System.out.println("Sorted array:");
        for (int i = 0; i < n-1; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
