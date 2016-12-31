import java.util.ArrayList;
import java.util.HashSet;

public class FindingTwoNumbers {
	
	public static void findTwoNumbers(int arr[]){
		int XOR = arr[0];
		for(int i=1;i<arr.length;i++){
			XOR = arr[i] ^ XOR;
		}
		String checkOne = Integer.toBinaryString(XOR);
		int index = 0;
		for(int i=0;i < checkOne.length();i++){
			if(checkOne.charAt(i) == '1'){
				index=i;
				break;
			}
		}
		ArrayList<Integer>set1 = new ArrayList<Integer>();
		ArrayList<Integer>set2 = new ArrayList<Integer>();
		for(int i=0;i<arr.length;i++){
			if(Integer.toBinaryString(arr[i]).length() < index+1){
				set2.add(arr[i]);
				continue;
			}
			if(Integer.toBinaryString(arr[i]).charAt(Integer.toBinaryString(arr[i]).length() - index - 1) == '1'){
				set1.add(arr[i]);
				continue;
			}
			set2.add(arr[i]);
		}
		XOR=0;
		for(Integer each : set1){
			if(XOR==0){
				XOR = each;
				continue;
			}
			XOR = each ^ XOR;
		}
		System.out.println(XOR);
		XOR=0;
		for(Integer each : set2){
			if(XOR==0){
				XOR = each;
				continue;
			}
			XOR = each ^ XOR;
		}
		System.out.println(XOR);
	}
	// 0100 0111  0011
	public static void main(String args[]){
		int arr[] = {1,2,1,2,4,7};
		findTwoNumbers(arr);
	}
}
