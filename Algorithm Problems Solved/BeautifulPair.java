import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class BeautifulPair {
	public static int countSamePair(int A[],int B[]){
		int pointer1 = 0,pointer2=0;
		int finalBeautifulPair = 0;
		int no_of_disjointA = 0;
		int no_of_disjointB = 0;
		if(A.length == 1 && B.length == 1){
			return 0;
		}		
		while(pointer1 != A.length && pointer2 != B.length){
			if(A[pointer1] == B[pointer2]){
				finalBeautifulPair += 1;
				pointer1++;
				pointer2++;
			}
			else{
				if(A[pointer1] >B[pointer2]){
					pointer2++;
					no_of_disjointB++;
				}
				else{
					pointer1++;
					no_of_disjointA++;
				}
			}
		}
		no_of_disjointA += A.length - pointer1;
		no_of_disjointB += B.length - pointer2;
		if(no_of_disjointA>0 && no_of_disjointB>0)
			finalBeautifulPair += 1;
		if(no_of_disjointA == 0 && no_of_disjointB == 0)
			finalBeautifulPair -= 1;
		return finalBeautifulPair;
	}
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int size = sc.nextInt();
		int A[] = new int[size];
		int B[] = new int[size];
		for(int i=0;i<size;i++){
			A[i] = sc.nextInt();
		}
		for(int i=0;i<size;i++){
			B[i] = sc.nextInt();
		}
		Arrays.sort(A);
		Arrays.sort(B);
		System.out.print(countSamePair(A,B));
		
	}
}
