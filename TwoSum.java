import java.util.HashMap;
import java.util.Scanner;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> targetDiff = new HashMap<Integer,Integer>();
        int answer[] = new int[2];
        for(int i = 0; i < nums.length; i++){
            if(targetDiff.containsKey(target - nums[i])){
                answer[0] = targetDiff.get(target - nums[i]);
                answer[1] = i;
                System.out.println(answer[0]+ " "+answer[1]);
                return answer;
            }
            targetDiff.put(nums[i],i);
        }
        return null;
    }
    public static void main(String args[]){
    	@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	int arrSum[] = new int[N];
    	for(int i = 0;i<arrSum.length;i++){
    		arrSum[i] = sc.nextInt();
    	}
    	twoSum(arrSum,9);
    }
}
