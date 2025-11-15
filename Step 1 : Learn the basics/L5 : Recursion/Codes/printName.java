import java.util.Scanner;


public class printName {

    public static void printMyName(int n) {
        if (n==0) {
            return;
    }
        else{
            System.out.println("Ashif");
        }
        printMyName(n-1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of times to print the name: ");
        int n = sc.nextInt();
        printMyName(n);
        sc.close();
    }

}


