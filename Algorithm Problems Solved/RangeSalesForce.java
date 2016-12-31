import java.util.*;
class Range{
	int low;
	int high;
	public Range(int low,int high){
		this.low = low;
		this.high = high;
	}
}

public class RangeSalesForce {
	
	public static String display(Stack<Range> rangeMerger){
		String finalResult = "";
		while(!rangeMerger.isEmpty()){
			if(finalResult.equals("")){
				finalResult = String.valueOf(rangeMerger.peek().low)+":"+String.valueOf(rangeMerger.peek().high);
				rangeMerger.pop();
				continue;
			}
				finalResult = String.valueOf(rangeMerger.peek().low)+":"+String.valueOf(rangeMerger.peek().high)+","+finalResult;
				rangeMerger.pop();
		}
		return finalResult;
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String ranges = sc.nextLine();
		ranges.trim();
		String tokens[] = ranges.split(",");
		ArrayList<Range> allRanges = new ArrayList<>();
		for(String token : tokens){
			String highLow[] = token.split(":");
			allRanges.add(new Range(Integer.parseInt(highLow[0].trim()),Integer.parseInt(highLow[1].trim())));
		}
		Collections.sort(allRanges,new Comparator<Range>() {
			@Override
			public int compare(Range o1, Range o2) {
			
				return o1.low - o2.low;
			}
		});
		System.out.println(allRanges);
		Stack<Range> rangeMerger = new Stack<>();
		rangeMerger.push(allRanges.get(0));
		for(int i = 1; i < allRanges.size();i++){
			if(rangeMerger.peek().high > allRanges.get(i).high){
				continue;
			}
			else if(rangeMerger.peek().high - allRanges.get(i).low + 1 >= 0){
				Range tempRange = rangeMerger.pop();
				rangeMerger.push(new Range(tempRange.low, allRanges.get(i).high));
			}
			else{
				rangeMerger.push(allRanges.get(i));
			}
		}
		System.out.println(display(rangeMerger));
	}
}
