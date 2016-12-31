import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class SumTriplets {
	public static void display(HashMap<Integer,ArrayList<Integer>> finalTripletResult){
		for (Integer each : finalTripletResult.keySet()){
			System.out.println(finalTripletResult.get(each));
		}
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int sum = sc.nextInt();
		int arrTriplet[] = new int[N];
		for(int i = 0;i < N;i++){
			arrTriplet[i] = sc.nextInt();
		}
		HashMap<Integer,ArrayList<Integer>> finalTripletResult = new HashMap<>();
		finalTripletResult = findAllTriplets(arrTriplet,sum);
		display(finalTripletResult);
	}

	public static ArrayList<Integer> addNewTriplets(int x,int y,int z){
		ArrayList<Integer> temp = new ArrayList<>();
		temp.add(x);
		temp.add(y);
		temp.add(z);
		return temp; 
	}
	public static HashMap<Integer,ArrayList<Integer>> findAllTriplets(int arrTriplet[],int sum){
		Arrays.sort(arrTriplet);
		HashMap<Integer,ArrayList<Integer>> tripletResults = new HashMap<>();
		int counter = -1;
		for(int i = 0;i < arrTriplet.length - 2;i++){
			int tempTarget = sum - arrTriplet[i];
			int pointer1 = i+1;
			int pointer2 = arrTriplet.length-1;
			while(pointer1 < pointer2){
				if(arrTriplet[pointer1]+arrTriplet[pointer2] == tempTarget){
					tripletResults.put(++counter, addNewTriplets(arrTriplet[pointer1], arrTriplet[pointer2],arrTriplet[i]));
					pointer1++;
					pointer2--;
				}
				else if(arrTriplet[pointer1]+arrTriplet[pointer2] < tempTarget){
					pointer1++;
				}
				else{
					pointer2--;
				}
			
		}
		
	}
		return tripletResults;
}
}
