import java.util.*;

public class TotalWaysToReachNCoin {
    public static long findTotalWaysToReachSum(long total_ways[][],int arr[],int N,int sum){
        for(int i=0;i<N;i++){
            for(int j=1;j<=sum;j++){
                if(arr[i]>j){
                	if(i>0)
                		total_ways[i][j] = total_ways[i-1][j];
                	else
                		total_ways[i][j] = 0;
                }
                else{
                    //boundary conditions
                    if(i==0){
                    	
                        total_ways[i][j] = total_ways[i][j-arr[i]];
                    }
                    else{
                        total_ways[i][j] = total_ways[i-1][j]+total_ways[i][j-arr[i]];
                    }
                    }
            }
        }
        return total_ways[N-1][sum];
    }
    public static void main(String[] args) {
     @SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
        int sum = sc.nextInt();
        int N = sc.nextInt();
        int arr[] = new int[N];
        for(int i=0;i<N;i++){
           arr[i] = sc.nextInt();
        }
        long mat_total_ways[][] = new long[N][sum+1];
        for(int i=0;i<N;i++){
            mat_total_ways[i][0] = 1;
        }
        System.out.println(findTotalWaysToReachSum(mat_total_ways,arr,N,sum));
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}