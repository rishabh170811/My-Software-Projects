import java.util.Scanner;

public class StringToInteger {
	public static String convertString(String atoi){
		String temp = "";
		if(atoi.length() == 0)
			return temp;
		if(atoi.charAt(0) < '0' && atoi.charAt(0)>'9' && atoi.charAt(0)!='-')
			return temp;
		else
			temp = String.valueOf(atoi.charAt(0));
		for(int i = 1;i<atoi.length();i++){
			if(atoi.charAt(i) < '0' || atoi.charAt(i)>'9'){
				return temp;
			}
			temp = temp + String.valueOf(atoi.charAt(i));
		}
		return temp;
	}
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String atoi = sc.next();
		atoi.trim();
		atoi = convertString(atoi);
		try{
			if(atoi.length() > 10){
				if(atoi.charAt(0) == '-'){
					
				}
			}
			if(Long.parseLong(atoi) > Integer.MAX_VALUE){
				System.out.println(Integer.MAX_VALUE);
				return;
			}
			if(Long.parseLong(atoi) < Integer.MIN_VALUE){
				System.out.println(Integer.MIN_VALUE);
				return;
			}
			System.out.println(Integer.parseInt(atoi.trim()));
		}
		catch(Exception e){
			System.out.println(0);
		}
	}
}
