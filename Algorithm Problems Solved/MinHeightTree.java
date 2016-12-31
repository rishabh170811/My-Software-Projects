import java.util.LinkedList;
import java.util.Queue;

public class MinHeightTree {
	public static boolean isLeaf(LargestBST.Node checkLeaf){
		if(checkLeaf.left == null && checkLeaf.right == null)
			return true;
		return false;
	}
	public static int minHeight(LargestBST.Node root){
		Queue<LargestBST.Node> eachNode = new LinkedList<>();
		eachNode.add(root);
		while(!eachNode.isEmpty()){
			LargestBST.Node leafCheck = eachNode.poll();
			if(isLeaf(leafCheck)){
				return leafCheck.level;
			}
			if(leafCheck.left != null){
				eachNode.add(leafCheck.left);
				leafCheck.left.level = leafCheck.level + 1;
				continue;
			}
			leafCheck.right.level = leafCheck.level + 1;
			eachNode.add(leafCheck.right);
		}
		return 0;
	}
	
	public static void main(String args[]){
		LargestBST.Node.insert(1);
		LargestBST.Node.initialize();
		LargestBST.Node.display(LargestBST.Node.root);
		System.out.println(minHeight(LargestBST.Node.root));
	}
}
