// Q15 -

// ABCDE
// ABCD
// ABC
// AB
// A


public class Pattern15 {

     static void pattern14(int N){

        for(int i=N-1;i>=0;i--){

            for(char j='A'; j<='A'+i; j++){
                System.out.print(j);
            }
            System.out.println();
    
}
     }

 public static void main(String[] args) {
        int N = 5;
        pattern14(N);
    }
}
    
    
