import java.util.Scanner;

public class LargestNumber {
	public static int largestNumber(int X){

		int arrayOfAvgSums[] = new int[Integer.toString(X).length()-1];
		String AvgSum2Nos = Integer.toString(X);
		if(AvgSum2Nos.length() == 1){
			return X;
		}
		for(int i=0;i<AvgSum2Nos.length()-1;i++){
			arrayOfAvgSums[i]= (int) Math.ceil((Double.parseDouble(AvgSum2Nos.substring(i, i+1)) + Double.parseDouble(AvgSum2Nos.substring(i+1,i+2)))/2);
		}
		int maxVal = Integer.MIN_VALUE;
		for(int i = 0;i<arrayOfAvgSums.length;i++){
			if(i==0){
				String evalString = arrayOfAvgSums[0]+AvgSum2Nos.substring(2);
				if(Integer.parseInt(evalString)>maxVal){
					maxVal = Integer.parseInt(evalString);
				}
				continue;
			}
			String Y = AvgSum2Nos.substring(0,i) + arrayOfAvgSums[i] + AvgSum2Nos.substring(i+2);
			if(Integer.parseInt(Y)> maxVal){
				maxVal = Integer.parseInt(Y);
			}
		}
		return maxVal;
	}
public static void main(String args[]){
	@SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
	int X = sc.nextInt();
	System.out.print(largestNumber(X));
	
}
}
