import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MergeKSortedArrays {
	public static class ArraysMerger{
		int val;
		int indexOfArr;
		int indexInArr;
		public ArraysMerger(int val, int indexOfArr,int indexInArr) {
			this.val = val;
			this.indexOfArr = indexOfArr;
			this.indexInArr = indexInArr;
		}
	}	
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int k = Integer.parseInt(sc.nextLine());
		ArrayList<ArrayList<ArraysMerger>> kSortedArrays = new ArrayList<>();
		for(int i = 0 ; i < k ; i++){
			String eachSortedArray[] = sc.nextLine().split(" ");
			for(int j = 0; j < eachSortedArray.length; j++ ){
				if(j==0){
					kSortedArrays.add(new ArrayList<>());
					kSortedArrays.get(i).add(new ArraysMerger(Integer.parseInt(eachSortedArray[j]),i,j));
					continue;
				}
				kSortedArrays.get(i).add(new ArraysMerger(Integer.parseInt(eachSortedArray[j]),i,j));
			}
		}
		Comparator<ArraysMerger> comparator = new Comparator<ArraysMerger>() {

			@Override
			public int compare(ArraysMerger o1, ArraysMerger o2) {
				// TODO Auto-generated method stub
				return o1.val - o2.val;
			}
		};
		PriorityQueue<ArraysMerger> minHeap = new PriorityQueue<>(k,comparator);
		for(int i = 0; i < k ; i++){
			minHeap.add(kSortedArrays.get(i).get(0));
		}
		ArrayList<Integer>finalMergedSortedList = new ArrayList<>();
		while(!minHeap.isEmpty()){
			ArraysMerger temp  = minHeap.poll();
			finalMergedSortedList.add(temp.val);
			if(kSortedArrays.get(temp.indexOfArr).size() == temp.indexInArr+1){
				continue;
			}
			minHeap.add(kSortedArrays.get(temp.indexOfArr).get(temp.indexInArr+1));			
		}
		System.out.println(finalMergedSortedList);
	}
}
