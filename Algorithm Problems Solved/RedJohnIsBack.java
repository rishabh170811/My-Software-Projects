import java.util.Scanner;

public class RedJohnIsBack {
	public static int totalNumberOfConfiguration(int N){
		int opt[] = new int[N+1];
		if(N < 4)
			return numberOfPrimesLesserThanOptN(1);
		opt[0] = 1;
		opt[1] = 1;
		opt[2] = 1;
		opt[3] = 1;
		for(int i = 4;i <= N;i++){
			opt[i] = opt[i-4] + opt[i-1];
		}
		return numberOfPrimesLesserThanOptN(opt[N]);
	}
	public static boolean isPrime(int checkIsPrime){
		for(int i = 2;i <= Math.sqrt(checkIsPrime); i++){
			if(checkIsPrime % i == 0)
				return false;
		}
		return true;
	}
	public static int numberOfPrimesLesserThanOptN(int P){
		int numberOfPrimes = 1;
		if(P < 2)
			return 0;
		for(int i = 3;i <= P; i++){
			if(isPrime(i)){
				numberOfPrimes += 1;
			}
		}
		return numberOfPrimes;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int i=0;i<T;i++){
			int N = sc.nextInt();
			System.out.println(totalNumberOfConfiguration(N));
		}
	}
}
