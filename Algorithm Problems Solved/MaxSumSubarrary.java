import java.util.Scanner;

class MaxSumSubarrary
{
    public static int subArraySum(int arr[], int n, int sum) 
    {
    	
        int curr_sum = arr[0], start = 0, i;
        int max_sum = 0;
        for (i = 1; i <= n; i++) 
        {
            while (curr_sum > sum && start < i-1)
            {
                curr_sum = curr_sum - arr[start];
                start++;
                if(max_sum < curr_sum && sum >curr_sum)
                	max_sum = curr_sum;                
            }
             
            if (curr_sum == sum) 
            {
                return curr_sum;
            }
             
            if (i < n){
            curr_sum = curr_sum + arr[i];
            if(max_sum < curr_sum && sum >curr_sum)
            	max_sum = curr_sum;
            }
        }
 
        return max_sum;
    }
 
    public static void main(String[] args) 
    {
    	Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int T = sc.nextInt();
        int A[] = new int[N];
        for(int i = 0;i<N;i++){
        	A[i] = sc.nextInt();
        }
        System.out.println(subArraySum(A,N,T));
    }
}
 