import java.util.PriorityQueue;
import java.util.Scanner;

public class KthLargestElement {
	
	public static int getKthLargestElement(int unsortedArray[],int k){
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();
		for(int i=0;i<unsortedArray.length;i++){
			if(minHeap.size() == k && minHeap.peek() < unsortedArray[i]){
				minHeap.remove();
			}
			if(minHeap.size() < k)
				minHeap.add(unsortedArray[i]);
		}
		return minHeap.peek();
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int unsortedArray[] = {2,32,52,244,2552,5255,25522,6466,96866};
		System.out.print(getKthLargestElement(unsortedArray,3));
	}
}
