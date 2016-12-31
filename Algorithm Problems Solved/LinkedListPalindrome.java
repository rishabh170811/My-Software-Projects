import java.util.Stack;

class LinkedListPalindrome
{
	
	// O(n) time and space complexity
    static boolean isPalindrome(LinkedList head) 
    {
        //Your code here
        Stack<Integer> checkPalindrome = new Stack<Integer>();
        LinkedList temp = head;
        while(temp != null){
            checkPalindrome.push(temp.data);
            temp = temp.next;
        }
        while(!checkPalindrome.isEmpty()){
            if(head == null)
                return false;
            if(!(head.data == checkPalindrome.pop()))
                return false;
            head = head.next;
        }
        return true;
    }
    public static void main(String args[]){
    	LinkedList insert = new LinkedList();
    	insert.insert(LinkedList.head, 1);
    	insert.insert(LinkedList.head, 2);
    	insert.insert(LinkedList.head, 3);
    	insert.insert(LinkedList.head, 4);
    	insert.insert(LinkedList.head, 3);
    	insert.insert(LinkedList.head, 2);
    	insert.insert(LinkedList.head, 1);
    	System.out.println(isPalindrome(LinkedList.head));
    }

}