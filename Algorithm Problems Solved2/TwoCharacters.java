import java.util.HashSet;
import java.util.Scanner;

public class TwoCharacters {
	public static String removeChar(char removed1,char removed2, String tobeUpdated){
		String newString = "";
		for(int i=0;i<tobeUpdated.length();i++){
			if(tobeUpdated.charAt(i) == removed1 || tobeUpdated.charAt(i) == removed2)
				newString += tobeUpdated.substring(i, i+1);
		} 
		return newString;
	}
	public static boolean checkAlternate(String tempIsAlternate){
		for(int i = 0;i<tempIsAlternate.length()-1;i++){
			if(tempIsAlternate.charAt(i) == tempIsAlternate.charAt(i+1))
				return false;
		} 
		return true;
	}
	
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        String s = in.next();
        int maxLength = 0;
        if(s.length() == 1){
        	System.out.println(0);
        	return;
        }
        HashSet<Character>allChar = new HashSet<Character>();
        for(int i=0;i<len;i++){
        	if(allChar.contains(s.charAt(i)))
        		continue;
        	allChar.add(s.charAt(i));
        }
        Object[] allCharArray = allChar.toArray();
        for(int i=0;i<allCharArray.length-1;i++){
        	for(int j=i+1;j<allCharArray.length;j++){
        		String newString = removeChar((char)allCharArray[i],(char)allCharArray[j],s);
        		
        		if(checkAlternate(newString)){
        			if(maxLength < newString.length())
        				maxLength = newString.length();
        	}
        	}
        }
        System.out.println(maxLength);
	}
}
