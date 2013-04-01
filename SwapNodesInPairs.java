package leetcode;

import basicClass.LinkedListNode;

//Given a linked list, swap every two adjacent nodes and return its head.
//For example,
//Given 1->2->3->4, you should return the list as 2->1->4->3.
public class SwapNodesInPairs {
	public static LinkedListNode swapPairs(LinkedListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		LinkedListNode newHead = null;
		LinkedListNode lastNode = null;
		while(head != null && head.next != null) {
			LinkedListNode swapNode = head.next;
			LinkedListNode nextNode = swapNode.next;
			if (lastNode != null) {
				lastNode.next = swapNode;
			}
			swapNode.next = head;
			head.next = nextNode;
			lastNode = head;
			// init only once for the new head
			if (newHead == null) {
				newHead = swapNode;
			}

			head = nextNode;
		}
		
		return newHead;
	}
	
	public static void doSwapPairRecursion(LinkedListNode head, LinkedListNode lastNode) {
		if (head == null || head.next == null) {
			return;
		}
		
		LinkedListNode swapNode = head.next;
		if (lastNode != null) {
			lastNode.next = swapNode;
		}
		
		LinkedListNode next = swapNode.next;
		swapNode.next = head;
		head.next = next;
		
		lastNode = head;
		head = next;
		doSwapPairRecursion(head, lastNode);
	}
	
	public static LinkedListNode swapPairsRecursion(LinkedListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		LinkedListNode newHead = head.next;
		doSwapPairRecursion(head,null);
		
		return newHead;
	}
	
	public static void main(String[] args) {
		LinkedListNode head = LinkedListNode.createALinkedList();
		head = swapPairsRecursion(head);
		LinkedListNode.printLinkedList(head);
	}
}
