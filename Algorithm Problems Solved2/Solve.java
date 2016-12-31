import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

public class Solve {
	/*
	 * Complete the function below.
	 */
	public static class Range{
	    int high;
	    int low;
	    public Range(int low,int high){
	        this.low = low;
	        this.high = high;
	    }
	}
	    public static String display(Stack<Range> rangeMerger){
	        String answer = "";
	        int size = rangeMerger.size();
	        while(!rangeMerger.isEmpty()){
	            Range temp = rangeMerger.pop();
	            if(rangeMerger.size() == size - 1)
	                answer = temp.low + ":" + temp.high;
	            else{
	                answer = temp.low + ":" + temp.high + ","+ answer;
	            }
	        }
	        return answer;
	    }
	    static String compactAndSort(String rangestring) {
	        String tokens[] = rangestring.split(",");
	        ArrayList<Range> listRange = new ArrayList<Range>();
	        for(String token : tokens){
	            token = token.trim();
	            listRange.add(new Range(Integer.parseInt(token.split(":")[0].trim()),Integer.parseInt(token.split(":")[1].trim())));
	        }
	        Collections.sort(listRange,new Comparator<Range>(){
	           @Override
	            public int compare(Range o1, Range o2){
	                return o1.low - o2.low;
	            } 
	        });
	        Stack<Range> rangeMerger = new Stack<Range>();
	        rangeMerger.push(listRange.get(0));
	        for(int counter = 1; counter < listRange.size();counter++){
	            if((rangeMerger.peek().high >= listRange.get(counter).low) || (rangeMerger.peek().high + 1 -listRange.get(counter).low == 0)){
	                Range temp = rangeMerger.pop();
	                rangeMerger.push(new Range(temp.low,listRange.get(counter).high));
	            }
	            else{
	                rangeMerger.push(listRange.get(counter));
	            }
	        }
	        System.out.println(rangeMerger.toString());
	        return display(rangeMerger);
	    }
	    
	    public static void main(String[] args) {
			String a = "10:20 , 1:5,     6:9";
			
			System.out.println(Solve.compactAndSort(a));
	    	
		}


}
