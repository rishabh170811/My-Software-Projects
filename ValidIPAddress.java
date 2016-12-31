import java.util.*;
public class ValidIPAddress {
	public static boolean validateIPV6(String tokens[]){
		for(String token : tokens){
			if(token.length() > 4)
				return false;
			for(int i=0;i<token.length();i++){
				if((token.charAt(i) >= 48 && token.charAt(i) <= 57) || (token.charAt(i) >= 65 && token.charAt(i) <= 70))
					continue;
				return false;
			}
		}
		return true;
	}
	
	public static boolean validateIPV4(String tokens[]){
		for(String token : tokens){
			try{
				if(Integer.parseInt(token) >= 0 && Integer.parseInt(token) <= 255)
					continue;
				return false;
			}
			catch(NumberFormatException e){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String args[]){
		
		String ax[] = {"This is a junk text","121.18.19.20"};
		String arr[] = new String[ax.length];
		for(int i = 0;i < ax.length;i++){
			String validateIp = ax[i];
			String tokensIPV6[] = validateIp.split(":");
			if(tokensIPV6.length >1 && tokensIPV6.length!=8){
				arr[i] = "Neither";
				continue;
			}
			if(tokensIPV6.length == 8){
				if(validateIPV6(tokensIPV6)){
					arr[i] = "IPV6";
					continue;
				}
				arr[i] = "Neither";
			}
			String tokensIPV4[] = validateIp.split("\\.");
			if(tokensIPV4.length !=4){
				arr[i] = "Neither";
				continue;
			}
			if(validateIPV4(tokensIPV4)){
				arr[i] = "IPV4";
				continue;
			}
			arr[i] = "Neither";
		}
		
		System.out.println(arr[0] +" "+ arr[1]);
	}
}
