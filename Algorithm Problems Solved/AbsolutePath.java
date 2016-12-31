import java.util.Scanner;

public class AbsolutePath {

	   static int solution(String S) {
		   String[] paths = S.split("\n");
		    int[] myPastLevels = new int[paths.length+1];
		    int maxLen = 0;
		    for(String s:paths){
		        int lev = s.lastIndexOf("\t")+1, curLen = myPastLevels[lev+1] = myPastLevels[lev]+s.length()-lev+1;
		        if(s.contains(".")) 
		        	maxLen = Math.max(maxLen, curLen);
		    }
		    return maxLen;
	    }
	   static int mySolution(String S){
		   int maxLen = 0;
		   //not exactly DP HAHA
		   //max path to a dir with image .. max path to reach level  i-1 + path i(no.char.dir)
		   String[] eachPath = S.split("\n");
		   int[] myPastLevels = new int[eachPath.length];
		   myPastLevels[0] = 1;
		   for(String s : eachPath){
			   if(s.equals(""))
				   continue;
			   int level = s.length() - s.replaceAll(" ", "").length() + 1;
				   if(level == 1){
					   myPastLevels[level] = myPastLevels[level-1] + s.length();
				   }
				   else{
				   myPastLevels[level] = myPastLevels[level-1] + s.replaceAll(" ", "").length() + 1;
			   }
			   if(s.contains(".jpeg") || s.contains(".gif") || s.contains(".png")){
				   if(myPastLevels[level-1]>maxLen){
					   maxLen = myPastLevels[level-1];
				   }
			   }
		   }
		   return maxLen;
	   }
public static void main(String args[]){
	@SuppressWarnings("resource")
	Scanner sc = new Scanner(System.in);
	String x = "";
	for(int i = 0;i < 8 ; i++){
		x = x + "\n"+sc.nextLine();
	}
	System.out.println(mySolution(x));	
}
}
