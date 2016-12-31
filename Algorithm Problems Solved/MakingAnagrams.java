
import java.util.*;

public class MakingAnagrams {
    public static int numberNeeded(String first, String second) {
      HashMap<Character,Integer> hmstr1 = new HashMap<Character,Integer>();
      HashMap<Character,Integer> hmstr2 = new HashMap<Character,Integer>();
      int counter = 0;
        for(char eachCharString1 : first.toCharArray()){
          if(!hmstr1.containsKey(eachCharString1)){
              hmstr1.put(eachCharString1,1);
          }
          else{
              hmstr1.put(eachCharString1,hmstr1.get(eachCharString1)+1);
          }
      }
      @SuppressWarnings("rawtypes")
	Iterator it1 = hmstr1.entrySet().iterator();
    
      for(char eachCharString2 : second.toCharArray()){
          if(!hmstr2.containsKey(eachCharString2)){
              hmstr2.put(eachCharString2,1);
          }
          else{
              hmstr2.put(eachCharString2,hmstr2.get(eachCharString2)+1);
          }   
      }
      
      while(it1.hasNext()){
        @SuppressWarnings("rawtypes")
		Map.Entry pair = (Map.Entry)it1.next();
        if(hmstr2.containsKey(pair.getKey())){
              if((hmstr2.get(pair.getKey()) - hmstr1.get(pair.getKey()))>0)
                counter += hmstr2.get(pair.getKey()) - hmstr1.get(pair.getKey());
              else
                counter += hmstr1.get(pair.getKey()) - hmstr2.get(pair.getKey());
          }
        else{
               counter += hmstr1.get(pair.getKey());
        } 
      }
      @SuppressWarnings("rawtypes")
	Iterator it2 = hmstr2.entrySet().iterator();
     while(it2.hasNext()){
         @SuppressWarnings("rawtypes")
		Map.Entry pair = (Map.Entry)it2.next();
        if(!hmstr1.containsKey(pair.getKey())){
           counter += hmstr2.get(pair.getKey());
          }  
      }
        return counter;
        //iterating over each entry in hm1..that will give me 
    }
  
    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}

