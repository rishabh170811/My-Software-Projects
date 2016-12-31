import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class KrushkalTreeBuilder {
	public static ArrayList<MyGraph.Edge> buildKrushkalTree(MyGraph g){
		ArrayList<MyGraph.Edge> krushkalEdges = new ArrayList<MyGraph.Edge>();
		Collections.sort(g.edge,new Comparator<MyGraph.Edge>() {

			@Override
			public int compare(MyGraph.Edge o1, MyGraph.Edge o2) {
				// TODO Auto-generated method stub
				return o1.weight - o2.weight;
			}
		});
		CycleDetectionGraph cycleDetector = new CycleDetectionGraph();
		cycleDetector.initialize(g.edge);
		for(MyGraph.Edge eachEdge : g.edge){
			if(!cycleDetector.hasCycle(eachEdge)){
				cycleDetector.mergeSet(eachEdge);
				krushkalEdges.add(eachEdge);
			}
		}
		return krushkalEdges;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		MyGraph g = new MyGraph(7,8);
		g.init();
		ArrayList<MyGraph.Edge> finalKrushkalGraph = buildKrushkalTree(g);
		for(MyGraph.Edge eachEdge : finalKrushkalGraph){
			System.out.println(eachEdge.source +" " + eachEdge.destination + " " + eachEdge.weight);
		}
	}
}
