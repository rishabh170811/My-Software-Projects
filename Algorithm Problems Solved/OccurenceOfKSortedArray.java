import java.util.Scanner;

public class OccurenceOfKSortedArray {
	public static int findLeftNumberOfKInSortedArr(int sortedArr[],int K,int i,int j){
		if(j==i){
			if(sortedArr[j]  == K)
				return j;
			return -1;
		}
			if(sortedArr[(j+i)/2] == K && sortedArr[(j+i)/2-1] == K){
				return findLeftNumberOfKInSortedArr(sortedArr, K, i, (j+i) / 2);
		}
		else if(sortedArr[(j+i)/2] == K)
			return (j+i)/2; 
		if(sortedArr[(j+i)/2] > K){
			return findLeftNumberOfKInSortedArr(sortedArr, K, i, (j+i)/2);
		}
		else if(sortedArr[(j+i)/2] < K){
			return findLeftNumberOfKInSortedArr(sortedArr, K, (j+i)/2+1, j);			
		}
		else{
			return -1;
		}
	}
	
	public static int findRightNumberOfKInSortedArr(int sortedArr[],int K,int i,int j){
		if(j == i){
			if(sortedArr[j] == K)
				return j;
			return -1;
		}
		if(sortedArr[(j+i)/2] == K && sortedArr[(j+i)/2+1] == K){
			return findRightNumberOfKInSortedArr(sortedArr, K, (j+i)/2+1, j);
		}
		else if(sortedArr[(j+i)/2] == K)
			return (j+i)/2; 
		if(sortedArr[(j+i)/2] > K){
			return findRightNumberOfKInSortedArr(sortedArr, K, i,(j+i)/2);
		}
		else if(sortedArr[(j-i)/2] < K){
			return findRightNumberOfKInSortedArr(sortedArr, K, (j+i)/2+1, j);			
		}
		else{
			return -1;
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int K = sc.nextInt();
		int sortedArr[] = {1,3,5,5,5,5,5,5,5,5,5,5,5,5,8,15,16};
		System.out.println(findRightNumberOfKInSortedArr(sortedArr,K,0,sortedArr.length)-findLeftNumberOfKInSortedArr(sortedArr,K,0,sortedArr.length)+1);
	}
}
