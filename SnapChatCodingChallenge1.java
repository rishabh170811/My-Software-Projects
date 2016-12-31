import java.util.*;
import java.lang.*;
public class SnapChatCodingChallenge1 {
	static String elements[] = {"sales","person","snap","chat","so","ever","people","what","per","son",};
	static Set<String> dict = new HashSet(Arrays.asList(elements));
	
	public static boolean wordBreak(String s, Set<String> wordDict) {
	    int[] pos = new int[s.length()+1];
	 
	    Arrays.fill(pos, -1);
	 
	    pos[0]=0;
	    
	    for(int i=0; i<s.length(); i++){
	        if(pos[i]!=-1){
	            for(int j=i+1; j<=s.length(); j++){
	                String sub = s.substring(i, j);
	                if(wordDict.contains(sub)){
	                	if(sub.length() == s.length())
	                		continue;
	                    pos[j]=i;
	                }
	            } 
	        }
	    }
	 
	    return pos[s.length()]!=-1;
	}
	static String[] simpleWords(String[] words) {
		String finalResult[] = new String[words.length];
		int counter = 0;
		for(String each : words){
			if(!wordBreak(each,dict)){
				finalResult[counter++] = each;
			}
		}
		return finalResult;
    }
	
	public static void main(String args[]){
		String compoundWord[] = {"chat","ever","snapchat","snap","salesperson","per","person","sales","son"};
		
		String finalResult[] = simpleWords(compoundWord);
		for(int i=0;i<finalResult.length;i++){
			System.out.println(finalResult[i]);
		}
	}
}
