import java.util.Scanner;

public class printNumber1toN {

    public static void print1toN(int i, int n) {
        if (i>n) {
            return;
    }
        else{
            System.out.println(i);
        }
        print1toN(i+1,n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number to print : ");
        int n = sc.nextInt();
        print1toN(1,n);
        sc.close();
    }
    
    }
    
