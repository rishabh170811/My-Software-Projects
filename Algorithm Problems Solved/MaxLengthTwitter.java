import java.util.Scanner;

public class MaxLengthTwitter {
	static int maxLength(int[] a, int k) {
	    int maxlen = 0;
	    int len = 0;
	    if(a.length == 0)
	        return 0;
	    int sum = a[0];
	    if(a[0] <= k){
	        maxlen = 1;
	        len = 1;
	    }
	    int trail_a = 0;
	    for(int i = 1; i < a.length; i++){
	        if(sum+a[i] <= k){
	            len = len + 1;
	            sum = sum + a[i];
	            if(maxlen < len)
	                maxlen = len;
	        }
	        else{
	            if(maxlen < len){
	                maxlen = len;
	            }
	            sum = sum + a[i];
	            len = len + 1;
	            while(sum > k){
	                sum = sum - a[trail_a];
	                trail_a = trail_a + 1;
	                len = len - 1;
	            }
	                
	        }
	    }
	    return maxlen;
	}

	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int a[] = new int[N];
		for(int i = 0; i < N ; i++){
			a[i] = sc.nextInt();
		}
		int k = sc.nextInt();
		System.out.println(maxLength(a,k));
	}
}

