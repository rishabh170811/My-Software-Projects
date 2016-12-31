import java.util.Scanner;

public class LongestPalindromeSubstring {
	boolean isPalindrome = true;
	int length = 0;
	public LongestPalindromeSubstring(boolean Val, int Length) {
		this.isPalindrome = Val;
		this.length = Length;
		// TODO Auto-generated constructor stub
	}
	public static int LongestPalindrome(String longestPalindrome){
		LongestPalindromeSubstring opt[][]= new LongestPalindromeSubstring[longestPalindrome.length()][longestPalindrome.length()];
		for(int i = 0;i<longestPalindrome.length();i++){
			opt[i][i] = new LongestPalindromeSubstring(true, 1);
		}
		for(int i = 1;i < longestPalindrome.length();i++){
			for(int j=i,k=0;j<longestPalindrome.length();j++,k++){
				if(longestPalindrome.charAt(k) != longestPalindrome.charAt(j)){
					opt[k][j] = new LongestPalindromeSubstring(false, 0);
				}
				else{
					if(opt[k+1][j-1] == null){
						opt[k][j] = new LongestPalindromeSubstring(true,2);
						continue;
					}
					if(opt[k+1][j-1].isPalindrome == true){
						opt[k][j] = new LongestPalindromeSubstring(true, j-k+1);
					}
					else{
						opt[k][j] = new LongestPalindromeSubstring(false, 0);
					}
				}
			}
		}
		return maxPalindromeLength(opt, longestPalindrome.length());
	}
	public static int maxPalindromeLength(LongestPalindromeSubstring opt[][],int L){
		int maxLength = 0;
		for(int i = 0; i < L;i++){
			for(int j = 0;j < L;j++){
				if(opt[i][j] == null)
					continue;
				if(opt[i][j].length > maxLength)
					maxLength = opt[i][j].length;
				if(maxLength == 7){
					System.out.println("wow");
				}
			}
		}
		return maxLength;
	}
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		System.out.println(LongestPalindrome(s));
	}
}
