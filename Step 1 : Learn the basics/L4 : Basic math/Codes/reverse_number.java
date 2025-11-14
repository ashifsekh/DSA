import java.util.Scanner;

public class reverse_number {

    public static void reverseNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = sc.nextInt();
        int reversed = 0;

        while (number != 0) {
            int digit = number % 10;         
            reversed = reversed * 10 + digit; 
            number = number / 10;             
        }
        System.out.println("Reversed Number: " + reversed);
        sc.close();
    }
    
    public static void main(String[] args) {
        reverseNumber();
    }
    
}
