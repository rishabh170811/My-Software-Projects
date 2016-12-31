import java.util.Scanner;

public class CheckSumEqualsXOR {
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Long N = 0L;
		N = sc.nextLong();
//		if(N.longValue() == 1099511627776L){
//			System.out.println(N);
//			return;
//		}
		int counter = 0;
		while(N!=0){
			if(N%2 == 0)
				counter++;
			N = N/2;
		}
		System.out.println((long)Math.pow(2, counter));
	}
}
