import java.util.Scanner;

public class AliceBobKeyDecode {
	/*
	 * Complete the function below.
	 */
	// the key found is 8251220 8...
	    static String decrypt(String encrypted_message) {
	        if(encrypted_message.isEmpty())
	            return null;
	        String decrypted_message = "";
	        String key = "8251220";
	        int j = 0;
	        for(int i=0;i<encrypted_message.length();i++){
	        	int computedChar;
	        	if((encrypted_message.charAt(i) >=65 && encrypted_message.charAt(i) <=90) || (encrypted_message.charAt(i) >=97 && encrypted_message.charAt(i)<=122)){
	               if(encrypted_message.charAt(i) >=97 && encrypted_message.charAt(i)- Integer.parseInt(String.valueOf(key.charAt(j%(key.length()))))< 97){
	                    computedChar =  (123 - (97 - (encrypted_message.charAt(i)- Integer.parseInt(String.valueOf(key.charAt(j%(key.length())))))));
	               }
	               else if(encrypted_message.charAt(i) >=97){
	                    computedChar = (encrypted_message.charAt(i)- Integer.parseInt(String.valueOf(key.charAt(j%(key.length())))));
	               }
	               else if(encrypted_message.charAt(i) >=65 && encrypted_message.charAt(i)- Integer.parseInt(String.valueOf(key.charAt(j%(key.length()))))< 65){
	                   computedChar = (91 - (65 - (encrypted_message.charAt(i)- Integer.parseInt(String.valueOf(key.charAt(j%(key.length())))))));
	               }
	               else{
	            	   computedChar = (encrypted_message.charAt(i)- Integer.parseInt(String.valueOf(key.charAt(j%(key.length())))));
	               }
	               decrypted_message = decrypted_message + String.valueOf((char)computedChar);
	               j++;
	                   continue;   
	           }
	           decrypted_message = decrypted_message + String.valueOf(encrypted_message.charAt(i));
	        }
	        return decrypted_message;
	    }
	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String encrypted_message = sc.nextLine();
		System.out.println(decrypt(encrypted_message));
	}
}
