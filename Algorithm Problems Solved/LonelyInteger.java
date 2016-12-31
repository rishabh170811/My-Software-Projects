import java.util.Scanner;

public class LonelyInteger {
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int pairBut1Unique[] = new int[N]; 
		for(int i = 0;i<N;i++){
			pairBut1Unique[i] = sc.nextInt();
		}
		int XORCheck = 0;
		for(int i = 0; i< N; i++){
			XORCheck = pairBut1Unique[i] ^ XORCheck;
		}
			System.out.println(XORCheck);
	}
	}

