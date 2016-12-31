import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class CycleDetectionGraph {
	public static HashMap<Integer,HashSet<Integer>> unionSets = new HashMap<Integer, HashSet<Integer>>(); 
	
	public void initialize(ArrayList<MyGraph.Edge> edges){
		for(int i=0;i<edges.size();i++){
			if(!unionSets.containsKey(edges.get(i).source)){
				HashSet<Integer> unionSetI = new HashSet<Integer>();
				unionSetI.add(edges.get(i).source);
				unionSets.put(edges.get(i).source,unionSetI);
			}
			if(!unionSets.containsKey(edges.get(i).destination)){
				HashSet<Integer> unionSetN = new HashSet<Integer>();
				unionSetN.add(edges.get(i).destination);
				unionSets.put(edges.get(i).destination,unionSetN);
			}

		}
	}
	public boolean hasCycle(MyGraph.Edge e){
		if(unionSets.containsKey(e.source)){
			if(unionSets.get(e.source).contains(e.source) && unionSets.get(e.source).contains(e.destination))
				return true;
		}
		if(unionSets.containsKey(e.destination)){
			if(unionSets.get(e.destination).contains(e.source) && unionSets.get(e.destination).contains(e.destination))
				return true;
		}
		return false;
	}
	
	public void mergeSet(MyGraph.Edge e){
		HashSet<Integer> temp1 = new HashSet<>();
		HashSet<Integer> temp2 = new HashSet<>();
		if(unionSets.containsKey(e.source)){
			temp1 = unionSets.get(e.source);

		}
		if(unionSets.containsKey(e.destination)){
			temp2 = unionSets.get(e.destination);
		}
		temp2.addAll(temp1);
		unionSets.put(e.destination, temp2);
		unionSets.put(e.source, temp2);
		return;
	}
	
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		MyGraph g = new MyGraph(sc.nextInt(),sc.nextInt());
	}
}
