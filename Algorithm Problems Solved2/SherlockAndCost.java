import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class SherlockAndCost {
	public static class SumComputer{
		int bestSum;
		public SumComputer(int bestSum){
			this.bestSum = bestSum;
		}
	}
	public static int mod(int j,int k){
		if(j > k)
			return j-k;
		return k-j;
					
	}
	public static int max(int a,int b){
		if(a>b)
			return a;
		return b;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int T = sc. nextInt();
		for(int l=0;l < T;l++){
			int finalMaxSum = 0;
			int size = sc.nextInt();
			int B[] = new int[size];
			for(int i=0;i<size;i++){
				B[i] = sc.nextInt();
			}
			HashMap<Integer,HashMap<Integer,SumComputer>> maxSum = new HashMap<>();
			for(int i=0;i<size;i++){
				HashMap<Integer,SumComputer> eachMaxSum = new HashMap<>();
				maxSum.put(i, eachMaxSum);
			}
			for(int i=0;i<size;i++){
					if(i==0){
						maxSum.get(i).put(1,new SumComputer(0));
						maxSum.get(i).put(B[i], new SumComputer(0));
					}
					else{
						maxSum.get(i).put(B[i],new SumComputer(max(maxSum.get(i-1).get(B[i-1]).bestSum + mod(B[i-1],B[i]),maxSum.get(i-1).get(1).bestSum + mod(B[i],1))));
						maxSum.get(i).put(1, new SumComputer(maxSum.get(i-1).get(B[i-1]).bestSum + mod(1,B[i-1])));
					}
					if(size-1 == i){
						finalMaxSum = max(maxSum.get(i).get(1).bestSum,maxSum.get(i).get(B[i]).bestSum);
					}
			}
			System.out.println(finalMaxSum);
		}
	}
}
