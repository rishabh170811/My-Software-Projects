import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class SumOfElementsInMatrix {
	public static class BundlePairs{
		int val1;
		int val2;
		public BundlePairs(int val1,int val2){
			this.val1 = val1;
			this.val2 = val2;
		}
	}
	public static ArrayList<BundlePairs> findPairOfElements(int matArray[][],int sum){
		ArrayList<BundlePairs> pairsOfSameSumElements = new ArrayList<>();
		HashMap<Integer,Integer> hashMapUniqueElements = new HashMap<>();
		for(int i = 0;i < matArray.length;i++){
			for(int j = 0;j < matArray[i].length;j++){
				if(!hashMapUniqueElements.containsKey(sum-matArray[i][j])){
					if(!hashMapUniqueElements.containsKey(matArray[i][j]))							
						hashMapUniqueElements.put(matArray[i][j], i);
					continue;
				}
				if(hashMapUniqueElements.get(sum-matArray[i][j]) != i){
					pairsOfSameSumElements.add(new BundlePairs(sum-matArray[i][j], matArray[i][j]));
				}
			}
		}
		return pairsOfSameSumElements;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int sum = sc.nextInt();
		int matArray[][] = {{1,2,3},{3,2,5},{2,4,7,8,9}};
		ArrayList<BundlePairs> final_result = findPairOfElements(matArray,sum);
		for(int i=0;i<final_result.size();i++){
			System.out.println(final_result.get(i).val1 + " " + final_result.get(i).val2);
		}
	}
}
