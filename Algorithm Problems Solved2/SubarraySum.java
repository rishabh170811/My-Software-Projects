import java.util.Scanner;

class SubarraySum 
{
    public static int subArraySum(int arr[], int n, int sum) 
    {
        int sumTillNow, i, j;
        int max = 0;
        for (i = 0; i < n; i++) 
        {
            sumTillNow = arr[i];
            for (j = i + 1; j <= n; j++) 
            {
                if (sumTillNow >= sum) 
                {
                    int p = j - 1;
                }
                if (sumTillNow > sum || j == n)
                    break;
                sumTillNow = sumTillNow + arr[j];
                if(sumTillNow >max && sumTillNow <= sum){
                	max = sumTillNow;
                }
            }
        }
        return max;
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