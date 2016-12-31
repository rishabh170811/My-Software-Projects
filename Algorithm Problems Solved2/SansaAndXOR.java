import java.util.Scanner;

public class SansaAndXOR {
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i = 0;i<T;i++){
			int N = sc.nextInt();
			long permutationXORCheck[] = new long[N];
			for(int j=0;j<N;j++){
				permutationXORCheck[j] = sc.nextLong();
			}
				if(N % 2 == 0)
					System.out.println(0);
				else{
					long XORCheck = 0L;
					for(int k=0;k<N;k=k+2){
						XORCheck = XORCheck ^ permutationXORCheck[k];
					}
					System.out.println(XORCheck);
				}
			}
		}
	}

