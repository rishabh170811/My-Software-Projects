import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
//leaf number .. time
//A[1] = 0
//A[0] = -1
//leaf 0 has to be leaf 1 I guess
public class FrogTodd {
	public static int solution(int D, int[] A){
		
		int movable = D;
		int currLocOfMonkey = -1;
		int maxTime = 0;
		HashMap<Integer,ArrayList<Integer>> timeToStoneBirth = new HashMap<Integer,ArrayList<Integer>>();
		for(int i = 0;i<A.length;i++){
			if(A[i] == -1 )
				continue;
			if(timeToStoneBirth.containsKey(timeToStoneBirth)){
				timeToStoneBirth.get(A[i]).add(i);
				timeToStoneBirth.put(A[i], timeToStoneBirth.get(A[i]));
			}
			else{
				ArrayList<Integer> stoneNumber = new ArrayList<Integer>();
				stoneNumber.add(i);
				timeToStoneBirth.put(A[i],stoneNumber);
			}
			}
		Object[] keys = timeToStoneBirth.keySet().toArray();
		Arrays.sort(keys);
		for(Object key : keys){
			if(currLocOfMonkey>=A.length){
				return maxTime;
			}
			
			ArrayList<Integer> stonesIndex = timeToStoneBirth.get(key);
			Arrays.sort(stonesIndex.toArray());
			for(Integer eachstonesIndex : stonesIndex){
				int maxValLessThanD = -1;
				if(D>=A.length){
					return maxTime;
				}
				if(maxValLessThanD < eachstonesIndex && eachstonesIndex < D){
					maxValLessThanD = eachstonesIndex;
					currLocOfMonkey = maxValLessThanD;
					D = D + maxValLessThanD;
				}
			}
			maxTime = (int)key;
		}
 
		
		
		//		int maxTime = 0;
//		if(A.length == 1){
//			if(A[0] == 1 && X == 1)
//				return 0;
//			else
//				return -1;
//		}
//		HashMap<Integer,Integer>leafsCheck = new HashMap<Integer,Integer>();
//		for(int i=0;i<A.length;i++){
//			if(leafsCheck.size() == X)
//				return maxTime;
//			if(A[i] <= X){
//				if(!leafsCheck.containsKey(A[i])){
//					if(maxTime < i){
//						maxTime = i;
//					}
//					leafsCheck.put(A[i],1);
//					
//				}
//			}
	//}
		return -1;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int A[] = new int[6];
		for(int i = 0; i < 6; i++){
			A[i] = sc.nextInt();
		}
		System.out.print(solution(3,A));
	}
}
