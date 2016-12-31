
public class NumberBSTNodesWithinRange {
	public static int countNodes = 0;
	public static int countNumberOfNodesInRange(int l,int r,BST.Node eachNode){
		if(eachNode == null)
			return countNodes;
		if(eachNode.value > l && eachNode.value < r){
			countNodes++;
			countNumberOfNodesInRange(l, r, eachNode.left);
			countNumberOfNodesInRange(l, r, eachNode.right);
		}
		else if(eachNode.value <= l){
			countNumberOfNodesInRange(l, r, eachNode.right);
		}
		else if(eachNode.value >= r){
			countNumberOfNodesInRange(l, r, eachNode.left);
		}
		return countNodes;
	}
	
	public static void main(String args[]){
		BST.insert(new BST.Node(8));
		BST.insert(new BST.Node(4));
		BST.insert(new BST.Node(12));
		BST.insert(new BST.Node(2));
		BST.insert(new BST.Node(6));
		BST.insert(new BST.Node(5));
		BST.insert(new BST.Node(7));
		BST.insert(new BST.Node(3));
		BST.insert(new BST.Node(10));
		BST.insert(new BST.Node(14));
		BST.insert(new BST.Node(11));
		BST.display(BST.Node.root);
		System.out.println();
		System.out.println(countNumberOfNodesInRange(1,16,BST.Node.root));
	}
}
