import java.util.Scanner;
import java.util.Stack;

public class LongestValidParentheses {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String parentheses = sc.next();
		Stack<String> vp = new Stack<>();
		int maxLength = 0;
		int count = 0;
		for(int i=0;i<parentheses.length();i++){
			if(parentheses.charAt(i) == ')'){
				if(vp.isEmpty()){
					if(maxLength < count)
						maxLength = count;
					count = 0;
					continue;
				}
				count = count+2;
				vp.pop();
				if(vp.isEmpty() &&maxLength < count)
					maxLength = count;
				continue;
			}
			vp.push(String.valueOf(parentheses.charAt(i)));
		}
		System.out.println(maxLength);
	}
}
