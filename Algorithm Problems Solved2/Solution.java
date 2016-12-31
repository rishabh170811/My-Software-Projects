import java.util.Scanner;

public class Solution {
	boolean isPalindrome = true;
	int length = 0;
	
	public static int LongestPalindrome(String longestPalindrome){
		Solution opt[][]= new Solution[longestPalindrome.length()][longestPalindrome.length()];
		for(int i = 0;i<longestPalindrome.length();i++){
			opt[i][i] = new Solution();
			opt[i][i].isPalindrome = true;
			opt[i][i].length = 1;
		}
		int maxLength = 0;
		int tempIndexI = 0;
		int tempIndexJ = 0;
		for(int i = 1;i < longestPalindrome.length();i++){
			for(int j=i,k=0;j<longestPalindrome.length();j++,k++){
				if(longestPalindrome.charAt(k) != longestPalindrome.charAt(j)){
					opt[k][j] = new Solution();
					opt[k][j].isPalindrome = false;
					opt[k][j].length = 0;
				}
				else{
					if(opt[k+1][j-1] == null){
						opt[k][j] = new Solution();
						opt[k][j].isPalindrome = true;
						opt[k][j].length = 2;
						if(maxLength < opt[k][j].length){
							maxLength = 2;
							tempIndexI = k;
							tempIndexJ = j;
						}
						continue;
					}
					if(opt[k+1][j-1].isPalindrome == true){
						opt[k][j] = new Solution();
						opt[k][j].isPalindrome = true;
						opt[k][j].length = j-k+1;
						if(maxLength < opt[k][j].length){
							maxLength = opt[k][j].length;
							tempIndexI = k;
							tempIndexJ = j;
						}
					}
					else{
						opt[k][j] = new Solution();
						opt[k][j].isPalindrome = false;
						opt[k][j].length = 0;
					}
				}
			}
		}
//		return longestPalindrome.substring(tempIndexI, tempIndexJ+1);
		return maxLength;
	}
	
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		System.out.println(LongestPalindrome(s));
	}
}
