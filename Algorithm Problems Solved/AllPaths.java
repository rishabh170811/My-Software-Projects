import java.util.HashSet;
import java.util.LinkedList;

public class AllPaths {
    private HashSet visited  = new HashSet();     
    private LinkedList path  = new LinkedList(); 
    private Graph G;
    private String s, t;

    public AllPaths(Graph G, String s, String t) {
        this.G = G;
        this.s = s;
        this.t = t;
    }
    public static class Graph{
    	String[] neighbours;
    	String name;
    	int children = 0;
    public void addEdge(String A, String B){
    	if(this.neighbours == null){
    		neighbours = new String[100];
    		this.neighbours[this.children] = B;
    		children++;
    	}
    	else{
    		this.neighbours[this.children] = B;
    	}
    }
    public String[] neighbours(String v){
    	return this.neighbours;
    }
    }

 
    public void show() { show(s); }

    private void show(String v) {

        // add node s to current path
        path.addLast(v);

        // found path from s to t
        if (v.equals(t)) {
            System.out.println(path);
        }

        // consider all neighbors that would continue path with repeating a node
        else {
            String[] neighbors = G.neighbours(v);
            for (int i = 0; i < neighbors.length; i++) {
                String w = neighbors[i];
                if (!path.contains(w)) show(w);
            }
        }

        // done exploring from v, so remove from path
        path.removeLast();
    }

   public static void main(String[] args) {
        Graph G = new Graph();
        G.addEdge("A", "B");
        G.addEdge("A", "C");
        G.addEdge("C", "D");
        G.addEdge("D", "E");
        G.addEdge("C", "F");
        G.addEdge("B", "F");
        G.addEdge("F", "D");
        G.addEdge("D", "G");
        G.addEdge("E", "G");
        System.out.println(G);
        AllPaths allpaths1 = new AllPaths(G, "A", "G");
        allpaths1.show();
        System.out.println();
    }


}