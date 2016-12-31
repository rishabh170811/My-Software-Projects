import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class MinimumStepsToSortArray {
	public static class SortedSwap implements Comparator<SortedSwap>{
		int value;
		int index;
		int isVisited = 0;
		public SortedSwap(int value, int index, int isVisited){
			this.value = value;
			this.index = index;
			this.isVisited = isVisited;
		}
		@Override
		public int compare(SortedSwap o1, SortedSwap o2) {
			return o1.value - o2.value;
		}
	}
	//Lot's of refactoring introduced ..improve 
	public static int findMinSwapsToSortArray(ArrayList<SortedSwap> minSwapFinderEngine,int unsortedArray[],HashMap<Integer,Integer>minSwapEngine){
		//sort array ..create graph depicted expected positions of elements
		int cycleSize = 0;
		int minSwaps = 0;
		for(int i=0;i < unsortedArray.length;i++){
			if(minSwapFinderEngine.get(i).isVisited == -1 || minSwapFinderEngine.get(i).value == unsortedArray[i])
				continue;
			int counter = minSwapEngine.get(unsortedArray[i]);
			cycleSize = 1;
			minSwapFinderEngine.get(i).isVisited = -1;
			// map arrayList elements to positions in the array !!! nup !!!!!
			while(minSwapFinderEngine.get(counter).isVisited != -1){
				minSwapFinderEngine.get(counter).isVisited = -1;
				counter = minSwapEngine.get(unsortedArray[counter]);
				cycleSize += 1;
			}
			minSwaps += cycleSize - 1;
			cycleSize = 0;
		}
		return minSwaps;
	}
	public static ArrayList<SortedSwap> buildArrayList(int unsortedArr[]){
		ArrayList<SortedSwap> minSwapEngine = new ArrayList<SortedSwap>();
		for(int i = 0;i <unsortedArr.length;i++){
			minSwapEngine.add(new SortedSwap(unsortedArr[i], i, 0));
		}
		return minSwapEngine;
	}
	
	public static void buildHashMap(HashMap<Integer,Integer> mapSwapEngine,ArrayList<SortedSwap> minSwapFinderEngine){
		int counter = 0;
		for(SortedSwap minSwap : minSwapFinderEngine){
			mapSwapEngine.put(minSwap.value,counter);
			counter++;
		}
	}
	
	public static void main(String args[]){
	int unSortedArr[] = {5,4,2,3,6}; // 2 3 4 5 6
	ArrayList<SortedSwap> minSwapFinderEngine = buildArrayList(unSortedArr);
	HashMap<Integer,Integer> mapSwapEngine = new HashMap<>();
	
	Collections.sort(minSwapFinderEngine, new SortedSwap(-1,-1, -1));
	buildHashMap(mapSwapEngine,minSwapFinderEngine);
	System.out.println(findMinSwapsToSortArray(minSwapFinderEngine,unSortedArr,mapSwapEngine));
	}
}
