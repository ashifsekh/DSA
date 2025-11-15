import java.util.Scanner;

public class sumOfNumbers {

    public static int sumOf(int n) {
        if (n == 0) {
            return 0;
        }
        return (n % 10) + sumOf(n / 10);   // add last digit + recurse on rest
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n = sc.nextInt();

        int result = sumOf(n);
        System.out.println("Sum of digits is: " + result);

        sc.close();
    }
}
