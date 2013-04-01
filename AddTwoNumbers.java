package leetcode;

import java.util.LinkedList;

/*
 * You are given two linked lists representing two non-negative numbers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 *  Add the two numbers and return it as a linked list.
IUNPUT: (2 -> 4 -> 3) + (5 -> 6 -> 4) (i.e. 342 + 465)
OUTPUT: 7 -> 0 -> 8 (i.e. 342 + 465 = 807)
 * 
 * */
public class AddTwoNumbers {
	public static LinkedList<Integer> addTwoNumbers(LinkedList<Integer> link1, LinkedList<Integer> link2) {
		if (link1 == null && link2 == null) {
			return null;
		} else if (link1 == null) {
			return link2;
		} else if (link2 == null) {
			return link1;
		}
		
		LinkedList<Integer> result = new LinkedList<Integer>();
		int cp = 0; //current position
		int maxLength = link1.size() >= link2.size() ? link1.size() : link2.size();
		int carry = 0;
		
		while (cp < maxLength) {
			int sum = (cp < link1.size() ? link1.get(cp) : 0) + (cp < link2.size() ? link2.get(cp) : 0) + carry;
			if (sum >= 10) {
				carry = 1;
				sum = sum - 10;
			} else {
				carry = 0;
			}
			
			result.add(cp, sum);
			cp++;
		}
		
		if (carry == 1) {
			result.add(cp, carry);
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> link1 = new LinkedList<Integer>();
		link1.add(2);
		link1.add(4);
		link1.add(6);
		
		LinkedList<Integer> link2 = new LinkedList<Integer>();
		link2.add(5);
		link2.add(6);
		link2.add(4);
		
		LinkedList<Integer> result = addTwoNumbers(link1, link2);
		
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + " ");
		}
	}
}
