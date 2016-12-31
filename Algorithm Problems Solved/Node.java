public class Node{
		int val;
		Node right;
		Node left;
		Node parent;
		int level = 0;
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
		System.out.print(root.val + " ");
		display(root.right);
	}
	public static void initialize(){
		
		Node.insert(5);
		Node.insert(3);
		Node.insert(2);
		Node.insert(4);
		Node.insert(8);
		Node.insert(9);
		Node.insert(7);
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
		newBSTNode.parent = be4temp;
		return;
	}

	}