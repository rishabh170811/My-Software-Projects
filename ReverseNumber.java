import java.util.Scanner;

public class ReverseNumber {
	public static int maxInt(int num){
		String reverse = "0";
		if(num == Integer.MAX_VALUE)
			return 0;
		else{
			int length = 0;
			int tempNum = num;
			while(tempNum !=0){
				length++;
				tempNum = tempNum/10;
			}
			int count = length-1;
			while(num != 0){
				int mod = num % 10;
					num = num/10;
					if(Math.pow(10, count) * mod + Integer.parseInt(reverse) > Integer.MAX_VALUE || Math.pow(10, count) * mod + Integer.parseInt(reverse) < Integer.MIN_VALUE)
						return 0;
				reverse = String.valueOf((int)Math.pow(10, count) * mod + Integer.parseInt(reverse));
				count -= 1;
				if(Integer.parseInt(reverse) == Integer.MAX_VALUE || Integer.parseInt(reverse) == Integer.MIN_VALUE)
					return 0;
				if(Integer.parseInt(reverse)<0 && num>0)
					return 0;
			}
		}
		
		return Integer.parseInt(reverse);
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		System.out.println(maxInt(num));
	}
}
