import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class CountLuckDFS {
	int index1 = -1;
	int index2 = -1;
	int individual_hermaine = 0;
	public CountLuckDFS(int index1,int index2) {
		this.index1 = index1;
		this.index2 = index2;
	}
	public static ArrayList<CountLuckDFS> returnNeighbours(char maze[][],int i,int j,int N, int M){
		ArrayList<CountLuckDFS> listNeighbours = new ArrayList<CountLuckDFS>();
		listNeighbours.add(new CountLuckDFS(i+1, j));
		listNeighbours.add(new CountLuckDFS(i-1, j));
		listNeighbours.add(new CountLuckDFS(i, j+1));
		listNeighbours.add(new CountLuckDFS(i, j-1));
		for(int k = 0;k < listNeighbours.size();k++){
			if(listNeighbours.get(k).index1 < 0 || listNeighbours.get(k).index1 > N-1){
				listNeighbours.remove(k);
					k--;
				continue;
			}
			if(listNeighbours.get(k).index2 < 0 || listNeighbours.get(k).index2 > M-1){
				listNeighbours.remove(k);
					k--;
				continue;
			}
			if(maze[listNeighbours.get(k).index1][listNeighbours.get(k).index2] == '*')
				continue;
			if(maze[listNeighbours.get(k).index1][listNeighbours.get(k).index2] != '.'){
				listNeighbours.remove(k);
					k--;
				continue;
			}
		}
		return listNeighbours;
	}
		
	public static CountLuckDFS findStartPoint(char maze[][],int N,int M){
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				if(maze[i][j] == 'M'){
					return new CountLuckDFS(i, j);
				}
					
			}
		}
		return null;
	}
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0;i<T;i++){
			int N = sc.nextInt();
			int M = sc.nextInt();
			int hermaine = 0;
			char maze[][] = new char[N][M];
			for(int j=0;j<N;j++){
				String temp = sc.next();
				for(int k=0;k<temp.length();k++){
					maze[j][k] = temp.charAt(k);
				}
			} // ....
			//   ....
			//   ....
			int K = sc.nextInt();
			Stack<CountLuckDFS> countLuckDFS = new Stack<CountLuckDFS>();
			CountLuckDFS startPoint = findStartPoint(maze,N,M);
			countLuckDFS.push(startPoint);
			while(!countLuckDFS.isEmpty()){
				ArrayList<CountLuckDFS>neighbours = returnNeighbours(maze, countLuckDFS.peek().index1, countLuckDFS.peek().index2,N,M);
				if(returnNeighbours(maze, countLuckDFS.peek().index1, countLuckDFS.peek().index2,N,M).size() > 1){
					for(CountLuckDFS each : neighbours){
						each.individual_hermaine = 1 + countLuckDFS.peek().individual_hermaine; 
					}
				}
				else if(returnNeighbours(maze, countLuckDFS.peek().index1, countLuckDFS.peek().index2,N,M).size() == 1)
					neighbours.get(0).individual_hermaine = countLuckDFS.peek().individual_hermaine;
					
				if(maze[countLuckDFS.peek().index1][countLuckDFS.peek().index2] == '*'){
					
					if(countLuckDFS.peek().individual_hermaine == K)
						System.out.println("Impressed");
					else
						System.out.println("Oops!");
					break;
				}
				maze[countLuckDFS.peek().index1][countLuckDFS.peek().index2] = 'd';
				countLuckDFS.pop();
				for(CountLuckDFS each : neighbours){
						countLuckDFS.push(each);
				}				
			}
			
		}
	}
}
