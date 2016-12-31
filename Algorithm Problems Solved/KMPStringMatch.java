import java.util.Scanner;
/*
 * Trivial way to solve KMP
 * Maintaining suffix array that keeps a track of longest suffixes that are also prefixes
 */
public class KMPStringMatch {
	public static int getIndexOfMatchingStrings(String input, String pattern){
		int suffixArr[] = new int[input.length()];
		suffixArr = getSuffixArray(pattern);
		int j = 0;
		for(int i = 0;i< input.length();i++){
			if(pattern.charAt(j) == input.charAt(i)){
				j++;
				if(j == pattern.length())
					return 1;
				continue;
			}
			if(j==0){continue;}
			j = suffixArr[j];
			i = i - 1;
		}
		return -1;
	}
	//abcabda
	//abcababcabda
	public static int[] getSuffixArray(String pattern){
		int suffixArr[] = new int[pattern.length()];
		suffixArr[0] = 0;
		int i=0;
		int j=1;
		while(j < pattern.length()){
			if(pattern.charAt(i) == pattern.charAt(j)){
				suffixArr[j] = i+1;
				i++;
				j++;
				continue;
			}
			suffixArr[j] = suffixArr[j-1];
			j++;
		}
		return suffixArr;
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		String pattern = sc.nextLine();
		// abcxabcxabcy
		// abcxabcy
		System.out.println(getIndexOfMatchingStrings(input,pattern));
	}
}
