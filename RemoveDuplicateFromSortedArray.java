package leetcode;

/*
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array A = [1,1,2],

Your function should return length = 2, and A is now [1,2].
 * */
public class RemoveDuplicateFromSortedArray {
	public static int removeDuplicate(int[] array) {
		if (array == null || array.length == 0)
			return 0;
		
		int j = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[j] != array[i]) {
				j++;
				array[j] = array[i];
			}
		}
		
		return j+1;
	}
	
	public static void main(String[] args) {
		int[] array = {1,1,1,3,4,5};
		System.out.println(removeDuplicate(array));
	}
}
