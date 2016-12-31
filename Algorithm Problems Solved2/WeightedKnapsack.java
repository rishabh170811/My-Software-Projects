import java.util.Scanner;

public class WeightedKnapsack {
	public static int max(int x,int y){
		if(x>y)
			return x;
		else
			return y;
	}
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i=0;i<T;i++){
			int itemSize = sc.nextInt();
			int maxWeight = sc.nextInt();
			int itemValues[] = new int[itemSize];
			for(int j=0;j<itemSize;j++){
				itemValues[j] = sc.nextInt();
			}
			int maxWeightKnapsack[][] = new int[itemSize][maxWeight+1];
			
			for(int j=0;j<itemSize;j++){
				for(int k=1;k<=maxWeight;k++){
					if(j==0){
						if(k<itemValues[j])
							maxWeightKnapsack[j][k] = maxWeightKnapsack[j][k-1];
						else
							maxWeightKnapsack[j][k] = itemValues[j] + maxWeightKnapsack[j][k-itemValues[j]];
						continue;
					}
					if(k<itemValues[j]){
						maxWeightKnapsack[j][k] = maxWeightKnapsack[j-1][k];
					}
					else{
						maxWeightKnapsack[j][k] = max(maxWeightKnapsack[j][k-itemValues[j]] + itemValues[j],maxWeightKnapsack[j-1][k]);
					}
				}
			}
			System.out.println(maxWeightKnapsack[itemSize-1][maxWeight]);
		}
		
	}
}
