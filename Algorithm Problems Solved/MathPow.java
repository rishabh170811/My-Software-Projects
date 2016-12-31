import java.util.Scanner;

public class MathPow {
	public static double myPow(double pow, int N){
		if(N == 0){
			return 1;
		}
		if(N < 0){
			if(N % 2 == 0)
				return (Math.pow(myPow(pow,N/2),2));
			else{
				double x = Math.pow(myPow(pow,N/2),2) * 1/pow;
				return x;
			}
			
		}
		else {
			if(N % 2 == 0)
				return Math.pow(myPow(pow,N/2),2);
			else
				return Math.pow(myPow(pow,N/2),2) * pow;
		}
	}
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		double powNo = scanner.nextDouble();
		int N = scanner.nextInt();
		System.out.println(myPow(powNo,N));
	}
}
