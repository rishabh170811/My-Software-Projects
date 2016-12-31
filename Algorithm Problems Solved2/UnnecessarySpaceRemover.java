import java.util.Scanner;

public class UnnecessarySpaceRemover {
	public static void main(String args[]){
	Scanner sc = new Scanner(System.in);
	String s = sc.nextLine();
	int counter = 0;
	for(int i=0;i<s.length();i++){
		if(s.charAt(i) == ' '){
			continue;
		}
		if(i != s.length() - 1)
			s = s.substring(0, counter) + String.valueOf(s.charAt(i)) + s.substring(counter+1);
		else
			s = s.substring(0, counter) + String.valueOf(s.charAt(i));
		counter++;
	}
		System.out.println(s);
	}
}
