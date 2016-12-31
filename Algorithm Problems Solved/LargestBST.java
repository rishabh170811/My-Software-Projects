import java.util.Scanner;

public class LargestBST {
	public static class Node{
		int val;
		int sizeBST=0;
		Node right;
		Node left;
		int level = 0;
		Node inter;
		public static Node root;
	public Node(int val){
		this.val = val;
		this.right = null;
		this.left = null;
	}
	public static void display(Node root){
		if(root == null)
			return;
		display(root.left);
		System.out.print(root.sizeBST + " ");
		display(root.right);
	}
	public static void initialize(){
		root.left = new Node(2);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.left.right.left = new Node(10);
		root.left.right.left.right = new Node(15);
		root.left.right.left.left = new Node(25);
		root.right = new Node(33);
		root.right.left = new Node(34);
		root.right.right = new Node(7);
		root.right.right.left = new Node(13);
		root.right.right.left.right = new Node(17);
		root.right.right.left.left = new Node(23);
	}
	
	public static void getLargestBST(Node root){
		if(root == null)
			return;
		getLargestBST(root.left);
		getLargestBST(root.right);
		if(root.left == null && root.right == null){
			root.sizeBST = 1;
			return;
		}
		if(root.left == null){
			if(root.right.sizeBST == 0)
				return;
			if(root.right.val > root.val){
				root.sizeBST = 1 + root.right.sizeBST;
				return;
			}
			return;
		}
		if(root.right == null){
			if(root.left.sizeBST == 0)
				return;
			if(root.left.val < root.val){
				root.sizeBST = 1 + root.left.sizeBST;
				return;
			}
			return;
		}
		if(root.left.val < root.val && root.right.val > root.val){
			if(root.left.sizeBST == 0 && root.right.sizeBST == 0)
				return;
			root.sizeBST = max(root.left.sizeBST,root.right.sizeBST) + 1;
			return;
		}
		if(root.left.val < root.val && root.left.sizeBST != 0){
			root.sizeBST = root.left.sizeBST + 1;
			return;
		}
		if(root.right.val > root.val && root.right.sizeBST != 0){
			root.sizeBST = root.right.sizeBST + 1;
			return;
		}
		return;
	}
	
	public static int max(int x, int y){
		if(x>y)
			return x;
		return y;
	}
	public static void insert(int val){
		if(root == null){
			root = new Node(val);
			return;
		}
		Node temp = root;
		Node be4temp = root;
		while(temp != null){
			be4temp = temp;
			if(temp.val < val)
				temp = temp.right;
			else
				temp = temp.left;
		}
		Node newBSTNode = new Node(val);
		if(be4temp.val > val)
			be4temp.left = newBSTNode;
		else
			be4temp.right = newBSTNode;
		return;
	}

	}
	
	public static void main(String args[]){
		
		LargestBST.Node.insert(1);
		LargestBST.Node.initialize();
		Node.getLargestBST(Node.root);
		Node.display(Node.root);
	}

}
