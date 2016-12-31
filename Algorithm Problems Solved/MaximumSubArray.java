import java.util.*;

public class MaximumSubArray {
    
    public static int sumContiguousArray(int sum_array[]){
        int max_cont_sum = Integer.MIN_VALUE; 
        int max_returned_sum = Integer.MIN_VALUE;
        for(int i=0;i<sum_array.length;i++){
            if((max_cont_sum+sum_array[i])>sum_array[i]){
                if(max_cont_sum != Integer.MIN_VALUE){
                	max_cont_sum += sum_array[i];
                }
                else
                	max_cont_sum = sum_array[i];
            }
            else{
                max_cont_sum = sum_array[i];
            }
            if(max_returned_sum < max_cont_sum){
            	max_returned_sum = max_cont_sum;
            }
            
        }
        return max_returned_sum;
    }
    
    public static int sumNonContiguousArray(int sum_array[]){
        int max_count_sum = Integer.MIN_VALUE;
        for(int i =0;i<sum_array.length;i++){
            if(sum_array[i]>0){
            	if(max_count_sum ==Integer.MIN_VALUE)
            		max_count_sum = sum_array[i];
            	else
            		max_count_sum += sum_array[i];
            }
        }
        if(max_count_sum == Integer.MIN_VALUE)
            return max(sum_array);
        return max_count_sum;
    }
    
    public static int max(int sum_array[]){
        int max_element = Integer.MIN_VALUE;
        for(int i=0;i<sum_array.length;i++){
            if(sum_array[i]>max_element)
                max_element = sum_array[i];
        }
        return max_element;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i=0;i<T;i++){       
            int arrayLength = sc.nextInt();
            int sum_array[] = new int[arrayLength]; 
            for(int j=0;j<arrayLength;j++){
                sum_array[j] = sc.nextInt();
            }
      System.out.println(sumContiguousArray(sum_array)+" "+sumNonContiguousArray(sum_array));
        }
    }
}