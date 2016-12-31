import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class IceCreamParlor {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0 ; i < T ; i++){
        	int target = sc.nextInt();
        	int no_of_flavors = sc.nextInt();
            int flavor_cost[] = new int[no_of_flavors];
            for (int j = 0; j<no_of_flavors; j++){
                flavor_cost[j] = sc.nextInt();
            }
            HashMap<Integer,Integer> targetMap = new HashMap<Integer,Integer>();
            int initial_point = 0;
            for(int eachflavorcost : flavor_cost){
                if(targetMap.containsKey(target - eachflavorcost)){
                  System.out.println(targetMap.get(target-eachflavorcost) +" "+(initial_point+1)); 
                }
                    else
                        targetMap.put(eachflavorcost,initial_point+1); 
                initial_point++;
            }
            initial_point = 0;
        }
    }
}