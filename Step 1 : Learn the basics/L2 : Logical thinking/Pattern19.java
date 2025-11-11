public class Pattern19 {

    static void pattern19(int N) {

        // Upper half
        for (int i = 0; i <= N; i++) {

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

        // Lower half
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
    }

    public static void main(String[] args) {
        int N = 5;
        pattern19(N);
    }
}
