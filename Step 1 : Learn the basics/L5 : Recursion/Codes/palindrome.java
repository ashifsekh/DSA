import java.util.Scanner;

public class palindrome {

    public static int checkPalindrome(int n, int rev) {
        if (n == 0) {
            return rev;
        }
        int digit = n % 10;
        rev = rev * 10 + digit;
        return checkPalindrome(n / 10, rev);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        int num = number;
        int result = checkPalindrome(number, 0);
        if (num == result) {
            System.out.println(number + " is a palindrome.");
        } else {
            System.out.println(number + " is not a palindrome.");
        }
        sc.close();
    }
    
}
