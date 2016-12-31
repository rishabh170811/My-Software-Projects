import java.util.Scanner;

public class MaximumSumMatrixRotation {
	public static int max(int a,int b,int c,int d){
		if(a > b && a > c && a > d)
			return a;
		if(b > c && b > d)
			return b;
		if(d > c)
			return d;
		return c;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0;i<T;i++){
			int n = sc.nextInt();
			int matRot[][] = new int[2*n][2*n];
			for(int j=0;j<2*n;j++){
				for(int k=0;k<2*n;k++){
					matRot[j][k] = sc.nextInt();
				}
			}
			int maxSum = 0;
			for(int j=0;j<n;j++){
				for(int k=0;k<n;k++){
					maxSum += max(matRot[j][k],matRot[j][2*n-1-k],matRot[2*n-1-j][k],matRot[2*n-1-j][2*n-1-k]);
				}
			}
			System.out.println(maxSum);
		}
	}
}
