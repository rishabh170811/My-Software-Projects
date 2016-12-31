
public class RemoveLinkedListDuplicate {
	public static void main(String args[]){
		LinkedList test = new LinkedList(75);
		test.insert(LinkedList.head, 23);
		test.insert(LinkedList.head, 15);
		test.insert(LinkedList.head, 22);
		test.insert(LinkedList.head, 22);
		test.insert(LinkedList.head, 22);
		test.insert(LinkedList.head, 63);
		test.insert(LinkedList.head, 32);
		test.insert(LinkedList.head, 15);
		test.insert(LinkedList.head, 22);
		test.display();
		test.deleteLinkedListNode(LinkedList.head);
		test.display();
	}
}
