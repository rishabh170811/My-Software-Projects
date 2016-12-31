import java.util.HashMap;

public class LinkedList {
	LinkedList next;
	int data;
	public static LinkedList head;

	public LinkedList(int data){
	this.data = data;
	}
	public LinkedList(){
		
	}
	public LinkedList deleteLinkedListNode(LinkedList start){
		HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
		if (start == null || start.next == null){
			return start;
		}
		LinkedList be4temp = new LinkedList();
		LinkedList temp = start;
		be4temp = temp;
		hm.put(temp.data,1);
		temp = temp.next;
		while(temp!=null){
			if(hm.containsKey(temp.data)){
				be4temp.next = temp.next;
				temp = temp.next; 
			}
			else{
				hm.put(temp.data, 1);
				be4temp = temp;
				temp = temp.next;
			}
			
		}
		return head;
	}
	
	public void display(){
		if(head == null)
			return;
		LinkedList temp = head;
		while(temp!=null){
			if(temp.next != null)
				System.out.print(temp.data +"->");
			else
				System.out.println(temp.data);
			temp = temp.next;
		}
	}
	
	public void insert(LinkedList start, int data){
		start = head;
		head = start;
		LinkedList newNode = new LinkedList(data);
		if(start == null){
			head = newNode;
			return;
		}
		else{
			newNode.next = start;
			start = newNode;
			head = start;
		}
	}
	}
