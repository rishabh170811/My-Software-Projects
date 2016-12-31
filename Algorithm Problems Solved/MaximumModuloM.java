import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class MaximumModuloM {
	public static class Node {
		int data;
		Node left;
		Node right;
		Node parent;
		public static Node root = new Node();

		public void insert(int data,int index,int total_sum_arr[]){
			Node temp = root;
			Node be4temp = root;
			while(temp!=null){
				be4temp = temp;
				if(total_sum_arr[temp.data]>data){
					temp = temp.left;
				}
				else
					temp = temp.right;
			}
			Node newNode = new Node();
			newNode.data = index;
			if(be4temp.left == null && be4temp.right == null){
				if(total_sum_arr[be4temp.data] > data){
					be4temp.left = newNode;
					newNode.parent = be4temp;
					return;
				}
				else{
					be4temp.right = newNode;
					newNode.parent = be4temp;
					return;
				}
			}
			if(be4temp.left == null){
				be4temp.left = newNode;
				newNode.parent = be4temp;
				return;
			}
			if(be4temp.right == null){
				be4temp.right = newNode;
				newNode.parent = be4temp;
				return;
			}
			
	}
	}
    public static int max(int x,int y){
        if(x>y)
            return x;
        else
            return y;
    }
    public static Node findNode(int index,Node graph,int total_sum[]){
    	if(total_sum[graph.data] == total_sum[index])
    		return graph;
    	if(total_sum[graph.data]>total_sum[index]){
    		return findNode(index,graph.left,total_sum);
    	}
    	else return findNode(index,graph.right,total_sum);
    	

    }
    public static Node minValue(Node minNode){
    	if(minNode.left == null)
    		return null;
    	else{
    		while(minNode.left != null){
    			minNode = minNode.left;
    		}
    		return minNode;
    }
    }
    public static Node inOrderSuccessor(Node root, Node n) {  	 
        // step 1 of the above algorithm 
        if (n.right != null) {
            return minValue(n.right);
        }
        // step 2 of the above algorithm
        Node p = n.parent;
        while (p != null && n == p.right) {
            n = p;
            p = p.parent;
        }
        return p;
    }
    
    public static Node nextInorderSuccessor(int index, Node graph,int total_sum[]){
    	Node newNode = findNode(index,graph,total_sum);
    	return inOrderSuccessor(graph,newNode);
    }
    
    public static int maxSumModuloM(int arr_computeM[],int total_sum[],int M){
        int max_sum = Integer.MIN_VALUE;
        for (int i=0;i<total_sum.length;i++){
            if(max_sum<total_sum[i])
                max_sum = total_sum[i];
        }
        //wasteful process that's increasing the complexity by a factor of n as opposed to log(n) ?
        //
        for(int i=1;i<arr_computeM.length;i++){
        		if(nextInorderSuccessor(i,Node.root,total_sum) == null)
        			continue;
        		  int smallestsumgreaterthantotalsum = total_sum[nextInorderSuccessor(i,Node.root,total_sum).data];
                  max_sum = max((total_sum[i] - smallestsumgreaterthantotalsum + M)%M,max_sum);
            }
        return max_sum;
    }
    
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int i=0;i<T;i++){
            int arrLength = sc.nextInt();
            int M = sc.nextInt();
            int arr_sum_modulo[] = new int[arrLength];
            for(int j=0;j<arrLength;j++){
                arr_sum_modulo[j] = sc.nextInt()%M;
            }
            int max_sum = Integer.MIN_VALUE;
            int total_sum[] = new int[arrLength];
            total_sum[0] = arr_sum_modulo[0]%M;
            Node.root.data = 0;
            Node.root.left = null;
            Node.root.right = null;
            Node.root.parent = null;
            Node methodCalling = new Node();
            for(int j=1;j<arrLength;j++){
                total_sum[j] = (total_sum[j-1] + arr_sum_modulo[j]%M)%M;
                methodCalling.insert(total_sum[j],j,total_sum);
                if(nextInorderSuccessor(j,Node.root,total_sum) == null){
        			max_sum = max((total_sum[j])%M,max_sum);
        			continue;
        		}
        		  int smallestsumgreaterthantotalsum = total_sum[nextInorderSuccessor(j,Node.root,total_sum).data];
                  max_sum = max((total_sum[j] - smallestsumgreaterthantotalsum + M)%M,max_sum);
            }
         
            for(int k =0; k < total_sum.length;k++){
            	if(total_sum[k]>max_sum)
            		max_sum = total_sum[k];
            }
            System.out.println(max_sum);
        }
    }
}