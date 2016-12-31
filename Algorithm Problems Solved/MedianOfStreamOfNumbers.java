import java.util.Scanner;
import java.util.Collections;
import java.util.PriorityQueue;
public class MedianOfStreamOfNumbers {
	public static boolean checkDifferenceOne(int a, int b){
		if( a - b == 1 || b - a == 1)
			return true;
		return false;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		PriorityQueue<Long> minHeap = new PriorityQueue<Long>();
		PriorityQueue<Long> maxHeap = new PriorityQueue<Long>(Collections.reverseOrder());
		long streamLimit = sc.nextLong();
		for(int i=0;i < streamLimit; i++){
			long incomingData = sc.nextLong();
			if(minHeap.size() == 0){
				minHeap.add(incomingData);
					System.out.println(minHeap.peek());
					continue;
			}
			if(incomingData < minHeap.peek()){
				maxHeap.add(incomingData);
			}
			else{
				minHeap.add(incomingData);
			}
			if(minHeap.size() == maxHeap.size()){
				System.out.println((minHeap.peek()+maxHeap.peek())/2+" ");
			}
			else if(checkDifferenceOne(minHeap.size(),maxHeap.size())){
				if(minHeap.size() > maxHeap.size())
					System.out.println(minHeap.peek());
				else
					System.out.println(maxHeap.peek());
			}
			else{
				if(minHeap.size() > maxHeap.size()){
					maxHeap.add(minHeap.poll());
				}
				else{
					minHeap.add(maxHeap.poll());
				}
				double result = (double)(minHeap.peek()+maxHeap.peek())/(double)2;
				System.out.println(result+" ");
			}
		}
	}
}
