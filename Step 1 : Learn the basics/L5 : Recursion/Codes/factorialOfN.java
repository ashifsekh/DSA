import java.util.Scanner;

public class factorialOfN {

    public static int factorialOf(int n) {

        if (n==0) {
        return 0;
    }

    return n*(n-1);   
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number to print : ");
        int n = sc.nextInt();
        factorialOf(n);
        int result = factorialOf(n);
        System.out.println("Sum is: " + result);
        sc.close();
    }
    
    
}
