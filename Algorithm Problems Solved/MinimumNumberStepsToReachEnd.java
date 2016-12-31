import java.util.Scanner;

public class MinimumNumberStepsToReachEnd {
	/* x1,x2,x3 .... xn
		for all xi from 1 to n
			for all k < i that can potentially reach xi in 1 hop ( k -> i)
				min curr(i),min(k)  +1
			endFor
		endFor
	*/
	public static int getMinimumHopsToReachEnd(int pathway[],int minDist[]){
		if(pathway.length == 0)
			return 0;
		minDist[0] = 0;
		for(int i=1 ; i < pathway.length;i++){
			minDist[i] = Integer.MAX_VALUE;
			for(int k=0;k < i; k++){
				if(pathway[k]+k >= i){
					minDist[i] = min(minDist[i],minDist[k]+1);
				}
			}
		}
		return minDist[pathway.length - 1];
	}
	
	public static int min(int a,int b){
		if(a>b)
			return b;
		return a;
	}
	public static void main(String args[]){
		int pathway[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
		int minDist[] = new int[pathway.length];
		System.out.println(getMinimumHopsToReachEnd(pathway,minDist));
	}
}
