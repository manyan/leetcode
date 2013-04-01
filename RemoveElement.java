package leetcode;

/*
 * Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

eg: {1,3,2,1,1,4} -> {3,2,4}
 * */
public class RemoveElement {
	public static int removeElement(int[] array, int target) {
		if (array == null || array.length == 0) {
			return 0;
		}
		
		int j = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != target) {
				array[j] = array[i];
				j++;
			}
		}
		
		return j;
	}
	
	public static void main(String[] args) {
		int[] array = {1,1,3,2,1,4};
		System.out.println(removeElement(array,1));
	}
}
