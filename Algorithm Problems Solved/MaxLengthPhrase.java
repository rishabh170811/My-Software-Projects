import java.util.Scanner;

public class MaxLengthPhrase {
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String inputPhrase[] = sc.nextLine().split(" ");
		int phraseLength[] = new int[inputPhrase.length];
		for(int i=0;i<phraseLength.length;i++){
			phraseLength[i] = inputPhrase[i].length();
		}
		int maxT = Integer.parseInt(sc.nextLine());
		int maxLength = 0;
		int sumTillNow = 0;
		int lastPrunedIndex = 0;
		for(int i=0;i<phraseLength.length;i++){
			if(phraseLength[i] > maxT){
				lastPrunedIndex = i+1;
				sumTillNow = 0;
				continue;
			}
			if(phraseLength[i]+sumTillNow <= maxT){
				sumTillNow = sumTillNow + phraseLength[i];
			}
			else{
				sumTillNow = sumTillNow + phraseLength[i];
				for(int j=lastPrunedIndex;j<i;j++){
					if(sumTillNow > maxT){
						sumTillNow = sumTillNow - phraseLength[j];
						lastPrunedIndex++;
					}
				}
			}
			if(sumTillNow > maxLength){
				maxLength = sumTillNow;
			}
		}
		System.out.println(maxLength);
	}
}
