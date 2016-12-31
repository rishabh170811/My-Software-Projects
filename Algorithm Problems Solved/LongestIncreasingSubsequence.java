import java.util.Scanner;

public class LongestIncreasingSubsequence {
public static void main(String args[]){
	Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int arr[] = new int[N];
    for(int i = 0;i < N;i++){
        arr[i] = sc.nextInt();
    }
    int opt[] = new int[N];
    opt[0] = 1;
    if(N == 1){
        System.out.println(opt[0]);
    }
    for(int i = 1 ; i < N ; i++){
        int maxVal = 0;
        for(int k=0 ; k < i ; k++){
            if(arr[k] < arr[i]){
            	//never considered what happens if no value is lesser than arr[k]
                if(maxVal < opt[k])
                    maxVal = opt[k]; 
            }
        }
        
        opt[i] = maxVal + 1;
    }
    int final_optimal_solution = 0;
    for(int i =0 ; i < N ; i++){
    	if(final_optimal_solution < opt[i]){
    		final_optimal_solution = opt[i];
    	}
    }
    System.out.println(final_optimal_solution);
}
}
