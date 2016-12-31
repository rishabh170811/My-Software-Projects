
public class InOrderSuccessor {
	public static Node getInorderSuccessor(int val){
		if(Node.root == null)
			return null;
		Node temp = Node.root;
		while(temp != null){
			if(temp.val == val)
				break;
			if(temp.val > val)
				temp = temp.left;
			else
				temp = temp.right;
		}
		if(temp == null)
			return null;
		return getInorderSuccessor(temp);
	}
	public static Node getSmallestElementInTheRightSubTree(Node smallestElement){
		if(smallestElement.left == null)
			return smallestElement;
		return getSmallestElementInTheRightSubTree(smallestElement.left);
	}
	public static Node getInorderSuccessor(Node nodeSuccessorToBeFound){
		if(nodeSuccessorToBeFound.parent == null)
			return nodeSuccessorToBeFound;
		if(nodeSuccessorToBeFound.right != null)
			return getSmallestElementInTheRightSubTree(nodeSuccessorToBeFound.right);
		if(nodeSuccessorToBeFound.parent.left.val == nodeSuccessorToBeFound.val)
			return nodeSuccessorToBeFound.parent;
		if(nodeSuccessorToBeFound.parent.right.val == nodeSuccessorToBeFound.val){
			while(nodeSuccessorToBeFound.parent.left.val != nodeSuccessorToBeFound.val){
				nodeSuccessorToBeFound = nodeSuccessorToBeFound.parent;
				if(nodeSuccessorToBeFound == null)
					return null;
			}
		}
		return nodeSuccessorToBeFound.parent;
	}
	
	public static void main(String args[]){
		Node.initialize();
	//	Node.display(Node.root);
		System.out.println(getInorderSuccessor(4).val);
	}	
}
