import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class SameCharactersdDistance {
	public static final String EMPTY_STR = "";
	public static final int EMPTY_INT = 0;
	public static class CharacterAndFrequencies implements Comparator<CharacterAndFrequencies>{
		int frequency;
		String character;
		@Override
		public int compare(CharacterAndFrequencies o1, CharacterAndFrequencies o2) {
			return o2.frequency - o1.frequency;
		}
		public CharacterAndFrequencies(String character, int freq){
			this.character = character;
			this.frequency = freq;
		}

	}
	public static void buildCharFreqMap(HashMap<String,CharacterAndFrequencies> hashCharacterToFreq, String pattern){
		for(int i = 0;i < pattern.length();i++){
			if(! hashCharacterToFreq.containsKey(String.valueOf(pattern.charAt(i)))){
				hashCharacterToFreq.put(String.valueOf(pattern.charAt(i)), new CharacterAndFrequencies(String.valueOf(pattern.charAt(i)),1));
				continue;
			}
			CharacterAndFrequencies tempCharFreq = hashCharacterToFreq.get(String.valueOf(pattern.charAt(i)));
			tempCharFreq.frequency++;
			hashCharacterToFreq.put(String.valueOf(pattern.charAt(i)), tempCharFreq);
		}
	}
	public static void buildArrayListCharFreq(HashMap<String,CharacterAndFrequencies>hashCharacterToFreq,String pattern,ArrayList<CharacterAndFrequencies> heapCharFreq){
		for(String eachCharacter : hashCharacterToFreq.keySet()){
			heapCharFreq.add(hashCharacterToFreq.get(eachCharacter));
		}
		return;
	}
	public static void fillEachCharWithEmptyString(String patternDApart[]){
		for(int i = 0; i < patternDApart.length;i++){
			patternDApart[i] = "$";
		}
	}
	
	public static String[] getPatterndDistance(ArrayList<CharacterAndFrequencies> heapCharFreq, int L,int D){
		String patternDApart[] = new String[L]; 
		int firstIndexOfEmptyString = 0;
		fillEachCharWithEmptyString(patternDApart);
		for(CharacterAndFrequencies eachCharFreq: heapCharFreq){
			int counter = 0;
			if(firstIndexOfEmptyString >= L)
				return null;
			while(eachCharFreq.frequency > 0){
				if(firstIndexOfEmptyString + counter * D >= L)
					return null;
				if(! patternDApart[firstIndexOfEmptyString + counter * D].equals("$")){
					return null;
				}
				patternDApart[firstIndexOfEmptyString + counter++ * D] = eachCharFreq.character;			
				eachCharFreq.frequency--;
			}
			int firstIndexFinder = firstIndexOfEmptyString + 1;
			while(firstIndexFinder < patternDApart.length){
				if(patternDApart[firstIndexFinder].equals("$"))
					break;
					firstIndexFinder++;
			}
			firstIndexOfEmptyString = firstIndexFinder;
		}
		return patternDApart;	
	}
	
	public static String[] getEachChardDistance(String pattern,int D){
		String dDistPattern[] = new String[pattern.length()];
		HashMap<String,CharacterAndFrequencies> hashCharacterToFreq = new HashMap<>();
		ArrayList<CharacterAndFrequencies> heapCharFreq = new ArrayList<>();
		buildCharFreqMap(hashCharacterToFreq,pattern);
		buildArrayListCharFreq(hashCharacterToFreq,pattern,heapCharFreq);
		Collections.sort(heapCharFreq,new CharacterAndFrequencies(EMPTY_STR,EMPTY_INT));
		
		return getPatterndDistance(heapCharFreq,pattern.length(),D);
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int d = sc.nextInt();
		String pattern = "geeksforgeeks";
		String patternChar[] = getEachChardDistance(pattern,d);
		for(String eachChar : patternChar)
		System.out.print(eachChar);
	}
}
