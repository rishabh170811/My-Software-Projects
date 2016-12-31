public class Graph {
 Graph left;
 Graph right;
 Graph parent;
 Integer data;
 static Graph root;
 
public Graph(int data){
	this.data = data;
}
public void display(Graph root){
	if(root == null){
		return;
	}
	else{
		System.out.print(root.data+" ");
	    display(root.left);
	    display(root.right);
	}
}

public void insert(Graph newNode, Graph iterator){
	if (newNode == null)
		return;
	if (iterator == null){
		root = newNode; 
	    return;
	}
	else{
		if(iterator.data>newNode.data){
			if(iterator.left == null){
				iterator.left = newNode;
			    newNode.parent = iterator;
			    return;
			}
			insert(newNode,iterator.left);
		}
		else{
		    if(iterator.right == null){
		    	iterator.right = newNode;
		    	newNode.parent = iterator;
		        return;
		    }
		    	insert(newNode,iterator.right);
		}
	}
}
public Graph findSuccessor(Graph node){
	if(node == null)
		return null;
	else if(node.left == null)
		return node;
	else
		findSuccessor(node.left);
	return null;
}
//delete is non trivial
public void delete(Graph findNode){
	if(root == null){
		System.out.println("ELEMENT NOT FOUND");
		return;
	}

if(root.data == findNode.data){
		if(root.left == null && root.right == null){
			root = null;
		}
		else if(root.right == null && root.left != null){
		   if(root.parent.right.data == root.data){
			   root.parent.right = root.left;
			   root.left.parent = root.parent;
		   }
		   else{
			   root.parent.left = root.left;
			   root.left.parent = root.parent;
		   }
		}
		   else if(root.left == null && root.right != null){
			   if(root.parent.right.data == root.data){
				   root.parent.right = root.left;
				   root.left.parent = root.parent;
			   }
			   else{
				   root.parent.left = root.left;
				   root.left.parent = root.parent;
			   }
		}
		   else {
			       Graph highestSuccessor =  findSuccessor(root);
			       highestSuccessor.parent = root.parent;
			       if(root.parent.right.data == root.data){
			    	   
			       }
			       else{
			    	   
			       }
		   }
   		}
	else{
		if(root.data > findNode.data)
			delete(root.left);
		else
			delete(root.right);
	}
}
}

