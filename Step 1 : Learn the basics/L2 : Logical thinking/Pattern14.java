// Q14 -

// A
// AB
// ABC
// ABCD
// ABCDE


public class Pattern14 {

     static void pattern14(int N){

        for(int i=0;i<=N-1;i++){

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
    

