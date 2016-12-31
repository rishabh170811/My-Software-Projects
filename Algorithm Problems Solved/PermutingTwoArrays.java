import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class PermutingTwoArrays {
	public static String checkGreaterThanKCondition(Long A[],Long B[],int k){
		for(int i=0;i<A.length;i++){
			if(A[i]+B[i] < k)
				return "NO";
		}
		return "YES";
	}
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0;i<T;i++){
			int size = sc.nextInt();
			int k = sc.nextInt();
			Long A[] = new Long[size];
			Long B[] = new Long[size];
			for(int j=0;j<size;j++)
				A[j] = sc.nextLong();
			for(int j=0;j<size;j++)
				B[j] = sc.nextLong();
			Arrays.sort(A);
			Arrays.sort(B,Collections.reverseOrder());
			System.out.println(checkGreaterThanKCondition(A,B,k));
		}
	}
}
