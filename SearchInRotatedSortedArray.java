package leetcode;
/*
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

solution: the point is not trying to find the pivot, cause we do not need it!
4 5 6 7 0 1 2, we can know that 7 is the pivot, it divide the array into first half and second half
and both halfs are increasing
do it as binary search, O(lgb)
 * */
public class SearchInRotatedSortedArray {
	public static int searchInRotatedSortedArray(int[] num, int target) {
		if (num == null || num.length == 0) {
			return -1;
		}
		
		int left = 0;
		int right = num.length - 1;
		
		while (left <= right) {
			// condition is left <= right to avoid the following condition
			// left = 0; right = 2, middle = 1, but num[middle] != target
			// then right has a chance to be middle - 1 which is 0, so we miss the chance to 
			// detect whether num[left] == target
			int middle = (left+right) / 2;
			if (num[middle] == target) {
				// find it , return
				return middle;
			}
			
			if (num[left] <= num[middle]) {
				// first half is in increasing order
				// its <= , because middle can == left, which means the first half only have one element
				if (num[left] <= target && target < num[middle]) {
					// from left to middle. array in ASC mode, which means num[middle] is the largest element of the first half
					// and mean while if num[left] <= target < middle
					// do binary search on the first half
					right = middle-1;
				} else {
					// if target < num[left]
					// target can not exist in the first half, so we just need to drop the first half
					// and start search target in the second half
					left = middle+1;
				}
			} else {
				// the second half is in increasing order
				if (num[middle] < target && target <= num[right]) {
					left = middle + 1;
				} else {
					right = middle - 1;
				}
			}
		}
		
		return -1;
	}
	
	// num is a sorted array
	public static int binarySearch(int[] num, int target) {
		int left = 0;
		int right = num.length - 1;
		while (left <= right) {
			int middle = (left+right)/2;
			if (num[middle] == target) {
				return middle;
			} else if (num[middle] < target) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
		
		return -1;
	}
	
	// num is a sorted array, if target is not in the array, return the one that closet to the target
	public static int binarySearchWithTheClosestNum(int[] num, int target) {
		int left = 0;
		int right = num.length - 1;
		while (left <= right) {
			int middle = (left+right)/2;
			if (num[middle] == target) {
				return middle;
			} else if (num[middle] < target) {
				left = middle + 1;
			} else {
				right = middle - 1;
			}
		}
		
		// when we reach here, means target is not in the array, so we need to find the closet element from num[left] or num[right]
		// notice that, right is smailler than left now, according to the while condition, if it breaks, left > right
		return Math.abs(target - num[left]) > Math.abs(target - num[right]) ? right : left;
	}
	
	public static void main(String[] args) {
		int[] num = {3,4,5,6,7,0,1,2};
		System.out.println(searchInRotatedSortedArray(num,1));
	}
}
