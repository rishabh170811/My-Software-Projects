import java.util.Arrays;
import java.util.Scanner;

public class GridChallenge {
	
	public static void sortRow(char arr[]){
		Arrays.sort(arr);
		return;
	}
	
	public static String areColumnsSorted(char grid[][],int N){
		
		for(int i = 0; i < N; i++){
			for(int j = 1; j < N; j++){
				if(grid[j][i]<grid[j-1][i]){
					return "NO";
				}
			}
		}
		return "YES";
	}
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i =0;i<T;i++){
			int N = sc.nextInt();
			char grid[][] = new char[N][N];
			for(int j = 0;j<N;j++){
					String eachGridElement = sc.next();
				for(int k=0;k<N;k++){
					grid[j][k] = eachGridElement.charAt(k);
				}
				sortRow(grid[j]);
			}
			System.out.println(areColumnsSorted(grid,N));
		}
	}
}
