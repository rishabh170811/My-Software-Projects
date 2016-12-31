
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class TwitterDataAggregation {
	public static class CompareDateCategories implements Comparator<TwitterData>{

		@Override
		public int compare(TwitterData o1, TwitterData o2) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			try{
				String temp1[] = o1.date.split("-");
			    String temp1Date = temp1[0] +"-" +temp1[1];
				String temp2[] = o2.date.split("-");
			    String temp2Date = temp2[0] +"-"+temp2[1];
			Date d1;
				d1 = sdf.parse(temp1Date);
			Date d2;
			
				d2 = sdf.parse(temp2Date);
				if(d2.compareTo(d1) == 0){
					return o1.categories.compareTo(o2.categories);
				}					
					return d2.compareTo(d1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return 0;
		}
		
	}
	public static class TwitterData{
		String date;
		String categories;
		long clicks = 0;
		
	public TwitterData(String date, String categories, int clicks){
		this.date = date;
		this.categories = categories;
		this.clicks = clicks;
	}
	}
	
	public static void display(ArrayList<TwitterData> twitterData){
		ArrayList<TwitterData> finalTwitterAgg = new ArrayList<>();
		
		for(int i = 0;i < twitterData.size();i++){
			String eachDate1[] = twitterData.get(i).date.split("-");
			twitterData.get(i).date = eachDate1[0] + "-" + eachDate1[1];
			if(finalTwitterAgg.size() == 0){				
				finalTwitterAgg.add(twitterData.get(i));
				continue;
			}
			if(finalTwitterAgg.get(finalTwitterAgg.size()-1).date.equals(twitterData.get(i).date)){
				if(finalTwitterAgg.get(finalTwitterAgg.size()-1).categories.equals(twitterData.get(i).categories)){
					finalTwitterAgg.get(finalTwitterAgg.size()-1).clicks += twitterData.get(i).clicks;
					continue;
				}
				finalTwitterAgg.add(twitterData.get(i));
				continue;
			}
			finalTwitterAgg.add(twitterData.get(i));
		}
		finalDisplay(finalTwitterAgg);
		return;
	}
	
	public static void finalDisplay(ArrayList<TwitterData> twitterData){
		String prevDate = "";
		for(int i = 0; i < twitterData.size();i++){
			if(twitterData.get(i).categories.equals(""))
				continue;
				String eachDate[] = twitterData.get(i).date.split("-");
				String dateCompare = eachDate[0] + "-" + eachDate[1];
			if(prevDate.equals(dateCompare)){
				System.out.print(", "+twitterData.get(i).categories+", "+twitterData.get(i).clicks);
				continue;
			}
			prevDate = dateCompare;
			if(i != 0)
				System.out.println();
			System.out.print(dateCompare +", "+twitterData.get(i).categories+", "+twitterData.get(i).clicks);
		}
	}
	
	public static boolean dateValidator(String tokenDate,String lowerRange,String upperRange){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String eachDate[] = tokenDate.split("-");
		String dateCompare = eachDate[0] + "-" + eachDate[1];
		try{
		Date d1 = sdf.parse(dateCompare);
		Date d2 = sdf.parse(lowerRange);
		Date d3 = sdf.parse(upperRange);
		return (d1.compareTo(d2) >= 0 && d1.compareTo(d3) < 0);
		}
		catch(Exception e){
			return false;
		}
	}

	public static void main(String args[]){
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String input[] = sc.nextLine().split(",");
		String lowerRange = input[0].trim();
		String upperRange = input[1].trim();
		ArrayList<TwitterData> listData = new ArrayList<>();
		sc.nextLine();
		String inputDate;
		while(!(inputDate = sc.nextLine()).isEmpty()){
			String tokens[] = inputDate.split(",");
			if(dateValidator(tokens[0].trim(),lowerRange,upperRange)){
			listData.add(new TwitterData(tokens[0].trim(),tokens[1].trim().toLowerCase(),Integer.parseInt(tokens[2].trim())));	
			}
		}
		Collections.sort(listData,new CompareDateCategories());
		display(listData);
	}
}
/*
  
*/