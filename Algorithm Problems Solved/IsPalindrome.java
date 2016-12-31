import java.util.Scanner;

public class IsPalindrome {
    public static long reverse(long temp){
        long tempdash = 0;
        int count = -1;
        long tempX = temp;
        while(tempX!=0){
            count++;
            tempX = tempX/10;
        }
        while(temp!=0){
        	System.out.println(temp%10 * Math.pow(10,count));
        	double x = (Math.pow(10,count) * temp%10);
        	System.out.println(x + tempdash);
            tempdash = (long) (temp%10*Math.pow(10,count) + tempdash);
            temp = temp/10;
            count = count - 1;
        }
        return (long)tempdash;
    }
    public static boolean isPalindrome(int x) {
        if(x < 0)
            x = -x;
        long temp = (long)x;
        if(reverse(temp) == temp){
            return true;   
        }
        return false;
    }
    public static void main(String args[]){
    	Scanner sc = new Scanner(System.in);
    	System.out.println(isPalindrome(sc.nextInt()));
    	
    }
}