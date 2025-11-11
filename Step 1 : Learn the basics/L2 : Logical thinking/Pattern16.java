// Q16 -

// A
// BB
// CCC
// DDDD
// EEEEE


public class Pattern16 {

      static void pattern16(int N){
        char x = 'A';

        for(int i=0;i<=N-1;i++){

            for(int j=0; j<=i; j++){
                System.out.print(x);
            }

             x = (char)(x + 1);

            System.out.println();
    
}
     }

 public static void main(String[] args) {
        int N = 5;
        pattern16(N);
    }
}
    

