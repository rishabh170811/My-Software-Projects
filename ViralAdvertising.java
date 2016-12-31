import java.util.Scanner;

public class ViralAdvertising {
    public static int sum = 0;
    public static int viralCount(int N){
    	if(N == 0)
    		return 5;
    	else{
    		int val = viralCount(N-1);
    			sum = val/2 + sum;
    		return val/2*3;
    	}
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */  
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int finalAns = viralCount(N);
        System.out.println(sum);
    }


}
