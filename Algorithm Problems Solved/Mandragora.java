import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;

public class Mandragora {
	public static long max(long x,long y){
		if (x > y)
			return x;
		else
			return y;
	}
	public static void main(String args[]) throws IOException{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		FileWriter fw = new FileWriter("outputyohan.txt", true);
		for(int test = 0; test < T; test++ ){
		int arrSize = sc.nextInt();
		long arrdrago[] = new long[arrSize];
		for (int i = 0; i < arrSize; i++){
			arrdrago[i] = sc.nextInt();
		}
		Arrays.sort(arrdrago);
		long opt[][] = new long[arrSize][arrSize+1];
		if (arrSize == 0){
			continue;
		}
		if(arrSize == 1){
			System.out.println(arrdrago[0]);
			continue;
		}
		opt[0][1] = arrdrago[0];
		opt[0][2] = 0;
		for(int i = 1;i < arrSize;i++){
			for(int j = 1; j <= i+1;j++){
				if(j == 1)
					opt[i][j] = opt[i-1][j] + arrdrago[i];
//				else if(j==i+1){
//					opt[i][j] = opt[i-1][j-1];
//				}
				else
					opt[i][j] = max(opt[i-1][j-1],opt[i-1][j] + (long)j*arrdrago[i]);
			}
		}
		long maxVal = -1;
		for(int i = 0; i <= arrSize;i++){
			if(opt[arrSize-1][i]>maxVal)
				maxVal = opt[arrSize-1][i];
		}
//		fw.append(String.valueOf(maxVal + "\n"));
		System.out.println(maxVal);
		
	}
}
}
