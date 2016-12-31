import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

public class AllPossibleWordsDictionary {
	public static boolean[] checkAllPossibleSentences(String s, HashSet<String> dict){
		boolean opt[] = new boolean[s.length()];
		if(s.length() == 0)
			return null;
		for(int i=0;i<opt.length;i++)
			opt[i] = false;
		if(dict.contains(s.substring(0,1)))
			opt[0] = true;
		for(int i=1; i<s.length();i++){
			if(dict.contains(s.substring(0,i+1))){
				opt[i] = true;
				continue;
			}
			for(int k=0;k<i;k++){
				if(opt[k] && dict.contains(s.substring(k+1,i+1)))
					opt[i] = true;
			}
		}
		return opt;
	}
	
	@SuppressWarnings("unchecked")
	public static void printAllPossibleSentences(String s, boolean opt[],HashSet<String> dict,int k,String immutableStr){
		if(k<=0){
			System.out.println(immutableStr);
			return;
		}
		for(int i= k;i>=0;i--){
			if(dict.contains(s.substring(i,k+1)) && i== 0){
				immutableStr = s.substring(i,k+1) +" "+ immutableStr;
				printAllPossibleSentences(s, opt, dict, i-1, immutableStr);
				immutableStr = "";
			}
			else if(i==0){
				return;
			}
			else if(opt[i-1] == true && dict.contains(s.substring(i,k+1))){
				immutableStr = s.substring(i,k+1) +" "+ immutableStr;
				printAllPossibleSentences(s, opt, dict, i-1, immutableStr);
				immutableStr = "";
			}
		}
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int size = sc.nextInt();
		HashSet<String> dictionary = new HashSet<>();
		for(int i=0;i<size;i++){
			dictionary.add(sc.next());
		}
		String immutableStr = "";
		printAllPossibleSentences(s,checkAllPossibleSentences(s,dictionary),dictionary,s.length()-1,immutableStr);
	}
}
