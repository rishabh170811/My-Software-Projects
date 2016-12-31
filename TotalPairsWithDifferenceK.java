import java.io.*;
import java.util.*;

public class TotalPairsWithDifferenceK {
    
    public static int countPairs(HashSet<Integer> integerSet, int diff){
            Iterator<Integer> iteratePair = integerSet.iterator();
            int totalPairs = 0;
            while(iteratePair.hasNext()){
                if(integerSet.contains(iteratePair.next() - diff))
                    totalPairs++;
            }
            return totalPairs;
    }
    
    @SuppressWarnings("resource")
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int diff = sc.nextInt();
        HashSet<Integer> integerSet = new HashSet<Integer>();
        for(int i = 0;i < size;i++){
            integerSet.add(sc.nextInt());
        }
        System.out.println(countPairs(integerSet,diff));
    }
}