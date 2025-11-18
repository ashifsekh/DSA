public class Bubble_sort {

    public static void main(String[] args) {
        int arr[] = {45,3,245,98,10};
        int n = arr.length;

        for (int i = 0; i <= n-2; i++) {
            for (int j = 0; j <= n-2-i; j++) {
                if (arr[j] > arr[j+1]) {
            int temp = arr[j];
            arr[j] = arr[j+1];
            arr[j+1] = temp;
                }
            }
            
        }
        for (int k = 0; k<=n-1; k++){
            System.out.println(arr[k]);
        }
    }

    }

    
