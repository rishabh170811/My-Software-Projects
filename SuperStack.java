import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;

public class SuperStack {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int noOps = Integer.parseInt(sc.nextLine());
		Stack<Integer> superStack =  new Stack<>();
		for(int i  = 0; i < noOps;i++){
			String currOp[] = sc.nextLine().split(" ");
			if(currOp[0].equals("push")){
				superStack.push(Integer.parseInt(currOp[1]));
				System.out.println(superStack.get(0));
			}
			else if(currOp[0].equals("pop")){
				int x = superStack.pop();
				if(superStack.isEmpty())
					System.out.println("EMPTY");
				else
					System.out.println(x);
			}
			else {
				Stack<Integer> newStack = new Stack<>();
				for(int j = 0; j < superStack.size() - Integer.parseInt(currOp[1]);j++){
					newStack.push(superStack.pop());
				}
				int size = superStack.size();
				for(int j= 0;j<size;j++){
					newStack.push(superStack.pop()+Integer.parseInt(currOp[2]));
				}
				System.out.println(newStack.peek());
				superStack = newStack;
			}
		}
	}
}
