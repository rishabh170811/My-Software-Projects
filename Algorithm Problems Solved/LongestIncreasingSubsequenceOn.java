
public class LongestIncreasingSubsequenceOn {
	
	public static int getIndex(int element, int input[],int endIndex,int startIndex,int dataArr[]){
		int index = 0;
		if(dataArr[input[0]] > element){
			return 0;
		}
		if(endIndex - startIndex <= 0)
			return 0;
		int mid = (startIndex + endIndex)/2;
		if(mid == 0)
			mid = 1;
		if(element > dataArr[input[mid-1]] && dataArr[input[mid]]>element){
			return mid;
		}
		if(element > dataArr[input[mid]])
			getIndex(element,input,endIndex,mid+1,dataArr);
		else
			getIndex(element,input,mid-1,startIndex,dataArr);
		return index;
	}
	// O(N Log(N))
	public static int getLCSFlash(int input[]){
		int allPossibleLCS[] = new int[input.length];
		allPossibleLCS[0] = 0;
		int mostRI = 0;
		for(int i=1;i<input.length;i++){
			if(input[allPossibleLCS[mostRI]] < input[i]){
				allPossibleLCS[++mostRI] = i;
			}
			else{
				 allPossibleLCS[getIndex(input[i],allPossibleLCS,mostRI,0,input)] = i;
			}
		}
		return mostRI+1;
	}
	
	//O(N^2 method)
	public static void main(String args[]){
		// 1 2 4 6 8 10 30 50 60
		int arrLongest[] = {1,4,2,4,6,4,2,8,5,10,2,4,30,50,60};
		int maxIncLength = 1;
		int arrLIS[] = new int[arrLongest.length];
		arrLIS[0] = 1;
		for(int i=1;i < arrLongest.length;i++){
			int localmaxLength = 0;
			for(int j=0;j<i;j++){
				
				if(arrLongest[j]< arrLongest[i]){
					if(localmaxLength < arrLIS[j] + 1){
						if(maxIncLength < arrLIS[j]+1){
							maxIncLength = arrLIS[j] + 1;
					}
					arrLIS[i] = arrLIS[j] + 1;
					localmaxLength = arrLIS[i];
					}
				}
			}
		}
		System.out.println(maxIncLength);
		System.out.println(getLCSFlash(arrLongest));
	}
	
}
