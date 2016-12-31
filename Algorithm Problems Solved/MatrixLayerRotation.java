import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MatrixLayerRotation {
	
	public static void updateMatrixOuterStrip(ArrayList<Integer> rs, int mat[][],int lt,int ld,int rt,int rd){
		for(int i=lt;i<ld;i++){
			mat[i][lt] = rs.get(0);
			rs.remove(0);
		}
		for(int j=lt;j<rt;j++){
			mat[ld][j] = rs.get(0);
			rs.remove(0);;
		}
		for(int k=rd;k>lt;k--){
			mat[k][rt]  = rs.get(0);
			rs.remove(0);;			
		}
		for(int l=rt;l>lt;l--){
			mat[lt][l]  = rs.get(0);
			rs.remove(0);;
		}
	}
	public static ArrayList<Integer> reverseArrayList(ArrayList<Integer> rs,int r){
		ArrayList<Integer> merger1 = new ArrayList<>();
		ArrayList<Integer> merger2 = new ArrayList<>();
		for(int i=rs.size() - r - 1;i>=0;i--){
			merger1.add(rs.get(i));
		}
		for(int i=rs.size() - 1;i>=rs.size()-r;i--){
			merger2.add(rs.get(i));
		}
		merger1.addAll(merger2);
		Collections.reverse(merger1);
		return merger1;
	}
	
	public static int[][] getRotatedMatrix(int mat[][],int rotationFactor,int lt,int ld,int rt,int rd){
		ArrayList<Integer> reversingStripper = new ArrayList<Integer>();
		if(lt >= ld || lt >= rt)
			return mat;
		for(int i=lt;i<ld;i++){
			reversingStripper.add(mat[i][lt]);
		}
		for(int j=lt;j<rt;j++){
			reversingStripper.add(mat[ld][j]);
		}
		for(int k=rd;k>lt;k--){
			reversingStripper.add(mat[k][rt]);			
		}
		for(int l=rt;l>lt;l--){
			reversingStripper.add(mat[lt][l]);
		}
		if(reversingStripper.isEmpty())
			return mat;
		reversingStripper = reverseArrayList(reversingStripper,rotationFactor%reversingStripper.size());
		updateMatrixOuterStrip(reversingStripper,mat,lt,ld,rt,rd);
		getRotatedMatrix(mat,rotationFactor,lt+1,ld-1,rt-1,rd-1);
		return mat;
	}
	
	public static void display(int row,int column,int matFinalResult[][]){
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				System.out.print(matFinalResult[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		int rotationFactor = sc.nextInt();
		int mat[][] = new int[rows][columns];
		for(int i = 0; i < rows; i++){
			for(int k=0;k < columns;k++){
				mat[i][k] = sc.nextInt();
			}
		}
		int matFinalResult[][] = getRotatedMatrix(mat,rotationFactor,0,rows-1,columns-1,rows-1);
		display(rows,columns,matFinalResult);
	}
}
