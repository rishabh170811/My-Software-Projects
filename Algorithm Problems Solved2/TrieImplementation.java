import java.util.HashMap;


public class TrieImplementation {
	public static class TrieBuilder{
		HashMap<Character,TrieBuilder> children = new HashMap<>();
		boolean isLeaf = false;
		public static TrieBuilder root = new TrieBuilder();
	}
	
	public static void buildTree(String eachTrieElement, TrieBuilder root){
		for(int i = 0;i<eachTrieElement.length();i++){
			if(root.children.containsKey(eachTrieElement.charAt(i))){
				root = root.children.get(eachTrieElement.charAt(i));				
				continue;
			}
			root.children.put(eachTrieElement.charAt(i), new TrieBuilder());
			root = root.children.get(eachTrieElement.charAt(i));
			if(i == eachTrieElement.length() - 1)
				root.isLeaf = true;
			else
				root.isLeaf = false;
		}
	}
	
	public static void howAboutDisplay(TrieBuilder root){
		if(root == null)
			return;
		if(root.isLeaf == true)
			return;
		int counter = 0;
		for(Character eachChar : root.children.keySet()){
			System.out.println(eachChar + " " + counter++ +  " level");
			howAboutDisplay(root.children.get(eachChar));
		}
	}
	
	public static boolean findPatternInTrie(TrieBuilder root,String pattern){
		if(root == null)
			return false;
		for(int i = 0; i < pattern.length();i++){
			if(root.children.containsKey(pattern.charAt(i))){
				root = root.children.get(pattern.charAt(i));
				continue;
			}
			return false;
		}
		return true;
	}
	//trie is a really cool data structure  
	public static void main(String args[]){
		final String KNOWLEDGE_BASE = "hereisasimplepatternmatchingtriealgorithm";
		String allTries[] = new String[KNOWLEDGE_BASE.length()];
		for(int i = 0; i < KNOWLEDGE_BASE.length();i++){
			allTries[i] = KNOWLEDGE_BASE.substring(i, KNOWLEDGE_BASE.length());
		}
		for(int i = 0;i < allTries.length;i++){
			buildTree(allTries[i],TrieBuilder.root);
		}
		howAboutDisplay(TrieBuilder.root);
		String pattern = "algoris";
		System.out.println(findPatternInTrie(TrieBuilder.root,pattern));
	}
}
