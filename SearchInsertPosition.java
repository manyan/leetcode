package leetcode;
/*
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
 * 
 * 
 *solution: binary search
 * */
public class SearchInsertPosition {
	public static int insertPosition(int[] array, int target) {
		if (array == null || array.length == 0) {
			return 0;
		}
		
		int left = 0;
		int right = array.length-1;
		int middle = 0;
		while (left <= right) {
			middle = (left+right)/2;
			if (array[middle] == target) {
				return middle;
			} else if (array[middle] < target) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
		// the final round of the loop is always middle == left == right
		if (array[middle] > target) {
			return middle;
		} else {
			return middle + 1;
		}
	}
	
	public static void main(String[] args) {
		int[] array = {1,3,5};
		System.out.println(insertPosition(array,4));
	}
}
