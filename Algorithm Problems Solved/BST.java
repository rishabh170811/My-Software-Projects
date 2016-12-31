
public class BST {
	public static class Node{
		int value;
		Node left;
		Node right;
		public static Node root;
		
		public Node(int value){
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}
	
	public static void display(Node newNode){
		if(newNode == null)
			return;
		display(newNode.left);
		System.out.print(newNode.value + " ");
		display(newNode.right);
	}
	
	public static void insert(Node newNode){
		if(BST.Node.root == null){
			BST.Node.root = newNode;
			return;
		}
		Node temp = BST.Node.root;
		Node be4temp = BST.Node.root;
		while(temp != null){
			be4temp = temp;
			if(temp.value < newNode.value)
				temp = temp.right;
			else
				temp = temp.left;
		}
		if(be4temp.value < newNode.value)
			be4temp.right = newNode;
		else
			be4temp.left = newNode;
		return;
	}
}
