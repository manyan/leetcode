package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 * 
 * idea:
 * scan the original array, 
 * 1) its a duplicate element, ignore it
 * 2) consider each element is a window(eg, [i,i], [i,k] means from i to k, because its a single elment , so k = i)
 * 3) array[i] = k, and there is an existing range [k-1,a] with a <= k-1, merge k to that range, remove the orignal range
 * 4) if there is an existing range [k+1,b] with b >= k+1. merge k to that range, remove the original range
 * 5) add the new range and the reverse of the new range into ranges
 * the reason we need to keep the new range and the reverse of the new range is because:
 * new range is [2,5], reverse is : [5,2]
 * then we have 6 comes in, so we need to merge 6 into the current range
 * the the left of the current range is get from the right of [5,2], so the new range is [2,6]
 * then we have 1 comes in, so we need to merge 1 into the current range, 
 *
 * */
public class LongestConsectiveSequeue {
	public static void getLongestConsectiveSequence(int[] array) {
		if (array.length == 0) {
			return;
		} 
		
		Map<Integer, Integer> ranges = new HashMap<Integer, Integer>();
		Set<Integer> unique = new HashSet<Integer>();
		int maxLength = 1;
		int maxLeft = array[0];
		int maxRight = array[0];
		
		for (int i = 0; i < array.length; i++) {
			if (unique.contains(array[i])) {
				// ignore duplicate element
				continue;
			} else {
				// deal with new element
				int currentElement = array[i];
				int leftOfNewRange = currentElement;
				int rightOfNewRange = currentElement;
				
				if (ranges.containsKey(currentElement-1) && ranges.get(currentElement-1) <= currentElement-1) {
					leftOfNewRange = ranges.get(currentElement-1);
					ranges.remove(currentElement-1);
				}
				
				if (ranges.containsKey(currentElement+1) && ranges.get(currentElement+1) >= currentElement+1) {
					rightOfNewRange = ranges.get(currentElement+1);
					ranges.remove(currentElement+1);
				}
				
				ranges.put(leftOfNewRange, rightOfNewRange);
				ranges.put(rightOfNewRange, leftOfNewRange);
				
				if (rightOfNewRange - leftOfNewRange + 1 > maxLength) {
					maxLeft = leftOfNewRange;
					maxRight = rightOfNewRange;
				}
			}
		}
		
		for (int i = maxLeft; i <= maxRight; i++) {
			System.out.print(i + " ");
		}
	}
	
	public static void main(String[] args) {
		int[] array = {10,4,7,6,12,5};
		getLongestConsectiveSequence(array);
	}
}
