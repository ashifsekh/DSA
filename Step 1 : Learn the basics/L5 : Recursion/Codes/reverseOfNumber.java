import java.util.Scanner;


public class reverseOfNumber {

    public static int reverseNumber(int n, int rev) {
        if (n == 0) {
            return rev;
        }
        int digit = n % 10;
        rev = rev * 10 + digit;
        return reverseNumber(n / 10, rev);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number to reverse: ");
        int n = sc.nextInt();
        int reversedNumber = reverseNumber(n, 0);
        System.out.println("Reversed Number: " + reversedNumber);
        sc.close();
    }

    
}
