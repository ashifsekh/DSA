public class Pattern20 {

    static void pattern20(int N) {

            for (int i = 0; i <= N; i++) {

            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }

            for (int j = 0; j < 2 * (N - i); j++) {
                System.out.print(" ");
            }

            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }

                for (int i = 1; i <= N; i++) {

            for (int j = 0; j < N - i + 1; j++) {
                System.out.print("*");
            }

            for (int j = 0; j < 2 * i; j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < N - i + 1; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        int N = 5;
        pattern20(N);
    }
}

    

