import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LetterCombinationsPhoneNumbers {
    static HashMap<Integer,String> numberToCharMap = new HashMap<Integer,String>();
	static{
		numberToCharMap.put(2, "abc");
		numberToCharMap.put(3, "def");
		numberToCharMap.put(4, "ghi");
		numberToCharMap.put(5, "jkl");
		numberToCharMap.put(6, "mno");
		numberToCharMap.put(7, "pqrs");
		numberToCharMap.put(8, "tuv");
		numberToCharMap.put(9, "wxyz");
	}
	static String numberGlobal = "";
	static List<String> allCombinationPhoneNumbers = new ArrayList<String>();
	
		public  static String letterCombinationPhoneNumber(String number,String temp){
		if(number.length() == 1){
			return "";
		}
		else{
			for(int i = 0; i < numberToCharMap.get(Integer.parseInt(String.valueOf(number.charAt(0)))).length();i++){
				String tempX = temp + String.valueOf(numberToCharMap.get(Integer.parseInt(String.valueOf(number.charAt(0)))).charAt(i)); 
				letterCombinationPhoneNumber(number.substring(1),tempX);
				if(tempX.length() == numberGlobal.length()-1){
					allCombinationPhoneNumbers.add(tempX);
				}
			
			}
			return "";
		}
	}
	
    public static List<String> letterCombinations(String digits) {
        digits = digits + "#";
        allCombinationPhoneNumbers.clear();
        numberGlobal = digits;
        letterCombinationPhoneNumber(digits,"");
        
        return allCombinationPhoneNumbers;
    }
    public static void main(String args[]){
    	for(int i = 0;i < 2; i++){
    		String digits = new Scanner(System.in).next();
    		System.out.println(letterCombinations(digits));
    	}
    	}
}