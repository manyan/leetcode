package leetcode;

import basicClass.LinkedListNode;

/*
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example,
Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
 * 
 * solution:
 * 1) write a method to reverse a linked list
 * 2) use a counter to walk through the linked list, 
 * 3) find kth element 
 * 4) put nextNode = kth.next
 * 5) set kth.next = null
 * 6) reverse it
 * 7) do 2) to 6) till we reach the end of linked list 
 * 
 * we need to store the first element , the last element and the first element of next iteration, and the last element of prvious iteration
 * */
public class ReverseNodeInKGroup {
	public static LinkedListNode reverseInKGroup(LinkedListNode head, int k) {
		int count = 1;
		LinkedListNode tempHead = head;
		LinkedListNode nextNode = null;
		LinkedListNode newHead = null;
		LinkedListNode lastNode = head;
		while (head != null) {
			if (count == k) {
				count = 1; // reset
				nextNode = head.next; // nextNode means next iteration's head
				
				// after reverse, tempHead becomes the last node
				LinkedListNode temp = null;
				head.next = null;
				if (newHead == null) {
					newHead = reverse(tempHead);
					temp = newHead;
				} else {
					temp = reverse(tempHead);
				}
				// we need to link lastNode(the last element from last iteration) to the new head
				lastNode.next = temp;
				
				//now tempHead is the last node of the current iteration, so we need to link it to nextNode
				tempHead.next = nextNode;
				
				lastNode = tempHead;
				// link the last node's next to nextNode
				head = nextNode;
				tempHead = head;
				continue;
			}
			
			head = head.next;
			count++;
		}
		
		return newHead;
	}
	
	public static LinkedListNode reverse(LinkedListNode head) {
		LinkedListNode lastNode = null;
		while (head != null) {
			LinkedListNode nextNode = head.next;
			if (nextNode == null) {
				head.next = lastNode;
				return head;
			}
			
			head.next = lastNode;
			lastNode = head;
			head = nextNode;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		LinkedListNode head = LinkedListNode.createALinkedList();
		head = reverseInKGroup(head,3);
		LinkedListNode.printLinkedList(head);
	}
}
