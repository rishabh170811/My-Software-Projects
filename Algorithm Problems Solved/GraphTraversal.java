import java.util.Scanner;

public class GraphTraversal {
	private static Scanner sc;
	public static int MINUSONE = -1;

	public static void main(String args[]){
		sc = new Scanner(System.in);
		Graph newNode = new Graph(MINUSONE);
		for(int i=1; i < 10; i++){
			Integer data = sc.nextInt();
			newNode = new Graph(data);
		    newNode.insert(newNode,Graph.root);
		}
		newNode.display(Graph.root);
	}
}
