import java.util.ArrayList;

public class NLevelLinkedList {	
	public static void transformBTtoBTL(LargestBST.Node eachNode){
		ArrayList<LargestBST.Node> qNodes = new ArrayList<LargestBST.Node>();
		if(eachNode == null)return;
		qNodes.add(eachNode);
		while(!qNodes.isEmpty()){
			LargestBST.Node chance = qNodes.get(0);
			qNodes.remove(0);
			System.out.print(chance.val + " ");
			if(!qNodes.isEmpty()){
				if(qNodes.get(0).level == chance.level)
					chance.inter = qNodes.get(0);}
			if(chance.left != null){
				chance.left.level = chance.level + 1;
				qNodes.add(chance.left);}
			if(chance.right != null){
				chance.right.level = chance.level + 1;
				qNodes.add(chance.right);}}}
	public static void main(String args[]){
		LargestBST.Node.insert(24);
		LargestBST.Node.root.level = 0;
		LargestBST.Node.initialize();
		transformBTtoBTL(LargestBST.Node.root);
	}
}
