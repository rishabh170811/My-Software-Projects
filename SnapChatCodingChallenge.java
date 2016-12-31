import java.util.Scanner;

public class SnapChatCodingChallenge {
	int length = 0;
	public SnapChatCodingChallenge(int Length) {
		this.length = Length;
		// TODO Auto-generated constructor stub
	}
	public static int max(int x,int y){
		if(x>y)
			return x;
		return y;
	}
	
	public static int LongestPalindrome(String longestPalindrome){
		int opt[][]= new int[longestPalindrome.length()][longestPalindrome.length()];
		if(longestPalindrome.length() == 1)
			return 1;
		for(int i = 0;i<longestPalindrome.length();i++){
			opt[i][i] = 1;
		}
		for(int i = 1;i < longestPalindrome.length();i++){
			for(int j=i,k=0;j<longestPalindrome.length();j++,k++){
				if(longestPalindrome.charAt(k) != longestPalindrome.charAt(j)){
					opt[k][j] = max(opt[k+1][j],opt[k][j-1]);
					continue;
				}
				opt[k][j] = opt[k+1][j-1] + 2;
				}
			}
		return opt[0][longestPalindrome.length()-1];
	}
	
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		System.out.println(s.length() - LongestPalindrome(s));
	}
}
