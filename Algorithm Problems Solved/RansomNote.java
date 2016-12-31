import java.util.*;

public class RansomNote {
    HashMap<String, Integer> magazineMap;
    HashMap<String, Integer> noteMap;
    
    public RansomNote(String magazine, String note) {
        this.magazineMap = new HashMap<String,Integer>();
        this.noteMap = new HashMap<String,Integer>();
    	for(String eachString : magazine.split(" ")){
            if(!this.magazineMap.containsKey(eachString))
                this.magazineMap.put(eachString,1);
            else
            	this.magazineMap.put(eachString,this.magazineMap.get(eachString)+1);
        }
        for(String eachString : note.split(" ")){
            if(!this.noteMap.containsKey(eachString))
                this.noteMap.put(eachString,1);
            else
            	this.noteMap.put(eachString, this.noteMap.get(eachString)+1);
        }
        
    }
    
    
	

	public boolean solve() {
		System.out.println(this.magazineMap);
		System.out.println(this.noteMap);
        Iterator itr = this.noteMap.entrySet().iterator();
        while(itr.hasNext()){
            Map.Entry pair = (Map.Entry)itr.next();
            if(!this.magazineMap.containsKey(pair.getKey()))
                return false;
            else if(this.magazineMap.get(pair.getKey()) < this.noteMap.get(pair.getKey()))
            	return false;
        }
         return true;     
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        
        // Eat whitespace to beginning of next line
        scanner.nextLine();
        
        RansomNote s = new RansomNote(scanner.nextLine(), scanner.nextLine());
        scanner.close();
        
        boolean answer = s.solve();
        if(answer)
            System.out.println("Yes");
        else System.out.println("No");
      
    }
}

