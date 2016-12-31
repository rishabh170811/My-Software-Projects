
public class OrderedArrayOneSwap {

	    public static int findIndexSmallestElementGreaterThanX(int[] A,int X){
	        int smallGreater = -1;
	        for(int i=0;i<A.length;i++){
	            if(X <= A[i])
	                continue;
	            if(smallGreater == -1){
	                smallGreater = i;
	                continue;
	            }
	            if(A[smallGreater] >= A[i])
	                smallGreater = i;
	        }
	        return smallGreater;
	    }
	    public static boolean solution(int[] A) {
	        if(A.length == 0 || A.length == 1)
	            return true;
	        int swapElement;
	        int numberSwaps = 0;
	        for(int i=1;i < A.length;i++){
	            if(A[i-1] <= A[i])
	                continue;
	            numberSwaps++;
	            if(numberSwaps>1)
	                return false;
	            int swap = findIndexSmallestElementGreaterThanX(A,A[i-1]);
	            swapElement = A[swap];
	            A[swap] = A[i];
	            A[i] = swapElement;
	            i = A.length - 1;
	        }
	        return true;
	    }
	    public static void main(String args[]){
	    	int a[] = {1,2,4,6,7,10,8};
	    	System.out.println(solution(a));
	    }
	}

