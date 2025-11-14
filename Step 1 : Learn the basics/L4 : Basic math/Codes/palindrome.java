import java.util.Scanner;

public class palindrome {

    public static void checkPalindrome() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        int originalNumber = number;
        int reversed = 0;

        while (number != 0) {
            int digit = number % 10;         
            reversed = reversed * 10 + digit; 
            number = number / 10;             
        }

        if (originalNumber == reversed) {
            System.out.println(originalNumber + " is a palindrome.");
        } else {
            System.out.println(originalNumber + " is not a palindrome.");
        }
        sc.close();
    }
    
    public static void main(String[] args) {
        checkPalindrome();
    }
    
}
