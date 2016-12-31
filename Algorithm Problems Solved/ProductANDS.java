import java.util.Scanner;

public class ProductANDS {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i=0;i<T;i++){
            long a = sc.nextLong();
            long b = sc.nextLong();
            int counter = 0;
            long andValue = a;
            while((a+(long)Math.pow(2,counter)) < b){
                andValue = andValue & (a+(long)Math.pow(2,counter++));
            }
            System.out.println(andValue & b);
        }
    }
}