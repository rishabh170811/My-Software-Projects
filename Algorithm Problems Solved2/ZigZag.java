import java.util.Scanner;

public class ZigZag {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String str = "";
		for(int i = 0;i<3;i++){
			 str += sc.next();
		}
		str.replaceAll("\n", "").replaceAll(" ", "");
		System.out.println(str);
		}
}
