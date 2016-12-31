
public class ListNode {
      int val;
      public static int count = 0;
      public static ListNode start;
      ListNode next;
      ListNode(int x) { val = x; }

public static void main(String args[]){
	ListNode l1 = new ListNode(1);

	ListNode l2 = new ListNode(5);
	ListNode l22 = new ListNode(8);
	l2.next = l22;
	ListNode l3 = addTwoNumbers(l1,l2);
	display(l3);
	
}

public static void display(ListNode l3){
	while(l3 != null){
		System.out.print(l3.val + " ");
		l3 = l3.next;
	}
}


public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null)
            return null;
        if(l1 == null)
            return l2;
        if(l2 == null)
            return l1;
        else{
            int carry = 0;
            ListNode head = null,temp = null;
            while(l1!=null && l2!=null){
                int data = (l1.val + l2.val + carry)%10;
                carry = (l1.val + l2.val + carry)/10;
                if(head == null){
                    ListNode node1 = new ListNode(data);
                    head = node1;
                    temp = head;
                }
                else{
                    ListNode nodei = new ListNode(data);
                    nodei.next = null;
                    temp.next = nodei;
                    temp = nodei;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
            if(l1 != null){
                while(l1 != null){
                    int data = (l1.val + carry)%10;
                    carry = (l1.val + carry)/10;
                    ListNode nodei = new ListNode(data);
                    temp.next = nodei;
                    temp = nodei;
                    l1 = l1.next;
                }
            }
            if(l2 != null){
                while(l2 != null){
                    int data = (l2.val + carry)%10;
                    carry = (l2.val + carry)/10;
                    ListNode nodei = new ListNode(data);
                    temp.next = nodei;
                    temp = nodei;
                    l2 = l2.next;
                }
            }
            if(carry != 0){
            	ListNode nodei = new ListNode(carry);
            	temp.next = nodei;
            	temp = nodei;
            }

            return head;
        }
    }
}