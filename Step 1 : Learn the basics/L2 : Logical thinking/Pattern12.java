// Q12 -
// 0        0
// 01      10
// 012    210
// 0123  3210
// 0123443210


public class Pattern12 {

    static void pattern12(int N){

        for(int i=0;i<N;i++){

            for(int j=0;j<=i;j++){
                System.out.print(j);
            }
            for(int j=0;j<2*(N-i-1);j++){
                System.out.print(" ");
            }
            for(int j=i;j>=0;j--){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int N = 5;
        pattern12(N);
    }
}
    

