import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FormOneSingleSortedArrayOutOfK {
	public static class SortedArrays implements Comparator<SortedArrays>{
	int position;
	int value;
	int arrayNumber;
	
	public SortedArrays(int position,int value,int arrayNumber){
		this.position = position;
		this.value = value;
		this.arrayNumber = arrayNumber;
	}
	
	public int compare(SortedArrays a, SortedArrays b){
		return a.value - b.value;
	}
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int k = Integer.parseInt(sc.nextLine());
		PriorityQueue<SortedArrays> minHeap = new PriorityQueue<>(k,new SortedArrays(0,0,0));
		HashMap<Integer,ArrayList<SortedArrays>> kSortedArrays = new HashMap<>();
		for(int i=0;i<k;i++){
			String eachNumber[] = sc.nextLine().split(" ");
			ArrayList<SortedArrays> eachSortedArray = new ArrayList<>();
			for(int j=0;j<eachNumber.length;j++){
				eachSortedArray.add(new SortedArrays(j,Integer.parseInt(eachNumber[j]),i));
			}
			kSortedArrays.put(i, eachSortedArray);
			minHeap.add(eachSortedArray.get(0));
		}
		while(!minHeap.isEmpty()){
			SortedArrays eachElement = minHeap.poll();
			System.out.print(eachElement.value + " ");
			if(eachElement.position+1 < kSortedArrays.get(eachElement.arrayNumber).size())
				minHeap.add(kSortedArrays.get(eachElement.arrayNumber).get(eachElement.position+1));
		}
	}
}
