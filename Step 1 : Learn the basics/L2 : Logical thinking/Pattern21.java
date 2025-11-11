public class Pattern21 {
    
    static void pattern21(int N){

        for(int i=0;i<N;i++){
            System.out.print("*");
        }

        System.out.println();    

        for(int j=0;j<N-2;j++){

            for(int k=0;k<1;k++){
            System.out.print("*");
            }

            for(int k=0;k<N-2;k++){
            System.out.print(" ");
            }

            for(int k=0;k<1;k++){
            System.out.print("*");
        }

        System.out.println();

    }

        for(int i=0;i<N;i++){
            System.out.print("*");
    }

}
    public static void main(String[] args) {
            int N = 5;
            pattern21(N);
        }
}
    
