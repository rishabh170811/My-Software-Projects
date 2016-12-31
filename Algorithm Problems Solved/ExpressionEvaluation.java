import java.util.Scanner;

public class ExpressionEvaluation {
	
	public static int evaluate(String expression[],int start){
		if(start >= expression.length)
			return Integer.parseInt(expression[start/2-1]);
		if(!expression[start].equals("*") && !expression[start].equals("/") && !expression[start].equals("-") && !expression[start].equals("+"))
			return Integer.parseInt(expression[start]);
		int l = evaluate(expression,start*2+1);
		int r = evaluate(expression,start*2+2);
		if(expression[start].equals("+"))
			return l+r;
		if(expression[start].equals("-"))
			return l-r;
		if(expression[start].equals("/"))
			return l/r;
		else
			return l*r;
	}
	public static void main(String args[]){
		String exp = "+ * - 5 4 100 20";
		String expression[] = exp.split(" ");
		int startIndex = 0;
		System.out.println(evaluate(expression,startIndex));
	}
}
