package leetcode;

/*
 * Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].

solution:
find a position such that num[position] = target
then divide this array into 2 parts, 0 - position-1 and position+1 to num.length-1;
find the first element that smaller than target in the first half, and the first element that larger than target in the second half
 * */
public class SearchForARange {
	public static int[] searchRange(int[] num, int target) {
		int[] result = new int[2];
		result[0] = -1;
		result[1] = -1;
		if (num == null || num.length == 0) {
			return result;
		}
		
		// binary serach , find target position first
		int left = 0;
		int right = num.length-1;
		int position = -1;
		while (left <= right) {
			int middle = (left+right)/2;
			if (num[middle] == target) {
				position = middle;
				break;
			} else if (num[middle] > target) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		
		if (position == -1) {
			// no target
			return result;
		}
		
		// now we are going to search the left part of postion, find the first element that smaller than target
		left = 0;
		right = position-1;
		// every element from num[left] to num[right] is <= target
		while (left <= right) {
			int middle = (left+right)/2;
			if (num[middle] == target) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		// when the loop breaks,right is the first element that smaller than target;
		// the last condition of the loop is always : left == right == middle
		// at this point, if num[middle] == target, right = middle - 1; 
		// else num[middle]  != null target. right is already the  first element that smaller than target;
		result[0] = right + 1;
		
		// do the same thing to the second half
		// num[j] >= target, position < j <= num.lenght-1
		left = position+1;
		right = num.length-1;
		while (left <= right) {
			int middle = (left+right)/2;
			if (num[middle] == target) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
		result[1] = left-1;
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] num = {6,7,8,8,9,10};
		int[] result = searchRange(num, 8);
		for (int i : result) {
			System.out.print(i + " ");
		}
	}
}
