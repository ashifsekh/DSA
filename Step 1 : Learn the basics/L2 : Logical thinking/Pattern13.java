// Q13 -
// 1 
// 2 3 
// 4 5 6 
// 7 8 9 10 
// 11 12 13 14 15 


public class Pattern13 {

     static void pattern13(int N){

        int num =1;
        for(int i=1;i<=N;i++){

            for(int j=1;j<=i;j++){
                System.out.print(num + " ");
                num++;
            }
            System.out.println();
    
}
     }

 public static void main(String[] args) {
        int N = 5;
        pattern13(N);
    }
}
    

