import java.util.HashSet;
import java.util.Scanner;
/*
 * abcdefgh
 * a
 * ba..ab
 * solving for all combinations of length i-1 strings produced 
 * and doing the following for all j from 0 to i-1
 * place(charat i,j,str(k,i-1)
 */
public class AnagramNoDuplicates {
	public static HashSet<String> uniqueSet = new HashSet<>(); 
	public static void anagramFinder(String anagram,int start){
		if(anagram.length() == start)
			return;
		else{
			int index = start; // the element which will be swapped
			for(int i = index; i < anagram.length();i++){
				String temp = swap(anagram,index,i);
				if(!uniqueSet.contains(temp)){
					System.out.println(temp);
					uniqueSet.add(temp);
					
				}
				anagramFinder(temp,index+1);
			}
		}
		return;
	}
	
	public static String swap(String anagram,int startIndex,int endIndex){
		StringBuilder sb = new StringBuilder(anagram);
		char start = sb.charAt(startIndex);
		sb.setCharAt(startIndex, sb.charAt(endIndex));
		sb.setCharAt(endIndex,start);
		return sb.toString();		
		
	}
	
	/*designing an idea that would revolutionalize the food industry and by application of optimized */
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String anagram = sc.nextLine();
		int start = 0;
		anagramFinder(anagram,start);
	}
}
