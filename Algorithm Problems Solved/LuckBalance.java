import java.util.*;

public class LuckBalance {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        int totalCounts = sc.nextInt();
        int K = sc.nextInt();
        ArrayList<Integer> importantContestLuck = new ArrayList<Integer>();
        int T[] = new int[totalCounts];
        int L[] = new int[totalCounts];
        int maximumLuckCount = 0;
        for(int i = 0;i < totalCounts;i++){
            L[i] = sc.nextInt();
            T[i] = sc.nextInt();
            if(T[i] == 1){
                importantContestLuck.add(L[i]);
            }
            else{
                maximumLuckCount += L[i];
            }
        }
        Collections.sort(importantContestLuck,Collections.reverseOrder());
        while(!importantContestLuck.isEmpty()){
            if(K > 0)
               maximumLuckCount += importantContestLuck.get(0);
            else
               maximumLuckCount -= importantContestLuck.get(0);
            importantContestLuck.remove(0);
            K--;
        }
        System.out.println(maximumLuckCount);
    }
}