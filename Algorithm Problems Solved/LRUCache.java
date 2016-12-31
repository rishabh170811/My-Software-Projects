import java.beans.FeatureDescriptor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Scanner;

import javax.print.attribute.HashAttributeSet;

public class LRUCache {
	public static class LRUNode{
		int pageNumber = -1;
		String information ="";
		public static LinkedList<Integer> recentlyVisited= new LinkedList<>();
		public static HashMap<Integer,LRUNode> pages = new HashMap<>();
		public static int MAX_SIZE;
		public LRUNode(int keyVal,String information){
			this.pageNumber = keyVal;
			this.information = information;
		}
		public void updateQueue(int pageNumber){
			recentlyVisited.add(pageNumber);
			ListIterator<Integer> findPageNumber = recentlyVisited.listIterator();
			int counter = -1;
			while(findPageNumber.hasNext()){
				counter++;
				if(findPageNumber.next() == pageNumber){
					if(counter == 0)
						continue;
					recentlyVisited.remove(counter);
					return;
				}
			}
			return;
		}
		
		public LRUNode getLRUNode(int pageNumber){
			if(pages.containsKey(pageNumber)){
				updateQueue(pageNumber);
				return pages.get(pageNumber);
			} 
			return null;
		}
		public void setLRUNode(int pageNumber, String val){
			if(pages.size() == MAX_SIZE)
				pages.remove(recentlyVisited.getLast());
			if(pages.containsKey(pageNumber)){
				pages.put(pageNumber,new LRUNode(pageNumber,val));
			}
		}
	}
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		
	}
}
