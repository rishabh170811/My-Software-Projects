import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BinaryMultiple {
	public static boolean mod(String binaryNum, int num){
		int remainder = 0;
		//23434
		int counter = 0;
		if(binaryNum.equals("10"))
			System.out.println("am here");
		for(int i = 0 ; i < binaryNum.length() ; i++){
			System.out.println(binaryNum.charAt(binaryNum.length()-1-i));
			System.out.println(binaryNum.charAt(binaryNum.length()-1-i) -'0');
			remainder += Math.pow(10,counter)* Integer.parseInt(String.valueOf(binaryNum.charAt(binaryNum.length()-1-i)));
			remainder = remainder % num;
			counter++;
		}
		if(remainder == 0)
			return true;
		return false;
	}
    static String Zero_One(int num) {
        if(num == 0)
            return "0";
        String zero_one = "1";
        Queue<String> binaries = new LinkedList<String>();
        binaries.add(zero_one);
        while(!binaries.isEmpty()){
           String binary_test = binaries.poll();
           if(Long.parseLong(binary_test) > num && mod(binary_test,num))
               return binary_test;
           binaries.add(binary_test+"0");
           binaries.add(binary_test+"1");
        }
        return null;
    }
    public static void main(String args[]){
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	System.out.println(Zero_One(N));
    }


}
