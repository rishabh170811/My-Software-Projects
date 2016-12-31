
public class SquareNumberRange {
	static int[] getMinimumUniqueSum(String[] arr) {
        int countEachRange[] = new int[arr.length];
        for(int i =0;i<arr.length;i++){
            String lowHigh[] = arr[i].split(" ");
            int low = Integer.parseInt(lowHigh[0]);
            int high = Integer.parseInt(lowHigh[1]);
            int local_Count = 0;
            for(int j = (int)Math.sqrt(low); j <= (int)Math.sqrt(high);j++){
                if(Math.pow(j,2) <= high && Math.pow(j,2) >=low)
                    local_Count++;
            }
            countEachRange[i] = local_Count;
            }
        return countEachRange;    
    }


}
