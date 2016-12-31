import java.util.Scanner;

public class NikitaAndTheGame {
	static int prefix_sum[];
	public static int max_sum = 0;
	public static int checkBestPartitionHops(int partionNikita[],int i,int j){
		int temp = 0;
		if(j-i <= 0)
			return 0;
		if(j-i==1){
			if(partionNikita[i] == partionNikita[j]){
				return 1;
			}
		}
		else{
			
			for(int k = i+1; k <= j; k++){
				
				if(i==0){
				if(prefix_sum[k-1] == prefix_sum[j] - prefix_sum[k-1]){
					temp = max(checkBestPartitionHops(partionNikita, i, k-1),checkBestPartitionHops(partionNikita, k, j))+1;
					if(max_sum < temp)
						max_sum = temp;
						System.out.println(max_sum);
					return temp;
				}
				}
				else if(prefix_sum[k-1]-prefix_sum[i-1] == prefix_sum[j] - prefix_sum[k-1]){
					temp = max(checkBestPartitionHops(partionNikita, i, k-1),checkBestPartitionHops(partionNikita, k, j))+1;					
					if(max_sum < temp)
						max_sum = temp;
						System.out.println(max_sum);
					return temp;
				}
				
				}
		}
		return temp;
	}
	private static int max(int checkBestPartitionHops, int checkBestPartitionHops2) {
		// TODO Auto-generated method stub
		if(checkBestPartitionHops > checkBestPartitionHops2)
			return checkBestPartitionHops;
		else
			return checkBestPartitionHops2;
	}
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0;i<T;i++){
			int N = sc.nextInt();
			int partitionGame[] = new int[N];
			prefix_sum = new int[N];
			for(int j=0;j<N;j++){
				partitionGame[j] = sc.nextInt();
			}
			prefix_sum[0] = partitionGame[0];
			for(int j=1;j<N;j++){
				prefix_sum[j] = prefix_sum[j-1] + partitionGame[j];
			}
			checkBestPartitionHops(partitionGame,0,partitionGame.length-1);	
			System.out.println(max_sum);
			max_sum = 0;
		}
	}
}
