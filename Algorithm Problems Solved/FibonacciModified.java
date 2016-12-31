import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// 0 1 1 2 5
public class FibonacciModified {
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        String firstNum = sc.nextLine();
        String arr[] = firstNum.split(" ");
        BigInteger first = new BigInteger(arr[0]);
        BigInteger second = new BigInteger(arr[1]);        
        String N = arr[2];
        BigInteger final_sum = new BigInteger("3");
        for(int i=2;i<Integer.parseInt(N);i++){
        	final_sum = second.multiply(second);
        	final_sum = final_sum.add(first);
        	first = second;
        	second = final_sum;
        }
        System.out.println(final_sum);
    }
}