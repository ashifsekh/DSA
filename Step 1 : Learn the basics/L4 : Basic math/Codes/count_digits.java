import java.util.Scanner;

public class count_digits {

public static void countDigits() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a number: ");
    int number = sc.nextInt();
    int count = 0;      

    while (number != 0) {
        number = number / 10; 
        count++;             
    }
    System.out.println("Number of digits: " + count);
    sc.close();

}

    public static void main(String[] args) {
    countDigits();
       
    }

}
