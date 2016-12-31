import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class ConnectedCellsInGrid {
	public static int grid[][];
	int i = -1;
	int j = -1;
	public ConnectedCellsInGrid(int i,int j){
		this.i = i;
		this.j = j;
	}
	public static int findMaxRegion(int grid[][],int N,int M){
		int maxRegion = 0;
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(grid[i][j] == 1){
					int toBeCompared = DFS(i,j,N,M);
					if(toBeCompared > maxRegion ){
						maxRegion = toBeCompared;
					}
				}
			}
		}
		return maxRegion;
	}
	public static int neighboursPush(int i,int j,Stack<ConnectedCellsInGrid> cells,int N, int M){
		ArrayList<ConnectedCellsInGrid> validNeighbours = new ArrayList<ConnectedCellsInGrid>();
		int countCells = 0;
		validNeighbours.add(new ConnectedCellsInGrid(i-1, j));
		validNeighbours.add(new ConnectedCellsInGrid(i+1, j));
		validNeighbours.add(new ConnectedCellsInGrid(i, j-1));
		validNeighbours.add(new ConnectedCellsInGrid(i, j+1));
		validNeighbours.add(new ConnectedCellsInGrid(i-1, j-1));
		validNeighbours.add(new ConnectedCellsInGrid(i-1, j+1));
		validNeighbours.add(new ConnectedCellsInGrid(i+1, j-1));
		validNeighbours.add(new ConnectedCellsInGrid(i+1, j+1));
		for(int k = 0; k < validNeighbours.size(); k++){
			int local1 = validNeighbours.get(k).i;
			int local2 = validNeighbours.get(k).j;
			if((local1 > N-1 || local1 < 0 || local2 < 0 || local2 > M-1)){
				continue;
			}
			if(grid[local1][local2] == 1){
				cells.push(new ConnectedCellsInGrid(local1, local2));
				grid[local1][local2] = -1;
				countCells++;
			}
		}
		return countCells;
		
	}
	public static int DFS(int i,int j,int N,int M){
		int noPointsRegion = 1;
		Stack<ConnectedCellsInGrid> cells = new Stack<ConnectedCellsInGrid>();
		cells.push(new ConnectedCellsInGrid(i, j));
		grid[i][j] = -1;
		while(!cells.isEmpty()){
			int XCell = cells.peek().i;
			int YCell = cells.peek().j;
			cells.pop();
			noPointsRegion += neighboursPush(XCell, YCell, cells, N, M);
		}
		return noPointsRegion;
	}
	
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		grid= new int[N][M];
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				grid[i][j] = sc.nextInt();
			}
		}
		System.out.println(findMaxRegion(grid,N,M));
	}
}
