import java.util.HashMap;

//
public class MaxLengthSubarrayWithEqualNumberOf0sAnd1s {
	public static int getMaxLengthSubarrayWithEqualNumberOf0sAnd1s(int input[]){
		if(input.length == 0 || input.length == 1)
			return 0;
		for(int i=0;i<input.length;i++){
			if(input[i] == 0)
				input[i] = -1;
		}
		int maxSize = 0;
		int subLeft[] = new int[input.length];
		subLeft[0] = input[0];
		
		for(int i=1;i<input.length;i++){
			subLeft[i] = subLeft[i-1] + input[i];
			if(maxSize < i+1 && subLeft[i] == 0)
				maxSize = i+1;
		}
		
		
		HashMap<Integer,Integer> indicesWithSameSum = new HashMap<>(); 

		for(int i = 0; i < subLeft.length;i++){
			if(indicesWithSameSum.containsKey(subLeft[i])){
				if((i-indicesWithSameSum.get(subLeft[i])) > maxSize){
					maxSize = i-indicesWithSameSum.get(subLeft[i]);
					continue;
				}
			}
			indicesWithSameSum.put(subLeft[i],i);
		}
		return maxSize;
	}
	
	public static void main(String args[]){
		int input[] = {1,1,1,1,0,1,1,0,0,1,1,0,0,1,1};
		System.out.println(getMaxLengthSubarrayWithEqualNumberOf0sAnd1s(input));
	}
}
