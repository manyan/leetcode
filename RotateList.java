package leetcode;

import basicClass.LinkedListNode;

/*
 * Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
 * */
public class RotateList {
	public static LinkedListNode rotateRight(LinkedListNode head, int n) {
        // Start typing your Java solution below
        // DO NOT write main() function
        if (n == 0) {
        	return head;
        }
        
        if (head == null) {
        	return head;
        }
        
        int counter = 0;
        LinkedListNode tempHead = head;
        while (counter < n && tempHead != null) {
        	counter++;
        	tempHead = tempHead.next;
        }
        
        if (tempHead == null || tempHead.next == null) {
        	return head;
        }
        
        LinkedListNode nextHead = tempHead.next;
        tempHead.next = null;
        tempHead = nextHead;
        while (tempHead.next != null) {
        	tempHead = tempHead.next;
        }
        
        tempHead.next = head;
        return nextHead;
    }
	
	public static void main(String[] args) {
		LinkedListNode head = LinkedListNode.createALinkedList();
		head = rotateRight(head, 3);
		LinkedListNode.printLinkedList(head);
	}
}
