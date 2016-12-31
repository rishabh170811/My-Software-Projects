import java.util.Scanner;

public class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        if(strs.length == 1)
            return strs[0];
        String smallestString = findSmallestString(strs);
        String longestCommonPrefix ="";
        for(int i = 0;i < smallestString.length();i++){
            for(int j=0;j<strs.length;j++){
                if(smallestString.charAt(i) != strs[j].charAt((i)))
                    return longestCommonPrefix;
            }
            longestCommonPrefix += String.valueOf(smallestString.charAt(i));
        }
        return longestCommonPrefix;
    }
    public static String findSmallestString(String[] strX){
        int minLength = Integer.MAX_VALUE;
        String minString = "";
        for(int i = 0; i < strX.length;i++){
            if(strX[i].length() < minLength){
                minString = strX[i];
                minLength = strX[i].length();
            }
        }
        return minString;
    }
    
    public static void main(String args[]){
    	Scanner sc = new Scanner(System.in);
    	int N = sc.nextInt();
    	String str[] = new String[N];
    	for(int i = 0; i < N; i++){
    		str[i] = sc.next();
    	}
    	System.out.println(longestCommonPrefix(str));
    }
}