import java.util.ArrayList;
import java.util.Scanner;

public class MyGraph {
	public static class Edge {
		int source;
		int destination;
		int weight;
	public Edge(int source,int destination,int weight){
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	}
	ArrayList<Edge> edge = new ArrayList<Edge>();
	int V,E; // defines the number of edges and vertices
	// do you need anything else ? 
	public MyGraph(int V, int E){
		this.E = E;
		this.V = V;
	}
	public void init(){
		this.edge.add(new Edge(0,1,2));
		this.edge.add(new Edge(1,2,4));
		this.edge.add(new Edge(0,2,3));
	}
}
