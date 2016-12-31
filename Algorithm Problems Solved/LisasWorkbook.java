import java.util.*;

public class LisasWorkbook {
    public static int min(int x, int y){
        if (x>y)
            return y;
        else
            return x;
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        int page_counter = 1;
        int numberOfSpecialProblems = 0;
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numberOfProblemsChapter = new ArrayList<Integer>();
        int no_of_chapters = sc.nextInt();
        int max_problems = sc.nextInt();
        for(int i = 0; i < no_of_chapters; i++){
            numberOfProblemsChapter.add(sc.nextInt());
        }
        for (int i =0; i < no_of_chapters;i++){
            int incrementer = numberOfProblemsChapter.get(0)/max_problems;
            if(numberOfProblemsChapter.get(0)%max_problems != 0)
                incrementer += 1;
            for(int j=0;j < incrementer;j++){
                if( j * max_problems < page_counter && page_counter <= (j*max_problems + min(numberOfProblemsChapter.get(0)-j*max_problems,max_problems))){
                   numberOfSpecialProblems+=1;
                }
                page_counter = page_counter + 1;
            }
            numberOfProblemsChapter.remove(0);
        }
        System.out.println(numberOfSpecialProblems);
    }
}