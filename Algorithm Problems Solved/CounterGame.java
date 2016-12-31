import java.math.BigInteger;
import java.util.Scanner;

public class CounterGame {
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		BigInteger Two = new BigInteger("2");
		for(int i=0;i<T;i++){
			BigInteger N1 = new BigInteger(sc.next()); 
			int counterWhoWins = 0;
			while(!N1.toString().equals("1")){
				for(int j=0;j<100;j++){
					if(N1.compareTo(Two.pow(j)) <= 0){
						if(N1.compareTo(Two.pow(j)) == 0 )
							N1 = N1.divide(Two);
						else{
							N1 = N1.subtract(Two.pow(j-1));
						}
							break;
					}
				}
				counterWhoWins += 1;
			}
			if(counterWhoWins % 2 == 1)
				System.out.println("Louise");
			else
				System.out.println("Richard");
		}
		
	}
}
