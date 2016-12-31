import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

//you can do it !
// code to find nearest larger element in the array
public class NearestGreaterElement {
	public static class BundleElements{
		int value;
		int nextGreaterValue;
		public BundleElements(int value,int nextGreaterValue){
			this.value = value;
			this.nextGreaterValue = nextGreaterValue;
		}
	}
	public  static ArrayList<BundleElements> findAllNearestGreaterElements(int allElements[]){
		Stack<Integer> elements = new Stack<Integer>();
		ArrayList<BundleElements> bundleElements = new ArrayList<BundleElements>();
		if(allElements.length == 0)
			return null;
		elements.push(allElements[0]);
		for(int i = 1; i < allElements.length;i++){
			int all_current = allElements[i]; 
			while(!elements.isEmpty()){
				if(all_current <= elements.peek()){
					break;
				}
				int valueToPair = elements.pop();
				bundleElements.add(new BundleElements(valueToPair,all_current));
			}
			elements.push(all_current);
		}
		return  bundleElements;
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int allElements[] = {4,2,3,6,8};
		
		ArrayList<BundleElements> bundleElements= findAllNearestGreaterElements(allElements);
		for(int i=0;i< bundleElements.size();i++)
			System.out.println(bundleElements.get(i).value +" " + bundleElements.get(i).nextGreaterValue);
	}
}
