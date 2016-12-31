import java.util.HashMap;

public class NumberOfIdenticalPairs {
	 public static int solution(int[] A) {
	        // write your code in Java SE 8
	        HashMap<Integer,Integer> occurCount = new HashMap<Integer,Integer>();
	        int numberOfPairs = 0;
	        for(int i=0;i<A.length;i++){
	            if(occurCount.containsKey(A[i])){
	                numberOfPairs += occurCount.get(A[i]);
	                occurCount.put(A[i],occurCount.get(A[i])+1);
	                continue;
	            }
	            occurCount.put(A[i],1);            
	        }
	        return numberOfPairs;
	    }
	 public static void main(String args[]){
		 int a[] = { 1,5,3,3,3,5,7};
		 System.out.println(solution(a));
	 }
}
