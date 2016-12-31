import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class PlayWithWords {
	public static int[][] LCSP(String palindrome){
		int opt[][] = new int[palindrome.length()][palindrome.length()];
		if(palindrome.length() == 0){
			return null;
		}
		for(int i = 0;i < palindrome.length(); i++){
			 opt[i][i] = 1;
		}
		for(int i = 1; i < palindrome.length();i++){
			for(int j = i,k=0; j < palindrome.length() ; j++,k++){
				if(palindrome.charAt(j) == palindrome.charAt(k)){
					opt[k][j] = opt[k+1][j-1] + 2;
				}
				else{
					opt[k][j] = max(opt[k][j-1],opt[k+1][j]);
				}
			}
		}
		
		return opt;
	}
	
	public static String backTrack(int opt[][],String palindrome){
		int i = 0;
		int j = palindrome.length() - 1;
		HashSet<Integer> maxPalindromeSubseq = new HashSet<Integer>();
		while(i < palindrome.length()-1 && j > 0 && opt[i][j] > 0){
			if(opt[i][j] == 1){
				maxPalindromeSubseq.add(j);
				break;
			}
			if(opt[i+1][j-1]+2 == opt[i][j] && palindrome.charAt(i) == palindrome.charAt(j)){
				maxPalindromeSubseq.add(i++);
				maxPalindromeSubseq.add(j--);
				continue;
			}
			if(opt[i+1][j] > opt[i][j-1]){
				i++;
			}
			else{
				j--;
			}
				
		}
		String LCSP ="";
		for(int eachIndex : maxPalindromeSubseq){
			LCSP += String.valueOf(palindrome.charAt(eachIndex));
		}
		return LCSP;
	}
	
	public static int max(int x,int y){
		if(x>y)
			return x;
		else
			return y;
	}
	
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String palindromeString = sc.next();
		int opt[][] = LCSP(palindromeString);
		int maxProduct = 0;
		for(int i = 0;i<palindromeString.length()-1;i++){
			if(maxProduct < opt[0][i] * opt[i+1][palindromeString.length()-1]){
				maxProduct = opt[0][i] * opt[i+1][palindromeString.length()-1];
			}
		}
		System.out.println(maxProduct);
	}
}
