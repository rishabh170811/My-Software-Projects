import java.util.Scanner;

public class PowerCheckN {
	
	public static boolean isXpowerOfN(int x,int n){
		int powerN = (int) (Math.log(x)/Math.log(n));
		if(Math.pow(n, powerN) == x)
			return true;
		return false;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int x = sc.nextInt();
		System.out.println(isXpowerOfN(x,n));
	}
}
