import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MaximunMinimumArr {
    private static Scanner sc;
	public static HashMap<Integer,Integer> buildMinArr(int arr[],HashMap<Integer,Integer>minarr){
    	for(int i=1;i<arr.length;i++){
    		if(minarr.get(i-1) >arr[i]){
    			minarr.put(i,arr[i]);
    		}
    		else{
    			minarr.put(i,minarr.get(i-1));
    		}
    }
    	return minarr;
    }
    
    public static HashMap<Integer,Integer> buildMaxArr(int arr[],HashMap<Integer,Integer>maxarr){
    	for(int i= arr.length-2;i>=0;i--){
    		if(maxarr.get(i+1) < arr[i]){
    			maxarr.put(i,arr[i]);
    		}
    		else{
    			maxarr.put(i,maxarr.get(i+1));
    		}
    }
    	return maxarr;
    }
    
    public static int checkMaxMinIndex(int arr[],HashMap<Integer,Integer> minarr,HashMap<Integer,Integer>maxarr){
    	for(int i=1;i<arr.length-1;i++){
    		if(arr[i]>minarr.get(i-1) && arr[i]<maxarr.get(i+1)){
    			return 1;
    		}
    	}
    	return -1;
    }
    //Controller
	public static int findMaxMinIndex(int arr[]){
	HashMap<Integer,Integer> minarr = new HashMap<Integer,Integer>();
	HashMap<Integer,Integer> maxarr = new HashMap<Integer,Integer>();
	if(arr.length <= 2){
		return 1;
	}
	else{
		minarr.put(0, arr[0]);
	    maxarr.put(arr.length-1,arr[arr.length-1]);
	    buildMinArr(arr,minarr);
	    buildMaxArr(arr,maxarr);
	}
	   
	return checkMaxMinIndex(arr,minarr,maxarr);
	}
	public static void main(String args[]){
		sc = new Scanner(System.in);
		int arr[] = new int[10];
		for(int i=0;i<10;i++){
			arr[i] = sc.nextInt();
		}
		System.out.print(findMaxMinIndex(arr));
}
}
