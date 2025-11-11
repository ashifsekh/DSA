// Q18 -

// E 
// D E 
// C D E 
// B C D E 
// A B C D E 


public class Pattern18 {

    static void pattern18(int N) {
        for (int i = 0; i <= N - 1; i++) {

            for (char j = (char)('E' - i); j <= 'E'; j++) {
                System.out.print(j + " ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        int N = 5;
        pattern18(N);
    }
}
